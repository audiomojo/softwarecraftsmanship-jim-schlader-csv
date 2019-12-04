package com.henryschein.dataservices.csvtranslator.parser;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CSVParser {

    public List<List<String>> parseCSV(String csv) {
        List<List<String>> response = new ArrayList<>();
        List<String> responseList;

        String carriageFree = csv.replace("\r", "" );
        responseList = Arrays.asList(carriageFree.split("\n"));

        for (String s : responseList) {
            List<String> current =
                    new ArrayList<>(Arrays.asList(replaceInternalQuotes(s).split(",")));
            response.add(prepareOutput(current));
        }

        return response;
    }

    private String replaceInternalQuotes(String text){
        boolean inQuotes = false;
        char[] input = text.toCharArray();
        StringBuilder output = new StringBuilder();

        for (char c : input) {
            if (inQuotes && c == ',') {
                output.append("@@@");
            } else {
                output.append(c);
            }
            if (c == '"') {
                inQuotes = !inQuotes;
            }
        }

        return output.toString();
    }

    private List<String> prepareOutput (List<String> strings){
       List<String> output = Collections.unmodifiableList(strings
               .stream()
               .map(item -> item.replace('"', ' ').trim())
               .map(item-> item.replace("@@@", ","))
               .collect(Collectors.toList()));
        return output;
    }
}
