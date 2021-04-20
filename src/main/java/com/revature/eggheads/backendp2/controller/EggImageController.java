package com.revature.eggheads.backendp2.controller;

import com.revature.eggheads.backendp2.service.EggImageService;
import com.revature.eggheads.backendp2.util.EggImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Example Sources
 * <p>img src='image/egg?height=30&width=50&size=50&red=12&green=20&blue=130'</p>
 * <p>img src='image/egg?height=60&width=500&size=30&red=24&green=40&blue=255'</p>
 */
@RestController
@RequestMapping("image/egg")
@CrossOrigin(origins = {"http://localhost:4200", "http://egghead-p2-angular.s3-website.us-east-2.amazonaws.com"})
public class EggImageController {

    private EggImageService eggImageService;

    @Autowired
    public EggImageController(EggImageService eggImageService){
        this.eggImageService = eggImageService;
    }

    /**
     * All Parameters are read from the request and parsed into Strings
     * @param imgHeight Image Height in pixels, defaults to EggImageUtil.LARGE for scaling down
     * @param imgWidth Image Width in pixels, defaults to EggImageUtil.LARGE for scaling down
     * @param size 0-100
     * @param red 0-255
     * @param green 0-255
     * @param blue 0-255
     * @param response the httpResponse that the image will be written into
     * @throws IOException
     * @throws org.springframework.data.repository.query.ParameterOutOfBoundsException
     */
    @GetMapping
    public void image(@RequestParam(name = "height", defaultValue = EggImageUtil.LARGE_HEIGHT) String imgHeight,
                              @RequestParam(name = "width", defaultValue = EggImageUtil.LARGE_WIDTH) String imgWidth,
                              @RequestParam(name = "size", required = false) String size,
                              @RequestParam(name = "red", required = false) String red,
                              @RequestParam(name = "green", required = false) String green,
                              @RequestParam(name = "blue", required = false) String blue,
                              HttpServletResponse response) throws IOException {
        eggImageService.generateImage(imgHeight, imgWidth, size, red, green, blue, response);
    }
    @GetMapping("/medium")
    public void mediumImage(@RequestParam(name = "size", required = false) String size,
                           @RequestParam(name = "red", required = false) String red,
                           @RequestParam(name = "green", required = false) String green,
                           @RequestParam(name = "blue", required = false) String blue,
                           HttpServletResponse response) throws IOException {
        eggImageService.generateImage(EggImageUtil.MEDIUM_HEIGHT, EggImageUtil.MEDIUM_WIDTH, size, red, green, blue, response);
    }
    @GetMapping("/small")
    public void smallImage(@RequestParam(name = "size", required = false) String size,
                            @RequestParam(name = "red", required = false) String red,
                            @RequestParam(name = "green", required = false) String green,
                            @RequestParam(name = "blue", required = false) String blue,
                            HttpServletResponse response) throws IOException {
        eggImageService.generateImage(EggImageUtil.SMALL_HEIGHT, EggImageUtil.SMALL_WIDTH, size, red, green, blue, response);
    }
    @GetMapping("/large")
    public void largeImage(@RequestParam(name = "size", required = false) String size,
                           @RequestParam(name = "red", required = false) String red,
                           @RequestParam(name = "green", required = false) String green,
                           @RequestParam(name = "blue", required = false) String blue,
                           HttpServletResponse response) throws IOException {
        eggImageService.generateImage(EggImageUtil.LARGE_HEIGHT, EggImageUtil.LARGE_WIDTH, size, red, green, blue, response);
    }
}