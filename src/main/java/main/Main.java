package main;

import command.jeremy.TalkCommand;
import command.misc.ClearCommand;
import command.CommandManager;
import command.misc.InfoCommand;
import command.music.JoinCommand;
import command.music.PauseCommand;
import command.music.PlayCommand;
import command.music.ResumeCommand;
import command.music.SkipCommand;
import command.music.StopCommand;
import javax.security.auth.login.LoginException;
import misc.ResourceManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static JDA jda;
    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws LoginException {
        ResourceManager.init();
        jda = JDABuilder.createDefault(ResourceManager.getProperty("discord.bottoken"))
                .enableIntents(GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.MESSAGE_CONTENT)
                .enableCache(CacheFlag.VOICE_STATE).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("Sleeping"));
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommandManager commandManager = new CommandManager();
        commandManager.addCommand(new InfoCommand("info"));
        commandManager.addCommand(new PlayCommand("play"));
        commandManager.addCommand(new JoinCommand("join"));
        commandManager.addCommand(new StopCommand("stop"));
        commandManager.addCommand(new PauseCommand("pause"));
        commandManager.addCommand(new ResumeCommand("resume"));
        commandManager.addCommand(new SkipCommand("skip"));
        commandManager.addCommand(new ClearCommand("clear"));
        commandManager.addCommand(new TalkCommand("talk"));
        jda.addEventListener(commandManager);
        LOGGER.info("{}: everything loaded", Main.class.getSimpleName());
    }
}
