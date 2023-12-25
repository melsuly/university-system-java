package model;

public enum Language {
    KAZAKH("Қазақша", "kk", "KZ"),
    RUSSIAN("Русский", "ru", "RU"),
    ENGLISH("English", "en", "US");

    private final String name;
    private final String code;
    private final String region;

    Language(String name, String code, String region) {
        this.name = name;
        this.code = code;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getRegion() {
        return region;
    }
}
