package org.foilage.model;

public enum  MimeType {

    // Audio
    MP3(MimeCategory.AUDIO, "mpeg"),

    // Images
    JPG(MimeCategory.IMAGE, "jpeg"),
    GIF(MimeCategory.IMAGE, "gif"),
    PNG(MimeCategory.IMAGE, "png"),
    SHORTCUT_ICON(MimeCategory.IMAGE, "x-icon"),
    TIFF(MimeCategory.IMAGE, "tiff"),

    // Application
    PDF(MimeCategory.APPLICATION, "pdf"),

    // Texts
    CSS(MimeCategory.TEXT, "css"),
    JAVA_SCRIPT(MimeCategory.TEXT, "javascript"),
    HTML(MimeCategory.TEXT, "html"),
    PLAIN_TEXT(MimeCategory.TEXT, "plain"),
    RITCH_TEXT(MimeCategory.TEXT, "richtext"),
    CSV(MimeCategory.TEXT, "csv"),
    V_CARD(MimeCategory.TEXT, "x-vcard"),
    XML(MimeCategory.TEXT, "xml"),

    NONE(MimeCategory.NONE, "");

    private MimeCategory category;

    private String mimeType;

    private MimeType(MimeCategory category, String mimeType) {
        this.category = category;
        this.mimeType = mimeType;
    }

    public static MimeType parseType(String mimeString) {

        String[] mimeArray = mimeString.split("/");

        for(MimeType type: MimeType.values()) {

            if(type.category.getCategoryId().equalsIgnoreCase(mimeArray[0]) && type.getMimeType().equalsIgnoreCase(mimeArray[1])) {

                return type;
            }
        }

        throw new IllegalArgumentException("No valid mimetype found for "+mimeString);
    }

    public MimeCategory getCategory() {
        return category;
    }

    public String getMimeType() {
        return mimeType;
    }

    @Override
    public String toString() {
        return category + "/" + mimeType;
    }
}
