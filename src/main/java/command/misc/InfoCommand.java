package command.misc;

import command.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;

public class InfoCommand extends Command {

    public InfoCommand(String name) {
        super(name);
    }

    @Override
    public void handle(MessageReceivedEvent event, String... params) {
        TextChannel channel = event.getChannel().asTextChannel();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setImage("attachment://jeremy.png") // we specify this in sendFile as "cat.png"
                .setDescription("This is a cute cat :3");
        channel.sendFiles(FileUpload.fromData(new File("jeremy.png"), "jeremy.png"))
                .setEmbeds(embed.build()).queue();
    }


}
