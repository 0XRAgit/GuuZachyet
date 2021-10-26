package ru.wnfx.framework.data.network;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface NetworkApi {

    @GET("/")
    Single<Object> getTest();
}
