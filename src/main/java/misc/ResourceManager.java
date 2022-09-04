package misc;

import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public final class ResourceManager {

    private static final String RESOURCE_DIR = "";

    private static List<File> allSounds;
    private static int next = 0;

    private ResourceManager(){}

    private static Properties properties;

    public static void init(){
        try {
            // ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // InputStream inputStream = classLoader.getResourceAsStream("config.properties");
            InputStream inputStream = new FileInputStream("config.properties");
            properties = new Properties();
            properties.load(inputStream);

            //init all sounds
            ResourceManager.allSounds = new ArrayList<>();
            File folder = new File(RESOURCE_DIR + "sounds/jeremy/english/");
            File[] listOfFiles = folder.listFiles();
            File folder2 = new File(RESOURCE_DIR + "sounds/jeremy/german/");
            File[] listOfFiles2 = folder2.listFiles();
            Collections.addAll(allSounds,listOfFiles);
            Collections.addAll(allSounds,listOfFiles2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(@NonNls String key){
        return properties.getProperty(key);
    }

    public static String randomSound(){
        return allSounds.get((next++)%allSounds.size()).getAbsolutePath();
    }

}
