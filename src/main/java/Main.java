import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.Properties;

public class Main {

    public static JDA jda;

    public static String prefix = "/";

    public static Properties properties;

    public static void main(String[] args) throws LoginException {


        try {
            InputStream inputStream = new FileInputStream("auth.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jda = JDABuilder.createDefault(properties.getProperty("discord.applicationid")).build();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setActivity(Activity.playing("IntelliJ IDEA Ultimate"));

        jda.addEventListener(new Commands());

    }
}
