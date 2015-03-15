package com.luodichen.practice.algorithm.bst;

public class SearchTree<K extends Comparable<K>, V> implements ISearchTree<K, V> {

    protected BSTNode<K, V> mRoot = null;
    
    @Override
    public void put(K key, V value) {
        BSTNode<K, V> found = find(key).mFound;
        
        if (null == found) {
            mRoot = new BSTNode<K, V>(key, value);
            return;
        }
        
        putOn(found, key, value);
    }
    
    protected void putOn(BSTNode<K, V> target, K key, V value) {
        int nCompare = key.compareTo(target.getKey());
        if (0 == nCompare) {
            target.set(value);
        } else if (nCompare < 0) {
            BSTNode<K, V> node = new BSTNode<K, V>(key, value);
            target.setLeft(node);
        } else {
            BSTNode<K, V> node = new BSTNode<K, V>(key, value);
            target.setRight(node);
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
    
    protected class FindResult {
        public int mDepth = -1;
        public BSTNode<K, V> mFound = null;
    }
    
    protected FindResult find(K key) {
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

    @Override
    public void remove(K key) {
        BSTNode<K, V> found = find(key).mFound;
        if (null == found)
            return;
        
        BSTNode<K, V> left = found.getLeft();
        BSTNode<K, V> right = found.getRight();
        BSTNode<K, V> parent = found.getParent();
        BSTNode<K, V> newChild = null;
        
        if (null == left && null == right) {
            newChild = null;
        } else if (null == left) {
            newChild = right;
        } else if (null == right) {
            newChild = left;
        } else {
            newChild = removeMinNode(right);
            newChild.setLeft(left);
            
            if (newChild != right) {
                newChild.setRight(right);
            }
            
            //newChild.setParent(parent);
        }

        if (null == parent) {
            mRoot = newChild;
        } else if (parent.getLeft() == found) {
            parent.setLeft(newChild);
        } else if (parent.getRight() == found) {
            parent.setRight(newChild);
        } else {
            throw new RuntimeException("It's impossible.");
        }
    }
    
    private BSTNode<K, V> removeMinNode(BSTNode<K, V> root) {
        BSTNode<K, V> ret = root;
        while (null != ret.getLeft()) {
            ret = ret.getLeft();
        }
        
        BSTNode<K, V> right = ret.getRight();
        ret.getParent().setLeft(right);
        
        ret.setParent(null);
        ret.setRight(null);
        
        return ret;
    }
}
