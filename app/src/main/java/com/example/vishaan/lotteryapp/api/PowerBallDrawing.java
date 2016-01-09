package com.example.vishaan.lotteryapp.api;

import java.util.Date;

/**
 * Created by Vishaan on 1/7/2016.
 */
public class PowerBallDrawing extends AbstractDrawing {

    private Date mDrawDate;
    private Integer[] mNumbers;
    private int mPowerBall;

    public PowerBallDrawing() {
        this(null, null, 0);
    }
    public PowerBallDrawing(Date drawDate, Integer[] numbers, int powerBall) {
        setDrawDate(drawDate);
        setNumbers(numbers);
        setPowerBall(powerBall);
    }

    @Override
    public String toString() {
        return mDrawDate + " - " + convertNumbersToJSON(mNumbers) +  " - " + mPowerBall;
    }

    public Date getDrawDate() {
        return mDrawDate;
    }

    public void setDrawDate(Date drawDate) {
        mDrawDate = drawDate;
    }

    public int getPowerBall() {
        return mPowerBall;
    }

    public void setPowerBall(int powerBall) {
        mPowerBall = powerBall;
    }

    public Integer[] getNumbers() {
        return mNumbers;
    }

    public void setNumbers(Integer[] numbers) {
        mNumbers = numbers;
    }
}
