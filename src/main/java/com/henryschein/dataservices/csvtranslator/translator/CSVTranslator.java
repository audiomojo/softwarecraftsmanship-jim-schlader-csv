package com.henryschein.dataservices.csvtranslator.translator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CSVTranslator {
    public String translateCSV(List<List<String>> parsedCSV) {
        StringBuilder response = new StringBuilder();
        parsedCSV.forEach(list -> {
            list.forEach(text-> response.append("[").append(text.trim()).append("]"));
            response.append("\n");
        });
        return response.toString();
    }
}
