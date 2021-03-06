package com.henryschein.dataservices.csvtranslator.csvtranslators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henryschein.dataservices.csvtranslator.interfaces.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


@Component
public class SpanishTranslator implements Translator {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String Translate(List<List<String>> source){

        StringBuilder returnString = new StringBuilder();

        ObjectMapper mapper = new ObjectMapper();

        source.forEach(list->list.forEach(text-> {
            String
                    result = null;
            try {
                result = restTemplate.exchange(
                "https://systran-systran-platform-for-language-processing-v1.p.rapidapi" +
                        ".com/translation/text/translate?source=en&target=es&input="
                        + URLEncoder.encode(text, "UTF-8"),
                HttpMethod.GET, getEntity(), String.class).getBody();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try{
                Map<String, List<Map<String, String>>> stringListMap = mapper.readValue(result, Map.class);
                System.out.println(stringListMap.get("outputs").get(0).get("output"));
                returnString.append(stringListMap.get("outputs").get(0).get("output"));
            } catch(IOException error){
                error.printStackTrace();
            }

        }));

        return returnString.toString();
    }

    private HttpEntity getEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-rapidapi-host", "systran-systran-platform-for-language-processing-v1.p.rapidapi.com");
        headers.add("x-rapidapi-key",  "hiVJaOl7eamsh6lVNbVigZtUYm2up1AJm9Qjsn2pV7pyFHLVjC");
        headers.add("accept", "application/json");

        return new HttpEntity(headers);
    }

    class ResponseList {
        private List<SpanishResponse> outputs;
    }

    class SpanishResponse {
        private String output;
        Object stats;

    }

}
