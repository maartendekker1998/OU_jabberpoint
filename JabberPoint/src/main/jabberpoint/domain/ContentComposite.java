package domain;

import java.util.List;

public abstract class ContentComposite extends Content<List<Content>>
{

    ContentComposite(int indentation, List<Content> data) {
        super(indentation, data);
    }
}
