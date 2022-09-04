package command.misc;

import command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand extends Command {


    public HelpCommand(String name) {
        super(name);
    }

    @Override
    public void handle(MessageReceivedEvent event, String... params) {

    }

}
