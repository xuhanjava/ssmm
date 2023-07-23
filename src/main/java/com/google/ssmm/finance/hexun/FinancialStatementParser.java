package com.google.ssmm.finance.hexun;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class FinancialStatementParser {

    public static Map<String,Object>  parseFinancialStatement(String input) {
        FinancialStatement financialStatement = new FinancialStatement();

        int startIndex = 0;
        Map<String,Object> map = new LinkedHashMap<>();

        while (true) {
            int startFieldIndex = input.indexOf("<strong>", startIndex);
            int endFieldIndex = input.indexOf("</strong>", startFieldIndex);
            if (startFieldIndex == -1 || endFieldIndex == -1) {
                break;
            }

            String field = input.substring(startFieldIndex + 8, endFieldIndex).trim();

            int startValueIndex = input.indexOf("<div class='tishi'>", endFieldIndex);
            int endValueIndex = input.indexOf("</div>", startValueIndex);
            String value = input.substring(startValueIndex + 19, endValueIndex).trim();
            value = removeCommas(value);

            map.put(field, value);

            startIndex = endValueIndex;
        }

        return map;
    }

    private static String removeCommas(String input) {
        return input.replaceAll(",", "");
    }


}



