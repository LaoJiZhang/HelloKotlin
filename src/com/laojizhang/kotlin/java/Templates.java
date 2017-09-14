package com.laojizhang.kotlin.java;

public class Templates {

    public static void main(String[] args) {
        int result = breakControl();
        System.out.println("result = " + result);
    }

    private static int breakControl() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i == 10 && j == 10) {
                    return i * j;
                }
                if (i > 10)
                    break;
                if (j > 10)
                    break;
                System.out.println("i = " + i + "  j = " + j);
            }
        }
        return -1;
    }
}
