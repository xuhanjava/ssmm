package com.google.ssmm;

/**
 * Created by xuhan on 16-11-21.
 */
class Employee{
    private static Employee employee = new Employee();
    public static int count1=0;
    public static int count2;

    private Employee(){
        count1++;
        count2++;
    }

    public static Employee getInstance(){

        return employee;
    }
}
public class TestLoader {
    public static void main(String[] args) {
        Employee employee = Employee.getInstance();
        System.out.println("count1--------------"+employee.count1);
        System.out.println("count2--------------"+employee.count2);
    }
}
