package com.luodichen.practice.algorithm.pq;

public class MaxPQ<T extends Comparable<T>> extends PriorityQueue<T> {

    @Override
    protected int compare(T left, T right) {
        return right.compareTo(left);
    }
}
