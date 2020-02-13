package com.trongtrung.calculator;

import com.trongtrung.calculator.converter.Unit;
import com.trongtrung.calculator.ui.Formater;
import com.trongtrung.calculator.ui.StandardFragment;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

/**
 * Created by NguyenTrongTrung on 13 February 2020
 */
public class EvalutateExpression {
    private static double ansResult=0;
    private static String ERROR = "ERROR";
    public static String evaluateExpression(String input)
    {
        //Stack for numbers: 'values'
        Stack<Double> values = new Stack<>();

        //Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<>();
        
        for(int i=0;i<input.length();i++)
        {
            //Current token is a number, push it to stack for numbers
            if(isDigitCharacter(input.charAt(i))||isPointOrSubtractCharacter(input.charAt(i)))
            {
                StringBuilder buf = new StringBuilder();
                while(i<input.length()&&(isDigitCharacter(input.charAt(i))||isPointOrSubtractCharacter(input.charAt(i)))) {
                    buf.append(input.charAt(i));
                    i++;
                }
                String temp = buf.toString();
                if(temp.equals(GeneralCharacter.POINT) || temp.equals("-")||temp.equals("-."))
                    return ERROR;
                else
                    values.push(Double.parseDouble(temp));
                i--;

            }
            //values in ANS variable
            else if (input.charAt(i) == 'A')
            {
                values.push(ansResult);
                i+=2;
            }
            //Current token is an operator
            else if (isOperator(input.charAt(i)))
            {
                while(!ops.empty() && hasPrecedence(input.charAt(i),ops.peek()))
                {
                    String temp = applyOp(ops.pop(),values.pop(),values.pop());
                    if (temp.equals(ERROR))
                    {
                        return temp;
                    }
                    else {
                        values.push(Double.valueOf(temp));
                    }
                }

                ops.push(input.charAt(i));
            }
        }
        while(!ops.empty())
        {
            String temp = applyOp(ops.pop(),values.pop(),values.pop());
            if (temp.equals(ERROR))
            {
                return temp;
            }
            else {
                values.push(Double.valueOf(temp));
            }
        }
        ansResult = values.pop();
        return formatOutput(ansResult);
    }

    private static boolean isOperator(char token) {
        String c = String.valueOf(token);
        return StandardFragment.OPERATOR.contains(c);
    }

    private static boolean isDigitCharacter(char token)
    {
        return token>='0' && token<='9';
    }

    private static boolean isPointOrSubtractCharacter(char token)
    {
        String temp = String.valueOf(token);
        return temp.equals(GeneralCharacter.POINT) || temp.equals("-");
    }


    private static boolean hasPrecedence(char op1, char op2)
    {
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    private static String applyOp(char op,double b, double a)
    {
        double temp=0;
        switch (String.valueOf(op))
        {
            case GeneralCharacter.ADD:
                temp = a + b;
                break;
            case GeneralCharacter.SUB:
                temp= a - b;
                break;
            case GeneralCharacter.MUL:
                temp = a * b;
                break;
            case GeneralCharacter.DIV:
                if (Double.compare(b,0.0)==0) return ERROR;
                temp = a / b;
                break;
        }
        return String.valueOf(temp);
    }

    private static String formatOutput(double value)
    {
        String result = (new BigDecimal(value,  new MathContext(Unit.PRECISION))).toString();
        return Formater.formatString(result);
    }
}
