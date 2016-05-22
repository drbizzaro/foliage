package org.foilage.http.html.body;

public enum EncodingType {

    FORM("application/x-www-form-urlencoded"), MULTIPART("multipart/form-data"), PLAIN("text/plain");

    private final String encoding;

    EncodingType(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return encoding;
    }
}
