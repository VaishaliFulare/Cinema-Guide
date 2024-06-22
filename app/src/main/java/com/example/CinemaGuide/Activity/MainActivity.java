package com.example.CinemaGuide.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SearchView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.CinemaGuide.Adapter.FilmListAdapter;
import com.example.CinemaGuide.Domain.ListFilm;
import com.example.CinemaGuide.R;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapterNewMoview, adapterUpComing;
private  RecyclerView recyclerViewNewMovies, recyclerViewUpComing;
private RequestQueue mRequestQueue;
private StringRequest mStringRequest,mStringRequest2;
private ProgressBar loading1, loading2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sendRequest();
        sendRequest1();
    }

    private void sendRequest() {
        mRequestQueue= Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mStringRequest=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page={page}", response ->  {
        Gson gson=new Gson();
        loading1.setVisibility(View.GONE);

            ListFilm item=gson.fromJson(response,ListFilm.class);
            adapterNewMoview= new FilmListAdapter(item);
            recyclerViewNewMovies.setAdapter(adapterNewMoview);

        },error -> {
            loading1.setVisibility(View.GONE);
        });
        mRequestQueue.add(mStringRequest);
    }
    private void sendRequest1() {
        mRequestQueue= Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest2=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", response ->  {
            Gson gson=new Gson();
            loading2.setVisibility(View.GONE);

            ListFilm item=gson.fromJson(response,ListFilm.class);
            adapterUpComing= new FilmListAdapter(item);
            recyclerViewUpComing.setAdapter(adapterUpComing);

        },error -> {
            loading2.setVisibility(View.GONE);
        });
        mRequestQueue.add(mStringRequest2);
    }

    private void initView() {
        recyclerViewNewMovies =findViewById(R.id.view1);
        recyclerViewNewMovies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewUpComing=findViewById(R.id.view2);
        recyclerViewUpComing.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        loading1=findViewById(R.id.loading1);
        loading2=findViewById(R.id.loading2);
    }
}