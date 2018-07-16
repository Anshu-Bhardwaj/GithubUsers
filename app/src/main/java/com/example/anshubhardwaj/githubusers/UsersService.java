package com.example.anshubhardwaj.githubusers;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsersService {

    @GET("{name}")
    Call<UserResponse> getDetails(@Path("name") String name );

    @GET("{name}/repos")
    Call<ArrayList<Repositories>> getRepoDetails(@Path("name") String name );

    @GET("{name}/followers")
    Call<ArrayList<Followers>> getFollowersDetails(@Path("name") String name );

    @GET("{name}/following")
    Call<ArrayList<Following>> getFollowingDetails(@Path("name") String name );
}
