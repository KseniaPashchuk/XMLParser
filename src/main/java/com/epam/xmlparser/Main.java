package com.epam.xmlparser;

import com.epam.xmlparser.dom.PaperDOMBuilder;
import com.epam.xmlparser.stax.PaperStAXBuilder;
import com.epam.xmlparser.validator.XMLValidator;

public class Main {
    public static void main(String[] args) {
//        XMLValidator validator = new XMLValidator();
//        System.out.println(validator.validate("input/input2.xml"));
//        PaperDOMBuilder domBuilder = new PaperDOMBuilder();
//        domBuilder.buildPapersSet("input/input2.xml");
//        System.out.println(domBuilder.getPapers());
        PaperStAXBuilder staxBuilder = new PaperStAXBuilder();
        staxBuilder.buildPapersSet("input/input2.xml");
        System.out.println(staxBuilder.getPapers());

    }
}
