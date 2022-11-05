package main.jabberpoint.control;

import main.jabberpoint.domain.builder.SlideShowBuilder;
import main.jabberpoint.domain_service.BuilderService;
import main.jabberpoint.infrastructure.Infrastructure;
import main.jabberpoint.infrastructure.XMLDirectorStrategy;
import main.jabberpoint.userinterface.handlers.SwingHandlerFactory;
import main.jabberpoint.userinterface.UserInterface;

/**
 * Part of Factory pattern
 * Role: Concrete factory for Swing and XML
 *
 * This class defines a concrete configuration of what the program needs to run
 * This concrete class returns a configuration where XML presentations can be used together with the UI framework Swing
 */
public class XMLSwingConfiguration extends ControllerConfigurationFactory
{
    /**
     * Creates an UserInterface initiated with a SwingHandlerFactory
     * @return Instance of an UserInterface initiated with a SwingHandlerFactory
     */
    @Override
    public UserInterface getUserInterface()
    {
        return super.createUserInterface(new SwingHandlerFactory());
    }

    /**
     * Creates an infrastructure bound for Swing and XML
     * @return Instance of an Infrastructure initiated with a BuilderService and a XML strategy
     */
    @Override
    public Infrastructure getInfrastructure() {
        return super.createInfrastructure(new BuilderService(new SlideShowBuilder()), new XMLDirectorStrategy());
    }
}
