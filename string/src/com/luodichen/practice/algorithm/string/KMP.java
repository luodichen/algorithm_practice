package com.luodichen.practice.algorithm.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KMP extends StringSearch {
    
    private Map<Character, List<Integer>> mDFA = new HashMap<Character, List<Integer>>();
    private String mString = null;
    private int mIndex = 0;
    private int mPatternMatch = 0;
    private int mPattLen = 0;
    
    public KMP(String pattern) {
        super(pattern);
        initDFA();
    }

    @Override
    public int findFirst(String str) {
        mString = str;
        mIndex = 0;
        mPatternMatch = 0;
        
        return findNext();
    }

    @Override
    public int findNext() {
        int ret = -1;
        int mLength = mString.length();
        
        while (mIndex < mLength && mPatternMatch < mPattLen) {
            List<Integer> list = mDFA.get(mString.charAt(mIndex));
            mPatternMatch = (null == list) ? 0 : list.get(mPatternMatch);
            ++mIndex;
        }
        
        if (mPatternMatch == mPattLen) {
            ret = mIndex - mPattLen;
        }
        
        mPatternMatch = 0;
        
        return ret;
    }

    private void initDFA() {
        mPattLen = mPattern.length();
        Set<Character> charSet = new HashSet<Character>();
        
        for (int i = 0; i < mPattLen; i++) {
            charSet.add(mPattern.charAt(i));
        }
        
        for (char ch : charSet) {
            List<Integer> array = Arrays.asList(new Integer[mPattLen]);
            
            mDFA.put(ch, array);
            mDFA.get(ch).set(0, 0);
        }
        
        mDFA.get(mPattern.charAt(0)).set(0, 1);
        
        int x = 0;
        for (int i = 1; i < mPattLen; i++) {
            for (char ch : charSet) {
                mDFA.get(ch).set(i, mDFA.get(ch).get(x));
            }
            
            mDFA.get(mPattern.charAt(i)).set(i, i + 1);
            x = mDFA.get(mPattern.charAt(i)).get(x);
        }
    }
}
