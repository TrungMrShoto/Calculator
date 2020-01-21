package com.trongtrung.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenTrongTrung on 19 January 2020
 */
public class GeneralArray {
    private static List<String> listOfConverterNoSub;
    private static List<String> listOfConverterSub;

    public GeneralArray() {
        if (listOfConverterNoSub == null)
        {
            initialConvertNoSub();
        }
        if (listOfConverterSub == null)
        {
            initialConvertSub();
        }
    }

    private void initialConvertNoSub()
    {
        listOfConverterNoSub = new ArrayList<>();
        listOfConverterNoSub.add(GeneralCharacter.SPACE);
        listOfConverterNoSub.add(GeneralCharacter.CE);
        listOfConverterNoSub.add(GeneralCharacter.DEL);
        listOfConverterNoSub.add("7");
        listOfConverterNoSub.add("8");
        listOfConverterNoSub.add("9");
        listOfConverterNoSub.add("4");
        listOfConverterNoSub.add("5");
        listOfConverterNoSub.add("6");
        listOfConverterNoSub.add("1");
        listOfConverterNoSub.add("2");
        listOfConverterNoSub.add("3");
        listOfConverterNoSub.add(GeneralCharacter.SPACE);
        listOfConverterNoSub.add("0");
        listOfConverterNoSub.add(GeneralCharacter.POINT);
    }

    private void initialConvertSub()
    {
        listOfConverterSub = new ArrayList<>();
        listOfConverterSub.add(GeneralCharacter.SPACE);
        listOfConverterSub.add(GeneralCharacter.CE);
        listOfConverterSub.add(GeneralCharacter.DEL);
        listOfConverterSub.add("7");
        listOfConverterSub.add("8");
        listOfConverterSub.add("9");
        listOfConverterSub.add("4");
        listOfConverterSub.add("5");
        listOfConverterSub.add("6");
        listOfConverterSub.add("1");
        listOfConverterSub.add("2");
        listOfConverterSub.add("3");
        listOfConverterSub.add(GeneralCharacter.ADD_AND_SUB);
        listOfConverterSub.add("0");
        listOfConverterSub.add(GeneralCharacter.POINT);
    }

    public List<String> getListOfConverterNoSub()
    {
        return listOfConverterNoSub;
    }

    public List<String> getListOfConverterSub()
    {
        return listOfConverterSub;
    }
}
