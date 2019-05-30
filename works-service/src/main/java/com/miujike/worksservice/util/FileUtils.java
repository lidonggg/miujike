package com.miujike.worksservice.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author ykq
 * @date 2019-01-14
 */
public class FileUtils {

    public static byte[] readBytesFromFile(File file) {
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] bytes = new byte[bis.available()];
            bis.read(bytes);
            bis.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
