package com.revature.eggheads.backendp2.service;

import com.revature.eggheads.backendp2.exception.ParameterNotIncludedException;
import com.revature.eggheads.backendp2.exception.ParameterOutOfBoundsException;
import com.revature.eggheads.backendp2.util.EggImageUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 * Contains the business logic for loading an egg image into an Http Response
 */
@Service
public class EggImageService {

    public void generateImage(String imgHeight, String imgWidth, String size, String red, String green, String blue, HttpServletResponse response) throws IOException {
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

            //Return an error egg image depending on the Exception
        } catch (ParameterNotIncludedException e){
            bufferedImage = EggImageUtil.generateErrorEgg(EggImageUtil.SMALL_WIDTH, EggImageUtil.SMALL_HEIGHT, Color.BLACK);
            e.printStackTrace();
        } catch (ParameterOutOfBoundsException e){
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
}
