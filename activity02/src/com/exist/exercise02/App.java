package src.com.exist.exercise02;

import src.com.exist.exercise02.matrix.controller.MatrixController;

public class App {
    public static void main(String[] args) {
        System.out.println("Exercise 2: Advance Java ");
        MatrixController matrixController = new MatrixController();
        
        matrixController.handleMenu();
        
    }
}
