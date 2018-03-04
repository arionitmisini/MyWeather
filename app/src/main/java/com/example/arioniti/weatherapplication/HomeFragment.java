package com.example.arioniti.weatherapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arioniti.weatherapplication.dal.APIInterface;
import com.example.arioniti.weatherapplication.dal.RetrofitClient;
import com.example.arioniti.weatherapplication.db.DBHelper;
import com.example.arioniti.weatherapplication.db.HomeModel;
import com.example.arioniti.weatherapplication.models.Main;
import com.example.arioniti.weatherapplication.models.WeatherAPIResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arioniti on 1/29/2018.
 */

public class HomeFragment extends Fragment {

    private static final String CLEAR_SKY_ID = "01d";

    private APIInterface apiInterface;
    private TextView tempTextViews, locationTextView;
    private ImageView weatherIcon;
    private View inflate;
    private DBHelper dbHelper;
    private HomeModel homeModel;
    private Main main;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        // Inflate the xml file for the fragment
        inflate = inflater.inflate(R.layout.home_fragment, parent, false);

        //Initialize Views
        tempTextViews = inflate.findViewById(R.id.tempTextViews);
        locationTextView = inflate.findViewById(R.id.locationTextView);
        weatherIcon = inflate.findViewById(R.id.homeWeatherIcon);

        dbHelper = new DBHelper(getContext());

        apiInterface = RetrofitClient.getClient().create(APIInterface.class);

        //Check if device is not connected to the internet
        if (Utils.isNetworkAvailable(getContext())) {
            homeModel = dbHelper.getHomeModel();
            if (homeModel != null) {
                tempTextViews.setText(homeModel.getTemp() + " °C");
                locationTextView.setText(homeModel.getLocationName());
                weatherIcon.setImageResource(weatherIcon(homeModel.getWeatherIconPath()));
            } else {
                Toast.makeText(getContext(), "You should connect device to internet for the first time", Toast.LENGTH_LONG).show();
            }
        }

        Call<WeatherAPIResult> call = apiInterface.getWeatherReq(42.660834, 21.165261, "2d16334731df0891ec0aae3edf3d73af");
        call.enqueue(new Callback<WeatherAPIResult>() {
            @Override
            public void onResponse(Call<WeatherAPIResult> call, Response<WeatherAPIResult> response) {
                //Initialize Main Class
                main = response.body().getMain();

                /*tempTextViews.setText(main.getTemp() + " °C");
                locationTextView.setText(response.body().getName());
                weatherIcon.setImageResource(weatherIcon(response.body().getWeather().get(0).getIcon()));*/
                dbHelper.createHomeModelItem(
                        new HomeModel(
                                response.body().getName(),
                                response.body().getWeather().get(0).getIcon(),
                                main.getTemp()
                        )
                );

                homeModel = dbHelper.getHomeModel();

                tempTextViews.setText(homeModel.getTemp() + " °C");
                locationTextView.setText(homeModel.getLocationName());
                weatherIcon.setImageResource(weatherIcon(homeModel.getWeatherIconPath()));
            }

            @Override
            public void onFailure(Call<WeatherAPIResult> call, Throwable t) {
                Log.i("Error", t.getMessage());
            }
        });

        return inflate;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    public int weatherIcon(String idIcon) {

        int resources = 0;

        switch (idIcon) {
            case CLEAR_SKY_ID:
                resources = R.drawable.clearsky;
                break;
            case "02d":
                resources = R.drawable.fewclouds;
                break;
            case "03d":
                resources = R.drawable.scatteredclouds;
                break;
            case "04d":
                resources = R.drawable.brokenclouds;
                break;
            case "09d":
                resources = R.drawable.showerrain;
                break;
            case "11d":
                resources = R.drawable.thunderstorm;
                break;
            case "13d":
                resources = R.drawable.snow;
                break;
            case "50d":
                resources = R.drawable.mist;
                break;
            default:
                resources = R.drawable.clearsky;
        }
        return resources;
    }

}

