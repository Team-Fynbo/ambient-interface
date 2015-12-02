import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioModule implements Runnable{
	
	/**
	 * Sunny audio file location
	 */
	private String audioSunny;

	/**
	 * Thunder audio file location
	 */
	private String audioThunder;
	
	/**
	 * Rain audio file location
	 */
	private String audioRain;

	/**
	 * Wind audio file location
	 */
	private String audioWind;
	
	/**
	 * Snow audio file location
	 */
	private String audioSnow;
	
	/**
	 * Cloudy audio file location
	 */
	private String audioCloudy;
	
	/**
	 * Other audio file location
	 */
	private String audioOther;
	
	private boolean stop;
	
	private int weather;
	
	/**
	 * Initializes audio file locations
	 * 
	 * @param 
	 * 
	 * @param 
	 */
	
	public AudioModule(String audioSunny, String audioThunder, String audioRain, String audioWind, String audioSnow, String audioCloudy, String audioOther, int weather) {
		
		// audio
		if(audioSunny.compareTo("") == 0) {
			this.audioSunny = "Ambient sounds/sunny_audio.wav";
		}
		else {
			this.audioSunny = audioSunny;
		}
	    
		// audio
		if(audioRain.compareTo("") == 0) {
			this.audioRain = "Ambient sounds/rainy_audio.wav";  // changed
		}
		else {
			this.audioRain = audioRain;
		}
		
		// audio
		if(audioWind.compareTo("") == 0) {
			this.audioWind = "Ambient sounds/wind_audio.wav";
		}
		else {
			this.audioWind = audioWind;
		}	
		
		// audio
		if(audioThunder.compareTo("") == 0) {
			this.audioThunder = "Ambient sounds/thunder_audio.wav";
		}
		else {
			this.audioThunder = audioThunder;
		}
		
		// audio
		if(audioCloudy.compareTo("") == 0) {
			this.audioCloudy = "Ambient sounds/other_audio.wav";
		}
		else {
			this.audioCloudy = audioCloudy;
		}
		
		// audio
		if(audioSnow.compareTo("") == 0) {
			this.audioSnow = "Ambient sounds/snow_audio.wav";
		}
		else {
			this.audioSnow = audioSnow;
		}
		
		// audio
		if(audioOther.compareTo("") == 0) {
			this.audioOther = "Ambient sounds/other_audio.wav";
		}
		else {
			this.audioOther = audioOther;
		}
		
		this.stop = false;
		this.weather = weather;
	}	
	
	public void updateAudio(int weather) {
		
		String audio = "";
		
		audio = getAudio(weather);
		playAudio(audio);	
	}
	
	private String getAudio(int weather) {
		
		String audioFile = "";
		
		switch (weather){
		
		    case 1: audioFile = this.audioSunny;
            break;
        
			case 2: audioFile = this.audioRain;
            break;

			case 3: audioFile = this.audioWind;
            break;

			case 4: audioFile = this.audioThunder;
            break;

			case 5: audioFile = this.audioSnow;
            break;
            
			case 6: audioFile = this.audioCloudy;
            break;
            
            default: audioFile = this.audioOther;
            break;
            }
		
		return audioFile;
	}
	
	private void playAudio(String audioFile) {
		
		AudioInputStream audioInputStream;
		
	    try 
	    {
	        audioInputStream = AudioSystem.getAudioInputStream(new File(audioFile));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			
			while(!this.stop && clip.isRunning());  // play audio while it is not killed and has time left.
		} 
	    catch (UnsupportedAudioFileException e) 
	    {
	        e.printStackTrace();
		} 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
		} 
	    catch (LineUnavailableException e) 
	    {
	        e.printStackTrace();
		}		
	}

	@Override
	public void run() {
		
		String audio = "";
		
		audio = getAudio(this.weather);
		playAudio(audio);
		
	}
	
	/**
	 * Stops this current runnable thread
	 */
	public void stop() {
		this.stop = true;
	}
	
}
