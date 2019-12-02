package com.henryschein.dataservices.csvtranslator.services;

import com.henryschein.dataservices.csvtranslator.parser.CSVParser;
import com.henryschein.dataservices.csvtranslator.translator.CSVTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CSVTranslatorService {

    @Autowired
    CSVParser csvParser;

    @Autowired
    CSVTranslator csvTranslator;

    public String translateCSV(String csv) {
        List<List<String>> parsedCSV = csvParser.parseCSV(csv);
        return csvTranslator.translateCSV(parsedCSV);
    }
}
