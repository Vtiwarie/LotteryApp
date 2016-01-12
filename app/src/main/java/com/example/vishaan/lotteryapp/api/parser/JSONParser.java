package com.example.vishaan.lotteryapp.api.parser;

import android.content.Context;

import com.example.vishaan.lotteryapp.util.Helper;

/**
 * Created by Vishaan on 2/4/2015.
 */
public abstract class JSONParser extends AbstractParser {

    private final static String LOG_TAG = JSONParser.class.getSimpleName();

    protected String rawString;

    public JSONParser(Context context) {

        super(context);
        this.setRawString(Helper.getStringFromStream(LOG_TAG));

    }

    public String getRawString() {
        return this.rawString;
    }

    public void setRawString(String rawString) {
        if (this.rawString == null || this.rawString == "") {
            this.rawString = rawString;
        }
    }


    @Override
    public String toString() {
        return getRawString();
    }

}
