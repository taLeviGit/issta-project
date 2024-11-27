package issta.co.il;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public String readProperty(String key) {

        String value;
        try (InputStream input = new FileInputStream("./src/test/resources/configuration.properties")) {
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            value = prop.getProperty(key);

        } catch (Exception e) {
            throw new RuntimeException("Can not find configuration.properties");
        }
        return value;
    }
}
