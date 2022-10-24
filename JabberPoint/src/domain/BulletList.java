package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BulletList extends ContentComposite
{
    BulletList(int indentation, Content... contents)
    {
        super(indentation, Arrays.asList(contents));
    }

    @Override
    public List<Content> getContent()
    {
        List<Content> displayContentList = new ArrayList<>();
        super.getData().forEach(content -> displayContentList.addAll(content.getContent()));
        return displayContentList;
    }
}
