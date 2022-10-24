package control;

import domain.SlideShowComponentBuilder;
import domain_service.BuilderService;
import domain_service.ProjectorService;
import infrastructure.Infrastructure;
import infrastructure.XMLDirectorStrategy;
import userinterface.SwingHandlerFactory;
import userinterface.UserInterface;

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
