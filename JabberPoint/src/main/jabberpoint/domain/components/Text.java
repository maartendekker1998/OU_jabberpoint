package main.jabberpoint.domain.components;

import java.util.ArrayList;
import java.util.List;

public class Text extends Content<String>
{
    public Text(int indentation, String data)
    {
        super(indentation, data);
    }

    @Override
    public List<Text> getContent()
    {
        List<Text> displayContentList = new ArrayList<>();
        displayContentList.add(this);
        return displayContentList;
    }
}
