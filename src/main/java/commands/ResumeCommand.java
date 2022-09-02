package commands;

import lavaplayer.PlayerManager;
import lavaplayer.TrackScheduler;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ResumeCommand extends Command{
    public ResumeCommand(String name) {
        super(name);
    }

    @Override
    public void handle(MessageReceivedEvent event, String... params) {
        final TextChannel textChannel = event.getChannel().asTextChannel();
        final Member self = event.getGuild().getMember(event.getJDA().getSelfUser());
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        if(selfVoiceState.inAudioChannel()){
            TrackScheduler scheduler = PlayerManager.getInstance()
                    .getMusicManager(event.getGuild())
                    .getScheduler();
            if(scheduler.isPaused()){
                scheduler.resume();
                textChannel.sendMessage("resuming").queue();
            }
            else{
                textChannel.sendMessage("I am not paused currently. Moron").queue();
            }
        }
    }
}
