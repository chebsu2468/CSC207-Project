package Classes.retrieveInfo;

import Classes.retrieveInfo.AnimalDataSource;
import Classes.retrieveInfo.Animal;
import Classes.retrieveInfo.AnimalFactory;
import Classes.retrieveInfo.SearchAnimalsPresenter;
import Classes.Filter.FuzzySearch.FuzzySearchProvider;
import java.io.IOException;

public class SearchAnimalsInteractor implements SearchAnimalsInputBoundary {
    private final AnimalDataSource dataSource;
    private final SearchAnimalsOutputBoundary presenter;
    private final AnimalFactory factory;
    private final FuzzySearchProvider fuzzyProvider;

    public SearchAnimalsInteractor(AnimalDataSource dataSource, SearchAnimalsOutputBoundary presenter,
                                   AnimalFactory factory, FuzzySearchProvider fuzzyProvider) {
        this.dataSource = dataSource;
        this.presenter = presenter;
        this.factory = factory;
        this.fuzzyProvider = fuzzyProvider;
    }

    @Override
    public void execute(SearchAnimalsInputData requestModel) {
        String q = requestModel.query == null ? "" : requestModel.query.trim().toLowerCase();
        if (q.isEmpty()) {
            presenter.present(new SearchAnimalsOutputData(false, "Please enter an animal name.", new Animal[0], null));
            return;
        }
        try {
            String json = dataSource.getAnimalJson(q);
            if (json == null) {
                // attempt fuzzy suggestion
                String suggestion = fuzzyProvider == null ? null : fuzzyProvider.getSuggestion(q);
                presenter.present(new SearchAnimalsOutputData(false,
                        suggestion == null ? "Animal '" + q + "' not found." : "Animal not found. Suggestion: " + suggestion,
                        new Animal[0], suggestion));
                return;
            }
            int num = dataSource.lastNumResults();
            if (num == 0) {
                presenter.present(new SearchAnimalsOutputData(false, "No results found.", new Animal[0], null));
                return;
            }
            if (num == 1) {
                Animal a = factory.fromJsonArrayString(json);
                presenter.present(new SearchAnimalsOutputData(true, null, new Animal[]{a}, null));
                return;
            }
            // multi results
            org.json.JSONArray arr = new org.json.JSONArray(json);
            Animal[] list = new Animal[arr.length()];
            for (int i = 0; i < arr.length(); i++) {
                org.json.JSONArray single = new org.json.JSONArray();
                single.put(arr.getJSONObject(i));
                list[i] = factory.fromJsonArrayString(single.toString());
            }
            presenter.present(new SearchAnimalsOutputData(true, null, list, null));
        } catch (IOException | InterruptedException e) {
            presenter.present(new SearchAnimalsOutputData(false, "Network error: " + e.getMessage(), new Animal[0], null));
        } catch (Exception e) {
            presenter.present(new SearchAnimalsOutputData(false, "Parsing error: " + e.getMessage(), new Animal[0], null));
        }
    }
}
