package com.luodichen.practice.algorithm.pq;

public abstract class PriorityQueue<T> {
    
    private HeapArray<T> mHeap = new HeapArray<T>();
    
    public PriorityQueue() {
        mHeap.insert(null);
    }
    
    protected abstract int compare(T left, T right);
    
    public void insert(T v) {
        swim(mHeap.insert(v));
    }
    
    public T first() {
        return mHeap.get(1);
    }
    
    public T delFirst() {
        if (mHeap.size() <= 1) {
            return null;
        }
        
        T ret = first();
        T back = mHeap.deleteBack();
        
        if (1 == mHeap.size()) {
            return ret;
        }
        
        mHeap.set(1, back);
        sink(1);
        
        return ret;
    }
    
    private void swap(int i, int j) {
        T tmp = mHeap.get(i);
        mHeap.set(i, mHeap.get(j));
        mHeap.set(j, tmp);
    }
    
    private void swim(int index) {
        while (null != mHeap.get(index / 2) 
                && compare(mHeap.get(index), mHeap.get(index / 2)) < 0) {
            swap(index, index / 2);
            index /= 2;
        }
    }
    
    private void sink(int index) {
        int size = mHeap.size();
        
        while (index * 2 < size) {
            T parent = mHeap.get(index);
            index *= 2;
            if ((index + 1 < size) 
                    && compare(mHeap.get(index + 1), mHeap.get(index)) < 0) {
                index++;
            }
            
            if (compare(mHeap.get(index), parent) < 0) {
                swap(index, index / 2);
            } else {
                break;
            }
        }
    }
}
