package com.playwright.core;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties props = new Properties();

    static {
        try {
            InputStream is = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (is == null) {
                throw new RuntimeException("config.properties bulunamadı! Resources root kontrol et.");
            }

            props.load(is);

            System.out.println("Config yüklendi. baseUrl = " + props.getProperty("baseUrl"));

        } catch (Exception e) {
            throw new RuntimeException("Config okunamadı!", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}