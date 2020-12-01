package ru.sber.game;

import java.util.*;
import java.util.stream.Collectors;

public class SquareBoard<V> extends Board<Key, V>{

    public SquareBoard(int size) {
        super(size, size);
    }

    /** Заполняем поле элементами из входного списка.
     * Если нужно задать пустой элемент, указываем null.
     */
    @Override
    public void fillBoard(List<V> list) {
        Map<Key, V> helpMap = new HashMap<>();
        int w = 0;
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWeight(); j++) {
                Key key = new Key(i, j);
                if(w < list.size()) {
                    helpMap.put(key, list.get(w++));
                } else {
                    helpMap.put(key, null);
                }
            }
        }
        setBoard(helpMap);
    }

    /** Возвращаем все ключи, у которых значение null. */
    @Override
    public List<Key> availableSpace() {
        List<Key> list = new ArrayList<>();
        Set<Key> keys = getBoard().keySet();
        for (Key key : keys) {
            if (getBoard().get(key) == null) {
                list.add(key);
            }
        }
        return list;
    }

    /** Добавляем элемент {@param value} по ключу {@param key}. */
    @Override
    public void addItem(Key key, V value) {
        Map<Key, V> helpMap = getBoard();
        helpMap.put(key, value);
        setBoard(helpMap);
    }

    /** Ищем уже существующий ключ по координатам {@param i} {@param j}. */
    @Override
    public Key getKey(int i, int j) {
        for (Key key : getBoard().keySet()) {
            if ((key.getI() == i) && (key.getJ() == j)) {
                return key;
            }
        }
        return null;
    }

    /** Получаем значение по {@param key} */
    @Override
    public V getValue(Key key) {
        V val = getBoard().containsKey(key) ? getBoard().get(key) : null;
        return val;
    }

    /** Получаем столбец ключей, по которым потом можно будет выбрать значения. */
    @Override
    public List<Key> getColumn(int j) {
        List<Key> list = new ArrayList<>();

        for (int i = 0; i < getWeight(); i++) {
            list.add(getKey(i, j));
        }

        return list;
    }

    private List<Key> sortColumn(List<Key> list) {

        for (int i = 0; i < list.size(); i++) {
            list.sort(this::keyComparator);
        }

        return list;
    }

    private int keyComparator(Key k1, Key k2) {
        int a = k1.getI();
        int b = k2.getI();
        return (a < b) ? -1 : ((a == b) ? 0 : 1);
    }


    /** Получаем строку ключей, по которым потом можно будет выбрать значения. */
    @Override
    public List<Key> getRow(int i) {
        List<Key> list = new ArrayList<>();

        for (int j = 0; j < getHeight(); j++) {
            list.add(getKey(i, j));
        }

        return list;
    }

    private List<Key> sortRow(List<Key> list) {

        for (int i = 0; i < list.size(); i++) {
            list.sort(this::keyComparatorRow);
        }

        return list;
    }

    private int keyComparatorRow(Key k1, Key k2) {
        int a = k1.getJ();
        int b = k2.getJ();
        return (a < b) ? -1 : ((a == b) ? 0 : 1);
    }

    /** Проверяем, есть ли такое значение на поле. */
    @Override
    public boolean hasValue(V value) {
        return getBoard().containsValue(value);
    }

    /** Получаем строку значений по строке ключей. */
    @Override
    public List<V> getValues(List<Key> keys) {
        List<V> list = new ArrayList<>();

        for (Key key : keys) {
            list.add(getBoard().get(key));
        }

        return list;
    }
}
