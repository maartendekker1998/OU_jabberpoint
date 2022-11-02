package main.jabberpoint.domain.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Composite Pattern
 * Role: Concrete Leaf
 */
public class Image extends Content<String>
{
    /**
     * Creates an instance of an Image
     * @param indentation indentation to be set
     * @param data string data to be set
     */
    public Image(int indentation, String data)
    {
        super(indentation, data);
    }

    /**
     * Gets its children as a list of Content, since an Image does not have children, it adds itself
     * @return List of Content items with only itself in it
     */
    @Override
    public List<Image> getContent()
    {
        List<Image> displayContentList = new ArrayList<>();
        displayContentList.add(this);
        return displayContentList;
    }
}
