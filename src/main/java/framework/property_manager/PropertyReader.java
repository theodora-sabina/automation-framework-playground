package framework.property_manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyReader {

    private static final Logger LOG = LogManager.getLogger(PropertyReader.class);
    private static final HashMap<String, String> CONFIG_MAP = new HashMap<>();
    private static final String CONFIG_FILE = "selenium.properties";

    private PropertyReader() {
    }

    static {

        InputStream inputStream = null;
        Properties prop = new Properties();

        try {
            inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        } catch (Exception e) {
            LOG.error("Properties file was not found at the specified path", e);
        }

        try {
            prop.load(inputStream);
        } catch (IOException e) {
            LOG.error("File was not loaded", e);
        }

        for (Map.Entry<Object, Object> currentEntry : prop.entrySet()) {
            CONFIG_MAP.put(String.valueOf(currentEntry.getKey()), String.valueOf(currentEntry.getValue()));
        }
    }

    public static String getProperty(String key) {

        String value = CONFIG_MAP.get(key);
        if (Objects.isNull(key) || Objects.isNull(value)) {
            try {
                throw new NullPointerException();
            } catch (NullPointerException n) {
                LOG.error("Invalid key: {} was specified",key, n);
            }
        }
        return value;
    }
}
