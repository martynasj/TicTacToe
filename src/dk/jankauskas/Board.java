package dk.jankauskas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Board {

    private ObservableList<String> cells;

    // Default constructor, board size 9
    public Board() {
        this(9);
    }

    // Constructor with board size parameter
    public Board(int size) {
        cells = FXCollections.observableArrayList();

        for (int i = 0; i < size; i++) {
            cells.add(null);
        }
        System.out.println("New board is constructed with size: " + cells.size());
    }

    public void setCellValue(int cellNumber, String playerSymbol) {
        if (cells.get(cellNumber) == null) {
            cells.set(cellNumber, playerSymbol);
        } else {
            System.out.println("This cell is already occupied");
        }
    }

    public ObservableList<String> getCells() {
        return cells;
    }

    public void clearBoard() {
        for (String s : cells) {
            s = null;
        }
    }

    public boolean isFull() {
        for (String cell : cells) {
            if (cell == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isWon() {
        boolean won = false;
        // always trows null pointer exception, because initially squares are set to be null
        try {
            // ========== Check rows ========== //
            for (int i = 0; i < 3; i++) {
                if (cells.get(i*3).equals(cells.get(i*3+1)) && cells.get(i*3+1).equals(cells.get(i*3+2))) {
                    won = true;
                }
            }

            // ========== Check columns ========== //
            for (int i = 0; i < 3; i++) {
                if (cells.get(i).equals(cells.get(i+3)) && cells.get(i+3).equals(cells.get(i+6))) {
                    won = true;
                }
            }

            // ========== Check diagonal ========== //
            if ((cells.get(0).equals(cells.get(4)) && cells.get(4).equals(cells.get(8))) ||
                    (cells.get(0).equals(cells.get(4)) && cells.get(4).equals(cells.get(8)))) {
                won = true;
            }
        } catch (NullPointerException e) {
            // do nothing
        }
        return won;
    }

    public int getSize() {
        return cells.size();
    }

    public String getCellValue(int number) {
        return cells.get(number);
    }

}
