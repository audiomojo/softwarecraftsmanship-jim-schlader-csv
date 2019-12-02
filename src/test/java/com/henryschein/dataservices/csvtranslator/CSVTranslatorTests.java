package com.henryschein.dataservices.csvtranslator;

import com.henryschein.dataservices.csvtranslator.translator.CSVTranslator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;
import static org.junit.Assert.assertEquals;

public class CSVTranslatorTests {


    @Test
    public void Test_TranslateCSVData_EmptyString(){
        CSVTranslator translator = new CSVTranslator();
        List<List<String>> testList = new ArrayList<>();
        List<String> text = new ArrayList<>();
        text.add("");
        testList.add(text);

        assertEquals("empty string should return empty brackets", translator.translateCSV(unmodifiableList(testList)), "[]\n");

    }

    @Test
    public void Test_TranslateCSVData_SingleLine(){
        CSVTranslator translator = new CSVTranslator();
        List<List<String>> testList = new ArrayList<>();
        List<String> text = new ArrayList<>();
        text.add(" Some text ");
        testList.add(text);

        assertEquals("single line of text should map to itself inside brackets", translator.translateCSV(unmodifiableList(testList)), "[Some text]\n");
    }

    @Test
    public void Test_Multiple_Items_in_List(){
        CSVTranslator translator = new CSVTranslator();
        List<List<String>> testList = new ArrayList<>();
        List<String> text = new ArrayList<>();
        text.add(" First ");
        text.add(" Second ");
        text.add(" Third ");
        testList.add(text);
        String outcome = "[First][Second][Third]\n";

        assertEquals("Multiple items in list should each come out inside brackets", translator.translateCSV(testList), outcome);
    }

    @Test
    public void Test_Multiple_Lines_with_Multiple_Items(){
        CSVTranslator translator = new CSVTranslator();
        List<List<String>> testList = new ArrayList<>();
        List<String> text1 = new ArrayList<>();
        List<String> text2 = new ArrayList<>();
        List<String> text3 = new ArrayList<>();
        text1.add("First");
        text1.add("Second");
        text1.add("Third");
        text2.add("First");
        text2.add("Second");
        text2.add("Third");
        text3.add("First");
        text3.add("Second");
        text3.add("Third");
        testList.add(text1);
        testList.add(text2);
        testList.add(text3);
        String outcome = "[First][Second][Third]\n[First][Second][Third]\n[First][Second][Third]\n";

        assertEquals("Multiple lines with multiple items translates correctly", translator.translateCSV(testList), outcome);
    }
    }


