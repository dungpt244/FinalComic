package com.example.testaplication.API;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetImageAPI {
    @GET("v/99ad1531")
    Call<APIBlackClover> getBlackCloverImages();
    @GET("v/08041532")
    Call<ApiAttackOnTitan> getImageAttackOnTitan();
}
