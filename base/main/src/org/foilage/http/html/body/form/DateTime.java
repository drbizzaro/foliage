package org.foilage.http.html.body.form;

import org.foilage.http.html.HtmlComponentImpl;

public class DateTime extends HtmlComponentImpl {

    private final Builder builder;

    public DateTime(Builder builder) {

        this.builder = builder;
    }

    public DateTime(String name) {

        Builder b = new Builder();

        b.name(name);

        this.builder = b;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<input");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.placeholder!=null) {

            htmlBuilder.append(" placeholder=\"");
            htmlBuilder.append(builder.placeholder);
            htmlBuilder.append("\"");
        }

        if(builder.required) {

            htmlBuilder.append(" required");
        }

        htmlBuilder.append(" type=\"datetime-local\"");

        htmlBuilder.append(">\n");
    }

    public static class Builder extends InputBuilder<DateTime,Builder> {

        private String placeholder = null;

        private boolean required = false;

        public Builder() {
        }

        public Builder(String name, String value) {

            this.name(name);
            this.value(value);;
        }

        public Builder placeholder(String placeholder) {

            this.placeholder = placeholder; return this;
        }

        public Builder required(boolean required) {

            this.required = required; return this;
        }


        @Override
        public DateTime build() {
            return new DateTime(this);
        }
    }

}
