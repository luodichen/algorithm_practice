package com.luodichen.practice.algorithm.sort;

public class SelectionSort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public void sort(T[] a) {
        int length = a.length;
        
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i; j < length; j++) {
                if (a[j].compareTo(a[min]) < 0) min = j;
            }
            T tmp = a[i];
            a[i] = a[min];
            a[min] = tmp;
        }
    }
}
