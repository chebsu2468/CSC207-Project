package AppPkg.Controllers;

import Classes.retrieveInfo.APIClass;
import Classes.retrieveInfo.Animal;
import Classes.Filter.FuzzySearch.FuzzySearchProvider;
import Classes.Filter.FuzzySearch.AnimalFuzzySearch;
import Classes.retrieveInfo.AnimalFactory;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchController {
    private final APIClass api;
    private final FuzzySearchProvider fuzzy;
    private final AnimalFactory factory;

    public SearchController() {
        this.api = new APIClass();
        this.fuzzy = new AnimalFuzzySearch();
        this.factory = new AnimalFactory();
    }

    // For dependency injection / testing:
    public SearchController(APIClass api, FuzzySearchProvider fuzzy, AnimalFactory factory) {
        this.api = api != null ? api : new APIClass();
        this.fuzzy = fuzzy != null ? fuzzy : new AnimalFuzzySearch();
        this.factory = factory != null ? factory : new AnimalFactory();
    }

    public SearchResult search(String rawQuery) {
        if (rawQuery == null) rawQuery = "";
        String animalName = rawQuery.toLowerCase().trim();

        if (animalName.isEmpty()) {
            return new SearchResult(false, "Please select an animal name.", new Animal[0], null);
        }

        String qForLink = animalName;
        if (animalName.contains(" ")) {
            qForLink = animalName.replace(" ", "%20");
        }

        String result = api.getAnimalData(qForLink);
        if (result == null) {
            // try fuzzy
            String suggestion = fuzzy.getSuggestion(animalName);
            if (suggestion != null && !suggestion.isEmpty()) {
                String htmlText = "<html>Animal not found. Did you mean: <a href=''>" + suggestion + "</a>?</html>";
                return new SearchResult(false, htmlText, new Animal[0], suggestion);
            } else {
                return new SearchResult(false, "Animal '" + animalName + "' not found.", new Animal[0], null);
            }
        }

        int numResults = api.numResults();
        if (numResults == 0) {
            return new SearchResult(false, "Please double check animal name.", new Animal[0], null);
        }

        if (numResults == 1) {
            // original Animal constructor accepted a single-element JSON array string
            Animal searched = factory.fromJsonArrayString(result);
            return new SearchResult(true, null, new Animal[]{searched}, null);
        }

        // numResults >= 2: create array of Animals
        JSONArray jsonArray = new JSONArray(result);
        Animal[] animals = new Animal[numResults];
        for (int i = 0; i < numResults; i++) {
            JSONObject singleAnimal = jsonArray.getJSONObject(i);
            // wrap into single-element array (keeps compatibility with original Animal constructor)
            JSONArray singleArray = new JSONArray();
            singleArray.put(singleAnimal);
            animals[i] = factory.fromJsonArrayString(singleArray.toString());
        }

        return new SearchResult(true, null, animals, null);
    }
}
