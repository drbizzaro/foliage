package org.foilage.http.html.body.form;

import org.foilage.http.html.*;
import org.foilage.http.html.body.EncodingType;
import org.foilage.http.html.body.Target;

import java.util.ArrayList;
import java.util.List;

public class Form extends HtmlComponentImpl {

    private final Builder builder;

    public Form(Builder builder) {
        this.builder = builder;

        tabOffset = 0;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<form");

        if(builder.acceptCharset!=null) {

            htmlBuilder.append("accept-charset=\"");
            htmlBuilder.append(builder.acceptCharset.getEncoding());
            htmlBuilder.append("\" ");
        }

        if(builder.action!=null) {

            htmlBuilder.append("action=\"");
            htmlBuilder.append(builder.action);
            htmlBuilder.append("\" ");
        }

        if(!builder.autocomplete) {

            htmlBuilder.append("autoComplete=\"off\" ");
        }

        if(builder.enctype!=null) {

            htmlBuilder.append("enctype=\"");
            htmlBuilder.append(builder.enctype.getEncoding());
            htmlBuilder.append("\" ");
        }

        if(builder.method!=null) {

            htmlBuilder.append("method=\"");
            htmlBuilder.append(builder.method.name().toLowerCase());
            htmlBuilder.append("\" ");
        }

        if(builder.name!=null) {

            htmlBuilder.append("name=\"");
            htmlBuilder.append(builder.name);
            htmlBuilder.append("\" ");
        }

        if(builder.novalidate) {

            htmlBuilder.append("novalidate ");
        }

        if(builder.target!=null) {

            htmlBuilder.append("target=\"");
            htmlBuilder.append(builder.target.getValue());
            htmlBuilder.append("\" ");
        }

        htmlBuilder.append(">\n");

        for(HtmlComponent component: builder.components){
            htmlBuilder.append(component.getHtml(this, false));
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</form>\n");
    }

    public static class Builder extends BaseEventComponentBuilder<Form> {

        private List<HtmlComponent> components;

        private CharacterSet acceptCharset = null;

        private String action = null;

        private boolean autocomplete = true;

        private EncodingType enctype = null;

        private FormMethod method = null;

        private String name = null;

        private boolean novalidate = false;

        private Target target = null;

        public Builder() {
            this.components = new ArrayList<>();
        }

        @Override
        public Form build() {
            return new Form(this);
        }

        private Builder add(HtmlComponent component) {

            this.components.add(component);

            return this;
        }

        private Builder add(HtmlComponent... component) {

            for(HtmlComponent comp: component) {

                this.components.add(comp);
            }

            return this;
        }

        private Builder action(String action) {

            this.action = action; return this;
        }

        private Builder autocomplete(boolean autocomplete) {

            this.autocomplete = autocomplete; return this;
        }

        private Builder characterSet(CharacterSet acceptCharset) {

            this.acceptCharset = acceptCharset; return this;
        }

        private Builder enctype(EncodingType enctype) {

            this.enctype = enctype; return this;
        }

        private Builder method(FormMethod method) {

            this.method = method; return this;
        }

        private Builder name(String name) {

            this.name = name; return this;
        }

        private Builder novalidate(boolean novalidate) {

            this.novalidate = novalidate; return this;
        }

        private Builder target(Target target) {

            this.target = target; return this;
        }
    }

}
