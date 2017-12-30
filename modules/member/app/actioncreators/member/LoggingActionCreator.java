package actioncreators.member;

import java.lang.reflect.Method;
import java.util.concurrent.CompletionStage;

import play.Logger;
import play.http.HttpEntity;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class LoggingActionCreator implements play.http.ActionCreator {

    @Override
    public Action<?> createAction(Http.Request request, Method actionMethod) {
        return new Action.Simple() {
            @Override
            public CompletionStage<Result> call(Http.Context ctx) {

                Logger.info("before at {}#{}()", actionMethod.getDeclaringClass().getName(), actionMethod.getName());

                return delegate.call(ctx).whenComplete((result, error) -> {
                    
                    if (ctx.request().body().asJson() != null) {
                        Logger.info("request parameter {}", ctx.request().body().asJson().toString());
                    }
                    Logger.info("http status {}", result.status());
                    if (error == null) {
                        result.headers().entrySet().stream().forEach(h -> {
                            Logger.info("response header {}: {}", h.getKey(), h.getValue());
                        });
                        Logger.info("response contentLength {}", result.body().contentLength().orElse(0L));
                        Logger.info("response contentType {}", result.body().contentType().orElse(""));
                        if (result.body() instanceof HttpEntity.Strict) {
                            Logger.info("response content {}", (((HttpEntity.Strict) result.body()).data())
                                    .decodeString(result.charset().orElse("utf-8")));
                        }
                    } else {
                        Logger.error("error {}", error.toString());
                    }

                    Logger.info("after at {}#{}()", actionMethod.getDeclaringClass().getName(), actionMethod.getName());
                });
            }
        };
    }
}
