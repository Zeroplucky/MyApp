package com.example.aac.http;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .removeHeader("Expires")
                .build();
    }
}