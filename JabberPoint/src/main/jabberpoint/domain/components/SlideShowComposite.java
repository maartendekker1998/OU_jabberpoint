package main.jabberpoint.domain.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Iterator Pattern
 * Role: Abstract Aggregate
 *
 * Part of Composite Pattern
 * Role: Composite
 */
public abstract class SlideShowComposite extends SlideShowComponent
{
    List<SlideShowComponent> componentList = new ArrayList<>();
}
