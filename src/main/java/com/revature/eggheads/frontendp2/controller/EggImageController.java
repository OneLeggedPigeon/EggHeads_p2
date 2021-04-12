package com.revature.eggheads.frontendp2.controller;

import com.revature.eggheads.frontendp2.exception.BackendParameterOutOfBoundsException;
import com.revature.eggheads.frontendp2.exception.ParameterNotIncludedException;
import com.revature.eggheads.frontendp2.util.EggImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 *
 * Example Sources
 * <p>img src='image/egg?height=30&width=50&size=50&red=12&green=20&blue=130'</p>
 * <p>img src='image/egg?height=60&width=500&size=30&red=24&green=40&blue=255'</p>
 */
@RestController
@RequestMapping("image/egg")
public class EggImageController {

    @Autowired
    ServletContext context;

    /**
     * All Parameters are read from the request and parsed into Strings
     * @param imgHeight Image Height in pixels, defaults to EggImageUtil.HEIGHT
     * @param imgWidth Image Width in pixels, defaults to EggImageUtil.WIDTH
     * @param size 0-100
     * @param red 0-255
     * @param green 0-255
     * @param blue 0-255
     * @param response the httpResponse that the image will be written into
     * @throws IOException
     * @throws ParameterOutOfBoundsException
     */
    @GetMapping
    public void mediumImage(@RequestParam(name = "height", defaultValue = EggImageUtil.HEIGHT) String imgHeight,
                              @RequestParam(name = "width", defaultValue = EggImageUtil.WIDTH) String imgWidth,
                              @RequestParam(name = "size", required = false) String size,
                              @RequestParam(name = "red", required = false) String red,
                              @RequestParam(name = "green", required = false) String green,
                              @RequestParam(name = "blue", required = false) String blue,
                              HttpServletResponse response) throws IOException {
        generateImage(imgHeight, imgWidth, size, red, green, blue, response);
    }
    @GetMapping("/small")
    public void smallImage(@RequestParam(name = "size", required = false) String size,
                            @RequestParam(name = "red", required = false) String red,
                            @RequestParam(name = "green", required = false) String green,
                            @RequestParam(name = "blue", required = false) String blue,
                            HttpServletResponse response) throws IOException {
        generateImage(EggImageUtil.SMALL_HEIGHT, EggImageUtil.SMALL_WIDTH, size, red, green, blue, response);
    }
    @GetMapping("/large")
    public void largeImage(@RequestParam(name = "size", required = false) String size,
                           @RequestParam(name = "red", required = false) String red,
                           @RequestParam(name = "green", required = false) String green,
                           @RequestParam(name = "blue", required = false) String blue,
                           HttpServletResponse response) throws IOException {
        generateImage(EggImageUtil.LARGE_HEIGHT, EggImageUtil.LARGE_WIDTH, size, red, green, blue, response);
    }


    private void generateImage(String imgHeight, String imgWidth, String size, String red, String green, String blue, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        ServletOutputStream outStream = response.getOutputStream();
        BufferedOutputStream bout = new BufferedOutputStream(outStream);
        BufferedImage bufferedImage = null;
        try{
            // check that the parameters aren't out of bounds
            if(
                    size == null ||
                            red == null ||
                            green == null ||
                            blue == null)
            {
                throw new ParameterNotIncludedException("Insufficient parameters have been included for dynamic egg image creation");
            }

            bufferedImage = EggImageUtil.generateEggImage(imgWidth, imgHeight, size, red, green, blue);

        } catch (ParameterNotIncludedException e){
            bufferedImage = EggImageUtil.generateErrorEgg(EggImageUtil.SMALL_WIDTH, EggImageUtil.SMALL_HEIGHT, Color.BLACK);
            e.printStackTrace();
        } catch (BackendParameterOutOfBoundsException e){
            bufferedImage = EggImageUtil.generateErrorEgg(EggImageUtil.SMALL_WIDTH, EggImageUtil.SMALL_HEIGHT, Color.BLUE);
            e.printStackTrace();
        } catch (Exception e){
            bufferedImage = EggImageUtil.generateErrorEgg(EggImageUtil.SMALL_WIDTH, EggImageUtil.SMALL_HEIGHT, Color.RED);
            e.printStackTrace();
        } finally {
            ImageIO.write(bufferedImage, "jpeg", outStream);
            bout.close();
            outStream.close();
        }
    }

//    /**
//     * The Error Egg for ParameterOutOfBoundsException
//     * @param response the HttpServletResponse to be loaded with an Egg jpg
//     */
//    @ExceptionHandler(BackendParameterOutOfBoundsException.class)
//    public void generateImageParameterOutOfBoundsException(HttpServletResponse response) throws IOException{
//        EggImageUtil.generateErrorEgg(response, EggImageUtil.SMALL_WIDTH, EggImageUtil.SMALL_HEIGHT, Color.BLACK);
//    }
//    /**
//     * The Error Egg for ParameterNotIncludedException
//     * @param response the HttpServletResponse to be loaded with an Egg jpg
//     */
//    @ExceptionHandler(ParameterNotIncludedException.class)
//    public void generateImageParameterNotIncludedException(HttpServletResponse response) throws IOException{
//        EggImageUtil.generateErrorEgg(response, EggImageUtil.SMALL_WIDTH, EggImageUtil.SMALL_HEIGHT, Color.BLUE);
//    }
//    /**
//     * The Error Egg for IOException
//     * @param response the HttpServletResponse to be loaded with an Egg jpg
//     */
//    @ExceptionHandler(IOException.class)
//    public void generateImageIOException(HttpServletResponse response) throws IOException{
//        EggImageUtil.generateErrorEgg(response, EggImageUtil.SMALL_WIDTH, EggImageUtil.SMALL_HEIGHT, Color.RED);
//    }
}