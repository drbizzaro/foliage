package org.foilage.http.html.body;

import org.foilage.http.html.*;

import java.util.ArrayList;
import java.util.List;

public class Body extends HtmlComponentImpl {

    private final List<HtmlComponent> components;

    public Body(List<HtmlComponent> components) {
        this.components = components;

        tabOffset = 0;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<body>\n");

        for(HtmlComponent component: components){
            htmlBuilder.append(component.getHtml(this, false));
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</body>\n");
    }

    public static class Builder implements ComponentBuilder<Body>, HtmlComponentStorage<HtmlComponent> {

        private List<HtmlComponent> components;

        public Builder() {

            components = new ArrayList<>();
        }

        @Override
        public Body build() {
            return new Body(components);
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
