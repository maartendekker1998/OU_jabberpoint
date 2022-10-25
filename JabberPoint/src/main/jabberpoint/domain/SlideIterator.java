package main.jabberpoint.domain;

import java.util.ArrayList;
import java.util.List;

public class SlideIterator extends SlideShowCompositeIterator
{
    private List<ContentList> chunks = new ArrayList<>();

    SlideIterator(ConcreteSlide slideshow)
    {
        for (Content content : slideshow.getContent())
        {
            List<Content> chunkList = new ArrayList<>();
            chunkList.add(content);
            this.chunks.add(new ContentList(0, chunkList));
//            if (content.getIndentation() == 1)
//            {
//
//            }
        }
    }

    @Override
    public void next()
    {
        if ((this.index+1) >= this.chunks.size()) return;
        this.index++;
        System.out.println("slide item next" + (this.index));
    }

    @Override
    public void previous()
    {
//        if ((this.index - 2) < 0) return;
        this.index--;
        System.out.println("slide item prev" + (this.index));
    }

    @Override
    public boolean isDone()
    {
        return (this.index+1) >= this.chunks.size();
    }

    @Override
    public void resetIndex()
    {
        //        this.index = 0;
    }

    @Override
    public SlideShowComponent current()
    {
        return this.chunks.get(this.index);
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