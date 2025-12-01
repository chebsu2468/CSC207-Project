package Classes.retrieveInfo;

import java.io.IOException;

public interface AnimalDataSource {
    // gives the raw json response for animal query
    String getAnimalJson(String animalQuery) throws IOException, InterruptedException;

    // num results for the last response
    int lastNumResults();
}
