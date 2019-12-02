package com.henryschein.dataservices.csvtranslator.parser;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CSVParser {
    public List<List<String>> parseCSV(String csv) {
        List<List<String>> response = new ArrayList();
        return response;
    }
}
