public class Square {
    public Square(boolean dirty, boolean occupied){
        this.dirty = dirty;
        this.occupied = occupied;
        this.rep = "CU";
    }
    public String printSquare(){
        return rep;
    }
    public void setRep(){
        if(occupied && dirty)
            rep = "DO";
        else if(occupied){
            rep = "CO";
        }
        else if(dirty){
            rep = "DU";
        }
        else{
            rep = "CU";
        }
    }
    boolean dirty;
    boolean occupied;
    String rep;
}
