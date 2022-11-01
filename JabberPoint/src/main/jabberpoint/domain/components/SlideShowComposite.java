package main.jabberpoint.domain.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Composite Pattern
 * Role: Composite
 */
public abstract class SlideShowComposite extends SlideShowComponent
{
    private List<SlideShowComponent> componentList = new ArrayList<>();

    /**
     * Returns the componentList, the concrete classes under SlideShowComposite can still override this for an
     * own implementation
     * @return componentList
     */
    @Override
    public List<SlideShowComponent> getContent()
    {
        return this.componentList;
    }
}
