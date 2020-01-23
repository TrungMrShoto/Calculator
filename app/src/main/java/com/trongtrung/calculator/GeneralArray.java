package com.trongtrung.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by NguyenTrongTrung on 19 January 2020
 */
public class GeneralArray {
    private static List<Element> listOfConverterNoSub;
    private static List<Element> listOfConverterSub;
    private static List<Element> listOfProgrammerVertical;
    private static List<Element> listOfProgrammerHorizontal;
    private static List<Element> listOfStandardVertical;
    private static List<Element> listOfStandardHorizontal;

    public static List<Element> getListOfProgrammerVertical() {
        if (listOfProgrammerVertical == null) initialProgrammerVertical();
        return listOfProgrammerVertical;
    }

    public static List<Element> getListOfProgrammerHorizontal() {
        if (listOfProgrammerHorizontal == null) initialProgrammerHorizontal();
        return listOfProgrammerHorizontal;
    }

    public static List<Element> getListOfStandardVertical() {
        if (listOfStandardVertical == null) initialStandardVertical();
        return listOfStandardVertical;
    }

    public static List<Element> getListOfStandardHorizontal() {
        if (listOfStandardHorizontal == null) initialStandardHorizontal();
        return listOfStandardHorizontal;
    }

    public static  List<Element> getListOfConverterNoSub()
    {
        if (listOfConverterNoSub == null) initialConverterNoSub();
        return listOfConverterNoSub;
    }

    public static List<Element> getListOfConverterSub()
    {
        if (listOfConverterSub == null ) initialConverterSub();
        return listOfConverterSub;
    }

    private GeneralArray(){}

    private static void initialProgrammerVertical(){
        listOfProgrammerVertical = new ArrayList<>();
        Element[] key = {
                new Element("A", R.color.c3),
                new Element(GeneralCharacter.LEFT_SHIFT, R.color.c2),
                new Element(GeneralCharacter.RIGHT_SHIFT, R.color.c2),
                new Element(GeneralCharacter.CE, R.color.c2),
                new Element(GeneralCharacter.DEL, R.color.c2),
                new Element("B", R.color.c3),
                new Element(GeneralCharacter.LEFT_PARENTHESIS, R.color.c2),
                new Element(GeneralCharacter.RIGHT_PARENTHESIS, R.color.c2),
                new Element(GeneralCharacter.MOD, R.color.c2),
                new Element(GeneralCharacter.DIV, R.color.c2),
                new Element("C", R.color.c3),
                new Element("7", R.color.c3),
                new Element("8", R.color.c3),
                new Element("9", R.color.c3),
                new Element(GeneralCharacter.MUL, R.color.c2),
                new Element("D", R.color.c3),
                new Element("4", R.color.c3),
                new Element("5", R.color.c3),
                new Element("6", R.color.c3),
                new Element(GeneralCharacter.SUB, R.color.c2),
                new Element("E", R.color.c3),
                new Element("1", R.color.c3),
                new Element("2", R.color.c3),
                new Element("3", R.color.c3),
                new Element(GeneralCharacter.ADD, R.color.c2),
                new Element("F", R.color.c3),
                new Element(GeneralCharacter.ADD_AND_SUB, R.color.c3),
                new Element("0", R.color.c3),
                new Element(GeneralCharacter.ANS, R.color.c3),
                new Element(GeneralCharacter.EQUAL, R.color.c1)

        };
        listOfProgrammerVertical = Arrays.asList(key);
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
        Element[] key = {
                new Element(GeneralCharacter.SPACE, R.color.c3),
                new Element(GeneralCharacter.CE, R.color.c2),
                new Element(GeneralCharacter.DEL, R.color.c2),
                new Element("7", R.color.c3),
                new Element("8",R.color.c3),
                new Element("9", R.color.c3),
                new Element("4",R.color.c3),
                new Element("5", R.color.c3),
                new Element("6",R.color.c3),
                new Element("1", R.color.c3),
                new Element("2",R.color.c3),
                new Element("3", R.color.c3),
                new Element(GeneralCharacter.SPACE,R.color.c3),
                new Element("0", R.color.c3),
                new Element(GeneralCharacter.POINT,R.color.c3)
        };
        listOfConverterNoSub = Arrays.asList(key);
    }

    private static void initialConverterSub()
    {
        listOfConverterSub = new ArrayList<>();
        Element[] key = {
                new Element(GeneralCharacter.SPACE, R.color.c3),
                new Element(GeneralCharacter.CE, R.color.c2),
                new Element(GeneralCharacter.DEL, R.color.c2),
                new Element("7", R.color.c3),
                new Element("8",R.color.c3),
                new Element("9", R.color.c3),
                new Element("4",R.color.c3),
                new Element("5", R.color.c3),
                new Element("6",R.color.c3),
                new Element("1", R.color.c3),
                new Element("2",R.color.c3),
                new Element("3", R.color.c3),
                new Element(GeneralCharacter.ADD_AND_SUB,R.color.c3),
                new Element("0", R.color.c3),
                new Element(GeneralCharacter.POINT,R.color.c3)
        };
        listOfConverterNoSub = Arrays.asList(key);
    }


}
