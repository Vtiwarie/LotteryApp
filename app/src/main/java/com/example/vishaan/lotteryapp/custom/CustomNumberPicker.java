package com.example.vishaan.lotteryapp.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.vishaan.lotteryapp.R;

/**
 * Created by Vishaan on 9/20/2015.
 */
public class CustomNumberPicker extends LinearLayout{

    private static final String LOG_TAG = CustomNumberPicker.class.getSimpleName();

    private int mOrientation = LinearLayout.VERTICAL;
    private Context context;

    private OnValueChangeListener mOnValueChangeListener;
    private int mValue;
    private static int LAYOUT_RESOURCE_ID = R.layout.custom_numberpicker;

    public CustomNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;

//        this.setOrientation(mOrientation);
//
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        this.setLayoutParams(layoutParams);

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(LAYOUT_RESOURCE_ID, this, true);
    }


    public void setMinValue(int minValue) {

//        postInvalidate();
    }

    public void setMaxValue(int maxValue) {

//        postInvalidate();
    }

    /**
     * Interface to listen for changes of the current value.
     */
    public interface OnValueChangeListener {
        void onValueChange(CustomNumberPicker picker, int oldVal, int newVal);
    }

    public void setOnValueChangedListener(CustomNumberPicker.OnValueChangeListener onValueChangeListener) {
        this.mOnValueChangeListener = onValueChangeListener;
    }

    private void notifyChange(int previous) {
        if( mOnValueChangeListener != null) {
            mOnValueChangeListener.onValueChange(this, previous, mValue);
        }
    }

}
