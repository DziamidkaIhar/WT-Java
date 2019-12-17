package by.dziamidka.parser;

import by.dziamidka.MyException;
import by.dziamidka.entity.publisher.Publisher;
import by.dziamidka.service.DomParser;
import by.dziamidka.service.XmlParser;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDomParser implements XmlParser<Publisher>, DomParser {
    private static final Logger logger = Logger.getLogger(PublisherDomParser.class);

    private static class PublisherDomSingleton{
        private static final PublisherDomParser instance = new PublisherDomParser();
    }

    public static PublisherDomParser getInstance(){
        return PublisherDomSingleton.instance;
    }

    @Override
    public List<Publisher> getDataFromXmlPath(String path) throws MyException {
        List<Publisher> publishers;
        try {
            Document document = createXmlDocument(path);
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("publisher");
            publishers = new ArrayList<>(nodeList.getLength());
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node instanceof Element){
                    Publisher publisher = new Publisher();
                    publisher.setId(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue()));
                    NodeList childNodes = node.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node cNode = childNodes.item(j);
                        if (cNode instanceof Element) {
                            String content = cNode.getLastChild().getTextContent().trim();
                            switch (cNode.getNodeName()) {
                                case "publisherTitle":
                                    publisher.setTitle(content);
                                    break;
                            }
                        }
                    }
                    publishers.add(publisher);
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            logger.error(path + " not found");
            throw new MyException(path + " not found");
        }
        return publishers;
    }
}
