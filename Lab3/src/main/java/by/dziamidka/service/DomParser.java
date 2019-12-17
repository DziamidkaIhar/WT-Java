package by.dziamidka.service;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public interface DomParser {
    default Document createXmlDocument(String path) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(path);
        Document document;
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = documentBuilder.parse(file);
        document.normalize();
        return document;
    }
}
