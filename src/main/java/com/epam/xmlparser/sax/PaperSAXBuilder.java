package com.epam.xmlparser.sax;

import com.epam.xmlparser.entity.Paper;

import java.util.Set;
import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class PaperSAXBuilder {
    private Set<Paper> papers;
    private PaperHandler sh;
    private XMLReader reader;

    public PaperSAXBuilder() {
        sh = new PaperHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public void buildPapersSet(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        papers = sh.getPapers();
    }

}
