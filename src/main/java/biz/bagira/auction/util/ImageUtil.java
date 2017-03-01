package biz.bagira.auction.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dmitriy on 22.02.2017.
 */

public class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    private final String ROOT_FOLDER;


    public ImageUtil(final String rootFolder) {
        this.ROOT_FOLDER = rootFolder;
        createStorageFolder(ROOT_FOLDER);
    }

    public static boolean isValidString(String s) {
        return s != null || !s.trim().equals("");
    }


    private void createStorageFolder(String path) {
        File file = new File(ROOT_FOLDER);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }

    private  String createUserFolder(Integer userId) {
        String userFolder = ROOT_FOLDER + File.separator + String.valueOf(userId);
        File file = new File(userFolder);
        System.out.println("File exist = " + file.exists());
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("User directory is created!");

            } else {
                System.out.println("Failed to create user directory!");

            }
        }
        return file.getAbsolutePath();
    }


    public  String saveAvatar(Integer userId, byte[] resByteArray) {
        createUserFolder(userId);
        String pathToUserDir = ROOT_FOLDER + File.separator + userId;
        String fileName = userId + "_avat.jpg";
        writeImage(resByteArray, fileName, pathToUserDir);
        return pathToUserDir + File.separator + fileName;
    }

    public  byte[] downloadPicture(String directory, String fileName) {

        byte[] resByteArray = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(1000)) {
            BufferedImage bufferedImage = ImageIO.read(new File(directory, fileName));
            ImageIO.write(bufferedImage, "jpg", baos);
            baos.flush();
            resByteArray = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resByteArray;
    }

    private  boolean writeImage(byte[] resByteArray, String fileName, String path) {
        if (!fileName.endsWith(".jpg")) {
            fileName += ".jpg";
        }
        try (ByteArrayInputStream bais = new ByteArrayInputStream(resByteArray)) {
            BufferedImage resultImage = ImageIO.read(bais);
            return ImageIO.write(resultImage, "jpg", new File(path, fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public  void folderDelete(File file) {
        if (!file.exists())
            return;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                folderDelete(f);
            }
        }
        file.delete();
        logger.info("Successfully delete user folder: " + file.getAbsolutePath());
    }

    public  boolean deliteImage(String pathToImage) {
        File file = new File(pathToImage);
        if (!file.exists()) {
            logger.info(pathToImage + " does not exist");
            return false;
        } else {
            file.delete();
            logger.info(pathToImage + " successfully deleted");
            return true;
        }

    }

    public  byte[] downloadPicture(String fullPath) {
        int i = fullPath.lastIndexOf('\\');
        String directory = fullPath.substring(0, i + 1);
        String fileName = fullPath.substring(i + 1, fullPath.length());

        return downloadPicture(directory,fileName);
    }


    public  String getRootFolder() {
        return ROOT_FOLDER;
    }
}
