package Classes.Settings;

import javax.swing.*;
import java.awt.*;

import static Classes.Settings.SettingConstants.*;

public class TextSettingInteractor implements TextSettingInputBoundary {

    private final TextSettingDataAccess settingFetcher;
    private TextSettingOutputBoundary presenter;
    private final TextSetting config;

    public TextSettingInteractor(String filePath) {
        this.settingFetcher = new TextSettingDataAccess(filePath);
        this.config = settingFetcher.load();
        this.presenter = new TextSettingOutput(config);

    }

    @Override
    public void editSettings(TextSettingRequest request) {

        // Color mapping
        Color finalColor = switch (request.color.toLowerCase()) {
            case NAME_PURPLE -> PURPLE;
            case NAME_BLUE -> BLUE;
            case NAME_GREEN -> GREEN;
            default -> DEFAULT_COLOR;
        };

        // Size mapping
        int finalSize = switch (request.size) {
            case 1 -> FONT_SIZE_ONE;
            case 2 -> FONT_SIZE_TWO;
            case 4 -> FONT_SIZE_FOUR;
            case 5 -> FONT_SIZE_FIVE;
            default -> DEFAULT_FONT_SIZE;
        };

        // Color（font color）
        config.setTextColor(finalColor);

        // Font Size
        config.setTextSize(finalSize);

        // String (font name)
        config.setTextStyle(request.style);

        // Save changes
        settingFetcher.save(config);

        presenter = new TextSettingOutput(config);

    }

    public void updateALL(Window obj) {
        // Display the UI changes
        presenter.presentSettingOutput(obj);
    }

    public void updateALL(JDialog obj) {
        // Display the UI changes
        presenter.presentSettingOutput(obj);
    }

    public void updateALL(JFrame obj) {
        // Display the UI changes
        presenter.presentSettingOutput(obj);
    }

    // Optional getters if needed by presenter or UI
    public Color getColor() {
        return config.getTextColor();
    }

    public int getSize() {
        return config.getTextSize();
    }

    public String getStyleName() {
        return config.getFontName();
    }

    public Font getStyle() {
        return config.getFont();
    }
}
