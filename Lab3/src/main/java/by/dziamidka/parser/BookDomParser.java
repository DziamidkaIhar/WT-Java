package by.dziamidka.parser;

import by.dziamidka.MyException;
import by.dziamidka.entity.book.Book;
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

public class BookDomParser implements XmlParser<Book>, DomParser {

    private static final Logger logger = Logger.getLogger(BookDomParser.class);

    private static class BookDomSingleton{
        private static final BookDomParser instance = new BookDomParser();
    }

    public static BookDomParser getInstance(){
        return BookDomSingleton.instance;
    }

    @Override
    public List<Book> getDataFromXmlPath(String path) throws MyException {
        List<Book> books;
        try {
            Document document = createXmlDocument(path);
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("book");
            books = new ArrayList<>(nodeList.getLength());
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node instanceof Element){
                    Book book = new Book();
                    book.setId(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue()));

                    NodeList childNodes = node.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node cNode = childNodes.item(j);
                        //Identifying the child tag of employee encountered.
                        if (cNode instanceof Element) {
                            String content = cNode.getLastChild().
                                    getTextContent().trim();
                            switch (cNode.getNodeName()) {
                                case "title":
                                    book.setTitle(content);
                                    break;
                                case "authorId":
                                    book.setAuthorId(Integer.parseInt(content));
                                    break;
                                case "yearOfPublication":
                                    book.setYearOfPublication(content);
                                    break;
                                case "publisherId":
                                    book.setPublisherId(Integer.parseInt(content));
                                    break;
                                case "genreId":
                                    book.setGenreId(Integer.parseInt(content));
                                    break;
                            }
                        }
                    }
                    books.add(book);
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            logger.error(path + " not found");
            throw new MyException(path + " not found");
        }
        return books;
    }
}
