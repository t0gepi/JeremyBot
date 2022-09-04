package command.misc;

import command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.concurrent.TimeUnit;

public class ClearCommand extends Command {

    public ClearCommand(String name) {
        super(name);
    }

    @Override
    public void handle(MessageReceivedEvent event, String... params) {

        if(params.length != 0){
            try{
                String amount = Integer.valueOf(params[0]).toString();
                event.getChannel().asTextChannel().getHistory()
                        .retrievePast(Integer.parseInt(amount) + 1).complete()
                        .forEach(message -> message.delete().queue());

                event.getChannel().asTextChannel().sendMessageFormat("Deleted %s Messages", amount)
                        .queue(msg -> msg.delete().queueAfter(5, TimeUnit.SECONDS));
            }
            catch (NumberFormatException e){
                event.getChannel().asTextChannel().sendMessage("Type !clear <amount>").queue();
            }
        }
        else{
            event.getChannel().asTextChannel().createCopy().queue();
            event.getChannel().asTextChannel().delete().queue();
        }
    }

}
