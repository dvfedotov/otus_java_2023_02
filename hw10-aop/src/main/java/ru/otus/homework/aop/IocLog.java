package ru.otus.homework.aop;


import ru.otus.homework.LogClass;
import ru.otus.homework.annotations.Log;
import ru.otus.homework.impl.LogClassImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class IocLog {

    private IocLog() {
    }

    public static LogClass createLogClass() {
        InvocationHandler handler = new DemoInvocationHandler(new LogClassImpl());
        return (LogClass) Proxy.newProxyInstance(IocLog.class.getClassLoader(),
                new Class<?>[]{LogClass.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final LogClass logClass;

        DemoInvocationHandler(LogClass logClass) {
            this.logClass = logClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method invokeMethod = LogClassImpl.class.getMethod(method.getName(), method.getParameterTypes());
            if (invokeMethod.isAnnotationPresent(Log.class)) {
                System.out.println("executed method:" + method.getName() + " , param: " + Arrays.toString(args));
            }
            return method.invoke(logClass, args);
        }
    }
}
