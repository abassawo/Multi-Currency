package com.abasscodes.myapplication.model.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by C4Q on 11/18/16.
 */

public interface FixerApi {

    @GET("/latest")
    Call<RateResponse> getRates(@Query("base")String baseCurrency);
}
