package com.google.ssmm.DesignPatternTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by xuhan on 17-3-30.
 */
public class StrategyPatternAndSimpleFactoryPattern {
    public static void main(String[] args) {
        new Sumpermarket().getResult("打8折",new BigDecimal(100));
        new Sumpermarket().getResult("满300减100",new BigDecimal(1000));
    }
}

//使用BigDecimal表示double的一个结论 先用double转换成string 再转换成bigdecimal.
//http://www.cnblogs.com/mingforyou/p/3344489.html
class Sumpermarket {
    private Strategy strategy;

    public void getResult(String type,BigDecimal money){
        switch (type){
            case "正常收费":
                strategy = new Normal();
                break;
            case "打8折":
                strategy = new Dazhe(new BigDecimal(Double.toString(0.8)));
                break;
            case "满300减100":
                strategy = new Manjian(new BigDecimal(300),new BigDecimal(100));
        }
        strategy.acceptCash(money);
    }
}


/**
 * 相应的付款收费策略
 */
interface Strategy {
    void acceptCash(BigDecimal money);
}


class Normal implements Strategy {
    private BigDecimal normal;

    @Override public void acceptCash(BigDecimal money) {
        System.out.println(money + "应收金额:" + normal);
    }
}


class Dazhe implements Strategy {
    private BigDecimal zheKou;

    public Dazhe(BigDecimal zheKou) {
        this.zheKou = zheKou;
    }

    @Override public void acceptCash(BigDecimal money) {
        System.out.println(money + "应收金额:" + money.multiply(zheKou));
    }
}


class Manjian implements Strategy {
    private BigDecimal begin;
    private BigDecimal subvide;

    public Manjian(BigDecimal begin, BigDecimal subvide) {
        this.begin = begin;
        this.subvide = subvide;
    }

    @Override public void acceptCash(BigDecimal money) {
        System.out.println(money + "应收金额:" + money
            .subtract(new BigDecimal(money.divideToIntegralValue(begin, new MathContext(2,
                RoundingMode.DOWN)).doubleValue()).multiply(subvide)));
    }
}

