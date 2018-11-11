package com.ashokmachineni.videoplayerapp.services;

import com.ashokmachineni.videoplayerapp.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("movie/popular")
    Call<Example> getPopularMovies(@Query("api_key")String apiKey);
}
