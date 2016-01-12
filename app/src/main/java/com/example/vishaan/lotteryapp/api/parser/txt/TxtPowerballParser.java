package com.example.vishaan.lotteryapp.api.parser.txt;

import android.content.Context;

import com.example.vishaan.lotteryapp.api.parser.TxtParser;
import com.example.vishaan.lotteryapp.util.Helper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishaan on 8/29/2015.
 */
public class TxtPowerballParser extends TxtParser {

    private static final String LOG_TAG = TxtPowerballParser.class.getSimpleName();
    private static boolean debug = false;

    public TxtPowerballParser(Context context) {
        super(context);
    }

    @Override
    public Integer[][] parse() {
        FileInputStream inputStream = (FileInputStream)this.getInputStream();
        List<Integer[]> output = new ArrayList<>();

        if(inputStream == null)
        {
            return null;
        }

//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream)));

        String line;
        String[] winningNumbersArray;

        File f = this.getContext().getFileStreamPath("powerball.txt");
        if( f.length() <= 0) {
            return null;
        }
        try {
            while ((line = reader.readLine()) != null) {
                winningNumbersArray = Helper.explode(line, "  ");
                System.arraycopy(winningNumbersArray, 1, winningNumbersArray, 0, winningNumbersArray.length-1);
                    output.add(Helper.converStringToIntegerArray(winningNumbersArray));
                if(debug) {
//                    Helper.log(LOG_TAG, "WINNING NUMBERS " + line);
                    Integer[] i = Helper.converStringToIntegerArray(winningNumbersArray);
//                    Helper.log(LOG_TAG, Arrays.toString(i));
                }

            }
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }

            } catch (IOException e) {
//                Helper.log(LOG_TAG, e.getMessage());
            }
        }
        //reform the 2D array
        Integer[][] retVal = new Integer[output.size()][];
        for(int i=0; i<output.size(); i++) {
            retVal[i] = output.get(i);
        }

        return retVal;
    }
}
