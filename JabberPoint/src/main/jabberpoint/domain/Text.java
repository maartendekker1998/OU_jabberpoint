package main.jabberpoint.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
