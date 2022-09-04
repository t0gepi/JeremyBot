package command.jeremy;

import command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class KickCommand extends Command {


    public KickCommand(String name) {
        super(name);
    }

    @Override
    public void handle(MessageReceivedEvent event, String... params) {

    }
}
