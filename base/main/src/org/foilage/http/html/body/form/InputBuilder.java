package org.foilage.http.html.body.form;

import org.foilage.http.html.BaseEventComponentBuilder;

public abstract class InputBuilder<T> extends BaseEventComponentBuilder<T> {

    private boolean autoComplete = true;

    private boolean autoFocus;

    //TODO: Implement dirname
    //private String dirName = null;

    private boolean disabled;

    private String form = null;

    //TODO: Implement datalist
    //private DataList list

    private String name = null;

    private String pattern = null;

    private boolean readonly;

    private String value = null;

    /**
     * This method should be called from the html generation in the extending class constructor.
     * @return String The generated html code to be included in the extending class constructor.
     * */
    public String addGeneralComponents() {

        StringBuilder html = new StringBuilder();

        html.append(super.addGeneralComponents());

        if(!autoComplete) {

            html.append(" autocomplete=\"off\"");
        }

        if(autoFocus) {

            html.append(" autofocus");
        }

        if(disabled) {

            html.append(" disabled");
        }

        if(form!=null) {

            html.append(" form=\"");
            html.append(form);
            html.append("\"");
        }

        if(name!=null) {

            html.append(" name=\"");
            html.append(name);
            html.append("\"");
        }

        if(pattern!=null) {

            html.append(" pattern=\"");
            html.append(pattern);
            html.append("\"");
        }

        if(readonly) {

            html.append(" readonly");
        }

        if(value!=null) {

            html.append(" value=\"");
            html.append(value);
            html.append("\"");
        }

        return html.toString();
    }

    public InputBuilder<T> autoComplete(boolean autoComplete) {

        this.autoComplete = autoComplete; return this;
    }

    public InputBuilder<T> autoFocus(boolean autoFocus) {

        this.autoFocus = autoFocus; return this;
    }

    public InputBuilder disabled(boolean disable) {

        this.disabled = disable; return this;
    }

    public InputBuilder form(String form) {

        this.form = form; return this;
    }

    public InputBuilder name(String name) {

        this.name = name; return this;
    }

    public InputBuilder pattern(String pattern) {

        this.pattern = pattern; return this;
    }

    public InputBuilder readonly(boolean readonly) {

        this.readonly = readonly; return this;
    }

    public InputBuilder value(String value) {

        this.value = value; return this;
    }

}
