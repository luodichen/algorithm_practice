package com.luodichen.practice.algorithm.bst;

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
    
    public boolean checkCorrect() {
        CheckCorrectResult result = new CheckCorrectResult();
        checkCorrect((RBTNode<K, V>)mRoot, 0, result);
        return result.mCorrect;
    }
    
    private class CheckCorrectResult {
        public boolean mCorrect = true;
        public int mLeafDepth = -1;
    }
    
    private void checkCorrect(RBTNode<K, V> node, int nDepth, CheckCorrectResult result) {
      
        RBTNode<K, V> left = (RBTNode<K, V>)node.getLeft();
        RBTNode<K, V> right = (RBTNode<K, V>)node.getRight();
        
        if (null == left && null == right) {
            if (-1 == result.mLeafDepth) {
                result.mLeafDepth = nDepth;
            } else if (result.mLeafDepth != nDepth) {
                System.out.println("red-black tree check incorrect - leaf depth doesn't match at key " + node.getKey());
                result.mCorrect = false;
            }
        }
        
        if (null != left) {
            if (isRed(node) && isRed(left)) {
                System.out.println("red-black tree check incorrect - no black nodes between two red nodes at key " + node.getKey());
                result.mCorrect = false;
            }
            
            checkCorrect(left, nDepth + (isRed(left) ? 0 : 1), result);
        }
        
        if (null != right) {
            if (isRed(node) && isRed(right)) {
                System.out.println("red-black tree check incorrect - no black nodes between two red nodes at key " + node.getKey());
                result.mCorrect = false;
            }
            
            checkCorrect(right, nDepth + (isRed(right) ? 0 : 1), result);
        }
    }
}
