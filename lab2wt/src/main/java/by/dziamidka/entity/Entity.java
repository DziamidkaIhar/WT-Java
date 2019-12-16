package by.dziamidka.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public class Entity implements Serializable {
    @JacksonXmlProperty(isAttribute = true)
    private int id;

    public int getId() {
        return id;
    }
}
