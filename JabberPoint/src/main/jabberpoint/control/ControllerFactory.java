package control;

import domain_service.ProjectorService;
import userinterface.HandlerFactory;
import userinterface.UserInterface;

public abstract class ControllerFactory
{
    public abstract UserInterface getUI();
//    public abstract InfraStructure getInfraStructure();

    public final ProjectorService getProjectorService()
    {
        return new ProjectorService();
    }

    final UserInterface createUI(HandlerFactory handlerFactory)
    {
        return new UserInterface(handlerFactory);
    }

    //infrastructure omitted

//    final InfraStructure createInfra(DirectorStrategy strategy)
//    {
//        return new InfraStructure(strategy);
//    }
}
