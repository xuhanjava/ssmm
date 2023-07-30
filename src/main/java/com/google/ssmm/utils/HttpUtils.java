package com.google.ssmm.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpUtils {
    public static String getHexunResp(String apiURL) {
        StringBuilder responseBuilder = new StringBuilder();
        try {
            // Create a URL object with the API URL
            URL url = new URL(apiURL);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Accept-Charset", "gb2312");
            //connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\n");
//            connection.setRequestProperty("Accept-Encoding","gzip, deflate");
//            connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.9");
            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.setRequestProperty("Cookie", "ADHOC_MEMBERSHIP_CLIENT_ID1.0=8bd9e9b4-0637-5703-5522-6d518d7eabaa; hxck_cd_sourceteacher=sR%2FuPcnSSZVIdShwHag3RAnrY9aauRbMjEnRBtq%2FNF1ooDP7obDVPgaQGWxsj76JqbJObjTyc6GbSipnNuzgOzeX6Xt2ApZ%2FBmbbVGDadBReQgl0KVWchhCkfV3GUaetLSIS%2BpszfgkYDx2z%2Fok0vhEqewizXG2r%2FmqWXJ7p85cwDKqSP1UoQqZ81BcOtHbL; Hm_lvt_335e8db9c1ab7e05236db267e3be61fb=1690190408; HexunTrack=SID=20230724172008146c8331b477ea749929159ae8aeda75878&CITY=0&TOWN=0; __utma=194262068.1143037316.1690190408.1690190408.1690190408.1; __utmc=194262068; __utmz=194262068.1690190408.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmt=1; ADHOC_MEMBERSHIP_CLIENT_ID1.0=8bd9e9b4-0637-5703-5522-6d518d7eabaa; ASP.NET_SessionId=jihoohw1xafbi54vswa410f5; Hm_lvt_335e8db9c1ab7e05236db267e3be61fb=1690190408; hxck_webdev1_general=stocklist=002739_2|000002_2; Hm_lpvt_335e8db9c1ab7e05236db267e3be61fb=1690190469; hxck_cd_channel_order_mark1=tKK6EMkJ7JK75WOJ%2FqluxbbMrhZQZtn9if6%2FTggkwv05ylPHXG7FcjDGIS%2B95uu3e%2FXIL6y4NMcFPLL8JhS0uUfTHebbxBD95FRlsFZsFd4Ckny3dD%2FoFX%2FvSoilYcOesYbU8CYrW%2Bf%2BAWotP%2F2hXQ%3D%3D; __utmb=194262068.14.10.1690190408; Hm_lpvt_335e8db9c1ab7e05236db267e3be61fb=1690191932");
            connection.setRequestProperty("Host", "stockdata.stock.hexun.com");
            connection.setRequestProperty("Pragma", "no-cache");
            connection.setRequestProperty("Proxy-Connection", "keep-alive");
            connection.setRequestProperty("Referer", apiURL);
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
            // Set the request method to GET (or POST, PUT, DELETE, etc. if needed)
            connection.setRequestMethod("GET");
            connection.setReadTimeout(1000);


            int responseCode = connection.getResponseCode();
            //System.out.println("Response Code: " + responseCode);

            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gb2312"));

            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            // Get the response body as a String
            String responseBody = responseBuilder.toString();

            // Print or process the response body as needed
//            System.out.println("Response Body:");
//            System.out.println(responseBody);

            // Disconnect the connection
            connection.disconnect();
            return responseBody;
        } catch (IOException e) {

            // Get the response body as a String
            String responseBody = responseBuilder.toString();

            // Print or process the response body as needed
//            System.out.println("Response Body:");
//            System.out.println(responseBody);

            // Disconnect the connection
            return responseBody;
        }
    }

    public static String getXueQiuResp(String apiURL) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            // 设置请求URL
            String url = apiURL;

            // 创建HttpGet请求对象
            HttpGet httpGet = new HttpGet(url);

            // 设置请求头信息，如果需要的话
            httpGet.addHeader("User-Agent", "Mozilla/5.0");

            // 执行请求并获取响应
            response = httpClient.execute(httpGet);

            // 获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();

            // 获取响应实体
            HttpEntity entity = response.getEntity();

            // 将实体内容转换为字符串
            String responseBody = EntityUtils.toString(entity);

            // 打印响应内容
            System.out.println(responseBody);
            return responseBody;
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}