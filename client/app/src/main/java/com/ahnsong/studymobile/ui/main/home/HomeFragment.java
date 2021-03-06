package com.ahnsong.studymobile.ui.main.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.applications.StudyWithMeInstance;
import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.data.HomeSlide;
import com.ahnsong.studymobile.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private HomeListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setupView();
        homeViewModel.getClassData().observe(getViewLifecycleOwner(), classList -> {
            Log.d(TAG, "Size from viewModel: " + classList.size());
            adapter.setDataSet(classList);
        });
        homeViewModel.startReferenceClass();
        return root;
    }

    private void setupView() {
        setupTopView();
        setupBottomView();
    }

    private void setupTopView() {
        List<HomeSlide> pagerList = new ArrayList<>();

        pagerList.add(new HomeSlide(getString(R.string.home_slide_1_title),
                getString(R.string.home_slide_1), true, false, false));
        pagerList.add(new HomeSlide(getString(R.string.home_slide_2_title),
                getString(R.string.home_slide_2), false, true, false));
        pagerList.add(new HomeSlide(getString(R.string.home_slide_3_title),
                getString(R.string.home_slide_3), false, false, true));
        pagerList.add(new HomeSlide(getString(R.string.home_slide_4_title),
                getString(R.string.home_slide_4), false, false, false));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        SnapHelper snapHelper = new PagerSnapHelper();
        layoutManager.setAutoMeasureEnabled(true);
        binding.rvTop.setLayoutManager(layoutManager);
        binding.rvTop.setAdapter(new HomeSlideAdapter(getContext(), pagerList));
        snapHelper.attachToRecyclerView(binding.rvTop);
    }

    private void setupBottomView() {
        final int numOfColumns = 3;
        adapter = new HomeListAdapter(getContext());
        binding.rvBottom.setLayoutManager(new GridLayoutManager(getContext(), numOfColumns));
        binding.rvBottom.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}