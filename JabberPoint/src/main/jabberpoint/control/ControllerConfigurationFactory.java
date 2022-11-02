package main.jabberpoint.control;

import main.jabberpoint.domain_service.BuilderService;
import main.jabberpoint.domain_service.ProjectorService;
import main.jabberpoint.infrastructure.DirectorStrategy;
import main.jabberpoint.infrastructure.Infrastructure;
import main.jabberpoint.userinterface.handlers.HandlerFactory;
import main.jabberpoint.userinterface.UserInterface;

/**
 * Part of Factory pattern
 * Role: Abstract Factory
 *
 * This abstract class defines an abstraction of the needed configuration for the program to run
 */
public abstract class ControllerConfigurationFactory
{
    public abstract UserInterface getUserInterface();

    public abstract Infrastructure getInfrastructure();

    final ProjectorService getProjectorService()
    {
        return new ProjectorService();
    }

    final UserInterface createUserInterface(HandlerFactory handlerFactory)
    {
        return new UserInterface(handlerFactory);
    }

    final Infrastructure createInfrastructure(BuilderService builderService, DirectorStrategy strategy)
    {
        return new Infrastructure(builderService, strategy);
    }
}
