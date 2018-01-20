package com.example.aac.http;

import android.content.Context;
import android.util.Log;

import com.example.utils.FileUtil;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 */

public class HttpClientManager {

    private static volatile HttpClientManager instance;

    private Retrofit retrofit;
    private OkHttpClient client;

    private static final String CACHE_DIR = "onHttpCache";
    private static final String BASE_URL = "http://xxxx";

    private HttpClientManager(Context context) {
//        File cacheDir = new File(context.getCacheDir(), CACHE_DIR);
        File cacheDir = FileUtil.createCacheDir(context, CACHE_DIR);
        int cacheSize = 50 * 1024 * 1024;
        Cache cache = new Cache(cacheDir, cacheSize);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("xxx", "log: ----" + message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(new CacheInterceptor())
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static HttpClientManager getInstance(Context context) {
        if (instance == null) {
            synchronized (HttpClientManager.class) {
                if (instance == null) {
                    instance = new HttpClientManager(context);
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public OkHttpClient getOkHttpClient() {
        return client;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

}
