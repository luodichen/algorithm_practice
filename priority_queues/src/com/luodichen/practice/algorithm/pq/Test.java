package com.luodichen.practice.algorithm.pq;

import java.util.Random;

public class Test {
    public static void main(String args[]) {
        int nSize = 100000;
        int nBound = nSize * 10;
        
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        
        Random rand = new Random();
        for (int i = 0; i < nSize; i++) {
            pq.insert(rand.nextInt(nBound));
        }
        
        int prev = 0;
        for (int i = 0; i < nSize; i++) {
            int cur = pq.delFirst();
            if (i > 0 && prev < cur) {
                System.out.println("MaxPQ incorrect.");
                break;
            }
            
            prev = cur;
            System.out.println(cur);
        }
        
        System.out.println("MaxPQ correct.");
    }
}
