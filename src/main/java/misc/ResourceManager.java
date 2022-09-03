package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import org.jetbrains.annotations.NonNls;

public final class ResourceManager {

    private static final String RESOURCE_DIR = "src/main/resources/";

    private ResourceManager(){}

    private static Properties properties;

    public static void init(){
        try {
            // ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // InputStream inputStream = classLoader.getResourceAsStream("config.properties");
            InputStream inputStream = new FileInputStream(RESOURCE_DIR + "config.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(@NonNls String key){
        return properties.getProperty(key);
    }

    public static String randomSound(){
        File folder = new File(RESOURCE_DIR + "/sounds/");
        File[] listOfFiles = folder.listFiles();
        return listOfFiles[new Random().nextInt(listOfFiles.length)].getAbsolutePath();
    }

}
