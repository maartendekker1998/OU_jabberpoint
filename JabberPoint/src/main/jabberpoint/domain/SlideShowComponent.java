package main.jabberpoint.domain;

import java.util.List;
import java.util.Map;

public abstract class SlideShowComponent implements Iterable
{
    public abstract List<? extends Content> getContent();

    public Map<String, String> getMetadata(){
        return Metadata.getInstance().metadata;
    }
}