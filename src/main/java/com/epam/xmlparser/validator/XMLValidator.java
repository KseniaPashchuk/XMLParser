package com.epam.xmlparser.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private final String SCHEMA_NAME = "input/input2.xsd";

    public boolean validate(String fileName) {
        SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
        File schemaLocation = new File(SCHEMA_NAME);
        //ErrorHandler2 handler = new ErrorHandler2();
        try {
            // создание схемы
            Schema schema = factory.newSchema(schemaLocation);
            // создание валидатора на основе схемы
            Validator validator = schema.newValidator();
            // проверка документа
            Source source = new StreamSource(fileName);
           // validator.setErrorHandler(handler);
            validator.validate(source);
        } catch (IOException ex) {
            LOGGER.log(Level.ERROR, ex.getMessage());
            return false;
        } catch (SAXException ex) {
            LOGGER.log(Level.ERROR,  ex.getMessage());
            return false;
        }
        return true;
    }
}
