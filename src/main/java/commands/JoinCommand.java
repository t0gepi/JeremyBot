package commands;

import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.managers.channel.middleman.AudioChannelManager;

public class JoinCommand extends Command{

    public JoinCommand(String name) {
        super(name);
    }

    @Override
    public void handle(MessageReceivedEvent event, String... params) {
        final TextChannel textChannel = event.getChannel().asTextChannel();
        final Member self = event.getGuild().getMember(event.getJDA().getSelfUser());
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if(selfVoiceState.inAudioChannel()){
            textChannel.sendMessage("I'm already in a voice channel").queue();
            return;
        }

        final Member member = event.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inAudioChannel()){
            textChannel.sendMessage("You need to be in a voice channel for this command");
            return;
        }

        final AudioManager audioManager = event.getGuild().getAudioManager();
        final AudioChannel audioChannel = memberVoiceState.getChannel();
        audioManager.openAudioConnection(audioChannel);
        textChannel.sendMessageFormat("Connecting to `\uD83D\uDD0A %s`", audioChannel.getName()).queue();

    }
}
