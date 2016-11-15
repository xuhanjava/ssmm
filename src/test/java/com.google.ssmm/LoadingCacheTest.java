package com.google.ssmm;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Walter on 16-11-14.
 * 通常来说，Guava Cache适用于：
 * 你愿意消耗一些内存空间来提升速度。
 * 你预料到某些键会被查询一次以上。
 * 缓存中存放的数据总量不会超出内存容量。（Guava Cache是单个应用运行时的本地缓存。它不把数据存放到文件或外部服务器。如果这不符合你的需求，请尝试Memcached这类工具）
 */
public class LoadingCacheTest {
    private LoadingCache<String, Object> cache = null;

    @Test
    public void getCache() throws ExecutionException, InterruptedException {
        for(int i=1;i<=5;i++){
            if(cache == null || cache.get("1") != null){
                cache = CacheBuilder.newBuilder().maximumSize(200)
                    .build(new CacheLoader<String, Object>() {
                        @Override
                        public Object load(String time) throws Exception {
                            if("time".equals(time)){
                                return new Date();
                            }else if("token".equals(time)){
                                return null;
                            }


                            return null;
                        }
                    });
            }else{
                System.out.println(cache.get("1"));
            }
            Thread.sleep(6000);
        }
    }

    @Test
    public void testCreate() throws ExecutionException {
        //超过设计的最大值时 就会驱除最不经常使用的那些entries  。 evict：驱逐收回的意思 in populating:填充
        LoadingCache cache = CacheBuilder.newBuilder().maximumSize(1000).build(new CacheLoader<String, Integer>() {
            @Override public Integer load(String key) throws Exception {
                if ("1".equals(key)) {
                    return 1;
                } else if ("2".equals(key)) {
                    return 2;
                }
                return 3;
            }
        });

        Object o = cache.get("1");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @Test
    public void test() {
        System.out.println(isMobileNO("18766761211"));
    }

    public boolean isMobileNO(String mobiles) {
        Pattern pi = Pattern.compile("^[1][0-9]{10}$");
        Matcher mi = pi.matcher(mobiles);
        return mi.matches();
    }

    @Test
    public void testPaixu() {
        List<String> strings = new ArrayList<>();
        strings.add("可住宿");
        //strings.add("立即确认");
        strings.add("可餐饮");

        List<String> strs = new ArrayList<>();
        if (strings.contains("立即确认")) {
            strs.add("立即确认");
        }
        if (strings.contains("可餐饮")) {
            strs.add("可餐饮");
        }
        if (strings.contains("可住宿")) {
            strs.add("可住宿");
        }

        for (String s : strs) {
            System.out.println(s);
        }
    }

    @Test
    public void testYiHuo() {
        boolean a = false;
        boolean b = true;
        if (!(a ^ b)) {
            System.out.println(123);
        } else {
            System.out.println("error");
        }
    }

    @Test
    public void testB() {
        String content = "亲爱的场地主，您对客户{name}的接单报名已通过，我们已为您推单啦";
        content = content.replaceAll("\\{name\\}", "123");
        System.out.println(content);
    }

    public static String replaceMobileToStar(String originStr) {
        if (StringUtils.isNotBlank(originStr)) {
            return originStr
                .replaceAll("^(.*)((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}(.*)$", "$1**$8");
        } else {
            return null;
        }
    }

    @Test
    public void testL() {
        String s = "dasdas18655049262sdasdasd";

        String[] st = s.split("((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}");
        for (int i = 0; i < st.length; i++) {
            System.out.println(st[i]);
        }
        System.out.println(isMobile(s));
    }

    public  boolean isMobile(String mobiles) {
        Pattern pi = Pattern.compile("^(.*)((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}(.*)$");
        Matcher mi = pi.matcher(mobiles);
        return mi.matches();
    }

    @Test
    public void testE(){
        int a =3;
        int b =7;
        double c =1.00d;
        //c = (double)a/b;
        System.out.println(c);
        BigDecimal   bv   =   new   BigDecimal(c);
        double   f1   =   bv.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);
    }

    @Test
    public void testD(){
        Date date = new Date();
        Date date1 = new Date(date.getTime() + 1000 * 60 * 60 * 24 * 2);
        System.out.println(date1);
    }
}
