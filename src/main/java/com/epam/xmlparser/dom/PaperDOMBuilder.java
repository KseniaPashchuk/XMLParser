package com.epam.xmlparser.dom;

import com.epam.xmlparser.entity.Chars;
import com.epam.xmlparser.entity.Entertaining;
import com.epam.xmlparser.entity.Paper;
import com.epam.xmlparser.entity.Science;
import com.epam.xmlparser.type.EntertainingType;
import com.epam.xmlparser.type.PaperType;
import com.epam.xmlparser.type.ScienceType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PaperDOMBuilder {
    private static final Logger LOGGER = LogManager.getLogger(PaperDOMBuilder.class);
    private Set<Paper> papers;
    private DocumentBuilder docBuilder;

    public PaperDOMBuilder() {
        this.papers = new HashSet<Paper>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.ERROR, "Parsing configuration error: " + e);

        }
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public void buildPapersSet(String fileName) {
        Document doc;
        try {
// parsing XML-документа и создание древовидной структуры
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
// получение списка дочерних элементов
            NodeList entertainingList = root.getElementsByTagName("entertaining");
            for (int i = 0; i < entertainingList.getLength(); i++) {
                Element entertainingElement = (Element) entertainingList.item(i);
                Entertaining entertaining = buildEntertaining(entertainingElement);
                papers.add(entertaining);
            }
            NodeList scienceList = root.getElementsByTagName("science");
            for (int i = 0; i < scienceList.getLength(); i++) {
                Element scienceElement = (Element) scienceList.item(i);
                Science science = buildScience(scienceElement);
                papers.add(science);
            }
        } catch (IOException e) {
            LOGGER.log(Level.ERROR,"File error or I/O error: " + e);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR,"Parsing failure: " + e);
        }
    }


    private Entertaining buildEntertaining(Element entertainingElement) {
        Entertaining entertaining = new Entertaining();
        buildPaper(entertainingElement, entertaining);
        entertaining.setTheme(EntertainingType.getEnum(entertainingElement.getAttribute("theme"))); // проверка на null

        return entertaining;
    }

    private Science buildScience(Element scienceElement) {
        Science science = new Science();
        buildPaper(scienceElement, science);
        science.setTheme(ScienceType.getEnum(scienceElement.getAttribute("theme")));//!!!!!!!!!!!!!!!!!!!
        return science;
    }

    private void buildPaper(Element paperElement, Paper paper) {
        paper.setType(PaperType.getEnum(paperElement.getAttribute("type")));
        paper.setTitle(paperElement.getAttribute("title"));
        paper.setColored(Boolean.parseBoolean(getElementTextContent(paperElement, "colored")));
        paper.setColored(Boolean.parseBoolean(getElementTextContent(paperElement, "monthly")));
        paper.setChars(getXMLChars(paperElement));
    }

    private Chars getXMLChars(Element paperElement) {
        Chars chars = new Chars();
        Element charsElement = (Element) paperElement.getElementsByTagName("chars").item(0);
        chars.setGlossy(Boolean.parseBoolean(getElementTextContent(charsElement, "glossy")));
        chars.setPageNumber(Integer.parseInt(getElementTextContent(charsElement, "pages")));
        if (getElementTextContent(charsElement, "subscribe-index") != null) {
            chars.setSubscribeIndex(Integer.parseInt(getElementTextContent(charsElement, "subscribe-index")));
        }
        return chars;
    }

    // получение текстового содержимого тега
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        if (node != null) {
             return node.getTextContent();
        }
        return null;
    }
}

