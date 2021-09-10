package src.com.exist.exercise02_new;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.com.exist.exercise02_new.model.Cell;

public class Testing1 {
    public static void main(String[] args) {

        ArrayList<ArrayList<Cell>> matrixTable = new ArrayList<ArrayList<Cell>>();

        ArrayList<Cell> firstRow = new ArrayList<Cell>();
        firstRow.add(new Cell("0,0", "101"));
        firstRow.add(new Cell("0,1", "102"));
        firstRow.add(new Cell("0,2", "103"));

        ArrayList<Cell> secondRow = new ArrayList<Cell>();
        secondRow.add(new Cell("1,0", "204"));
        secondRow.add(new Cell("1,1", "205"));
        secondRow.add(new Cell("1,2", "206"));


        ArrayList<Cell> thirdRow = new ArrayList<Cell>();
        thirdRow.add(new Cell("2,0", "304"));
        thirdRow.add(new Cell("2,1", "305"));
        thirdRow.add(new Cell("2,2", "306"));

        matrixTable.add(firstRow);
        matrixTable.add(secondRow);
        matrixTable.add(thirdRow);

        
        try{//WRITE FILE
            BufferedWriter writer = new BufferedWriter(new FileWriter("filename.txt", true));

            for (int i = 0; i < matrixTable.size(); i++) {
                matrixTable.get(i).stream().forEach((cell)->{
                    try {
                        writer.append("{"+cell.getKey() +":(" + cell.getValue() + ")}");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.append("\n");
            }

            writer.close();
            
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ArrayList<ArrayList<Cell>> readedTable = new ArrayList<ArrayList<Cell>>();
        int rowIndex = 0;      
        // HashMap<String,String> rowMap = new HashMap<>(); //Initialize Cell
        try{ //READ FILE

            BufferedReader br = new BufferedReader(new FileReader("filename.txt"));
            Scanner myReader = new Scanner(br);
            ArrayList<Cell> rowList = new ArrayList<Cell>(); //Initialize Cell

            while(myReader.hasNext()){
                String line = myReader.next();
                int colIndex = 0;                
                
                Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(line);
                while(m.find()) {
                    String key = rowIndex+","+colIndex;
                    String value = m.group(1);
                    System.out.println("KEY: "+rowIndex+","+colIndex);
                    System.out.println("VALUE: "+m.group(1));
                    rowList.add(new Cell(key,value));    
                    ++colIndex;
                }
                System.out.println(rowList.get(0).getValue());
                if(myReader.hasNextLine())
                    readedTable.add(rowList);
                    ++rowIndex;
                    rowList = new ArrayList<Cell>();
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // System.out.println(readedTable);

        for (int i = 0; i < readedTable.size(); i++) {
            // System.out.println("INSIDE FOR");
            readedTable.get(i).stream().forEach((cell)->{
                System.out.printf("{ "+cell.getKey() +" : (" + cell.getValue() + ") }");
            });
            System.out.println();
        }

        // //PRINT MATRIX 
        // for (int indexRow = 0; indexRow < readedTable.size(); indexRow++) {
        //     Iterator<Map.Entry<String, String>> itr =  readedTable
        //                                                             .get(indexRow)
        //                                                             .entrySet().iterator();
        //     while(itr.hasNext()){ //Iterate Cols of HashMaps
        //         Map.Entry<String, String> entry = itr.next();
        //         System.out.printf(entry.getValue() + " " + " key: " + entry.getKey() + " ");
        //     }
        //     System.out.println();
        // }
        // String currentDir = System.getProperty("user.dir");
        // System.out.println("Current dir using System:" + currentDir);
    }   
}
