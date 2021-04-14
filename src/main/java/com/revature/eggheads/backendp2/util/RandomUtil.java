package com.revature.eggheads.backendp2.util;

import java.util.Random;

/**
 * Provides normal randomization. All methods are static, as this is a pure utility class with no encapsulated data.
 */
public final class RandomUtil {
    public static int getRandomInt(int mean, int stdDeviation, int min, int max, int maxDeviation){
        Random r = new Random();
        double result = r.nextGaussian() * stdDeviation + mean;
        // bound the result
        if(maxDeviation>=0) {
            min = Math.max(mean-maxDeviation,min);
            max = Math.min(mean+maxDeviation,max);
        }
        result = Math.min(Math.max(result, min), max);
        return (int) Math.round(result);
    }

    public static int getRandomInt(int mean, int stdDeviation, int min, int max){
        return getRandomInt(mean,stdDeviation,min,max,-1);
    }

    public static int getRandomIntPercentage(int mean, int stdDeviation, int maxDeviation){
        return getRandomInt(mean, stdDeviation, 0, 100, maxDeviation);
    }

    public static int getRandomIntColor(int mean, int stdDeviation, int maxDeviation){
        return getRandomInt(mean, stdDeviation, 0, 255, maxDeviation);
    }
}