package com.ahnsong.studymobile.ui.main.search;

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

import com.ahnsong.studymobile.databinding.FragmentSearchBinding;
import com.ahnsong.studymobile.ui.live.LiveStationCastActivity;

public class SearchFragment extends Fragment {

    private SearchViewModel studyZoneViewModel;
    private FragmentSearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        studyZoneViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button broadcastBtn = binding.textStartBroadcast;
        broadcastBtn.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), LiveStationCastActivity.class))
        );
        studyZoneViewModel.getText().observe(getViewLifecycleOwner(), broadcastBtn::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}