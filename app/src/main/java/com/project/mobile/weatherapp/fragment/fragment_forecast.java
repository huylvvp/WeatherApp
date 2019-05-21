package com.project.mobile.weatherapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.mobile.weatherapp.adapter.DailyAdapter;
import com.project.mobile.weatherapp.R;
import com.project.mobile.weatherapp.model.Daily;
import com.project.mobile.weatherapp.model.open_weather_map.ListOfWeather;
import com.project.mobile.weatherapp.model.open_weather_map.OpenWeatherMap;
import com.project.mobile.weatherapp.model.open_weather_map.OpenWeatherPredict;
import com.project.mobile.weatherapp.utils.NetworkChecking;
import com.project.mobile.weatherapp.utils.Weather5DaysAsyncTask;
import com.project.mobile.weatherapp.utils.WeatherAsyncTask;
import com.project.mobile.weatherapp.utils.doComplete;
import com.project.mobile.weatherapp.utils.doComplete5Days;

import java.util.ArrayList;
import java.util.List;


/**
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class fragment_forecast extends Fragment {
    private double lat;
    private double lon;
    public List<Daily> mList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DailyAdapter mAdapter;
    private DataCommunication dataCommunication;
    private Context context;
    private Weather5DaysAsyncTask weather5DaysAsyncTask;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        Bundle args = getArguments();
        lat = args.getDouble("lat");
        lon = args.getDouble("lon");
        Log.i("Log", "check");
        getForecastWeather();


    }


    private void getForecastWeather() {
//        if (NetworkChecking.isNetworkAvailable(getContext())) {
            weather5DaysAsyncTask = new Weather5DaysAsyncTask(lat, lon, new doComplete5Days() {
                @Override
                public void doComplete(OpenWeatherPredict openWeatherPredict) {
                    int i = 0;
                    for (ListOfWeather list : openWeatherPredict.getListWeather()) {
                        mList.get(i).setmTextWeather(list.getWeather().get(0).getDescription());
                        mList.get(i).setmTextDate(list.getDt_txt());
                        mList.get(i).setmTempMin(list.getTemp_min() + "");
                        mList.get(i).setmTempMax(list.getTemp_max() + "");
                        i ++ ;
                        Log.i("Debug", openWeatherPredict.getListWeather().get(i).getClouds() + "");
                    }
                }
            });
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Log.i("Log", "check 1");
//        Log.i("Log", "check 2");

        View view = inflater.inflate(R.layout.fragment_forecast, null);
        initView();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_daily);
        mAdapter = new DailyAdapter(mList,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        return view;
    }
    private void initView () {
        Daily daily = new Daily("THỨ NĂM, THG 5 16", "Nắng nhẹ", " 36", "26");
        mList.add(daily);
    }

    public List<Daily> getmList() {
        return mList;
    }
}