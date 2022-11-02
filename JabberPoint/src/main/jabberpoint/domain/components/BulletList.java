package main.jabberpoint.domain.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Composite Pattern
 * Role: Concrete ContentComposite
 */
public class BulletList extends ContentComposite
{
    /**
     * Creates an instance of a BulletList, this contains a list of Content items
     * @param indentation indentation of the BulletList
     * @param contents List of Content as children
     */
    public BulletList(int indentation, List<Content> contents)
    {
        super(indentation, contents);
    }

    /**
     * Gets its children as a list of Content, the concrete classes under ContentComposite can still override this
     * for an own implementation
     * @return List of Content items
     */
    @Override
    public List<SlideShowComponent> getContent()
    {
        List<SlideShowComponent> displayContentList = new ArrayList<>();
        displayContentList.add(this);
        return displayContentList;
    }
}
