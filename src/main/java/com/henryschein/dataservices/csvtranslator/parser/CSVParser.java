package com.henryschein.dataservices.csvtranslator.parser;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CSVParser {
    public List<List<String>> parseCSV(String csv) {
        List<List<String>> response = new ArrayList<>();
        List<String> responseList;

        responseList = Arrays.asList(csv.split("\n"));
        for (String s : responseList) {
            List current = Arrays.asList(s.split(","));
            response.add(current);
        }
        return response;
    }
}
