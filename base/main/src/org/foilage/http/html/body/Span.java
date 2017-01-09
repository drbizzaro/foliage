package org.foilage.http.html.body;

import org.foilage.http.html.*;

import java.util.ArrayList;
import java.util.List;

import static org.foilage.utils.checkers.NullChecker.notNull;

public class Span extends HtmlComponentImpl {

    private final Builder builder;

    public Span(Builder builder) {

        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<span ");
        htmlBuilder.append(builder.addGeneralComponents());
        htmlBuilder.append(">\n");

        for(HtmlComponent component: builder.components){
            htmlBuilder.append(component.getHtml(this, false));
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</span>\n");
    }

    public static class Builder extends BaseEventComponentBuilder<Span,Builder> implements HtmlComponentStorage<HtmlComponent> {

        private List<HtmlComponent> components;

        public Builder() {

            components = new ArrayList<>();
        }

        public Builder(String text) {

            components = new ArrayList<>();
            components.add(new Text(text));
        }

        public Builder(HtmlComponent component) {

            components = new ArrayList<>();
            components.add(component);
        }

        @Override
        public Span build() {
            return new Span(this);
        }

        @Override
        public void add(HtmlComponent htmlComponent) {

            components.add(notNull(htmlComponent));
        }

        @Override
        public void add(HtmlComponent... htmlComponent) {

            for(HtmlComponent comp: htmlComponent) {

                components.add(notNull(comp));
            }
        }
    }

}
