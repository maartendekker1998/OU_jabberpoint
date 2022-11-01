package main.jabberpoint.domain.components;

import main.jabberpoint.domain.iterators.Iterable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Part of Composite Pattern
 * Role: Root component
 */
public abstract class SlideShowComponent implements Iterable
{
    private final Map<String, String> styles = new HashMap<>();

    /**
     * Abstract function for all its children to inherit, it gets the content of the concrete item
     * @return a list with items that must extend from Content
     */
    public abstract List<? extends Content> getContent();

    /**
     * Add styles to the Content item
     * @param styles a map containing tyles to be set
     */
    public void addStyles(Map<String, String> styles){
        this.styles.putAll(styles);
    }

    /**
     * Gets the styles of a content item
     * @return a map containing styles
     */
    public Map<String, String> getStyles() {
        return styles;
    }
}