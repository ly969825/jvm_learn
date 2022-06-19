package seven_week.readandwritseparation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

@Component
@Aspect
public class RoutingDataSourceAdvisor {
    private Logger log= (Logger) LoggerFactory.getLogger(DynamicDataSource.class);
 
    @Pointcut("execution(@RoutingDataSource * com.ljh..*Service+.*(..))")
    private void routingDataSource() {
    }
 
    @Around("routingDataSource()")
    public Object routing(ProceedingJoinPoint joinPoint) throws Exception {
 
        Class<?> clazz = joinPoint.getTarget().getClass();
        String className = clazz.getName();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = method.getName();
        Object[] arguments = joinPoint.getArgs();
 
        String key;
        RoutingDataSource routingDataSource = method.getAnnotation(RoutingDataSource.class);
        key = routingDataSource.value().getKey();
 
        Object result = null;
        DataSourceKeyHolder.set(key);
 
        try {
            checkPROPAGATION(clazz, method);
            result = joinPoint.proceed(arguments);
        } catch (Throwable e) {
            log.error("Error occurred during datasource(key=" + key + ") routing, ", e);
        } finally {
            DataSourceKeyHolder.clear();
        }
        return result;
    }
 
    private void checkPROPAGATION(Class<?> clazz, Method method) {
        if (DataSourceKeyHolder.isNestedCall()) {
            Transactional transactional = method.getAnnotation(Transactional.class);
            if (transactional == null) {
                transactional = clazz.getAnnotation(Transactional.class);
            }
            if (transactional != null) {
                if (transactional.propagation() != Propagation.REQUIRES_NEW) {
                    throw new RuntimeException("必须定义为propagation = REQUIRES_NEW");
                }
            }
        }
    }
 
}