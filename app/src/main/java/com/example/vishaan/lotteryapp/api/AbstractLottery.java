package com.example.vishaan.lotteryapp.api;

import com.example.vishaan.lotteryapp.api.parser.IParsable;
import com.example.vishaan.lotteryapp.api.parser.IParser;
import com.example.vishaan.lotteryapp.util.Helper;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by Vishaan on 2/4/2015.
 */
public abstract class AbstractLottery implements IParsable {

    private final static String LOG_TAG = AbstractLottery.class.getSimpleName();

    protected IParser parser;
    protected Map<Integer, Integer> map;
    protected Integer[] userInputArray;
    protected int mapSize;
    protected String lottoName;

    protected AbstractLottery(InputStream inputStream, Integer[] userInputArray) {
        this.userInputArray = userInputArray;
    }

    protected abstract IParser createParser(String dataFormat, InputStream inputStream);

    public void performParse() {
        if(this.getParser() == null)
        {
            return;
        }
        Map<Integer, Integer> map = Helper.getMap(LOG_TAG, this.userInputArray, this.getParser().parse());
        this.setMap(map);
        this.setMapSize(this.getMap().size());
        Helper.log(LOG_TAG, String.valueOf(this.getMapSize()));
    }


    public IParser getParser() {
        return this.parser;
    }

    public void setParser(IParser parser) {
        this.parser = parser;
    }

    public Map<Integer, Integer> getMap() {
        return this.map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    public int getMapSize() {
        return this.mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public String getLottoName() {
        return lottoName;
    }

    public void setLottoName(String lottoName) {
        this.lottoName = lottoName;
    }
}
