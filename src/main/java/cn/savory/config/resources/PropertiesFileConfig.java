package cn.savory.config.resources;

import cn.savory.config.SavoryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resources;
import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * 从资源文件(即resources文件夹)中获取配置
 */
public class PropertiesFileConfig implements SavoryConfig {

    private String filePath;
    private Properties properties = null;

    private Logger logger = LoggerFactory.getLogger(PropertiesFileConfig.class);

    /**
     * 从资源文件(即resources文件夹)中获取配置
     */
    public PropertiesFileConfig(String filePath) {
        if (filePath.startsWith("/")) {
            this.filePath = filePath;
            return;
        }

        this.filePath = "/" + filePath;

        URL url = Resources.class.getResource(this.filePath);
        if (url == null) {
            logger.error("{} is not found", this.filePath);
        }

        File file = new File(url.getFile());
        try {
            InputStream inputStream = new FileInputStream(file);

            properties = new Properties();
            properties.load(inputStream);

        } catch (FileNotFoundException e) {
            logger.error("PropertiesFileConfig", e);
        } catch (IOException e) {
            logger.error("PropertiesFileConfig", e);
        }
    }

    @Override
    public String getProperty(String key) throws Exception {

        return (String) properties.get(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) throws Exception {
        return (String) properties.getOrDefault(key, defaultValue);
    }

    @Override
    public Integer getIntProperty(String key, Integer defaultValue) throws Exception {
        String value = properties.getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    @Override
    public Long getLongProperty(String key, Long defaultValue) throws Exception {
        String value = properties.getProperty(key);
        return value != null ? Long.parseLong(value) : defaultValue;
    }

    @Override
    public Short getShortProperty(String key, Short defaultValue) throws Exception {
        String value = properties.getProperty(key);
        return value != null ? Short.parseShort(value) : defaultValue;
    }

    @Override
    public Float getFloatProperty(String key, Float defaultValue) throws Exception {
        String value = properties.getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    @Override
    public Double getDoubleProperty(String key, Double defaultValue) throws Exception {
        String value = properties.getProperty(key);
        return value != null ? Double.parseDouble(value) : defaultValue;
    }

    @Override
    public Byte getByteProperty(String key, Byte defaultValue) throws Exception {
        String value = properties.getProperty(key);
        return value != null ? Byte.parseByte(value) : defaultValue;
    }

    @Override
    public Boolean getBooleanProperty(String key, Boolean defaultValue) throws Exception {
        String value = properties.getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    @Override
    public String[] getArrayProperty(String key, String spliter, String[] defaultValue) throws Exception {
        String value = properties.getProperty(key);
        if (value != null) {
            return value.split(spliter);
        }
        return defaultValue;
    }
}
