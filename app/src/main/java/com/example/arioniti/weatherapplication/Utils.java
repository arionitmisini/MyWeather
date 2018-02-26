package com.example.arioniti.weatherapplication;

import com.example.arioniti.weatherapplication.fivedays.Daily;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Arioniti on 1/31/2018.
 */

public class Utils {


    private Utils() throws IllegalAccessException {
        throw new IllegalAccessException("Utils class");
    }

    public static List<Daily> getDayliForFiveDaysFormated(ArrayList<Daily> lista) throws ParseException {
        Set<Daily> temp = new HashSet<>();

        Date dateWi = removeTime(lista.get(0).getDate());
        lista.get(0).setDate(dateWi);
        temp.add(lista.get(0));
        for (Iterator<Daily> iterator = lista.iterator(); iterator.hasNext(); ) {

            Daily newObject = iterator.next();
            Date dateWithoutHours = removeTime(newObject.getDate());
            newObject.setDate(dateWithoutHours);

            if (iterator.hasNext()) {
                if (temp.contains(newObject)) {
                    for (Daily oldObject : temp) {
                        if (oldObject.equals(newObject)) {
                            double max = newObject.getTempMax();
                            double min = newObject.getTempMin();
                            if (max > oldObject.getTempMax()) {
                                max = newObject.getTempMax();
                                oldObject.setTempMax(max);
                            }
                            if (min < oldObject.getTempMin()) {
                                min = newObject.getTempMin();
                                oldObject.setTempMin(min);
                            }
                        }
                    }
                } else {
                    temp.add(newObject);
                }
            }
        }
        ArrayList<Daily>tempList = new ArrayList<>(temp);
        Collections.sort(tempList);
        return tempList;
    }

    private static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
