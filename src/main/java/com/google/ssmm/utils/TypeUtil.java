package com.google.ssmm.utils;

/**
 * 
 * @author Walter
 *
 */
public class TypeUtil {

    public static String INTEGER_TYPE = "java.lang.Integer";

    public static String BASE_EXAMPLE_TYPE = "com.ecotech.ecotechmybatiscommon.common.BaseExample";

    public static String BASE_MAPPER_TYPE = "com.ecotech.ecotechmybatiscommon.common.BaseMapper";

    public static String LONG_TYPE = "Long";

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println("10110101110111011110011110100001101000011".length());
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<41;i++){
            sb.append("1");
        }
        System.out.println(sb.toString());
    }

}
