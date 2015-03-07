package com.luodichen.practice.algorithm.bst;

public class SearchTree<K extends Comparable<K>, V> implements ISearchTree<K, V> {

    private BSTNode<K, V> mRoot = null;
    
    @Override
    public int put(K key, V value) {
        int ret = 0;
        BSTNode<K, V> found = find(key);
        
        if (null == found) {
            mRoot = new BSTNode<K, V>(key, value, 0);
            return ret;
        }
        
        int nCompare = key.compareTo(found.getKey());
        if (0 == nCompare) {
            found.set(value);
        } else if (nCompare < 0) {
            BSTNode<K, V> node = new BSTNode<K, V>(key, value, found.getDepth() + 1);
            found.setLeft(node);
            node.setParent(found);
            ret = node.getDepth();
        } else {
            BSTNode<K, V> node = new BSTNode<K, V>(key, value, found.getDepth() + 1);
            found.setRight(node);
            node.setParent(found);
            ret = node.getDepth();
        }
        
        return ret; 
    }

    @Override
    public V get(K key) {
        BSTNode<K, V> found = find(key);
        if (null == found)
            return null;
        
        if (0 == key.compareTo(found.getKey()))
            return found.get();
        
        return null;
    }
    
    public int getDepth(K key) {
        int ret = -1;
        
        BSTNode<K, V> found = find(key);
        if (null != found) {
            ret = found.getDepth();
        }
        
        return ret;
    }
    
    private BSTNode<K, V> find(K key) {
        BSTNode<K, V> ret = null;
        if (null == (ret = mRoot))
            return null;
        
        BSTNode<K, V> next = ret;
        do {
            ret = next;
            
            int nCompare = key.compareTo(ret.getKey());
            if (0 == nCompare) {
                break;
            } else if (nCompare < 0) {
                next = ret.getLeft();
            } else {
                next = ret.getRight();
            }
        } while (next != null);
        return ret;
    }
}
