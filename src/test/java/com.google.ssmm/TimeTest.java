package com.google.ssmm;

import org.junit.Test;

import java.util.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * jdk1.8新增的时间类 不可变且线程安全的类
 * Java 8中新的时间与日期API中的所有类都是不可变且线程安全的，这与之前的Date与Calendar API中的恰好相反，那里面像java.util.Date以及SimpleDateFormat这些关键的类都不是线程安全的
 * 就是说当多线程并发调用的时候结果会和串行调用结果不一样
 * Created by xuhan
 * 2017/9/26
 * 下午8:47
 */
public class TimeTest {

    /**
     * 不返回时间的当前日期
     */
    @Test
    public void testLocalDate(){
        LocalDate now = LocalDate.now();
        System.out.println("当前时间"+now.toString());

        //返回哪年，哪月，哪日
        int year = now.getYear();
        int monthValue = now.getMonthValue();
        int dayOfYear = now.getDayOfYear();
        int dayOfMonth = now.getDayOfMonth();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println("year:"+year+",month:"+monthValue+",dayOfYear:"+dayOfYear+",dayOfMonth:"+dayOfMonth+",dayOfWeek:"+dayOfWeek);

        //获取某个特定的日期
        LocalDate dateOfMan = LocalDate.of(2011, 11, 11);
        System.out.println(dateOfMan);
        //同理 LocalTime.of()

        //是否是闰年
        now.isLeapYear();

        //两个日期之间包含多少天/月/年
        Period between = Period.between(now, dateOfMan);
        System.out.println(between+":"+between.getYears()+"-"+between.getMonths()+"-"+between.getDays());
    }

    /**
     * 不返回日期的当前时间
     */
    @Test
    public void testLocalTime(){
        LocalTime now = LocalTime.now();
        System.out.println(now.toString());

        //开始增加时间了
        LocalTime twoHoursLater = now.plusHours(2);
        System.out.println(twoHoursLater.toString());
    }

    /**
     * 不区分时区
     */
    @Test
    public void testLocalDateTime(){
        LocalDateTime nowDateTime = LocalDateTime.of(2017, 9, 26, 21, 1, 11);
        System.out.println(nowDateTime);

        LocalDateTime twoDaysBefore = nowDateTime.plusDays(-2);
        System.out.println(twoDaysBefore);

        //增加一周
        LocalDateTime oneWeeksLater = nowDateTime.plus(1, ChronoUnit.WEEKS);
        System.out.println(oneWeeksLater);

        //nowDateTime.minus 一天前

        //比较两个时间前后顺序
        LocalDateTime comparedTime = LocalDateTime.of(2017, 8, 11, 12, 11);
        boolean before = comparedTime.isBefore(LocalDateTime.now());
        System.out.println(before);

    }

    /**
     * 根据账号注册日期发现生日
     */
    @Test
    public void testBirth(){
        LocalDate dateOfBirth = LocalDate.of(2010, 9, 26);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(MonthDay.now());
        if(currentMonthDay.equals(birthday)){
            System.out.println("Many Many happy returns of the day !!");
        }else{
            System.out.println("Sorry, today is not your birthday");
        }
    }

    /**
     * 判断当月/年有多少天
     */
    @Test
    public void testCountDayOfMonth(){
        YearMonth now = YearMonth.now();
        int monthDayCount = now.lengthOfMonth();
        int yearDayCount = now.lengthOfYear();
        System.out.println(monthDayCount+":"+yearDayCount);

    }

    /**
     * MM dd之类的和之前的SimpleDateFormat 样式一样
     * 将字符串转换为时间
     */
    @Test
    public void testDateFormatterToLocalDate(){
        String goodFriday = "12 18 2014";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
            LocalDate holiday = LocalDate.parse(goodFriday, formatter);
            System.out.printf("Successfully parsed String %s, date is %s%n", goodFriday, holiday);
        } catch (DateTimeParseException ex) {
            System.out.printf("%s is not parsable!%n", goodFriday);
            ex.printStackTrace();
        }
    }

    /**
     * 将时间转化为字符串
     */
    @Test
    public void testDateFormatterToString(){
        LocalDateTime arrivalDate = LocalDateTime.now();
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            String landing = arrivalDate.format(format);
            System.out.printf("Arriving at : %s %n", landing);
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", arrivalDate);
            ex.printStackTrace();
        }

    }

    /**
     * 将Instant 好像直接显示的是utc时间
     */
    @Test
    public void testInstant(){
        Instant now = Instant.now();
        //返回当前时间戳 但返回的不是是秒 不是毫秒
        long epochSecond = now.getEpochSecond();
        System.out.println(epochSecond);

        //可以和 Date之间相互转换
        Date dateNow = Date.from(now);
        Instant instant = dateNow.toInstant();
        System.out.println(dateNow.getTime()+":"+instant.getEpochSecond());
        System.out.println(now);
        System.out.println("-------------------");
        System.out.println(Instant.ofEpochMilli(epochSecond * 1000).atZone(ZoneId.systemDefault()).toLocalDateTime());
    }


}
