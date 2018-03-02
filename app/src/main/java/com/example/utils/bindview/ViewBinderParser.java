package com.example.utils.bindview;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2018/3/2.
 */

public class ViewBinderParser {

    public static void inject(Object obj) {
        Class<?> aClass = obj.getClass();
        int id = 0;
        Field[] fields = aClass.getDeclaredFields();
        AppCompatActivity activity = null;

        if (obj instanceof AppCompatActivity) {
            activity = (AppCompatActivity) obj;
        }
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ViewBinder.class)) {
                ViewBinder viewBinder = field.getAnnotation(ViewBinder.class);
                id = viewBinder.id();
                View view = activity.findViewById(id);
                try {
                    field.set(obj, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
