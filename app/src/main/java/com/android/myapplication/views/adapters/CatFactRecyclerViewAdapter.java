package com.android.myapplication.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.myapplication.R;
import com.android.myapplication.persistance.CatFact;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CatFactRecyclerViewAdapter extends RecyclerView.Adapter<CatFactRecyclerViewAdapter.CatFactViewHolder> {

    private List<CatFact> catFacts;
    private static CatFactRecyclerViewAdapter.MyClickListener myClickListener;

    public CatFactRecyclerViewAdapter() {
        catFacts = new ArrayList<>();
    }

    public void setCatFactModelList(List<CatFact> catFacts) {
        this.catFacts = catFacts;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public CatFactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cat_fact_list_item, parent, false);
        return new CatFactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatFactViewHolder holder, int position) {
        CatFact catFact = catFacts.get(position);
        holder.tvCatFactItem.setText(catFact.getText());
    }


    @Override
    public int getItemCount() {
        return catFacts.size();
    }

    static class CatFactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_cat_fact_item)
        TextView tvCatFactItem;

        public CatFactViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }
}