package com.example.vishaan.lotteryapp.api;

import org.json.JSONArray;

/**
 * Created by Vishaan on 1/8/2016.
 */
public class AbstractDrawing {

    public static final String convertNumbersToJSON(Integer[] numbers) {
        JSONArray json = new JSONArray();
        if(numbers == null || numbers.length <= 0) {
            return "";
        }
        for(Integer number : numbers) {
            json.put(number);
        }
        return json.toString();
    }

    public static final Integer[] convertJSONToNumbers(String str) {
        try {
            JSONArray json = new JSONArray(str);
            if(json == null || json.length() <= 0) {
                return null;
            }
            Integer[] numbers = new Integer[json.length()];
            for(int i=0; i<json.length(); i++) {
                numbers[i] = new Integer((int)json.get(i));
            }
            return numbers;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
