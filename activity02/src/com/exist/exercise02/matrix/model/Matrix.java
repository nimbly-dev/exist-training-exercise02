package src.com.exist.exercise02.matrix.model;

import java.util.ArrayList;
import java.util.HashMap;

import src.com.exist.exercise02.matrix.utility.Helper;
import src.com.exist.exercise02.matrix.view.Text;


public class Matrix{
    private ArrayList<HashMap<String, Cell>> matrixTable;

    int row,col;

    public Matrix(ArrayList<HashMap<String, Cell>> matrixTable){ this.matrixTable = matrixTable; }

    public Matrix(ArrayList<HashMap<String, Cell>> matrixTable, int row, int col) {
        this.row = row;
        this.col = col;
        this.matrixTable = matrixTable;
        //FILL UP ARRAY
        for (int i = 0; i < row; i++) {
            HashMap<String,Cell> rowMap = new HashMap<>(); //Initialize Row
            for (int j = 0; j < col; j++) {
                Cell cell = new Cell(Helper.generateAsciiChar(Text.ASCII));
                rowMap.put(i+","+j, cell);
            }
            this.matrixTable.add(rowMap);
        }
    }

    public ArrayList<HashMap<String, Cell>> getMatrixTable() {
        return matrixTable;
    }

    public void setMatrixTable(ArrayList<HashMap<String, Cell>> matrixTable) {
        this.matrixTable = matrixTable;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    
}
