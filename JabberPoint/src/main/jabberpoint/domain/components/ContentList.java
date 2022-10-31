package main.jabberpoint.domain.components;

import java.util.ArrayList;
import java.util.List;

public class ContentList extends ContentComposite
{
    public ContentList(int indentation, List<Content> contents)
    {
        super(indentation, contents);
    }

    @Override
    public List<Content> getContent()
    {
        List<Content> displayContentList = new ArrayList<>();
        super.getData().forEach(content -> displayContentList.addAll(content.getContent()));
        return displayContentList;
    }
}