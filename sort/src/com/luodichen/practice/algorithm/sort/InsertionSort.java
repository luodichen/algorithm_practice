package com.luodichen.practice.algorithm.sort;

public class InsertionSort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public void sort(T[] a) {
        int length = a.length;
        
        for (int i = 0; i < length; i++) 
            for (int cur = i; cur > 0 && a[cur].compareTo(a[cur - 1]) < 0; cur--) 
                swap(a, cur, cur - 1);
    }
    
    private void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
