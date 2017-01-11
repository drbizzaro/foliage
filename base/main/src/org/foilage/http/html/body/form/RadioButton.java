package org.foilage.http.html.body.form;

import org.foilage.http.html.HtmlComponentImpl;

import static org.foilage.utils.checkers.NullChecker.notNull;

public class RadioButton extends HtmlComponentImpl {

    private final Builder builder;

    public RadioButton(Builder builder) {

        this.builder = notNull(builder);
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<input");

        htmlBuilder.append(builder.addGeneralComponents());

        htmlBuilder.append(" type=\"radio\"");

        if(builder.checked) {

            htmlBuilder.append(" checked");
        }

        htmlBuilder.append(">");
        if(builder.buttonText!=null) {
            htmlBuilder.append(builder.buttonText);
        }
        htmlBuilder.append("\n");
    }

    public static class Builder extends InputBuilder<RadioButton,Builder> {

        private boolean checked = false;

        private String buttonText;

        public Builder() {
        }

        @Override
        public RadioButton build() {
            return new RadioButton(this);
        }

        public Builder checked(boolean checked) {

            this.checked = checked; return this;
        }

        public Builder buttonText(String buttonText) {

            this.buttonText = buttonText; return this;
        }
    }
}
