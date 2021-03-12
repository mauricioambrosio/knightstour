import java.util.Arrays;
/**
 * Created by Maurício on 2/12/2017.
 *
 * this class is where the magic happens. this class is the tour itself. it contains the board
 * and methods for the knight to move in each direction of the eight directions which are later
 * used in the methods for exhaustive moving (move()) and for warnsdoff moving (warnsdoffMove()).
 *
 * the system used for the moves names is as follows:
 * N: north; S: south; E: east; W: west
 *
 * each of the 8 moves is a combination of 2 moves. the move that comes first is 2 steps move and
 * the second is the 1 step move. ex: NE: moves 2 steps north and 1 step east; WS: moves 2 steps
 * west and 1 step south.
 *
 * variables and methods names are self explanatory.
 *
 */
public class Tour {
    String []moves={"NE","NW","SE","SW","EN","ES","WN","WS"};
    private int [][]board=new int[8][8];
    private int currentPositionX;
    private int currentPositionY;
    private int sequenceNumber=0;
    private int steps=0;//counts actual number of steps taken, including wrong ones, but not including the backtracking


    //this stack will store every previous states and the current state of the board
    private MyStack states=new MyStack();


    public Tour() {//constructor used
        initializeBoard(this.board);
        states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
    }
    public Tour(int startingPositionX, int startingPositionY){//alternative constructor
        initializeBoard(this.board);
        board[startingPositionX][startingPositionY]=sequenceNumber;
        sequenceNumber++;//sequenceNumber becomes 1 after 0 is put in the starting position
        this.currentPositionX=startingPositionX;
        this.currentPositionY=startingPositionY;
        //adds the first state to the stack.
        states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
    }
    //getters and setters start here
    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
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

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setBoardCell(int currentPositionX, int currentPositionY,int sequenceNumber){//setter for a single
        board[currentPositionX][currentPositionY]=sequenceNumber;                           //board sell. this is
    }                                                                                       //the one used
    //getters and setters end here


    /*
    checks if a move makes the knight go to a position inside the board and if the
    knight has not gone to that position yet. two letter system for the kinds of moves
    is explained at beginning of the class.
    */
    public boolean moveAvailable(String move, int currentPositionX, int currentPositionY){
        if(move.equals("NE")){
           if(currentPositionX>=7){
               return false;
           }
           if(currentPositionY<=1){
               return false;
           }
           if(board[currentPositionX+1][currentPositionY-2]!=-1){
               return false;
           }
           return true;
       }else if(move.equals("NW")){
           if(currentPositionX<=0){
               return false;
           }
           if(currentPositionY<=1){
               return false;
           }
           if(board[currentPositionX-1][currentPositionY-2]!=-1){
               return false;
           }

           return true;
       }else if(move.equals("SE")){
           if(currentPositionX>=7){
               return false;
           }
           if(currentPositionY>=6){
               return false;
           }
           if(board[currentPositionX+1][currentPositionY+2]!=-1){
               return false;
           }
           return true;
       }else if(move.equals("SW")){

           if(currentPositionX<=0){
               return false;
           }
           if(currentPositionY>=6){
               return false;
           }
           if(board[currentPositionX-1][currentPositionY+2]!=-1){
               return false;
           }
           return true;

       }else if(move.equals("EN")){
           if(currentPositionX>=6){
               return false;
           }
           if(currentPositionY<=0){
               return false;
           }
           if(board[currentPositionX+2][currentPositionY-1]!=-1){
               return false;
           }

           return true;
       }else if(move.equals("ES")){
           if(currentPositionX>=6){
               return false;
           }
           if(currentPositionY>=7){
               return false;
           }
           if(board[currentPositionX+2][currentPositionY+1]!=-1){
               return false;
           }

           return true;
       }else if(move.equals("WN")){

           if(currentPositionX<=1){
               return false;
           }
           if(currentPositionY<=0){
               return false;
           }
           if(board[currentPositionX-2][currentPositionY-1]!=-1){
               return false;
           }
           return true;
       }else if(move.equals("WS")){

           if(currentPositionX<=1){
               return false;
           }
           if(currentPositionY>=7){
               return false;
           }
           if(board[currentPositionX-2][currentPositionY+1]!=-1){
               return false;
           }
           return true;
       }else{
           return false;
       }
    }

    /*
    methods that make knight perform each type of movement start here. every time the knight moves, the cell the
    knight is going to is set to sequenceNumber, sequenceNumber and steps are incremented.
    */
    public int moveNorthEast(){

        this.setCurrentPositionX(this.getCurrentPositionX()+1);
        this.setCurrentPositionY(this.getCurrentPositionY()-2);
        setBoardCell(this.currentPositionX,this.getCurrentPositionY(),this.getSequenceNumber());
        this.sequenceNumber++;
        this.setSteps(getSteps()+1);
        return 1;
    }
    public int moveNorthWest(){
        this.setCurrentPositionX(this.getCurrentPositionX()-1);
        this.setCurrentPositionY(this.getCurrentPositionY()-2);
        setBoardCell(this.currentPositionX,this.getCurrentPositionY(),this.getSequenceNumber());
        this.sequenceNumber++;
        this.setSteps(getSteps()+1);
        return 1;
    }
    public int moveSouthEast(){
        this.setCurrentPositionX(this.getCurrentPositionX()+1);
        this.setCurrentPositionY(this.getCurrentPositionY()+2);
        setBoardCell(this.currentPositionX,this.getCurrentPositionY(),this.getSequenceNumber());
        this.sequenceNumber++;
        this.setSteps(getSteps()+1);
        return 1;
    }
    public int moveSouthWest(){
        this.setCurrentPositionX(this.getCurrentPositionX()-1);
        this.setCurrentPositionY(this.getCurrentPositionY()+2);
        setBoardCell(this.currentPositionX,this.getCurrentPositionY(),this.getSequenceNumber());
        this.sequenceNumber++;
        this.setSteps(getSteps()+1);
        return 1;
    }
    public int moveEastNorth(){
        this.setCurrentPositionX(this.getCurrentPositionX()+2);
        this.setCurrentPositionY(this.getCurrentPositionY()-1);
        setBoardCell(this.currentPositionX,this.getCurrentPositionY(),this.getSequenceNumber());
        this.sequenceNumber++;
        this.setSteps(getSteps()+1);
        return 1;
    }
    public int moveEastSouth(){
        this.setCurrentPositionX(this.getCurrentPositionX()+2);
        this.setCurrentPositionY(this.getCurrentPositionY()+1);
        setBoardCell(this.currentPositionX,this.getCurrentPositionY(),this.getSequenceNumber());
        this.sequenceNumber++;
        this.setSteps(getSteps()+1);
        return 1;
    }
    public int moveWestNorth(){
        this.setCurrentPositionX(this.getCurrentPositionX()-2);
        this.setCurrentPositionY(this.getCurrentPositionY()-1);
        setBoardCell(this.currentPositionX,this.getCurrentPositionY(),this.getSequenceNumber());
        this.sequenceNumber++;
        this.setSteps(getSteps()+1);
        return 1;
    }
    public int moveWestSouth(){
        this.setCurrentPositionX(this.getCurrentPositionX()-2);
        this.setCurrentPositionY(this.getCurrentPositionY()+1);
        setBoardCell(this.currentPositionX,this.getCurrentPositionY(),this.getSequenceNumber());
        this.sequenceNumber++;
        this.setSteps(getSteps()+1);
        return 1;
    }
    //methods for each type of move end here



    /*
    this method makes the knight move in an exhaustive way. moves for knight are always tried in this particular order:
    NE,NW,SE,SW,EN,ES,WN,ES. if NE is not available, it tries NW, and then SE and so on.
    each time the knight is about to move, in the triple array moves made, for that particular position, that particular
    move is set to one to indicate that from that state, that move has been tried. if the knight then gets stuck and back
    tracks, that move will be seen as unavailable from that state.
    in addtition after executing a move, that new state is pushed onto the stack.

    two letter system for the kinds of moves is explained at beginning of the class.

    */
    public boolean move(){
        State currentState = (State)states.peek();//creates and initializes current state as the last state in the stack
        if(moveAvailable("NE", this.currentPositionX, this.currentPositionY)&&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][0]!=1){

            //states that move has been done from this state
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),0,1);
            moveNorthEast();

            //pushes new state to the stack
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(moveAvailable("NW", this.currentPositionX, this.currentPositionY)&&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][1]!=1){
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),1,1);
            moveNorthWest();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(moveAvailable("SE", this.currentPositionX, this.currentPositionY)&&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][2]!=1) {
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),2,1);
            moveSouthEast();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(moveAvailable("SW", this.currentPositionX, this.currentPositionY)&&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][3]!=1) {
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),3,1);
            moveSouthWest();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(moveAvailable("EN", this.currentPositionX, this.currentPositionY)&&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][4]!=1) {
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),4,1);
            moveEastNorth();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(moveAvailable("ES", this.currentPositionX, this.currentPositionY)&&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][5]!=1) {
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),5,1);
            moveEastSouth();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(moveAvailable("WN", this.currentPositionX, this.currentPositionY)&&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][6]!=1) {
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),6,1);
            moveWestNorth();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(moveAvailable("WS", this.currentPositionX, this.currentPositionY)&&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][7]!=1) {
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),7,1);
            moveWestSouth();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }
        else
            goBack();//backtracks if no move is available
            return false;
    }

    //backtracks by popping out last state from the stack, and then loading second to last state to the current parameters.
    public State goBack(){
        board[this.getCurrentPositionX()][this.getCurrentPositionY()]=-1;
        states.pop();
        State newCurrentState=(State)states.peek();
        this.setSequenceNumber(this.getSequenceNumber()-1);
        this.setCurrentPositionX(newCurrentState.getCurrentPositionX());
        this.setCurrentPositionY(newCurrentState.getCurrentPositionY());
        return (State)states.peek();
    }


    /*executes the move entered as a string parameter. ex: NE, SW, ES,...
    move is only done if it is available and if it has not been done from that state.
    again two letter system for movement is explained at the beginning.
    */
    public boolean warnsdoffMove(String move){
        State currentState = (State)states.peek();
        if (move.equals("NE")&&moveAvailable("NE", this.currentPositionX, this.currentPositionY)
                &&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][0]!=1){

            //states that move has been done from this state
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),0,1);
            moveNorthEast();

            //pushes new state to the stack
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(move.equals("NW")&&moveAvailable("NW", this.currentPositionX, this.currentPositionY)
                &&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][1]!=1){
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),1,1);
            moveNorthWest();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(move.equals("SE")&&moveAvailable("SE", this.currentPositionX, this.currentPositionY)
                &&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][2]!=1){
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),2,1);
            moveSouthEast();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(move.equals("SW")&&moveAvailable("SW", this.currentPositionX, this.currentPositionY)
                &&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][3]!=1){
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),3,1);
            moveSouthWest();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(move.equals("EN")&&moveAvailable("EN", this.currentPositionX, this.currentPositionY)
                &&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][4]!=1){
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),4,1);
            moveEastNorth();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(move.equals("ES")&&moveAvailable("ES", this.currentPositionX, this.currentPositionY)
                &&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][5]!=1){
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),5,1);
            moveEastSouth();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(move.equals("WN")&&moveAvailable("WN", this.currentPositionX, this.currentPositionY)
                &&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][6]!=1){
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),6,1);
            moveWestNorth();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else if(move.equals("WS")&&moveAvailable("WS", this.currentPositionX, this.currentPositionY)
                &&currentState.getMovesMade()[this.currentPositionX][this.currentPositionY][7]!=1){
            currentState.altSetMovesMade(this.getCurrentPositionX(),this.getCurrentPositionY(),7,1);
            moveWestSouth();
            states.push(new State(board,this.getSequenceNumber(),this.getCurrentPositionX(),this.getCurrentPositionY()));
            return true;
        }else{
            goBack();
            return false;
        }
    }

    /*
    * returns the best move from a given position based on Warnsdoff parameters
    * */
    public String bestWarnsdoffMove(int currentPositionX, int currentPositionY){
        State currentState = (State)states.peek();
        int x=currentPositionX;
        int y=currentPositionY;

        int bestChoice=0;

        /*
        will store number of possible moves from each future position after
        each move. ex: index 0 is number of possible moves if after you
        move NE, index is NW..
        */
        int []possibleMoves={0,0,0,0,0,0,0,0};

        //each future position
        int [][]positions={{x+1,y-2},{x-1,y-2},{x+1,y+2},{x-1,y+2},{x+2,y-1},{x+2,y+1},{x-2,y-1},{x-2,y+1}};


        for(int i=0;i<8;i++){
            if(!moveAvailable(moves[i],x,y)){//if move is not available
                possibleMoves[i]=99;
            }
            if(currentState.getMovesMade()[x][y][i]==1){//if move has been tried from that state
                possibleMoves[i]=99;
            }
        }

        //counting number of moves available for each future position
        for(int i=0; i<8;i++){
            if(possibleMoves[i]!=99){//if move is not available do not consider its future possible moves
                for(int j=0;j<8;j++){
                    if(moveAvailable(moves[j],positions[i][0],positions[i][1])){
                        possibleMoves[i]+=1;
                    }
                }
            }
        }

        //looking for the move that its future position has the least number of possible moves. it is given by bestChoice
        for(int i=0;i<possibleMoves.length;i++){
            if(possibleMoves[i]<possibleMoves[bestChoice]){
                bestChoice=i;
            }
        }

        return moves[bestChoice];
    }

    //checks if board has been completed. ex: all positions are different from -1
    public boolean isBoardComplete(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board[i][j]==-1)
                    return false;
            }
        }
        return true;
    }

    //prints the board
    public void printBoard(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print(board[j][i]+"   ");
            }
            System.out.println("");
            System.out.println("");
        }
        System.out.println("///////////////////////////////////////////////////////////////////////////////////");
    }

    //initializes the board with -1 at every position
    public void initializeBoard(int [][]board){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                board[i][j]=-1;
            }
        }
    }


    /*this method receives the desired starting position from the user as parameters
    and starts the begins the tour from that position and returns number of steps done,
    including the wrong ones but not including the backtracks.
    */
    public int start(int startingPositionX, int startingPositionY){
        int x = startingPositionX;
        int y = startingPositionY;
        if(x>7||x<0||y>7||y<0){//returns -1 if the starting position is not on the board
            return -1;
        }

        //initializes tour parameters
        this.setSteps(0);
        this.setCurrentPositionX(x);
        this.setCurrentPositionY(y);
        board[this.getCurrentPositionX()][this.getCurrentPositionY()]=sequenceNumber;
        sequenceNumber++;
        this.printBoard();

        /*
        executes Warnsdoff parameters for move on the first 48 moves. for only the first 32 there were starting
        positions from were it was still taking too long to complete the board.
        */
        while(this.sequenceNumber<48){
            this.warnsdoffMove(this.bestWarnsdoffMove(this.currentPositionX,this.currentPositionY));
        }

        //executes exhaustive move for the rest of the cells until board is complete
        while(!this.isBoardComplete()){
            this.move();
        }

        //initialize parameters of the tour after each completion of the board
        this.printBoard();
        this.initializeBoard(this.board);
        this.setSequenceNumber(0);
        return this.getSteps();
    }
}
