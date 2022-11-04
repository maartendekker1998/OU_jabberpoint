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
    /**
     * Creates an user interface
     * @return Instance of an UserInterface
     */
    public abstract UserInterface getUserInterface();

    /**
     * Creates an infrastructure
     * @return Instance of a Infrastructure
     */
    public abstract Infrastructure getInfrastructure();

    /**
     * Creates an instance of the ProjectorService, this function cannot be overridden
     * @return Instance of the ProjectorService
     * @see ProjectorService
     */
    final ProjectorService getProjectorService()
    {
        return new ProjectorService();
    }

    /**
     * Creates an instance of the UserInterface, with a defined HandlerFactory this function cannot be overridden
     * @param handlerFactory An instance of a HandlerFactory
     * @return Instance of the UserInterface initiated with a handler factory
     * @see UserInterface
     */
    final UserInterface createUserInterface(HandlerFactory handlerFactory)
    {
        return new UserInterface(handlerFactory);
    }

    /**
     * Creates an instance of the InfraStructure, with a defined builder service and a strategy this function cannot
     * be overridden
     * @param builderService An instance of a BuilderService for building slides
     * @param strategy An instance of a DirectorStrategy for defining the building process
     * @return Instance of the Infrastructure initiated with a builder service and strategy
     * @see Infrastructure
     */
    final Infrastructure createInfrastructure(BuilderService builderService, DirectorStrategy strategy)
    {
        return new Infrastructure(builderService, strategy);
    }
}
