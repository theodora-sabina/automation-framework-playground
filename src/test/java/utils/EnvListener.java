package utils;

import framework.annotations.IgnoreIfNotTestEnv;
import framework.annotations.IgnoreIfOnTestEnv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.function.Predicate;

import static framework.property_manager.PropertyProvider.isEnvTest;

public class EnvListener implements IMethodInterceptor {

    private static final Logger LOG = LogManager.getLogger(EnvListener.class.getName());

    public List<IMethodInstance> intercept(List<IMethodInstance> list, ITestContext iTestContext) {
        LOG.info("Test environment is set to be <{}>", isEnvTest());
        if (isEnvTest()) {
            removeClassMethodIfAnnotated(list, IgnoreIfOnTestEnv.class);
        } else {
            removeClassMethodIfAnnotated(list, IgnoreIfNotTestEnv.class);
        }
        return list;
    }

    public void removeClassMethodIfAnnotated(List<IMethodInstance> list, Class<? extends Annotation> annotationType) {
        //noinspection unchecked
        Predicate<IMethodInstance> condition = (method) ->
                method.getMethod().getRealClass().isAnnotationPresent(annotationType) ||
                        method.getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(annotationType);
        list.removeIf(condition);
    }
}
