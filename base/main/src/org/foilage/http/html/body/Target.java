package org.foilage.http.html.body;

public enum Target {

    BLANK("_blank"), SELF("_self");

    private String value;

    Target(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
