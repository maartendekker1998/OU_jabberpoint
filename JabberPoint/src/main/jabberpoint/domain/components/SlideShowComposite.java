package main.jabberpoint.domain.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Composite Pattern
 * Role: Composite
 */
public abstract class SlideShowComposite extends SlideShowComponent
{
    public List<SlideShowComponent> componentList = new ArrayList<>();
}
