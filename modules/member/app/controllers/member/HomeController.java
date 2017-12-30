package controllers.member;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import play.cache.Cached;
import play.data.Form;
import play.db.jpa.Transactional;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Security;
import requests.member.UserRequest;
import responses.member.UserResponse;
import securities.member.Secured;
import services.member.HomeService;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
@Singleton
public class HomeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Inject
    private HomeService service;

    /**
     * An action that renders an HTML page with a welcome message. The configuration
     * in the <code>routes</code> file means that this method will be called when
     * the application receives a <code>GET</code> request with a path of
     * <code>/</code>.
     */
    @Transactional()
    public Result index() {
        service.index();
        return ok();
    }

    public Result list() {
        List<?> list = service.list();
        return ok(Json.toJson(list));
    }

    public Result detail() {
        logger.info(String.format("detail"));
        service.detail();
        return ok();
    }

    @Transactional()
    public Result input() throws JsonProcessingException {

        Form<UserRequest> userForm = form(UserRequest.class);
        if (userForm.hasErrors()) {
            return badRequest(userForm.errorsAsJson());
        }
        UserResponse response = service.input(userForm.get());
        response().setHeader("X-Sample", "example");
        return ok(toResponseJson(response));
    }
    
    @AddCSRFToken
    public Result login() {
        logger.info(String.format("login"));
        return ok();
    }
    
    @RequireCSRFCheck
    @Security.Authenticated(Secured.class)
    @Cached(key = "")
    public Result secure() {
        logger.info(String.format("secure"));
        return ok();
    }
    
    @Security.Authenticated(Secured.class)
    public Result logout() {
        logger.info(String.format("logout"));
        return ok();
    }

    public Result error() {
        logger.info(String.format("error"));
        int i = 1 / 0;
        return ok();
    }
}
