package com.yau.injecty;

import android.util.ArrayMap;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * author: yau
 * time: 2019/06/22 15:41
 * desc: 将事件监听器的回调函数替换为添加了注解的函数
 */
public class ListenerCallbackInterceptor implements InvocationHandler {

    private Object target;
    private Map<String, Method> interceptedMethods = new ArrayMap<>();

    /**
     * @param target 替换的方法存在的对象，比如activity
     */
    ListenerCallbackInterceptor(Object target) {
        this.target = target;
    }

    /**
     * 将需要拦截的方法添加
     * @param interceptMethod 需要拦截的方法，如：onClick()
     * @param replaceMethod 替换的方法
     */
    void addMethod(String interceptMethod, Method replaceMethod) {
        interceptedMethods.put(interceptMethod, replaceMethod);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (target != null) {
            String methodName = method.getName();
            method = interceptedMethods.get(methodName);
            if (method != null) {
                return method.invoke(target, args);
            }
        }
        return null;
    }
}
