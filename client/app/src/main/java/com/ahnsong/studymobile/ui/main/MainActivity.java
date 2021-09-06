package com.ahnsong.studymobile.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.applications.StudyWithMeInstance;
import com.ahnsong.studymobile.base.Consts;
import com.ahnsong.studymobile.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.ahnsong.studymobile.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if (setVisibilityOfFloatingActionButton()) {
            binding.floatingActionButton.setVisibility(View.VISIBLE);
            binding.floatingActionButton.setOnClickListener(v -> startNewLecture());
        } else {
            binding.floatingActionButton.setVisibility(View.GONE);
        }
    }

    private boolean setVisibilityOfFloatingActionButton() {
        return Consts.Database.USER_STATUS_TEACHER
                .equals(StudyWithMeInstance.getInstance().getCurrentUserStatus());
    }

    private void startNewLecture() {
        Log.d(TAG, "startNewLecture");
    }
}