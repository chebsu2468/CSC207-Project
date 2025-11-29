package Classes.retrieveInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public final class AnimalFactory {
    private final AnimalConverter converter = new AnimalConverter();

    // Creates an animal from a JSON array string
    public Animal fromJsonArrayString(String jsonArrayString) {
        if (jsonArrayString == null || jsonArrayString.trim().isEmpty() || jsonArrayString.equals("[]")) {
            throw new IllegalArgumentException("Empty JSON for Animal");
        }
        JSONArray arr = new JSONArray(jsonArrayString);
        if (arr.length() == 0) throw new IllegalArgumentException("Empty JSON array");
        JSONObject a = arr.getJSONObject(0);
        JSONObject characteristics = a.optJSONObject("characteristics");
        JSONObject taxonomy = a.optJSONObject("taxonomy");

        Map<String,String> taxMap = new HashMap<>();
        if (taxonomy != null) {
            for (String k : taxonomy.keySet()) taxMap.put(k, taxonomy.optString(k));
        }

        String[] locations = toStringArray(a.optJSONArray("locations"));
        String preyStr = characteristics == null ? "" : characteristics.optString("prey", "");
        String[] prey = preyStr.isEmpty() ? new String[0] : preyStr.split(",\\s*");

        double lifespan = characteristics == null ? 0 : converter.parseAverageLifespanYears(characteristics.optString("lifespan", ""));
        double weight = characteristics == null ? 0 : converter.parseAverageWeightKg(characteristics.optString("weight", ""));
        double height = characteristics == null ? 0 : converter.parseAverageHeightCm(characteristics.optString("height", characteristics.optString("length", "")));

        return new Animal(
                a.optString("name"),
                taxMap,
                characteristics == null ? "" : characteristics.optString("habitat", ""),
                locations,
                prey,
                characteristics == null ? "" : characteristics.optString("most_distinctive_feature", ""),
                lifespan,
                characteristics == null ? "" : characteristics.optString("diet", ""),
                characteristics == null ? "" : characteristics.optString("lifestyle", ""),
                weight,
                height,
                characteristics == null ? "" : characteristics.optString("group", ""),
                characteristics == null ? "" : characteristics.optString("type", "")
        );
    }

    private String[] toStringArray(JSONArray arr) {
        if (arr == null) return new String[0];
        String[] out = new String[arr.length()];
        for (int i = 0; i < arr.length(); i++) out[i] = arr.optString(i);
        return out;
    }
}
