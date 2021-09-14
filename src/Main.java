//Justin Mattix
//Vacuum World

import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Square[][] board = new Square[4][4];
        Random rand = new Random();
        int x = rand.nextInt(4);
        int y = rand.nextInt(4);
        Vacuum robot = new Vacuum(x, y, false);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boolean dirty = false;
                int num = rand.nextInt(2);
                if(num == 1){
                    dirty = true;
                }
                board[i][j] = new Square(dirty, false);
                board[i][j].setRep();
            }
        }
        board[robot.x][robot.y].occupied = true;
        board[robot.x][robot.y].setRep();
        printWorld(board, robot);
        cleanBoard(board, robot);
    }
    //updates the board of squares after each move
    private static void updateBoard(Square[][] board){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                board[i][j].setRep();
            }
        }
    }
    //loops for parsing the 2D array and cleaning squares
    //starts by moving to top right corner
    //goes up and down columns until reaching the opposite corner
    //used sleep to better visualize actions
    private static void cleanBoard(Square[][] board, Vacuum robot) throws InterruptedException {
        System.out.println("Finding the top right corner!");
        while(robot.x != 0 || robot.y != 0) {
            if(robot.canMoveLeft()){
                robot.moveLeft(board);
                Thread.sleep(1000);
                updateBoard(board);
                printWorld(board, robot);
            }
            if(robot.canMoveUp()){
                robot.moveUp(board);
                Thread.sleep(1000);
                updateBoard(board);
                printWorld(board, robot);
            }
        }
        System.out.println("Start cleaning!");
        checkSqaure(board, robot);
        cleanSquare(board, robot);
        Thread.sleep(1000);
        while(!checkAllClean(board)){
            if(robot.y == 0){
                for(int i = 0; i < 4; i++){
                    robot.moveDown(board);
                    updateBoard(board);
                    Thread.sleep(1000);
                    printWorld(board, robot);
                    checkSqaure(board, robot);
                    cleanSquare(board, robot);
                }

            }
            else{
                for(int i = 0; i < 4; i++){
                    robot.moveUp(board);
                    updateBoard(board);
                    Thread.sleep(1000);
                    printWorld(board, robot);
                    checkSqaure(board, robot);
                    cleanSquare(board, robot);
                }
            }
            robot.moveRight(board);
            updateBoard(board);
            Thread.sleep(1000);
            printWorld(board, robot);
            checkSqaure(board, robot);
            cleanSquare(board, robot);
        }
        System.out.println("All Finished!!!");
    }
    //checks square for dirt and turns vacuum on if dirty
    private static void checkSqaure(Square[][] board, Vacuum robot){
        if(board[robot.x][robot.y].dirty){
            System.out.println("Turning on vacuum");
            robot.status = true;
        }
        else{
            robot.status = false;
            System.out.println("Turning off vacuum!");
        }
    }
    //cleans square if dirty and vacuum is on
    private static void cleanSquare(Square[][] board, Vacuum robot) {
        if(board[robot.x][robot.y].occupied && board[robot.x][robot.y].dirty
        && robot.status){
            board[robot.x][robot.y].dirty = false;
            updateBoard(board);
            printWorld(board, robot);
        }
    }
    //checks that all sqaures on the board are clean
    private static boolean checkAllClean(Square[][] board){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(board[i][j].dirty)
                    return false;
            }
        }
        return true;
    }
    //prints the board
    private static void printWorld(Square[][] board, Vacuum robot) {
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(board[j][i].printSquare() + " ");
            }
            System.out.println();
        }
        System.out.println("Vacuum Location: " + robot.x + " " + robot.y);
        System.out.println("Robot status: " + robot.status);
        System.out.println();
    }
}
