package com.android.myapplication.viewmodel;

import android.app.Application;

import com.android.myapplication.persistance.CatFact;
import com.android.myapplication.repositories.CatFactsRepository;
import com.android.myapplication.utils.Resource;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class CatFactsListViewModel extends AndroidViewModel {
    private CatFactsRepository catFactsRepository;
    private MediatorLiveData<Resource<List<CatFact>>> catFacts = new MediatorLiveData<>();

    public CatFactsListViewModel(@NonNull Application application) {
        super(application);
        catFactsRepository = CatFactsRepository.getInstance(application);
//        catFacts = catFactsRepository
    }

    public LiveData<Resource<List<CatFact>>> getAllCatFacts() {
        final LiveData<Resource<List<CatFact>>> repositorySource = catFactsRepository.getCatFacts();
        catFacts.addSource(repositorySource, new Observer<Resource<List<CatFact>>>() {
            @Override
            public void onChanged(Resource<List<CatFact>> listResource) {
                catFacts.setValue(listResource);
            }
        });
        return catFacts;
    }

//    public void insert(CatFact catFact) {
//        catFactsRepository.insert(catFact);
//    }

}
