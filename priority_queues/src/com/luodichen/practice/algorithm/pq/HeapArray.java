package com.luodichen.practice.algorithm.pq;

public class HeapArray<T> {
    
    private int mArraySize = 0;
    private int mSize = 0;
    private Object mArray[] = null;
    
    public HeapArray() {
        mArraySize = 64;
        mArray = new Object[mArraySize];
    }
    
    public int insert(T v) {
        int nIndex = mSize++;
        
        if (mSize > mArraySize) {
            mArraySize *= 2;
            Object newArray[] = new Object[mArraySize];
            System.arraycopy(mArray, 0, newArray, 0, mArray.length);
            mArray = newArray;
        }
        
        mArray[nIndex] = v;
        
        return nIndex;
    }
    
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= mSize) {
            throw new RuntimeException("out of bound.");
        }
        
        return (T)mArray[index];
    }
    
    @SuppressWarnings("unchecked")
    public T deleteBack() {
        T ret = null;
        if (mSize > 0) {
            ret = (T)mArray[--mSize];
            mArray[mSize] = null;
        }
        
        return ret;
    }
    
    public void set(int index, T v) {
        if (index >= mSize) {
            throw new RuntimeException("out of bound.");
        }
        
        mArray[index] = v;
    }
    
    public int size() {
        return mSize;
    }
}
