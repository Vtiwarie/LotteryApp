package com.example.vishaan.lotteryapp.api;

import android.content.Context;

import com.example.vishaan.lotteryapp.api.parser.IParser;

import java.util.Map;

/**
 * Created by Vishaan on 2/4/2015.
 */
public class Cash4LifeLottery extends AbstractLottery {

//    private static final String LOG_TAG = Cash4LifeLottery.class.getSimpleName();
//    private static final String URL = "https://data.ny.gov/api/views/kwxv-fwze/rows.json?accessType=DOWNLOAD";
//
    public Cash4LifeLottery(Context context, Map<Integer, Integer> userInputArray)
    {
        super(context, userInputArray);
//        super(context, userInputArray);
//        this.setLottoName("Cash4Life");
//        this.setFileName("Cash4Life.json");
//        this.setParser(this.createParser(this.mContext, "json");
//        this.getParser().setRawData((this.getParser()).parse());
//        this.performParse();
    }
//
    public String getUrl() {
        return null;
    }
//
    public IParser createParser(Context context, String dataFormat) {
//        if(dataFormat.equals("json")) {
//            return new JSONCash4LifeParser(context);
//        }
//        else if(dataFormat.equals("xml")) {
//            return null;
//        }
//        else if(dataFormat.equals("text")) {
//            return null;
//        }
        return null;
    }

}
