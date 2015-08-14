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
    protected String rawString;
    protected int mapSize;

    protected AbstractLottery(InputStream inputStream, Integer[] userInputArray) {
        this.rawString = Helper.getStringFromStream(LOG_TAG, inputStream);
        this.userInputArray = userInputArray;
    }

    protected abstract IParser createParser(String dataFormat);

    @Override
    public void performParse(String rawString) {
        Map<Integer, Integer> map = Helper.getMap(LOG_TAG, this.userInputArray, this.getParser().parse(this.getRawString()));
        this.setMap(map);
        this.setMapSize(this.getMap().size());
        Helper.log(LOG_TAG, String.valueOf(this.getMapSize()));
    }

    public IParser getParser() {
        return parser;
    }

    public void setParser(IParser parser) {
        this.parser = parser;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    public String getRawString() {
        return rawString;
    }

    public void setRawString(String rawString) {
        this.rawString = rawString;
    }

    @Override
    public String toString() {
        return getRawString();
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }
}
