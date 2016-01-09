package com.example.vishaan.lotteryapp.api.parser;

import android.content.Context;

import java.io.InputStream;

/**
 * Created by Vishaan on 2/4/2015.
 */
public abstract class TxtParser extends AbstractParser {

    public TxtParser(Context context, InputStream inputStream) {
        super(context, inputStream);
    }


}
