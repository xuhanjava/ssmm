package com.google.ssmm;

import com.google.ssmm.entity.Student;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;

/**
 * Created by xuhan on 16-11-18.
 */
public class EnumTest {
    @Test
    public void testSeason(){
        System.out.println(Season.AUTUMN.getValue());

        //enum这种类型自带的方法
        Season spring = Season.valueOf("SPRING");
        System.out.println(spring.getClass());
        //返回的是value。aaa
        System.out.println(spring.getValue());
        //返回的是value对应的那个值.SPRING
        System.out.println(spring.name());
        System.out.println(Season.values().length);
    }

    @Test
    public void testOther(){
        Student st = new Student();
        st.setName(null);
    }

    @Test
    public void testSplit(){
        SecureRandom sr = new SecureRandom();
        int password = sr.nextInt(99999);
        int count = 0;
        while (password < 10000) {
            count ++;
            password = sr.nextInt(99999);
        }
        System.out.println(count);
    }

}
