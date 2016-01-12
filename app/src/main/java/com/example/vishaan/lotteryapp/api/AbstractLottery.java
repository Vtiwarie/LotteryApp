package com.example.vishaan.lotteryapp.api;

import android.content.Context;

import com.example.vishaan.lotteryapp.api.parser.IParsable;
import com.example.vishaan.lotteryapp.api.parser.IParser;
import com.example.vishaan.lotteryapp.util.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vishaan on 2/4/2015.
 */
public abstract class AbstractLottery implements IParsable {

    protected IParser parser;
    protected Map<Integer, Integer> map;
    protected Map<Integer, Integer> mUserInput = new HashMap<>();
    private List<AbstractDrawing> mDrawings = new ArrayList<>();
    protected int mapSize;
    protected String lottoName;
    protected Context mContext;
    protected int maxNumbers;

    protected AbstractLottery(Context context, Map<Integer, Integer> userInputArray) {
        this.mUserInput = userInputArray;
        this.mContext = context;
    }

    public Map<Integer, Integer> getUserInput() {
        return mUserInput;
    }

    public void setUserInput(Map<Integer, Integer> mUserInput) {
        this.mUserInput = mUserInput;
        this.performParse();
    }

    public abstract IParser createParser(Context context, String dataFormat);


    public void performParse() {
        if(this.getParser() == null)
        {
            return;
        }
//        Map<Integer, Integer> map = Helper.getMap(LOG_TAG, new ArrayList<>(this.getUserInput().values()), this.getParser().getRawData());
        Map<Integer, Integer> map = createMapFromDrawings();
        this.setMap(map);
        this.setMapSize(this.getMap().size());
    }

    public void setDrawings(List<AbstractDrawing> drawings) {
        mDrawings = drawings;
    }

    private Map<Integer, Integer> createMapFromDrawings() {
        List<Integer> userInputArray = new ArrayList<>(this.getUserInput().values());
        List<AbstractDrawing> drawings = this.getDrawings();

        Map<Integer, Integer> outputMap = new LinkedHashMap<>();

        Helper.initializeMap(outputMap, this.getMaxNumbers());
        if(drawings == null || drawings.isEmpty()) {
            return outputMap;
        }

        Integer[] winningNumbers;
        try {
            for (AbstractDrawing drawing : drawings) {
                winningNumbers = ((PowerBallDrawing)drawing).getNumbers();
                //if all of the user-inputted values were not found, go to the next drawing
                if (( ! userInputArray.isEmpty()) && !Arrays.asList(winningNumbers).containsAll(userInputArray)) {
                    continue;
                }
                for (int p = 0; p < winningNumbers.length; p++) {
                    if (!outputMap.containsKey(winningNumbers[p])) {
                        outputMap.put(winningNumbers[p], 1);
                    } else {
                        outputMap.put(winningNumbers[p], outputMap.get(winningNumbers[p]) + 1);
                    }
                }
            }
            Helper.log("HASH MAP OUTPUT");
            Helper.printMap(outputMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputMap;
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

    public int getMaxNumbers() {
        return maxNumbers;
    }

    public void setMaxNumbers(int maxNumbers) {
        this.maxNumbers = maxNumbers;
    }

    public List<AbstractDrawing> getDrawings() {
        return mDrawings;
    }
}
