package com.luodichen.practice.algorithm.bst;

public class RBTNode<K, V> extends BSTNode<K, V> {
    
    private Color mColor = Color.BLACK;

    public RBTNode(K key, V value) {
        super(key, value);
    }

    public RBTNode(K key) {
        super(key);
    }

    public enum Color {
        BLACK, RED
    }
    
    public void setColor(Color color) {
        mColor = color;
    }
    
    public Color getColor() {
        return mColor;
    }
    
    public boolean isRed() {
        return Color.RED == mColor;
    }
}
