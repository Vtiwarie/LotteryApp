package com.example.vishaan.lotteryapp.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vishaan on 2/2/2015.
 */
public class Helper {

    private static final String LOG_TAG = Helper.class.getSimpleName();

    public static void log(String message) {
        Log.v(LOG_TAG, message);
    }

    public static void t(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT);
    }

    public static <E> void printList(String tag, List<E> list) {
        Iterator<E> iterator = list.iterator();

        while (iterator.hasNext()) {
//            log(tag, String.valueOf(iterator.next()));
        }
    }

    public static void printMap(String tag, Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            log(tag, entry.getKey() + " = " + entry.getValue());
        }
    }


    public static <Integer> String getMapString(String tag, Map<Integer, Integer> map) {
        Iterator<Integer> iterator = map.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {

            Integer next = iterator.next();
            sb.append("Key: " + next + " " + "Value: " + map.get(next));
            sb.append(map.get(next));
            sb.append("\n");
        }
        return sb.toString();
    }

    public static <T> void print2DArray(String tag, T[][] array2D) {
        for (int i = 0; i < array2D.length; i++) {
            for (int j = 0; j < array2D[i].length; j++) {
//                log(tag, array2D[i][j].toString());
            }
        }
    }

    public static String getStringFromStream(String tag, InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            if (sb.length() == 0) {
                return null;
            }
            inputStream.mark(100);
            inputStream.reset();
            return sb.toString();
        } catch (IOException e) {

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }

            } catch (IOException e) {
//                Helper.log(tag, e.getMessage());
            }
        }
        return null;
    }

    public static Integer[] converStringToIntegerArray(String tag, String[] target) {
        Integer[] results = new Integer[target.length];

        for (int i = 0; i < target.length; i++) {
            try {
                results[i] = new Integer(Integer.parseInt(target[i]));
            } catch (NumberFormatException nfe) {
//                Log.e(tag, nfe.getMessage());
//                nfe.printStackTrace();
//                results[i] = 0;
            }
        }

        return results;
    }

    public static Map<Integer, Integer> getMap(String tag, List<Integer> userInputArray, Integer[][] dataArray) {
//        Integer[] mUserInput = {2, 3, 4};
//        Integer[][] dataArray =
//                {
//                        {1, 2, 3, 4, 5},
//                        {1, 6, 7, 8, 9},
//                        {1, 2, 3, 12, 13},
//                        {2, 4, 3, 16, 17},
//                        {2, 18, 19, 3, 21},
//                        {3, 22, 2, 24, 17}
//                };

        if(dataArray == null) {
            return new HashMap<Integer, Integer>();
        }

        Map<Integer, Integer> outputMap = new LinkedHashMap<>();

        initializeMap(tag, outputMap);

        try {
            boolean bShouldAdd;
            for (Map.Entry<Integer, Integer> entry : outputMap.entrySet()) {
                int i = entry.getKey();
                bShouldAdd = true;
                if (( ! userInputArray.isEmpty()) && !Arrays.asList(dataArray[i]).containsAll(userInputArray)) {
                    continue;
                }
                if (bShouldAdd) {
                    for (int p = 0; p < dataArray[i].length; p++) {
                        if (!outputMap.containsKey(dataArray[i][p])) {
                            outputMap.put(dataArray[i][p], 1);
                        } else {
                            outputMap.put(dataArray[i][p], outputMap.get(dataArray[i][p]) + 1);
                        }
                    }
                }
            }
//            Helper.log(tag, "HASH MAP OUTPUT");
            Helper.printMap(tag, outputMap);
            return outputMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void initializeMap(String tag, Map<Integer, Integer> map) {
        //initialize the map
        for (int i = 1; i <= 60; i++) {
            map.put(i, 0);
        }
    }

    public static String[] explode(String target, String glue) {
        return target.split(glue);
    }

    public static String[] explodeIntegers(String target, String glue) {
        String[] str = explode(target, glue);
        for (int i = 0; i < str.length; i++) {
            str[i] = String.valueOf(Integer.parseInt(str[i]));
        }
        return str;
    }

    public static String removeLeadingZeros(String str) {
        if (str.matches("\\d+"))
            return str.replaceAll("^0+", "");
        return str;
    }
}
