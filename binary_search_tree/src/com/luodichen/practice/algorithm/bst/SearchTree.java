package com.luodichen.practice.algorithm.bst;

public class SearchTree<K extends Comparable<K>, V> implements ISearchTree<K, V> {

    private BSTNode<K, V> mRoot = null;
    
    @Override
    public void put(K key, V value) {
        BSTNode<K, V> found = find(key).mFound;
        
        if (null == found) {
            mRoot = new BSTNode<K, V>(key, value);
            return;
        }
        
        int nCompare = key.compareTo(found.getKey());
        if (0 == nCompare) {
            found.set(value);
        } else if (nCompare < 0) {
            BSTNode<K, V> node = new BSTNode<K, V>(key, value);
            found.setLeft(node);
            node.setParent(found);
        } else {
            BSTNode<K, V> node = new BSTNode<K, V>(key, value);
            found.setRight(node);
            node.setParent(found);
        }
    }

    @Override
    public V get(K key) {
        BSTNode<K, V> found = find(key).mFound;
        if (null == found)
            return null;
        
        if (0 == key.compareTo(found.getKey()))
            return found.get();
        
        return null;
    }
    
    public int getDepth(K key) {
        return find(key).mDepth;
    }
    
    private class FindResult {
        public int mDepth = -1;
        public BSTNode<K, V> mFound = null;
    }
    
    private FindResult find(K key) {
        FindResult ret = new FindResult();
        if (null == (ret.mFound = mRoot))
            return ret;
        
        BSTNode<K, V> next = ret.mFound;
        do {
            ret.mDepth++;
            ret.mFound = next;
            
            int nCompare = key.compareTo(ret.mFound.getKey());
            if (0 == nCompare) {
                break;
            } else if (nCompare < 0) {
                next = ret.mFound.getLeft();
            } else {
                next = ret.mFound.getRight();
            }
        } while (next != null);
        return ret;
    }
}
