package com.example.vishaan.lotteryapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.example.vishaan.lotteryapp.api.AbstractDrawing;
import com.example.vishaan.lotteryapp.api.PowerBallDrawing;
import com.example.vishaan.lotteryapp.db.Contracts;
import com.example.vishaan.lotteryapp.db.LotteryDataSource;
import com.example.vishaan.lotteryapp.util.Helper;

import java.util.Date;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    private LotteryDataSource mDataSource;

    void deleteDatabases() {
        mContext.deleteDatabase(Contracts.PowerBall.TABLE_NAME);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        deleteDatabases();
        mDataSource = new LotteryDataSource(mContext);
        mDataSource.open();
        assertTrue(mDataSource.isOpen());
    }

    public void testPowerBall() {
//        mDataSource.resetPowerBalls();
        Helper.log("WRITING DATA");
        Integer[] numbers = new Integer[]{1, 2, 3, 4, 5};
        PowerBallDrawing drawing = new PowerBallDrawing(new Date(), numbers, 1);
        long ts = mDataSource.createPowerBallRow(drawing);
        assertTrue("Create Powerball row failed.", ts > 0);

        Helper.log("READING DATA");
        List<AbstractDrawing> drawings = mDataSource.getPowerBalls(null, null);

    }
}