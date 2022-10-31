package main.jabberpoint.control;

import main.jabberpoint.domain.SlideShowComponentBuilder;
import main.jabberpoint.domain_service.BuilderService;
import main.jabberpoint.infrastructure.Infrastructure;
import main.jabberpoint.infrastructure.XMLDirectorStrategy;
import main.jabberpoint.userinterface.SwingHandlerFactory;
import main.jabberpoint.userinterface.UserInterface;

/**
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
