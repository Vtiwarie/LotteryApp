package com.example.vishaan.lotteryapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vishaan.lotteryapp.api.AbstractDrawing;
import com.example.vishaan.lotteryapp.api.PowerBallDrawing;
import com.example.vishaan.lotteryapp.util.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vishaan on 1/7/2016.
 */
public class LotteryDataSource {
    private LotteryOpenHelper mDBHelper;
    private SQLiteDatabase db;

    public LotteryDataSource(Context context) {
        mDBHelper = LotteryOpenHelper.getInstance(context);
    }

    public boolean isOpen() {
        return db.isOpen();
    }

    public void open() {
        db = mDBHelper.getWritableDatabase();
    }

    public void close() {
        if(isOpen()) {
            db.close();
        }
    }

    public void resetPowerBalls() {
        db.delete(Contracts.PowerBall.TABLE_NAME, null, null);
    }

    public long createPowerBallRow(PowerBallDrawing drawing) {
        open();
        ContentValues cv = new ContentValues();
        cv.put(Contracts.PowerBall.Columns.PB_DATE, drawing.getDrawDate().getTime());
        cv.put(Contracts.PowerBall.Columns.PB_NUMBERS, AbstractDrawing.convertNumbersToJSON(drawing.getNumbers()));
        cv.put(Contracts.PowerBall.Columns.PB_POWERBALL, drawing.getPowerBall());
        long ts = db.insert(Contracts.PowerBall.TABLE_NAME, null, cv);
        Helper.log("PowerBall Created: " + drawing);
        close();
        return ts;
    }

    public List<PowerBallDrawing> getPowerBalls(Date dateFrom, Date dateTo) {
        open();
        List<PowerBallDrawing> drawings = new ArrayList<>();
        Cursor cursor = db.query(
                Contracts.PowerBall.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            PowerBallDrawing drawing = new PowerBallDrawing();
            Date date = new Date();
            date.setTime(cursor.getLong(cursor.getColumnIndex(Contracts.PowerBall.Columns.PB_DATE)));
            drawing.setDrawDate(date);
            drawing.setNumbers(AbstractDrawing.convertJSONToNumbers(cursor.getString(cursor.getColumnIndex(Contracts.PowerBall.Columns.PB_NUMBERS))));
            drawing.setPowerBall(cursor.getInt(cursor.getColumnIndex(Contracts.PowerBall.Columns.PB_POWERBALL)));
            drawings.add(drawing);
            Helper.log("Powerball Drawing: " + drawing);
        }
        Helper.log("# Entries found: " + cursor.getCount());
        cursor.close();
        close();
        return drawings;
    }
}
