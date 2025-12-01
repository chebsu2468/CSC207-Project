package Classes.retrieveInfo;

import Config.ProjectConfig;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIAnimalDataSource implements AnimalDataSource {
    private final String apiUrl;
    private final String apiKey;
    private final HttpClient client = HttpClient.newHttpClient();
    private String lastResponse = "[]";

    public APIAnimalDataSource() {
        this.apiUrl = ProjectConfig.NINJA_API_URL;
        this.apiKey = ProjectConfig.getNinjaApiKey();
    }

    @Override
    public String getAnimalJson(String animalQuery) throws IOException, InterruptedException {
        String q = animalQuery.replace(" ", "%20");
        HttpRequest request = HttpRequest.newBuilder(URI.create(apiUrl + q))
                .header("X-Api-Key", apiKey)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            lastResponse = response.body();
            if (lastResponse == null || lastResponse.trim().isEmpty() || lastResponse.equals("[]")) {
                return null;
            }
            return lastResponse;
        } else {
            lastResponse = "[]";
            return null;
        }
    }

    @Override
    public int lastNumResults() {
        try {
            JSONArray arr = new JSONArray(lastResponse);
            return arr.length();
        } catch (Exception e) {
            return 0;
        }
    }
}
