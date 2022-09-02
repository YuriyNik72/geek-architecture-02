package ru.geekbrains.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    private final Properties properties;
    private final String propertyFileName;

    public ApplicationProperties(String propertyFileName) {
        this.propertyFileName = propertyFileName;
        this.properties = new Properties();
    }

    public ApplicationProperties readProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertyFileName)) {
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Ошибка чтения файла конфигурации", ex);
        }
        return this;
    }

    public Properties getProperties(){
        return properties;
    }

    public int getPort(){
        return Integer.parseInt(properties.getProperty("port"));
    }

    public String getRoot(){
        return properties.getProperty("root");
    }
}
