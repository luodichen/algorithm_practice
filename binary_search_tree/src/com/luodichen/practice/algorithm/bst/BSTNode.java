package com.luodichen.practice.algorithm.bst;

public class BSTNode<K, V> {
    private K mKey = null;
    private V mValue = null;
    
    private BSTNode<K, V> mParent = null;
    private BSTNode<K, V> mLeftChild = null;
    private BSTNode<K, V> mRightChild = null;

    int mDepth = 0;
    
    public BSTNode(K key, int depth) {
        mKey = key;
        mDepth = depth;
    }
    
    public BSTNode(K key, V value, int depth) {
        mKey = key;
        mValue = value;
        mDepth = depth;
    }
    
    public void set(V value) {
        mValue = value;
    }
    
    public V get() {
        return mValue;
    }
    
    public K getKey() {
        return mKey;
    }
    
    public void setLeft(BSTNode<K, V> node) {
        mLeftChild = node;
    }
    
    public void setRight(BSTNode<K, V> node) {
        mRightChild = node;
    }
    
    public void setParent(BSTNode<K, V> parent) {
        mParent = parent;
    }
    
    public BSTNode<K, V> getLeft() {
        return mLeftChild;
    }
    
    public BSTNode<K, V> getRight() {
        return mRightChild;
    }
    
    public BSTNode<K, V> getParent() {
        return mParent;
    }
    
    public int getDepth() {
        return mDepth;
    }
}
