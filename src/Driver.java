import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws InterruptedException {
		String url = "http://www.accuweather.com/en/us/honolulu-hi/96817/weather-forecast/348211";
		long time = 5;
		// Long.parseLong(time, 10)
		int delimeter = 1;
		URLModule urlmod = new URLModule(url, convertToMillis(time, delimeter));
		Thread urlThread = new Thread(urlmod);
		urlThread.setName("URL Thread");
		urlThread.start();
		Scanner scan = new Scanner(System.in);

		while (!scan.next().equals("q"));
		urlmod.stop();
		scan.close();
	}

	public static long convertToMillis(long num, int delimeter) {
		if (delimeter == 1) {
			return convertSecToMillis(num);
		} else if (delimeter == 2) {
			return convertMinToMillis(num);
		} else {
			return convertHourToMillis(num);
		}
	}

	public static long convertSecToMillis(long sec) {
		return sec * 1000;
	}

	public static long convertMinToMillis(long min) {
		return convertSecToMillis(min * 60);
	}

	public static long convertHourToMillis(long h) {
		return convertMinToMillis(h * 60);
	}

}
