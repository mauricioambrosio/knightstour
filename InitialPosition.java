/**
 * Created by Maurício on 2/12/2017.
 * this class creates and allows for the storage of initial
 * positions inputted by the user in a singly linked list.
 * methods can only be called by an object so the object calling
 * the methods is the head of the linked list.
 *
 * variables and methods names are self explanatory.
 *
 */
public class InitialPosition {

    private int id=-1;
    private int startingPositionX=-1;
    private int startingPositionY=-1;
    private InitialPosition nextInitialPosition=null;

    //3 different constructors with different or no input parameters
    public InitialPosition() {

    }
    public InitialPosition(int startingPositionX, int startingPositionY) {
        this.startingPositionY = startingPositionY;
        this.startingPositionX = startingPositionX;
    }
    public InitialPosition(int id, int startingPositionX, int startingPositionY) {
        this.id = id;
        this.startingPositionX = startingPositionX;
        this.startingPositionY = startingPositionY;
    }
    //getters and setters start here
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getStartingPositionX() {
        return startingPositionX;
    }
    public void setStartingPositionX(int startingPositionX) {
        this.startingPositionX = startingPositionX;
    }
    public int getStartingPositionY() {
        return startingPositionY;
    }
    public void setStartingPositionY(int startingPositionY) {
        this.startingPositionY = startingPositionY;
    }

    public InitialPosition getNextInitialPosition() {
        return nextInitialPosition;
    }

    public void setNextInitialPosition(InitialPosition nextInitialPosition) {
        this.nextInitialPosition = nextInitialPosition;
    }
    //getters and setters end here

    public InitialPosition findTail(InitialPosition current){//finds last element of linked list.
        InitialPosition temp=current;
        while(temp.getNextInitialPosition()!=null){
            temp=temp.getNextInitialPosition();
        }
        return temp;
    }

    public InitialPosition addInitialPosition(InitialPosition toAdd){//adds an element to the last position of the linked list
        InitialPosition head=this;
        InitialPosition tail = findTail(head);
        toAdd.setId(tail.getId()+1);
        tail.setNextInitialPosition(toAdd);
        return toAdd;
    }
    public void modifyInitialPosition(int id, int x,int y){//updates startingPositionX/Y of element with the given id.
        if(this==null){
            System.out.println("no initial positions entered");
            return;
        }
        InitialPosition toModify=findInitialPositionById(id);
        toModify.setStartingPositionX(x);
        toModify.setStartingPositionY(y);
    }

    public InitialPosition deleteInitialPosition(int id){//deletes element with the given id
        if(this==null){
            System.out.println("no initial positions entered");
            return this;
        }
        //taking care of all possible cases. element is head, element is tail,..
        InitialPosition head=this;
        InitialPosition toBeDeleted=findInitialPositionById(id);
        if(toBeDeleted==head){
            head=head.getNextInitialPosition();
            return head;
        }
        if(toBeDeleted==null){
            System.out.println("this initial position does not exist!");
            return head;
        }
        if(toBeDeleted==head&&toBeDeleted.getNextInitialPosition()!=null){
            head=head.getNextInitialPosition();
        }else if(toBeDeleted==head&&toBeDeleted.getNextInitialPosition()==null){
            head=null;
            toBeDeleted=null;
        }else{
            InitialPosition previousInitialPosition=head;
            while(previousInitialPosition.getNextInitialPosition()!=toBeDeleted){
                previousInitialPosition=previousInitialPosition.getNextInitialPosition();
            }
            if(toBeDeleted.getNextInitialPosition()==null){
                previousInitialPosition.setNextInitialPosition(null);
                toBeDeleted=null;
            }else{
                previousInitialPosition.setNextInitialPosition(toBeDeleted.getNextInitialPosition());
                toBeDeleted=null;
            }
        }
        return head;
    }

    public InitialPosition findInitialPositionById(int id){//returns the element with the given id
        InitialPosition head=this;
        if(head==null){
            return null;
        }
        InitialPosition temp=head;
        while (temp!=null){
            if(temp.getId()==id){
                return temp;
            }
            temp=temp.getNextInitialPosition();
        }
        return null;
    }

    public void printInitialPositions(){//prints all elements in linked list with their parameters in an organized manner
        if(this==null){
            System.out.println("...");
            return;
        }
        InitialPosition currentInitialPosition=this;
        while(currentInitialPosition!=null){
            System.out.println("id: "+currentInitialPosition.getId()+"    x: "+
                    currentInitialPosition.getStartingPositionX()+"    y: "+currentInitialPosition.getStartingPositionY());
            currentInitialPosition=currentInitialPosition.getNextInitialPosition();
        }
    }
    public int howManyElementsInList(){//counts and returns number of elements in the liked list
        int numOfElements=0;
        InitialPosition temp=this;
        while(temp!=null){
            temp=temp.getNextInitialPosition();
            numOfElements++;
        }
        return numOfElements;
    }
}
