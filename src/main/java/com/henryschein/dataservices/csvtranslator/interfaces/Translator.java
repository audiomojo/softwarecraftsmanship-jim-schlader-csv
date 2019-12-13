package com.henryschein.dataservices.csvtranslator.interfaces;

import org.springframework.stereotype.Component;

import java.util.List;


public interface Translator {
    String Translate(List<List<String>> source);
}
