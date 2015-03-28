package com.luodichen.practice.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

public class Test {
    public static void main(String args[]) {
        
        int nSize = 100000;
        int nBound = nSize * 10;
        
        Integer array[] = new Integer[nSize];
        
        Random rand = new Random();
        for (int i = 0; i < nSize; i++) {
            array[i] = rand.nextInt(nBound);
        }
        
        Integer testArray[] = new Integer[nSize];
        
        System.out.println("================ Test 1 (SS) ================");
        System.arraycopy(array, 0, testArray, 0, nSize);
        test1(testArray, new SelectionSort<Integer>());
        System.out.println("================ Test 1 (IS) ================");
        System.arraycopy(array, 0, testArray, 0, nSize);
        test1(testArray, new InsertionSort<Integer>());
        System.out.println("================ Test 1 (ShS) ================");
        System.arraycopy(array, 0, testArray, 0, nSize);
        test1(testArray, new Shellsort<Integer>());
    }
    
    public static void test1(Integer array[], ISort<Integer> sort) {
        
        long time = System.currentTimeMillis();
        sort.sort(array);
        System.out.println("Cost " + (System.currentTimeMillis() - time) + " ms.");
        
        if (check(array)) {
            System.out.println("Array sort check correct.");
        } else {
            System.out.println("Array sort check incorrect.");
            System.out.println(Arrays.asList(array));
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
