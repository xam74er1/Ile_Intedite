package IHM;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlaySound {

	private static  Clip audioClip = null; 

	public static Clip play(String audioFilePath) {
		File audioFile = new File(audioFilePath);
		Clip audioClip = null;

		AudioInputStream audioStream;
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);

			audioClip = (Clip) AudioSystem.getLine(info);



			audioClip.open(audioStream);

			audioClip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audioClip;


	}

	public static void stop() {
		if(audioClip != null) {
			if(audioClip.isActive()) {
				audioClip.close();
				audioClip.stop();
			}
		}
	}

}
