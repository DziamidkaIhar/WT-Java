package by.dziamidka.service;

import by.dziamidka.MyException;
import java.util.List;

public interface XmlParser<T> {
    List<T> getDataFromXmlPath(String string) throws MyException;
}
