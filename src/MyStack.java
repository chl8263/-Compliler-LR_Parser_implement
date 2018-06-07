import java.util.ArrayList;

public class MyStack {

    int top;
    private ArrayList<Object> myStack ;

    public MyStack() {
        top = -1;
        myStack = new ArrayList<>();
    }

    public Object peek() {
        System.out.println("PEEK : " + String.valueOf(myStack.get(myStack.size()-1)) );
        return myStack.get(myStack.size()-1);
    }


    public void push(Object value) {
        myStack.add(value);
        System.out.println( String.valueOf(myStack.get(myStack.size()-1)) + " PUSH !");
    }

    public Object pop() {
        System.out.println(String.valueOf(myStack.get(myStack.size()-1)) + " POP !");
        Object o = myStack.get(myStack.size()-1);
        myStack.remove(myStack.size()-1);

        return o;
    }


    public int searchNumber(Object o) {
        for (int i=myStack.size()-1; i>=0 ; i--){
            if(myStack.get(i).equals(o)){
                return i;
            }
        }
        return 404;
        //System.out.println("PEEK : " + String.valueOf(myStack.get(myStack.size()-1)) );
        //return myStack.get(myStack.size()-1);
    }

    public int stackSize(){
        return myStack.size();
    }

    public int peekPrevious(){
        System.out.println("PEEK : " + String.valueOf(myStack.get(myStack.size()-2))) ;
        return (int) myStack.get(myStack.size()-2);
    }

    public void printStack() {
        System.out.println("-- STACK LIST --");
        for (int i = 0; i <myStack.size(); i++) {
            System.out.print(myStack.get(i)+" ");
        }
        System.out.println("");
        System.out.println("-- END OF LIST --");
        System.out.println("");

    }
}