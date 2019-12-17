package by.dziamidka.parser;

import by.dziamidka.MyException;
import by.dziamidka.entity.genre.Genre;
import by.dziamidka.service.XmlParser;
import com.sun.tools.javac.jvm.Gen;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenreSaxParser implements XmlParser<Genre> {

    private static final Logger logger = Logger.getLogger(GenreSaxParser.class);

    private static class GenreSaxSingleton{
        private static final GenreSaxParser instance = new GenreSaxParser();
    }

    public static GenreSaxParser getInstance(){
        return GenreSaxSingleton.instance;
    }

    private XMLStreamReader reader;
    private List<Genre> genres;

    @Override
    public List<Genre> getDataFromXmlPath(String path) throws MyException {
        List<Genre> genres =  new ArrayList<>();
        XMLInputFactory xmlInputFactory;
        xmlInputFactory = XMLInputFactory.newFactory();
        InputStream inputStream = null;
        try {
            SAXParserFactory parserFactor = SAXParserFactory.newInstance();
            SAXParser parser = parserFactor.newSAXParser();
            SAXGenreHandler handler = new SAXGenreHandler();
            parser.parse(new File(path), handler);
            genres = handler.genreList;
        }
        catch (ParserConfigurationException | SAXException | IOException e){
            logger.error(e.getMessage());
            throw new MyException(e.getMessage());
        }
        return genres;
    }

}
