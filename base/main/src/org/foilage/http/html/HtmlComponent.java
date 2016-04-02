package org.foilage.http.html;

public interface HtmlComponent {

    public String getHtml(HtmlComponentImpl parent);

    public String getHtml(HtmlComponentImpl parent, boolean onSameRow);
}
