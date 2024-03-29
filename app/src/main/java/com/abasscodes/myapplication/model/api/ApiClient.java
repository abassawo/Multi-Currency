package com.abasscodes.myapplication.model.api;

import android.util.Log;

import com.abasscodes.myapplication.helpers.PreferenceHelper;
import com.abasscodes.myapplication.model.RateDictionary;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by C4Q on 11/18/16.
 */

public class ApiClient {
    private static final String TAG = ApiClient.class.getSimpleName();
    private static ApiClient instance;
    private final String API_BASE_URL = "http://api.fixer.io/";
    private static FixerApi api;
    private final String baseCurrency;
    private Listener listener;


    public static synchronized ApiClient getInstance(Listener listener){
        if(instance == null){
            instance = new ApiClient(listener);
        }
        return instance;
    }

    private ApiClient(Listener listener) {
        this.listener = listener;
        baseCurrency = PreferenceHelper.getBaseCurrency();
        if (api == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            api = retrofit.create(FixerApi.class);
        }
    }

    public void getConversionMap(){
        getLatest(baseCurrency).enqueue(new Callback<RateResponse>() {
            @Override
            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
                Log.d(TAG, response.body().toString());
                RateResponse resp = response.body();
                Rates rates = resp.getRates();
                RateDictionary.getInstance().setRates(rates);
                listener.onConversionComplete(RateDictionary.getInstance());
            }

            @Override
            public void onFailure(Call<RateResponse> call, Throwable t) {
                Log.d(TAG, "Failure  retrofitting ");
            }
        });


    }

    private Call<RateResponse> getLatest(String base){
        return api.getRates(base);
    }

    public interface Listener{
        void onConversionComplete(RateDictionary dictionary);
    }
}
