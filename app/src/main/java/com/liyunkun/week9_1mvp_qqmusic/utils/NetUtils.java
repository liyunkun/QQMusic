package com.liyunkun.week9_1mvp_qqmusic.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by liyunkun on 2016/10/19 0019.
 */
public class NetUtils {

    public static String httpUrl="http://route.showapi.com/213-4?showapi_appid=25896&showapi_sign=da9d45bda2d84cdf840546a2646db060&topid=%d&";
    public static String lrcHttpUrl="http://route.showapi.com/213-2?showapi_appid=25798&showapi_sign=6d9b08da66aa47adb911f1065b7baa4e&musicid=%d&";
    public static OkHttpClient client=new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).build();
}
