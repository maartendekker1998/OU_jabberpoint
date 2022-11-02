package main.jabberpoint.domain.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Composite Pattern
 * Role: Abstract Content with a List of Content as data type
 */
public abstract class ContentComposite extends Content<List<Content>>
{
    /**
     * Creates an instance of ContentComposite, this constructor is part of an abstract class
     * @param indentation indentation to be set
     * @param data data to be set
     */
    ContentComposite(int indentation, List<Content> data) {
        super(indentation, data);
    }

    /**
     * Adds content to the data
     * @param content Content to be added
     */
    public void addContent(Content content)
    {
        super.getData().add(content);
    }
}
