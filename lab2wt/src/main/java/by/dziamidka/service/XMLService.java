package by.dziamidka.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class XMLService {

    public static XMLService getInstance()
    {
        return XMLService.XMLServiceSingleton.Instance;
    }

    private static class  XMLServiceSingleton
    {
        static final XMLService Instance = new XMLService();
    }

    public String InputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
