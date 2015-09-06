package com.example.vishaan.lotteryapp.api;

import com.example.vishaan.lotteryapp.api.parser.IParser;
import com.example.vishaan.lotteryapp.api.parser.txt.TxtPowerballParser;

import java.io.InputStream;

/**
 * Created by Vishaan on 2/4/2015.
 */
public class PowerBallLottery extends AbstractLottery {

    private static final String LOG_TAG = PowerBallLottery.class.getSimpleName();

    public PowerBallLottery(InputStream inputStream, Integer[] userInputArray)
    {
        super(inputStream, userInputArray);
        this.setLottoName("PowerBall");
        this.setParser(this.createParser("text", inputStream));
        this.performParse();
    }

    @Override
    protected IParser createParser(String dataFormat, InputStream inputStream) {
        if(dataFormat.equals("json")) {
            return null;
        }
        else if(dataFormat.equals("xml")) {
            return null;
        }
        else if(dataFormat.equals("text")) {
            return new TxtPowerballParser(inputStream);
        }
        return null;
    }
}
