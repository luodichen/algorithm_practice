package com.luodichen.practice.algorithm.string;

public abstract class StringSearch {
    
    protected String mPattern = null;
    
    public StringSearch(String pattern) {
        mPattern = pattern;
    }
    
    public abstract int findFirst(String str);
    public abstract int findNext();
}
