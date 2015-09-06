package com.example.vishaan.lotteryapp.api.parser.txt;

import com.example.vishaan.lotteryapp.api.parser.TxtParser;
import com.example.vishaan.lotteryapp.util.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vishaan on 8/29/2015.
 */
public class TxtPowerballParser extends TxtParser {

    private static final String LOG_TAG = TxtPowerballParser.class.getSimpleName();
    private static boolean debug = false;

    public TxtPowerballParser(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public Integer[][] parse() {
        InputStream inputStream = this.getInputStream();
        List<Integer[]> output = new ArrayList<>();

        if(inputStream == null)
        {
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String[] winningNumbersArray;

        try {
            while ((line = reader.readLine()) != null) {
                winningNumbersArray = Helper.explode(line, "  ");
                System.arraycopy(winningNumbersArray, 1, winningNumbersArray, 0, winningNumbersArray.length-1);
                    output.add(Helper.converStringToIntegerArray(LOG_TAG, winningNumbersArray));
                if(debug) {
                    Helper.log(LOG_TAG, "WINNING NUMBERS " + line);
                    Integer[] s = Helper.converStringToIntegerArray(LOG_TAG, winningNumbersArray);
                    Helper.log(LOG_TAG, Arrays.toString(s));
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
                Helper.log(LOG_TAG, e.getMessage());
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
