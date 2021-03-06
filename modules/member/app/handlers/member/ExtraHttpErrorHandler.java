package handlers.member;

import play.http.DefaultHttpErrorHandler;
import play.*;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.api.routing.Router;
import play.mvc.Http.*;
import play.mvc.*;

import javax.inject.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Singleton
public class ExtraHttpErrorHandler extends DefaultHttpErrorHandler {
	
	@Inject
    public ExtraHttpErrorHandler(Configuration configuration, Environment environment,
                        OptionalSourceMapper sourceMapper, Provider<Router> routes) {
        super(configuration, environment, sourceMapper, routes);
    }
	
	protected CompletionStage<Result> onDevServerError(RequestHeader request, UsefulException exception) {
        return CompletableFuture
				.completedFuture(Results.internalServerError("A server error occurred (Dev): " + exception.getMessage()));
    }

	protected CompletionStage<Result> onProdServerError(RequestHeader request, UsefulException exception) {
		return CompletableFuture
				.completedFuture(Results.internalServerError("A server error occurred (Prod): " + exception.getMessage()));
	}

	protected CompletionStage<Result> onForbidden(RequestHeader request, String message) {
		return CompletableFuture.completedFuture(Results.forbidden("You're not allowed to access this resource."));
	}
}
