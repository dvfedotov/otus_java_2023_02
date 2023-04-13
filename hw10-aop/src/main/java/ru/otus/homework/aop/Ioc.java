package ru.otus.homework.aop;


import ru.otus.homework.service.SuperCalculator;
import ru.otus.homework.annotations.Log;
import ru.otus.homework.service.impl.SuperCalculatorImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Ioc {

    private Ioc() {
    }

    public static SuperCalculator createProxyLogClass() {
        InvocationHandler handler = new DemoInvocationHandler(new SuperCalculatorImpl());
        return (SuperCalculator) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{SuperCalculator.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final SuperCalculator superCalculator;

        DemoInvocationHandler(SuperCalculator superCalculator) {
            this.superCalculator = superCalculator;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method invokeMethod = SuperCalculatorImpl.class.getMethod(method.getName(), method.getParameterTypes());
            if (invokeMethod.isAnnotationPresent(Log.class)) {
                System.out.println("executed method:" + method.getName() + " , param: " + Arrays.toString(args));
            }
            return method.invoke(superCalculator, args);
        }
    }
}
