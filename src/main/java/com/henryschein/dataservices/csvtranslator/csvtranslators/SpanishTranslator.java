package com.henryschein.dataservices.csvtranslator.csvtranslators;

import com.henryschein.dataservices.csvtranslator.interfaces.Translator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpanishTranslator implements Translator {
    @Override
    public String Translate(List<List<String>> source){
        return "Gonna translate " + source + " in Spanish!";
    }
}
