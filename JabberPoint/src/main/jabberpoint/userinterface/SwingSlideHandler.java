package main.jabberpoint.userinterface;

import main.jabberpoint.domain.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingSlideHandler implements SlideHandler
{
    private WindowHandler windowHandler;

    @Override
    public void renderSlide(ConcreteSlide slide)
    {
        this.windowHandler.clear(true);
        this.windowHandler.setTitle(slide);
        this.windowHandler.setTransitions(slide.hasTransitions());
        if (!slide.hasTransitions()) this.renderContent(slide.getContent());
    }

    private void renderContent(List<Content> contentList)
    {
        for (Content content : contentList)
        {
            if (content instanceof Text) this.windowHandler.addText((Text)content);
            if (content instanceof Image) this.windowHandler.addImage((Image)content);
            if (content instanceof BulletList)
            {
                for (Content bulletContent : content.getContent())
                {
                    Map<String, String> style = new HashMap<>();
                    style.put("bullet", content.getStyles().get("bullet") == null ? "-" : content.getStyles().get("bullet"));
                    bulletContent.addStyles(style);
                }
                this.renderContent(((ContentComposite)content).getData());
            }
            if (content instanceof ContentList) this.renderContent(((ContentComposite)content).getData());
        }
    }

    @Override
    public void setWindowHandler(WindowHandler windowHandler)
    {
        this.windowHandler = windowHandler;
    }

    @Override
    public void renderContent(Content content)
    {
        if (content == null) return;
        List<Content> contentList = new ArrayList<>();
        contentList.add(content);
        this.renderContent(contentList);
    }

    @Override
    public void removeLastContent(Content content)
    {
        this.windowHandler.removeLastContent(content);
    }

    @Override
    public void removeAllContent()
    {
        this.windowHandler.clear(false);
    }
}
