package com.ahnsong.studymobile.ui.main.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.applications.GlideApp;
import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.data.HomeSlide;
import com.ahnsong.studymobile.ui.player.YoutubePlayerActivity;
import com.ahnsong.studymobile.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class HomeSlideAdapter extends RecyclerView.Adapter<HomeSlideAdapter.MainSlidePageHolder> {
    private Context context;
    private List<HomeSlide> homeSlideList;

    public HomeSlideAdapter(Context context, List<HomeSlide> slides) {
        this.context = context;
        this.homeSlideList = slides;
    }

    @NonNull
    @NotNull
    @Override
    public MainSlidePageHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_slide, parent, false);
        return new MainSlidePageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainSlidePageHolder holder, int position) {
        MainSlidePageHolder mainSlidePageHolder = holder;
        mainSlidePageHolder.onBind(context, homeSlideList.get(position));
    }

    @Override
    public int getItemCount() {
        return homeSlideList.size();
    }

    static class MainSlidePageHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;
        private FloatingActionButton actionButton;

        public MainSlidePageHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.slide_title);
            image = itemView.findViewById(R.id.slide_image);
            actionButton = itemView.findViewById(R.id.slide_action_button);
        }

        public void onBind(Context context, HomeSlide homeSlide) {
            title.setText(homeSlide.getTitle());
            StorageReference background =
                    Utils.getImageReference(Consts.Storage.SLIDE, homeSlide.getImageName());
            GlideApp.with(context)
                    .load(background)
                    //.placeholder(R.color.gray)
                    .fitCenter()
                    .into(image);
            if (homeSlide.isPlayable()) {
                actionButton.setOnClickListener(v -> {
                    playYoutubeLink(context);
                });
            } else {
                actionButton.setVisibility(View.GONE);
            }
        }

        private void playYoutubeLink(Context context) {
            context.startActivity(new Intent(context, YoutubePlayerActivity.class));
        }
    }

}
