package com.ahnsong.studymobile.ui.main;

import android.content.Context;
import android.content.Intent;
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
import com.ahnsong.studymobile.firebase.FirebaseCallback;
import com.ahnsong.studymobile.firebase.FirebaseManager;
import com.ahnsong.studymobile.ui.live.LiveStationCastActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = this;
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
        FirebaseManager manger = new FirebaseManager(this);
        manger.createLectureReference(key -> {
            Intent intent = new Intent(context,
                    LiveStationCastActivity.class);
            intent.putExtra("key", key);
            startActivity(intent);
        });
    }
}