package src.com.exist.exercise02.matrix.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import src.com.exist.exercise02.matrix.model.Cell;
import src.com.exist.exercise02.matrix.model.Matrix;


public class Helper {
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

    public static void sortToAscending(Matrix matrix){
        for (int indexRow = 0; indexRow < matrix.getMatrixTable().size(); indexRow++) {
            Iterator<Map.Entry<String, Cell>> itr = matrix
                                                            .getMatrixTable()
                                                            .get(indexRow).entrySet()
                                                            .iterator();

            List<Cell> sorted = new ArrayList<>(matrix.getMatrixTable().get(indexRow).values()); //Sorted Map
            Collections.sort(sorted);

            for(Cell element: sorted){ 
                
                while(itr.hasNext()){
                    Map.Entry<String, Cell> entry = itr.next();    
                    matrix
                        .getMatrixTable()
                        .get(indexRow)
                        .replace(entry.getKey(), element);   
                    break;  
                }
            }
            
        }
    }
    
    public static void sortToDescending(Matrix matrix){
        for (int indexRow = 0; indexRow < matrix.getMatrixTable().size(); indexRow++) {
            Iterator<Map.Entry<String, Cell>> itr = matrix
                                                            .getMatrixTable()
                                                            .get(indexRow).entrySet()
                                                            .iterator();

            List<Cell> sorted = new ArrayList<>(matrix.getMatrixTable().get(indexRow).values()); //Sorted Map
            Collections.sort(sorted, Collections.reverseOrder());

            for(Cell element: sorted){ 
                
                while(itr.hasNext()){
                    Map.Entry<String, Cell> entry = itr.next();    
                    matrix
                        .getMatrixTable()
                        .get(indexRow)
                        .replace(entry.getKey(), element);   
                    break;  
                }
            }
            
        }
    }
}
