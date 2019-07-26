package com.google.ssmm;

import com.google.ssmm.entity.Student;
import org.junit.Test;

public class CacheRefreshTest {
    @Test
    public void testDisplay(){
        Student student = new Student();
        student.setId(1);
        Student student1 = new Student();
        student1.setId(2);
        student.setStudent(student1);

        new Thread(()->{
            student.getId();
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            student.setId(2);
            student.setName("2");
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            student.getId();
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            student.setId(3);
            student.setName("3");
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(30000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
