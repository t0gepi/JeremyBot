package main;

import commands.CommandManager;
import commands.InfoCommand;
import javax.security.auth.login.LoginException;
import misc.ResourceManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Main {
    public static JDA jda;

    public static void main(String[] args) throws LoginException {
        ResourceManager.init();
        jda = JDABuilder.createDefault(ResourceManager.getProperty("discord.bottoken")).build();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setActivity(Activity.playing("Sleeping"));
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommandManager commandManager = new CommandManager();
        commandManager.addCommand(new InfoCommand("info"));
        jda.addEventListener(commandManager);
    }
}
