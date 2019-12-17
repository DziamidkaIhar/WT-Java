package by.dziamidka.parser;

import by.dziamidka.entity.genre.Genre;
import com.sun.tools.javac.jvm.Gen;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXGenreHandler extends DefaultHandler {
    List<Genre> genreList = new ArrayList<>();
    Genre genre = null;
    String content = null;

    @Override
    //Triggered when the start of tag is found.
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {

        switch(qName){
            //Create a new Employee object when the start tag is found
            case "genre":
                genre = new Genre();
                genre.setId(Integer.parseInt(attributes.getValue("id")));
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        switch(qName){
            //Add the employee to list once end tag is found
            case "genre":
                genreList.add(genre);
                break;
            //For all other end tags the employee has to be updated.
            case "genreTitle":
                genre.setTitle(content);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }
}
