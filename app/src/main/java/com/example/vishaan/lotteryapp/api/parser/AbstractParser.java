package com.example.vishaan.lotteryapp.api.parser;

import java.io.InputStream;

/**
 * Created by Vishaan on 2/7/2015.
 */
public abstract class AbstractParser implements IParser{

    protected Integer[][] mRawData;

    protected InputStream inputStream;

    public Integer[][] getRawData() {
        return mRawData;
    }

    public void setRawData(Integer[][] mRawData) {
        this.mRawData = mRawData;
    }

    public  AbstractParser(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
