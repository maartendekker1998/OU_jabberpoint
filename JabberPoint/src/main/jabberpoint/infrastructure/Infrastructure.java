package main.jabberpoint.infrastructure;

import main.jabberpoint.domain.SlideShowComponent;
import main.jabberpoint.domain_service.BuilderService;

import java.io.IOException;

public class Infrastructure {

    public DirectorStrategy director;
    public BuilderService builderService;

    public Infrastructure(BuilderService builderService ,DirectorStrategy director){
        this.builderService = builderService;
        this.director = director;
    }

    public SlideShowComponent loadFile(String filename) throws IOException {

        this.director.construct(builderService, filename);

        return this.builderService.getResults();
    }


}
