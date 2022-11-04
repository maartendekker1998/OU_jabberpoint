package main.jabberpoint.domain.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Composite Pattern
 * Role: Concrete Leaf
 *
 * T type is set to String since text usually consists of just text
 */
public class Text extends Content<String>
{
    /**
     * Creates an instance of a Text
     * @param indentation indentation to be set
     * @param data string data to be set
     */
    public Text(int indentation, String data)
    {
        super(indentation, data);
    }

    /**
     * Since an Text is a leaf, it returns itself
     * @return List of Content items with only itself in it
     */
    @Override
    public List<Text> getContent()
    {
        List<Text> displayContentList = new ArrayList<>();
        displayContentList.add(this);
        return displayContentList;
    }
}
