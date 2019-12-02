package com.henryschein.dataservices.csvtranslator.translator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CSVTranslator {
    public String translateCSV(List<List<String>> parsedCSV) {
        StringBuilder response = new StringBuilder();
        return response.toString();
    }
}
