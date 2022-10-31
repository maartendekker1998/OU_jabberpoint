package main.jabberpoint.infrastructure;

import main.jabberpoint.domain.*;
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

    /**
     * names of items and attributes
     */
    private static final String METADATA = "metadata";
    private static final String PRESENTER = "presenter";
    private static final String SHOWTITLE = "showtitle";
    private static final String FONT = "font";
    private static final String FONTSIZE = "fontsize";
    private static final String COLOR = "color";
    private static final String BULLET = "bullet";
    private static final String TRANSITIONS = "transitions";
    private static final String SLIDETITLE = "title";
    private static final String SLIDE = "slide";
    private static final String ITEM = "item";
    private static final String LEVEL = "level";
    private static final String KIND = "kind";
    private static final String TEXT = "text";
    private static final String IMAGE = "image";
    private static final String BULLETLIST = "bulletlist";
    private static final String TRUE = "true";


    /**
     * names of error messages
     */
    private static final String PCE = "Parser Configuration Exception";
    private static final String UNKNOWNTYPE = "Unknown Element type";
    private static final String NFE = "Number Format Exception";

    private boolean recursive = false;

    /**
     * Parses the style attributes and sets them via the builder service
     * @param builderService
     * @param attributes
     */
    private void parseStyles(BuilderService builderService, NamedNodeMap attributes){
        if (attributes.getNamedItem(FONT) != null) {
            builderService.addStyle(FONT, attributes.getNamedItem(FONT).getTextContent());
        }
        if (attributes.getNamedItem(FONTSIZE) != null) {
            builderService.addStyle(FONTSIZE, attributes.getNamedItem(FONTSIZE).getTextContent());
        }
        if (attributes.getNamedItem(COLOR) != null) {
            builderService.addStyle(COLOR, attributes.getNamedItem(COLOR).getTextContent());
        }
        if (attributes.getNamedItem(BULLET)!= null)  {
            builderService.addStyle(BULLET, attributes.getNamedItem(BULLET).getTextContent());
        }
    }

    /**
     * Parses the metadata and sets this data via the builder service
     * @param builderService
     * @param metadata
     */
    public void parseMetadata(BuilderService builderService, NodeList metadata){
        for (int i = 0; i < metadata.getLength() ; i++) {

            Element metadataField = (Element) metadata.item(i);
            String type = metadataField.getAttribute(KIND);

            if (PRESENTER.equals(type))
            {
                builderService.setMetadata(PRESENTER, metadataField.getTextContent());
            }
            else if (SHOWTITLE.equals(type))
            {
                builderService.setMetadata(SHOWTITLE, metadataField.getTextContent());
            }
            else{
                System.err.println(UNKNOWNTYPE);
            }
        }
    }

    /**
     * Parses the slide title with accompanying styles and sets them via the builder service
     * @param builderService
     * @param element
     * @return
     */
    private String getTitle(BuilderService builderService ,Element element) {
        NodeList titles = element.getElementsByTagName(SLIDETITLE);
        NamedNodeMap attributes = titles.item(0).getAttributes();
        if (attributes.getLength() == 0) return titles.item(0).getTextContent();
        builderService.newStyles();
        parseStyles(builderService, attributes);
        return titles.item(0).getTextContent();
    }


    /**
     * Get only the first level of child nodes with a certain name from an element
     * @param parent
     * @param name
     * @return
     */
    public List<Element> getChildrenByTagName(Element parent, String name) {
        List<Element> nodeList = new ArrayList<>();
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE &&
                    name.equals(child.getNodeName())) {
                nodeList.add((Element) child);
            }
        }
        return nodeList;
    }

    /**
     * Opens an XML file and directs the construction of the domain by using the builder service
     *
     * @param builderService
     * @param filename
     */
    @Override
    public void construct(BuilderService builderService, String filename) {

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            Element doc = document.getDocumentElement();

            builderService.newMetaData();
            parseMetadata(builderService, doc.getElementsByTagName(METADATA));

            builderService.newSlideShow();

            NodeList slides = doc.getElementsByTagName(SLIDE);

            for (int slideNumber = 0; slideNumber < slides.getLength(); slideNumber++) {

                Element xmlSlide = (Element) slides.item(slideNumber);

                builderService.newSlide();
                builderService.addSlideTitle(getTitle(builderService ,xmlSlide));

                NamedNodeMap slideAttributes = xmlSlide.getAttributes();
                String transitions = slideAttributes.getNamedItem(TRANSITIONS).getTextContent();

                builderService.setTransitions(transitions.equals(TRUE));

                this.prepareSlide(builderService, xmlSlide, null);
                builderService.addSlide();
            }

            builderService.addMetadata();
        }
        catch (IOException iox) {
            System.err.println(iox);
        }
        catch (SAXException sax) {
            System.err.println(sax.getMessage());
        }
        catch (ParserConfigurationException pcx) {
            System.err.println(PCE);
        }
    }


    /**
     * Parses all the slide items of a slide
     * This function is used recursively to build bullet lists
     *
     * @param builderService
     * @param xmlSlide
     * @param contents
     */
    private void prepareSlide(BuilderService builderService, Element xmlSlide, ContentComposite contents)
    {
        List<Element> slideItems = this.getChildrenByTagName(xmlSlide, ITEM);
        for (Element element : slideItems)
        {
            int indentation = 0; // default
            NamedNodeMap attributes = element.getAttributes();
            String indentationText = attributes.getNamedItem(LEVEL).getTextContent();
            if (indentationText != null) {
                try {
                    indentation = Integer.parseInt(indentationText);
                    if (indentation < 0) indentation = 0;
                }
                catch(NumberFormatException x) {
                    System.err.println(NFE);
                }
            }

            builderService.newStyles();
            parseStyles(builderService, attributes);

            String type = attributes.getNamedItem(KIND).getTextContent();
            if (TEXT.equals(type))
            {
                if (recursive) builderService.addTextToBullet(indentation, element.getTextContent(), contents);
                else builderService.addTextContent(indentation, element.getTextContent());
            }
            else if (IMAGE.equals(type))
            {
                if (recursive) builderService.addImageToBullet(indentation, element.getTextContent(), contents);
                else builderService.addImageContent(indentation, element.getTextContent());
            }
            else if (BULLETLIST.equals(type))
            {
                if (recursive)
                {
                    ContentComposite bulletList = builderService.newBulletList(indentation);
                    this.prepareSlide(builderService, element, bulletList);
                    contents.addContent(bulletList);
                    continue;
                }
                recursive = true;
                BulletList bulletList = builderService.newBulletList(indentation);
                this.prepareSlide(builderService, element, bulletList);
                builderService.addBulletList(indentation, bulletList);
                recursive = false;
            }
            else{
                System.err.println(UNKNOWNTYPE);
            }
        }
    }
}
