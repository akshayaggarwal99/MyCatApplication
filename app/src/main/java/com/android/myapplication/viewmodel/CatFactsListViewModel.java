package com.android.myapplication.viewmodel;

import android.app.Application;

import com.android.myapplication.models.CatFactModel;
import com.android.myapplication.repositories.CatFactsRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CatFactsListViewModel extends AndroidViewModel {
    private CatFactsRepository catFactsRepository;

    public CatFactsListViewModel(@NonNull Application application) {
        super(application);
        catFactsRepository = CatFactsRepository.getInstance(application);
//        catFacts = catFactsRepository
    }

    public LiveData<List<CatFactModel>> getAllCatFacts() {
        return catFactsRepository.getCatFacts();
    }

//    public void insert(CatFact catFact) {
//        catFactsRepository.insert(catFact);
//    }

}
