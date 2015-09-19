package com.example.vishaan.lotteryapp.api.parser;

import com.example.vishaan.lotteryapp.util.Helper;

import java.io.InputStream;

/**
 * Created by Vishaan on 2/4/2015.
 */
public abstract class JSONParser extends AbstractParser {

    private final static String LOG_TAG = JSONParser.class.getSimpleName();

    protected String rawString;

    public JSONParser(InputStream inputStream) {

        super(inputStream);
        this.setRawString(Helper.getStringFromStream(LOG_TAG, inputStream));

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
