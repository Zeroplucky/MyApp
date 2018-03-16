package com.example.utils.bindview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/3/2.
 * java 注解详解 https://www.jianshu.com/p/a2cf360c38bf
 */

@Target(ElementType.FIELD) // 标识改注解应用在成员变量上
@Retention(RetentionPolicy.RUNTIME) // 标识生命周期是运行时
public @interface ViewBinder {
    int id() default 0;
}
