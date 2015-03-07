package com.luodichen.practice.algorithm.bst;

public interface ISearchTree<K extends Comparable<K>, V> {
    public void put(K key, V value);
    public void remove(K key);
    public V get(K key);
}
