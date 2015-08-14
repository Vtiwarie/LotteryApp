package com.example.vishaan.lotteryapp.api.parser.json;

import com.example.vishaan.lotteryapp.api.parser.JSONParser;
import com.example.vishaan.lotteryapp.util.Helper;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Vishaan on 2/7/2015.
 */
public class JSONCash4LifeParser extends JSONParser {

    private static final String LOG_TAG = JSONCash4LifeParser.class.getSimpleName();
    private static boolean debug = true;

    public JSONCash4LifeParser(String rawString) {
        super(rawString);
    }

    @Override
    public Integer[][] parse(String rawString) {
        try {
            JSONObject object = new JSONObject(rawString);
            JSONArray data = object.getJSONArray("data");

            String[] winningNumbersArray = null;
            JSONArray winningEntryArray = null;
            String winningNumbers = null;
            Integer[][] dataArray = new Integer[data.length()][];

            for (int i=0; i < data.length(); i++) {
                winningEntryArray = data.getJSONArray(i);
                winningNumbers = winningEntryArray.getString(9);
                if(debug)
                {
                    Helper.log(LOG_TAG, "WINNING NUMBERS " + winningNumbers);
                }
                winningNumbersArray = Helper.explode(winningNumbers, " ");
                dataArray[i] = Helper.converStringToIntegerArray(LOG_TAG, winningNumbersArray);
            }
            if(debug)
            {
                Helper.log(LOG_TAG, "DATA ARRAY");
//                Helper.print2DArray(LOG_TAG, dataArray);
            }
            return dataArray;

        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }
}
