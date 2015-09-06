package com.example.vishaan.lotteryapp.api.parser;

import java.io.InputStream;

/**
 * Created by Vishaan on 2/7/2015.
 */
public abstract class AbstractParser implements IParser{

    protected InputStream inputStream;

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
