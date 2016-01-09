package com.example.vishaan.lotteryapp.api.parser;

import android.content.Context;

import java.io.InputStream;

/**
 * Created by Vishaan on 2/7/2015.
 */
public abstract class AbstractParser implements IParser{

    protected Integer[][] mRawData;

    protected InputStream inputStream;

    protected Context mContext;

    public Integer[][] getRawData() {
        return mRawData;
    }

    public void setRawData(Integer[][] mRawData) {
        this.mRawData = mRawData;
    }

    public  AbstractParser(Context context, InputStream inputStream) {
        this.inputStream = inputStream;
        this.mContext = context;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }
}
