package domain;

import java.util.ArrayList;
import java.util.List;

public class Image extends Content<String>
{
    Image(int indentation, String data)
    {
        super(indentation, data);
    }

    @Override
    public List<Image> getContent()
    {
        List<Image> displayContentList = new ArrayList<>();
        displayContentList.add(this);
        return displayContentList;
    }
}
