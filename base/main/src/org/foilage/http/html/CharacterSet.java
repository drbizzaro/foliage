package org.foilage.http.html;

public enum CharacterSet {

    UTF8("UTF-8"), LATIN("ISO-8859-1");

    private final String encoding;

    CharacterSet(String encoding) {

        this.encoding = encoding;
    }

    public String getEncoding() {
        return encoding;
    }
}
