package org.foilage.http.html.body;

import org.foilage.http.html.HtmlComponentImpl;

public class Raw extends HtmlComponentImpl {

    private final String rawHtml;

    public Raw(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append(rawHtml);
    }

}
