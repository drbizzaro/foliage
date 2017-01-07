package org.foilage.http.html.body;

import org.foilage.http.html.*;

import java.util.ArrayList;
import java.util.List;

public class Table extends HtmlComponentImpl {

    private final Builder builder;

    public Table(Builder builder) {

        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<table ");
        htmlBuilder.append(builder.addGeneralComponents());
        htmlBuilder.append(">\n");

        for(HtmlComponent component: builder.rowList){
            htmlBuilder.append(component.getHtml(this, false));
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</table>");

        if(!onSameRow) {

            htmlBuilder.append("\n");
        }
    }

    public static class Builder extends BaseEventComponentBuilder<Table,Builder> {

        private List<TR> rowList;

        public Builder() {

            this.rowList = new ArrayList<>();
        }

        public Builder(TR... row) {

            this.rowList = new ArrayList<>();

            for(TR tr: row) {

                rowList.add(tr);
            }
        }

        @Override
        public Table build() {
            return new Table(this);
        }

        public void add(TR row) {

            rowList.add(row);
        }

        public void add(TR... row) {

            for(TR tr: row) {

                rowList.add(tr);
            }
        }
    }

}
