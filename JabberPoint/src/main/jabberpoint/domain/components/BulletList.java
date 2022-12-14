package main.jabberpoint.domain.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Composite Pattern (with SlideShowComponent as Component)
 * Role: Concrete Leaf
 *
 * Part of Composite Pattern (With Content as Component)
 * Role: Concrete Composite
 *
 * T type is set in the ContentComposite
 * @see Content
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
     * Since an BulletList is a leaf, it returns itself
     * @return List of Content items with only itself in it
     */
    @Override
    public List<Content> getContent()
    {
        List<Content> contentList = new ArrayList<>();
        contentList.add(this);
        return contentList;
    }
}
