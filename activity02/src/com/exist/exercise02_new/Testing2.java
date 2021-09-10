package src.com.exist.exercise02_new;

import java.util.ArrayList;
import java.util.Collections;

import src.com.exist.exercise02_new.model.Cell;
import src.com.exist.exercise02_new.model.Matrix;
import src.com.exist.exercise02_new.utility.Reader;


public class Testing2 {
    static final String ASCII = "0123456789";   

    interface MatrixOperations{
        boolean ifFound(int i);
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Cell>> matrixTable = new ArrayList<ArrayList<Cell>>();
        Matrix matrix = new Matrix(matrixTable, true);


        //PRINT TABLE CODE
        for (int i = 0; i < matrix.getMatrixTable().size(); i++) {
            matrix.getMatrixTable().get(i).stream().forEach((cell)->
                System.out.printf("{ "+cell.getKey() +" : (" + cell.getValue() + ") }")
            );
            System.out.println();
        }


        int totalFound = 0;
        //SEARCH COMMAND CODE
        // String searchValue = Reader.readString("Enter search value ");
        // for (int i = 0; i < matrix.getMatrixTable().size(); i++) {

        //     for (int j = 0; j < matrix.getMatrixTable().get(i).size(); j++) {

        //         if(matrix.getMatrixTable().get(i).get(j).getValue().contains(searchValue)){
        //             int countInstances = Helper.countChar(matrix.getMatrixTable().get(i).get(j).getValue(), searchValue);
        //             System.out.println("Found " + searchValue + " on index ("+i+","+j+") with "+countInstances+ " instances" );
        //             ++totalFound; 
        //             break;                   
        //         }

        //     }
        // }
        // System.out.println("Total Comparisons found: "+totalFound);

        // //UPDATE COMMAND CODE
        // String inputKey = Reader.readString("Enter key ");
        // String inputNewValue = Reader.readString("Enter new value ");
        // boolean didUpdate = false;
        // for (int i = 0; i < matrix.getMatrixTable().size(); i++) {
        //     for (int j = 0; j <  matrix.getMatrixTable().get(i).size(); j++) {
        //         if(inputKey.equals((matrix.getMatrixTable().get(i).get(j).getKey())) ){
        //             System.out.println("FOUND KEY " +inputKey + " with value of "
        //                 + matrix.getMatrixTable().get(i).get(j).getValue());
        //             matrix.getMatrixTable().get(i).get(j).setValue(inputNewValue);
        //             didUpdate = true;
        //             break;
        //         }
        //     }
        // }
        // if(didUpdate == false){
        //     System.out.println("Update failed ");
        // }

        // //RESET COMMAND CODE
        // matrixTable.clear();
        // matrix = new Matrix(matrixTable);

        // //ADD NEW DATA CODE
        // String addNewDataChoice = Reader.readString("Enter choice ");
        // if(addNewDataChoice.toUpperCase() == "A"){
        //     int rowInput = Reader.readInt("Enter new row ");
        //     int colInput = Reader.readInt("Enter new col ");
            
        //     for (int i = 0; matrix.getRow() < rowInput; i++) {
        //         ArrayList<Cell> rowList = new ArrayList<Cell>();
        //         for (int j = 0; matrix.getCol() < colInput; j++) {
        //             int newRowIndex = matrix.getRow() + i;
        //             Cell newCell = new Cell(newRowIndex+","+j,Helper.generateAsciiChar(ASCII));
        //             rowList.add(newCell);
        //         }
        //         matrix.getMatrixTable().add(rowList);
        //     }
        // }else{
        //     int colInput = Reader.readInt("Enter col ");
        //     int rowIndex = Reader.readInt("Enter index where to insert ");
            
        //     try{
        //         for (int i = 0; i < colInput; i++) {
        //             int newColIndex = colInput + i;
        //             Cell cell = new Cell(rowIndex+","+newColIndex, Helper.generateAsciiChar(ASCII));
        //             matrix.getMatrixTable().get(rowIndex).add(cell);
        //         }
        //     }catch(IndexOutOfBoundsException e){
        //         System.out.println("Index not found ");
        //     }
        // }

        //SORT TABLE CODE
        String sortChoice = Reader.readString("Enter choice ");
       
        for (int i = 0; i < matrix.getMatrixTable().size(); i++) {
            if(sortChoice.equals("A")){
                Collections.sort(matrix.getMatrixTable().get(i));
            }else if(sortChoice.equals("D")){
                Collections.sort(matrix.getMatrixTable().get(i), Collections.reverseOrder());
            }
        }
        System.out.println("Successfully sorted");
        

        //PRINT TABLE CODE
        for (int i = 0; i < matrix.getMatrixTable().size(); i++) {
            matrix.getMatrixTable().get(i).stream().forEach((cell)->
                System.out.printf("{ "+cell.getKey() +" : (" + cell.getValue() + ") }")
            );
            System.out.println();
        }

        
    }
}
