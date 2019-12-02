package com.henryschein.dataservices.csvtranslator.translator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CSVTranslator {
    public String translateCSV(List<List<String>> parsedCSV) {
        StringBuilder response = new StringBuilder();
        for (List<String> strings : parsedCSV) {
            for (int j = 0; j < strings.size(); j++) {
                response.append("[").append(strings.get(j).trim()).append("]");
                if (j == strings.size() - 1 ) {
                    response.append("\n");
                }
            }
        }
        return response.toString();
    }
}
