package clients.member;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Singleton;

import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

@Singleton
public class WebserviceClient {

    @Inject
    private WSClient ws;

    public CompletionStage<WSResponse> get() {
        return ws.url("http://echo.jsontest.com/key/value/one/two")
//                .setHeader("headerKey", "headerValue")
//                .setRequestTimeout(1000)
//                .setQueryParameter("paramKey", "paramValue")
                .get();
    }
}
