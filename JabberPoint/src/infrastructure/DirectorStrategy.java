package infrastructure;

import domain_service.BuilderService;

import java.io.IOException;

public interface DirectorStrategy {

    void construct(BuilderService builderService, String filename) throws IOException;
}
