package com.shikanga.livedata.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.shikanga.livedata.models.NewsResponse;
import com.shikanga.livedata.networking.NewsApi;
import com.shikanga.livedata.networking.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
    This class provides Singleton network request for hitting API and using LiveData for observing API response.
    LiveData is an observable data holder class.
    Unlike a regular observable, LiveData respects the lifecycle of other app components, such as activities and fragments.
 */
public class NewsRepository {
    private static NewsRepository newsRepository;

    public static NewsRepository getInstance(){
        if(newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private NewsApi newsApi;

    public NewsRepository(){
        newsApi = RetrofitService.createService(NewsApi.class);
    }

    public MutableLiveData<NewsResponse> getNews(String source, String key){
        final MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        newsApi.getNewsList(source, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("NewsRepository", t.getMessage());
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
