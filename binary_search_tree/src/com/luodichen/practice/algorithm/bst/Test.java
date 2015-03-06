package com.luodichen.practice.algorithm.bst;

import java.util.Random;

public class Test {
    public static void main(String args[]) {
        int nBound = 1000000;
        int nTimes = 1000000;
        int nMaxDepth = 0;
        
        Random random = new Random();
        SearchTree<Integer, Integer> st = new SearchTree<Integer, Integer>();
        
        while (0 != nTimes--) {
            Integer i = random.nextInt(nBound);
            int nDepth = st.put(i, i);
            
            if (nDepth > nMaxDepth)
                nMaxDepth = nDepth;
        }
        
        for (int i = 0; i < 1000; i++) {
            Integer k = random.nextInt(nBound);
            Integer v = st.get(k);
            if (null != v) {
                System.out.print(k + " => " + v + "\n");
            }
        }
        
        System.out.print("max depth=" + nMaxDepth);
    }
}
