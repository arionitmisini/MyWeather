package com.example.arioniti.weatherapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arioniti.weatherapplication.fivedays.Daily;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 * Created by Arioniti on 1/29/2018.
 *
*/

public class FiveDaysWeatherAdapter extends BaseAdapter {

    private final Context context;
    private final int layout;

    private List<Daily> listItems;

    public FiveDaysWeatherAdapter(Context context, int layout, List<Daily> listItems) {
        this.context = context;
        this.layout = layout;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, parent, false);
        }

        //FindView by id
        ImageView imageView = view.findViewById(R.id.imageView); // new ImageView();
        TextView dateTextView = view.findViewById(R.id.dateTextView);
        TextView tempMaxTextView = view.findViewById(R.id.tempMaxTextView);
        TextView tempMinTextView = view.findViewById(R.id.tempMinTextView);

        //Initialize Daily Class from ListItems
        Daily daily = listItems.get(position);

        //Getting daily data
        Date date = daily.getDate();

        //Get Date from Daily class and format
        DateFormat formatter1 = new SimpleDateFormat("dd MMM");

        //Initialize view items
        imageView.setImageResource(weatherIcon(daily.getIdIcon()));
        dateTextView.setText(formatter1.format(date));
        tempMaxTextView.setText("Max : " + daily.getTempMax() + "°C");
        tempMinTextView.setText("Min : " + daily.getTempMin() + "°C");

        return view;
    }

    public int weatherIcon(String idIcon) {

        int resources = 0;

        switch (idIcon) {
            case "01d":
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
                resources = R.drawable.thunderstorm;
        }
        return resources;
    }

}
