package dk.jankauskas;

import java.util.Arrays;

public class Board {

    private int[] cells;

    // Default constructor, board size 9
    public Board() {
        this(9);
    }

    // Constructor with board size parameter
    public Board(int size) {
        cells = new int[size];
        System.out.println("New board is constructed with size: " + size);
    }

    public void markCell(int cellNumber) {
        cells[cellNumber] = 1;
        System.out.println(Arrays.toString(cells));
    }

    public int getSize() {
        return cells.length;
    }

}
