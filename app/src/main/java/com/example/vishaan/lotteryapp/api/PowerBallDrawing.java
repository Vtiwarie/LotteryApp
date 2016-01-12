package com.example.vishaan.lotteryapp.api;

import com.example.vishaan.lotteryapp.util.Helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static PowerBallDrawing parseRow(String row) {
        List<Integer[]> output = new ArrayList<>();

        Integer[] winningNumbers = new Integer[5];
        String[] parseData;

        try {
            parseData = Helper.explode(row, "  ");
            String dateString = (parseData[0] != null) ? parseData[0] : "";
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = df.parse(dateString);
            Helper.log(date.toString());
            for(int i=0; i<5; i++) {
                winningNumbers[i] = (parseData[i+1] != null) ? Integer.parseInt(parseData[i+1]) : null;
            }
            int powerball = Integer.parseInt(parseData[6]);
            output.add(Helper.converStringToIntegerArray(parseData));
//            Helper.log("WINNING NUMBERS " + row);
//            Helper.log(Arrays.toString(winningNumbers));
//            Helper.log(powerball + "***");
            return new PowerBallDrawing(date, winningNumbers, powerball);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
