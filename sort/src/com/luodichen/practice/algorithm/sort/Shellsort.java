package com.luodichen.practice.algorithm.sort;

public class Shellsort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public void sort(T[] a) {
        int len = a.length;
        
        for (int step = step(len); step > 0; step = nextStep(step)) {
            if (1 == step) {
                step = 1;
            }
            for (int i = 0; i < step; i++) {
                for (int j = i + step; j < len; j += step) {
                    int cur = j;
                    while (cur > i && a[cur].compareTo(a[cur - step]) < 0) {
                        T tmp = a[cur];
                        a[cur] = a[cur - step];
                        a[cur - step] = tmp;
                        
                        cur -= step;
                    }
                }
            }
        }
    }
    
    protected int step(int length) {
        int ret = 1;
        while (ret < length / 3) 
            ret = 3 * ret + 1;
        
        return ret;
    }
    
    protected int nextStep(int step) {
        return step / 3;
    }
}
