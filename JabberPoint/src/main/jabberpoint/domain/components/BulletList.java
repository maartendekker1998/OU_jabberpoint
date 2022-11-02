package main.jabberpoint.domain.components;

import java.util.List;

/**
 * Part of Composite Pattern (with SlideShowComponent as Component)
 * Role: Concrete Leaf
 *
 * Part of Composite Pattern (With Content as Component)
 * Role: Concrete Composite
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
}
