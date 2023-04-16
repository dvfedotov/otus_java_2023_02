package ru.otus.homework.aop;


import ru.otus.homework.annotations.Log;
import ru.otus.homework.service.impl.SuperCalculatorImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Ioc {

    private Ioc() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxyLogClass(T delegate) {
        InvocationHandler handler = new DemoInvocationHandler(delegate);
        return (T) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                delegate.getClass().getInterfaces(), handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final Object delegate;

        DemoInvocationHandler(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method invokeMethod = delegate.getClass().getMethod(method.getName(), method.getParameterTypes());
            if (invokeMethod.isAnnotationPresent(Log.class)) {
                System.out.println("executed method:" + method.getName() + " , param: " + Arrays.toString(args));
            }
            return method.invoke(delegate, args);
        }
    }
}
