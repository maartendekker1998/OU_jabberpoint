package main.jabberpoint.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SlideShowComponent implements Iterable
{
    private Map<String , String > styles = new HashMap<>();

    public abstract List<? extends Content> getContent();

    public Map<String, String> getMetadata(){
        return Metadata.getInstance().metadata;
    }

    public void addStyles(Map<String, String> styles){
        this.styles.putAll(styles);
    }

    public Map<String, String> getStyles() {
        return styles;
    }
}