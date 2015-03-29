package com.luodichen.practice.algorithm.sort;

public class MergeSort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public void sort(T[] a) {
        Object aux[] = new Object[a.length];
        sort(a, 0, a.length - 1, aux);
    }

    private void sort(T[] a, int lo, int hi, Object[] aux) {
        if (lo == hi)
            return;
        
        int mid = (lo + hi) / 2;
        sort(a, lo, mid, aux);
        sort(a, mid + 1, hi, aux);
        
        int index = 0;
        int iLeft = lo, iRight = mid + 1;
        while (index < (hi - lo + 1)) {
            if (iLeft > mid) {
                aux[index++] = a[iRight++];
            } else if (iRight > hi) {
                aux[index++] = a[iLeft++];
            } else {
                aux[index++] = a[iLeft].compareTo(a[iRight]) < 0 ? a[iLeft++] : a[iRight++];
            }
        }
        
        System.arraycopy(aux, 0, a, lo, hi - lo + 1);
    }
}
