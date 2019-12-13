package com.henryschein.dataservices.csvtranslator.services;

import com.henryschein.dataservices.csvtranslator.TranslatorScanner;
import com.henryschein.dataservices.csvtranslator.interfaces.Translator;
import com.henryschein.dataservices.csvtranslator.parser.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
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
    TranslatorScanner translatorScanner;

    public String translateCSV(String csv, String type) {

        List<List<String>> parsedCSV = csvParser.parseCSV(csv);
        Set<String> keys = translatorScanner.getTranslatorMap().keySet();
        if(!keys.contains(type)) type = "bracket";
        return translatorScanner.getTranslatorMap().get(type).Translate(parsedCSV);
    }
}
