package com.yau.injecty.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: yau
 * time: 2019/06/22 16:07
 * desc:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventType(listenerSetterName = "setOnLongClickListener",
        listenerType = View.OnLongClickListener.class, callbackName = "onLongClick")
public @interface OnLongClick {
    int[] value();
}
