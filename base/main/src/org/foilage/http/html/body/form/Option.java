package org.foilage.http.html.body.form;

import org.foilage.http.html.BaseEventComponentBuilder;
import org.foilage.http.html.HtmlComponentImpl;

public class Option extends HtmlComponentImpl {

    private final Builder builder;

    public Option(Builder builder) {
        this.builder = builder;
    }

    public Option(String label, String value) {
        this.builder = new Builder(label, value);
    }

    public Option(String label, String value, boolean selected) {
        this.builder = new Builder(label, value, selected);
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<option ");
        htmlBuilder.append("value=\"");
        htmlBuilder.append(builder.value);
        htmlBuilder.append("\"");
        if(builder.selected) {
            htmlBuilder.append(" selected");
        }
        if(builder.disabled) {
            htmlBuilder.append(" disabled");
        }
        htmlBuilder.append(">");
        htmlBuilder.append(builder.label);
        htmlBuilder.append("</option>");
    }

    public static class Builder extends BaseEventComponentBuilder<Option,Builder> {

        private String label = "";

        private String value = "";

        private boolean selected = false;

        private boolean disabled = false;

        public Builder() {
        }

        public Builder label(String label) {

            this.label = label; return this;
        }

        public Builder value(String value) {

            this.value = value; return this;
        }

        public Builder selected(boolean selected) {

            this.selected = selected; return this;
        }

        public Builder disabled(boolean disabled) {

            this.disabled = disabled; return this;
        }

        public Builder(String label, String value) {
            this.label = label;
            this.value = value;
        }

        public Builder(String label, String value, boolean selected) {
            this.label = label;
            this.value = value;
            this.selected = selected;
        }

        @Override
        public Option build() {
            return new Option(this);
        }
    }

}
