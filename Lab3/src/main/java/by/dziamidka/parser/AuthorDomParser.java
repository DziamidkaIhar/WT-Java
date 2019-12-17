package by.dziamidka.parser;

import by.dziamidka.MyException;
import by.dziamidka.entity.author.Author;
import by.dziamidka.service.DomParser;
import by.dziamidka.service.XmlParser;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDomParser implements XmlParser<Author>, DomParser {

    private static final Logger logger = Logger.getLogger(AuthorDomParser.class);

    private static class AuthorDomSingleton{
        private static final AuthorDomParser instance = new AuthorDomParser();
    }

    public static AuthorDomParser getInstance(){
        return AuthorDomSingleton.instance;
    }

    @Override
    public List<Author> getDataFromXmlPath(String path) throws MyException {
        List<Author> authors;
        try {
            Document document = createXmlDocument(path);
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("author");
            authors = new ArrayList<>(nodeList.getLength());
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node instanceof Element){
                    Author author = new Author();
                    //var x =node.getAttributes().getNamedItem("id").getNodeValue();
                    author.setId(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue()));

                    NodeList childNodes = node.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node cNode = childNodes.item(j);
                        //Identifying the child tag of employee encountered.
                        if (cNode instanceof Element) {
                            String content = cNode.getLastChild().
                                    getTextContent().trim();
                            switch (cNode.getNodeName()) {
                                case "surname":
                                    author.setSurname(content);
                                    break;
                                case "name":
                                    author.setName(content);;
                                    break;
                            }
                        }
                    }
                    authors.add(author);
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            logger.error(path + " not found");
            throw new MyException(path + " not found");
        }
        return authors;
    }


}
