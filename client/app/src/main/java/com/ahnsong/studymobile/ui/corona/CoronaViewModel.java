package com.ahnsong.studymobile.ui.corona;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahnsong.studymobile.BuildConfig;
import com.ahnsong.studymobile.data.opendata.Corona;
import com.ahnsong.studymobile.data.opendata.Item;
import com.ahnsong.studymobile.network.OpenDataClient;
import com.ahnsong.studymobile.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoronaViewModel extends ViewModel {
    private static final String TAG = "CoronaViewModel";
    private MutableLiveData<CoronaStat> coronaStatLiveData;

    public CoronaViewModel() {
        coronaStatLiveData = new MutableLiveData<>();
    }

    public void requestData() {
        int startDate = Utils.getToday(-2);
        int endDate = Utils.getToday(0);
        Call<Corona> call = OpenDataClient.getApiService().getCoronaInfo(BuildConfig.OPENDATA_API_KEY,
                1, 6, startDate, endDate);
        call.enqueue(new Callback<Corona>() {

            @Override
            public void onResponse(Call<Corona> call, Response<Corona> response) {
                Log.d(TAG, "onResponse: " + response.message() + ", " + response.code());
                Log.d(TAG, "getHeader: " + response.body().getHeader().getResultMsg());
                List<Item> itemList = response.body().getBody().getItems().getItemList();
                if (itemList != null && !itemList.isEmpty()) {
                    Item today = itemList.get(0);
                    Item yesterday = itemList.get(1);
                    CoronaStat coronaStat = new CoronaStat(
                            today.getDecideCnt()-yesterday.getDecideCnt(),
                            today.getDeathCnt()-yesterday.getDeathCnt(),
                            today.getExamCnt(), today.getCareCnt(), today.getAccDefRate());
                    coronaStatLiveData.setValue(coronaStat);
                }
            }

            @Override
            public void onFailure(Call<Corona> call, Throwable t) {
                Log.e(TAG, "Unable to get requested data");
            }
        });
    }

    public MutableLiveData<CoronaStat> getCoronaStatLiveData() {
        return coronaStatLiveData;
    }

    public void callPhone() {

    }

    public void textMessage() {
    }
}
