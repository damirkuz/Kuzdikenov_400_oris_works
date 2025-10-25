package ru.kuzdikenov.util;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", "dn9h4iaa1");
            config.put("api_key", "951245256812959");
            config.put("api_secret", "cUXcY0WprK7BAix0yKUPqVwIuvw");
            cloudinary = new Cloudinary(config);
        }

        return cloudinary;
    }
}
