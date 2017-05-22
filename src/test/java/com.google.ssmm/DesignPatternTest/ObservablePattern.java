package com.google.ssmm.DesignPatternTest;


import java.util.Observable;
import java.util.Observer;

/**
 * Created by xuhan on 17-4-17. 其实就是看源码 Observable对象
 */
public class ObservablePattern {
    /**
     * 类似客户端调用 气象局的作用
     * @param args
     */
    public static void main(String[] args) {
        Earth e = new Earth();
        Satellite satellite = new Satellite();
        e.addObserver(satellite);
        e.setWeather("台风快来了");
        e.setWeather("来了");
        e.setWeather("走了");
    }
}


/**
 * 被观察对象:地球
 */
class Earth extends Observable {
    private String weather;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
        //设置变化点
        setChanged();
        //通知观察者
        notifyObservers(weather);
    }
}


/**
 * 观察对象
 */
class Satellite implements Observer {

    private String weather;

    @Override public void update(Observable obj, Object arg) {
        weather = (String) arg;
        //捕获天气异常，反馈给检查者
        System.out.println(" 近期天气变化情况：" + weather);
    }
}
