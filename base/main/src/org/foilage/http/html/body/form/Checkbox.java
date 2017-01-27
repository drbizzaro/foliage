package org.foilage.http.html.body.form;

import org.foilage.http.html.HtmlComponentImpl;

public class Checkbox extends HtmlComponentImpl {

    private final Builder builder;

    public Checkbox(Builder builder) {

        this.builder = builder;
    }

    public Checkbox(String name, String value) {

        Builder b = new Builder(name, value);

        this.builder = b;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<input");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.checked) {

            htmlBuilder.append(" checked");
        }

        htmlBuilder.append(" type=\"checkbox\"");

        htmlBuilder.append(">");

        if(builder.title!= null) {

            htmlBuilder.append(" ");
            htmlBuilder.append(builder.title);
        }

        htmlBuilder.append("\n");
    }

    public static class Builder extends InputBuilder<Checkbox,Builder> {

        private boolean checked = false;

        private String title = null;

        public Builder() {
        }

        public Builder(String name, String value) {

            this.name(name);
            this.value(value);
            this.title(value);
        }

        public Builder required(boolean checked) {

            this.checked = checked; return this;
        }

        public Builder title(String title) {

            this.title = title; return this;
        }

        @Override
        public Checkbox build() {
            return new Checkbox(this);
        }
    }

}
