package com.luodichen.practice.algorithm.string;

public class Test {
    public static void main(String args[]) {
        KMP kmp = new KMP("luodichen.com");
        String str = "luodichelluoluodafdfsfwfichen.luodichen.comxxfaefweluoluooollllluodichen.com.comluodichen.comluodichen.com";
        System.out.println(kmp.findFirst(str));
        System.out.println(kmp.findNext());
        System.out.println(kmp.findNext());
        System.out.println(kmp.findNext());
        System.out.println(kmp.findNext());
    }
}
