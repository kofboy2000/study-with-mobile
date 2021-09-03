package com.ahnsong.studymobile.ui.main.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ahnsong.studymobile.databinding.FragmentSettingsBinding;
import com.ahnsong.studymobile.ui.live.LiveStationCastActivity;
import com.ahnsong.studymobile.ui.live.LiveStationWatchActivity;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button watchBtn = binding.textWatchBroadcast;
        watchBtn.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), LiveStationWatchActivity.class))
        );
        settingsViewModel.getText().observe(getViewLifecycleOwner(), watchBtn::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}