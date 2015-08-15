package com.example.vishaan.lotteryapp.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Vishaan on 2/2/2015.
 */
public class Helper {
    public static void log(String tag, String message) {
        Log.v(tag, message);
    }
    private static boolean debug = true;

    public static <E> void printList(String tag, List<E> list) {
        Iterator<E> iterator = list.iterator();

        while (iterator.hasNext()) {
            if(debug)
            {
                log(tag, String.valueOf(iterator.next()));
            }
        }
    }

    public static void printMap(String tag, Map<Integer, Integer> map) {
        Iterator<Integer> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            int i = iterator.next();
            if(debug)
            {
                log(tag, "Key: " + i + "  " + "Value: " + map.get(i));
            }
        }
    }

    public static <T> void print2DArray(String tag, T[][] array2D) {
        for(int i=0; i<array2D.length; i++) {
            for(int j=0; j<array2D[i].length; j++) {
                if(debug)
                {
                    log(tag, array2D[i][j].toString());
                }
            }
        }
    }

    public static String getStringFromStream(String tag, InputStream inputStream) {
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
            return sb.toString();
        } catch (IOException e) {

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }

            } catch (IOException e) {
                Helper.log(tag, e.getMessage());
            }
        }
        return null;
    }

    public static Integer[] converStringToIntegerArray(String tag, String[] target) {
        Integer[] results = new Integer[target.length];

        for (int i=0; i<target.length; i++) {
            try {
                results[i] = new Integer(Integer.parseInt(target[i]));
            }
            catch (NumberFormatException nfe) {
                Log.e(tag, nfe.getMessage());
                nfe.printStackTrace();
            }
        }

        return results;
    }

    public static Map<Integer, Integer> getMap(String tag, Integer[] userInputArray, Integer[][] dataArray) {
//        Integer[] userInputArray = {2, 3, 4};
//        Integer[][] dataArray =
//                {
//                        {1, 2, 3, 4, 5},
//                        {1, 6, 7, 8, 9},
//                        {1, 2, 3, 12, 13},
//                        {2, 4, 3, 16, 17},
//                        {2, 18, 19, 3, 21},
//                        {3, 22, 2, 24, 17}
//                };

        Map<Integer, Integer> outputMap = new HashMap<Integer, Integer>();

        //initialize the map
        Iterator<Integer> iterator = outputMap.keySet().iterator();

        for(int i=0; i<dataArray.length; i++) {
            outputMap.put(i, 0);
        }

        try{
            boolean bShouldAdd;
            for(int i=0; i<dataArray.length; i++)
            {
                bShouldAdd = true;
//                Helper.log(tag, String.valueOf(Arrays.asList(dataArray[i]).containsAll(Arrays.asList(userInputArray))));
                if((userInputArray.length>0) && ! Arrays.asList(dataArray[i]).containsAll(Arrays.asList(userInputArray)))
                {
                    continue;
                }
                if(bShouldAdd)
                {
                    for(int p=0; p<dataArray[i].length; p++) {
                        if ( ! outputMap.containsKey(dataArray[i][p])) {
                            outputMap.put(dataArray[i][p], 1);
                        } else {
                            outputMap.put(dataArray[i][p], outputMap.get(dataArray[i][p]) + 1);
                        }
                    }
                }
            }
            if(debug)
            {
                Helper.log(tag, "HASH MAP");
                Helper.printMap(tag, outputMap);
            }
            return outputMap;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] explode(String target, String glue) {
        return target.split(glue);
    }

    public static String[] explodeIntegers(String target, String glue) {
        String[] str = explode(target, glue);
        for(int i=0; i<str.length; i++) {
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
