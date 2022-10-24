package main.jabberpoint.control;

import main.jabberpoint.domain.SlideShowComponentBuilder;
import main.jabberpoint.domain_service.BuilderService;
import main.jabberpoint.infrastructure.Infrastructure;
import main.jabberpoint.infrastructure.XMLDirectorStrategy;
import main.jabberpoint.userinterface.SwingHandlerFactory;
import main.jabberpoint.userinterface.UserInterface;

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
