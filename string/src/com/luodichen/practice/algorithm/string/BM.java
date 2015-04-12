package com.luodichen.practice.algorithm.string;

import java.util.HashMap;
import java.util.Map;

public class BM extends StringSearch {

    private Map<Character, Integer> mRight = new HashMap<Character, Integer>();
    private String mString = null;
    private int mIndex = 0;
    
    public BM(String pattern) {
        super(pattern);
        init();
    }

    @Override
    public int findFirst(String str) {
        mIndex = 0;
        mString = str;
        return findNext();
    }

    @Override
    public int findNext() {
        int ret = -1;
        int nStrLen = mString.length();
        int nMatchIndex = mPattern.length() - 1;
        
        while (mIndex + nMatchIndex < nStrLen && nMatchIndex >= 0) {
            if (mString.charAt(mIndex + nMatchIndex) == mPattern.charAt(nMatchIndex)) {
                nMatchIndex--;
            } else {
                Integer nRight = mRight.get(mString.charAt(mIndex + nMatchIndex));
                nRight = (null == nRight) ? -1 : nRight;
                mIndex += mPattern.length() - 1 - nRight;
                nMatchIndex = mPattern.length() - 1;
            }
        }
        
        if (-1 == nMatchIndex) {
            ret = mIndex;
            mIndex += mPattern.length();
        }
        
        return ret;
    }
    
    private void init() {
        int nPattLen = mPattern.length();
        for (int i = 0; i < nPattLen; i++) {
            mRight.put(mPattern.charAt(i), i);
        }
    }
}
