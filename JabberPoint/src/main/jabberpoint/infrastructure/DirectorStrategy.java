package main.jabberpoint.infrastructure;

import main.jabberpoint.domain_service.BuilderService;

/**
 * This class is the interface for the concrete strategies to read and parse presentation files
 *
 * Part of the Strategy Pattern
 * Role : Strategy
 */
public interface DirectorStrategy {

    void construct(BuilderService builderService, String filename);
}
