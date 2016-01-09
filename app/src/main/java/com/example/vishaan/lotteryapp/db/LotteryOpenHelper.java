package com.example.vishaan.lotteryapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vishaan on 1/7/2016.
 */
public class LotteryOpenHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "db_lottery";
    public static final int DB_VERSION = 1;

    private static LotteryOpenHelper instance;

    public static synchronized LotteryOpenHelper getInstance(Context context) {
        if(instance == null) {
            instance = new LotteryOpenHelper(context);
        }
        return instance;
    }

    private LotteryOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql = "CREATE TABLE IF NOT EXISTS " + Contracts.PowerBall.TABLE_NAME + "(" +
                Contracts.PowerBall.Columns.PB_DATE + " INTEGER PRIMARY KEY NOT NULL, " +
                Contracts.PowerBall.Columns.PB_NUMBERS + " TEXT NOT NULL, " +
                Contracts.PowerBall.Columns.PB_POWERBALL + " INTEGER NOT NULL " +
        ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contracts.PowerBall.TABLE_NAME);
        onCreate(db);
    }


}
