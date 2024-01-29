package de.tum.cit.ase.maze;
import de.tum.cit.ase.maze.Square;
public class Cell {
    public int row; //this will be the index of the row a certain cell will be in same for column
    public int column;

    public CellType cellType; //this is the cell type the cell will have defined in cell type enum class

    public Cell(int row, int column, CellType cellType) {
        this.row = row;
        this.column = column;
        this.cellType = cellType;
    }

    public Square getSquare(){
        return new Square(column * 16,row * 16,16,16); //defining cell size based on the square class
    }
}
