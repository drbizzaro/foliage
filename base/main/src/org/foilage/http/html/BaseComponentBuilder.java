package org.foilage.http.html;

public abstract class BaseComponentBuilder<B,T> implements ComponentBuilder<B> {

    protected Character accessKey = null;

    protected String cssClass = null;

    protected Boolean contentEditable = null;

    //TODO: Implement contextmenu

    //TODO: Implement data-*

    protected TextDirection dir = null;

    //TODO: Implement draggable

    //TODO: Implement dropzone

    protected boolean hidden = false;

    protected String id = null;

    protected String lang = null;

    protected Boolean spellCheck = null;

    protected String style = null;

    protected int tabIndex = 0;

    protected String title = null;

    //TODO: Implement translate

    /**
     * This method should be called from the html generation in the extending class constructor.
     * @return String The generated html code to be included in the extending class constructor.
     * */
    public String addGeneralComponents() {

        StringBuilder html = new StringBuilder();

        if(accessKey!=null) {
            html.append(" accesskey=\"");
            html.append(accessKey);
            html.append("\"");
        }

        if(cssClass!=null) {
            html.append(" class=\"");
            html.append(cssClass);
            html.append("\"");
        }

        if(contentEditable!=null) {
            html.append(" contenteditable=\"");
            html.append(contentEditable.toString());
            html.append("\"");
        }

        if(dir!=null) {
            html.append(" dir=\"");
            html.append(dir.getValue());
            html.append("\"");
        }

        if(hidden) {

            html.append(" hidden");
        }

        if(id!=null) {
            html.append(" id=\"");
            html.append(id);
            html.append("\"");
        }

        if(lang!=null) {
            html.append(" lang=\"");
            html.append(lang);
            html.append("\"");
        }

        if(spellCheck !=null) {
            html.append(" spellcheck=\"");
            html.append(spellCheck.toString());
            html.append("\"");
        }

        if(style!=null) {
            html.append(" style=\"");
            html.append(style);
            html.append("\"");
        }

        if(tabIndex>0) {
            html.append(" tabindex=\"");
            html.append(tabIndex);
            html.append("\"");
        }

        if(title!=null) {
            html.append(" title=\"");
            html.append(title);
            html.append("\"");
        }

        return html.toString();
    }

    public T accessKey(Character accessKey) {
        this.accessKey = accessKey;
        return (T)this;
    }

    public T cssClass(String cssClass) {
        this.cssClass = cssClass;
        return (T)this;
    }

    public T contentEditable(Boolean contentEditable) {
        this.contentEditable = contentEditable;
        return (T)this;
    }

    public T dir(TextDirection dir) {
        this.dir = dir;
        return (T)this;
    }

    public T hidden(boolean hidden) {
        this.hidden = hidden;
        return (T)this;
    }

    public T id(String id) {
        this.id = id;
        return (T)this;
    }

    public T lang(String lang) {
        this.lang = lang;
        return (T)this;
    }

    public T spellCheck(Boolean spellCheck) {
        this.spellCheck = spellCheck;
        return (T)this;
    }

    public T style(String style) {
        this.style = style;
        return (T)this;
    }

    public T tabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
        return (T)this;
    }

    public T title(String title) {
        this.title = title;
        return (T)this;
    }
}
