package biz.bagira.auction.util;

import org.junit.Test;
import org.testng.Assert;

import java.io.File;

/**
 * Created by Dmitriy on 19.03.2017.
 */
public class ImageUtilTest {
    @Test
    public void isValidString() throws Exception {
        String s = null;
        Assert.assertFalse(ImageUtil.isValidString(s));
        Assert.assertFalse(ImageUtil.isValidString(""));
        String s1 = "qwerty";
        Assert.assertTrue(ImageUtil.isValidString(s1));
    }

    @Test
    public void getRootFolder() throws Exception {
        String rootFolder = "C:\\test_folder";
       ImageUtil imageUtil = new ImageUtil(rootFolder);
        Assert.assertEquals(imageUtil.getRootFolder(),rootFolder);
    }

    @Test
    public void deleteFolder(){
        String rootFolder = "C:\\test_folder";
        //create folder
        ImageUtil imageUtil = new ImageUtil(rootFolder);

        File file = new File(rootFolder);
        Assert.assertTrue(file.exists());
        imageUtil.folderDelete(file);
        Assert.assertFalse(file.exists());


    }

}