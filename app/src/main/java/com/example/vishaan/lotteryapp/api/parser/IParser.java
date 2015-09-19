package com.example.vishaan.lotteryapp.api.parser;

/**
 * Created by Vishaan on 2/5/2015.
 */
public interface IParser {
//    public abstract Integer[][] parse(String string);

    Integer[][] parse();
    Integer[][] getRawData();
    void setRawData(Integer[][] rawData);
}
