package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.net.URL;

public class Sound {
	
	Clip music;
	
	URL soundPath[] = new URL[30];
	
	public Sound() {
		
		soundPath[0] = getClass().getResource("/sound/sound1.wav");
		soundPath[1] = getClass().getResource("/sound/keys.wav");
		soundPath[2] = getClass().getResource("/sound/katana.wav");
		soundPath[3] = getClass().getResource("/sound/suitUp.wav");
		soundPath[4] = getClass().getResource("/sound/willPower.wav");
		soundPath[5] = getClass().getResource("/sound/chest.wav");
		soundPath[6] = getClass().getResource("/sound/PAUSE.wav");
		soundPath[7] = getClass().getResource("/sound/UNPAUSE.wav");
		soundPath[8] = getClass().getResource("/sound/shinobiJutsu.wav");
		soundPath[9] = getClass().getResource("/sound/selectChoice.wav");
		soundPath[10] = getClass().getResource("/sound/menuSong.wav");
		soundPath[11] = getClass().getResource("/sound/WILLPOWAH.wav");
		soundPath[12] = getClass().getResource("/sound/attack.wav");
		soundPath[13] = getClass().getResource("/sound/eatKitKat.wav");
		soundPath[14] = getClass().getResource("/sound/runningAway.wav");
	}
	
	public void setFile(int i){
		
		try {
			// code bunch to play music files in java
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundPath[i]); 
			music = AudioSystem.getClip();
			music.open(ais);
			
		}catch(Exception e) {
		
		}
	}
	public void start() {
		
		music.start();
	
	}
	
	public void loop() {
		
		music.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public void stop() {
		
		music.stop();
		
	}
}
