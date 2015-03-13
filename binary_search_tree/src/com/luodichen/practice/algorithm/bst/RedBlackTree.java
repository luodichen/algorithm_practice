package com.luodichen.practice.algorithm.bst;

/*
 * This isn't a standard Red-Black Tree, red-right child was allowed in standard
 * Red-Black Tree, but it may not appear in this implement.
 */

public class RedBlackTree<K extends Comparable<K>, V> extends SearchTree<K, V> {
    
    @Override
    public void put(K key, V value) {
        RBTNode<K, V> found = (RBTNode<K, V>)(find(key).mFound);
        if (null == found) {
            mRoot = new RBTNode<K, V>(key, value);
        } else {
            putOn(found, key, value);
        }
    }

    @Override
    protected void putOn(BSTNode<K, V> target, K key, V value) {
        RBTNode<K, V> rbTarget = (RBTNode<K, V>)target;
        int nCompare = key.compareTo(rbTarget.getKey());
        
        if (0 == nCompare) {
            rbTarget.set(value);
        } else if (nCompare < 0) {
            RBTNode<K, V> node = new RBTNode<K, V>(key, value);
            node.setColor(RBTNode.Color.RED);
            rbTarget.setLeft(node);
        } else {
            RBTNode<K, V> node = new RBTNode<K, V>(key, value);
            node.setColor(RBTNode.Color.RED);
            rbTarget.setRight(node);
        }
        
        RBTNode<K, V> current = rbTarget;
        while (null != current) {
            current = (RBTNode<K, V>)(transform(current).getParent());
        }
        ((RBTNode<K, V>)mRoot).setColor(RBTNode.Color.BLACK);
    }
    
    private RBTNode<K, V> transform(RBTNode<K, V> node) {
        RBTNode<K, V> ret = node;
        
        if (isRed((RBTNode<K, V>)ret.getRight()) && !isRed((RBTNode<K, V>)ret.getLeft())) {
            ret = rotateLeft(ret);
        } else if (isRed((RBTNode<K, V>)ret.getRight())) {
            ret = flipColors(ret);
        }
        
        if (isRed((RBTNode<K, V>)ret) && isRed((RBTNode<K, V>)ret.getLeft())) {
            ret = flipColors(rotateRight((RBTNode<K, V>)ret.getParent()));
        }
        
        return ret;
    }
    
    private RBTNode<K, V> rotateRight(RBTNode<K, V> node) {
        BSTNode<K, V> parent = node.getParent();
        
        RBTNode<K, V> ret = (RBTNode<K, V>)node.getLeft();
        RBTNode.Color tColor = node.getColor();
        node.setColor(ret.getColor());
        ret.setColor(tColor);
        
        node.setLeft(ret.getRight());
        ret.setRight(node);
        
        ret.setParent(parent);
        
        if (null != parent) {
            if (parent.getLeft() == node) {
                parent.setLeft(ret);
            } else {
                parent.setRight(ret);
            }
        } else {
            mRoot = ret;
            mRoot.setParent(null);
            ((RBTNode<K, V>)mRoot).setColor(RBTNode.Color.BLACK);
        }
        return ret;
    }
    
    private RBTNode<K, V> rotateLeft(RBTNode<K, V> node) {
        BSTNode<K, V> parent = node.getParent();
        
        RBTNode<K, V> ret = (RBTNode<K, V>)node.getRight();
        RBTNode.Color tColor = node.getColor();
        node.setColor(ret.getColor());
        ret.setColor(tColor);

        node.setRight(ret.getLeft());
        ret.setLeft(node);
        
        if (null != parent) {
            if (parent.getLeft() == node) {
                parent.setLeft(ret);
            } else {
                parent.setRight(ret);
            }
        } else {
            mRoot = ret;
            mRoot.setParent(null);
            ((RBTNode<K, V>)mRoot).setColor(RBTNode.Color.BLACK);
        }
        return ret;
    }
    
    private RBTNode<K, V> flipColors(RBTNode<K, V> node) {
        ((RBTNode<K, V>)node.getLeft()).setColor(RBTNode.Color.BLACK);
        ((RBTNode<K, V>)node.getRight()).setColor(RBTNode.Color.BLACK);
        node.setColor(RBTNode.Color.RED);
        
        return node;
    }
    
    private boolean isRed(RBTNode<K, V> node) {
        return (null != node) && node.isRed();
    }
    
    private RBTNode<K, V> removeMinNode(RBTNode<K, V> root) {
        RBTNode<K, V> cur = root;
        RBTNode<K, V> ret = null;
        while (null != cur.getLeft()) {
            cur = (RBTNode<K, V>)cur.getLeft();
        }
        ret = cur;
        cur = (RBTNode<K, V>)cur.getParent();
        ret.getParent().setLeft(ret.getRight());
        
        ret.setLeft(null);
        ret.setRight(null);
        ret.setParent(null);
        
        if (isRed(ret)) {
            return ret;
        }
        
        while (null != cur) {
            RBTNode<K, V> left = (RBTNode<K, V>)cur.getLeft();
            RBTNode<K, V> right = (RBTNode<K, V>)cur.getRight();
            
            
            
            if (!isRed((RBTNode<K, V>)right.getLeft())) {
                right.setColor(RBTNode.Color.RED);
                if (isRed((RBTNode<K, V>)cur)) {
                    cur.setColor(RBTNode.Color.BLACK);
                    cur = rotateLeft(cur);
                    break;
                } else {
                    cur = rotateLeft(cur);
                    cur = (RBTNode<K, V>)cur.getParent();
                    continue;
                }
            } else {
                
            }
        }
        
        return ret;
    }
}
