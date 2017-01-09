package org.foilage.http.html.body;

import org.foilage.http.html.BaseEventComponentBuilder;
import org.foilage.http.html.HtmlComponent;
import org.foilage.http.html.HtmlComponentImpl;
import org.foilage.http.html.Util;

import java.util.ArrayList;
import java.util.List;

public class TR extends HtmlComponentImpl {

    private final Builder builder;

    public TR(Builder builder) {

        this.builder = builder;
    }

    public TR(TableCell... cell) {

        this.builder = new Builder(cell);
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<tr ");
        htmlBuilder.append(builder.addGeneralComponents());
        htmlBuilder.append(">\n");

        for(HtmlComponent component: builder.rowData){
            htmlBuilder.append(component.getHtml(this, false));
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</tr>");

        if(!onSameRow) {

            htmlBuilder.append("\n");
        }
    }

    public static class Builder extends BaseEventComponentBuilder<TR,Builder> {

        private List<TableCell> rowData;

        public Builder() {

            this.rowData = new ArrayList<>();
        }

        public Builder(TableCell... cell) {

            this.rowData = new ArrayList<>();

            for(TableCell c: cell) {

                this.rowData.add(c);
            }
        }

        @Override
        public TR build() {
            return new TR(this);
        }

        public void add(TableCell cell) {

            this.rowData.add(cell);
        }

        public void add(TableCell... cell) {

            for(TableCell c: cell) {

                this.rowData.add(c);
            }
        }
    }

}
