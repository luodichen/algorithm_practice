package com.luodichen.practice.algorithm.bst;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Test {
    public static void main(String args[]) {
        System.out.println("================ Test 1 ================");
        test1(new RedBlackTree<Integer, Integer>());
        //test1(new SearchTree<Integer, Integer>());
        System.out.println("================ Test 2 ================");
        //test2(new RedBlackTree<Integer, Integer>());
    }
    
    private static void test1(SearchTree<Integer, Integer> st) {
        int nBound = 10000000;
        int nTimes = 1000000;
        
        Set<Integer> standard = new HashSet<Integer>();
        Random random = new Random();
        //SearchTree<Integer, Integer> st = new SearchTree<Integer, Integer>();
        
        for (int i = 0; i < nTimes; i++) {
            int num = random.nextInt(nBound);
            st.put(num, num);
            standard.add(num);
        }
        
        System.out.println("total " + standard.size() + " nodes");
        
        for (int nTestNum : standard) {
            Integer found = st.get(nTestNum);
            if (null == found || nTestNum != found) {
                System.out.println("integrity test incorrect");
                return;
            }
        }
        System.out.println("integrity test correct");
        
        for (int i = 0; i < 1000; i++) {
            Integer k = random.nextInt(nBound);
            Integer v = st.get(k);
            if (null != v) {
                System.out.println(k + " => " + v);
            }
        }
        
        long lDepthTotal = 0;
        int nCount = 0;
        int nMaxDepth = 0;
        int nMinDepth = 0;
        
        for (int i = 0; i < 10000; i++) {
            int k = random.nextInt(nBound);
            int depth = st.getDepth(k);
            
            if (depth >= 0) {
                lDepthTotal += depth;
                nCount++;
                
                if (0 == i) {
                    nMaxDepth = nMinDepth = depth;
                } else {
                    nMaxDepth = depth > nMaxDepth ? depth : nMaxDepth;
                    nMinDepth = depth < nMinDepth ? depth : nMinDepth;
                }
            }
        }
        
        System.out.print("min-depth = " + nMinDepth);
        System.out.print(", max-depth = " + nMaxDepth);
        System.out.println(", average-depth = " + (1.0 * lDepthTotal / nCount));
    }
    
    private static void test2(SearchTree<Integer, Integer> st) {
        int nTestArray[] = {
                100, 50, 150, 25, 75, 125, 175, 15, 35, 65, 85, 115, 135,
                165, 185, 10, 20, 30, 40, 60, 70, 80, 90, 110, 120, 130,
                140, 160, 170, 180, 190
        };
        
        //SearchTree<Integer, Integer> st = new SearchTree<Integer, Integer>();
        for (int i : nTestArray) {
            st.put(i, i);
        }
        
        st.remove(10);
        st.remove(100);
        st.remove(175);
        
        for (int i : nTestArray) {
            Integer got = st.get(i);
            System.out.println(i + " => " + (null == got ? "null" : got));
        }
    }
}
