package domain;

import java.util.ArrayList;
import java.util.List;

public class SlideIterator extends SlideShowCompositeIterator
{
    private List<ContentList> chunks = new ArrayList<>();

    SlideIterator(ConcreteSlide slideshow)
    {
        for (Content content : slideshow.getContent())
        {
            if (content.getIndentation() == 1)
            {

            }
        }

        List<Content> chunk = new ArrayList<>();

        chunks.add(new ContentList(0, chunk));

//        this.slideshow = slideshow;
    }

    @Override
    public void next()
    {
        this.index++;
        System.out.println("slide item next" + (this.index-1));
    }

    @Override
    public void previous()
    {
        this.index--;
        System.out.println("slide item prev" + (this.index));
    }

    @Override
    public boolean isDone()
    {
        return this.index >= this.chunks.size();
    }

    @Override
    public void resetIndex()
    {
        //        this.index = 0;
    }

    @Override
    public SlideShowComponent current()
    {
        return this.chunks.get(this.index-1);
    }

//    @Override
//    public void reset()
//    {
//    }

//
//    void dsfsdF()
//    {
//        this.handle();
//    }

//    void handle(Content... contents)
//    {
//        this.SwingFrame.clear();
//        this.actuallyWriteToDisplay(title);
//        for (Content content : contents) {
//            this.actuallyWriteToDisplay(content);
//
//        }
//
//    }

    @Override
    public SlideShowComponent first()
    {
        return this.chunks.get(0);
    }
}