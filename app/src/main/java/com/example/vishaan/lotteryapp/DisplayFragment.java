package com.example.vishaan.lotteryapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vishaan.lotteryapp.api.AbstractLottery;
import com.example.vishaan.lotteryapp.api.Cash4LifeLottery;
import com.example.vishaan.lotteryapp.api.PowerBallLottery;
import com.example.vishaan.lotteryapp.api.chart.AbstractChart;
import com.example.vishaan.lotteryapp.api.chart.LotteryChart;
import com.example.vishaan.lotteryapp.util.Helper;

import org.achartengine.GraphicalView;

import java.util.ArrayList;
import java.util.Iterator;

public class DisplayFragment extends Fragment {

    private static final String LOG_TAG = PowerBallLottery.class.getSimpleName();

    private AbstractLottery currentLotto;
    private ArrayList<AbstractLottery> arrLotteries = new ArrayList<>();
    private AbstractChart chart = new LotteryChart();
    private GraphicalView graphicalView;

    public DisplayFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_display, container, false);

        Integer[] userInputArray = {};
        AbstractLottery powerBall = new PowerBallLottery(getResources().openRawResource(R.raw.powerball), userInputArray);
        AbstractLottery cash4Life = new Cash4LifeLottery(getResources().openRawResource(R.raw.cash4life), userInputArray);
        this.arrLotteries.add(powerBall);
        this.arrLotteries.add(cash4Life);

        ArrayList<String> strLotteries = new ArrayList<>();
        Iterator<AbstractLottery> iterator = this.arrLotteries.iterator();
        while (iterator.hasNext()) {
            strLotteries.add(iterator.next().getLottoName());
        }
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spnChooseLotto);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, strLotteries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DisplayFragment.this.currentLotto = DisplayFragment.this.arrLotteries.get(i);
                String lottoName = DisplayFragment.this.currentLotto.getLottoName();
                Toast.makeText(DisplayFragment.this.getActivity().getApplicationContext(), lottoName + String.valueOf(i), Toast.LENGTH_LONG).show();

                Helper.printMap(LOG_TAG, DisplayFragment.this.currentLotto.getMap());
                DisplayFragment.this.addChartView(container, inflater.getContext());
                Toast.makeText(inflater.getContext(), "clicked i", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(inflater.getContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private void addChartView(ViewGroup container, Context context) {
        //get chart view
        LinearLayout linLayout = (LinearLayout) container.findViewById(R.id.linLayout);
        if (this.graphicalView != null) {
            linLayout.removeView(this.graphicalView);
        }
        this.graphicalView = (GraphicalView) this.chart.buildChart(context, this.currentLotto.getMap());

        linLayout.addView(this.graphicalView, new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }
}
