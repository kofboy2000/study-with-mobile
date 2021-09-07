package com.ahnsong.studymobile.ui.main.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.applications.GlideApp;
import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.data.Notice;
import com.ahnsong.studymobile.databinding.FragmentSettingsBinding;
import com.ahnsong.studymobile.utils.Utils;
import com.google.firebase.storage.StorageReference;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private SettingsViewModel settingsViewModel;
    private SettingsAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        adapter = new SettingsAdapter(getContext());
        binding.rvNoticeList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvNoticeList.setAdapter(adapter);

        referenceUserData();
        referenceNoticeData();
        return root;
    }

    private void referenceUserData() {
        settingsViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            StorageReference ref = Utils.getImageReference(Consts.Storage.PROFILE, user.getProfile());
            GlideApp.with(getContext())
                .load(ref)
                .placeholder(R.color.colorGrey)
                .into(binding.imgUserProfile);
            binding.tvUserName.setText(user.getName());
            binding.tvUserStatus.setText(user.getUserStatus());
        });
        settingsViewModel.startReferenceUserData();
    }

    private void referenceNoticeData() {
        settingsViewModel.getNoticeLiveData().observe(getViewLifecycleOwner(), list -> {
            for (Notice item : list) {
                adapter.addItem(item);
            }
            adapter.notifyDataSetChanged();
        });
        settingsViewModel.startReferenceNoticeData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}