package org.foilage.http.html.body.form;

import org.foilage.http.html.HtmlComponentImpl;

public class FileUpload extends HtmlComponentImpl {

    private final FileUpload.Builder builder;

    public FileUpload(FileUpload.Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<input");

        htmlBuilder.append(builder.addGeneralComponents());

        htmlBuilder.append(" type=\"file\"");

        htmlBuilder.append(">\n");
    }

    public static class Builder extends InputBuilder<FileUpload,FileUpload.Builder> {

        @Override
        public FileUpload build() {
            return new FileUpload(this);
        }
    }

}


