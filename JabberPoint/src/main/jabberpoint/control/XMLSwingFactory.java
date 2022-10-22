package control;

import userinterface.SwingHandlerFactory;
import userinterface.UserInterface;

public class XMLSwingFactory extends ControllerFactory
{
    @Override
    public UserInterface getUI()
    {
        return super.createUI(new SwingHandlerFactory());
    }

    //Infra omitted
//    @Override
//    public InfraStructure getInfraStructure()
//    {
//        return super.createInfra(new XMLDirector());
//    }
}
