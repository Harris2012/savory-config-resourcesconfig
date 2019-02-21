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
public class PropertiesFileConfig extends SavoryConfig {

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
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProperty(String key) throws Exception {

        return (String) properties.get(key);
    }
}
