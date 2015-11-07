import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLModule {

	public static void main(String[] args) {
		try {
			if (args.length > 0) {
				URL site = new URL(args[0]);
				URLConnection conn = site.openConnection();
				BufferedReader input = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				String line = null;
				String components[] = null;

				while ((line = input.readLine()) != null) {
					if (line.contains("\"cond\">")) {
						components = line.split("\"cond\">");
						if (components.length > 0) {
							line = components[1].trim();
							line = line.substring(0, line.indexOf('<')).trim();
							break;
						}
					}
				}
				System.out.println("Current Conditions: " + line);
				System.out.println(weatherToInt(line));
				input.close();
			} else {
				System.out.println("Insufficient Arguments");
			}
		} catch (MalformedURLException mURLe) {
			System.out.println("Inproper URL Error");
		} catch (IOException ioe) {
			System.out.println(ioe.toString());
			if (ioe.toString().toLowerCase().contains("unknownhostexception")) {
				System.out.println("Suggestion:");
				System.out.println("Check Internet Connection");
			}
		}
	}

	public static int weatherToInt(String weather) {
		weather = weather.toLowerCase();
		if (weather.contains("sunny"))
		{
			return 1;
		}
		else if (weather.contains("thunder"))
		{
			return 4;
		}
		else if (weather.contains("rain") || weather.contains("shower") || weather.contains("drizzle"))
		{
			return 2;
		}
		else if (weather.contains("wind") || weather.contains("breez"))
		{
			return 3;
		}
		else if (weather.contains("snow"))
		{
			return 5;
		}
		else if (weather.contains("cloud"))
		{
			return 6;
		}
		else
		{
			return 0;
		}
	}

}
