package com.example.vishaan.lotteryapp.api;

import com.example.vishaan.lotteryapp.api.parser.IParser;
import com.example.vishaan.lotteryapp.api.parser.json.JSONCash4LifeParser;

import java.io.InputStream;

/**
 * Created by Vishaan on 2/4/2015.
 */
public class Cash4LifeLottery extends AbstractLottery {

    private static final String LOG_TAG = Cash4LifeLottery.class.getSimpleName();

    public Cash4LifeLottery(InputStream inputStream, Integer[] userInputArray)
    {
        super(inputStream, userInputArray);
        this.setParser(this.createParser("json"));
        this.performParse(this.getRawString());
    }


    @Override
    public IParser createParser(String dataFormat) {
        if(dataFormat.equals("json")) {
            return new JSONCash4LifeParser(this.rawString);
        }
        else if(dataFormat.equals("xml")) {
            return null;
        }
        else if(dataFormat.equals("text")) {
            return null;
        }
        return null;
    }
}
