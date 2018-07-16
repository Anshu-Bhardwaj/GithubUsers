package com.example.anshubhardwaj.githubusers;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText username;
    TextView name;
    TextView id;
    ImageView imageView;
    public static String user;
    ProgressBar progressBar;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username= findViewById(R.id.userEditText);
        name= findViewById(R.id.nameValue);
        layout=findViewById(R.id.layout);
        id=findViewById(R.id.idValue);
        progressBar=findViewById(R.id.progressBar);
        imageView=findViewById(R.id.imageView);

    }

    public void search(View view){
        layout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        user=username.getText().toString();
        Retrofit.Builder builder=new Retrofit.Builder().baseUrl("https://api.github.com/users/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        UsersService service=retrofit.create(UsersService.class);
        Call<UserResponse> call=service.getDetails(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse=response.body();
                name.setText(userResponse.name);
                id.setText(userResponse.id+"");
                Picasso.get().load(userResponse.avatar_url).into(imageView);
                layout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    public void repositories(View view){
        Intent intent = new Intent(this,ShowDetails.class);
        intent.putExtra("type","repositories");
        startActivityForResult(intent,1101);
    }

    public void followers(View view){
        Intent intent = new Intent(this,ShowDetails.class);
        intent.putExtra("type","followers");
        startActivity(intent);
    }

    public void following(View view){
        Intent intent = new Intent(this,ShowDetails.class);
        intent.putExtra("type","following");
        startActivity(intent);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode==1101)
//        {
//            if(resultCode==1)
//            {
//                layout.setVisibility(View.GONE);
//                progressBar.setVisibility(View.VISIBLE);
//                user=username.getText().toString();
//                Retrofit.Builder builder=new Retrofit.Builder().baseUrl("https://api.github.com/users/").addConverterFactory(GsonConverterFactory.create());
//                Retrofit retrofit=builder.build();
//                UsersService service=retrofit.create(UsersService.class);
//                Call<UserResponse> call=service.getDetails(user);
//                call.enqueue(new Callback<UserResponse>() {
//                    @Override
//                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                        UserResponse userResponse=response.body();
//                        name.setText(userResponse.name);
//                        id.setText(userResponse.id+"");
//                        Picasso.get().load(userResponse.avatar_url).into(imageView);
//                        layout.setVisibility(View.VISIBLE);
//                        progressBar.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onFailure(Call<UserResponse> call, Throwable t) {
//
//                    }
//                });
//            }
//        }
//
  //  }


}
