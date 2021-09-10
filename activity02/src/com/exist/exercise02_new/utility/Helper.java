package src.com.exist.exercise02_new.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.com.exist.exercise02_new.model.Cell;
import src.com.exist.exercise02_new.model.Matrix;

public class Helper {


    //Sets sort changes to main matrix obj
    public static void saveMatrixValues(ArrayList<ArrayList<String>> concatValues, Matrix matrix){
        for (int i = 0; i < matrix.getMatrixTable().size(); i++) {
            for (int j = 0; j < matrix.getMatrixTable().get(i).size(); j++) {
                matrix.getMatrixTable().get(i).get(j).setValue(concatValues.get(i).get(j));
            }
        }
    }

    //Transfer Cell values to an list of list of values
    public static void getCellValuesToList(ArrayList<ArrayList<String>> concatValues, Matrix matrix){
        for (int i = 0; i < matrix.getMatrixTable().size(); i++) {
            ArrayList<String> rowList = new ArrayList<String>();
            for (int j = 0; j < matrix.getMatrixTable().get(i).size(); j++) {
                String value = matrix.getMatrixTable().get(i).get(j)
                                       .getValue();
                rowList.add(value);
            }
            concatValues.add(rowList);
        }
    }

    //Read File
    public static void readFile(String filePath, Matrix matrixTable) throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        Scanner myReader = new Scanner(br);
        ArrayList<Cell> rowList = new ArrayList<Cell>(); //Initialize Cell

        int rowIndex = 0;      
        while(myReader.hasNext()){
            String line = myReader.next();
            int colIndex = 0;                
            
            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(line);
            while(m.find()) {
                String key = rowIndex+","+colIndex;
                String value = m.group(1);
                rowList.add(new Cell(key,value));    
                ++colIndex;
            }
            
            if(myReader.hasNextLine())
                matrixTable.getMatrixTable().add(rowList);
                ++rowIndex;
                rowList = new ArrayList<Cell>();
        }
        myReader.close();
        
    }

    //Write File
    public static void writeFile(String filePath, Matrix matrixTable) throws FileNotFoundException, IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));

        for (int indexRow = 0; indexRow < matrixTable.getMatrixTable().size(); indexRow++) {
            for (int indexCol = 0; indexCol < matrixTable.getMatrixTable().get(indexRow).size(); indexCol++) {
                writer.write("{"+
                    matrixTable.getMatrixTable()
                                .get(indexRow).get(indexCol)
                                .getKey() +":(" +  
                                matrixTable.getMatrixTable()
                                .get(indexRow).get(indexCol)
                                .getValue() + ")}");
            }
            writer.newLine();
        }
        writer.close();
    }

    //Helper Functions
    public static int countChar(String str, String searchString){
        int count = 0;

        //Split the string
        String a[] = str.split("");

        for (int i = 0; i < a.length; i++) {
            if(searchString.equals(a[i])){
                count++;
            }
        }
        return count;
    }


    public static String generateAsciiChar(String asciiChar){
        //Source @https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 3) { // length of the random string.
            int index = (int) (rnd.nextFloat() * asciiChar.length());
            salt.append(asciiChar.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
