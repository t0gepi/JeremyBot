package lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler extends AudioEventAdapter {

    private final AudioPlayer audioPlayer;
    private final BlockingQueue<AudioTrack> queue;

    public TrackScheduler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void queue(AudioTrack audioTrack){
        if(!this.audioPlayer.startTrack(audioTrack, true)){
            this.queue.offer(audioTrack);
        }
    }

    public void nextTrack(){
        this.audioPlayer.startTrack(this.queue.poll(), false);
    }

    public void stop(){
        this.audioPlayer.stopTrack();
    }

    public boolean isPlaying(){
        return this.audioPlayer.getPlayingTrack() != null;
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if(endReason.mayStartNext){
            nextTrack();
        }
    }

    public void pause() {
        this.audioPlayer.setPaused(true);

    }

    public boolean isPaused(){
        return this.audioPlayer.isPaused();
    }

    public void resume() {
        this.audioPlayer.setPaused(false);
    }
}
