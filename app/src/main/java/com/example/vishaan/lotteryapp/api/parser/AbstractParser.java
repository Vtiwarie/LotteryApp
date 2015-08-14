package com.example.vishaan.lotteryapp.api.parser;

/**
 * Created by Vishaan on 2/7/2015.
 */
public abstract class AbstractParser implements IParser{

    protected String rawString;

    public AbstractParser(String rawString)
    {
        this.rawString = rawString;
    }
}
