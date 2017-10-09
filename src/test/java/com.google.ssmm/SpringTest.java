package com.google.ssmm;

import com.google.ssmm.config.AOPConfig;
import com.google.ssmm.config.DataConfig;
import com.google.ssmm.config.DataSourceConfig;
import com.google.ssmm.config.WebAppInitializer;
import com.google.ssmm.config.WebConfig;
import com.google.ssmm.dao.FruitMapper;
import com.google.ssmm.entity.Fruit;
import com.google.ssmm.service.FirstServiceInterface;
import com.google.ssmm.service.SecondServiceInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xuhan on 16-12-13.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class,
    AOPConfig.class, WebConfig.class, WebAppInitializer.class, DataConfig.class})
public class SpringTest {
    private Logger logger = LoggerFactory.getLogger(SpringTest.class);

    @Autowired
    private FirstServiceInterface firstService;

    @Autowired
    private SecondServiceInterface secondService;

    @Autowired
    private FruitMapper fruitMapper;

    @Test
    public void testTransaction() {
        for(int i=0;i<=10000;i++){
            String[] colours = new String[]{"BLACK","WHITE","RED","GREEN","BLUE","GREEN","GRAY","VIOLET","YELLOW"};
            Fruit fruit = new Fruit(null, UUID.randomUUID().toString(),colours[i%colours.length],null);
            fruitMapper.insertFruit(fruit);
        }
    }

    @Test
    public void testFormat() {
        String format = String.format("许iuhdiahdiuh%sdshosdhiaushui%s", "虚汗1", "虚汗2");
        System.out.println(format);
    }

    @Test
    public void test1() {
        String[] a = {"12", "123"};
        System.out.println(a.getClass());

        logger.info("xuhan{}", 3);
    }

    @Test
    public void testQ() {
        long a = new Date().getTime();
        long timestamp = a / 1000;
        System.out.println(timestamp);
    }

    public Map<String, String> getSss() {
        Map<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("xuhan", "hanhan");
        return stringStringHashMap;
    }

    @Test
    public void test() throws Exception {
        // 构造URL
        File file = File.createTempFile("upload-", "bin");
        String filename = file.getAbsolutePath();
        URL url = new URL(
            "http://wx.qlogo.cn/mmopen/Q3auHgzwzM5rCq5m1vxljmib66jmryE1a33hjIbnjfMKafgR2FrGs0OBNMBJtl4810qwCTmNic1byvGM9rCzZTeK97av4zeP06Hib5BpicuicMlo/0");
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }
    //-------------------------------------------------------------------------------------

    @Test
    public void testMapper()throws Exception{
        Fruit fruit = fruitMapper.selectById(1l);

        System.out.println(fruit);
    }

    @Test
    public void testSql()throws Exception{
        //List<Map<Object, Object>> map = fruitMapper.selectExample();
        //System.out.println(123);

        //ReentrantLock lock =
    }

}
