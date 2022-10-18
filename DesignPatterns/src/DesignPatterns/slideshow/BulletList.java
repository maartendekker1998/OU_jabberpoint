package DesignPatterns.slideshow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BulletList extends Content<List<Content>>
{
    BulletList(int indentation, Content... contents)
    {
        super(indentation, Arrays.asList(contents));
    }

    @Override
    public List<Content> getData()
    {
        return super.getData();
    }

    @Override
    public List<Content> getContent()
    {
        List<Content> displayContentList = new ArrayList<>();
        super.getData().forEach(content -> displayContentList.addAll(content.getContent()));
        return displayContentList;
    }
}
