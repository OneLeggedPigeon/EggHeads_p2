package com.revature.eggheads.backendp2.util;

import com.revature.eggheads.backendp2.exception.ParameterOutOfBoundsException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A utility Class with various methods for writing an Image for an Egg with certain attributes to an HttpResponse,
 * as well as checking that the parameters are in defined bounds.
 */
public final class EggImageUtil {

    // These are set to Strings, as they are default uri parameter values
    public static final String SMALL_WIDTH = "120";
    public static final String SMALL_HEIGHT = "120";
    public static final String MEDIUM_WIDTH = "240";
    public static final String MEDIUM_HEIGHT = "240";
    public static final String LARGE_WIDTH = "800";
    public static final String LARGE_HEIGHT = "800";

    // border of unused space around the image
    private static final int MARGIN = 4;

    // Egg shape Dimensions - These could be entered based on added Egg attributes if we want different egg shapes later.
    // For now, an egg is 3/4 as wide as it is tall
    private static final int HEIGHT_RATIO = 100;
    private static final int WIDTH_RATIO = 75;
    private static final int BASE_RATIO = 42;

    private static BufferedImage generateEggImage(int imgWidth, int imgHeight,
                                                  int size,
                                                  int red, int green, int blue,
                                                  int hRatio, int wRatio, int bRatio){
        // normalize size to between 30% and 100% instead of 0-100;
        size = (size * 7 / 10) + 30;

        // actual maximum egg dimensions
        int maxWidth = imgWidth - (MARGIN * 2);
        int maxHeight = imgHeight - (MARGIN * 2);

        // check if the egg will be cut off on the sides by a narrow image size
        if(maxWidth < maxHeight * wRatio / hRatio){
            //TODO
        }
        int height = size * maxHeight / 100;
        //calculate arc values
        int arcWidth = height * wRatio / hRatio;
        int arcHeightBase = height * bRatio / hRatio;
        int arcHeightTop = height * (hRatio - bRatio) / hRatio;
        Color color = new Color(red, green, blue);

        BufferedImage bi = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        // create background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, imgWidth, imgHeight);

        int bothX = (maxWidth - arcWidth) / 2 + MARGIN;
        int baseY = (maxHeight - height - (arcHeightBase - arcHeightTop)) + MARGIN;
        int topY = (maxHeight - height) + MARGIN;

        //
        System.out.println();
        System.out.println("imgWidth "+imgWidth);
        System.out.println("maxWidth "+maxWidth);
        System.out.println("imgHeight "+imgHeight);
        System.out.println("maxHeight "+maxHeight);
        System.out.println("size "+size);
        System.out.println("height "+height);
        System.out.println("arcWidth "+arcWidth);
        System.out.println("arcHeightBase "+arcHeightBase);
        System.out.println("arcHeightTop "+arcHeightTop);
        System.out.println("color "+color.toString());
        System.out.println("MARGIN "+MARGIN);
        System.out.println("bothX "+bothX);
        System.out.println("baseY "+baseY);
        System.out.println("topY" +topY);
        //

        // Fill Egg
        g.setColor(color);
        g.fillArc(bothX, topY, arcWidth, 2 * arcHeightTop, 0, 180);
        g.fillArc(bothX, baseY, arcWidth, 2 * arcHeightBase, 180, 180);

        // Outline Egg
        g.setColor(Color.BLACK);
        g.drawArc(bothX, topY, arcWidth, 2 * arcHeightTop, 0, 180);
        g.drawArc(bothX, baseY, arcWidth, 2 * arcHeightBase, 180, 180);

        g.dispose();

        return bi;
    }

    public static BufferedImage generateEggImage(
                                        String imgWidthS, String imgHeightS,
                                        String sizeS,
                                        String redS, String greenS, String blueS) throws IOException, ParameterOutOfBoundsException {

        // parse ints
        int imgWidth = Integer.parseInt(imgWidthS);
        int imgHeight = Integer.parseInt(imgHeightS);
        int size = Integer.parseInt(sizeS);
        int red = Integer.parseInt(redS);
        int green = Integer.parseInt(greenS);
        int blue = Integer.parseInt(blueS);

        // check if parameters are out of bounds
        areParametersOutOfBounds(size, red, green, blue);

        return generateEggImage(imgWidth, imgHeight, size,red,green,blue,HEIGHT_RATIO,WIDTH_RATIO,BASE_RATIO);
    }

    /**
     * Creates and egg image to be returned if something is wrong with the call to image creation
     * @param imgWidthS should usually use EggImageUtil.SMALL_HEIGHT
     * @param imgHeightS should usually use EggImageUtil.SMALL_WIDTH
     * @param color color of the big X to be drawn over the Egg
     * @throws IOException if this can't generate the error Egg image for some reason
     */
    public static BufferedImage generateErrorEgg(String imgWidthS, String imgHeightS, Color color) throws IOException {

        int imgWidth = Integer.parseInt(imgWidthS);
        int imgHeight = Integer.parseInt(imgHeightS);

        // create a white egg of medium size
        BufferedImage bi = generateEggImage(imgWidth, imgHeight, 50,253,253,253,HEIGHT_RATIO,WIDTH_RATIO,BASE_RATIO);
        Graphics2D g = bi.createGraphics();
        g.setColor(color);

        // draw an X over the white egg
        g.drawLine(0,0,imgWidth,imgHeight);
        g.drawLine(imgWidth,0,0,imgHeight);
        g.dispose();

        return bi;
    }

    /**
     *
     * @param size 0-100
     * @param red 0-255
     * @param green 0-255
     * @param blue 0-255
     * @throws ParameterOutOfBoundsException
     */
    public static void areParametersOutOfBounds(int size,
                                                int red, int green, int blue) throws ParameterOutOfBoundsException {
        if(size < 0 || size > 100 ||
                red < 0 || red > 255 ||
                green < 0 || green > 255 ||
                blue < 0 || blue > 255){
            throw new ParameterOutOfBoundsException("One of the egg parameters is out of bounds");
        }
    }
}
