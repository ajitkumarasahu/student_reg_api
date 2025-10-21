package com.studentapi.util;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.Part;

public class FileUploadUtil {

    public static String saveFile(String uploadDir, Part filePart) throws IOException {
        String fileName = filePart.getSubmittedFileName();
        String savePath = uploadDir + File.separator + fileName;

        File fileSaveDir = new File(uploadDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        filePart.write(savePath);

        // return relative path to save in DB
        return "uploads/" + fileName;
    }
}
