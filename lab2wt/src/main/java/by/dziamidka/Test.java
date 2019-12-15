package by.dziamidka;

import by.dziamidka.exception.XsdValidationException;
import by.dziamidka.service.XsdValidatorService;
import org.apache.log4j.Logger;


import java.io.File;

public class Test {

    private static final Logger _logger = Logger.getLogger(Test.class);

    private static final String _xmlPath = "src/main/resources/Periodicals.xml";

    private static final String _xsdPath = "src/main/resources/Periodicals.xsd";

    private static XsdValidatorService _xsdValidator = XsdValidatorService.getInstance();

    public static void main(String[] args)
    {
        System.out.println("Start");
        _logger.debug("Start");
        try
        {
            _xsdValidator.Validate(new File(_xmlPath), new File(_xsdPath));
            System.out.println("successful validation");
            _logger.debug("successful validation");
        }
        catch (XsdValidationException e)
        {
            System.out.println("Validation exception");
            _logger.error("xsd schema exception");
        }
        _logger.debug("End");
        System.out.println("Finish");
    }
}
