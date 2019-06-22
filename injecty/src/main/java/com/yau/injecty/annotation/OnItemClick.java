package com.yau.injecty.annotation;

import com.yau.injecty.recyclerview.RecyclerView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: yau
 * time: 2019/06/22 23:21
 * desc:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventType(listenerSetterName = "setOnItemClickListener",
        listenerType = RecyclerView.OnItemClickListener.class, callbackName = "onItemClick")
public @interface OnItemClick {
    int[] value();
}
