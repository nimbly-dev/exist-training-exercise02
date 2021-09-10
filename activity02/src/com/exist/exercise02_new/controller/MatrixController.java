package src.com.exist.exercise02_new.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import src.com.exist.exercise02_new.model.Cell;
import src.com.exist.exercise02_new.model.Matrix;
import src.com.exist.exercise02_new.utility.Helper;
import src.com.exist.exercise02_new.view.MatrixView;
import src.com.exist.exercise02_new.view.Text;

public class MatrixController {
    boolean isEnd;

    ArrayList<ArrayList<Cell>> matrixTable;
    MatrixView matrixView;

    Matrix matrix;
    public MatrixController(){
        this.isEnd = false;
        this.matrixView = new MatrixView();
        this.matrixTable = new ArrayList<ArrayList<Cell>>();
        this.matrix = new Matrix(matrixTable,true);
        try {
            Helper.readFile(Text.FILEPATH, this.matrix);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showMenu(){
        this.matrixView.viewCommands();
        do{
            String command = this.matrixView.commandChoice();
            switch (command.toUpperCase()) {
                case "P":
                    printTable();
                    break;
                case "S":
                    searchCommand();
                    break;
                case "U":
                    try {
                        updateCommand();
                    } catch (FileNotFoundException e2) {
                        System.out.println(Text.FILE_NOT_FOUND);
                    } catch (IOException e2) {
                        System.out.println(Text.INVALID_INPUT);
                    }
                    break;
                case "R":
                    try{
                        resetCommand();
                    }catch (FileNotFoundException e1) {
                        System.out.println(Text.FILE_NOT_FOUND);
                    } catch (IOException e1) {
                        System.out.println(Text.INVALID_INPUT);
                    }
                    break;
                case "A":
                    try {
                        addNewData();
                    } catch (IndexOutOfBoundsException e1) {
                        System.out.println(Text.FILE_NOT_FOUND);
                    } catch (FileNotFoundException e1) {
                        System.out.println(Text.FILE_NOT_FOUND);
                    } catch (IOException e1) {
                        System.out.println(Text.INVALID_INPUT);
                    }
                    break;
                case "SORT":
                    try {
                        sortTable();
                    } catch (FileNotFoundException e) {
                       System.out.println(Text.FILE_NOT_FOUND);
                    } catch (IOException e) {
                        System.out.println(Text.INVALID_INPUT);
                    }
                    break;
                case "V":
                    this.matrixView.viewCommands();
                    break;
                case "X":
                    System.out.println(Text.PROGRAMM_ENDING_MSSG);
                    this.isEnd = true;
                default:
                    System.out.println(Text.INVALID_INPUT);
                    break;
            }
        }while(this.isEnd == false);
    }

    public void printTable(){
        for (int indexRow = 0; indexRow < this.matrix.getMatrixTable().size(); indexRow++) {
            this.matrix.getMatrixTable().get(indexRow).stream().forEach((cell)->
                System.out.printf("{ "+cell.getKey() +" : (" + cell.getValue() + ") }")
            );
            System.out.println();
        }
    }   

    public void searchCommand(){
        int totalFound = 0;
        String searchValue = this.matrixView.getSearchInput();
        for (int indexRow = 0; indexRow < this.matrix.getMatrixTable().size(); indexRow++) {

            Optional<Cell> matchValue = this.matrix.getMatrixTable().get(indexRow)
                                                                    .stream()
                                                                    .filter(c->c.getValue().contains(searchValue))
                                                                    .findFirst();
            if(matchValue.isPresent()){
                int countInstances = Helper.countChar(matchValue.get().getValue(), searchValue);
                System.out.println("Found " + searchValue + " at "+matchValue.get().getKey()+" with "+countInstances+" instances");
                ++totalFound;
            }
        }
        System.out.println("Total Comparisons found: "+totalFound);
    }

    public void updateCommand() throws FileNotFoundException, IOException{
        String inputKey = matrixView.getInputKey();
        String inputNewValue = matrixView.getNewValue();
        boolean didUpdate = false;
        for (int indexRow = 0; indexRow < this.matrix.getMatrixTable().size(); indexRow++) {                                                                 

            for (int indexCol = 0; indexCol <  this.matrix.getMatrixTable().get(indexRow).size(); indexCol++) {
                boolean foundKey = this.matrix.getMatrixTable().get(indexRow).get(indexCol)
                                                                             .getKey()
                                                                             .equals(inputKey);
                if(foundKey == true){
                    System.out.println("FOUND KEY " +inputKey + " with value of "
                        + this.matrix.getMatrixTable().get(indexRow).get(indexCol).getValue());
                    this.matrix.getMatrixTable().get(indexRow).get(indexCol).setValue(inputNewValue);
                    didUpdate = true;
                    break;
                }
            }
        }
        if(didUpdate == false)
            System.out.println(Text.UPDATE_FAILED);
        else
            Helper.writeFile(Text.FILEPATH, this.matrix);
        
    }

    public void resetCommand() throws FileNotFoundException, IOException{
        matrixTable.clear();
        this.matrix = new Matrix(this.matrixTable, false);
        Helper.writeFile(Text.FILEPATH, this.matrix);
    }

    public void addNewData() throws IndexOutOfBoundsException, FileNotFoundException, IOException{
        String addNewDataChoice = this.matrixView.commandChoice();
        this.matrixView.printAddNewDataCommands();
        if(addNewDataChoice.equals("A")){
            int rowInput = this.matrixView.getRow();
            int colInput = this.matrixView.getCol();
            
            for (int indexRow = 0; indexRow < rowInput; indexRow++) {
                ArrayList<Cell> rowList = new ArrayList<Cell>();
                for (int indexCol = 0; indexCol < colInput; indexCol++) {
                    int newRowIndex = this.matrix.getRow() + indexRow;
                    Cell newCell = new Cell(newRowIndex+","+indexCol,Helper.generateAsciiChar(Text.ASCII));
                    rowList.add(newCell);
                }
                this.matrix.getMatrixTable().add(rowList);
            }
        }else if(addNewDataChoice.equals("I")){
            int colInput = this.matrixView.getCol();
            int rowIndex = this.matrixView.getInputIndexRow();
            
            for (int indexCol = 0; indexCol < colInput; indexCol++) {
                int newColIndex = colInput + indexCol;
                Cell cell = new Cell(this.matrix.getRow()+rowIndex+","+newColIndex, Helper.generateAsciiChar(Text.ASCII));
                this.matrix.getMatrixTable().get(rowIndex).add(cell);
            }
        }
        Helper.writeFile(Text.FILEPATH, this.matrix);
    }

    public void sortTable() throws FileNotFoundException, IOException{
        boolean exitSortTableMenu = false;

        ArrayList<ArrayList<String>> concatValues = new ArrayList<ArrayList<String>>();
        Helper.getCellValuesToList(concatValues, this.matrix);

        this.matrixView.printSortCommands();
        do {
            String sortChoice = this.matrixView.commandChoice();
            switch(sortChoice.toUpperCase()){
                case "A":
                    for (int i = 0; i < matrix.getMatrixTable().size(); i++) {
                        Collections.sort(concatValues.get(i));
                    }
                    Helper.saveMatrixValues(concatValues, this.matrix);
                    System.out.println("Now sorted ascendingly ");
                    break;
                case "D":
                    for (int i = 0; i < matrix.getMatrixTable().size(); i++) {
                        Collections.sort(concatValues.get(i), Collections.reverseOrder());
                    }
                    Helper.saveMatrixValues(concatValues, this.matrix);
                    System.out.println("Now sorted descendingly ");
                    break;
                case "X":
                    exitSortTableMenu = true;
                    System.out.println("Succesfully exited sort table menu");
                default:
                    System.out.println("Error, pleae try again");
                    this.matrixView.printSortCommands();
            }
        } while (exitSortTableMenu == true);

        Helper.writeFile(Text.FILEPATH, this.matrix);
    }

}
