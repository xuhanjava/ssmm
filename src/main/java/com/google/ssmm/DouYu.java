package com.google.ssmm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DouYu {
    private String rid;
    private String did;
    private String t10;
    private String t13;

    public DouYu(String rid) throws IOException {
        this.did = "10000000000000000000000000001501";
        this.t10 = String.valueOf(System.currentTimeMillis() / 1000);
        this.t13 = String.valueOf(System.currentTimeMillis());

        this.rid = getRealRoomID(rid);
        if (this.rid == null) {
            throw new IOException("房间号错误");
        }
    }

    private static String md5(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    private String getRealRoomID(String rid) throws IOException {
        URL url = new URL("https://m.douyu.com/" + rid);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        Pattern pattern = Pattern.compile("rid\":(\\d{1,8}),\"vipId");
        Matcher matcher = pattern.matcher(response.toString());
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    private String getPre() throws IOException, NoSuchAlgorithmException {
        String url = "https://playweb.douyucdn.cn/lapi/live/hlsH5Preview/" + rid;
        String data = "rid=" + rid + "&did=" + did;
        String auth = md5(rid + t13);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("rid", rid);
        connection.setRequestProperty("time", t13);
        connection.setRequestProperty("auth", auth);
        connection.setDoOutput(true);
        connection.getOutputStream().write(data.getBytes());

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Process the JSON response and extract the key
        // ...

        return response.toString(); // Replace this with the extracted key
    }

    private String getJs() throws IOException, NoSuchAlgorithmException {
        // Extract the JavaScript code from the webpage
        // ...

        // Process the JavaScript code to calculate the parameters
        // ...

        // Send a POST request to "https://m.douyu.com/api/room/ratestream"
        // and process the response to get the complete streaming URL
        // ...

        return null; // Replace this with the complete streaming URL
    }

    private String getPcJs(String cdn, int rate) throws IOException, NoSuchAlgorithmException {
        // Extract the JavaScript code from the PC webpage
        // ...

        // Process the JavaScript code to calculate the parameters
        // ...

        // Send a POST request to "https://www.douyu.com/lapi/live/getH5Play/{rid}"
        // with the calculated parameters and process the JSON response
        // ...

        return null; // Replace this with the complete streaming URL
    }

    public String[] getRealURL() throws IOException, NoSuchAlgorithmException {
        String key = getPre();
        if (key == null) {
            key = getJs();
        }
        String[] realURL = new String[2];
        realURL[0] = "http://vplay1a.douyucdn.cn/live/" + key + ".flv?uuid=";
        realURL[1] = "http://tx2play1.douyucdn.cn/live/" + key + ".xs?uuid=";
        return realURL;
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入斗鱼直播间号：");
        String rid = reader.readLine();
        reader.close();

        try {
            DouYu douYu = new DouYu(rid);
            String[] realURLs = douYu.getRealURL();
            System.out.println("FLV URL: " + realURLs[0]);
            System.out.println("x-p2p URL: " + realURLs[1]);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("NoSuchAlgorithmException: " + e.getMessage());
        }
    }
}

