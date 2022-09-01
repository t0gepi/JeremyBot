package commands;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PlayCommand extends Command{

    public PlayCommand(String name) {
        super(name);
    }

    @Override
    public void handle(MessageReceivedEvent event, String... params) {
        final TextChannel textChannel = event.getChannel().asTextChannel();
        final Member self = event.getGuild().getMember(event.getJDA().getSelfUser());
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if(!selfVoiceState.inAudioChannel()){
            textChannel.sendMessage("Bot needs to be in voice channel for this command");
            return;
        }
        final Member member = event.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        if(!memberVoiceState.inAudioChannel()){
            textChannel.sendMessage("You need to be in voice channel for this command");
            return;
        }


    }
}
