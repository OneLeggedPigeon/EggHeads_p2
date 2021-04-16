package com.revature.eggheads.backendp2.util;

import java.util.List;
import java.util.Random;

import static java.lang.Math.*;

/**
 * Provides normal randomization. All methods are static, as this is a pure utility class with no encapsulated data.
 */
public final class RandomUtil {
    public static int getRandomInt(int mean, int stdDeviation, int min, int max, int maxDeviation){
        Random r = new Random();
        double result = r.nextGaussian() * stdDeviation + mean;
        // bound the result
        if(maxDeviation>=0) {
            min = max(mean-maxDeviation,min);
            max = min(mean+maxDeviation,max);
        }
        result = min(max(result, min), max);
        return (int) round(result);
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

    public static String getRandomString(List<String> strings) {
        Random r = new Random();
        return strings.get(r.nextInt(strings.size()));
    }

    /**
     * Returns an int with a standard deviation of mean/10, and within three standard deviations of the mean
     * @param mean the expected value for the distribution
     * @return an int between 70% and 130% of the mean, minimum 1
     */
    public static int getRandomIntAutoScaled(int mean) {
        return getRandomInt(mean, (int) round(max(mean*0.1,1)), (int) round(max(mean * 0.7,1)), (int) round(mean * 1.3), -1);
    }
}