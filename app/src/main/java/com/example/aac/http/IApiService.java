package com.example.aac.http;


import com.example.aac.bean.AdInfo;
import com.example.aac.bean.RiverSegment;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IApiService {

    @Headers("Cache-Control:max-stale=7200")
    @GET()
    Call<AdInfo> getAdInfo(@Url String url, @Query("uid") String uid, @Query("ad_code") String adCode);


    @Headers("Cache-Control:max-stale=7200")
    @GET()
    Call<RiverSegment> getRiverSegment(@Url String url, @QueryMap Map<String, Object> map);
}
