package thestressteam.spiking;


import java.util.ArrayList;


/**
 * Created by Ivan on 4/26/2016.
 */

public class GOTO_interpreter implements java.io.Serializable{
    public int lineNumber;
    public String statement;
    public ArrayList variables_declared;

    public static void main(String[] args){
        String esp = "1 > 1";
        new GOTO_interpreter().readingExpression(esp);
    }

    public void readingExpression(String expression){
        String[] expressionList;
        expressionList = expression.split(" ");
//        lineNumber = Integer.parseInt(expressionList[0]);
//        statement = expressionList[1];
        int value1 = Integer.parseInt(expressionList[0]);
        String operator = expressionList[1];
        int value2 = Integer.parseInt(expressionList[2]);
        int result = operation(operator, value1, value2);
        System.out.println("Result: " + result);
    }

    public int operation(String op, int num1, int num2){
        int num3 = 0;
        if (op.equals("+")){
            num3 = num1+num2;
        }
        else if(op.equals("-")){
            num3 = num1-num2;
        }
        else if (op.equals("==")){
            if (num1 == num2){
                num3 = 1;
            }
            else{
                num3 = 0;
            }
        }
        else if(op.equals(">")) {
            if (num1 > num2) {
                num3 = 1;
            }
            else{
                num3 = 0;
            }
        }
        return num3;
    }

}
