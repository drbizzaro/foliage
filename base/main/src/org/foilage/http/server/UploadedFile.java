package org.foilage.http.server;

import org.foilage.model.MimeType;

public class UploadedFile {

    private final String name;

    private final MimeType mimeType;

    private final byte[] fileData;

    public UploadedFile(String name, MimeType mimeType, byte[] fileData) {
        this.name = name;
        this.mimeType = mimeType;
        this.fileData = fileData;
    }

    public String getName() {
        return name;
    }

    public MimeType getMimeType() {
        return mimeType;
    }

    public byte[] getFileData() {
        return fileData;
    }
}
