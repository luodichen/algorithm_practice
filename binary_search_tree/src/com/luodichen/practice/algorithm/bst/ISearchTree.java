package com.luodichen.practice.algorithm.bst;

public interface ISearchTree<K extends Comparable<K>, V> {
    public int put(K key, V value);
    public V get(K key);
}
