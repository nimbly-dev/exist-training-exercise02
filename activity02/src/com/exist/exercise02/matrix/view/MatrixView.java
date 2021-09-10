package src.com.exist.exercise02.matrix.view;

import src.com.exist.exercise02.matrix.utility.Reader;

public class MatrixView {

    public void printDurationAndTotalItems(int totalItems, long duration){
        System.out.println("Found " + totalItems + " items with execution speed of " + duration + " ms");
    }
    
    public void printDurationAndTotalFound(long duration, int totalItems){
        System.out.println("========================");
        System.out.println("Total substrings found: " + totalItems);
        System.out.println("Search took "+ duration +" ms");
    }

    public void printDuration(long duration){
        System.out.println("This process took " + duration + " ms");
    }

    public String getCommand(){
        return Reader.readString("Enter command: ");
    }

    public String getInputKey(){
        return Reader.readString("Enter input key: ");
    }

    public String getSearch(){
        return Reader.readString("Enter search substring ");
    }
    public int getRow(){
        return Reader.readInt("Enter row ");
    }

    public int getCol(){
        return Reader.readInt("Enter column ");
    }

    public int getInputRow(){
        return Reader.readInt("Enter how many row ");
    }

    public int getInputCol(){
        return Reader.readInt("Enter how many column ");
    }

    public String getPath(){
        return Reader.readString("Enter path ");
    }

    public int getRowIndex(){
        return Reader.readInt("Enter selected row index ");
    }

    public String enterChoice(){
        return Reader.readString("Enter choice ");
    }   

    public void viewCommands() {
        System.out.println("COMMANDS LIST: ");
        System.out.println("[V]: view the commands ");
        System.out.println("[A]: add new row command ");
        System.out.println("[SORT]: sort to ascending order ");
        System.out.println("[FILE]: open file management menu ");
        System.out.println("[S]: users input search character/characters, identify number of occurance in the table, and identify index of search string ");
        System.out.println("[E]: specify index to update ");
        System.out.println("[P]: print table ");
        System.out.println("[R]: specify new dimensions, new random chars ");
        System.out.println("[X]: exit the programm ");
    }

    public void printFileManagementCommands(){
        System.out.println("File Management Commands: ");
        System.out.println("[READ-TXT] - read txt file");
        System.out.println("[SAVE-TXT] - write txt file");
        System.out.println("[READ-JSON] - read json file");
        System.out.println("[SAVE-JSON] - write json file");
        System.out.println("[X] - exit menu");
    }
    public void printSortCommands(){
        System.out.println("Sort Commands: ");
        System.out.println("[A] - to sort in ascending order");
        System.out.println("[D] - to sort in descending order");
        System.out.println("[X] - exit menu");
    }

    public void printAddNewDataCommands(){
        System.out.println("Add New Data Commands: ");
        System.out.println("[A] - append new data to tail-end");
        System.out.println("[I] - insert new data to an row");
        System.out.println("[X] - exit menu");
    }
}
