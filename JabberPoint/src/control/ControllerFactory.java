package control;

import domain_service.BuilderService;
import domain_service.ProjectorService;
import infrastructure.DirectorStrategy;
import infrastructure.Infrastructure;
import userinterface.HandlerFactory;
import userinterface.UserInterface;

public abstract class ControllerFactory
{
    public abstract UserInterface getUI();

    public abstract Infrastructure getInfraStructure();

    public final ProjectorService getProjectorService()
    {
        return new ProjectorService();
    }

    final UserInterface createUI(HandlerFactory handlerFactory)
    {
        return new UserInterface(handlerFactory);
    }

    final Infrastructure createInfra(BuilderService builderService, DirectorStrategy strategy)
    {
        return new Infrastructure(builderService, strategy);
    }
}
