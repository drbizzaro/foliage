package org.foilage.http.html.body.form;

import org.foilage.http.html.HtmlComponentImpl;

public class TextArea extends HtmlComponentImpl {

    private final Builder builder;

    public TextArea(Builder builder) {
        this.builder = builder;
    }

    public TextArea(String name) {

        Builder b = new Builder();

        b.name(name);

        this.builder = b;
    }

    public TextArea(String name, String text) {

        Builder b = new Builder();

        b.name(name);
        b.text(text);

        this.builder = b;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<textarea");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.placeholder!=null) {

            htmlBuilder.append("placeholder=\"");
            htmlBuilder.append(builder.placeholder);
            htmlBuilder.append("\"");
        }

        if(builder.required) {

            htmlBuilder.append(" required");
        }

        htmlBuilder.append(">\n");

        htmlBuilder.append(builder.text);

        htmlBuilder.append("</textarea>\n");

    }

    public static class Builder extends InputBuilder<TextArea,Builder> {

        private String text = "";

        private String placeholder = null;

        private boolean required = false;

        public Builder text(String text) {

            this.text = text; return this;
        }

        public Builder placeholder(String placeholder) {

            this.placeholder = placeholder; return this;
        }

        public Builder required(boolean required) {

            this.required = required; return this;
        }


        @Override
        public TextArea build() {
            return new TextArea(this);
        }
    }

}
