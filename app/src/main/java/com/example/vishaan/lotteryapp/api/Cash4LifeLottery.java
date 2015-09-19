package com.example.vishaan.lotteryapp.api;

import com.example.vishaan.lotteryapp.api.parser.IParser;
import com.example.vishaan.lotteryapp.api.parser.json.JSONCash4LifeParser;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by Vishaan on 2/4/2015.
 */
public class Cash4LifeLottery extends AbstractLottery {

    private static final String LOG_TAG = Cash4LifeLottery.class.getSimpleName();

    public Cash4LifeLottery(InputStream inputStream, Map<Integer, Integer> userInputArray)
    {
        super(userInputArray);
        this.setLottoName("Cash4Life");
        this.setParser(this.createParser("json", inputStream));
        this.getParser().setRawData((this.getParser()).parse());
        this.performParse();
    }


    protected IParser createParser(String dataFormat, InputStream inputStream) {
        if(dataFormat.equals("json")) {
            return new JSONCash4LifeParser(inputStream);
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
