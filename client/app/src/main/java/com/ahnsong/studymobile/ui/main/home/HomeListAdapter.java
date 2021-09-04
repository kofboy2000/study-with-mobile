package com.ahnsong.studymobile.ui.main.home;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder> {

    @NonNull
    @NotNull
    @Override
    public HomeListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class HomeListViewHolder extends RecyclerView.ViewHolder {

        public HomeListViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
