package com.luodichen.practice.algorithm.sort;

public class InsertionSort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public void sort(T[] a) {
        int length = a.length;
        
        for (int i = 0; i < length; i++) {
            int cur = i;
            while (cur > 0 && a[cur].compareTo(a[cur - 1]) < 0) {
                T tmp = a[cur - 1];
                a[cur - 1] = a [cur];
                a[cur] = tmp;
                
                --cur;
            }
        }
    }
}
