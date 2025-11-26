package Classes.Settings;

import java.awt.Color;
import java.io.*;

import static Classes.Settings.SettingConstants.*;

public class TextSettingDataAccess implements TextSettingGateway{
    private final String filePath;

    public TextSettingDataAccess(String filePath) {
        this.filePath = filePath;
    }

    public TextSetting load() {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println(FILE_NOT_FOUND);
            TextSetting defaultSetting = getDefaultSetting();
            save(defaultSetting);
            return defaultSetting;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // Skip header
            String line = br.readLine();

            if (line == null || line.trim().isEmpty()) {
                return getDefaultSetting();
            }

            String[] values = line.split(",");
            int size = Integer.parseInt(values[0]);
            int r = Integer.parseInt(values[1]);
            int g = Integer.parseInt(values[2]);
            int b = Integer.parseInt(values[3]);
            String fontName = values[4].trim();

            return new TextSetting(size, new Color(r, g, b), fontName);

        } catch (IOException | NumberFormatException e) {
            System.err.println(FAILED_TO_LOAD + e.getMessage());
            return getDefaultSetting();
        }
    }

    public void save(TextSetting setting) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(SAVE_SETTING_HEADER_REGEX);
            bw.write(String.format(SAVE_SETTING_INFO_FORMAT,
                    setting.getTextSize(),
                    setting.getTextColor().getRed(),
                    setting.getTextColor().getGreen(),
                    setting.getTextColor().getBlue(),
                    setting.getFontName()));
        } catch (IOException e) {
            System.err.println(FAILED_TO_SAVE + e.getMessage());
        }
    }

    private TextSetting getDefaultSetting() {
        return new TextSetting(DEFAULT_FONT_SIZE, ERROR_COLOR, DEFAULT_FONT_NAME);
    }
}