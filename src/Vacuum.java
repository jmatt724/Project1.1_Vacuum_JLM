public class Vacuum {
    //vacuum object for agent.
    public Vacuum(int x, int y, boolean status){
        this.x = x;
        this.y = y;
        this.status = status;
    }
    public boolean canMoveLeft(){
        return x > 0;
    }
    public boolean canMoveUp(){
        return y > 0;
    }
    //methods for moving the vacuum
    public void moveLeft(Square[][] board){
        if(x > 0){
            board[x][y].occupied = false;
            x = x-1;
            board[x][y].occupied = true;
            System.out.println("I moved left!");
        }
    }
    public void moveRight(Square[][] board){
        if(x < 3){
            board[x][y].occupied = false;
            x = x+1;
            board[x][y].occupied = true;
            System.out.println("I moved right!");
        }
    }
    public void moveDown(Square[][] board){
        if(y < 3){
            board[x][y].occupied = false;
            y = y+1;
            board[x][y].occupied = true;
            System.out.println("I moved down!");
        }
    }
    public void moveUp(Square[][] board){
        if(y > 0){
            board[x][y].occupied = false;
            y = y-1;
            board[x][y].occupied = true;
            System.out.println("I moved up!");
        }
    }
    int x;
    int y;
    boolean status;

}
