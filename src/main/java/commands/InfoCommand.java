package commands;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class InfoCommand extends Command {


    public InfoCommand(String name) {
        super(name);
    }

    @Override
    public void handle(MessageReceivedEvent event, String... args) {
        if(args[0].equals(Main.prefix + "info")){
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("This is my title");
            builder.setDescription("This is a test description" );

            builder.setFooter("Created by t0gepi");
            builder.setColor(0xf45642);


            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessageEmbeds(builder.build()).queue();
        }
    }
}
