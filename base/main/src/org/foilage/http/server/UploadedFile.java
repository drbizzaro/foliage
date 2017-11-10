package org.foilage.http.server;

public class UploadedFile {

    private final byte[] fileData;

    public UploadedFile(byte[] fileData) {
        this.fileData = fileData;
    }

    public byte[] getFileData() {
        return fileData;
    }
}
