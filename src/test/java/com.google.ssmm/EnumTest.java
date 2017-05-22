package com.google.ssmm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.ssmm.controller.Controller1;
import com.google.ssmm.entity.Student;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.Executors;

/**
 * Created by xuhan on 16-11-18.
 */
public class EnumTest {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(EnumTest.class);

    @Test
    public void testSeason() {
        System.out.println(Season.AUTUMN.getValue());

        //enum这种类型自带的方法
        Season spring = Season.valueOf("AUTUMN");
        System.out.println(spring.getClass());
        //返回的是value。aaa
        System.out.println(spring.getValue());
        //返回的是value对应的那个值.SPRING
        System.out.println(spring.name());
        System.out.println(Season.values().length);
        System.out.println(spring.ordinal());
    }

    @Test
    public void testOther() {
        Student st = new Student();
        st.setName(null);
    }

    @Test
    public void testSplit() {
        SecureRandom sr = new SecureRandom();
        int password = sr.nextInt(99999);
        int count = 0;
        while (password < 10000) {
            count++;
            password = sr.nextInt(99999);
        }
        System.out.println(count);
    }

    @Test
    public void testSD() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println(sdf.format(new Date()));
    }

    @Test
    public void testSub() {
        String s = "18655049262";
        System.out.println(s.substring(s.length() - 4, s.length()));
    }

    @Test
    public void testSort() {
        Student student = new Student(12, BedShapeType.SINGLE_BED.getValue());
        Student student2 = new Student(9, BedShapeType.OTHER.getValue());
        Student student3 = new Student(14, BedShapeType.DOUBLE_BED.getValue());
        List<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(student2);
        students.add(student3);

        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                //先排年龄
                return BedShapeType.valueOf(s1.getName()).ordinal() - BedShapeType.valueOf(s2.getName()).ordinal();
            }
        });

        for (Student s : students) {
            System.out.println(s.toString());
        }
    }

    @Test
    public void testCollection() {
        ArrayList<String> a1 = new ArrayList<>();
        ArrayList<String> a2 = new ArrayList<>();

        a1.add("15");
        a1.add("4");
        a2.add("1");
        a1.addAll(a2);
        System.out.println(a1);
    }

    /**
     * 遍历对象的全部属性
     */
    @Test
    public void testReflectionFields() {
        Controller1 con = new Controller1();
        Class<? extends Controller1> a = con.getClass();
        Field[] fields = a.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("name:" + field.getName() + ",type:" + field.getType());
            System.out.println(Modifier.toString(field.getModifiers()));
        }
        System.out.println("----------------------------------");
        Method[] methods = a.getMethods();
        int i = 1;
        for (Method method : methods) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> returnType = method.getReturnType();
            for (Class parameterType : parameterTypes) {
                System.out.println(parameterType.getName() + " -> " + returnType.getName());
            }
            Annotation[] annotations = method.getAnnotations();
            System.out.println(annotations.toString());
            System.out.println("--------------------" + (i++) + "------------------method.name:" + method.getName());
        }
    }

    /**
     * ArrayUtils的一些基本用法
     */
    @Test
    public void testArray() {
        int[] intArray = new int[] {1, 2, 32, 12, 1};
        int[] intArray2 = new int[] {22, 11, 33};
        int[] ints = ArrayUtils.removeElement(intArray, 2);
        int[] ints1 = ArrayUtils.addAll(intArray, intArray2);
        ArrayUtils.reverse(ints1);
        System.out.println(ints1);
    }


    /**
     * 判断数组中是否包含某个数的时候
     */
    @Test
    public void testArray2() {
        Integer[] arrays = new Integer[] {1, 3, 2};
        boolean contains = Arrays.asList(arrays).contains(1);
        System.out.println(contains);
    }

    /**
     * 看是否是多个线程共享同一个资源
     */
    @Test
    public void testMultipleThread() {
        Runnable thread = new Thread() {
            @Override public void run() {
                System.out.println(currentThread().hashCode() + "-" + currentThread().getName());
            }
        };
        new Thread(thread, "1").start();
        new Thread(thread, "2").start();
    }

    @Test
    public void test1() {
        logger.info("123:", 123);
    }

    @Test
    public void testA() {
        List<Student> s = null;
        for (Student s1 : s) {
            System.out.println(s.toString());
        }
    }

    @Test
    public void testObjectMapper() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        //设置为false遇到不认识的字段,或者没有setter的字段就不会报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
       /* Student student = new Student(1,"xuhan");
        String s = mapper.writeValueAsString(student);
        System.out.println(s);*/
        String s = "{\"studentId\":1,\"name\":\"xuhan\",\"sd\":\"123\"}";
        System.out.println(mapper.readValue(s, Student.class));
    }

    @Test
    public void testJNI(){
        String[] strs = new String[]{"1dqw","d21d"};
        String str = "1";
        System.out.println(strs.getClass());
        System.out.println(str.getClass());
    }

    @Test
    public void testDate(){
        Date date = new Date(new Date().getTime() - 2 * 60 * 60 * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        String format = sdf.format(date);
        System.out.println(format);
    }

    @Test
    public void testStringSplice(){
        String s = "BCH-0XR81LG";
        String substring = s.substring(4, s.length());
        System.out.println(substring);
    }

    @Test
    public void testEnv(){
        Map<String, String> maps = System.getenv();
        for(Map.Entry<String,String> entry : maps.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    @Test
    public void testSDS(){
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.toString().endsWith(","));
    }

    @Test
    public void testLiteral(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date beginDate = cal.getTime();

        cal.add(Calendar.DATE, 1);
        Date endDate = cal.getTime();
        System.out.println(123);
    }

    @Test
    public void testStringButeSize() throws UnsupportedEncodingException {
        String a = "123asd123";
        System.out.println("size:"+a.getBytes("Unicode").length);
        System.out.println("size:"+a.getBytes("utf-8").length);

        String b = "许寒茶色哦电话哦地区和文档";
        System.out.println("size:"+b.getBytes("Unicode").length);
        System.out.println("size:"+b.getBytes("utf-8").length);

    }

    @Test
    public void testSas() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date endDate = new Date(new Date().getTime() + 7* 24 * 60 * 60 * 1000);
        Date newDate = sdf.parse(sdf.format(endDate));
        System.out.println(newDate);
        System.out.println(newDate.getTime());
    }
}
