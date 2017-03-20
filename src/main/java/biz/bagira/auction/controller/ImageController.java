package biz.bagira.auction.controller;

import biz.bagira.auction.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * Created by Dmitriy on 28.02.2017.
 */

@Controller
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    ImageUtil imageUtil;


    @RequestMapping(value = "/image/{userId}/{imageId}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] dispalayImage(@PathVariable Integer userId, @PathVariable String imageId) {

        String fullPath = imageUtil.getRootFolder()+ File.separator+userId+File.separator+imageId+".jpg";
        logger.info("ImageController fullPath = "+fullPath);
        return imageUtil.downloadPicture(fullPath);
    }


    @RequestMapping(value = "/show/{folder}/{pic}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] dispalayUserAvatar(@PathVariable String folder, @PathVariable String pic) {

        String fullPath = imageUtil.getRootFolder()+ File.separator+folder+File.separator+pic+".jpg";
        logger.info("ImageController fullPath = "+fullPath);
        return imageUtil.downloadPicture(fullPath);
    }

}
