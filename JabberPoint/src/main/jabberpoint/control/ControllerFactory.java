package main.jabberpoint.control;

import main.jabberpoint.domain_service.BuilderService;
import main.jabberpoint.domain_service.ProjectorService;
import main.jabberpoint.infrastructure.DirectorStrategy;
import main.jabberpoint.infrastructure.Infrastructure;
import main.jabberpoint.userinterface.HandlerFactory;
import main.jabberpoint.userinterface.UserInterface;

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
