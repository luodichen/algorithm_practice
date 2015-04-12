package com.luodichen.practice.algorithm.string;

public class Test {
    public static void main(String args[]) {
        
        String str = "luodichelluoluodafdfsfwfichen.luodichen.comxxfaefweluoluooollllluodichen.com.comluodichen.comluodichen.com";
        String pattern = "luodichen.com";
        
        KMP kmp = new KMP(pattern);
        BM bm = new BM(pattern);
        
        System.out.println(kmp.findFirst(str) + "," + bm.findFirst(str));
        System.out.println(kmp.findNext() + "," + bm.findNext());
        System.out.println(kmp.findNext() + "," + bm.findNext());
        System.out.println(kmp.findNext() + "," + bm.findNext());
        System.out.println(kmp.findNext() + "," + bm.findNext());
    }
}
