package managers;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {
    private static Clip backgroundClip;

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

    public static void playBackgroundMusic(String path) {

        try {

            if (backgroundClip != null && backgroundClip.isRunning()) {

                return;
            }

            File file = new File(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);

            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInputStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundClip.start();

        } catch (Exception e) {

            System.out.println("Background music error: " + e.getMessage());
        }
    }

    public static void stopBackgroundMusic() {

        if (backgroundClip != null) {

            backgroundClip.stop();
            backgroundClip.close();
            backgroundClip = null;
        }
    }
}
