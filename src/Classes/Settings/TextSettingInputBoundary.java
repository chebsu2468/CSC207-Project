package Classes.Settings;

import java.awt.*;

public interface TextSettingInputBoundary {
    /**
     * Handle all the request from controller.
     *
     * @param request from controller
     */
    void editSettings(TextSettingInput request);

    /**
     * DTO for presenter.
     *
     * @return TextSettingOutput DTO
     */
    Color getTextColor();
    String getFontName();
    int getTextSize();
}
