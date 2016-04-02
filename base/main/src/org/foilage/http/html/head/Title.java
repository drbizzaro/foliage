package org.foilage.http.html.head;

import org.foilage.http.html.ComponentBuilder;
import org.foilage.http.html.HtmlComponentImpl;

public class Title extends HtmlComponentImpl implements HeadComponent {

    private final String label;

    public Title(String label) {
        this.label = label;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<title>");
        htmlBuilder.append(label);
        htmlBuilder.append("</title>\n");
    }

    public static class Builder implements ComponentBuilder<Title> {

        private String label;

        public Builder() {

        }

        public Builder label(String label) {
            this.label = label;

            return this;
        }

        @Override
        public Title build() {
            return new Title(label);
        }
    }
}
