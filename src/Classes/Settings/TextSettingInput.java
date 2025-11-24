package Classes.Settings;


import javax.swing.*;
import java.awt.*;

public class TextSettingInput {
    private final TextSettingInteractor config;
    private final TextSettingInputBoundary interactor;

    public TextSettingInput(TextSettingInteractor config) {
        this.config = config;
        this.interactor = config;

    }

    public void updateChangesAll(TextSettingRequest request) {

        interactor.editSettings(request);

    }
}
