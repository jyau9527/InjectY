package com.yau.injecty;

import android.app.Activity;
import android.view.View;

import com.yau.injecty.annotation.ContentView;
import com.yau.injecty.annotation.EventType;
import com.yau.injecty.annotation.FindView;
import com.yau.injecty.annotation.GetString;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author: yau
 * time: 2019/06/22 14:02
 * desc:
 */
public class InjectManager {

    private InjectManager() {}

    private static final class SingletonHolder {
        private static final InjectManager INSTANCE = new InjectManager();
    }

    public static InjectManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void inject(Activity activity) {
        injectContentView(activity);
        injectViews(activity);
        injectEvents(activity);
        injectString(activity);
    }

    private void injectContentView(Activity activity) {
        Class clazz = activity.getClass();
        ContentView contentView = (ContentView) clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            int layoutId = contentView.value();
            activity.setContentView(layoutId);
        }
    }

    private void injectViews(Activity activity) {
        Class clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            FindView findView = field.getAnnotation(FindView.class);
            if (findView != null) {
                try {
                    int viewId = findView.value();
                    View view = activity.findViewById(viewId);
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 注入事件的原理是：每找到一个viewId，就设置一个listener，
     * 并listener的回调方法替换为添加了注解的方法
     */
    private void injectEvents(Activity activity) {
        Class clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType != null) {
                    EventType eventType = annotationType.getAnnotation(EventType.class);
                    if (eventType != null) {
                        String listenerSetterName = eventType.listenerSetterName();
                        Class<?> listenerType = eventType.listenerType();
                        String callbackName = eventType.callbackName();

                        Object listener = getProxyListener(activity, listenerType, callbackName, method);
                        setViewsListener(activity, annotation, annotationType, listenerSetterName, listenerType, listener);
                    }
                }
            }
        }
    }

    /**
     * 创建一个代理监听器
     */
    private Object getProxyListener(Activity activity,
                                    Class<?> listenerType,
                                    String interceptMethod,
                                    Method replaceMethod) {
        ListenerCallbackInterceptor interceptor = new ListenerCallbackInterceptor(activity);
        replaceMethod.setAccessible(true);
        interceptor.addMethod(interceptMethod, replaceMethod);
        return Proxy.newProxyInstance(
                listenerType.getClassLoader(), new Class[] {listenerType}, interceptor);
    }

    private void setViewsListener(Activity activity,
                                  Annotation annotation,
                                  Class<? extends Annotation> annotationType,
                                  String listenerSetterName,
                                  Class<?> listenerType,
                                  Object listener) {

        try {
            int[] viewIds = (int[]) annotationType
                    .getDeclaredMethod("value")
                    .invoke(annotation);
            for (int viewId : viewIds) {
                View view = activity.findViewById(viewId);
                view.getClass()
                        .getMethod(listenerSetterName, listenerType)
                        .invoke(view, listener);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void injectString(Activity activity) {
        Class clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            GetString getString = field.getAnnotation(GetString.class);
            if (getString != null) {
                try {
                    int resId = getString.value();
                    String[] args = getString.args();
                    String string = activity.getString(resId, (Object[]) args);
                    field.setAccessible(true);
                    field.set(activity, string);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
