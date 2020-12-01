package ru.sber.game;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public abstract class Board <K, V> {

    private int weight;
    private int height;
    private Map<K, V> board = new HashMap<>();

    public Board(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    public void setBoard(Map<K, V> board) {
        this.board = board;
    }

    public Map<K, V> getBoard() {
        return board;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void fillBoard(List<V> list);
    public abstract List<K> availableSpace();
    public abstract void addItem(K key, V value);
    public abstract K getKey(int i, int j);
    public abstract V getValue(K key);
    public abstract List<K> getColumn(int j);
    public abstract List<K> getRow(int i);
    public abstract boolean hasValue(V value);
    public abstract List<V> getValues(List<K> keys);

}
