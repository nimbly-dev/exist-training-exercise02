package src.com.exist.exercise02_new.view;

import src.com.exist.exercise02_new.utility.Reader;

public class MatrixView{

    public String commandChoice(){
        return Reader.readString("Enter choice ");
    }

    public String getSearchInput(){
        return Reader.readString("Enter search value ");
    }

    public String getInputKey(){
        return Reader.readString("Enter input key ");
    }

    public String getNewValue(){
        return Reader.readString("Enter new value ");
    }

    public int getRow(){
        return Reader.readInt("Enter row ");
    }

    public int getInputIndexRow(){
        return Reader.readInt("Enter index where to insert ");
    }

    public int getCol(){
        return Reader.readInt("Enter col ");
    }

    public void viewCommands() {
        System.out.println("COMMANDS LIST: ");
        System.out.println("[V]: view the commands ");
        System.out.println("[A]: add new row command ");
        System.out.println("[SORT]: sort to ascending order ");
        System.out.println("[S]: users input search character/characters, identify number of occurance in the table, and identify index of search string ");
        System.out.println("[U]: specify index to update ");
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
        System.out.println("Sort Menu Commands: ");
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
