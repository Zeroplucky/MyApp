package com.example.aac.http;

import com.example.aac.bean.AdInfo;
import com.example.aac.bean.RiverSegment;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IApiRepository {

    AdInfo getAdInfo(String adCode) throws Exception;

    RiverSegment getRiverSegment(int queryType, String code) throws Exception;
}
