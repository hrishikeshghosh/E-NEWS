package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.news_app.Clinets.API_clients;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String API_KEY = "e1716a0dcaaa4ef8af1dbcfda127c037";
    private RecyclerView recyclerView;
    private news_adapter adapter;
    private List<Article> articleList=new ArrayList<>();
    private SwipeRefreshLayout swiper;
    private EditText search_news;
    private ImageButton search_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swiper=findViewById(R.id.swiper);
        search_news=findViewById(R.id.search_for_news);
        search_btn=findViewById(R.id.search_news_btn);

        recyclerView=findViewById(R.id.news_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final String country=getCountry();

        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJson("",country,API_KEY);
            }
        });

        retrieveJson("",country,API_KEY);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!search_news.getText().toString().equals("")){
                    swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            retrieveJson(search_news.getText().toString(),country,API_KEY);
                        }
                    });
                    retrieveJson(search_news.getText().toString(),country,API_KEY);
                }else{
                    swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            retrieveJson("",country,API_KEY);
                        }
                    });

                    retrieveJson("",country,API_KEY);
                }
            }
        });


    }

    public void retrieveJson(String query,String country,String apiKey){
        swiper.setRefreshing(true);
        Call<ResponseModel> call;
        if (!search_news.getText().toString().equals("")){
            call= API_clients.getInstance().getApi().getSpecificNews(query,apiKey);
        }else{
            call=API_clients.getInstance().getApi().getLatestNews(country,apiKey);
        }

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    swiper.setRefreshing(false);
                    articleList.clear();
                    articleList=response.body().getArticles();
                    adapter=new news_adapter(articleList,MainActivity.this);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                swiper.setRefreshing(false);
                Toast.makeText(MainActivity.this, t.getCause().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public  String getCountry(){

        Locale locale=Locale.getDefault();
        String country=locale.getCountry();
        return  country.toLowerCase();
    }
    }
