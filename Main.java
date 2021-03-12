import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Maurício on 2/12/2017.
 *
 * variables and methods names are self explanatory
 */
public class Main {

    /*edits TeePrintStream class and overrides its methods to make it be
    able to combine an OutputStream and a PrintStream into one single
    output so it can be used later to be set as the system output.
    this is done so that output is written both on the console and on file.
    */
    public static class TeePrintStream extends PrintStream {

        private final PrintStream sec;

        public TeePrintStream(OutputStream main, PrintStream sec) {
            super(main);
            this.sec = sec;
        }

        /**
         * Closes the main stream.
         * The second stream is just flushed but <b>not</b> closed.
         * @see java.io.PrintStream#close()
         */
        @Override
        public void close() {
            // just for documentation
            super.close();
        }

        @Override
        public void flush() {
            super.flush();
            sec.flush();
        }

        @Override
        public void write(byte[] buf, int off, int len) {
            super.write(buf, off, len);
            sec.write(buf, off, len);
        }

        @Override
        public void write(int b) {
            super.write(b);
            sec.write(b);
        }

        @Override
        public void write(byte[] b) throws IOException {
            super.write(b);
            sec.write(b);
        }
    }

    public static void main(String[] args){
        Tour myTour=new Tour();
        Scanner sc= new Scanner(System.in);
        InitialPosition initialPosition;
        int x;
        int y;
        char answerChar;
        int answerInt;
        String answerString=new String();

        System.out.println("write file name to save output in: ");
        answerString=sc.nextLine();
        System.out.println("your output will be saved in "+answerString);
        System.out.println("");
        /*
        uses edited TeePrintStream class to combine file and console into a single output
        and make it the system output.
         */

        try {
            TeePrintStream out = new TeePrintStream(System.out,new PrintStream(answerString));
            System.setOut(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("what position do you want your knight to start from?");
        System.out.print("x:");
        x=sc.nextInt();
        System.out.print("y:");
        y=sc.nextInt();
        initialPosition=new InitialPosition(0,x,y);

        //menu..
        do{

            if(initialPosition!=null) {
                System.out.println("\nyour initial positions:");
                initialPosition.printInitialPositions();
            }
            System.out.println("");
            System.out.print("enter add(A), delete(D), modify(M), continue(C), or Quit(Q):");
            answerChar=sc.next().charAt(0);//gets answer from user
            sc.nextLine();
            answerChar= Character.toUpperCase(answerChar);
            switch (answerChar) {
                case 'A':
                    System.out.println("what position do you want your knight to start from?");
                    System.out.print("x:");
                    x = sc.nextInt();
                    System.out.print("y:");
                    y = sc.nextInt();
                    if(initialPosition==null){
                        initialPosition=new InitialPosition(0,x, y);

                    }else {
                        initialPosition.addInitialPosition(new InitialPosition(x, y));
                    }
                    System.out.println("you added the initial position x="+x+" y="+y);
                    break;
                case 'D':
                    System.out.println("what initial position do you want to delete by id?");
                    if(initialPosition!=null){
                        initialPosition.printInitialPositions();
                    }else{
                        System.out.println("no initial positions entered\n");
                        break;
                    }
                    System.out.println("");
                    System.out.print("id:");
                    answerInt=sc.nextInt();
                    System.out.println("\nyou deleted the initial position x="
                            +initialPosition.findInitialPositionById(answerInt).getStartingPositionX()+" y="
                            +initialPosition.findInitialPositionById(answerInt).getStartingPositionY());
                    initialPosition=initialPosition.deleteInitialPosition(answerInt);
                    break;
                case 'M':
                    System.out.println("what initial position do you want to modify by id?");
                    if(initialPosition!=null){
                        initialPosition.printInitialPositions();
                    }else{
                        System.out.println("no initial positions entered\n");
                        break;
                    }
                    System.out.println("");
                    System.out.print("id:");
                    answerInt=sc.nextInt();
                    if(initialPosition.findInitialPositionById(answerInt)==null)
                        System.out.println("this initial position does not exist!\n");
                    else {
                        System.out.print("new x:");
                        x = sc.nextInt();
                        System.out.print("new y:");
                        y = sc.nextInt();
                        System.out.println("\nyou modified the initial position x="
                                +initialPosition.findInitialPositionById(answerInt).getStartingPositionX()+" y="
                                +initialPosition.findInitialPositionById(answerInt).getStartingPositionY()+" to x="
                                +x+" y="+y);
                        initialPosition.modifyInitialPosition(answerInt, x, y);
                    }
                    break;
                case 'C':
                    if(initialPosition==null)
                        System.out.println("no initial positions entered\n");
                    break;
                case 'Q':
                    return;
            }
        }while(answerChar!='C'||initialPosition==null);
        System.out.println("\nyour initial positions:");
        initialPosition.printInitialPositions();
        InitialPosition currentInitialPosition=initialPosition;

        //array to store each solution
        int [][][]arrayOfSolutions=new int [initialPosition.howManyElementsInList()][8][8];//array to store each solution
        int counter=0;
        int numberOfSteps;
        while(currentInitialPosition!=null){
            System.out.println("\ninitial position:");
            System.out.println("id: "+currentInitialPosition.getId()+"    x: "+
                    currentInitialPosition.getStartingPositionX()+"    y: "+currentInitialPosition.getStartingPositionY());
            numberOfSteps=myTour.start(currentInitialPosition.getStartingPositionX(),currentInitialPosition.getStartingPositionY());
            arrayOfSolutions[counter]=myTour.getBoard();
            counter++;
            System.out.println("Knight's Tour total number of steps: " +numberOfSteps);
            if(numberOfSteps==-1){
                System.out.println("position does not exist!");
            }
            currentInitialPosition=currentInitialPosition.getNextInitialPosition();
        }

    }
}
