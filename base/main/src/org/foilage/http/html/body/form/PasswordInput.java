package org.foilage.http.html.body.form;

import org.foilage.http.html.HtmlComponentImpl;

public class PasswordInput extends HtmlComponentImpl {

    private final Builder builder;

    public PasswordInput(Builder builder) {

        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<input");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.placeholder!=null) {

            htmlBuilder.append("placeholder=\"");
            htmlBuilder.append(builder.placeholder);
            htmlBuilder.append("\"");
        }

        if(builder.required) {

            htmlBuilder.append(" required");
        }

        htmlBuilder.append(" type=\"password\"");

        htmlBuilder.append(">\n");

    }

    public static class Builder extends InputBuilder<PasswordInput> {

        private String placeholder = null;

        private boolean required = false;

        public Builder() {

            autoComplete(false);
        }

        public Builder placeholder(String placeholder) {

            this.placeholder = placeholder; return this;
        }

        public Builder required(boolean required) {

            this.required = required; return this;
        }


        @Override
        public PasswordInput build() {
            return new PasswordInput(this);
        }
    }

}
