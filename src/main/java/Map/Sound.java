package Map;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Holds all sound functions for the project.
 */
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[20];

    public Sound() {
        String soundPath[] = {
            "/sound/music.wav",
            "/sound/regHeart.wav",
            "/sound/bigHeart.wav",
            "/sound/enter.wav",
            "/sound/start.wav",
            "/sound/lost.wav",
            "/sound/zombieAttack.wav",
            "/sound/random_1.wav",
            "/sound/random_2.wav",
            "/sound/random_3.wav",
            "/sound/random_4.wav",
            "/sound/random_5.wav",
            "/sound/random_6.wav",
            "/sound/hurt.wav"
        };
        for (int i = 0; i < soundPath.length; i++){
            soundURL[i] = getClass().getResource(soundPath[i]);
        }
    }

    /**
     * Sets the sound file.
     */
    public void setFile(int i) {
        try{
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the sound file.
     */
    public void play() {
        clip.start();
    }

    /**
     * Loops the sound file.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the sound file.
     */
    public void stop() {
        clip.stop();
    }

    public void playSound(int i) {
        this.setFile(i);
        this.play();
    }

    /**
     * Plays music
     */
    public void playMusic(int i) {
        this.setFile(i);
        this.play();
        this.loop();
    }
}
