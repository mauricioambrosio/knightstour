/**
 * Created by Maurício on 2/14/2017.
 *
 * allows the creation of a stack containing each state of the board each every time.
 * this class contains all the methods necessary. variables and methods names are self
 * explanatory.
 *
 */
public class MyStack {
    private State top;//element at the top of stack

    //getter and setter
    public State getTop() {
        return top;
    }
    public void setTop(State top) {
        this.top = top;
    }

    public MyStack() {//standard constructor
    }
    public boolean empty(){//returns true if stack is empty. false if not.
        if(this.getTop()==null){
            return true;
        }
        return false;
    }
    public State pop(){//returns and deletes top element of stack
        if(this.getTop()!=null) {
            State currentTop=this.getTop();
            this.setTop(this.getTop().getPreviousState());
            return currentTop;
        }
        else
            return this.getTop();
    }
    public State peek(){
        return this.getTop();
    }//returns but keeps top element of stack

    public State push(State state){//adds an element to the top of stack and returns it
        if(this.getTop()==null){
            this.setTop(state);
        }else if(this.getTop()!=null){
            state.setPreviousState(this.getTop());
            this.setTop(state);
        }
        return state;
    }
    public int search(State state){//returns offset from top to given element
        int offset=0;
        State currentState=this.getTop();
        while(state!=currentState){
            currentState=currentState.getPreviousState();
            offset++;
        }
        return offset;
    }

}
