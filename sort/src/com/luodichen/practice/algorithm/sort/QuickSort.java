package com.luodichen.practice.algorithm.sort;

public class QuickSort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public void sort(T[] a) {
        sort(a, 0, a.length - 1);
    }
    
    private void sort(T[] a, int lo, int hi) {
        if (lo == hi)
            return;
        
        int left = lo + 1, right = hi;
        
        while (left != right) {
            while (left != right && a[left].compareTo(a[lo]) <= 0)
                left++;
            while (left != right && a[right].compareTo(a[lo]) >= 0)
                right--;
            
            if (left != right) {
                swap(a, left, right);
            }
        }
        
        int split = (a[left].compareTo(a[lo]) <= 0) ? left : (left - 1);
        swap(a, lo, split);
        
        if (split > lo)
            sort(a, lo, split - 1);
        
        if (split < hi)
            sort(a, split + 1, hi);
    }
    
    private void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
