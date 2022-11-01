package main.jabberpoint.infrastructure;

import main.jabberpoint.domain.components.SlideShowComponent;
import main.jabberpoint.domain_service.BuilderService;

/**
 * This class interfaces with an encapsulated algorithm to read and parse presentation files.
 *
 * Part of the Strategy Pattern
 * Role: Context
 */
public class Infrastructure {

    private DirectorStrategy director;
    private BuilderService builderService;

    /**
     * Constructs the infrastructure instance
     * @param builderService service to build slideshows
     * @param director director to determine how the slideshow should be build
     */
    public Infrastructure(BuilderService builderService, DirectorStrategy director){
        this.builderService = builderService;
        this.director = director;
    }

    /**
     * This function receives a filepath and instructs the director to construct the domain
     *
     * @param filepath the filepath to the file we wish to load
     * @return A reference to the fully constructed slideshow
     */
    public SlideShowComponent loadFile(String filepath) {
        this.director.construct(builderService, filepath);
        return this.builderService.getSlideShow();
    }
}
