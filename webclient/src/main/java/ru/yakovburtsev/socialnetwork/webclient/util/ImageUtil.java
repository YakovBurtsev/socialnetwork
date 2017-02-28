package ru.yakovburtsev.socialnetwork.webclient.util;

import org.springframework.web.multipart.MultipartFile;
import ru.yakovburtsev.socialnetwork.webclient.util.exception.ImageUploadException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class ImageUtil {

    private ImageUtil() {
    }

    public static void validateImage(MultipartFile image) {
        if(!"image/jpeg".equals(image.getContentType())) {
            throw new ImageUploadException("Only JPG images accepted");
        }
    }

    public static void saveImage(String filename, MultipartFile image) {
        try {
            File file = new File(filename);
            Files.write(file.toPath(), image.getBytes());
        } catch (IOException e) {
            throw new ImageUploadException("Unable to save image", e);
        }
    }
}
