package org.foilage.http.html.body;

import org.foilage.http.html.CharacterSet;
import org.foilage.http.html.HtmlComponentImpl;
import org.foilage.http.html.Util;
import org.foilage.model.MimeType;

import static org.foilage.utils.checkers.NullChecker.notNull;

public class Script extends HtmlComponentImpl {

    private final Builder builder;

    public Script(Builder builder) {

        this.builder = notNull(builder);
    }

    public Script(String script) {

        this.builder = new Builder(script);
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<script");

        if(builder.async) {

            htmlBuilder.append(" async");
        }

        if(builder.charset!=null) {

            htmlBuilder.append(" charset=\"");
            htmlBuilder.append(builder.charset.getEncoding());
            htmlBuilder.append("\"");
        }

        if(builder.defer) {

            htmlBuilder.append(" defer");
        }

        if(builder.src!=null) {

            htmlBuilder.append(" src=\"");
            htmlBuilder.append(builder.src);
            htmlBuilder.append("\"");
        }

        if(builder.type!=null) {

            htmlBuilder.append(" type=\"");
            htmlBuilder.append(builder.type.toString());
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

        if(builder.script!=null) {
            htmlBuilder.append(builder.script);
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</script>");

        if(!onSameRow) {

            htmlBuilder.append("\n");
        }
    }

    public static class Builder {

        private boolean async;

        private CharacterSet charset;

        private boolean defer;

        private String src;

        private MimeType type;

        private String script;

        public Builder() {
        }

        public Builder(String script) {
            this.script = script;
        }

        public Builder async(boolean async) {

            this.async = async; return this;
        }

        public Builder charset(CharacterSet charset) {

            this.charset = charset; return this;
        }

        public Builder defer(boolean defer) {

            this.defer = defer; return this;
        }

        public Builder src(String src) {

            this.src = src; return this;
        }

        public Builder type(MimeType type) {

            this.type = type; return this;
        }

        public Builder script(String script) {

            this.script = script; return this;
        }

        public Script build() {

            return new Script(this);
        }

    }

}
