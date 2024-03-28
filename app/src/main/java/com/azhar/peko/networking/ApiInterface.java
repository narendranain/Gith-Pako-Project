package com.azhar.peko.networking;

import com.azhar.peko.model.follow.ModelFollow;
import com.azhar.peko.model.repo.RepositoryDataCap;
import com.azhar.peko.model.search.ModelSearch;
import com.azhar.peko.model.user.ModelUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("users/{username}")
    Call<ModelUser> detailUser(@Path("username") String username);

    @GET("/search/users")
    Call<ModelSearch> searchUser(@Query("q") String username);

    @GET("users/{username}/followers")
    Call<ArrayList<ModelFollow>> followersUser(@Path("username") String username);

    @GET("users/{username}/following")
    Call<ArrayList<ModelFollow>> followingUser(@Path("username") String username);

    @GET("users/{username}/repos")
    Call<ArrayList<RepositoryDataCap>> userRepository(@Path("username") String username);
}
