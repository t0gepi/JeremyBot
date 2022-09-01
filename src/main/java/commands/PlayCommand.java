package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class PlayCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if(!event.getMessage().getContentRaw().split(" ")[0].equals("/play")){
            return;
        }

        if(event.getMember() != null){
            if(!event.getMember().getVoiceState().inAudioChannel()){
                event.getChannel().sendMessage("You need to be in a voice channel for this command").queue();
                return;
            }
            else{

            }

        }
    }
}
