package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = row < data.length && column < data[row].length;
        if (!rsl && row < data.length - 1) {
            for (int i = row + 1; i < data.length; i++) {
                if (data[i].length > 0) {
                    row = i;
                    column = 0;
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
