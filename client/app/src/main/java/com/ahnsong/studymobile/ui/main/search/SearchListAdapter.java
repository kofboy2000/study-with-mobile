package com.ahnsong.studymobile.ui.main.search;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.applications.GlideApp;
import com.ahnsong.studymobile.applications.StudyWithMeInstance;
import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.data.User;
import com.ahnsong.studymobile.databinding.ItemUserSearchBinding;
import com.ahnsong.studymobile.utils.Utils;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.SearchItemViewHolder> {
    private static final String TAG = "SearchListAdapter";

    private final Context context;
    private List<User> userList;

    public SearchListAdapter(Context context) {
        this.context = context;
        userList = new ArrayList<>();
    }

    @NonNull
    @NotNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemUserSearchBinding itemUserSearchBinding = ItemUserSearchBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new SearchItemViewHolder(context, itemUserSearchBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchItemViewHolder holder, int position) {
        User user = userList.get(position);
        holder.setItem(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setDataSet(List<User> users) {
        userList.clear();
        userList.addAll(users);
        notifyDataSetChanged();
    }

    static class SearchItemViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private final ItemUserSearchBinding itemUserSearchBinding;

        public SearchItemViewHolder(Context context, @NonNull @NotNull ItemUserSearchBinding itemBinding) {
            super(itemBinding.getRoot());
            this.context = context;
            this.itemUserSearchBinding = itemBinding;
        }

        public void setItem(User user) {
            itemUserSearchBinding.itemUserName.setText(user.getName());
            if (Consts.Database.USER_STATUS_TEACHER.equals(StudyWithMeInstance.getInstance().getCurrentUserStatus())) {
                itemUserSearchBinding.itemSubject.setVisibility(View.GONE);
            } else {
                itemUserSearchBinding.itemSubject.setText(user.getSubject());
            }
            itemUserSearchBinding.itemPhone.setText(user.getPhone());
            StorageReference ref = Utils.getImageReference(Consts.Storage.PROFILE, user.getProfile());
            GlideApp.with(context)
                    .load(ref)
                    .placeholder(R.color.colorGrey)
                    .into(itemUserSearchBinding.itemThumbnail);
            itemUserSearchBinding.itemLabel.setOnClickListener(v -> {
                Intent intent = new Intent( Intent.ACTION_VIEW);
                intent.setData(Uri.parse("smsto:" + Uri.encode(user.getPhone())));
                intent.putExtra( "sms_body", "Hello, I'm contacting you via StudyWithMobile." );
                context.startActivity(intent);
            });
        }
    }
}
