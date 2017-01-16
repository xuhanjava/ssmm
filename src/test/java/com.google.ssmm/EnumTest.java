package com.google.ssmm;

import com.google.ssmm.entity.Student;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by xuhan on 16-11-18.
 */
public class EnumTest {
    @Test
    public void testSeason(){
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

    @Test
    public void testSD(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println(sdf.format(new Date()));
    }

    @Test
    public void testSub(){
        String s = "18655049262";
        System.out.println(s.substring(s.length()-4,s.length()));
    }

    @Test
    public void testSort(){
        Student student = new Student(12,BedShapeType.SINGLE_BED.getValue());
        Student student2 = new Student(9,BedShapeType.OTHER.getValue());
        Student student3 = new Student(14, BedShapeType.DOUBLE_BED.getValue());
        List<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(student2);
        students.add(student3);

        Comparator<Student> comparator = new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                //先排年龄
                return BedShapeType.valueOf(s1.getName()).ordinal() - BedShapeType.valueOf(s2.getName()).ordinal();
            }
        };
        Collections.sort(students,comparator);
        for(Student s : students){
            System.out.println(s.toString());
        }
    }

    @Test
    public void testCollection(){
        ArrayList<String> a1 = new ArrayList<>();
        ArrayList<String> a2 = new ArrayList<>();

        a1.add("15");
        a1.add("4");
        a2.add("1");
        a1.addAll(a2);
        System.out.println(a1);
    }

}
