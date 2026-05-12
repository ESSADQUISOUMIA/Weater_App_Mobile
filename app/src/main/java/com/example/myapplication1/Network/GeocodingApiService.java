package com.example.myapplication1.Network;

import com.example.myapplication1.Models.GeocodingResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocodingApiService {
    @GET("v1/search")
    Call<GeocodingResponse> searchCity(
        @Query("name") String name,
        @Query("count") int count,
        @Query("language") String language
    );
}
