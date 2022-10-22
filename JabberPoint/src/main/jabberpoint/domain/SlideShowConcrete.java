package domain;

import java.util.ArrayList;
import java.util.List;

public class SlideShowConcrete extends SlideShowComposite
{
    SlideShowConcrete(List<ConcreteSlide> slides)
    {
        super.componentList.addAll(slides);
    }

    @Override
    public SlideShowIterator createIterator()
    {
        return new SlideShowIterator(this);
    }

    @Override
    public List<Content> getContent()
    {
        return new ArrayList<>();
    }
}

//
//class UserInterface{
//    public UserInterface(HandlerFactory handlerFactory) {
//    }
//}
//
//class InfraStructure{
//    public InfraStructure(DirectorStrategy strategy) {
//    }
//}
//
//class XMLDirector extends DirectorStrategy{}
//
//class ProjectorService{}
//
//class HandlerFactory{}
//
//class DirectorStrategy{}
//
//class SwingHandlerFactory extends HandlerFactory{}
//
//
//class Command
//{
//    void sdfsdf()
//    {
//        Controller.getInstance().loadFile();
//    }
//}
//
//
//class JabberPoint
//{
//    public static void fakeMain(String[] args)
//    {
//        Controller.getInstance().init(new XMLSwingFactory());
//    }
//}
//
//class Controller
//{
//    private static Controller controller;
//    private InfraStructure infraStructure;
//    private UserInterface userInterface;
//    private ProjectorService projectorService;
//
//    private Controller()
//    {
//    }
//
//    void init(ControllerFactory factory)
//    {
//        this.infraStructure = factory.getInfraStructure();
//        this.userInterface = factory.getUI();
//        this.projectorService = factory.getProjectorService();
//    }
//
//    public static Controller getInstance()
//    {
//        if (controller == null)
//        {
//            controller = new Controller();
//            return controller;
//        }
//        return controller;
//    }
//
//    public void loadFile() {
//        //inraStructure.load();
//    }
//}
//
//abstract class ControllerFactory
//{
//    public abstract UserInterface getUI();
//    public abstract InfraStructure getInfraStructure();
//
//    public final ProjectorService getProjectorService()
//    {
//        return new ProjectorService();
//    }
//
//    final UserInterface createUI(HandlerFactory handlerFactory)
//    {
//        return new UserInterface(handlerFactory);
//    }
//
//    final InfraStructure createInfra(DirectorStrategy strategy)
//    {
//        return new InfraStructure(strategy);
//    }
//}
//
//class XMLSwingFactory extends ControllerFactory
//{
//    @Override
//    public UserInterface getUI() {
//        return super.createUI(new SwingHandlerFactory());
//    }
//
//    @Override
//    public InfraStructure getInfraStructure() {
//        return super.createInfra(new XMLDirector());
//    }
//}