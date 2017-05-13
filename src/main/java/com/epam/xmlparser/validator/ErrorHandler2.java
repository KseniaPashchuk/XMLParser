package com.epam.xmlparser.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ErrorHandler2 extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(ErrorHandler2.class);
    private boolean isValid = true;

    public ErrorHandler2() {
    }

    public void warning(SAXParseException e) {
        LOGGER.log(Level.WARN, getLineAddress(e) + "-" + e.getMessage());
    }

    public void error(SAXParseException e) {
        isValid = false;
        LOGGER.log(Level.ERROR, getLineAddress(e) + " - " + e.getMessage());
    }

    public void fatalError(SAXParseException e) {
        isValid = false;
        LOGGER.log(Level.FATAL, getLineAddress(e) + " - " + e.getMessage());
    }

    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }

    public boolean isValid() {
        return isValid;
    }
}
