package com.yaroshevich.app.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {

    public static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertiesHelper.class.getResourceAsStream("/app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
