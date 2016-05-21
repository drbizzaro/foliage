package org.foilage.http.html.body;

import org.foilage.http.html.BaseEventComponentBuilder;
import org.foilage.http.html.HtmlComponent;
import org.foilage.http.html.HtmlComponentImpl;
import org.foilage.http.html.Util;

import java.util.ArrayList;
import java.util.List;

public class Form extends HtmlComponentImpl {

    private final Builder builder;

    public Form(Builder builder) {
        this.builder = builder;

        tabOffset = 0;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<form");

        if(builder.action!=null) {

            htmlBuilder.append("action=\"");
            htmlBuilder.append(builder.action);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

        for(HtmlComponent component: builder.components){
            htmlBuilder.append(component.getHtml(this, false));
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</form>\n");
    }

    public static class Builder extends BaseEventComponentBuilder<Form> {

        private List<HtmlComponent> components;

        private String action = null;

        public Builder() {
            this.components = new ArrayList<>();
        }

        @Override
        public Form build() {
            return new Form(this);
        }

        private Builder add(HtmlComponent component) {

            this.components.add(component);

            return this;
        }

        private Builder add(HtmlComponent... component) {

            for(HtmlComponent comp: component) {

                this.components.add(comp);
            }

            return this;
        }

        private Builder action(String action) {

            this.action = action;

            return this;
        }

    }

}
