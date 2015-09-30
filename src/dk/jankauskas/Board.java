package dk.jankauskas;

import java.util.Arrays;

public class Board {

    public int[] cells = new int[9];

    public Board() {
        System.out.println("New board is constructed");
        System.out.println(Arrays.toString(cells));
    }

    public void markCell(int cellNumber) {
        cells[cellNumber] = 1;
    }

}
