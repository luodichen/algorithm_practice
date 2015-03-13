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
//        while (null != current) {
//            current = (RBTNode<K, V>)(transform(current).getParent());
//        }
        transform(current);
        ((RBTNode<K, V>)mRoot).setColor(RBTNode.Color.BLACK);
    }
    
    private void transform(RBTNode<K, V> node) {
        RBTNode<K, V> cur = node;
        
        while (null != cur) {
            if (isRed((RBTNode<K, V>)cur.getRight()) && !isRed((RBTNode<K, V>)cur.getLeft())) {
                cur = rotateLeft(cur);
            } else if (isRed((RBTNode<K, V>)cur.getRight())) {
                cur = flipColors(cur);
            }
            
            if (isRed((RBTNode<K, V>)cur) && isRed((RBTNode<K, V>)cur.getLeft())) {
                cur = flipColors(rotateRight((RBTNode<K, V>)cur.getParent()));
            } else {
                cur = (RBTNode<K, V>)cur.getParent();
            }
        }
    }
    
    private RBTNode<K, V> rotateRight(RBTNode<K, V> node) {
        if (!isRed((RBTNode<K, V>)node.getLeft()) || isRed(node)) {
            throw new RuntimeException("not supported.");
        }
        
        BSTNode<K, V> parent = node.getParent();
        
        RBTNode<K, V> ret = (RBTNode<K, V>)node.getLeft();
        node.setColor(RBTNode.Color.RED);
        ret.setColor(RBTNode.Color.BLACK);
        
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
