package com.google.ssmm.common;

/**
 * Created by xuhan
 * 2017/6/5
 * 下午2:40
 */
public class TestApple {
    public static void main(String[] args) {
        TestApple1 testApple1 = new TestApple1();
        TestApple1 testApple11 = new TestApple1();
        testApple1.setName("1");
        testApple11.setName("11");
        testApple1.setTest(testApple11);

        System.out.println(testApple1);
    }

}

class TestApple1{
    private TestApple1 test;
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TestApple1 getTest() {
        return test;
    }

    public void setTest(TestApple1 test) {
        this.test = test;
    }

    @Override
    public String
    toString() {
        return "TestApple1{" +
                "test=" + test +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
