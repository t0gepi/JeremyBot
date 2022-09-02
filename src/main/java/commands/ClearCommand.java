package commands;

import java.util.List;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ClearCommand extends Command{

    public ClearCommand(String name) {
        super(name);
    }

    @Override
    public void handle(MessageReceivedEvent event, String... params) {
        String amount = "all";
        if(params.length != 0){
            try{
                amount = Integer.valueOf(params[0]).toString();
            }
            catch (NumberFormatException e){
                event.getChannel().asTextChannel().sendMessage("Type !clear <amount>").queue();
            }
        }
        if(amount.equals("all")){
            event.getChannel().asTextChannel().createCopy().queue();
            event.getChannel().asTextChannel().delete().queue();
        }
        else{
            List<Message> messages = event.getChannel().asTextChannel().getHistory()
                    .retrievePast(Integer.parseInt(amount) + 1).complete();
            messages.forEach(message -> message.delete().queue());
        }
        event.getChannel().asTextChannel().sendMessageFormat("Deleted %s Messages", amount)
                .queue(msg -> msg.delete().queueAfter(5, TimeUnit.SECONDS));
    }
}
