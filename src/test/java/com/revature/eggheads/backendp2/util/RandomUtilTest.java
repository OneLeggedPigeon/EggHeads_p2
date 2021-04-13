package com.revature.eggheads.backendp2.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Random;

class RandomUtilTest {

    private int[] n;
    private int[] mi;
    private int[] ma;
    private int[][] r;
    Random rand;

    @BeforeEach
    void setUp() {
        // int
        n = new int[100];
        // positive int
        mi = new int[100];
        ma = new int[100];
        r = new int[100][2];
        Random rand = new Random();
        for(int i =0; i < r.length; i++){
            n[i] = rand.nextInt();
            mi[i] = rand.nextInt(10000);
            ma[i] = rand.nextInt(10000);
            for(int j = 0; j < r[i].length; j++) {
                r[i][j] = rand.nextInt(10000);
            }
        }
    }

    @Test
    void getRandomInt() {
        for(int i =0; i < r.length; i++) {
            int result = RandomUtil.getRandomInt(r[i][0], r[i][1], r[i][0]-mi[i], r[i][0]+ma[i], n[i]);
            Assert.isTrue(result >= r[i][0]-mi[i], message("below minimum: ",result,r[i][0], r[i][1], r[i][0]-mi[i], r[i][0]+ma[i], n[i]));
            Assert.isTrue(result <= r[i][0]+ma[i], message("above maximum: ",result,r[i][0], r[i][1], r[i][0]-mi[i], r[i][0]+ma[i], n[i]));
            if(n[i]>0){
                Assert.isTrue(Math.abs(result - r[i][0]) <= Math.abs(n[i]-r[i][0]),message("out of max deviation bounds: ",result,r[i][0], r[i][1], r[i][0]-mi[i], r[i][0]+ma[i], n[i]));
            }
        }
    }

    private String message(String message, int result, int mean, int stdDeviation, int min, int max, int maxDeviation){
        return message
                .concat(System.lineSeparator())
                .concat("result: "+result+System.lineSeparator())
                .concat("mean: "+mean+System.lineSeparator())
                .concat("stdDeviation: "+stdDeviation+System.lineSeparator())
                .concat("min: "+min+System.lineSeparator())
                .concat("max: "+max+System.lineSeparator())
                .concat("maxDeviation: "+maxDeviation);
    }
}