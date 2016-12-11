package org.foilage.http.html.body.form;

import org.foilage.http.html.HtmlComponentImpl;

import java.util.ArrayList;
import java.util.List;

public class Select extends HtmlComponentImpl {

    private final Builder builder;

    public Select(Builder builder) {
        this.builder = builder;
    }

    public Select(String name, List<Option> option) {

        this.builder = new Builder(name, option);
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<select ");

        htmlBuilder.append(builder.addGeneralComponents());

        htmlBuilder.append(">\n");

        for(Option option: builder.option) {

            htmlBuilder.append(option.getHtml(this, false));
        }

        htmlBuilder.append("</select>\n");
    }

    public static class Builder extends InputBuilder<Select,Builder> {

        private List<Option> option = new ArrayList<>();

        public Builder(String name, List<Option> option) {
            this.name(name);
            this.option = option;
        }

        @Override
        public Select build() {
            return new Select(this);
        }
    }

}
