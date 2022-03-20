package Map;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[20];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/music.wav");
        soundURL[1] = getClass().getResource("/sound/regHeart.wav");
        soundURL[2] = getClass().getResource("/sound/bigHeart.wav");
        soundURL[3] = getClass().getResource("/sound/enter.wav");
        soundURL[4] = getClass().getResource("/sound/start.wav");
        soundURL[5] = getClass().getResource("/sound/lost.wav");
        soundURL[6] = getClass().getResource("/sound/zombieAttack.wav");
        soundURL[7] = getClass().getResource("/sound/random_1.wav");
        soundURL[8] = getClass().getResource("/sound/random_2.wav");
        soundURL[9] = getClass().getResource("/sound/random_3.wav");
        soundURL[10] = getClass().getResource("/sound/random_4.wav");
        soundURL[11] = getClass().getResource("/sound/random_5.wav");
        soundURL[12] = getClass().getResource("/sound/random_6.wav");
        soundURL[13] = getClass().getResource("/sound/hurt.wav");
    }

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

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
