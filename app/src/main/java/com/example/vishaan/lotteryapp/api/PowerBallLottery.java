package com.example.vishaan.lotteryapp.api;

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

    public PowerBallLottery(InputStream inputStream, Map<Integer, Integer> userInputArray)
    {
        super(userInputArray);
        this.setLottoName("PowerBall");
        this.setParser(this.createParser("text", inputStream));
        this.getParser().setRawData(this.getParser().parse());
        this.performParse();

    }

    public String getUrl() {
        return URL;
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
