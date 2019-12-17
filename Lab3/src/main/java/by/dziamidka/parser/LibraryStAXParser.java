package by.dziamidka.parser;

import by.dziamidka.MyException;
import by.dziamidka.entity.library.Library;
import by.dziamidka.service.XmlParser;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LibraryStAXParser implements XmlParser<Library> {

    private static final Logger logger = Logger.getLogger(LibraryStAXParser.class);

    private static class LibraryParserSingleton{
        private static final LibraryStAXParser instance = new LibraryStAXParser();
    }

    public static LibraryStAXParser getInstance(){
        return LibraryParserSingleton.instance;
    }

    @Override
    public List<Library> getDataFromXmlPath(String path) throws MyException {
        List<Library> libriaries = new ArrayList<>();
        Library currLib = null;
        String tagContent = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        try {
            reader = factory.createXMLStreamReader(new FileInputStream(path));
            while(reader.hasNext()){
                int event = reader.next();
                switch(event){
                    case XMLStreamConstants.START_ELEMENT:
                        if ("library".equals(reader.getLocalName())){
                            currLib = new Library();
                            currLib.setId(Integer.parseInt(reader.getAttributeValue(0)));
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        switch(reader.getLocalName()){
                            case "library":
                                libriaries.add(currLib);
                                break;
                            case "libraryTitle":
                                currLib.setTitle(tagContent);
                                break;
                            case "creationLibDate":
                                currLib.setCreationDate(tagContent);
                                break;
                        }
                        break;

                }

            }
        } catch (FileNotFoundException | XMLStreamException e) {
            logger.error(e.getMessage());
            throw new MyException(e.getMessage());
        }
        return libriaries;
    }
}
