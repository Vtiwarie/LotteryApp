package com.example.vishaan.lotteryapp.api.chart;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Vishaan on 8/22/2015.
 */
public class LotteryChart extends AbstractChart implements IDemoChart {


    public String getName() {
        return "Lottery Chart Name";
    }

    public String getDesc() {
        return "Lottery Chart Description";
    }

    /**
     * Builds a bar multiple series renderer to use the provided colors.
     *
     * @param colors the series renderers colors
     * @return the bar multiple series renderer
     */
    protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setBarSpacing(1.5);
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(colors[i]);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a bar multiple series dataset using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the XY multiple bar dataset
     */
    protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            double[] v = values.get(i);
            int seriesLength = v.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(v[k]);
            }
            dataset.addSeries(series.toXYSeries());
        }
        return dataset;
    }

//    public View buildChart(Context context, List<double[]> values) {
//        String[] titles = new String[]{"2007"};
//        int[] colors = new int[]{Color.CYAN};
//        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
//        renderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
    //    setChartSettings(renderer, "Monthly sales in the last 2 years", "Month", "Units sold", 0.5,
    //            12.5, 0, 24000, Color.GRAY, Color.LTGRAY);
    //    renderer.setXLabels(1);
    //    renderer.setYLabels(10);
    //    renderer.addXTextLabel(1, "Jan");
    //    renderer.addXTextLabel(3, "Mar");
    //    renderer.addXTextLabel(5, "May");
    //    renderer.addXTextLabel(7, "Jul");
    //    renderer.addXTextLabel(10, "Oct");
    //    renderer.addXTextLabel(12, "Dec");
//        int length = renderer.getSeriesRendererCount();
//        for (int i = 0; i < length; i++) {
//            SimpleSeriesRenderer seriesRenderer = renderer.getSeriesRendererAt(i);
//            seriesRenderer.setDisplayChartValues(true);
//        }
////        return ChartFactory.getBarChartIntent(context, buildBarDataset(titles, values), renderer,
////                Type.DEFAULT);
//
//        return ChartFactory.getBarChartView(context, buildBarDataset(titles, values), renderer, BarChart.Type.DEFAULT);
//    }

    /**
     * Builds a bar multiple series dataset using the provided Map.
     *
     * @param titles the series titles
     * @param map the values
     * @return the XY multiple bar dataset
     */
    protected XYMultipleSeriesDataset buildBarDataset(String[] titles, Map<Integer, Integer> map) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            Set<Integer> keys = map.keySet();
            Iterator iterator = keys.iterator();
            while(iterator.hasNext())
            {
                int key = (int)iterator.next();
                series.add(String.valueOf(key), map.get(key));
            }
            dataset.addSeries(series.toXYSeries());
        }
        return dataset;
    }

    public View buildChart(Context context, Map<Integer, Integer> map)
    {
        String[] titles = new String[]{"2007"};
        int[] colors = new int[]{Color.CYAN};
        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
        renderer.setPanEnabled(false, true);
        renderer.setPanLimits(new double[] {0, 60, 0, 1000});
        renderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        setChartSettings(renderer, "Lottery numbers", "Month", "Numbers", 0,
                60, 0, 100, Color.RED, Color.WHITE);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            SimpleSeriesRenderer seriesRenderer = renderer.getSeriesRendererAt(i);
            seriesRenderer.setDisplayChartValues(true);
        }

        return ChartFactory.getBarChartView(context, buildBarDataset(titles, map), renderer, BarChart.Type.DEFAULT);
    }

}

