package DesignPatterns.slideshow;

import java.util.ArrayList;
import java.util.List;

abstract class SlideShowComposite extends SlideShowComponent
{
    List<SlideShowComponent> componentList = new ArrayList<>();
}
