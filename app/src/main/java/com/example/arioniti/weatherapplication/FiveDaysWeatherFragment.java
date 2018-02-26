package com.example.arioniti.weatherapplication;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.arioniti.weatherapplication.dal.APIInterface;
import com.example.arioniti.weatherapplication.dal.RetrofitClient;
import com.example.arioniti.weatherapplication.models.City;
import com.example.arioniti.weatherapplication.models.Daily;
import com.example.arioniti.weatherapplication.models.FiveDaysWeatherModel;
import com.example.arioniti.weatherapplication.models.ListWeather;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arioniti on 1/26/2018.
 */

public class FiveDaysWeatherFragment extends Fragment {

    private static final String TAG = FiveDaysWeatherFragment.class.getSimpleName();

    APIInterface apiInterface;
    ListView listView;
    ArrayAdapter<FiveDaysWeatherFragment> listAdapter;
    TextView cityNameTextView, timeNowTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        // Inflate the xml file for the fragment
        return inflater.inflate(R.layout.five_days_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Intialize Interface retrofit client
        apiInterface = RetrofitClient.getClient().create(APIInterface.class);

        //Intialize ListView
        listView = view.findViewById(R.id.listVieew);

        //Date formater
        final DecimalFormat df = new DecimalFormat("##.##");
        final DateFormat formatter1 = new SimpleDateFormat("dd MMM");
        final ArrayList<Daily> dailyArrayList = new ArrayList<>();

        Call<FiveDaysWeatherModel> call = apiInterface.getFiveWeatherReq(42.660834, 21.165261, "2d16334731df0891ec0aae3edf3d73af");
        call.enqueue(new Callback<FiveDaysWeatherModel>() {
            @Override
            public void onResponse(Call<FiveDaysWeatherModel> call, final Response<FiveDaysWeatherModel> response) {
                //Intialize ListWeather
                List<ListWeather> list = response.body().getList();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                //Intialize City
                City city = response.body().getCity();

                for (int i = 0; i < list.size(); i++) {
                    String date = list.get(i).getDtTxt();
                    try {
                        dailyArrayList.add(
                                new Daily(
                                        list.get(i).getMain().getTempMax(),
                                        list.get(i).getMain().getTempMin(),
                                        city.getName(),
                                        dateFormat.parse(date),
                                        list.get(i).getWeather().get(0).getIcon()
                                )
                        );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                List<Daily> dayliForFiveDaysFormated = new ArrayList<>();
                try {
                    dayliForFiveDaysFormated = Utils.getDayliForFiveDaysFormated(dailyArrayList);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //Response
                        List<ListWeather> list = response.body().getList();
                        Daily daily = (Daily) adapterView.getItemAtPosition(i);
                        Date dateDaily = daily.getDate();

                        //Pop up TextView initialize
                        TextView cityNamePopUp, tempPopUp, tempMaxPopUp, tempMinPopUp, datePopUp, windySpeedPopUp;
                        ImageButton imageButton;

                        //Create dialog
                        final Dialog fbDialogue = new Dialog(getContext(), android.R.style.Theme_Black_NoTitleBar);
                        fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                        fbDialogue.setContentView(R.layout.pop_up_fragment);
                        fbDialogue.setCancelable(true);
                        fbDialogue.show();

                        //City temp format
                        double temp = Double.parseDouble(df.format(list.get(i).getMain().getTemp()));

                        //Date format
                        DateFormat formatter1 = new SimpleDateFormat("dd MMM");

                        //FindViewByID by Dialog call
                        cityNamePopUp = fbDialogue.findViewById(R.id.city_name_popup_field);
                        tempPopUp = fbDialogue.findViewById(R.id.temp_popup_field);
                        tempMaxPopUp = fbDialogue.findViewById(R.id.temp_max_popup_field);
                        tempMinPopUp = fbDialogue.findViewById(R.id.temp_min_popup_field);
                        datePopUp = fbDialogue.findViewById(R.id.date_popup_field);
                        windySpeedPopUp = fbDialogue.findViewById(R.id.wind_speed_popup_field);
                        imageButton = fbDialogue.findViewById(R.id.close_button_pop_up);

                        //Set TextView
                        cityNamePopUp.setText(response.body().getCity().getName() + "");
                        tempPopUp.setText(temp + "°C");
                        tempMaxPopUp.setText(daily.getTempMax() + "°C");
                        tempMinPopUp.setText(daily.getTempMin() + "°C");
                        datePopUp.setText(formatter1.format(dateDaily) + "");
                        windySpeedPopUp.setText(list.get(i).getWind().getSpeed() + "km/h");

                        //OnClick Button exit fragment
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                fbDialogue.dismiss();
                            }
                        });

                        return true;
                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Daily daily = (Daily) adapterView.getItemAtPosition(i);
                        Toast.makeText(getContext(), "Date: " + formatter1.format(daily.getDate()) + "\nTemp max: " + daily.getTempMax() + "\nTemp min: " + daily.getTempMin(), Toast.LENGTH_LONG).show();
                    }
                });

                listView.setAdapter(new FiveDaysWeatherAdapter(getContext(), R.layout.daily_list_items, dayliForFiveDaysFormated));
            }

            @Override
            public void onFailure(Call<FiveDaysWeatherModel> call, Throwable t) {
                Log.e(TAG, t.getMessage());

            }
        });
    }

}
