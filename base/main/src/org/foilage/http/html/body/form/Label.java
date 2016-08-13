package org.foilage.http.html.body.form;

import org.foilage.http.html.BaseEventComponentBuilder;
import org.foilage.http.html.HtmlComponentImpl;

public class Label extends HtmlComponentImpl {

    private final Builder builder;

    public Label(Builder builder) {

        this.builder = builder;
    }

    public Label(String text, String forElement) {

        this.builder = new Builder(text, forElement);
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<label");

        if(builder.forElement!=null) {

            htmlBuilder.append(" for=\"");
            htmlBuilder.append(builder.forElement);
            htmlBuilder.append("\"");
        }

        if(builder.form!=null) {

            htmlBuilder.append(" form=\"");
            htmlBuilder.append(builder.form);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">");
        htmlBuilder.append(builder.text);
        htmlBuilder.append("</label>\n");
    }

    public static class Builder extends BaseEventComponentBuilder<Label,Builder> {

        private String text;

        private String forElement = null;

        private String form = null;

        public Builder(String text) {
            this.text = text;
        }

        public Builder(String text, String forElement) {
            this.text = text;
            this.forElement = forElement;
        }

        @Override
        public Label build() {
            return new Label(this);
        }

        public Builder text(String text) {

            this.text = text; return this;
        }

        public Builder forElement(String forElement) {

            this.forElement = forElement; return this;
        }

        public Builder form(String form) {

            this.form = form; return this;
        }
    }

}
