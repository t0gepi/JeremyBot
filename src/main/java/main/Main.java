package main;

import commands.ClearCommand;
import commands.CommandManager;
import commands.InfoCommand;
import commands.JoinCommand;
import commands.PauseCommand;
import commands.PlayCommand;
import commands.ResumeCommand;
import commands.SkipCommand;
import commands.StopCommand;
import javax.security.auth.login.LoginException;
import misc.ResourceManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Main {
    public static JDA jda;

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
        jda.addEventListener(commandManager);
    }
}
