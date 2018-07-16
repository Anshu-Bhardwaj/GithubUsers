package com.example.anshubhardwaj.githubusers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowDetails extends AppCompatActivity {

    ListView listview;
    ArrayList<String> titles=new ArrayList<>();
    ArrayAdapter<String> adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        listview=findViewById(R.id.listview);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,titles);
        listview.setAdapter(adapter);
        Intent intent =getIntent();
        String check=intent.getStringExtra("type");
        Retrofit.Builder builder=new Retrofit.Builder().baseUrl("https://api.github.com/users/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        UsersService service=retrofit.create(UsersService.class);
        if(check.equals("repositories"))
        {
            Call<ArrayList<Repositories>> call=service.getRepoDetails(MainActivity.user);
            call.enqueue(new Callback<ArrayList<Repositories>>() {
                @Override
                public void onResponse(Call<ArrayList<Repositories>> call, Response<ArrayList<Repositories>> response) {
                    ArrayList<Repositories> listResponse = response.body();
                    titles.clear();
                    for(int i=0;i<listResponse.size();i++)
                    {
                        String name=listResponse.get(i).name;
                        titles.add(name);
                    }
                    progressBar.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();


                }

                @Override
                public void onFailure(Call<ArrayList<Repositories>> call, Throwable t) {

                }
            });

        }
        else if(check.equals("followers"))
        {
            Call<ArrayList<Followers>> call=service.getFollowersDetails(MainActivity.user);
            call.enqueue(new Callback<ArrayList<Followers>>() {
                @Override
                public void onResponse(Call<ArrayList<Followers>> call, Response<ArrayList<Followers>> response) {
                    ArrayList<Followers> listResponse = response.body();
                    titles.clear();
                    for(int i=0;i<listResponse.size();i++)
                    {
                        String name=listResponse.get(i).login;
                        titles.add(name);
                    }
                    progressBar.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<Followers>> call, Throwable t) {

                }
            });

        }

        else if(check.equals("following"))
        {
            Call<ArrayList<Following>> call=service.getFollowingDetails(MainActivity.user);
            call.enqueue(new Callback<ArrayList<Following>>() {
                @Override
                public void onResponse(Call<ArrayList<Following>> call, Response<ArrayList<Following>> response) {
                    ArrayList<Following> listResponse = response.body();
                    titles.clear();
                    for(int i=0;i<listResponse.size();i++)
                    {
                        String name=listResponse.get(i).login;
                        titles.add(name);
                    }
                    progressBar.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<Following>> call, Throwable t) {

                }
            });

        }
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent =new Intent();
//        setResult(1,intent);
//        finish();
//    }
}