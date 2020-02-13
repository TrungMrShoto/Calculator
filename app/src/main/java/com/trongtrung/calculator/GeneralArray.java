package com.trongtrung.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by NguyenTrongTrung on 19 January 2020
 */
public class GeneralArray {
    private static List<Element> listOfConverterSub;
    private static List<Element> listOfStandardVertical;
    private static List<Element> listOfConverterSubHorizontal;

    public static List<Element> getListOfConverterSubHorizontal()
    {
        if (listOfConverterSubHorizontal == null) initialConverterSubHorizontal();
        return listOfConverterSubHorizontal;
    }

    public static List<Element> getListOfStandardVertical() {
        if (listOfStandardVertical == null) initialStandardVertical();
        return listOfStandardVertical;
    }

    public static List<Element> getListOfConverterSub()
    {
        if (listOfConverterSub == null ) initialConverterSub();
        return listOfConverterSub;
    }

    private GeneralArray(){}


    private static void initialStandardVertical(){
        listOfStandardVertical = new ArrayList<>();
        Element[] key = {
                new Element("7", R.color.c3),
                new Element("8", R.color.c3),
                new Element("9", R.color.c3),
                new Element(GeneralCharacter.CE, R.color.c2),
                new Element(GeneralCharacter.DEL, R.color.c2),
                new Element("4", R.color.c3),
                new Element("5", R.color.c3),
                new Element("6", R.color.c3),
                new Element(GeneralCharacter.MUL, R.color.c2),
                new Element(GeneralCharacter.DIV, R.color.c2),
                new Element("1", R.color.c3),
                new Element("2", R.color.c3),
                new Element("3", R.color.c3),
                new Element(GeneralCharacter.ADD, R.color.c2),
                new Element(GeneralCharacter.SUB, R.color.c2),
                new Element(GeneralCharacter.ADD_AND_SUB, R.color.c3),
                new Element("0", R.color.c3),
                new Element(GeneralCharacter.POINT,R.color.c3),
                new Element(GeneralCharacter.ANS, R.color.c2),
                new Element(GeneralCharacter.EQUAL, R.color.c1)

        };
        listOfStandardVertical = Arrays.asList(key);
    }



    private static void initialConverterSub()
    {
        listOfConverterSub = new ArrayList<>();
        Element[] key = {
                new Element(GeneralCharacter.SPACE, R.color.c2),
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
        listOfConverterSub = Arrays.asList(key);
    }

    private static void initialConverterSubHorizontal()
    {
        listOfConverterSubHorizontal = new ArrayList<>();
        Element[] key = {
                new Element("7", R.color.c3),
                new Element("8",R.color.c3),
                new Element("9", R.color.c3),
                new Element(GeneralCharacter.CE, R.color.c2),
                new Element("4",R.color.c3),
                new Element("5", R.color.c3),
                new Element("6",R.color.c3),
                new Element(GeneralCharacter.DEL, R.color.c2),
                new Element("1", R.color.c3),
                new Element("2",R.color.c3),
                new Element("3", R.color.c3),
                new Element(GeneralCharacter.SPACE, R.color.c2),
                new Element(GeneralCharacter.ADD_AND_SUB,R.color.c3),
                new Element("0", R.color.c3),
                new Element(GeneralCharacter.POINT,R.color.c3),
                new Element(GeneralCharacter.SPACE, R.color.c2)
        };
        listOfConverterSubHorizontal = Arrays.asList(key);
    }

}
