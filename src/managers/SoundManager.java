package managers;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {

    public static void playSound(String path) {

        try {

            File soundFile = new File(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception e) {

            System.out.println("Sound error: " + e.getMessage());
        }
    }

    public static void playSoundWithVolume(String path, float volume) {

        try {

            File soundFile = new File(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);

            clip.start();

        } catch (Exception e) {

            System.out.println("Sound error: " + e.getMessage());
        }
    }
}
