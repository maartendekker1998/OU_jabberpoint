package main.jabberpoint.control;

import main.jabberpoint.domain.builder.SlideShowComponentBuilder;
import main.jabberpoint.domain_service.BuilderService;
import main.jabberpoint.infrastructure.Infrastructure;
import main.jabberpoint.infrastructure.XMLDirectorStrategy;
import main.jabberpoint.userinterface.handlers.SwingHandlerFactory;
import main.jabberpoint.userinterface.UserInterface;

/**
 * Part of Factory pattern
 * Role: Concrete factory for XML
 *
 * This class defines a concrete configuration of what the program needs to run
 * This concrete class returns a configuration where XML presentations can be used together with the UI framework Swing
 */
public class XMLSwingFactory extends ControllerFactory
{
    @Override
    public UserInterface getUI()
    {
        return super.createUI(new SwingHandlerFactory());
    }

    @Override
    public Infrastructure getInfraStructure() {
        return super.createInfra(new BuilderService(new SlideShowComponentBuilder()), new XMLDirectorStrategy());
    }
}
