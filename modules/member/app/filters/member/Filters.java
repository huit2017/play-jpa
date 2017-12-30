package filters.member;

import play.http.DefaultHttpFilters;
import play.filters.gzip.GzipFilter;
import javax.inject.Inject;

public class Filters extends DefaultHttpFilters {

	@Inject
	public Filters(GzipFilter gzip, TimeFilter time) {
		super(gzip, time);
	}
}
