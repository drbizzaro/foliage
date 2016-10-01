package org.foilage.http.html.head;

import org.foilage.http.html.CharacterSet;
import org.foilage.http.html.ComponentBuilder;
import org.foilage.http.html.HtmlComponentImpl;
import org.foilage.model.MimeType;

public class Script extends HtmlComponentImpl implements HeadComponent {

    private final Builder builder;

    public Script(Builder builder) {

        this.builder = builder;
    }

    public Script(String src) {

        this.builder = new Builder(src);
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<script ");

        if(builder.async) {

            htmlBuilder.append("async ");
        }

        if(builder.charset!=null) {

            htmlBuilder.append("charset=\"");
            htmlBuilder.append(builder.charset.getEncoding());
            htmlBuilder.append("\" ");
        }

        if(builder.defer) {

            htmlBuilder.append("defer ");
        }

        if(builder.src!=null) {

            htmlBuilder.append("src=\"");
            htmlBuilder.append(builder.src);
            htmlBuilder.append("\" ");
        }

        if(builder.type!=null) {

            htmlBuilder.append("type=\"");
            htmlBuilder.append(builder.type.getMimeType());
            htmlBuilder.append("\" ");
        }

        htmlBuilder.append(">");

        if(builder.script!=null) {

            htmlBuilder.append(builder.script);
        }

        htmlBuilder.append("</script>\n");
}

    public static class Builder implements ComponentBuilder<Script> {

        private boolean async = false;

        private CharacterSet charset = CharacterSet.UTF8;

        private boolean defer = false;

        private String src = null;

        private MimeType type = null;

        private String script = null;

        public Builder() {
        }

        public Builder(String src) {
            this.src = src;
        }

        @Override
        public Script build() {
            return new Script(this);
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

            this.src =src; return this;
        }

        public Builder script(String script) {

            this.script = script; return this;
        }

        public Builder type(MimeType type) {

            this.type = type; return this;
        }

    }

}
