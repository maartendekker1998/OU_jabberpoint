package DesignPatterns.slideshow;

public class Projector
{
    private Iterable slideShowComponent;
    private Iterator iterator;

    public Projector(Iterable slideShowComponent)
    {
        this.slideShowComponent = slideShowComponent;
        this.iterator = this.slideShowComponent.createIterator();
    }

    public void start()
    {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("<html><body>");
//        for (this.iterator.first(); !this.iterator.isDone(); this.iterator.next())
//        {
//            stringBuilder.append(this.iterator.current().getContent());
//        }
//        stringBuilder.append("</body></html>");
//        System.out.println(stringBuilder.toString());
    }

    public SlideShowComponent getNextSlide()
    {
        this.iterator.next();
        return this.iterator.current();
    }
}
