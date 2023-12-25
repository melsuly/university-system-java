package ui;

import java.util.Locale;
import java.util.ResourceBundle;

import core.AppState;

public class LanguageManager {
    private static LanguageManager instance;
    private ResourceBundle resourceBundle;
    private Locale locale;

    private LanguageManager(Locale locale) {
        this.locale = locale;
        loadResourceBundle();
    }

    public static synchronized LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager(AppState.getInstance().getCurrentLocale());
        }
        return instance;
    }

    private void loadResourceBundle() {
        resourceBundle = ResourceBundle.getBundle("StringsBundle", locale);
    }

    public String translate(String key) {
        return resourceBundle.getString(key);
    }

    public void changeLocale(Locale newLocale) {
        this.locale = newLocale;
        loadResourceBundle();
    }
}
