package org.foilage.http.html.body;

import org.foilage.http.html.*;

import java.util.ArrayList;
import java.util.List;

public class Div extends HtmlComponentImpl {

    private final List<HtmlComponent> components;

    private final Builder builder;

    public Div(List<HtmlComponent> components, Builder builder) {
        this.components = components;
        this.builder = builder;

        tabOffset = 0;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<div ");
        htmlBuilder.append(builder.addGeneralComponents());
        htmlBuilder.append(">\n");

        for(HtmlComponent component: components){
            htmlBuilder.append(component.getHtml(this, false));
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</div>");

        if(builder.rowBreak) {

            htmlBuilder.append("\n");
        }
    }

    public static class Builder extends BaseEventComponentBuilder<Div,Builder> implements HtmlComponentStorage<HtmlComponent> {

        private List<HtmlComponent> components;

        private boolean rowBreak = true;

        public Builder() {

            components = new ArrayList<>();
        }

        public Builder rowBreak(boolean rowBreak) {

            this.rowBreak = rowBreak; return this;
        }

        @Override
        public Div build() {
            return new Div(components, this);
        }

        @Override
        public void add(HtmlComponent htmlComponent) {

            components.add(htmlComponent);
        }

        @Override
        public void add(HtmlComponent... htmlComponent) {

            for(HtmlComponent comp: htmlComponent) {

                components.add(comp);
            }
        }
    }

}
