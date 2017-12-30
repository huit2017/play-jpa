package modules.member;

import com.google.inject.AbstractModule;

import play.data.format.Formatters;
import providers.member.FormattersProvider;

public class FormattersModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Formatters.class).toProvider(FormattersProvider.class);
	}

}
