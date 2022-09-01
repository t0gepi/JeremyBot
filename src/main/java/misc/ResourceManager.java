package misc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.jetbrains.annotations.NonNls;

public final class ResourceManager {
    private ResourceManager(){}

    private static Properties properties;

    public static void init(){
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("config.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(@NonNls String key){
        return properties.getProperty(key);
    }


}
