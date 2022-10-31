package main.jabberpoint.domain.components;

import java.util.List;

public abstract class ContentComposite extends Content<List<Content>>
{
    ContentComposite(int indentation, List<Content> data) {
        super(indentation, data);
    }

    public void addContent(Content content)
    {
        super.getData().add(content);
    }
}
