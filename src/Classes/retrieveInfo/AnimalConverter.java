package Classes.retrieveInfo;

public final class AnimalConverter {

    public double parseAverageLifespanYears(String s) {
        if (s == null || s.trim().isEmpty()) return 0;
        String in = s.toLowerCase().replaceAll("[()]", "").trim();
        in = in.replaceAll("years?", "year").replaceAll("yrs?", "year");

        if (in.startsWith("up to ")) {
            return valueWithUnitToYears(in.substring(6).trim());
        }
        String[] parts = splitRange(in);
        if (parts.length == 2) {
            double low = valueWithUnitToYears(parts[0]);
            double high = valueWithUnitToYears(parts[1]);
            if (low == 0) low = high;
            if (high == 0) high = low;
            return (low + high) / 2.0;
        }
        return valueWithUnitToYears(in);
    }

    public double parseAverageWeightKg(String s) {
        // Simplified: handles "10 kg", "22 lbs", "10-20 kg", "up to 5 kg"
        if (s == null || s.trim().isEmpty()) return 0;
        String in = s.toLowerCase().replaceAll("[()]", "").trim();
        String[] parts = splitRange(in);
        return parts.length == 2 ? (valueWithUnitToKg(parts[0]) + valueWithUnitToKg(parts[1])) / 2.0
                : valueWithUnitToKg(in);
    }

    public double parseAverageHeightCm(String s) {
        if (s == null || s.trim().isEmpty()) return 0;
        String in = s.toLowerCase().replaceAll("[()]", "").trim();
        String[] parts = splitRange(in);
        return parts.length == 2 ? (toCm(parts[0]) + toCm(parts[1])) / 2.0 : toCm(in);
    }

    // helpers
    private String[] splitRange(String s) {
        if (s.contains("-")) return s.split("-");
        if (s.contains("to")) return s.split("to");
        return new String[]{s};
    }

    private double valueWithUnitToYears(String token) {
        token = token.trim();
        double num = extractNumber(token);
        if (token.contains("month")) return num / 12.0;
        if (token.contains("week")) return num / 52.0;
        if (token.contains("day")) return num / 365.0;
        return num;
    }

    private double valueWithUnitToKg(String token) {
        token = token.trim();
        double num = extractNumber(token);
        if (token.contains("lb") || token.contains("lbs") || token.contains("pound")) return num * 0.45359237;
        if (token.contains("g") && !token.contains("kg")) return num / 1000.0;
        return num; // assume kg
    }

    private double toCm(String token) {
        token = token.trim();
        double num = extractNumber(token);
        if (token.contains("cm")) return num;
        if (token.contains("m")) return num * 100.0;
        if (token.contains("mm")) return num / 10.0;
        // imperial like "ft" or "in"
        if (token.contains("ft") || token.contains("'")) {
            String[] parts = token.replace("\"","").replace("'", " ").split("\\s+");
            try {
                double feet = Double.parseDouble(parts[0]);
                double inches = parts.length > 1 ? Double.parseDouble(parts[1]) : 0;
                return feet * 30.48 + inches * 2.54;
            } catch (Exception e) { return num * 30.48; }
        }
        if (token.contains("in") || token.contains("\"")) return num * 2.54;
        return num; // fallback
    }

    private double extractNumber(String s) {
        StringBuilder b = new StringBuilder();
        boolean seen = false;
        for (char c : s.toCharArray()) {
            if ((c >= '0' && c <= '9') || c == '.') { b.append(c); seen = true; }
            else if (seen) break;
        }
        try { return b.length() == 0 ? 0 : Double.parseDouble(b.toString()); }
        catch (NumberFormatException ex) { return 0; }
    }
}
