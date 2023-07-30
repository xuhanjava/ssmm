package com.google.ssmm.finance.hexun;

import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;
import java.util.Map;

//单笔手续费41
public class TestMain {
    public static void main(String[] args) {
        DecimalFormat percentageFormat = new DecimalFormat("0.00%");
        Map<String, Object> finance1 = FinanceAnalyze.getFinance("002714", "2022" + AccountDateEnum.THREE_SEASON.getAccountDate());
        Map<String, Object> finance2 = FinanceAnalyze.getFinance("002714", "2022" + AccountDateEnum.ALL_YEAR.getAccountDate());
        for (String key : finance1.keySet()) {
            String value1 = (String) finance1.get(key);
            String value2 = (String) finance2.get(key);
            if (NumberUtils.isNumber(value1)) {
                Double dValue1 = Double.valueOf(value1);
                Double dValue2 = Double.valueOf(value2);
                System.out.println(key + " :" + formatChinaDecimal(dValue1) + " vs " + formatChinaDecimal(dValue2) + ",  幅度："+
                        percentageFormat.format(dValue2/dValue1 -1) + "  ,数值："+formatChinaDecimal(dValue2-dValue1));
            } else {
                System.out.println(key + " :" + value1 + " vs " + value2);
            }
        }
    }

    private static String formatChinaDecimal(double value) {
        if (Math.abs(value) >= 1e8) {
            return formatNumber(value / 1e8) + "亿";
        } else if (Math.abs(value) >= 1e4) {
            return formatNumber(value / 1e4) + "万";
        } else {
            return formatNumber(value) + "元";
        }
    }

    // 格式化数字的方法（保留两位小数）
    private static String formatNumber(double value) {
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(value);
    }
    //1～2
    //流动资产合计 :543.72亿 vs 530.76亿,  幅度：-2.38%  ,数值：-12.97亿
    //流动资产合计 :565.84亿 vs 625.78亿,  幅度：10.59%  ,数值：59.94亿

    //流动负债合计 :908.80亿 vs 925.46亿,  幅度：1.83%  ,数值：16.66亿
    //流动负债合计 :862.74亿 vs 781.67亿,  幅度：-9.40%  ,数值：-81.07亿


}