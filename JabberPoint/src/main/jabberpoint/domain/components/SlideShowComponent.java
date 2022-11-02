package main.jabberpoint.domain.components;

import main.jabberpoint.domain.Metadata;
import main.jabberpoint.domain.iterators.Iterable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Part of Iterator Pattern
 * Role: Abstract Aggregate
 *
 * Part of Composite Pattern
 * Role: Component
 */
public abstract class SlideShowComponent implements Iterable
{
    private final Map<String, String> styles = new HashMap<>();

    /**
     * Abstract function for all its children to inherit, it gets the content of the concrete item
     * @return a list with items that must extend from Content
     */
    public abstract List<? extends SlideShowComponent> getContent();

    /**
     * Add styles to the Content item
     * @param styles a map containing styles to be set
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

    public Map<String, String> getMetadata() {
        return Metadata.getInstance().metadata;
    }
}