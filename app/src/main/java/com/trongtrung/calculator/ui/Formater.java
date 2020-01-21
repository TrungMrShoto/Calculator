package com.trongtrung.calculator.ui;

import android.widget.Toast;

/**
 * Created by NguyenTrongTrung on 21 January 2020
 */
public class Formater {
    public static String formatString(String input)
    {
        if (! input.contains("."))
            return input;
        String output;
        int pointPosition = input.indexOf('.');
        int ePosition;
        String digitAfterPoint;
        String digitAfterE;
        if (input.contains("E"))
        {
            ePosition= input.indexOf('E');
            digitAfterPoint= input.substring(pointPosition, ePosition);
            digitAfterE = input.substring(ePosition);
        }
        else
        {
            digitAfterPoint= input.substring(pointPosition);
            digitAfterE = "";
        }

        int i = 0;
        while (i>-1)
        {
            i = digitAfterPoint.length() - 1;

            if (digitAfterPoint.charAt(i) == '0') {
                digitAfterPoint = digitAfterPoint.substring(0, i);
                i--;
            }
            else
                break;
        }
        output = input.substring(0,pointPosition);
        if (digitAfterPoint.length()>1)
            output +=digitAfterPoint;
        output += digitAfterE;
        return output;
    }
}
