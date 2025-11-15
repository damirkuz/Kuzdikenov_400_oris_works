package ru.kuzdikenov.util;

import com.cloudinary.Cloudinary;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;

@MultipartConfig
public class FormParser {

    public static final String FILE_PREFIX = "/Users/damirkuzdikenov/IdeaProjects/University/2 course/ORIS/Works/2025-10-11-ajax/src/main/webapp/savedImages";

    public static final int DIRECTORIES_COUNT = 100;

    private static final Cloudinary cloudinary = CloudinaryUtil.getInstance();

    public static String getStringParameter(HttpServletRequest req, String parameterName) throws ServletException, IOException {
        Part part = req.getPart(parameterName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder value = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            value.append(line);
        }
        String param = value.toString();
        return param;
    }

    public static String parseAndSaveFile(HttpServletRequest req, String parameterName) throws ServletException, IOException {
        Part part = req.getPart(parameterName);
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

        StringBuilder sb = new StringBuilder();
        sb.append(FILE_PREFIX);
        sb.append(File.separator);
        sb.append(String.valueOf(Math.abs( filename.hashCode() % DIRECTORIES_COUNT)));
        sb.append(File.separator);
        sb.append(filename);
        String path = sb.toString();

        File file = new File(path);

        InputStream content = part.getInputStream();

        file.getParentFile().mkdirs();
        file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[content.available()];
        content.read(buffer);
        outputStream.write(buffer);
        outputStream.close();
        //        return shortenPath(path);
        String res = cloudinary.uploader().upload(file, new HashMap<>()).get("url").toString();
        file.delete();
        return res;
    }

    private static String shortenPath(String originalPath) {
        String marker = "/savedImages/";
        int index = originalPath.indexOf(marker);
        if (index != -1) {
            return originalPath.substring(index);
        }
        return originalPath;
    }
}
