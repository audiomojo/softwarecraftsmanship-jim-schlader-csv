package com.henryschein.dataservices.csvtranslator.interfaces;

import java.util.List;

public interface Translator {
    public String Translate(List<List<String>> source);
}
