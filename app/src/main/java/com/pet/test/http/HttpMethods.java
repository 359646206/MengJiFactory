package com.pet.test.http;

import com.pet.test.api.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangzhengguang on 2018/9/13.
 */

public class HttpMethods {
//    private static final String BASE_URL = "http://10.20.70.219/app/v1/";
    private static final String BASE_URL = "http://openwx.mjpet.net/production/";
    private static final int TIME_OUT = 4;
    private Retrofit retrofit;
    private ApiService apiService;

    private HttpMethods() {
        /**
         * 构造函数私有化
         * 并在构造函数中进行retrofit的初始化
         */
        MyInterceptor myInterceptor = new MyInterceptor();
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(myInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    private static class sinalInstance {
        public static final HttpMethods instance = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return sinalInstance.instance;
    }

    public ApiService getApiService() {
        return apiService;
    }
}
