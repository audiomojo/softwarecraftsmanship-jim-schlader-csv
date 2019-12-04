package com.henryschein.dataservices.csvtranslator;

import com.henryschein.dataservices.csvtranslator.parser.CSVParser;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CSVParserTests {
    private final StringBuilder case1;
    private final StringBuilder case2;
    private CSVParser parser;
    private List<String> inner;
    private List<List<String>> outer;

    public CSVParserTests() {
        case1 = new StringBuilder();
        String[] text1 = {"\"Prescott, Zeke\"", "542-51-6641", "21", "801-555-2134", "\"Opratory=2,PCP=1\""};
        for (String string : text1) {
            case1.append(string);
            case1.append(",");
        }
        case2 = new StringBuilder();
        for (String string: text1) {
            case2.append(string);
            case2.append(",");
        }
        case2.append("\r\n");
        String[] text2 = {"\"Goldstein, Bucky\"", "635-45-1254", "42", "435-555-1541", "\"Opratory=1,PCP=1\""};
        for (String string: text2) {
            case2.append(string);
            case2.append(",");
        }
        case2.append("\n");
        String[] text3 = {"\"Vox, Bono\"", "414-45-1475", "51", "801-555-2100", "\"Opratory=3,PCP=2\""};
        for (String string: text3) {
            case2.append(string);
            case2.append(",");
        }

    }

    @Before
    public void setup(){
        parser = new CSVParser();
        inner = new ArrayList<>();
        outer = new ArrayList<>();
    }

    @Test
    public void Test_TranslateCSVData_EmptyString(){
        String test = "";
        inner.add(test);
        outer.add(inner);
        assertEquals("handles empty string", outer , parser.parseCSV(test));
    }

    @Test
    public void Test_TranslateCSVData_NoCommas(){
        String test = "abcde";
        inner.add(test);
        outer.add(inner);
        String output = outer.get(0).get(0);
        assertEquals("handles a single string with no comma separators", test, output);
    }

    @Test
    public void Test_TranslateCSVData_Commas(){
        inner.add(case1.toString());
        outer.add(inner);
        List<List<String>> parsedResult = parser.parseCSV(case1.toString());
        assertEquals("Splits up a single CSV line into a list of strings at the comma", 5, parsedResult.get(0).size());
    }

    @Test
    public void Test_TranslateCSVData_Commas_CRLN(){
        // the input string has carriage return followed by new line \r\n
        inner.add(case2.toString());
        outer.add(inner);
        List<List<String>> parsedResult = parser.parseCSV(case2.toString());
        assertEquals("parses correctly at the new line character", 3 , parsedResult.size());
    }

    @Test
    public void Test_TranslateCSVData_Commas_LN(){
        // input string just has new line character \n
    }

    @Test
    public void Test_TranslateCSVData_Commas_LN_QuotedText(){
        // checks for multiple lines with quoted text inside the rows
        inner.add(case2.toString());
        outer.add(inner);
        String case3 = "\"Vox, Bono\", 414-45-1475, 51, 801-555-2100, \"Opratory=3, PCP=2\"";
        List<List<String>> parsedResult = parser.parseCSV(case3);
        assertEquals("parser removes any quotation marks from strings", "Vox, Bono" ,parsedResult.get(0).get(0) );
    }

    @Test
    public void Test_TranslateCSVData_Commas_LN_QuotedText_InternalComma(){
        inner.add(case2.toString());
        outer.add(inner);
        List<List<String>> parsedResult = parser.parseCSV(case2.toString());
        assertEquals("keeps internal commas when appropriate", "Prescott, Zeke", parsedResult.get(0).get(0));
        assertEquals("keeps internal commas when appropriate", "Goldstein, Bucky", parsedResult.get(1).get(0) );
        assertEquals("keeps internal commas when appropriate", "Vox, Bono", parsedResult.get(2).get(0));
        assertEquals("keeps internal commas when appropriate", "Opratory=2,PCP=1", parsedResult.get(0).get(4));
        assertEquals("keeps internal commas when appropriate", "Opratory=1,PCP=1" ,parsedResult.get(1).get(4) );
        assertEquals("keeps internal commas when appropriate", "Opratory=3,PCP=2" ,parsedResult.get(2).get(4) );
    }

}
