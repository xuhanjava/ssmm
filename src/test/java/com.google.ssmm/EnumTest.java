package com.google.ssmm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.ssmm.controller.Controller1;
import com.google.ssmm.entity.Apple;
import com.google.ssmm.entity.DESTools;
import com.google.ssmm.entity.Student;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xuhan on 16-11-18.
 */
public class EnumTest {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(EnumTest.class);

    private static final Set<Character> PHONE_CHAR_SET = Sets.newHashSet('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-');


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
        //CollectionUtils.isNot

    }

    @Test
    public void testSeason1() {
        System.out.println(Season.SPRING);
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
        int[] intArray = new int[]{1, 2, 32, 12, 1};
        int[] intArray2 = new int[]{22, 11, 33};
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
        Integer[] arrays = new Integer[]{1, 3, 2};
        boolean contains = Arrays.asList(arrays).contains(1);
        System.out.println(contains);
    }

    /**
     * 看是否是多个线程共享同一个资源
     */
    @Test
    public void testMultipleThread() {
        Runnable thread = new Thread() {
            @Override
            public void run() {
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
        String s = "{\"studentId\":1}";
        System.out.println(mapper.readValue(s, Student.class));
    }

    @Test
    public void testJNI() {
        String[] strs = new String[]{"1dqw", "d21d"};
        String str = "1";
        System.out.println(strs.getClass());
        System.out.println(str.getClass());
    }

    @Test
    public void testDate() {
        Date date = new Date(new Date().getTime() - 2 * 60 * 60 * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        String format = sdf.format(date);
        System.out.println(format);
    }

    @Test
    public void testStringSplice() {
        String s = "BCH-0XR81LG";
        String substring = s.substring(4, s.length());
        System.out.println(substring);
    }

    @Test
    public void testEnv() {
        Map<String, String> maps = System.getenv();
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    @Test
    public void testSDS() {
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.toString().endsWith(","));
    }

    @Test
    public void testLiteral() {
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
        System.out.println("size:" + a.getBytes("Unicode").length);
        System.out.println("size:" + a.getBytes("utf-8").length);

        String b = "许寒茶色哦电话哦地区和文档";
        System.out.println("size:" + b.getBytes("Unicode").length);
        System.out.println("size:" + b.getBytes("utf-8").length);

    }

    @Test
    public void testSas() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date endDate = new Date(new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
        Date newDate = sdf.parse(sdf.format(endDate));
        System.out.println(newDate);
        System.out.println(newDate.getTime());
    }

    @Test
    public void testSas1() {
        if (2 > 1 && 1 > 2) {
            System.out.println(true);
        }
        Lists.newArrayList();
    }

    @Test
    public void testSas12() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.format(new Date());
        try {
            simpleDateFormat.parse("2013-12-12");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Test
    public void testLambda3() {
        List<Apple> apples = new ArrayList<>();
        apples.add(null);

        apples.stream().forEach(a -> {
            if (a != null) {
                System.out.println(a.getname());
            }
        });
    }

    @Test
    public void testIterator() {
        //返回固定大小的list，你不能增加或者修改这个list.
        List<Integer> totalIdss = Arrays.asList(1, 3, 11, 3, 21);
        List<Integer> totalIds = new ArrayList<>();
        totalIds.add(1);
        totalIds.add(13);
        totalIds.add(11);
        totalIds.add(12);
        totalIds.add(3);
        totalIds.add(4);

        totalIds.add(131);
        List<Integer> tols = new ArrayList<>();
        tols.add(1);
        tols.add(11);

        Iterator<Integer> iterator = totalIds.iterator();
        while (iterator.hasNext()) {
            Integer temp = iterator.next();
            System.out.println(temp);
            if (temp > 10) {
                //  System.out.println(1);
                tols.remove(temp);
            }
        }

        System.out.println(totalIds);
        System.out.println(tols);
    }

    @Test
    public void testIterator1() {
        List<Integer> totalIds = new ArrayList<>();
        totalIds.add(1);
        totalIds.add(13);
        totalIds.add(11);
        totalIds.add(12);
        totalIds.add(3);
        totalIds.add(4);
        Iterator<Integer> iterators = totalIds.iterator();

        while (iterators.hasNext()) {
            Integer next = iterators.next();
            if (next > 10) {
                iterators.remove();
            }
        }
        System.out.println(totalIds);
    }

    /**
     * 查看lambda stream遍历之后是否还能再次遍历.可以再次遍历
     */
    @Test
    public void testLambdaBianli() {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(13);

        for (int i = 1; i < 3; i++) {
            objects.stream().filter(a -> a > 10).forEach(item -> {
                System.out.println(item);
            });
        }
    }

    @Test
    public void testRemoveNotExistsEmlements() {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(13);

        objects.remove(123l);
        System.out.println(objects);
    }

    @Test
    public void testSer() {
        String phone = "123456789";
        String star = "";
        for (int i = 0; i < phone.length() - 6; i++) {
            star += "*";
        }
        System.out.println(phone.substring(0, 3) + star + phone.substring(phone.length() - 3, phone.length()));
    }

    @Test
    public void testSe12() {
        testFun(1 | 2);
    }

    public void testFun(int i) {
        System.out.println(i);
    }

    @Test
    public void testLambda() {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(13);
        ArrayList<Integer> objects1 = new ArrayList<>();
        objects.stream().filter(a -> a > 10).forEach(item -> {
            System.out.println(item);
            objects1.add(12);
        });
        System.out.println(objects1);
    }

    @Test
    public void testSa() {
        Integer i = 3;
        System.out.println(i.toString());
    }

    @Test
    public void testLambda1() {

        TreeSet<String> collect = Stream.of(1, 2, 3, 4).map(a -> a + "v").collect(Collectors.toCollection(() -> new TreeSet<>()));
        TreeSet<String> collect1 = Stream.of(1, 2, 3, 4).map(a -> a + "v").collect(Collectors.toCollection(() -> new TreeSet<>()));

        System.out.println(collect);
    }

    @Test
    public void testStr() {
        String val = "查询\\催促预订状态";
        System.out.println(val);
    }

    @Test
    public void testDate12() {
        String date = "20170201";
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
        ;
        LocalDate localDate1 = localDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(localDate.equals(localDate1));
    }

    @Test
    public void testDateUtils() throws Exception {
        System.out.println(getDateTimeKey());
    }

    public static long getDateTimeKey() throws ParseException {
        LocalDate localDate1 = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        String format = localDate1.format(DateTimeFormatter.BASIC_ISO_DATE);

        return Long.valueOf(format);
    }

    @Test
    public void testYesDay() {
        String datekeyStr = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now().minusDays(1));
        System.out.println(Integer.parseInt(datekeyStr));
    }

    @Test
    public void testLogger() {
        try {
            throw new RuntimeException("this is error!");
        } catch (Exception e) {
            logger.error("detail", e);
        }
    }

    @Test
    public void testRunnable() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(123);
            }
        };
    }

    @Test
    public void testDouble() {
        long a = (long) 2.999999993d;
        System.out.println(a);
    }

    @Test
    public void testCalendar() {
        if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1) {
            System.out.println(Integer.valueOf(new SimpleDateFormat("yyyyMM").format(DateUtils.addMonths(new Date(), -1))));
        } else {
            System.out.println(Integer.valueOf(new SimpleDateFormat("yyyyMM").format(new Date())));
        }

        System.out.println(DateUtils.truncate(new Date(1496306401083l), Calendar.DATE).getTime());
        System.out.println(DateUtils.addDays(DateUtils.truncate(new Date(1498552801083l), Calendar.DATE), 1).getTime() - 1);
        System.out.println(DateUtils.truncate(new Date(), Calendar.MONTH).getTime());
        System.out.println(DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DATE).getTime() - 1);
    }

    @Test
    public void testOptional() {
        // @throws NullPointerException if value is null
        Optional o = Optional.of(1);
        //所以使用
        Optional o1 = Optional.ofNullable(1);
        System.out.println(o.get());
        Optional emptyOptional = Optional.empty();
        Optional alsoEmpty = Optional.ofNullable(null);

        System.out.println(DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DATE));
    }

    @Test
    public void testDateTi() {
        /*//昨日
        System.out.println(new DateTime().minusDays(1).withTimeAtStartOfDay());
        //本周
        System.out.println(new DateTime().withDayOfWeek(1).withTimeAtStartOfDay());
        //本月
        System.out.println(new DateTime().withDayOfMonth(1).withTimeAtStartOfDay());
        //上月
        System.out.println(new DateTime().minusMonths(1).withDayOfMonth(1).withTimeAtStartOfDay());
        //今天
        System.out.println(new DateTime().withTimeAtStartOfDay());*/

        long benYue = new DateTime().withDayOfMonth(1).withTimeAtStartOfDay().getMillis();
        long shangYue = new DateTime().minusMonths(1).withDayOfMonth(1).withTimeAtStartOfDay().getMillis();
        long benZhou = new DateTime().withDayOfWeek(1).withTimeAtStartOfDay().getMillis();


        System.out.println("本月===========");
        System.out.println(DateUtils.truncate(new Date(benYue), Calendar.MONTH));
        System.out.println(DateUtils.addMonths(new Date(benYue), 1));

        System.out.println("上月===========");
        System.out.println(DateUtils.truncate(new Date(shangYue), Calendar.MONTH));
        System.out.println(DateUtils.addMonths(new Date(shangYue), 1));

        System.out.println("本周");
        System.out.println(new Date(benZhou));
        System.out.println(DateUtils.addWeeks(new Date(benZhou), 1));

    }

    @Test
    public void testInteger() {
        Integer a = null;
        Integer b = 3;
        System.out.println(a + b);
    }

    @Test
    public void testDouble1() {
        Integer a = 23;
        Integer b = 3;
        BigDecimal before = new BigDecimal(a);
        BigDecimal after = new BigDecimal(b);
        BigDecimal divide = before.divide(after, 2, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(divide.toString());
    }

    /**
     * 如果 Arrays.asList();
     */
    @Test
    public void testList() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        for (int i = 0; i < 2; i++) {
            List<Integer> integers = list.subList(0, 1);
            System.out.println(integers);
            integers.clear();
        }
        System.out.println(list);
    }

    @Test
    public void testArr() {
        org.codehaus.jackson.map.ObjectMapper m = new org.codehaus.jackson.map.ObjectMapper();
        Student stu = new Student();
        stu.setName("walter");
        stu.setStudentId(12);
        Map<String, Object> queryMap = m.convertValue(stu, Map.class);
        System.out.println(queryMap);
    }

    //这个地方需要注意了，等于""的也会被打印出来了。所以得转换一下。
    @Test
    public void testMap() {
        //String xmlStr = "<>";
        Map<String, Object> map = new HashMap<>();
        map.put("name1", "1");
        map.put("name2", null);
        map.put("name3", "");

        Set<String> strings = map.keySet();
        for (String name : strings) {
            if (map.get(name) != null) {
                System.out.println("name=" + name + "----" + map.get(name));
            }
        }
        System.out.println("-----------------------------");
        for (String name : strings) {
            if (StringUtils.isNotEmpty((String) map.get(name))) {
                System.out.println("name=" + name + "----" + map.get(name));
            }
        }
    }

    //需要注意的是 switch后跟的变量不能是null
    @Test
    public void testSwitch() {
        String statusByEnum = null;
        switch (statusByEnum) {
            case "CANCEL":
            case "REFUND":
            case "FINAL_REFUSAL":
                System.out.println(statusByEnum);
        }
    }

    @Test
    public void testException() {
        if (tryAcquire(1)) {
            System.out.println(123);
        }
    }

    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }

    @Test
    public void testIntegerOf() {
        System.out.println(Integer.valueOf(null));
    }

    @Test
    public void testHandleByteQuery() {
        Byte apt_fail = Byte.valueOf("apt_fail");
        System.out.println(apt_fail);
    }

    @Test
    public void reconTest() {
        int[] nums = new int[]{101, 101, 100, 100, 90, 80, 80, 70, 70, 60, 60, 40};
        int n = 100;
        int temp = 0;                        //临时变量
        int num = 0;                            //自然排序变量
        int final_num = 0;                    //最终排名变量
        int temp_num = 0;                        //临时增量
        int attempt = nums.length + 1;                    //尝试次数
        int lastCount = 0;

        int rank = 0;
        int value = 0;
        int diff = 0;

        for (int str : nums) {
            //如果临时变量和错误率相等 说明本次错误率和上次是一致的 那么排名也应该和上次一样，但是要记录这种情况出现几次，最后如果当前排名不和上次一致时需要将排名加上空挡的增量
            if (temp == str) {
                temp_num++;                     //相等时记录增量  有几条一样的排名
                final_num = num;               //将排名赋值成和上一次排名一样
            } else {
                num++;                            //不相等时 排名自然增加
                final_num = num + temp_num;         //最终排名为自然排名加上之前的相等记录增量
                num = final_num;                  //将自然排名设置为最终排名的数上
                temp_num = 0;                       //记录相等排名的增量置空
                attempt--;
            }
            temp = str;                           //用个临时变量去记录上次的错误率

            if (n == str && final_num == 1) {
                attempt = 1;
                value = str;
                rank = final_num;
            } else if (n == str && final_num != 1) {
                value = str;
                diff = lastCount;
                rank = final_num;
                break;
            } else if (attempt == 0) {
                diff = str;
                break;
            }
            lastCount = str;
        }

        System.out.println(value + ",最后的排名==" + rank + ",差距" + (diff - value));
    }

    @Test
    public void testLong() {
        long langValue = 123L;
        Map<String, Object> map = new HashMap<>();
        map.put("w", langValue);
        long w = (long) map.get("w");
        int intW = (int) w;
        System.out.println(intW);
    }


    @Test
    public void testList1() {
        List list = new ArrayList<Integer>();
        List list2 = new ArrayList();
        list2.addAll(list);
        testArray(list2);
        System.out.println(list2);
    }


    public void testArray(List<Integer> list) {
       /* ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        list = integers;
        System.out.println(123);*/
        list.add(3);
    }

    @Test
    public void testCollect() {
        List<Integer> list = new ArrayList<>();
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    @Test
    public void testDouble12() {
        BigDecimal b = new BigDecimal(16.80000000000001);
        System.out.println(b.doubleValue());
    }

    @Test
    public void testIntegerLong() {
        Map<String, Object> map = new HashMap();
        map.put("name", new Integer(1));
        Integer name = (Integer) (map.get("name"));
        Long i = Long.valueOf(name.toString());
        System.out.println(i);

        System.out.println("-----------");
        Integer ii = 123;
        System.out.println(Long.valueOf(ii));

        Long ll = 123l;
    }

    @Test
    public void testS() {
        Integer integer = Integer.valueOf(null);
        System.out.println(integer);
    }

    @Test
    public void getTodayDatekey() {
        String DATEKEY_FORMAT = "yyyyMMdd";
        String datekeyStr = DateTimeFormatter.ofPattern(DATEKEY_FORMAT).format(LocalDate.now());
        Long longA = Long.valueOf(Integer.parseInt(datekeyStr));
        Date date = new Date(longA);
        System.out.println(longA);
    }

    @Test
    public void getDayKey() {
        long time = 1501195607000l;
        // 1501171200000
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    @Test
    public void testObject() {
        Object o = 1l;
        if (o instanceof Long) {
            System.out.println("Long");
        }
        if (o instanceof Integer) {
            System.out.println("Integer");
        }
    }

    @Test
    public void testReflect() {
        Student s = new Student(12, "walter");
        Field[] field = s.getClass().getDeclaredFields();
        for (Method method : s.getClass().getMethods()) {
            if (method.getName().startsWith("get")) {
                Object invoke = null;
                try {
                    invoke = method.invoke(s);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                System.out.println(invoke);
            }
        }
    }

    /**
     * Double都行但是BigDecimal不行 "null"
     *
     * @throws IOException
     */
    @Test
    public void testBigDecimal() throws IOException {
        //BigDecimal a = new BigDecimal("null");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Student student = objectMapper.readValue("{\"studentId\":\"null\",\"name\":\"walter\",\"doubleValue\":null}", Student.class);
        System.out.println(student);
    }

    @Test
    public void testTime() {
        String DATEKEY_FORMAT = "yyyyMMdd";
        long timestamp = 1502576409000l;
        Date date = new Date(timestamp);
        SimpleDateFormat sdfTemp = new SimpleDateFormat(DATEKEY_FORMAT);
        System.out.println(Integer.valueOf(sdfTemp.format(date)));
    }

    @Test
    public void testMap123() {
//        Map<String, Object> fields = new HashMap<>();
//        fields.put("name",1);
//        if(fields.get("name")!= null){
//            System.out.println(fields);
//        }
        Map<String, Object> fields = new HashMap<>();
        fields.put(null, 12);
        System.out.println(fields);
    }

    @Test
    public void testGeneratePoiId() {
        String s = "123" + generatePoiId(392036);
        System.out.println(Integer.valueOf(s));
    }

    private static String generatePoiId(long poiId) {
        Random r = new Random();
        int max = 9;
        int min = 1;
        int s = r.nextInt(max) % (max - min + 1) + min;
        String random = String.valueOf(s);
        String poiIdStr = String.valueOf(poiId);
        int length = poiIdStr.length();
        int middle = 0;
        if (length > 7) {
            int modpoint = Integer.valueOf(poiIdStr.substring(0, length - 7));
            int mod = modpoint % 10;
            String left = poiIdStr.substring(length - 7);
            poiIdStr = String.valueOf(mod) + left;
        } else {
            middle = 8 - length;
        }
        String middleStr = "";
        for (int i = 0; i < middle; i++) {
            middleStr += "9";
        }
        return random + middleStr + poiIdStr;
    }

    @Test
    public void testSubList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<Integer> integers = list.subList(0, list.size() - 1);
        System.out.println(integers);
    }

    @Test
    public void testListA() {
//        Long a1 = getTimeSort(12345678l,0l);
//        System.out.println();
//        System.out.println(getTimeSort(12345678l,new Date().getTime()));
    }

    public static String getPoiTimeSort(long poiId, String time) {
        String timePart = "";
        if (StringUtils.isNotBlank(time)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = sdf.parse(time);
                long timestamp = date.getTime() / 1000;
                timePart = String.valueOf(timestamp);
            } catch (ParseException e) {
                logger.warn("getPoiTimeSort ParseException poiId={},createTime={}", poiId, time, e);
            }
        }
        return timePart + generatePoiId(poiId);
    }

    public static String getTimeSort(long poiId, Long time) {
        long timePart = time / 1000;
        return timePart + generatePoiId(poiId);
    }

    @Test
    public void testCountDownLatch() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6, 6, 3,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        try {
            CountDownLatch countDownLatch = new CountDownLatch(17);
            for (int i = 0; i <= 17; i++) {
                threadPoolExecutor.execute(new SendClueMultiThread(countDownLatch));
            }
            countDownLatch.await();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    public String filterInvalidChar(String phone) {
        StringBuilder tel = new StringBuilder();
        for (char c : phone.toCharArray()) {
            if (PHONE_CHAR_SET.contains(c)) {
                tel.append(c);
            }
        }
        return tel.toString();
    }


    @Test
    public void testCookie() {
        String decrypt = DESTools.decrypt("5jaFjaE8l0SrE+aD0VtyZs1EzUfUuJnz03E7aQUhfqUxpog6FgwmQ2BF8ba2oU2E2ueaCevz84kpinNhxYCCKg==");
        String[] arr = decrypt.split(":");
        if (arr.length == 7) {
            //this.userId = getIntegerValue(arr[0]);
//            this.login = getStringValue(arr[1]);
//            this.userName = getStringValue(arr[2]);
//            this.tenantId = getLongValue(arr[3]);
//            this.tenantName = getStringValue(arr[4]);
//            this.appKey = getStringValue(arr[5]);
//            this.created = getLongValue(arr[6]);
        }
        System.out.println(123);
    }

    /**
     * 阿里这个要注意的地方没有看到
     */
    @Test
    public void testSplit123() {
        String str = "a,b,c";
        String[] ary = str.split(","); // 预期大于 3，结果是 3
        System.out.println(ary.length);
    }

    /**
     * 不要这样用
     *
     */
    @Test
    public void testList221() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        Object[] objects = list.toArray();
        for(Object o : objects){
            System.out.println((Integer)o);
        }
    }

    @Test
    public void testList2221(){
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        Integer[] intArray = new Integer[list.size()];
        intArray = list.toArray(intArray);
        for(int i : intArray){
            System.out.println(i);
        }
    }

    @Test
    public void testMapSize(){
        Map<String, Integer> map = new HashMap<>();
        System.out.println("map size="+map.size());
    }

    @Test
    public void testDisplayMap(){
        Map<String,Integer> map = new HashMap();
        map.put("xu",1);
        map.put("han",2);
        map.forEach((key,value)->{
            System.out.println(key+"-"+value);
        });
    }

    /**
     * treeSet保证了稳定性和有序性。稳定性：就是每次遍历的结果都是相同的。有序性：指的是每次遍历都是按照某种规则在执行
     */
    @Test
    public void testDisplayTreeSet(){
        TreeSet tree = new TreeSet();
        tree.add("China");
        tree.add("America");
        tree.add("Japan");
        tree.add("Chinese");
        tree.forEach(item-> System.out.println(item));
    }

    @Test
    public void testFinalClass(){
        Integer i = 3;
        System.out.println(i.hashCode());
        i =5;
        System.out.println(i.hashCode());
    }
}
