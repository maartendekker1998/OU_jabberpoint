package main.jabberpoint.control;

import main.jabberpoint.domain.components.SlideShowComponent;
import main.jabberpoint.domain_service.ProjectorService;
import main.jabberpoint.infrastructure.Infrastructure;
import main.jabberpoint.userinterface.UserInterface;

/**
 * The controller class is a singleton and provides the means of interfacing between the UI and the infrastructure / domain services
 */
public class Controller
{
    private ProjectorService projectorService;
    private Infrastructure infrastructure;
    private UserInterface userInterface;
    private static Controller instance;

    private Controller(){}

    /**
     * This function initializes the controller, and is only called once in the Main function of this program
     * @param factory factory to create controller items
     * @param filepath location to a file
     */
    void initialize(ControllerFactory factory, String filepath) {
        this.infrastructure = factory.getInfrastructure();
        this.projectorService = factory.getProjectorService();
        this.projectorService.setSlideShow(this.infrastructure.loadFile(filepath));
        this.userInterface = factory.getUserInterface();
        this.getNextSlide();
    }

    /**
     * The function defines the singleton behaviour of the controller
     * @return an instance of itself
     */
    public static Controller getInstance()
    {
        if (instance == null)
        {
            instance = new Controller();
            return instance;
        }
        return instance;
    }

    /**
     * This function instructs the infrastructure to read a file, and sets the parsed file in the projectorService
     * @param filepath location to a file
     */
    public void loadFile(String filepath) {
        this.projectorService.setSlideShow(this.infrastructure.loadFile(filepath));
        this.getNextSlide();
    }

    /**
     * This function can be called by the UI to go to the next slide
     */
    public void getNextSlide()
    {
        SlideShowComponent slide = this.projectorService.getNextSlide();
        if (slide == null) return;
        this.userInterface.renderSlide(slide);
    }

    /**
     * This function can be called by the UI to go to the previous slide
     */
    public void getPreviousSlide()
    {
        this.userInterface.renderSlide(this.projectorService.getPreviousSlide());
    }

    /**
     * This function can be called by the UI to go to the next content item, if a slide has transitions
     */
    public void getNextContent()
    {
        this.userInterface.renderContent(this.projectorService.getNextContent());
    }

    /**
     * This function can be called by the UI to go to the previous content item, if a slide has transitions
     */
    public void removeLastContent()
    {
        SlideShowComponent content = this.projectorService.getCurrentContent();
        if (content == null) return;
        this.userInterface.removeContent(content);
    }

    /**
     * This function can be called by the UI to go back to the beginning of the slide, if the slide has transitions
     */
    public void removeAllContent()
    {
        this.projectorService.removeAllContent();
        this.userInterface.removeAllContent();
    }

    /**
     * This function can be called by the UI to go to the end of the slide, if the slide has transitions
     */
    public void getRemainingContent()
    {
        this.userInterface.renderContent(this.projectorService.getRemainingContent());
    }
}
