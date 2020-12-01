package ru.sber.game;

import java.util.Objects;

public class Key {
    private int i;
    private int j;

    public Key(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return i == key.i &&
                j == key.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}
