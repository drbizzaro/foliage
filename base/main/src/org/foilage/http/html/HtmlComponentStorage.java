package org.foilage.http.html;

public interface HtmlComponentStorage<H extends HtmlComponent> {

    public void add(H htmlComponent);

    public void add(H... htmlComponent);
}
