package src.com.exist.exercise02.matrix.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import src.com.exist.exercise02.matrix.model.Cell;
import src.com.exist.exercise02.matrix.model.Matrix;
import src.com.exist.exercise02.matrix.utility.Helper;
import src.com.exist.exercise02.matrix.utility.Reader;
import src.com.exist.exercise02.matrix.view.MatrixView;
import src.com.exist.exercise02.matrix.view.Text;

public class MatrixController {
    ArrayList<HashMap<String, Cell>> matrixTable;
    MatrixView matrixView;
    Matrix matrixModel;

    boolean isEnd;

    public MatrixController(){
        this.matrixView = new MatrixView();
        this.matrixTable = new ArrayList<HashMap<String, Cell>>(); 
        System.out.println(Text.WELCOMING_MSSG);
        String choice = matrixView.enterChoice();
        
        if(choice.contentEquals("R")){
            try {
                this.matrixModel = new Matrix(matrixTable);
                this.readTxtFile();
            } catch (FileNotFoundException e) {
                System.out.println(Text.FILE_NOT_FOUND);
            } catch (IOException e) {
                System.out.println(Text.FILE_NOT_FOUND);
            }
        }else{
            this.matrixModel = new Matrix(matrixTable, matrixView.getRow(), matrixView.getCol());
        }
        
    }

    public void handleMenu(){
        do{
            String command = matrixView.getCommand();
            switch(command.toUpperCase()){
                case "S":
                    this.searchCommand(this.matrixModel);
                    break;
                case "E":
                    try{
                        this.updateCommand(this.matrixModel);
                    }
                    catch(ArrayIndexOutOfBoundsException e){
                        System.out.println(Text.INDEX_NOT_FOUND);
                    }
                    catch(IndexOutOfBoundsException e){
                        System.out.println(Text.INDEX_NOT_FOUND);
                    }
                    catch(NumberFormatException e){
                        System.out.println(Text.INVALID_INPUT);
                    }
                    break;
                case "A":
                    this.addNewDataMenu(this.matrixModel);
                    break;
                case "SORT":
                    this.sortTableMenu(this.matrixModel);
                    break;
                case "FILE":
                    this.readAndWriteMenu();
                    break;
                case "P":
                    this.printTable(this.matrixModel);
                    break;
                case "R":
                    this.resetCommand(this.matrixModel);
                    break;
                case "V":
                    matrixView.viewCommands();
                    break;
                case "X":
                    System.out.println(Text.PROGRAMM_ENDING_MSSG);
                    this.isEnd = true;
                    break;
                default:
                    System.out.println(Text.COMMAND_NOT_FOUND);
                    matrixView.viewCommands();
                    break;
            }
        }while(isEnd == false);
    }

    public void addNewDataMenu(Matrix matrix){
        boolean isAddNewDataMenu = false;
        System.out.println(Text.ON_ADD_NEW_DATA);
        matrixView.printAddNewDataCommands();

        do{
            String command = matrixView.enterChoice();
            switch (command.toUpperCase()) {
                case "A":
                    this.addNewRow(matrix);
                    break;
                case "I":
                    try {
                        this.insertNewData(matrix);
                    } catch (ArrayIndexOutOfBoundsException e) {
                       System.out.println(Text.INDEX_NOT_FOUND);
                    } catch(IndexOutOfBoundsException e){
                        System.out.println(Text.INDEX_NOT_FOUND);
                    }
                    break;
                case "X":
                    System.out.println(Text.ON_EXIT_FILE_MANAGEMENT);
                    isAddNewDataMenu = true;
                    break;
                default:
                    System.out.println(Text.COMMAND_NOT_FOUND);
                    matrixView.printAddNewDataCommands();
                    break;
            }
        }while(isAddNewDataMenu == false);
        
    }

    public void readAndWriteMenu(){
        boolean isReadAndWriteMenuEnd = false;
        this.matrixModel.getMatrixTable().clear();//Clear current matrix
        System.out.println(Text.ON_FILE_MANAGEMENT);
        matrixView.printFileManagementCommands();
    
        do{
            String command = Reader.readString("Enter choice: ");
            switch(command.toUpperCase()){
                case "READ-TXT":
                    try {
                        this.readTxtFile();
                    } catch (FileNotFoundException e) {
                        System.out.println(Text.FILE_NOT_FOUND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "SAVE-TXT":
                    try {
                        this.createTxtFile(this.matrixModel);
                    } catch (FileNotFoundException e) {
                        System.out.println(Text.FILE_NOT_FOUND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "READ-JSON":
                    try {
                        this.readJsonFile(this.matrixModel);
                    } catch (FileNotFoundException e) {
                        System.out.println(Text.FILE_NOT_FOUND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "SAVE-JSON":
                    try {
                        this.createJsonFile(this.matrixModel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "X":
                    System.out.println(Text.ON_EXIT_FILE_MANAGEMENT);
                    isReadAndWriteMenuEnd = true;
                    break;
                default:
                    System.out.println(Text.COMMAND_NOT_FOUND);
                    matrixView.printFileManagementCommands();
                    break;
            }
        }while(isReadAndWriteMenuEnd == false);
    }

    public void readTxtFile() throws FileNotFoundException, IOException{
        String filePath = Reader.readString("Enter path: ");
        int rowIndex = 0;

        // matrix.getMatrixTable().clear();//Clear current matrix
        HashMap<String,Cell> rowMap = new HashMap<>(); //Initialize Cell

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        Scanner myReader = new Scanner(br);
        
        while(myReader.hasNext()){
            String line = myReader.next();
            
            String[] data = line.split(",");

            for (int colIndex = 0; colIndex < data.length; colIndex++) {
                Cell newCell = new Cell( data[colIndex]);
                rowMap.put(rowIndex+","+colIndex, newCell);
            }
            
            if(myReader.hasNextLine())
                this.matrixModel.getMatrixTable().add(rowMap);
                ++rowIndex;
                rowMap = new HashMap<String,Cell>();
        }
        myReader.close();
    }

    public void createTxtFile(Matrix matrix) throws FileNotFoundException, IOException{
        String filePath = matrixView.getPath();

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (int i = 0; i < matrixTable.size(); i++) {

            // using iterators
            Iterator<Map.Entry<String, Cell>> itr = matrix
                                                            .getMatrixTable()
                                                            .get(i).entrySet().iterator();
            while(itr.hasNext())
            {
                Map.Entry<String, Cell> entry = itr.next();
                Cell cell = new Cell(entry.getValue().getContent());
                writer.append(cell.getContent());
                if(!itr.hasNext()) //If on final iteration, break the loop
                    break;
                else
                    writer.append(",");
                
            }
            writer.append("\n");
        }
        writer.close();
    }

    public void readJsonFile(Matrix matrix)throws FileNotFoundException, IOException {
        String filePath = matrixView.getPath();
        matrix.getMatrixTable().clear();//Clear current matrix
    
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        JsonArray array = JsonParser.parseReader(br).getAsJsonArray();

        for (int indexRow = 0; indexRow < array.size(); indexRow++) {
            HashMap<String,Cell> rowMap = new HashMap<>(); //Initialize Cell
            
            for (int indexCol = 0; indexCol < array.get(indexRow).getAsJsonArray().size(); indexCol++) {
                Cell cell = new Cell(array
                                        .get(indexRow)
                                        .getAsJsonArray()
                                        .get(indexCol)
                                        .getAsJsonObject()
                                        .get(indexRow+","+indexCol)
                                        .getAsString());
                rowMap.put(indexRow+","+indexCol, cell);
            }
            matrix.getMatrixTable().add(rowMap);
        }            

        br.close();
    }

    public void createJsonFile(Matrix matrix)throws IOException {
            String filePath = matrixView.getPath();
            StringWriter out = new StringWriter();
            JsonWriter writer = new JsonWriter(out);
           
            writer.beginArray();
            //Write matrix to file
            long startTime = System.nanoTime();
            for (int indexRow = 0; indexRow < matrix.getMatrixTable().size(); indexRow++) {
                Iterator<Map.Entry<String, Cell>> itr = matrix
                                                                .getMatrixTable()
                                                                .get(indexRow)
                                                                .entrySet()
                                                                .iterator();
                writer.beginArray();
                while(itr.hasNext()){
                    Map.Entry<String, Cell> entry = itr.next();
                    writer.beginObject();
                    writer.name(entry.getKey()).value(entry.getValue().getContent());
                    writer.endObject();
                }
                writer.endArray();
            }
            writer.endArray();
            writer.close();
         
            //Code block for saving the file and pretty print it
            FileWriter file = new FileWriter(filePath);
            Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
                JsonElement je = JsonParser.parseString(out.toString());
            gson.toJson(je,file);

            file.close();
           
            long stopTime = System.nanoTime();
            long duration = (stopTime - startTime) / 1000000;
            System.out.println(Text.SUCCESSFUL_WROTE_FILE_TXT);
            matrixView.printDuration(duration);
    }

    public void sortTableMenu(Matrix matrix) {
        boolean sortFunctionEnd = false;

        matrixView.printSortCommands();
        String userChoice = matrixView.enterChoice();

        do{ //Switch case for sort type input
            switch(userChoice.toUpperCase()){
                case "A":
                    long startTime = System.nanoTime(); 
                    Helper.sortToAscending(matrix);
                    long stopTime = System.nanoTime(); 
                    long duration = (stopTime - startTime) / 1000000;
                    matrixView.printDuration(duration);
                    sortFunctionEnd = true;
                    break;
                case "D":
                    long startTime2 = System.nanoTime();
                    Helper.sortToDescending(matrix);
                    long stopTime2 = System.nanoTime();
                    long duration2 = (stopTime2 - startTime2) / 1000000;
                    matrixView.printDuration(duration2);
                    sortFunctionEnd = true;
                    break;
                default:
                    System.out.println(Text.COMMAND_NOT_FOUND);
                    matrixView.printSortCommands();
                    userChoice = matrixView.enterChoice();
                    break;
            }
        }while(sortFunctionEnd == false);
    }

    public void insertNewData(Matrix matrix) throws ArrayIndexOutOfBoundsException,IndexOutOfBoundsException {
        System.out.println(Text.INFO_DEFAULT_VALUE_INSERT_NEW_DATA);
        int colInput = matrixView.getCol();
        int inputRowIndex = matrixView.getRowIndex();

        if(colInput <= 0){
            System.out.println("Used default values ");
            colInput = matrix.getCol();
        }

        for (int colIndex = 0; colIndex < colInput; colIndex++) {
            Cell newCell = new Cell(Helper.generateAsciiChar(Text.ASCII));
            int newColIndex = colInput + colIndex; //To generate new colIndex for our newly created Cells
            matrix.getMatrixTable().get(inputRowIndex).put(inputRowIndex+","+newColIndex, newCell);
        }
    }

    public void addNewRow(Matrix matrix) { // Add new row
        System.out.println("Defaulted value is the recent row and col input");
        int rowInput = matrixView.getInputRow();
        int colInput = matrixView.getInputCol();

        if(rowInput <= 0 || colInput <= 0){
            System.out.println("Used default values ");
            rowInput = matrix.getRow();
            colInput = matrix.getCol();
        }

        for (int indexRow = 0; indexRow < rowInput; indexRow++) {
            HashMap<String,Cell> rowMap = new HashMap<>(); //Initialize Cell

            for (int indexCol = 0; indexCol < colInput; indexCol++) {
                Cell newCell = new Cell(Helper.generateAsciiChar(Text.ASCII));
                int newRowIndex = matrix.getRow() + indexRow;
                rowMap.put(newRowIndex+","+indexCol, newCell);
            }
            matrix.getMatrixTable().add(rowMap);
        }
    }

    public void resetCommand(Matrix matrix) {
        int row = matrixView.getRow();
        int column = matrixView.getCol();

        matrix.getMatrixTable().clear();
    
        for (int indexRow = 0; indexRow < row; indexRow++) {
            HashMap<String,Cell> cell = new HashMap<>(); //Initialize Cell
            for (int indexCol = 0; indexCol < column; indexCol++) {
                Cell newCell = new Cell(Helper.generateAsciiChar(Text.ASCII));
                cell.put(indexRow+","+indexCol, newCell);
            }
            matrix.getMatrixTable().add(cell);
        }
    }

    public void updateCommand(Matrix matrix) throws ArrayIndexOutOfBoundsException,IndexOutOfBoundsException,NumberFormatException {
        
        String search = matrixView.getInputKey();
        String[] indexArr = search.split(",");

        int specifyRow = Integer.parseInt(indexArr[0]);
        int specifyColumn = Integer.parseInt(indexArr[1]);
        String inputKey = specifyRow+","+specifyColumn;
    
        for (int indexRow = 0; indexRow <  matrix.getMatrixTable().size();indexRow++) {
            Iterator<Map.Entry<String, Cell>> itr =  matrix
                                                        .getMatrixTable()
                                                        .get(indexRow)
                                                        .entrySet().iterator();
            while(itr.hasNext()){
                Entry<String, Cell> entry = itr.next();
                if(entry.getKey().contains(inputKey)){ //If key is found
                    System.out.println("FOUND Index ( "+specifyRow+","+specifyColumn+" ) with value " + entry.getValue().getContent());
                    String newValue = Reader.readString("Enter new value ");
                    Cell newCell = new Cell(newValue);
                    matrix.getMatrixTable().get(indexRow).put(entry.getKey(), newCell);
                    break; //Break the loop after finding the key to save processing time
                }
            }
        }
            
    }

    public void searchCommand(Matrix matrix) {
        //SEARCH 
        String searchString = matrixView.getSearch();
        int totalFound = 0;

        long startTime = System.nanoTime();
        for (int indexRow = 0; indexRow < matrix.getMatrixTable().size(); indexRow++) {
            Iterator<Map.Entry<String, Cell>> itr = matrix
                                                        .getMatrixTable()
                                                        .get(indexRow)
                                                        .entrySet()
                                                        .iterator();
            while(itr.hasNext()){
                Entry<String, Cell> entry = itr.next();
                if(entry.getValue().getContent().contains(searchString)){ //Compare to search string
                    int count = Helper.countChar(entry.getValue().getContent(), searchString);
                    System.out.println("Found " + searchString + " on cell ("+ entry.getKey() + ") with "+ count + " of instances");
                    ++totalFound;
                }    
            }
        }
        long stopTime = System.nanoTime();
        long duration =  (stopTime - startTime) / 1000000; 
        matrixView.printDurationAndTotalFound(duration, totalFound);
    }

    public void printTable(Matrix matrix) {
        int totalItems = 0;

        long startTime = System.nanoTime();
        //PRINT MATRIX 
        for (int indexRow = 0; indexRow < matrix.getMatrixTable().size(); indexRow++) {
            Iterator<Map.Entry<String, Cell>> itr =  matrix
                                                         .getMatrixTable()
                                                         .get(indexRow)
                                                         .entrySet().iterator();
            while(itr.hasNext()){ //Iterate Cols of HashMaps
                Map.Entry<String, Cell> entry = itr.next();
                System.out.printf(" " + entry.getValue().getContent());
                ++totalItems;
            }
            System.out.println();
        }
        long stopTime = System.nanoTime();
        long duration = (stopTime - startTime) / 1000000;
        
        matrixView.printDurationAndTotalItems(totalItems, duration);
    }
}