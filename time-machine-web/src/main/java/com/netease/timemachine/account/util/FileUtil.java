package com.netease.timemachine.account.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 23:59 2018/7/19
 **/
public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
