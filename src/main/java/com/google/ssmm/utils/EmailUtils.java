package com.google.ssmm.utils;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class EmailUtils {
    private static List<String> fromEmailList = new ArrayList<>(Arrays.asList("xuhan_code@126.com"));
    private static String toEmail = "waltercode@126.com";

    private static Map<String,Long>  lastSendUnixMap =  new HashMap<>();

    public static  boolean sendEmail(String subject,String content) {
        //5min发一次
        String sendKey = subject;
        lastSendUnixMap.putIfAbsent(sendKey,0L);
        if(System.currentTimeMillis() - lastSendUnixMap.get(sendKey) <  20 * 1000 ){
            return true;
        }
        for(int i =0;i<fromEmailList.size();i++){
            // 收件人邮箱地址
            // 发件人邮箱地址
            String fromEmail = fromEmailList.get(i);
            // 发件人邮箱密码或授权码
            String password = FileUtils.getFileValue(FileUtils.PASS_FILE).get(fromEmail);

            // 配置邮件服务器参数
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.126.com"); // 邮件服务器主机名
            props.put("mail.smtp.port", "25"); // 邮件服务器端口
            props.put("mail.smtp.auth", "true"); // 需要验证身份

            // 创建邮件会话
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            try {
                // 创建邮件消息
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(fromEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                message.setSubject(subject);
                message.setText(content);

                // 发送邮件
                Transport.send(message);

                System.out.println("邮件发送成功！");
                lastSendUnixMap.put(sendKey,System.currentTimeMillis());
                return true;
            } catch (MessagingException e) {
                System.out.println(fromEmail +"邮件发送失败：" + e.getMessage());
            }
        }
        return false;
    }
}
