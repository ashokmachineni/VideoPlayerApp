package com.ashokmachineni.videoplayerapp.view;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ashokmachineni.videoplayerapp.R;
import com.ashokmachineni.videoplayerapp.adapter.RecyceAdapter;
import com.ashokmachineni.videoplayerapp.model.Example;
import com.ashokmachineni.videoplayerapp.model.Movie;
import com.ashokmachineni.videoplayerapp.model.Result;
import com.ashokmachineni.videoplayerapp.services.MovieDataService;
import com.ashokmachineni.videoplayerapp.services.RetrofitInstance;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyceAdapter recyceAdapter;
    private ArrayList<Movie> movies = new ArrayList<Movie>();
//https://api.themoviedb.org/3/movie/popular?api_key=7f945f914277633f019081c00561f647
    //https://api.myjson.com/bins/flpva
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("NETFLIX");
        recyclerView = findViewById(R.id.recview);
        recyclerView.setHasFixedSize(true);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        
        getPopularMovies();


    }


    public void getPopularMovies() {
        String serverUrl = "https://api.myjson.com/bins/flpva";
        System.out.print(serverUrl);
        System.out.println("123");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, serverUrl, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        System.out.println("67890");
                        try {
                            Gson gson = new Gson();
                            JSONArray jsonArray = response.getJSONArray("large");

                            for (int i=0;i<= jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Movie movie = gson.fromJson(String.valueOf(jsonObject),Movie.class);
                                movies.add(movie);
                            }
                            recyceAdapter = new RecyceAdapter(getApplicationContext(),movies);
                            recyclerView.setAdapter(recyceAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }
}
