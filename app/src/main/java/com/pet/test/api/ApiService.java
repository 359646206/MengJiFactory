package com.pet.test.api;

import com.pet.test.javabean.BaseEntity;
import com.pet.test.javabean.Pet;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by yangzhengguang on 2018/9/13.
 */

public interface ApiService {
    /**
     * insert接口
     */
    @POST("insert")
    Observable<BaseEntity<String>> insertApi(@Body RequestBody requestBody);

    /**
     * cancel接口
     */
    @POST("cancel")
    Observable<BaseEntity<List<String>>> cancelApi(@Body RequestBody requestBody);

    /**
     * list接口
     */
    @POST("list")
    Observable<BaseEntity<List<Pet>>> listApi(@Body RequestBody requestBody);

    /**
     * info接口
     */
    @POST("info")
    Observable<BaseEntity<Pet>> infoApi(@Body RequestBody requestBody);

}
