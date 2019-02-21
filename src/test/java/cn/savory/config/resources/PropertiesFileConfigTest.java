package cn.savory.config.resources;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by hc_zhang on 2017/12/20.
 */
public class PropertiesFileConfigTest {

    @Test
    public void getProperty() throws Exception {

        PropertiesFileConfig config = new PropertiesFileConfig("basic.properties");

        {
            String expected = "tom";
            String actual = config.getProperty("name");
            Assert.assertEquals(expected, actual);
        }

        {
            int expected = 18;
            int actual = config.getIntProperty("age", 17);
            Assert.assertEquals(expected, actual);
        }

        {
            boolean expected = true;
            boolean actual = config.getBooleanProperty("isboy", false);
            Assert.assertEquals(expected, actual);
        }

        {
            String[] expected = new String[]{"math", "english", "chinese"};
            String[] actual = config.getArrayProperty("subject", ",", null);
            Assert.assertEquals(expected.length, actual.length);
            for (int i = 0; i < expected.length; i++) {
                Assert.assertEquals(expected[i], actual[i]);
            }
        }
    }


}