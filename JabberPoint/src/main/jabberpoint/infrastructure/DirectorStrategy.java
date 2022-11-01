package main.jabberpoint.infrastructure;

import main.jabberpoint.domain_service.BuilderService;

/**
 * This class is the interface for the concrete strategies to read and parse presentation files
 *
 * Part of the Strategy Pattern
 * Role : Strategy
 */
public interface DirectorStrategy {

    /**
     * Opens an XML file and directs the construction of the domain by using the builder service
     *
     * @param builderService a builder to build slideshows
     * @see BuilderService
     * @param filepath the filepath to the file we wish to load
     */
    void construct(BuilderService builderService, String filepath);
}
