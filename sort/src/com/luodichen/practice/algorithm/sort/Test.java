package com.luodichen.practice.algorithm.sort;

import java.util.Random;

public class Test {
    public static void main(String args[]) {
        System.out.println("================ Test 1 (SS) ================");
        test1(new SelectionSort<Integer>());
        System.out.println("================ Test 1 (IS) ================");
        test1(new InsertionSort<Integer>());
    }
    
    public static void test1(ISort<Integer> sort) {
        int nSize = 10000;
        int nBound = nSize * 10;
        
        Integer array[] = new Integer[nSize];
        
        Random rand = new Random();
        for (int i = 0; i < nSize; i++) {
            array[i] = rand.nextInt(nBound);
        }
        
        sort.sort(array);
        if (check(array)) {
            System.out.println("Array sort check correct.");
        } else {
            System.out.println("Array sort check incorrect.");
        }
    }
    
    public static boolean check(Integer array[]) {
        boolean ret = true;
        int len = array.length;
        
        if (len < 2) {
            return ret;
        }
        
        for (int i = 1; i < len; i++) {
            if (array[i] < array[i - 1]) {
                ret = false;
                break;
            }
        }
        
        return ret;
    }
}
