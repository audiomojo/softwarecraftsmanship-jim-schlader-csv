package com.henryschein.dataservices.csvtranslator.controllers;

import com.henryschein.dataservices.csvtranslator.services.CSVTranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CSVTranslatorController {

    @Autowired
    CSVTranslatorService csvTranslatorService;

    @RequestMapping("/translateCSV")
    public String translateCSV(@RequestParam(value="csv") String csv){
        return  csvTranslatorService.translateCSV(csv);
    }
}
