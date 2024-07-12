package com.longlung.postfreeapp.api;

import android.graphics.PostProcessor;

import com.longlung.postfreeapp.response.PostResponse;
import com.longlung.postfreeapp.response.PostsItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/posts")
    Call<PostResponse> getAllPosts();
    @GET("posts/{id}")
    Call<PostsItem> getPostById(@Path("id") int id);


}
