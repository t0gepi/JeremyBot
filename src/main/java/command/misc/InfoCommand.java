package command.misc;

import command.Command;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;

import java.io.File;

public class InfoCommand extends Command {

    public InfoCommand(String name) {
        super(name);
    }

    @Override
    public void handle(MessageReceivedEvent event, String... params) {
        TextChannel channel = event.getChannel().asTextChannel();
        MessageCreateBuilder builder = new MessageCreateBuilder();
        builder.addFiles(FileUpload.fromData(new File("jeremy.png")));
        channel.sendMessage(builder.build()).queue();
    }


}
