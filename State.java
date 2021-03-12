/**
 * Created by Maurício on 2/13/2017.
 *
 * this class is for storing information about a given state of the board at a given moment.
 * MyStack stores objects of this class. variable and methods names are self explanatory.
 */
public class State {
    private State previousState=null;
    private int[][]board=new int[8][8];
    /*used to know keep track of paths already tried that had no success. later if element is 1
    it means move has already been tried, and thus becomes invalid from that state*/
    private int[][][] movesMade=new int[8][8][8];
    private int sequenceNumber;//counts moves made
    private int currentPositionX;
    private int currentPositionY;


    public State(int[][] board, int sequenceNumber, int currentPositionX, int currentPositionY) {//constructor
        this.board = board;
        for(int i=0;i<8;i++){//initializing all elements as 0
            for(int j=0;j<8;j++){
                for(int k=0;k<8;k++){
                    movesMade[i][j][k]=0;
                }
            }
        }
        this.sequenceNumber = sequenceNumber;
        this.currentPositionX = currentPositionX;
        this.currentPositionY = currentPositionY;
    }

    //getters and setters
    public State getPreviousState() {
        return previousState;
    }
    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public int[][] getBoard(){
        return board;
    }
    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][][] getMovesMade() {
        return movesMade;
    }

    public void setMovesMade(int[][][] movesMade) {
        this.movesMade = movesMade;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getCurrentPositionX() {
        return currentPositionX;
    }

    public void setCurrentPositionX(int currentPositionX) {
        this.currentPositionX = currentPositionX;
    }

    public int getCurrentPositionY() {
        return currentPositionY;
    }

    public void setCurrentPositionY(int currentPositionY) {
        this.currentPositionY = currentPositionY;
    }
    //alternative setter for movesMade, and the one actually used.
    public void altSetMovesMade(int x,int y, int z, int newVal){
        this.movesMade[x][y][z]=newVal;
    }

}
