package com.android.myapplication.network.responses;

import com.android.myapplication.models.CatFactModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCatFactsResponse {

    @SerializedName("all")
    @Expose()
    private List<CatFactModel> catFactModels;

    public AllCatFactsResponse(List<CatFactModel> catFactModels) {
        this.catFactModels = catFactModels;
    }

    public List<CatFactModel> getCatFactModels() {
        return catFactModels;
    }

    @Override
    public String toString() {
        return "AllCatFactsResponse{" +
                "catFactModels=" + catFactModels +
                '}';
    }
}
