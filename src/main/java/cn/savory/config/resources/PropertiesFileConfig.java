package cn.savory.config.resources;

import cn.savory.config.BasicConfig;
import com.google.common.io.Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 一个BasicConfig相当于一个配置文件
 */
public class PropertiesFileConfig extends BasicConfig {

    private String filePath;
    private Properties properties = null;

    /**
     * 从资源文件(即resources文件夹)中获取一组配置
     *
     * @param filePath 资源文件的名称
     */
    public PropertiesFileConfig(String filePath) {
        if (filePath.startsWith("/")) {
            this.filePath = filePath;
            return;
        }

        this.filePath = "/" + filePath;
    }

    @Override
    public String getProperty(String key) throws Exception {

        if (properties == null) {
            synchronized (this) {
                if (properties == null) {

                    URL url = Resources.class.getResource(this.filePath);
                    if (url == null) {
                        throw new FileNotFoundException(this.filePath + "is not found");
                    }
                    File file = new File(url.getFile());
                    InputStream inputStream = new FileInputStream(file);

                    properties = new Properties();
                    properties.load(inputStream);
                }
            }
        }

        return (String) properties.get(key);
    }
}
