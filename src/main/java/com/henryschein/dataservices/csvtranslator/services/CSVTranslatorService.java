package com.henryschein.dataservices.csvtranslator.services;

import com.henryschein.dataservices.csvtranslator.interfaces.Translator;
import com.henryschein.dataservices.csvtranslator.parser.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CSVTranslatorService {
    @Autowired
    CSVParser csvParser;
    @Autowired
    Translator bracketTranslator;
    @Autowired
    Translator reverseTranslator;

    private static Map<String, Translator> translators = new HashMap<>();

    public String translateCSV(String csv, String type) {
        translators.putIfAbsent("bracket", bracketTranslator);
        translators.putIfAbsent("reverse", reverseTranslator);
        List<List<String>> parsedCSV = csvParser.parseCSV(csv);
        Set<String> keys = translators.keySet();
        if(!keys.contains(type)) type = "bracket";
        return translators.get(type).Translate(parsedCSV);
    }
}
