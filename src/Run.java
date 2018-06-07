import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Scanner;

public class Run {

    private int currentState=0;

    ArrayList<OutPut> outPuts = new ArrayList<>();

    private ArrayList<String> input = new ArrayList<String>();

    private MyStack inputStack = new MyStack();
    private MyStack mainStack = new MyStack();

    private Scanner scanner = new Scanner(System.in);

    private ActionTableState r1 = new ActionTableState("r", 1);
    private ActionTableState r2 = new ActionTableState("r", 2);
    private ActionTableState r3 = new ActionTableState("r", 3);
    private ActionTableState r4 = new ActionTableState("r", 4);
    private ActionTableState r5 = new ActionTableState("r", 5);
    private ActionTableState r6 = new ActionTableState("r", 6);

    private ActionTableState s0 = new ActionTableState("s", 0);
    private ActionTableState s1 = new ActionTableState("s", 1);
    private ActionTableState s2 = new ActionTableState("s", 2);
    private ActionTableState s3 = new ActionTableState("s", 3);
    private ActionTableState s4 = new ActionTableState("s", 4);
    private ActionTableState s5 = new ActionTableState("s", 5);
    private ActionTableState s6 = new ActionTableState("s", 6);
    private ActionTableState s7 = new ActionTableState("s", 7);
    private ActionTableState s8 = new ActionTableState("s", 8);
    private ActionTableState s9 = new ActionTableState("s", 9);
    private ActionTableState s10 = new ActionTableState("s", 10);
    private ActionTableState s11 = new ActionTableState("s", 11);

    private ActionTableState GOTO1 = new ActionTableState("goto", 1);
    private ActionTableState GOTO2 = new ActionTableState("goto", 2);
    private ActionTableState GOTO3 = new ActionTableState("goto", 3);
    private ActionTableState GOTO8 = new ActionTableState("goto", 8);
    private ActionTableState GOTO9 = new ActionTableState("goto", 9);
    private ActionTableState GOTO10 = new ActionTableState("goto", 10);

    private ActionTableState nil = new ActionTableState("nil", 404);
    private ActionTableState acc = new ActionTableState("acc", 200);


    private ActionTableState actionTable[][] = {
            //a - + - * - ( - ) - $ - E - T - F
            {s5, nil, nil, s4, nil, nil, GOTO1, GOTO2, GOTO3},   // 0
            {nil, s6, nil, nil, nil, acc, nil, nil, nil},  // 1
            {nil, r2, s7, nil, r2, r2, nil, nil, nil},     // 2
            {nil, r4, r4, nil, r4, r4, nil, nil, nil},     // 3
            {s5, nil, nil, s4, nil, nil, GOTO8, GOTO2, GOTO3},   // 4
            {nil, r6, r6, nil, r6, r6, nil, nil, nil},    // 5
            {s5, nil, nil, s4, nil, nil, nil, GOTO9, GOTO3},   // 6
            {s5, nil, nil, s4, nil, nil, nil, nil, GOTO10},   // 7
            {nil, s6, nil, nil, s11, nil, nil, nil, nil},  // 8
            {nil, r1, s7, nil, r1, r1, nil, nil, nil},     // 9
            {nil, r3, r3, nil, r3, r3, nil, nil, nil},     // 10
            {nil, r5, r5, nil, r5, r5, nil, nil, nil},     // 11
    };


    public Run() {
        analysis();
    }


    public void analysis() {
        System.out.print("Input string : ");
        String inputString = scanner.next();
        inputString += "$";
        System.out.println();
        if (inputString.equals("a*a+a$") || inputString.equals("a+a*a$") || inputString.equals("(a+a)*a$")) {
            for (int i = inputString.length() - 1; i >= 0; i--) {
                input.add(inputString.substring(i, i + 1));
            }
            for (int i = 0; i < input.size(); i++) {
                System.out.println(input.get(i));
                inputStack.push(input.get(i));
            }

            inputStack.printStack();
            mainStack.push(0);

            while (true) {

                if (mainStack.peek() instanceof Integer) {
                    System.out.println("good");
                    int row = (int) mainStack.peek();
                    int colunm = getColunm((String) inputStack.peek());

                    ActionTableState actionTableState = actionTable[row][colunm];

                    construction(actionTableState);

                    //System.out.println(actionTableState.getName()+actionTableState.getNumber()+"");


                } else {
                    System.out.println("bad");
                    int row = (int) mainStack.peekPrevious();
                    int colunm = getColunm((String) mainStack.peek());

                    System.out.println("@@@@@@@" + row + ",,," + colunm);

                    ActionTableState actionTableState = actionTable[row][colunm];

                    construction(actionTableState);
                }


            }
        } else {

            System.out.println("다시 입력하시오.");
        }


    }

    public void construction(ActionTableState as) {     //여기에서 단계 , 스택 , 입력심벌 , 구문분석내용 , 출력까지

        if (as.getName().equals("s")) {
            mainStack.push(inputStack.pop());
            mainStack.push(as.getNumber());

            mainStack.printStack();
            inputStack.printStack();

            outPuts.add(new OutPut(currentState+"",mainStack.getStackprint(),inputStack.getStackprintReverse(),"Shift "+as.getNumber()," "));
            currentState++;
        } else if (as.getName().equals("r")) {
            reduceGrammer(as.getNumber());

        } else if (as.getName().equals("goto")) {
            mainStack.push(as.getNumber());
            mainStack.printStack();
            inputStack.printStack();

            outPuts.add(new OutPut(currentState+"",mainStack.getStackprint(),inputStack.getStackprintReverse(),"GOTO "+as.getNumber()," "));
            currentState++;

        } else if (as.getName().equals("acc")) {
            System.out.println("$$$$$$$$$$$$$$$$");
            System.out.println("accept!!");

            outPuts.add(new OutPut(currentState+"",mainStack.getStackprint(),inputStack.getStackprintReverse(),"Accept!!!"," "));


            acceptPrint();

            System.exit(0);


        }
    }

    public void reduceGrammer(int grammer_num) {
        switch (grammer_num) {
            case 1:

                if (mainStack.searchNumber("T") != 404) {
                    if (mainStack.searchNumber("+") != 404) {
                        if (mainStack.searchNumber("E") != 404) {
                            int num3 = mainStack.searchNumber("E");
                            for (int i = mainStack.stackSize() - 1; i >= num3; i--) {
                                mainStack.pop();
                            }
                            mainStack.push("E");
                        }
                    }
                }
                mainStack.printStack();
                inputStack.printStack();

                outPuts.add(new OutPut(currentState+"",mainStack.getStackprint(),inputStack.getStackprintReverse(),"Reduce 1","1"));
                currentState++;

                break;
            case 2:
                int num2 = mainStack.searchNumber("T");
                for (int i = mainStack.stackSize() - 1; i >= num2; i--) {
                    mainStack.pop();
                }
                mainStack.push("E");
                mainStack.printStack();
                inputStack.printStack();

                outPuts.add(new OutPut(currentState+"",mainStack.getStackprint(),inputStack.getStackprintReverse(),"Reduce 2","2"));
                currentState++;

                break;
            case 3:

                System.out.println("rrrrrrrrrrr33333333");

                if (mainStack.searchNumber("F") != 404) {
                    if (mainStack.searchNumber("*") != 404) {
                        if (mainStack.searchNumber("T") != 404) {
                            int num3 = mainStack.searchNumber("T");
                            for (int i = mainStack.stackSize() - 1; i >= num3; i--) {
                                mainStack.pop();
                            }
                            mainStack.push("T");
                        }
                    }
                }
                mainStack.printStack();
                inputStack.printStack();

                outPuts.add(new OutPut(currentState+"",mainStack.getStackprint(),inputStack.getStackprintReverse(),"Reduce 3","3"));
                currentState++;

                break;
            case 4:
                System.out.println("====?===" + grammer_num + "====?====");

                int num4 = mainStack.searchNumber("F");
                System.out.println("========" + num4 + "==========");
                for (int i = mainStack.stackSize() - 1; i >= num4; i--) {
                    mainStack.pop();
                }
                mainStack.push("T");
                mainStack.printStack();
                inputStack.printStack();

                outPuts.add(new OutPut(currentState+"",mainStack.getStackprint(),inputStack.getStackprintReverse(),"Reduce 4","4"));
                currentState++;

                break;
            case 5:
                if (mainStack.searchNumber(")") != 404) {
                    if (mainStack.searchNumber("E") != 404) {
                        if (mainStack.searchNumber("(") != 404) {
                            int num3 = mainStack.searchNumber("(");
                            for (int i = mainStack.stackSize() - 1; i >= num3; i--) {
                                mainStack.pop();
                            }
                            mainStack.push("F");
                        }
                    }
                }
                mainStack.printStack();
                inputStack.printStack();

                outPuts.add(new OutPut(currentState+"",mainStack.getStackprint(),inputStack.getStackprintReverse(),"Reduce 5","5"));
                currentState++;

                break;
            case 6:
                int num6 = mainStack.searchNumber("a");
                System.out.println("========" + num6 + "==========");
                for (int i = mainStack.stackSize() - 1; i >= num6; i--) {
                    mainStack.pop();
                }
                mainStack.push("F");
                mainStack.printStack();
                inputStack.printStack();

                outPuts.add(new OutPut(currentState+"",mainStack.getStackprint(),inputStack.getStackprintReverse(),"Reduce 6","6"));
                currentState++;

                break;
        }
    }


    public int getColunm(String value) {

        if (value.equals("a")) {
            return 0;
        } else if (value.equals("+")) {
            return 1;
        } else if (value.equals("*")) {
            return 2;
        } else if (value.equals("(")) {
            return 3;
        } else if (value.equals(")")) {
            return 4;
        } else if (value.equals("$")) {
            return 5;
        } else if (value.equals("E")) {
            return 6;
        } else if (value.equals("T")) {
            return 7;
        } else if (value.equals("F")) {
            return 8;
        } else return 0;
    }

    private void acceptPrint(){
        System.out.println("====================================================================================================");
        System.out.print("Step");
        System.out.print("                      ");
        System.out.print("Stack");
        System.out.print("                      ");
        System.out.print("input");
        System.out.print("                      ");
        System.out.print("Action");
        System.out.print("        ");
        System.out.print("Output");
        System.out.println();


        for(int i =0 ; i<outPuts.size();i++){
            System.out.print(outPuts.get(i).getState());
            System.out.print("                   ");
            System.out.print(outPuts.get(i).getMainStack());
            System.out.print("                   ");
            System.out.print(outPuts.get(i).getInputStack());
            System.out.print("                   ");
            System.out.print(outPuts.get(i).getConstruction());
            System.out.print("                   ");
            System.out.print(outPuts.get(i).getOutput());
            System.out.println();
        }

        System.out.println("====================================================================================================");

    }
}
