package com.example.vishaan.lotteryapp;

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

import org.achartengine.GraphicalView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DisplayFragment extends Fragment {

    private static final String LOG_TAG = PowerBallLottery.class.getSimpleName();

    private AbstractLottery currentLotto;
    private  ArrayList<AbstractLottery> arrLotteries = new ArrayList<>();
    private AbstractChart chart = new LotteryChart();
    private GraphicalView chartView = null;

    public DisplayFragment() {
//        this.chartView = new GraphicalView(getActivity().getApplicationContext(), this.chart);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_display, container, false);

        Integer[] userInputArray = {2};
        AbstractLottery powerBall = new PowerBallLottery(getResources().openRawResource(R.raw.powerball), userInputArray);
        AbstractLottery cash4Life = new Cash4LifeLottery(getResources().openRawResource(R.raw.cash4life), userInputArray);
        this.arrLotteries.add(powerBall);
        this.arrLotteries.add(cash4Life);

        ArrayList<String> strLotteries = new ArrayList<>();
        Iterator<AbstractLottery> iterator = this.arrLotteries.iterator();
        while(iterator.hasNext())
        {
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

//                List<double[]> xValues =
//                List<double[]> yValues = new ArrayList<>();

                Map<Integer, Integer> testMap = new HashMap<>();
                testMap.put(1, 1);
                testMap.put(2, 2);
                testMap.put(3, 3);

//                values.add(new double[]{5230, 7300, 9240, 10540, 7900, 9200, 12030, 11200, 9500, 10500,
//                        11600, 13500});
//                values.add(new double[]{14230, 12300, 14240, 15244, 15900, 19200, 22030, 21200, 19500, 15500,
//                        12600, 14000});
//                GraphicalView graphicalView = (GraphicalView) DisplayFragment.this.chart.buildChart(inflater.getContext(), values);
                 GraphicalView graphicalView = (GraphicalView) DisplayFragment.this.chart.buildChart(inflater.getContext(), DisplayFragment.this.currentLotto.getMap());
                LinearLayout linLayout= (LinearLayout) rootView.findViewById(R.id.linLayout);
                DisplayFragment.this.addChartView(linLayout, graphicalView);
                Toast.makeText(inflater.getContext(), "clicked i", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(inflater.getContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private void addChartView(ViewGroup rootView, GraphicalView graphicalView) {
//        //get chart view
//        if (graphicalView == null) {
//            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
//            graphicalView = ChartFactory.getLineChartView(this, mDataset,
//                    mRenderer);
//            layout.addView(mChartView, new LayoutParams
//                    (LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//        } else {
//            graphicalView.repaint();
//        }

        //get chart view
//        if (graphicalView == null) {
            rootView.addView(graphicalView, new ViewGroup.LayoutParams
                    ( ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT));
//        } else {
//            graphicalView.repaint();
//        }

    }

}
