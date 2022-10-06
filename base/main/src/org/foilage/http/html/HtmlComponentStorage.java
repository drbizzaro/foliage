package org.foilage.http.html;

public interface HtmlComponentStorage<H extends HtmlComponent> {

    void add(H htmlComponent);

    void add(H... htmlComponent);
}
