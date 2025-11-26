package Classes.Settings;

public interface TextSettingGateway {
    TextSetting load();
    void save(TextSetting setting);
}
