package com.ahnsong.studymobile.ui.main.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.data.Notice;
import com.ahnsong.studymobile.databinding.ItemNoticeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ItemViewHolder> {
    private Context context;
    private List<Notice> noticeList = new LinkedList<>();
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int prePosition = -1;

    public SettingsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemNoticeBinding itemNoticeBinding = ItemNoticeBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new SettingsAdapter.ItemViewHolder(context, itemNoticeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemViewHolder holder, int position) {
        Notice notice = noticeList.get(position);
        holder.setItem(notice, position);
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public void addItem(Notice notice) {
        noticeList.add(0, notice);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context context;
        private ItemNoticeBinding binding;
        private int position;

        public ItemViewHolder(Context context, ItemNoticeBinding itemNoticeBinding) {
            super(itemNoticeBinding.getRoot());
            this.context = context;
            this.binding = itemNoticeBinding;
        }

        public void setItem(Notice notice, int position) {
            this.position = position;
            binding.noticeTitle.setText(notice.getTitle());
            binding.noticeDate.setText(notice.getDate());
            binding.noticeContents.setText(notice.getContents());
            binding.noticeExpand.setOnClickListener(this);
            changeVisibility(selectedItems.get(position));
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.notice_expand) {
                if (selectedItems.get(position)) {
                    selectedItems.delete(position);
                } else {
                    selectedItems.delete(prePosition);
                    selectedItems.put(position, true);
                }
                if (prePosition != -1)
                    notifyItemChanged(prePosition);
                notifyItemChanged(position);
                prePosition = position;
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private void changeVisibility(final boolean isExpanded) {
            binding.noticeExpand.setBackground(isExpanded ? context.getDrawable(R.drawable.ic_close_arrow):
                    context.getDrawable(R.drawable.ic_open_arrow));
            binding.noticeContentContainer.setVisibility(isExpanded ? View.VISIBLE: View.GONE);
        }
    }
}
