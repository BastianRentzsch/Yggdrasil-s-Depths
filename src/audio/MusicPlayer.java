// Copyright (c) 2026 Bastian Rentzsch

package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Utility class responsible for playing and managing background music.
 * <p>
 * This class uses Java Sound API to load and loop audio clips for different
 * game states such as combat, exploration, and menu screens. Only one track
 * is played at a time; starting a new track automatically stops the current one.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class MusicPlayer {
	
	/**
	 * The currently active audio clip.
	 */
	private Clip clip;

	/**
	 * Creates a new MusicPlayer instance.
	 * <p>
	 * The player is initially idle until a track is started.
	 * </p>
	 */
	public MusicPlayer() {
	}
	
	/**
	 * Plays an audio file in a continuous loop.
	 * <p>
	 * If another track is already playing, it will be stopped first.
	 * </p>
	 *
	 * @param filePath the path to the audio file to be played
	 */
    private void playLoop(String filePath) {
        this.stop();

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            this.clip = AudioSystem.getClip();
            this.clip.open(audioStream);
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the currently playing audio clip and releases system resources.
     */
    private void stop() {
        if (this.clip != null && this.clip.isRunning()) {
        	this.clip.stop();
        	this.clip.close();
        }
    }
    
    /**
     * Plays the combat background music.
     *
     * @param musicPlayer the MusicPlayer instance used for playback
     */
    public static void playCombat(MusicPlayer musicPlayer) {
    	musicPlayer.playLoop("./res/audio/combat.wav");
    }
    
    /**
     * Plays the exploration background music.
     *
     * @param musicPlayer the MusicPlayer instance used for playback
     */
    public static void playExploration(MusicPlayer musicPlayer) {
    	musicPlayer.playLoop("./res/audio/exploration.wav");
    }
    
    /**
     * Plays the main menu background music.
     *
     * @param musicPlayer the MusicPlayer instance used for playback
     */
    public static void playMainMenu(MusicPlayer musicPlayer) {
    	musicPlayer.playLoop("./res/audio/mainMenu.wav");
    }
    
    /**
     * Plays background music for secondary menu screens.
     *
     * @param musicPlayer the MusicPlayer instance used for playback
     */
    public static void playOtherMenus(MusicPlayer musicPlayer) {
    	musicPlayer.playLoop("./res/audio/otherMenus.wav");
    }
}