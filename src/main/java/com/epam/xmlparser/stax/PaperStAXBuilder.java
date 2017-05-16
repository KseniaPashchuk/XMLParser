package com.epam.xmlparser.stax;

import com.epam.xmlparser.entity.Chars;
import com.epam.xmlparser.entity.Entertaining;
import com.epam.xmlparser.entity.Paper;
import com.epam.xmlparser.entity.Science;
import com.epam.xmlparser.type.EntertainingType;
import com.epam.xmlparser.type.PaperEnum;
import com.epam.xmlparser.type.PaperType;
import com.epam.xmlparser.type.ScienceType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PaperStAXBuilder {
    private static final Logger LOGGER = LogManager.getLogger(PaperStAXBuilder.class);

    private Set<Paper> papers = new HashSet<>();
    private XMLInputFactory inputFactory;

    public PaperStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public void buildPapersSet(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        Paper paper;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (PaperEnum.getEnum(name) == PaperEnum.ENTERTAINING) {
                        paper = buildEntertaining(reader);
                        papers.add(paper);
                    }
                    if (PaperEnum.getEnum(name) == PaperEnum.SCIENCE) {
                        paper = buildScience(reader);
                        papers.add(paper);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            LOGGER.log(Level.ERROR, "StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.ERROR, "File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Entertaining buildEntertaining(XMLStreamReader reader) throws XMLStreamException {
        Entertaining en = new Entertaining();
        String theme;
        if((theme = reader.getAttributeValue(null, PaperEnum.THEME.getValue()))!=null) {
            en.setTheme(EntertainingType.getEnum(theme));
        }
        buildPaper(reader, en);
        return en;

    }

    private Science buildScience(XMLStreamReader reader) throws XMLStreamException {
        Science sc = new Science();
        String theme;
        if((theme = reader.getAttributeValue(null, PaperEnum.THEME.getValue()))!=null) {
            sc.setTheme(ScienceType.getEnum(theme));
        }
        buildPaper(reader, sc);
        return sc;
    }

    private void buildPaper(XMLStreamReader reader, Paper paper) throws XMLStreamException {
        paper.setType(PaperType.getEnum(reader.getAttributeValue(null, PaperEnum.TYPE.getValue())));
        paper.setTitle(reader.getAttributeValue(null, PaperEnum.TITLE.getValue()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PaperEnum.valueOf(name.toUpperCase())) {
                        case MONTHLY:
                            paper.setMonthly(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case COLORED:
                            paper.setColored(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case CHARS:
                            paper.setChars(getXMLChars(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PaperEnum.getEnum(name) ==
                            PaperEnum.ENTERTAINING || PaperEnum.getEnum(name) ==
                            PaperEnum.SCIENCE) {
                        return;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element");
    }

    private Chars getXMLChars(XMLStreamReader reader) throws XMLStreamException {
        Chars chars = new Chars();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PaperEnum.getEnum(name)) {
                        case GLOSSY:
                            chars.setGlossy(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case PAGES:
                            chars.setPageNumber(Integer.parseInt(getXMLText(reader)));
                            break;
                        case SUBSCRIBE_INDEX:
                            chars.setSubscribeIndex(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PaperEnum.getEnum(name) ==
                            PaperEnum.CHARS) {
                        return chars;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Chars");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
