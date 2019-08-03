package com.shikanga.livedata.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shikanga.livedata.models.NewsResponse;
import com.shikanga.livedata.repositories.NewsRepository;

public class NewsViewModel extends ViewModel {
    private MutableLiveData<NewsResponse> mutableLiveData;
    private NewsRepository newsRepository;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        newsRepository = NewsRepository.getInstance();
        mutableLiveData = newsRepository.getNews("google-news", "9bbfa2e83a874ba3a5292842c6c36495");
    }

    public LiveData<NewsResponse> getNewsRepository(){
        return mutableLiveData;
    }
}
