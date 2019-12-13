package com.henryschein.dataservices.csvtranslator;

import com.henryschein.dataservices.csvtranslator.interfaces.Translator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CsvtranslatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsvtranslatorApplication.class, args);
    }

}
