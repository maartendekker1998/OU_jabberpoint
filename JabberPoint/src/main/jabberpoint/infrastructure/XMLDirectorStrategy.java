package main.jabberpoint.infrastructure;

import main.jabberpoint.domain_service.BuilderService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLDirectorStrategy implements DirectorStrategy {

    /** namen van xml tags of attributen */
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";

    /** tekst van messages */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";

    private String getTitle(Element element, String tagName) {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();
    }

    @Override
    public void construct(BuilderService builderService, String filename) throws IOException {

        try {
            int slideNumber, itemNumber, max = 0, maxItems = 0;

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename)); // maak een JDOM document
            Element doc = document.getDocumentElement();;

            System.out.println(getTitle(doc, SHOWTITLE));

            builderService.newSlideShow();

            NodeList slides = doc.getElementsByTagName(SLIDE);
            max = slides.getLength();
            for (slideNumber = 0; slideNumber < max; slideNumber++) {

                Element xmlSlide = (Element) slides.item(slideNumber);

                builderService.newSlide();
                builderService.addSlideTitle(getTitle(xmlSlide, SLIDETITLE));

                NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
                maxItems = slideItems.getLength();

                for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
                    Element item = (Element) slideItems.item(itemNumber);

                    int indentation = 1; // default
                    NamedNodeMap attributes = item.getAttributes();
                    String indentationText = attributes.getNamedItem(LEVEL).getTextContent();
                    if (indentationText != null) {
                        try {
                            indentation = Integer.parseInt(indentationText);
                        }
                        catch(NumberFormatException x) {
                            System.err.println(NFE);
                        }
                    }

                    String type = attributes.getNamedItem(KIND).getTextContent();
                    if (TEXT.equals(type)) {
                        builderService.addTextContent(indentation, item.getTextContent());
                    }
                    else if (IMAGE.equals(type)) {
                        builderService.addImageContent(indentation, item.getTextContent());
                    }
                    else{
                        System.err.println(UNKNOWNTYPE);
                    }
                }
                builderService.addSlide();
            }
        }
        catch (IOException iox) {
            System.err.println(iox.toString());
        }
        catch (SAXException sax) {
            System.err.println(sax.getMessage());
        }
        catch (ParserConfigurationException pcx) {
            System.err.println(PCE);
        }
    }
}
