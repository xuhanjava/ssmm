package com.google.ssmm.finance.xueqiu;

import com.google.ssmm.utils.EmailUtils;
import com.google.ssmm.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.ssmm.utils.MacDialogBoxUtils;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * @author xuhan
 * @date 2023/7/28 2:41 下午
 */
public class XueQiuMain {
    public static final String financeURL = "https://stock.xueqiu.com/v5/stock/realtime/quotec.json?symbol=%s&_=%s";

    public static void main(String[] args) throws InterruptedException {
        //https://stock.xueqiu.com/v5/stock/realtime/quotec.json?symbol=SH513130&_=1690526210194
        long time = System.currentTimeMillis();
        while (true) {
            String resp = HttpUtils.getXueQiuResp(String.format(financeURL, "SH513130", time));
            parseString(resp, new Double(0.608), 0.508, 0.504);
            Thread.sleep(1000);
        }
    }

    public static void parseString(String jsonString, Double buyPrice, Double alertUpperPrice, Double alertLowerPrice) {
        DecimalFormat percentageFormat = new DecimalFormat("0.00%");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

        if (jsonObject != null) {
            JsonArray dataArray = jsonObject.getAsJsonArray("data");
            if (dataArray != null && dataArray.size() > 0) {
                JsonObject stockData = dataArray.get(0).getAsJsonObject();

                String symbol = stockData.get("symbol").getAsString();
                double current = stockData.get("current").getAsDouble();
                double percent = stockData.get("percent").getAsDouble();
                double chg = stockData.get("chg").getAsDouble();
                long timestamp = stockData.get("timestamp").getAsLong();
                long volume = stockData.get("volume").getAsLong();
                double amount = stockData.get("amount").getAsDouble();
                double marketCapital = stockData.get("market_capital").getAsDouble();
                double amplitude = stockData.get("amplitude").getAsDouble();
                double open = stockData.get("open").getAsDouble();
                double lastClose = stockData.get("last_close").getAsDouble();
                double high = stockData.get("high").getAsDouble();
                double low = stockData.get("low").getAsDouble();
                double avgPrice = stockData.get("avg_price").getAsDouble();
                double currentYearPercent = stockData.get("current_year_percent").getAsDouble();

//                System.out.println("股票代码: " + symbol);
                System.out.println(new Date() + ",当前价: " + current);
//                System.out.println("当日新增比: " + percent);
//                System.out.println("Change: " + chg);
//                System.out.println("Timestamp: " + timestamp);
//                System.out.println("Volume: " + volume);
//                System.out.println("Amount: " + amount);
//                System.out.println("Market Capital: " + marketCapital);
//                System.out.println("Amplitude: " + amplitude);
//                System.out.println("Open Price: " + open);
//                System.out.println("Last Close Price: " + lastClose);
//                System.out.println("High Price: " + high);
//                System.out.println("Low Price: " + low);
//                System.out.println("Average Price: " + avgPrice);
//                System.out.println("Current Year Percentage: " + currentYearPercent);

                if (alertLowerPrice != null) {
                    if (current <= alertLowerPrice) {
                        String content =  String.format("warn! 价格已突破下限 当前价格 %s 下限价格 %s 购买价格 %s 当前升降比例 %s", current, alertLowerPrice, buyPrice, "" + (percentageFormat.format(buyPrice / current - 1)));
                        System.out.println(content);
                        EmailUtils.sendEmail(symbol +"下限突破",content);
                        //MacDialogBoxUtils.displayDialog("下");
                    }
                }

                if (alertUpperPrice != null) {
                    if (current >= alertUpperPrice) {
                        String content = String.format("warn! 价格已突破上限 当前价格 %s 上限价格 %s 购买价格 %s 当前升降比例 %s", current, alertUpperPrice, buyPrice, "" + (percentageFormat.format(buyPrice / current - 1)));
                        System.out.println(content);
                        EmailUtils.sendEmail(symbol+"上限突破",content);
                        //MacDialogBoxUtils.displayDialog("上");

                    }
                }
            }
        }
    }
}