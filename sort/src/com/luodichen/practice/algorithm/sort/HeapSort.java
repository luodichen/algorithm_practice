package com.luodichen.practice.algorithm.sort;

public class HeapSort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public void sort(T[] a) {
        int len = a.length;
        int _len = len + 1;
        int _index = _len / 2;
        
        while (_index >= 1) {
            int index = _index - 1;
            sink(a, index, len);
            
            _index--;
        }
        
        
        _index = _len - 1;
        while (_index > 1 ) {
            int index = _index - 1;
            swap(a, 0, index);
            
            _index--;
            index = _index - 1;
            
            sink(a, 0, index + 1);
        }
    }
    
    private void sink(T[] a, int k, int n) {
        int _k = k + 1;
        int _n = n + 1;
        
        int _index = _k;
        
        while (_index * 2 < _n) {
            int _left = _index * 2;
            int _right = _left + 1;
            
            int index = _index - 1;
            int left = _left - 1;
            int right = _right - 1;
            
            if (_right < _n && a[left].compareTo(a[right]) < 0 
                    && a[index].compareTo(a[right]) < 0) {
                swap(a, index, right);
                index = right;
            } else if (a[index].compareTo(a[left]) < 0) {
                swap(a, index, left);
                index = left;
            } else {
                break;
            }
            
            _index = index + 1;
        }
    }
    
    private void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
