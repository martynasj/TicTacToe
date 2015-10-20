package dk.jankauskas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Board {

    private ObservableList<Mark> cells;

    // Default constructor, board size 9
    public Board() {
        this(9);
    }

    // Constructor with board size parameter
    public Board(int size) {
        cells = FXCollections.observableArrayList();

        for (int i = 0; i < size; i++) {
            cells.add(Mark.EMPTY);
        }
        System.out.println("New board is constructed with size: " + cells.size());
    }

    public void setCellValue(int cellNumber, Mark playerSymbol) {
        if (cells.get(cellNumber).equals(Mark.EMPTY)) {
            cells.set(cellNumber, playerSymbol);
        } else {
            System.out.println("This cell is already occupied");
        }
    }

    public ObservableList<Mark> getCells() {
        return cells;
    }

    public void clearBoard() {
        for (Mark m : cells) {
            m = Mark.EMPTY;
        }
    }

    public boolean isFull() {
        for (Mark cell : cells) {
            if (cell == Mark.EMPTY) {
                return false;
            }
        }
        return true;
    }

    public boolean cellAvailable(int cell) {
        return cells.get(cell).equals(Mark.EMPTY);
    }

    public Mark getWinner() {
            // ========== Check rows ========== //
            for (int i = 0; i < 3; i++) {
                if (!cells.get(i*3).equals(Mark.EMPTY) && cells.get(i*3).equals(cells.get(i*3+1)) && cells.get(i*3+1).equals(cells.get(i*3+2))) {
                    return cells.get(i*3);
                }
            }

            // ========== Check columns ========== //
            for (int i = 0; i < 3; i++) {
                if (!cells.get(i).equals(Mark.EMPTY) && cells.get(i).equals(cells.get(i+3)) && cells.get(i+3).equals(cells.get(i+6))) {
                    return cells.get(i);
                }
            }

            // ========== Check diagonal ========== //
            if ((!cells.get(0).equals(Mark.EMPTY) && cells.get(0).equals(cells.get(4)) && cells.get(4).equals(cells.get(8))) ||
                    (!cells.get(2).equals(Mark.EMPTY) && cells.get(2).equals(cells.get(4)) && cells.get(4).equals(cells.get(6)))) {
                return cells.get(4); // returns symbol of the middle cell
            }
        return Mark.EMPTY;
    }

    public int getSize() {
        return cells.size();
    }

    public Mark getCellValue(int number) {
        return cells.get(number);
    }

}
