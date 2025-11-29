package Classes.retrieveInfo;
import Classes.retrieveInfo.Animal;

public class SearchAnimalsOutputData {
    public final boolean success;
    public final String errorMessage;
    public final Animal[] animals; // may be length 0
    public final String suggestion; // fuzzy suggestion if any

    public SearchAnimalsOutputData(boolean success, String errorMessage, Animal[] animals, String suggestion) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.animals = animals;
        this.suggestion = suggestion;
    }
}