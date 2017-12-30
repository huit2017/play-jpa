package modules.member;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

import annotations.member.ServiceInterceptorTarget;
import interceptor.member.ServiceInterceptor;

public class InterceptorModule extends AbstractModule {

    @Override
    protected void configure() {
        bindInterceptor(
                Matchers.any(), 
                Matchers.annotatedWith(ServiceInterceptorTarget.class),
                new ServiceInterceptor());
    }
}
