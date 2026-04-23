package utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip clip;

    // Method to play an audio file in a loop
    public void playLoop( String filePath ) {
        stop(); // Stop current music if running

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream( new File( filePath ) );
            clip = AudioSystem.getClip();
            clip.open( audioStream );
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch ( UnsupportedAudioFileException | IOException | LineUnavailableException e ) {
            e.printStackTrace();
        }
    }

    // Method to stop audio playback
    public void stop() {
        if ( clip != null && clip.isRunning() ) {
            clip.stop();
            clip.close();
        }
    }
}