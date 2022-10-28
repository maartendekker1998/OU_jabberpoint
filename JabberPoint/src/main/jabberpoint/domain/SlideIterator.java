package main.jabberpoint.domain;

import java.util.ArrayList;
import java.util.List;

public class SlideIterator extends SlideShowCompositeIterator
{
    private List<ContentList> chunks = new ArrayList<>();

    SlideIterator(ConcreteSlide slideshow)
    {
        boolean buildingChunk = false;
        List<Content> chunkList = new ArrayList<>();

        for (Content content : slideshow.getContent())
        {
            if (content.getIndentation() == 1 && buildingChunk){
                this.chunks.add(new ContentList(0, chunkList));
                chunkList = new ArrayList<>();
                buildingChunk = false;
            }
            if (content.getIndentation() == 1 && !buildingChunk){
                chunkList.add(content);
                buildingChunk = true;
            }
            if (content.getIndentation() > 1 && buildingChunk){
                chunkList.add(content);
            }
        }
        if (!chunkList.isEmpty()) this.chunks.add(new ContentList(0, chunkList));
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
        if (this.index < 0) return;
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
        if (this.index < 0) return null;
        return this.chunks.get(this.index);
    }

    @Override
    public SlideShowComponent first()
    {
        return this.chunks.get(0);
    }
}