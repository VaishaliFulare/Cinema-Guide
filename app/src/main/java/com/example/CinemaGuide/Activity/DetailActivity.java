package com.example.CinemaGuide.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.CinemaGuide.Adapter.ImagesListAdapter;
import com.example.CinemaGuide.Domain.FilmItem;
import com.example.CinemaGuide.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titletxt, movieratetxt, movieTimetxt, movieDatetxt, movieSummaryinfo, movieActorsinfo;
    private NestedScrollView scrollView;
    private int idFilm;
    private ShapeableImageView pic1;
    private ImageView pic2, backimg;
    private RecyclerView.Adapter adapterimglist;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        idFilm = getIntent().getIntExtra("id", 0);
        initView();
        sendrequest();
    }

    private void sendrequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        String url = "https://moviesapi.ir/api/v1/movies/" + idFilm;

        mStringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            FilmItem item = gson.fromJson(response, FilmItem.class);

            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(pic1);

            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(pic2);

            titletxt.setText(item.getTitle());
            movieratetxt.setText(item.getRated());
            movieTimetxt.setText(item.getRuntime());
            movieDatetxt.setText(item.getReleased());
            movieSummaryinfo.setText(item.getPlot());
            movieActorsinfo.setText(item.getActors());

            if (item.getImages() != null) {
                adapterimglist = new ImagesListAdapter(item.getImages());
                recyclerView.setAdapter(adapterimglist);
            }

        }, error -> {
            progressBar.setVisibility(View.GONE);
            Log.i("DetailActivity", "onErrorResponse: " + error.toString());
        });

        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        titletxt = findViewById(R.id.MovieNameTxt);
        progressBar = findViewById(R.id.detailloading);
        scrollView = findViewById(R.id.ScrollView3);
        pic1 = findViewById(R.id.postelnormalimage);
        pic2 = findViewById(R.id.posterbigimage);
        movieratetxt = findViewById(R.id.MovieRateTxt);
        movieTimetxt = findViewById(R.id.MovieTimeTxt);
        movieDatetxt = findViewById(R.id.MovieDateTxt);
        movieSummaryinfo = findViewById(R.id.MovieSummary);
        movieActorsinfo = findViewById(R.id.movieactioninfo);
        backimg = findViewById(R.id.backimg);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backimg.setOnClickListener(v -> finish()); // Close this activity and return to the previous activity
    }
}
