package cn.savory.config.resources;

import cn.savory.config.IContentLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resources;
import java.io.*;
import java.net.URL;

public class ResourceContentLoader implements IContentLoader<String> {

    private static Logger logger = LoggerFactory.getLogger(ResourceContentLoader.class);

    @Override
    public String loadText(String fileName) {
        if (!fileName.startsWith("/")) {
            fileName = "/" + fileName;
        }

        URL url = Resources.class.getResource(fileName);
        if (url == null) {
            logger.error("{} is not found", fileName);
        }

        try {
            StringBuffer buffer = new StringBuffer();

            File file = new File(url.getFile());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            return buffer.toString();
        } catch (FileNotFoundException e) {

            logger.error("loadText", e);
            return null;
        } catch (IOException e) {

            logger.error("loadText", e);
            return null;
        }
    }
}
