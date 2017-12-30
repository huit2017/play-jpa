package filters.member;

import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import javax.inject.Inject;
import akka.stream.Materializer;
import play.Logger;
import play.mvc.Filter;
import play.mvc.Result;
import play.mvc.Http;
import org.slf4j.LoggerFactory;

public class TimeFilter extends Filter {
	
	@Inject
	public TimeFilter(Materializer mat) {
		super(mat);
	}

	@Override
    public CompletionStage<Result> apply(
            Function<Http.RequestHeader, CompletionStage<Result>> nextFilter,
            Http.RequestHeader requestHeader) {
		
        long startTime = System.currentTimeMillis();
        return nextFilter.apply(requestHeader).thenApply(result -> {
            long endTime = System.currentTimeMillis();
            long requestTime = endTime - startTime;

            Logger.info("{} {} took {}ms and returned {} - TimeFilter",
                requestHeader.method(), requestHeader.uri(), requestTime, result.status());

            return result.withHeader("Request-Time", "" + requestTime);
        });
    }
}
