package com.luodichen.practice.algorithm.sort;

public class HeapSort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public void sort(T[] a) {
        int len = a.length + 1;
        int index = len / 2;
        
        while (index >= 1) {
            sink(a, index - 1, len - 1);
            index--;
        }

        index = len - 1;
        while (index > 1 ) {
            swap(a, 0, index - 1);
            index--;
            sink(a, 0, index);
        }
    }
    
    private void sink(T[] a, int k, int n) {
        int index = k;
        
        while ((index + 1) * 2 < n + 1) {

            int left = (index + 1) * 2 - 1;
            int right = (index + 1) * 2;
            
            if (right < n && a[left].compareTo(a[right]) < 0 
                    && a[index].compareTo(a[right]) < 0) {
                swap(a, index, right);
                index = right;
            } else if (a[index].compareTo(a[left]) < 0) {
                swap(a, index, left);
                index = left;
            } else {
                break;
            }
        }
    }
    
    private void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
