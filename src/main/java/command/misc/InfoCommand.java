package command.misc;

import command.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class InfoCommand extends Command {

    public InfoCommand(String name) {
        super(name);
    }

    @Override
    public void handle(MessageReceivedEvent event, String... params) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Jeremy Fragrance");
        builder.setDescription("Businessman with 800k earnings per month\n" +
                "What's your favorite fragrance man?" );
        builder.setImage("attachment://jeremy.png");
        builder.setFooter("Created by Kalli");
        builder.setColor(0xf45642);
        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessageEmbeds(builder.build()).queue();
    }


}
