package com.ahnsong.studymobile.ui.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.data.HomeSlide;
import com.ahnsong.studymobile.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setupTopView();
        return root;
    }

    private void setupTopView() {
        List<HomeSlide> pagerList = new ArrayList<>();
        pagerList.add(new HomeSlide(getString(R.string.home_slide_1_title),
                                    getString(R.string.home_slide_1), false));
        pagerList.add(new HomeSlide(getString(R.string.home_slide_2_title),
                getString(R.string.home_slide_2), false));
        pagerList.add(new HomeSlide(getString(R.string.home_slide_3_title),
                getString(R.string.home_slide_3), true));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setAutoMeasureEnabled(true);
        binding.rvTop.setLayoutManager(layoutManager);
        binding.rvTop.setAdapter(new MainSlidePagerAdapter(getContext(), pagerList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}