package com.google.ssmm.finance.hexun;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.ssmm.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Map;

public class FinanceAnalyze {

    public static final String financeURL = "http://stockdata.stock.hexun.com/2008/zcfz.aspx?stockid=%s&accountdate=%s";

    public static Map<String,Object> getFinance(String stockId, String accountDate) {
        String resp = HttpUtils.getHexunResp(String.format(financeURL, stockId, accountDate));
        //System.out.println(resp);

        // Parse the HTML using Jsoup
        Document doc = Jsoup.parse(resp);

        // Find the <span> element with id="ControlEx1_lbl"
        Element spanElement = doc.select("span#ControlEx1_lbl").first();

        // Extract the text content from the <span> element
        String extractedText = extractTextFromSpan(resp, "ControlEx1_lbl");

        // 打印提取的文本内容
        //System.out.println(extractedText );

        // Create an instance of FinancialStatement

        // Display extracted values
        Map result = FinancialStatementParser.parseFinancialStatement(extractedText);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(result);
            //System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String extractTextFromSpan(String html, String spanId) {
        String pattern = "<span\\s+id\\s*=\\s*\"" + spanId + "\">(.+?)</span>";
        java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern, java.util.regex.Pattern.DOTALL);
        java.util.regex.Matcher m = r.matcher(html);
        if (m.find()) {
            return m.group(1).trim();
        }
        return "";
    }
}