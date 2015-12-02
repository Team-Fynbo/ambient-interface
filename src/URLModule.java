import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class URLModule implements Runnable {

	/**
	 * The url to pull data from
	 */
	private String url;
	/**
	 * The update interval to update the data from the url
	 */
	private long time;
	/**
	 * Tells whether or not to stop this thread
	 */
	private boolean stop;

	private String[] wallpapers;
	private String[] audios;

	/**
	 * Initializes the url, time, and stop variables url and time given by the
	 * user
	 * 
	 * @param url
	 *            the url to pull data from
	 * @param time
	 *            the update interval time
	 */
	public URLModule(String url, Long time, String[] wallpapers, String[] audios) {
		this.url = url;
		this.time = time;
		this.wallpapers = wallpapers;
		this.audios = audios;
		stop = false;
	}

	/**
	 * Further parses the current conditions to convert the condition to an
	 * integer
	 * 
	 * @param weather
	 *            the current condition to convert
	 * @return integer representation of the given weather condition
	 */
	public static int weatherToInt(String weather) {
		weather = weather.toLowerCase();
		if (weather.contains("sunny")) {
			return 1;
		} else if (weather.contains("thunder")) {
			return 4;
		} else if (weather.contains("rain") || weather.contains("shower") || weather.contains("drizzle")) {
			return 2;
		} else if (weather.contains("wind") || weather.contains("breez")) {
			return 3;
		} else if (weather.contains("snow")) {
			return 5;
		} else if (weather.contains("cloud")) {
			return 6;
		} else {
			return 0;
		}
	}

	/**
	 * Runs like a background process, pulls data from the given URL and parses
	 * the data to convert and pass an integer representing the current weather
	 * constantly updates based on the time the user gives
	 */
	@Override
	public void run() {

		WallpaperModule wm = new WallpaperModule(wallpapers[0], wallpapers[1], wallpapers[2], wallpapers[3],
				wallpapers[4], wallpapers[5], wallpapers[6], 0);
		AudioModule am = new AudioModule(audios[0], audios[1], audios[2], audios[3],
				audios[4], audios[5], audios[6], 0);
		int current = 0;
		int previous = -1;
		// wait for another thread to call stop
		while (!stop) {

			// Code for time stamp
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ssa");
			String timeStamp = date.format(formatter);

			try {
				// initialize variables
				// set up connection and buffer stream
				URL site = new URL(url);
				URLConnection conn = site.openConnection();
				BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = null;
				String components[] = null;

				// read the source code
				while ((line = input.readLine()) != null) {
					// parse for the current weather conditions
					if (line.contains("\"cond\">")) {
						components = line.split("\"cond\">");
						if (components.length > 0) {
							line = components[1].trim();
							line = line.substring(0, line.indexOf('<')).trim();
							break;
						}
					}
				}
				System.out.println("Current Conditions as of " + timeStamp + ": " + line);
				previous = current;
				current = weatherToInt(line);
				System.out.println(current);

				if (current != previous) {
					wm = new WallpaperModule(wallpapers[0], wallpapers[1], wallpapers[2], wallpapers[3], wallpapers[4],
							wallpapers[5], wallpapers[6], current);
					am = new AudioModule(audios[0], audios[1], audios[2], audios[3],
							audios[4], audios[5], audios[6], current);
					Thread amt = new Thread(am);
					Thread wmt = new Thread(wm);
					amt.setName("Audio Thread");
					wmt.setName("Wallpaper Thread");
					amt.start();
					wmt.start();
				}
				input.close();

			} catch (MalformedURLException mURLe) { // URL Error
				System.out.println("Inproper URL Error");
			} catch (IOException ioe) { // Possible internet connectivity issues
				System.out.println(ioe.toString());
				if (ioe.toString().toLowerCase().contains("unknownhostexception")) {
					System.out.println("Suggestion:");
					System.out.println("Check Internet Connection");
				}
			}

			// putting thread to sleep for time entered as second arg
			try {
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	/**
	 * Stops this current runnable thread
	 */
	public void stop() {
		stop = true;
	}

}