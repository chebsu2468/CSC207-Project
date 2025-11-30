package Classes.retrieveInfo;

import java.util.Map;
import java.util.StringJoiner;

public final class Animal {
    private final String name;
    private final Map<String,String> taxonomy;
    private final String habitat;
    private final String[] locations;
    private final String[] prey;
    private final String mostDistinctiveFeature;
    private final double lifespanYears;
    private final String diet;
    private final String lifestyle;
    private final double weightKg;
    private final double heightCm;
    private final String group;
    private final String type;

    public Animal(
            String name,
            Map<String,String> taxonomy,
            String habitat,
            String[] locations,
            String[] prey,
            String mostDistinctiveFeature,
            double lifespanYears,
            String diet,
            String lifestyle,
            double weightKg,
            double heightCm,
            String group,
            String type
    ) {
        this.name = name;
        this.taxonomy = taxonomy;
        this.habitat = habitat;
        this.locations = locations == null ? new String[0] : locations;
        this.prey = prey == null ? new String[0] : prey;
        this.mostDistinctiveFeature = mostDistinctiveFeature;
        this.lifespanYears = lifespanYears;
        this.diet = diet;
        this.lifestyle = lifestyle;
        this.weightKg = weightKg;
        this.heightCm = heightCm;
        this.group = group;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Map<String,String> getTaxonomy() {
        return taxonomy;
    }

    public String getHabitat() {
        return habitat;
    }

    public String[] getLocation() {
        return locations.clone();
    }

    public String[] getPrey() {
        return prey.clone();
    }

    public String getMostDistinctiveFeature() {
        return mostDistinctiveFeature;
    }

    public double getLifespan() {
        return lifespanYears;
    }

    public String getDiet() {
        return diet;
    }

    public String getLifestyle() {
        return lifestyle;
    }

    public double getWeight() {
        return weightKg;
    }

    public double getHeight() {
        return heightCm;
    }

    public String getGroup() {
        return group;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("Name: " + safe(name));
        sj.add("Taxonomy: " + formatTaxonomy());
        sj.add("Habitat: " + safe(habitat));
        sj.add("Locations: " + joinArray(locations));
        sj.add("Prey: " + joinArray(prey));
        sj.add("Most Distinctive Feature: " + safe(mostDistinctiveFeature));
        sj.add(String.format("Lifespan (yrs): %.2f", lifespanYears));
        sj.add("Diet: " + safe(diet));
        sj.add("Lifestyle: " + safe(lifestyle));
        sj.add(String.format("Weight (kg): %.2f", weightKg));
        sj.add(String.format("Height (cm): %.2f", heightCm));
        sj.add("Group: " + safe(group));
        sj.add("Type: " + safe(type));
        return sj.toString();
    }

    private String safe(String s) { return (s == null || s.isEmpty()) ? "N/A" : s; }

    private String joinArray(String[] arr){
        if (arr == null || arr.length == 0) return "N/A";
        StringJoiner j = new StringJoiner(", ");
        for (String x : arr) j.add(x);
        return j.toString();
    }

    private String formatTaxonomy() {
        if (taxonomy == null || taxonomy.isEmpty()) return "N/A";
        StringJoiner j = new StringJoiner(", ");
        for (Map.Entry<String,String> e : taxonomy.entrySet()) {
            j.add(cap(e.getKey()) + ": " + safe(e.getValue()));
        }
        return j.toString();
    }

    private String cap(String s){
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
