package main.jabberpoint.domain.components;

import java.util.List;

/**
 * Part of Composite Pattern
 * Role: Concrete ContentComposite
 */
public class ContentList extends ContentComposite
{
    /**
     * Creates an instance of a ContentList, this contains a list of Content items
     * @param indentation indentation of the ContentList
     * @param contents List of Content as children
     */
    public ContentList(int indentation, List<Content> contents)
    {
        super(indentation, contents);
    }
}