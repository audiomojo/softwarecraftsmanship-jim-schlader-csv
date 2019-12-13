package com.henryschein.dataservices.csvtranslator.csvtranslators;

import com.henryschein.dataservices.csvtranslator.interfaces.Translator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("bracket")
public class BracketTranslator implements Translator {

    @Override
    public String Translate(List<List<String>> source) {
        StringBuilder response = new StringBuilder();

        source.forEach(list -> {
            list.forEach(text-> response.append("[").append(text.trim()).append("]"));
            response.append("\n");
        });

        return response.toString();
    }
}
