package main.jabberpoint.infrastructure;

import main.jabberpoint.domain.BulletList;
import main.jabberpoint.domain.Content;
import main.jabberpoint.domain.Image;
import main.jabberpoint.domain.Text;
import main.jabberpoint.domain_service.BuilderService;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLDirectorStrategy implements DirectorStrategy {

    /** namen van xml tags of attributen */
    protected static final String SHOWTITLE = "showtitle";
    protected static final String TRANSITIONS = "transitions";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";
    protected static final String BULLETLIST = "bulletlist";

    /** tekst van messages */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";

    private String getTitle(Element element, String tagName) {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();
    }

    public static List<Element> getChildrenByTagName(Element parent, String name) {
        List<Element> nodeList = new ArrayList<Element>();
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE &&
                    name.equals(child.getNodeName())) {
                nodeList.add((Element) child);
            }
        }
        return nodeList;
    }

    @Override
    public void construct(BuilderService builderService, String filename) {

        try {
            int slideNumber, itemNumber, max = 0, maxItems = 0;

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            Element doc = document.getDocumentElement();;


            builderService.newSlideShow();

            NodeList slides = doc.getElementsByTagName(SLIDE);
            max = slides.getLength();
            for (slideNumber = 0; slideNumber < max; slideNumber++) {

                Element xmlSlide = (Element) slides.item(slideNumber);

                builderService.newSlide();
                builderService.addSlideTitle(getTitle(xmlSlide, SLIDETITLE));

                NamedNodeMap slideAttributes = xmlSlide.getAttributes();
                String transitions = slideAttributes.getNamedItem(TRANSITIONS).getTextContent();

                if (transitions.equals("true")){
                    builderService.setTransitions(true);
                }else{
                    builderService.setTransitions(false);
                }

                List<Element> slideItems = getChildrenByTagName(xmlSlide, ITEM);

                for (Element e : slideItems ) {


                    int indentation = 1; // default
                    NamedNodeMap attributes = e.getAttributes();
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
                        builderService.addTextContent(indentation, e.getTextContent());
                    }
                    else if (IMAGE.equals(type)) {
                        builderService.addImageContent(indentation, e.getTextContent());
                    }
                    else if (BULLETLIST.equals(type)) {
                        builderService.addBulletList(indentation, parseBulletList(e));
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

    private List<Content> parseBulletList(Element element){

        List<Content> bulletList = new ArrayList<>();

        List<Element> slideItems = getChildrenByTagName(element, ITEM);

        for (Element e : slideItems ) {

            int indentation = 1; // default
            NamedNodeMap attributes = e.getAttributes();
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
                bulletList.add(new Text(indentation, e.getTextContent()));
            }
            else if (IMAGE.equals(type)) {
                bulletList.add(new Image(indentation, e.getTextContent()));
            }
            else if (BULLETLIST.equals(type)) {
                bulletList.add(new BulletList(indentation, parseBulletList(e)));
            }
            else{
                System.err.println(UNKNOWNTYPE);
            }
        }
        return bulletList;
    }
}
