package com.luodichen.practice.algorithm.bst;

public class BSTNode<K, V> {
    private K mKey = null;
    private V mValue = null;
    
    private BSTNode<K, V> mParent = null;
    private BSTNode<K, V> mLeftChild = null;
    private BSTNode<K, V> mRightChild = null;
    
    public BSTNode(K key) {
        mKey = key;
    }
    
    public BSTNode(K key, V value) {
        mKey = key;
        mValue = value;
    }
    
    public void set(V value) {
        mValue = value;
    }
    
    public void setKey(K key) {
        mKey = key;
    }
    
    public V get() {
        return mValue;
    }
    
    public K getKey() {
        return mKey;
    }
    
    public void setLeft(BSTNode<K, V> node) {
        mLeftChild = node;
        if (null != node) {
            node.setParent(this);
        }
    }
    
    public void setRight(BSTNode<K, V> node) {
        mRightChild = node;
        if (null != node) {
            node.setParent(this);
        }
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
}
