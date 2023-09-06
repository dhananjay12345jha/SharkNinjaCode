package com.salmon.test.framework.helpers;

import static java.lang.System.out;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Props {
    private static final Logger LOG = LoggerFactory.getLogger(Props.class);
    private static Properties environmentProps;
    private static Properties properties;
    private static Properties messages,devices;
    static {
        loadRunConfigProps("/environment.properties");
    }

    /**
     * Gets the key from en_messages.properties for a Site
     **/
    public static String getMessage(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return messages.getProperty(key);
        }
    }

    /**
     * Gets the key from devices
     **/
    public static String getdevice(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return devices.getProperty(key);
        }
    }

    /**
     * Gets the key from Config.properties related to chosen profile
     **/
    public static String getExecutionEnv() {
        return environmentProps.getProperty("profile.executionEnv");
    }

    public static String getEnvProperty(String propertyName) {
        return environmentProps.getProperty(propertyName);
    }

    public static String getProp(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return properties.getProperty(key);

        }

    }
    public static boolean setProp(String key,String value) {
        boolean flag=false;
        if ((key == null) || key.isEmpty()) {
        } else {
            properties.setProperty(key, value);
            flag=true;
        }
        return flag;

    }

    public static void loadRunConfigProps(String configPropertyFileLocation) {
        environmentProps = new Properties();
        try (InputStream inputStream = Props.class.getResourceAsStream(configPropertyFileLocation)) {
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            environmentProps.load(reader);
           // environmentProps.list(out);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        properties = new Properties();
        try (InputStream inputStream = Props.class
                .getResourceAsStream(environmentProps.getProperty("profile.path"))) {
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            properties.load(reader);
           // properties.list(out);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        //Read message file
        String messagesFile = environmentProps.getProperty("profile.msg");
        messages = new Properties();
        try (InputStream inputStream = Props.class.getResourceAsStream(messagesFile)) {
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            messages.load(reader);
            messages.list(out);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        //Read Device file
        String devicesFile = environmentProps.getProperty("profile.device");
        devices = new Properties();
        try (InputStream inputStream = Props.class.getResourceAsStream(devicesFile)) {
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            devices.load(reader);
            devices.list(out);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}

