package org.foilage.http.html.body.form;

import org.foilage.http.html.HtmlComponentImpl;

public class Hidden extends HtmlComponentImpl {

    private final Builder builder;

    public Hidden(String name, String value) {

        this.builder = new Hidden.Builder(name, value);
    }

    public Hidden(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<input");

        htmlBuilder.append(builder.addGeneralComponents());

        htmlBuilder.append(" type=\"hidden\"");

        htmlBuilder.append(">\n");
    }

    public static class Builder extends InputBuilder<Hidden,Builder> {

        private String placeholder = null;

        private boolean required = false;

        public Builder() {
        }

        public Builder(String name, String value) {

            this.name(name);
            this.value(value);
        }

        public Builder placeholder(String placeholder) {

            this.placeholder = placeholder; return this;
        }

        public Builder required(boolean required) {

            this.required = required; return this;
        }


        @Override
        public Hidden build() {
            return new Hidden(this);
        }
    }

}
