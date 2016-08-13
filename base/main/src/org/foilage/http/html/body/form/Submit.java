package org.foilage.http.html.body.form;

import org.foilage.http.html.HtmlComponentImpl;
import org.foilage.http.html.body.Target;

public class Submit extends HtmlComponentImpl {

    private final Builder builder;

    public Submit(Builder builder) {

        this.builder = builder;
    }

    public Submit(String name, String value) {

        this.builder = new Builder(name, value);
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<input");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.formAction!=null) {

            htmlBuilder.append(" formaction=\"");
            htmlBuilder.append(builder.formAction);
            htmlBuilder.append("\"");
        }

        if(builder.formEncType!=null) {

            htmlBuilder.append(" formenctype=\"");
            htmlBuilder.append(builder.formEncType);
            htmlBuilder.append("\"");
        }

        if(builder.formMethod!=null) {

            htmlBuilder.append(" formmethod=\"");
            htmlBuilder.append(builder.formMethod.getValue());
            htmlBuilder.append("\"");
        }

        if(builder.formTarget!=null) {

            htmlBuilder.append(" formtarget=\"");
            htmlBuilder.append(builder.formTarget.getValue());
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(" type=\"submit\"");

        htmlBuilder.append(">\n");

    }

    public static class Builder extends InputBuilder<Submit,Builder> {

        private String formAction = null;

        private String formEncType = null;

        private FormMethod formMethod = null;

        private Target formTarget = null;

        public Builder() {
        }

        public Builder(String name, String value) {

            this.name(name);
            this.value(value);
        }

        public Builder formAction(String formAction) {

            this.formAction = formAction; return this;
        }

        public Builder formEncType(String formEncType) {

            this.formEncType = formEncType; return this;
        }

        public Builder formMethod(FormMethod formMethod) {

            this.formMethod = formMethod; return this;
        }

        public Builder formTarget(Target formTarget) {

            this.formTarget = formTarget; return this;
        }

        @Override
        public Submit build() {

            return new Submit(this);
        }
    }

}