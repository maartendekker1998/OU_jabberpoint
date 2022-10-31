package main.jabberpoint.infrastructure;

import main.jabberpoint.domain.SlideShowComponent;
import main.jabberpoint.domain_service.BuilderService;

public class Infrastructure {

    public DirectorStrategy director;
    public BuilderService builderService;

    public Infrastructure(BuilderService builderService ,DirectorStrategy director){
        this.builderService = builderService;
        this.director = director;
    }

    /**
     * This function receives a filepath and instructs the director to construct the domain
     *
     * @param filepath
     * @return A reference to the fully constructed slideshow
     */
    public SlideShowComponent loadFile(String filepath) {
        this.director.construct(builderService, filepath);
        return this.builderService.getSlideShow();
    }
}
