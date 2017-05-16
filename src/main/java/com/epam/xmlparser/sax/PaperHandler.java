package com.epam.xmlparser.sax;

import com.epam.xmlparser.entity.Chars;
import com.epam.xmlparser.entity.Entertaining;
import com.epam.xmlparser.entity.Paper;
import com.epam.xmlparser.entity.Science;
import com.epam.xmlparser.type.EntertainingType;
import com.epam.xmlparser.type.PaperEnum;
import com.epam.xmlparser.type.PaperType;
import com.epam.xmlparser.type.ScienceType;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class PaperHandler extends DefaultHandler {
    private Set<Paper> papers;
    private Paper current = null;
    private PaperEnum currentEnum = null;
    private EnumSet<PaperEnum> withText;


    public PaperHandler() {
        papers = new HashSet<Paper>();
        withText = EnumSet.range(PaperEnum.COLORED, PaperEnum.SUBSCRIBE_INDEX);
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {

        if ("entertaining".equals(localName)) {
            Entertaining en = new Entertaining();
            en.setType(PaperType.getEnum(attrs.getValue(0)));
            en.setTitle(attrs.getValue(1));
            if (attrs.getLength() == 3) {
                en.setTheme(EntertainingType.getEnum(attrs.getValue(2)));
            }

            current = en;

        } else if ("science".equals(localName)) {
            Science sc = new Science();
            sc.setType(PaperType.getEnum(attrs.getValue(0)));
            sc.setTitle(attrs.getValue(1));
            if (attrs.getLength() == 3) {
                sc.setTheme(ScienceType.getEnum(attrs.getValue(2)));
            }
            current = sc;
        } else {
            PaperEnum temp = PaperEnum.getEnum(localName);
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("entertaining".equals(localName) || "science".equals(localName)) {
            papers.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null && current != null) {
            switch (currentEnum) {
                case COLORED:
                    current.setColored(Boolean.parseBoolean(s));
                    break;
                case MONTHLY:
                    current.setMonthly(Boolean.parseBoolean(s));
                    break;
                case CHARS:
                    current.setChars(new Chars());
                    break;
                case GLOSSY:
                    current.getChars().setGlossy(Boolean.parseBoolean(s));
                    break;
                case PAGES:
                    current.getChars().setPageNumber(Integer.parseInt(s));
                    break;
                case SUBSCRIBE_INDEX:
                    current.getChars().setSubscribeIndex(Integer.parseInt(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }

}
