package modules.member;

import com.google.inject.AbstractModule;
import com.typesafe.config.Config;

import providers.member.ConfigProvider;


public class ConfigModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Config.class).toProvider(ConfigProvider.class);
	}
}
