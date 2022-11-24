package com.yaroshevich.app.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {

    public static Properties properties = new Properties();

    public PropertiesHelper() {
        File propsFile = new File("app.properties");

        try {
            properties.load(new FileReader(propsFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
