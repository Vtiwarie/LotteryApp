package com.example.vishaan.lotteryapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vishaan.lotteryapp.api.AbstractLottery;
import com.example.vishaan.lotteryapp.api.Cash4LifeLottery;
import com.example.vishaan.lotteryapp.api.PowerBallLottery;
import com.example.vishaan.lotteryapp.api.chart.AbstractChart;
import com.example.vishaan.lotteryapp.api.chart.LotteryChart;
import com.example.vishaan.lotteryapp.util.Helper;

import org.achartengine.GraphicalView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DisplayFragment extends Fragment {

    private static final String LOG_TAG = DisplayFragment.class.getSimpleName();

    private AbstractLottery currentLotto;
    private ArrayList<AbstractLottery> arrLotteries = new ArrayList<>();
    private AbstractChart chart = new LotteryChart();
    private GraphicalView graphicalView;
    private static boolean debug = true;
    private ArrayList<NumberPicker> numberPickers;
    private Map<Integer, Integer> mUserInput = new HashMap();
    private int maxNumbers = 6;
    private Context mContext;

    //Constants
    private static final String[] CHART_AXIS_LABELS = {"Choose your number", "Frequency"};

    public DisplayFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.displayfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_refresh:
                updateData();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_display, container, false);
        this.mContext = inflater.getContext();

        AbstractLottery powerBall = new PowerBallLottery(getResources().openRawResource(R.raw.powerball), mUserInput);
        AbstractLottery cash4Life = new Cash4LifeLottery(getResources().openRawResource(R.raw.cash4life), mUserInput);
        this.arrLotteries.add(powerBall);
        this.arrLotteries.add(cash4Life);

        //set up the lottery selection functionality and charts
        ArrayList<String> strLotteries = new ArrayList<>();
        Iterator<AbstractLottery> iterator = this.arrLotteries.iterator();
        while (iterator.hasNext()) {
            strLotteries.add(iterator.next().getLottoName());
        }

        Spinner spinner = (Spinner) rootView.findViewById(R.id.spnChooseLotto);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity().getApplicationContext(), R.layout.custom_spinner_item, strLotteries);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DisplayFragment.this.currentLotto = DisplayFragment.this.arrLotteries.get(i);
                resetNumbers();

                if (debug) {
                    Helper.printMap(LOG_TAG, DisplayFragment.this.currentLotto.getMap());
                }
                DisplayFragment.this.addChartView(inflater.getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(inflater.getContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
            }
        });

        Button resetButton = (Button) rootView.findViewById(R.id.btnReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetNumbers();
            }
        });

        return rootView;
    }

    private void resetNumbers() {
        mUserInput.clear();
        this.currentLotto.setmUserInput(mUserInput);
        for(NumberPicker picker : this.numberPickers) {
            picker.setValue(0);
            picker.setEnabled(false);
        }

        NumberPicker firstPicker = this.numberPickers.get(0);
        firstPicker.setEnabled(true);
        firstPicker.requestFocus();
        this.addChartView(this.mContext);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //set up the number choosing UI
        maxNumbers = 6;
        this.numberPickers = new ArrayList<>(maxNumbers);
        LinearLayout linLayout = (LinearLayout) getActivity().findViewById(R.id.linLayout2_pickers);
        NumberPicker picker;
        for(int i=0; i<maxNumbers; i++) {
            picker = new NumberPicker(getActivity().getApplicationContext());
            picker.setOrientation(LinearLayout.HORIZONTAL);
            picker.setMinValue(1);
            picker.setMaxValue(60);
            picker.setEnabled(false);
            picker.setTag(i);

            linLayout.addView(picker);
            this.numberPickers.add(picker);

            picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    int offSet = (int) (picker.getTag());
                    mUserInput.put(offSet, newVal);

                    if (offSet < maxNumbers) {
                        DisplayFragment.this.numberPickers.get(++offSet).setEnabled(true);
                    }

                    DisplayFragment.this.currentLotto.setmUserInput(mUserInput);
                    DisplayFragment.this.addChartView(getActivity().getApplicationContext());
                }
            });
        }
        this.numberPickers.get(0).setEnabled(true);
    }

    private void addChartView(Context context) {

        //get chart view
        LinearLayout linLayout = (LinearLayout) getActivity().findViewById(R.id.linLayout_chart);
        if (this.graphicalView != null) {
            linLayout.removeView(this.graphicalView);
        }
        String[] legends = {getResources().getString(R.string.app_name)};
        this.graphicalView = (GraphicalView) this.chart.buildChart(context, this.currentLotto.getMap(), this.currentLotto.getLottoName(), legends, CHART_AXIS_LABELS);

        linLayout.addView(this.graphicalView, new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    private void updateData() {
        FetchLotteryData task = new FetchLotteryData();
        task.execute(this.currentLotto.getUrl());
    }

    public class FetchLotteryData extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params) {
            // If there's no zip code, there's nothing to look up.  Verify size of params.
            if (params.length == 0) {
                return null;
            }

            String URL;
            if(params[0] == null || params[0] == "") {
                return "";
            } else {
                URL = params[0];
            }

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {

                URL url = new URL(URL);

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                Log.v(LOG_TAG, buffer.toString());
                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                Log.v(LOG_TAG, buffer.toString());
                return buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } catch(Exception e) {
                e.printStackTrace();
                Log.e(LOG_TAG, "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        e.printStackTrace();
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
