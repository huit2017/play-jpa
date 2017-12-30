package interceptor.member;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceInterceptor implements MethodInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        
        logger.info(String.format("before at %s#%s()", invocation.getMethod().getDeclaringClass().getName(), invocation.getMethod().getName()));
        Object result = null;
        try {
            result = invocation.proceed();
        } catch(Exception e) {
            String message = "";
            if (e != null) {
                message = e.getMessage();
            }
            logger.warn(String.format("Exception: %s", message));
            throw new RuntimeException(message);
        }
        logger.info(String.format("after at %s#%s()", invocation.getMethod().getDeclaringClass().getName(), invocation.getMethod().getName()));
        return result;
    }
}
