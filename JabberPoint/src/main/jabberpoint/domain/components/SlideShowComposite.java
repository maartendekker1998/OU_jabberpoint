package main.jabberpoint.domain.components;

import java.util.ArrayList;
import java.util.List;

public abstract class SlideShowComposite extends SlideShowComponent
{
    public List<SlideShowComponent> componentList = new ArrayList<>();
}
