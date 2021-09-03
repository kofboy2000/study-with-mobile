package com.ahnsong.studymobile.ui.init;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.data.Permission;

import java.util.ArrayList;
import java.util.List;


public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.PermissionViewHolder> {
    private List<Permission> permissionList;

    public PermissionAdapter(Context context) {
        permissionList = new ArrayList<>();
        permissionList.add(new Permission(context.getString(R.string.permission_camera),
                context.getString(R.string.permission_camera_sub)));
        permissionList.add(new Permission(context.getString(R.string.permission_picture),
                context.getString(R.string.permission_picture_sub)));
        permissionList.add(new Permission(context.getString(R.string.permission_storage),
                context.getString(R.string.permission_storage_sub)));
        permissionList.add(new Permission(context.getString(R.string.permission_mic),
                context.getString(R.string.permission_mic_sub)));
    }

    @NonNull
    @Override
    public PermissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_permission, parent, false);
        return new PermissionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PermissionViewHolder holder, int position) {
        Permission permissionModel = permissionList.get(position);

        holder.title.setText(permissionModel.getTitle());
        holder.subText.setText(permissionModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return permissionList.size();
    }

    public static class PermissionViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView subText;

        public PermissionViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            subText = itemView.findViewById(R.id.tv_des);
        }
    }
}
