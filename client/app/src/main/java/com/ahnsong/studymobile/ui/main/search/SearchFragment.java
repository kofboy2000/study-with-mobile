package com.ahnsong.studymobile.ui.main.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.data.user.User;
import com.ahnsong.studymobile.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;
    private List<User> userList = new ArrayList<>();
    private SearchListAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        adapter = new SearchListAdapter(getContext());
        searchViewModel.getUserList().observe(getViewLifecycleOwner(), users -> {
            userList = users.stream()
                .filter(user -> Consts.Database.USER_STATUS_TEACHER.equals(user.getUserStatus()))
                .collect(Collectors.toList());
            adapter.setDataSet(userList);
        });
        binding.searchRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.searchRecylerView.setAdapter(adapter);
        searchViewModel.startReferenceUsers();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}