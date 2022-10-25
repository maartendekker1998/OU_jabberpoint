package main.jabberpoint.infrastructure;

import main.jabberpoint.domain.*;
import main.jabberpoint.domain.Text;
import main.jabberpoint.domain_service.BuilderService;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.ByteLookupTable;
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

    @Override
    public void construct(BuilderService builderService, String filename) {

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            Element doc = document.getDocumentElement();;


            builderService.newSlideShow();

            NodeList slides = doc.getElementsByTagName(SLIDE);

            for (int slideNumber = 0; slideNumber < slides.getLength(); slideNumber++) {

                Element xmlSlide = (Element) slides.item(slideNumber);

                builderService.newSlide();
                builderService.addSlideTitle(getTitle(xmlSlide, SLIDETITLE));

                NamedNodeMap slideAttributes = xmlSlide.getAttributes();
                String transitions = slideAttributes.getNamedItem(TRANSITIONS).getTextContent();

                builderService.setTransitions(transitions.equals("true"));

                this.prepareSlide(builderService, xmlSlide, null);
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

    private boolean recursive = false;

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
                }
                catch(NumberFormatException x) {
                    System.err.println(NFE);
                }
            }
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
                    ContentComposite bulletList = builderService.createBulletList(indentation);
                    this.prepareSlide(builderService, element, bulletList);
                    contents.addContent(bulletList);
                    continue;
                }
                recursive = true;
                BulletList bulletList = builderService.createBulletList(indentation);
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
