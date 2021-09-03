package com.ahnsong.studymobile.connector;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahnsong.studymobile.data.user.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private Context context;
    private List<User> userList;
    private UserItemClickListener itemClickListener;

    public PersonAdapter(Context context, List<User> users, UserItemClickListener listener) {
        this.context = context;
        this.userList = users;
        this.itemClickListener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PersonViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        public PersonViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
