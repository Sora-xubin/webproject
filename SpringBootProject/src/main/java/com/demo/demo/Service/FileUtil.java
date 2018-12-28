package com.demo.demo.Service;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by XB on 2018/12/28.
 */
public class FileUtil {
    public static void uploadFile(byte[] file,String filePath,String fileName) throws Exception{
        File targetFile = new File(filePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(filePath+fileName);
        outputStream.write(file);
        outputStream.flush();
        outputStream.close();
    }
}
