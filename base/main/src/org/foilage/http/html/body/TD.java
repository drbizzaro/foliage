package org.foilage.http.html.body;

import org.foilage.http.html.*;

import java.util.ArrayList;
import java.util.List;

import static org.foilage.utils.checkers.GreaterThenZeroChecker.greaterThenZero;
import static org.foilage.utils.checkers.NullChecker.notNull;

public class TD extends TableCell {

    private final Builder builder;

    public TD(Builder builder) {

        this.builder = notNull(builder);
    }

    public TD(String text) {

        this.builder = new Builder(text);
    }

    public TD(HtmlComponent comp) {

        this.builder = new Builder(comp);
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<td ");
        htmlBuilder.append(builder.addGeneralComponents());
        htmlBuilder.append(">\n");

        for(HtmlComponent component: builder.componentList){
            htmlBuilder.append(component.getHtml(this, false));
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</td>");

        if(!onSameRow) {

            htmlBuilder.append("\n");
        }
    }

    public static class Builder extends BaseEventComponentBuilder<TD,Builder> implements HtmlComponentStorage<HtmlComponent> {

        private int colSpan;

        private int rowSpan;

        private String headers;

        private List<HtmlComponent> componentList;

        public Builder() {

            this.componentList = new ArrayList<>();
        }

        public Builder(String text) {

            this.componentList = new ArrayList<>();

            this.componentList.add(new Text(text));
        }

        public Builder(HtmlComponent comp) {

            this.componentList = new ArrayList<>();

            this.componentList.add(comp);
        }

        @Override
        public TD build() {
            return new TD(this);
        }

        public Builder colSpan(int colSpan) {

            this.colSpan = greaterThenZero(colSpan); return this;
        }

        public Builder rowSpan(int rowSpan) {

            this.rowSpan = greaterThenZero(rowSpan); return this;
        }

        public Builder headers(String headers) {

            this.headers = notNull(headers); return this;
        }

        @Override
        public void add(HtmlComponent htmlComponent) {

            componentList.add(notNull(htmlComponent));
        }

        @Override
        public void add(HtmlComponent... htmlComponent) {

            for(HtmlComponent comp: htmlComponent) {

                componentList.add(notNull(comp));
            }
        }
    }
}
