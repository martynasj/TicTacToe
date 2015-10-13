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

    public void setCellValue(int cellNumber) {
        cells.set(cellNumber, "X");
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

    public int getSize() {
        return cells.size();
    }

    public String getCellValue(int number) {
        return cells.get(number);
    }

}
