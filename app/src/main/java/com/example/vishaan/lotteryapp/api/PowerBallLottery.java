package com.example.vishaan.lotteryapp.api;

import android.content.Context;

import com.example.vishaan.lotteryapp.api.parser.IParser;
import com.example.vishaan.lotteryapp.api.parser.txt.TxtPowerballParser;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by Vishaan on 2/4/2015.
 */
public class PowerBallLottery extends AbstractLottery {

    private static final String LOG_TAG = PowerBallLottery.class.getSimpleName();
    private static final String URL = "http://www.powerball.com/powerball/winnums-text.txt";

    public PowerBallLottery(Context context, Map<Integer, Integer> userInputArray)
    {
        super(context, userInputArray);
        this.setLottoName("PowerBall");
        this.setFileName("PowerBall.txt");
        this.setParser(this.createParser(this.mContext, "text", this.getRawInputStream()));
        this.getParser().setRawData(this.getParser().parse());
        this.performParse();
    }

    public String getUrl() {
        return URL;
    }

    @Override
    public IParser createParser(Context context, String dataFormat, InputStream inputStream) {
        if(dataFormat.equals("json")) {
            return null;
        }
        else if(dataFormat.equals("xml")) {
            return null;
        }
        else if(dataFormat.equals("text")) {
            return new TxtPowerballParser(context, inputStream);
        }
        return null;
    }

}
