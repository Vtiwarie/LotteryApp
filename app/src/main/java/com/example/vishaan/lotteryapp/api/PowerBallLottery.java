package com.example.vishaan.lotteryapp.api;

import android.content.Context;

import com.example.vishaan.lotteryapp.api.parser.IParser;
import com.example.vishaan.lotteryapp.api.parser.txt.TxtPowerballParser;

import java.util.Map;

/**
 * Created by Vishaan on 2/4/2015.
 */
public class PowerBallLottery extends AbstractLottery {

    private static final String URL = "http://www.powerball.com/powerball/winnums-text.txt";
    public static int MAX_NUMBERS = 69;

    public PowerBallLottery(Context context, Map<Integer, Integer> userInputArray)
    {
        super(context, userInputArray);
        this.setLottoName("PowerBall");
        this.setParser(this.createParser(this.mContext, "text"));
        this.getParser().setRawData(this.getParser().parse());
        this.setMaxNumbers(MAX_NUMBERS);
        this.performParse();
    }

    @Override
    public IParser createParser(Context context, String dataFormat) {
        if(dataFormat.equals("json")) {
            return null;
        }
        else if(dataFormat.equals("xml")) {
            return null;
        }
        else if(dataFormat.equals("text")) {
            return new TxtPowerballParser(context);
        }
        return null;
    }

}
