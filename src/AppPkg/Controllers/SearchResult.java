package AppPkg.Controllers;

import Classes.retrieveInfo.Animal;

/**
 * Simple result holder returned by SearchController.
 */
public class SearchResult {
    public final boolean success;
    public final String message;      // error / info message, may contain HTML for links (as UIs used)
    public final Animal[] animals;    // empty if none
    public final String suggestion;   // fuzzy suggestion if any

    public SearchResult(boolean success, String message, Animal[] animals, String suggestion) {
        this.success = success;
        this.message = message;
        this.animals = animals == null ? new Animal[0] : animals;
        this.suggestion = suggestion;
    }
}
