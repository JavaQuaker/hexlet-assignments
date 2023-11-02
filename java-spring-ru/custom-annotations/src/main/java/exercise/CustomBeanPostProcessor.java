package exercise;

import exercise.calculator.Calculator;
import exercise.calculator.CalculatorImpl;
import org.slf4j.ILoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
        private Map<String, Class> beanMap = new LinkedHashMap<>();
    private Map<String, String> loggerMap = new LinkedHashMap<>();
//    private Map<Class, String> beanLogger = new LinkedHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String level = bean.getClass().getAnnotation(Inspect.class).value();
            beanMap.put(beanName, bean.getClass());
            loggerMap.put(beanName, level);
        }
//        System.out.println("beanLogger " + " " + beanLogger);
        System.out.println("beanMap" + " " + beanMap);
        System.out.println("loggerMap" + " " + loggerMap);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!beanMap.containsKey(beanName)) {
            return bean;
        }

        Class beanClass = beanMap.get(beanName);
        String level = loggerMap.get(beanName);

        return Proxy.newProxyInstance(
                beanClass.getClassLoader(),
                beanClass.getInterfaces(),
                (proxy, method, args) -> {
                        String message = "Was called method: " + method.getName() + "()" + " with arguments: " +
                                Arrays.toString(args);
                        if (level.equals("info")) {
                            LOGGER.info(message);
                        } else {
                            LOGGER.debug(message);
                        }

                    return method.invoke(bean, args);
                }
        );
    }
}


// END
