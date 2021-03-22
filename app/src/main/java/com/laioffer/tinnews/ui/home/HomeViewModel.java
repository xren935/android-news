package com.laioffer.tinnews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

import java.util.function.Function;

public class HomeViewModel extends ViewModel {

    private final NewsRepository repository;
    // the below is like a call-back
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }

    public void setCountryInput(String country) {
        // feed the data
        countryInput.setValue(country);
    }

    public LiveData<NewsResponse> getTopHeadlines() {
        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
//        return Transformations.switchMap(countryInput, new Function<String, LiveData<NewsResponse>>(){
//            @Override
//            public LiveData<NewsResponse> apply(String country){
//                return repository.getTopHeadlines(country); // from homeFragment we get 'us'
//            }
//        });
    }
}