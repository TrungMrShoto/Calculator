package com.trongtrung.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by NguyenTrongTrung on 19 January 2020
 */
public class GeneralArray {
    private static List<String> listOfConverterNoSub;
    private static List<String> listOfConverterSub;
    private static List<String> listOfProgrammerVerical;
    private static List<String> listOfProgrammerHorizontal;
    private static List<String> listOfStandardVertical;
    private static List<String> listOfStandardHorizontal;

    public static List<String> getListOfProgrammerVerical() {
        if (listOfProgrammerVerical == null) initialProgrammerVertical();
        return listOfProgrammerVerical;
    }

    public static List<String> getListOfProgrammerHorizontal() {
        if (listOfProgrammerHorizontal == null) initialProgrammerHorizontal();
        return listOfProgrammerHorizontal;
    }

    public static List<String> getListOfStandardVertical() {
        if (listOfStandardVertical == null) initialStandardVertical();
        return listOfStandardVertical;
    }

    public static List<String> getListOfStandardHorizontal() {
        if (listOfStandardHorizontal == null) initialStandardHorizontal();
        return listOfStandardHorizontal;
    }

    public static  List<String> getListOfConverterNoSub()
    {
        if (listOfConverterNoSub == null) initialConverterNoSub();
        return listOfConverterNoSub;
    }

    public static List<String> getListOfConverterSub()
    {
        if (listOfConverterSub == null ) initialConverterSub();
        return listOfConverterSub;
    }

    private GeneralArray(){}

    private static void initialProgrammerVertical(){
        listOfProgrammerVerical = new ArrayList<>();
        String[] key = {
                "A", GeneralCharacter.LEFT_SHIFT,GeneralCharacter.RIGHT_SHIFT,GeneralCharacter.CE, GeneralCharacter.DEL,
                "B", GeneralCharacter.LEFT_PARENTHESIS, GeneralCharacter.RIGHT_PARENTHESIS, GeneralCharacter.MOD, GeneralCharacter.DIV,
                "C", "7", "8", "9", GeneralCharacter.MUL,
                "D", "4", "5", "6", GeneralCharacter.SUB,
                "E", "1", "2", "3", GeneralCharacter.ADD,
                "F", GeneralCharacter.ADD_AND_SUB, "0", GeneralCharacter.ANS, GeneralCharacter.EQUAL
        };
        listOfProgrammerVerical = Arrays.asList(key);
    }
    private static void initialStandardHorizontal(){

    }
    private static void initialStandardVertical(){

    }
    private static void initialProgrammerHorizontal() {
    }

    private static void initialConverterNoSub()
    {
        listOfConverterNoSub = new ArrayList<>();
        String[] key = {
                GeneralCharacter.SPACE, GeneralCharacter.CE, GeneralCharacter.DEL,
                "7" ,"8","9",
                "4","5","6",
                "1","2","3",
                GeneralCharacter.SPACE, "0", GeneralCharacter.POINT
        };
        listOfConverterNoSub = Arrays.asList(key);
    }

    private static void initialConverterSub()
    {
        listOfConverterSub = new ArrayList<>();
        String[] key = {
                GeneralCharacter.SPACE, GeneralCharacter.CE, GeneralCharacter.DEL,
                "7" ,"8","9",
                "4","5","6",
                "1","2","3",
                GeneralCharacter.ADD_AND_SUB, "0", GeneralCharacter.POINT
        };
        listOfConverterNoSub = Arrays.asList(key);
    }


}
