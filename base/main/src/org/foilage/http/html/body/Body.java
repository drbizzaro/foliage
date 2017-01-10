package org.foilage.http.html.body;

import org.foilage.http.html.*;

import java.util.ArrayList;
import java.util.List;

public class Body extends HtmlComponentImpl {

    private final Builder builder;

    public Body(Builder builder) {
        this.builder = builder;

        tabOffset = 0;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<body ");
        htmlBuilder.append(builder.addGeneralComponents());
        htmlBuilder.append(">\n");

        for(HtmlComponent component: builder.components){
            htmlBuilder.append(component.getHtml(this, false));
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</body>\n");
    }

    public static class Builder extends BaseEventComponentBuilder<Body, Builder> implements HtmlComponentStorage<HtmlComponent> {

        private List<HtmlComponent> components;

        public Builder() {

            components = new ArrayList<>();
        }

        @Override
        public Body build() {
            return new Body(this);
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
