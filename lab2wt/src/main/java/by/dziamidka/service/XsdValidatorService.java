package by.dziamidka.service;

import by.dziamidka.exception.XsdValidationException;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;


public class XsdValidatorService {


    public static XsdValidatorService getInstance()
    {
        return ValidatorSingleton.Instance;
    }

    private static class  ValidatorSingleton
    {
        static final XsdValidatorService Instance = new XsdValidatorService();
    }


    private final Logger logger = Logger.getLogger(XsdValidatorService.class);

    public void Validate(File xmlFile, File xsdFile) throws XsdValidationException {
        try
        {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
            throw new XsdValidationException(e.getMessage());
        }
        catch (org.xml.sax.SAXException e)
        {
            logger.error(xmlFile.getName() + e.getMessage());
            throw new XsdValidationException(xmlFile.getName() + e.getMessage());
        }
    }
}
