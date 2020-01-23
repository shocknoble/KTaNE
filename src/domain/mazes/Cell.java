package domain.mazes;

import java.util.ArrayList;


public class Cell {

    public Integer row;
    public Integer col;
    public ArrayList<Integer> walls = new ArrayList<Integer>();
    public Boolean visited = false;

    public Cell(Integer row, Integer col) {
        this.row = row;
        this.col = col;
    }

}
