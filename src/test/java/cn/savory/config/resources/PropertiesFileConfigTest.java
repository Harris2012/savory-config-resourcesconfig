package cn.savory.config.resources;

import cn.savory.config.ISavoryMapConfig;
import cn.savory.config.SavoryConfigService;
import cn.savory.config.SavoryConfigServiceFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by hc_zhang on 2017/12/20.
 */
public class PropertiesFileConfigTest {

    @Test
    public void getProperty() throws Exception {

        SavoryConfigService savoryConfigService = SavoryConfigServiceFactory.getSavoryConfigService(new ResourceContentLoader());

        ISavoryMapConfig mapConfig = savoryConfigService.getMapConfig("basic.properties");

        {
            String expected = "tom";
            String actual = mapConfig.getString("name");
            Assert.assertEquals(expected, actual);
        }

        {
            int expected = 18;
            int actual = mapConfig.getIntProperty("age", 17);
            Assert.assertEquals(expected, actual);
        }

        {
            boolean expected = true;
            boolean actual = mapConfig.getBooleanProperty("isboy", false);
            Assert.assertEquals(expected, actual);
        }
    }
}