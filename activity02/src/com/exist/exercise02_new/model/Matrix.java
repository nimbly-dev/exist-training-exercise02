package src.com.exist.exercise02_new.model;

import java.util.ArrayList;

import src.com.exist.exercise02_new.utility.Helper;
import src.com.exist.exercise02_new.utility.Reader;

public class Matrix {
    static final String ASCII = "0123456789";   
    //ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz

    ArrayList<ArrayList<Cell>> matrixTable;

    int col, row;

    public Matrix(ArrayList<ArrayList<Cell>> matrixTable, boolean isInitialization){
        if(isInitialization == true){
            System.out.println("Successfully readed the file");
            this.matrixTable = matrixTable;
        }else{
            this.row = Reader.readInt("Enter row ");
            this.col = Reader.readInt("Enter column ");
            this.matrixTable = matrixTable;
            for (int i = 0; i < this.row; i++) {
                ArrayList<Cell> rowList = new ArrayList<Cell>();
                for (int j = 0; j < this.col; j++) {
                    // Cell cell = new Cell(i+","+j, Helper.generateAsciiChar(ASCII));
                    rowList.add(new Cell(i+","+j, Helper.generateAsciiChar(ASCII)));
                }
                this.matrixTable.add(rowList);
            }
        }
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public ArrayList<ArrayList<Cell>> getMatrixTable() {
        return matrixTable;
    }

    public void setMatrixTable(ArrayList<ArrayList<Cell>> matrixTable) {
        this.matrixTable = matrixTable;
    }

}
