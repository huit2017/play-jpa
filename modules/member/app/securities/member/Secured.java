package securities.member;

import play.mvc.Http.Context;
import controllers.member.routes;
import play.mvc.Result;
import play.mvc.Security;

public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        //return ctx.session().get("username");
        return "logindsan";
    }
    
    @Override
    public Result onUnauthorized(Context ctx) {
        String url = ctx.request().uri();
        if (url == null) {
            url = "/";
        }
        ctx.session().put("returuUrl", url);
        return redirect(routes.HomeController.login());
    }
}
