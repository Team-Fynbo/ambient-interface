public class Driver {

	public static void main(String[] args) throws InterruptedException {
		GUI window = new GUI("Test");
		window.showWindow();
		/*String url = "http://www.accuweather.com/en/us/honolulu-hi/96817/weather-forecast/348211";
		long time = 5;
		// Long.parseLong(time, 10)
		int delimeter = 1;
		URLModule urlmod = new URLModule(url, GUI.convertToMillis(time, delimeter));
		Thread urlThread = new Thread(urlmod);
		urlThread.setName("URL Thread");
		urlThread.start();*/

	}

}
