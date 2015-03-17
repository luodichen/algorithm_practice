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
        if (!isRed((RBTNode<K, V>)node.getLeft())) {
            throw new RuntimeException("not supported.");
        }
        
        BSTNode<K, V> parent = node.getParent();
        RBTNode<K, V> ret = (RBTNode<K, V>)node.getLeft();
        
        ret.setColor(node.getColor());
        node.setColor(RBTNode.Color.RED);
        
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
        if (!isRed((RBTNode<K, V>)node.getRight())) {
            throw new RuntimeException("not supported.");
        }
        
        BSTNode<K, V> parent = node.getParent();       
        RBTNode<K, V> ret = (RBTNode<K, V>)node.getRight();
        
        ret.setColor(node.getColor());
        node.setColor(RBTNode.Color.RED);

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
    
    private RBTNode<K, V> unfilpColors(RBTNode<K, V> node) {
        ((RBTNode<K, V>)node.getLeft()).setColor(RBTNode.Color.RED);
        ((RBTNode<K, V>)node.getRight()).setColor(RBTNode.Color.RED);
        node.setColor(RBTNode.Color.BLACK);
        
        return node;
    }
    
    private boolean isRed(RBTNode<K, V> node) {
        return (null != node) && node.isRed();
    }
    
    private RBTNode<K, V> removeMinNode(RBTNode<K, V> root) {
        RBTNode<K, V> cur = root;
        while (null != cur.getLeft()) {
            if (!isRed((RBTNode<K, V>)cur.getLeft()) 
                    && null != cur.getLeft() 
                    && !isRed((RBTNode<K, V>)cur.getLeft().getLeft())) {
                cur = moveRedLeft(cur);
            }
            
            cur = (RBTNode<K, V>)cur.getLeft();
        }
        
        if (cur != root) {
            RBTNode<K, V> parent = (RBTNode<K, V>)cur.getParent();
            if (null != parent) {
                parent.setLeft(cur.getRight());
            }
        }
        
        return cur;
    }
    
    @Override
    public void remove(K key) {
        if (!isRed((RBTNode<K, V>)mRoot.getLeft()) 
                && !isRed((RBTNode<K, V>)mRoot.getRight())) {
            ((RBTNode<K, V>)mRoot).setColor(RBTNode.Color.RED);
        }
        
        RBTNode<K, V> cur = (RBTNode<K, V>)mRoot;
        
        while (null != cur) {
            if (key.compareTo(cur.getKey()) < 0) {
                if (!isRed((RBTNode<K, V>)cur.getLeft())
                        && null != cur.getLeft()
                        && !isRed((RBTNode<K, V>)cur.getLeft().getLeft())) {
                    cur = moveRedLeft(cur);
                }
                cur = (RBTNode<K, V>)cur.getLeft();
            } else {
                if (isRed((RBTNode<K, V>)cur.getLeft())) {
                    cur = rotateRight(cur);
                }
                if (0 == key.compareTo(cur.getKey()) && null == cur.getRight()) {
                    RBTNode<K, V> parent = (RBTNode<K, V>)cur.getParent();
                    if (null != parent) {
                        if (parent.getLeft() == cur) {
                            parent.setLeft(cur.getLeft());
                        } else {
                            parent.setRight(cur.getLeft());
                        }
                    } else {
                        mRoot = null;
                    }
                    
                    cur = (RBTNode<K, V>)parent;
                    break;
                }
                if (!isRed((RBTNode<K, V>)cur.getRight())
                        && null != cur.getRight()
                        && !isRed((RBTNode<K, V>)cur.getRight().getLeft())) {
                    cur = moveRedRight(cur);
                }
                if (0 == key.compareTo(cur.getKey())) {
                    RBTNode<K, V> minNode = removeMinNode((RBTNode<K, V>)cur.getRight());
                    if (minNode == cur.getRight()) {
                        cur.setRight(null);
                    }
                    cur.setKey(minNode.getKey());
                    cur.set(minNode.get());
                    cur = (RBTNode<K, V>)minNode.getParent();
                    break;
                } else {
                    cur = (RBTNode<K, V>)cur.getRight();
                }
            }
        }
        
        if (null != cur) {
            balance(cur);
        }
        
        if (null != mRoot) {
            ((RBTNode<K, V>)mRoot).setColor(RBTNode.Color.BLACK);
        }
    }
    
    private void balance(RBTNode<K, V> node) {
        RBTNode<K, V> cur = node;
        
        while (null != cur) {
            if (isRed((RBTNode<K, V>)cur.getRight()) 
                    && !isRed((RBTNode<K, V>)cur.getLeft())
                    && !isRed(cur)) {
                cur = (RBTNode<K, V>)rotateLeft(cur);
            }
            if (isRed((RBTNode<K, V>)cur.getLeft()) && isRed((RBTNode<K, V>)cur.getRight())) {
                cur = flipColors(cur);
            }
            if (isRed((RBTNode<K, V>)cur.getLeft()) && null != cur.getLeft()
                    && isRed((RBTNode<K, V>)cur.getLeft().getLeft())) {
                cur = (RBTNode<K, V>)rotateRight(cur);
            }
            if (isRed((RBTNode<K, V>)cur.getLeft()) && isRed((RBTNode<K, V>)cur.getRight())) {
                cur = flipColors(cur);
            }
            
            cur = (RBTNode<K, V>)cur.getParent();
        }
    }

    private RBTNode<K, V> moveRedLeft(RBTNode<K, V> node) {
        if (!isRed(node)) {
            throw new RuntimeException("node must be red, there is something wrong...");
        }
        
        RBTNode<K, V> ret = node;
        unfilpColors(ret);
        if (null != ret.getRight() && isRed((RBTNode<K, V>)ret.getRight().getLeft())) {
            ret.setRight(rotateRight((RBTNode<K, V>)ret.getRight()));
            ret = rotateLeft(ret);
        }
        
        return ret;
    }
    
    private RBTNode<K, V> moveRedRight(RBTNode<K, V> node) {
        if (!isRed(node)) {
            throw new RuntimeException("node must be red, there is something wrong...");
        }
        
        RBTNode<K, V> ret = node;
        unfilpColors(ret);
        if (null != node.getLeft() && !isRed((RBTNode<K, V>)node.getLeft().getLeft())) {
            ret = rotateRight(ret);
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
        if (null == node) {
            result.mCorrect = true;
            return;
        }
        
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
            if (isRed(right)) {
                System.out.println("red-black tree check incorrect - right-red node at key " + node.getKey());
                result.mCorrect = false;
            }
            
            checkCorrect(right, nDepth + (isRed(right) ? 0 : 1), result);
        }
    }
}
