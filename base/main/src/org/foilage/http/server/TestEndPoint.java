package org.foilage.http.server;

import org.foilage.authorization.Role;
import org.foilage.http.html.ComponentGroup;
import org.foilage.http.html.body.*;
import org.foilage.http.html.head.*;
import org.foilage.model.MimeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestEndPoint extends WWWEndPoint<TestResponseData> {

    public TestEndPoint(List<String> path, List<LogicWorker> preRenderLogicList) {
        super(path, Arrays.asList(Role.ADMIN), new ArrayList<>(), preRenderLogicList);
    }

    @Override
    protected String renderHead(RequestData req, TestResponseData resp) {

        Head.Builder head = new Head.Builder();

        head.add(new Title("Test titel"));
        head.add(new Meta(MetaName.CHARSET, "UTF-8"));
        head.add(new Meta(MetaName.AUTHOR, "Fredrik Gustavsson"));
        head.add(new Link.Builder("test.css", Rel.STYLESHEET).mimeType(MimeType.CSS).build());

        return head.build().getHtml(new ComponentGroup(head.build()));
    }

    @Override
    protected String renderBody(RequestData req, TestResponseData resp) {

        //sb.append("<h1>Test ext</h1>" + System.currentTimeMillis() + "<br><br>Param ID: "+req.getIntParam("id")+"<br><br>Test ID: "+resp.getTest()+"<br><br><img src=\"img.png\">");

        Body.Builder body = new Body.Builder();

        body.add(new H.Builder(H.H1, "Test ext").id("test_id").build());
        body.add(new A.Builder("Test2", "Test länk").onClick("return confirm('Gå till länken?');").build(), BR.I1);
        body.add(new Img("img.png", "En bild"));

        return body.build().getHtml(new ComponentGroup(body.build()));
    }

    @Override
    protected TestResponseData createResponseData() {
        return new TestResponseData();
    }
}
