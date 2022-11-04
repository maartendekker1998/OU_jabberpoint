package main.jabberpoint.domain.iterators;

import main.jabberpoint.domain.components.ConcreteSlide;
import main.jabberpoint.domain.components.Content;
import main.jabberpoint.domain.components.ContentList;
import main.jabberpoint.domain.components.SlideShowComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Iterator Pattern
 * Role: Concrete Iterator
 */
public class SlideIterator extends SlideShowCompositeIterator
{
    private final List<ContentList> chunks = new ArrayList<>();

    /**
     * Creates chunks of content items, this is because if a slide is iterated, it should be able to get
     * all the items which have a higher indentation then the previous content
     * @param slide slide that contains content
     */
    public SlideIterator(ConcreteSlide slide)
    {
        super.iterable = slide;
        this.initializeChunks();
    }

    private void initializeChunks(){
        boolean buildingChunk = false;
        List<Content> chunkList = new ArrayList<>();

        for (SlideShowComponent slideContent : super.iterable.getContent())
        {
            Content content = (Content)slideContent;
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

    /**
     * Iterates to the next item of the iterator
     */
    @Override
    public void next()
    {
        if ((super.index+1) >= this.chunks.size()) return;
        super.index++;
    }

    /**
     * Iterates to the previous item of the iterator
     */
    @Override
    public void previous()
    {
        if (super.index < 0) return;
        super.index--;
    }

    /**
     * Checks if the iterator is at its highest index and thus determine if it cannot iterate further
     * @return boolean value whether the iterator is done
     */
    @Override
    public boolean isDone()
    {
        return (super.index+1) >= this.chunks.size();
    }

    /**
     * Resets the index to -1, if hereafter is called next(), the index will be set to 0 and the current() will
     * be able to get the first item (which is then in fact the current item)
     */
    @Override
    public void resetIndex()
    {
        super.index = -1;
    }

    /**
     * Gets the current item where the index of the iterator is
     * @return current item or null if the index is lower then 0
     */
    @Override
    public SlideShowComponent current()
    {
        if (super.index < 0) return null;
        return this.chunks.get(super.index);
    }

    /**
     * Gets the first item of the iterator
     * @return first item
     */
    @Override
    public SlideShowComponent first()
    {
        return this.chunks.get(0);
    }
}