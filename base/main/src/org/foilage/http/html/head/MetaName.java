package org.foilage.http.html.head;

public enum MetaName {

    CHARSET("charset"),
    KEYWORDS("keywords"),
    DESCRIPTION("description"),
    AUTHOR("author"),
    GENERATOR("generator")
    ;

    private final String id;

    private MetaName(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
