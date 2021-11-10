package com.example;

/**
 *  String大总结
 */
public class TestString {
    public static void main(String[] args) {

        // s1 == s2 为true毫无疑问
        String s1 = "123";
        String s2 = "123";

        // s3 == s4 为false毫无疑问
        String s3 = "234";
        // 下面创建两个对象，一个是栈中的s4引用，一个是堆中的new String()对象
        String s4 = new String("234");

        String s5 = "1" + "2";
        String s6 = "12";
        System.out.println("s5==s6: " + (s5 == s6)); // true

        String s7 = "1" + "2";
        String s8 = new String("12");
        System.out.println("s7==s8: " + (s7 == s8)); // false

        String s9 = "666";
        final String s10 = new String("666");
        System.out.println("s9==s10: " + (s9 == s10)); // false

        String s12 = new String("234");
        String s11 = "234";
        System.out.println("s11==s12: " + (s11 == s12)); // false

        // 常量池有9 3 但没有93
        String s13 = new String("9") + new String("3");
        // s13会去常量池中找93，此时没有，便会创建一个93，返回其引用
        s13.intern();
        String s14 = "93";
        System.out.println("s13==s14: " + (s13 == s14)); // true

        String s15 = new String("9") + new String("3");
        String s16 = "93";
        // 因为93已经有了，所以下面这句有没有都无所谓
        s15.intern();
        System.out.println("s15==s16: " + (s15 == s16)); // false
    }
}
