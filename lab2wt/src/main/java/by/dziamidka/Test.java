package by.dziamidka;

import by.dziamidka.exception.DatabaseException;
import by.dziamidka.exception.XsdValidationException;
import by.dziamidka.service.JDBCService;
import by.dziamidka.service.XsdValidatorService;
import org.apache.log4j.Logger;


import java.io.File;

public class Test {

    private static final Logger _logger = Logger.getLogger(Test.class);

    private static final String _xmlPath = "src/main/resources/Periodicals.xml";

    private static final String _xsdPath = "src/main/resources/Periodicals.xsd";

    private static XsdValidatorService _xsdValidator = XsdValidatorService.getInstance();

    private static JDBCService jdbcService = JDBCService.getInstance();

    private static final String DBUser = "test";
    private  static  final String DBPassword = "2281488";
    private  static final  String DBUrl = "jdbc:sqlserver://localhost;DatabaseName=Periodicals";

    public static void main(String[] args)
    {
        System.out.println("Start");
        _logger.debug("Start");
        try
        {
            _xsdValidator.Validate(new File(_xmlPath), new File(_xsdPath));
            jdbcService.init(DBUrl, DBUser, DBPassword);
            System.out.println("successful validation");
            _logger.debug("successful validation");
        }
        catch (XsdValidationException e)
        {
            System.out.println("Validation exception");
            _logger.error("xsd schema exception");
        }
        catch (DatabaseException e)
        {
            System.out.println("Database Exception");
            _logger.error(e.getMessage());
        }
        _logger.debug("End");
        System.out.println("Finish");
    }
}
