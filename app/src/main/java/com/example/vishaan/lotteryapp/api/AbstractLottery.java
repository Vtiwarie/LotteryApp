package com.example.vishaan.lotteryapp.api;

import android.content.Context;

import com.example.vishaan.lotteryapp.api.parser.IParsable;
import com.example.vishaan.lotteryapp.api.parser.IParser;
import com.example.vishaan.lotteryapp.util.Helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vishaan on 2/4/2015.
 */
public abstract class AbstractLottery implements IParsable {

    private final static String LOG_TAG = AbstractLottery.class.getSimpleName();

    protected IParser parser;
    protected Map<Integer, Integer> map;
    protected Map<Integer, Integer> mUserInput = new HashMap<>();
    protected int mapSize;
    protected String lottoName;
    protected String mFileName = "";
    protected Context mContext;

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String mFileName) {
        this.mFileName = mFileName;
    }

    protected AbstractLottery(Context context, Map<Integer, Integer> userInputArray) {
        this.mUserInput = userInputArray;
        this.mContext = context;
    }

    public Map<Integer, Integer> getmUserInput() {
        return mUserInput;
    }

    public void setmUserInput(Map<Integer, Integer> mUserInput) {
        this.mUserInput = mUserInput;
        this.performParse();
    }

    public abstract IParser createParser(Context context, String dataFormat, InputStream inputStream);
    public abstract String getUrl();


    public void performParse() {
        if(this.getParser() == null)
        {
            return;
        }
        Map<Integer, Integer> map = Helper.getMap(LOG_TAG, new ArrayList<>(this.getmUserInput().values()), this.getParser().getRawData());
        this.setMap(map);
        this.setMapSize(this.getMap().size());
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
