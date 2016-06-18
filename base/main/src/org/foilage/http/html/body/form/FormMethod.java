package org.foilage.http.html.body.form;

public enum FormMethod {

    FORM("FORM"), GET("get"), POST("post");

    private final String value;

    FormMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
