package com.luodichen.practice.algorithm.sort;

public class Shellsort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public void sort(T[] a) {
        int len = a.length;
        
        for (int step = step(len); step > 0; step = nextStep(step)) 
            for (int i = 0; i < step; i++) 
                for (int j = i + step; j < len; j += step) 
                    for (int cur = j; 
                            cur > i && a[cur].compareTo(a[cur - step]) < 0; 
                            cur -= step) {
                        
                        T tmp = a[cur];
                        a[cur] = a[cur - step];
                        a[cur - step] = tmp;
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
