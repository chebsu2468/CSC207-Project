package Classes.Settings;

import static Classes.Settings.SettingConstants.*;

public class TextSettingRequest {
    public String style = DEFAULT_FONT_NAME;
    public int size = DEFAULT_FONT_SIZE;
    public String color = NAME_BLACK;

    public TextSettingRequest(String color, int size, String style) {
        if (color == null || size == 0 || style == null){
            return;
        }
        this.color = color;
        this.size = size;
        this.style = style;
    }

}