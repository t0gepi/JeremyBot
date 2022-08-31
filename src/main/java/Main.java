import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {

    public static JDA jda;

    public static String prefix = "/";

    public static void main(String[] args) throws LoginException {
        jda = JDABuilder.createDefault("MTAxNDI1NTc1NDE4MzcxMjkwOQ.GrqPJQ.e2O_nilq0Pc3erVeIPibLY558BCFIyLWEnanss").build();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setActivity(Activity.playing("IntelliJ IDEA Ultimate"));

        jda.addEventListener(new Commands());

    }
}
