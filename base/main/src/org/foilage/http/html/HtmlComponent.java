package org.foilage.http.html;

public interface HtmlComponent {

    String getHtml(HtmlComponentImpl parent);

    String getHtml(HtmlComponentImpl parent, boolean onSameRow);
}
