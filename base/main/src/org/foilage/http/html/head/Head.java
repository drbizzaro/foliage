package org.foilage.http.html.head;

import org.foilage.http.html.ComponentBuilder;
import org.foilage.http.html.HtmlComponent;
import org.foilage.http.html.HtmlComponentImpl;
import org.foilage.http.html.HtmlComponentStorage;

import java.util.ArrayList;
import java.util.List;

public class Head extends HtmlComponentImpl {

        private final List<HtmlComponent> components;

        public Head(List<HtmlComponent> components) {
            this.components = components;

            this.tabOffset = 0;
        }

    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<head>\n");

        for(HtmlComponent component: components){
            htmlBuilder.append(component.getHtml(this, false));
        }

        htmlBuilder.append("</head>\n\n");
    }

    public static class Builder implements ComponentBuilder<Head>, HtmlComponentStorage<HeadComponent> {

        private List<HtmlComponent> components;

        public Builder() {

            this.components = new ArrayList<HtmlComponent>();
        }

        public void add(HeadComponent component) {

            components.add(component);
        }

        @Override
        public void add(HeadComponent... component) {

            for(HeadComponent comp: component) {

                components.add(comp);
            }
        }

        @Override
        public Head build() {
            return new Head(components);
        }
    }
}
