package com.ahnsong.studymobile.ui.main.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.applications.GlideApp;
import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.data.MyClass;
import com.ahnsong.studymobile.databinding.ItemHomeLectureBinding;
import com.ahnsong.studymobile.ui.live.LiveStationWatchActivity;
import com.ahnsong.studymobile.utils.Utils;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder> {
    private static final String TAG = "HomeListAdapter";

    private Context context;
    private List<MyClass> myClassList;

    public HomeListAdapter(Context context) {
        this.context = context;
        this.myClassList = new ArrayList<>();
    }

    @NonNull
    @NotNull
    @Override
    public HomeListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemHomeLectureBinding itemHomeLectureBinding = ItemHomeLectureBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new HomeListViewHolder(context, itemHomeLectureBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeListViewHolder holder, int position) {
        MyClass myClass = myClassList.get(position);
        holder.setItem(myClass);
    }

    @Override
    public int getItemCount() {
        return myClassList.size();
    }

    public void setDataSet(List<MyClass> myClassList) {
        this.myClassList.clear();
        this.myClassList.addAll(myClassList);
        notifyDataSetChanged();
    }

    static class HomeListViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private final ItemHomeLectureBinding itemHomeLectureBinding;

        public HomeListViewHolder(Context context, @NonNull @NotNull ItemHomeLectureBinding itemBinding) {
            super(itemBinding.getRoot());
            this.context = context;
            this.itemHomeLectureBinding = itemBinding;
        }

        public void setItem(MyClass myClass) {
            final String liveStatus = "live";
            itemHomeLectureBinding.tvUserName.setText(myClass.getTeacher());
            itemHomeLectureBinding.tvClassTitle.setText(myClass.getTitle());
            itemHomeLectureBinding.tvClassTime.setText(String.format("%s.%s", myClass.getMonth(), myClass.getDay()));
            StorageReference ref = Utils.getImageReference(Consts.Storage.PROFILE, myClass.getProfile());
            GlideApp.with(context)
                    .load(ref)
                    .placeholder(R.color.colorGrey)
                    .into(itemHomeLectureBinding.imgUserProfile);
            if (liveStatus.equals(myClass.getStatus())) {
                itemHomeLectureBinding.imgLiveLabel.setVisibility(View.VISIBLE);
            }
            itemHomeLectureBinding.thumbItemContainer.setOnClickListener(v -> {
                if (liveStatus.equals(myClass.getStatus())) {
                    Intent intent = new Intent(context, LiveStationWatchActivity.class);
                    intent.putExtra("classid", myClass.getClassid());
                    intent.putExtra("profile", myClass.getProfile());
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, context.getString(R.string.live_status_none), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
