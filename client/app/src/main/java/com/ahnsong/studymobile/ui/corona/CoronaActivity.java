package com.ahnsong.studymobile.ui.corona;

import android.content.Intent;
import android.net.Uri;

import androidx.lifecycle.ViewModelProvider;

import com.ahnsong.studymobile.R;
import com.ahnsong.studymobile.base.BaseActivity;
import com.ahnsong.studymobile.databinding.ActivityCoronaBinding;

public class CoronaActivity extends BaseActivity {
    private static final String TAG = "CoronaActivity";
    private ActivityCoronaBinding binding;
    private CoronaViewModel coronaViewModel;

    @Override
    protected int setLayout() {
        return R.layout.activity_corona;
    }

    @Override
    protected void initView() {
        binding = ActivityCoronaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initStatView();
        initActionEvent();
    }

    @Override
    protected void initData() {
        coronaViewModel = new ViewModelProvider(this).get(CoronaViewModel.class);
        coronaViewModel.getCoronaStatLiveData().observe(this, coronaStat -> {
            binding.affected.textNumber.setText(String.valueOf(coronaStat.getDecideCnt()));
            binding.death.textNumber.setText(String.valueOf(coronaStat.getDeathCnt()));
            binding.exam.textNumber.setText(String.valueOf(coronaStat.getExamCnt()));
            binding.care.textNumber.setText(String.valueOf(coronaStat.getCareCnt()));
            binding.defRate.textNumber.setText(String.format("%.2f", coronaStat.getAccDefRate()));
        });
        coronaViewModel.requestData();
    }

    private void initStatView() {
        binding.affected.textTitle.setText(getString(R.string.affected));
        binding.death.textTitle.setText(getString(R.string.death));
        binding.exam.textTitle.setText(getString(R.string.exam));
        binding.care.textTitle.setText(getString(R.string.care));
        binding.defRate.textTitle.setText(getString(R.string.definite));
    }

    private void initActionEvent() {
        binding.callPhone.setOnClickListener(v-> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:1339"));
            startActivity(intent);
        });
        binding.textMessage.setOnClickListener(v-> {
            Intent intent = new Intent( Intent.ACTION_VIEW);
            intent.setData(Uri.parse("smsto:" + Uri.encode("1339")));
            intent.putExtra( "sms_body", "I feel bit sick." );
            startActivity(intent);
        });
    }
}