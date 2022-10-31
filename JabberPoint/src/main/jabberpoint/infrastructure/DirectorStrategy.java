package main.jabberpoint.infrastructure;

import main.jabberpoint.domain_service.BuilderService;

public interface DirectorStrategy {

    void construct(BuilderService builderService, String filename);
}
