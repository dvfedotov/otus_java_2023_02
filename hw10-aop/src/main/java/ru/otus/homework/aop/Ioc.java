package ru.otus.homework.aop;


import ru.otus.homework.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

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
        private final List<Method> logMethods;

        DemoInvocationHandler(Object delegate) {
            this.delegate = delegate;
            logMethods = Arrays.stream(delegate.getClass().getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(Log.class)).toList();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isLogPresent(method)) {
                System.out.println("executed method:" + method.getName() + " , param: " + Arrays.toString(args));
            }
            return method.invoke(delegate, args);
        }

        private boolean isLogPresent(Method method) {
            return logMethods.stream()
                    .anyMatch(m -> m.getName().equals(method.getName())
                            && Arrays.equals(m.getParameterTypes(), method.getParameterTypes()));
        }
    }
}
