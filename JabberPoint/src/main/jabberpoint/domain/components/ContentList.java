package main.jabberpoint.domain.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Composite Pattern (with SlideShowComponent as Component)
 * Role: Concrete Leaf
 *
 * Part of Composite Pattern (With Content as Component)
 * Role: Concrete Composite
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

    /**
     * Since an ContentList is a leaf, it returns itself
     * @return List of Content items with only itself in it
     */
    @Override
    public List<SlideShowComponent> getContent()
    {
        List<SlideShowComponent> contentList = new ArrayList<>();
        contentList.add(this);
        return contentList;
    }
}