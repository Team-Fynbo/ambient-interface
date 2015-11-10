import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class URLModule {

  /**
   * Takes a URL as input and parses for the current weather and finds the
   * corresponding integer for the specified weather
   * 
   * @param args
   *          args[1] must be the URL to connect to and parse from args[1] must
   *          be an integer in mS to pause before running again.
   */
  public static void main(String[] args) throws InterruptedException {

    // loop counter
    int iterationCount = 0;

    while (iterationCount != 5) {

      // Code for time stamp
      LocalDateTime date = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ssa");
      String timeStamp = date.format(formatter);

      try {
        // if arguments exist then move on
        if (args.length > 0) {
          // initialize variables
          // set up connection and buffer stream
          URL site = new URL(args[0]);
          URLConnection conn = site.openConnection();
          BufferedReader input = new BufferedReader(
              new InputStreamReader(conn.getInputStream()));
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
          System.out
              .println("Current Conditions as of " + timeStamp + ": " + line);
          System.out.println(weatherToInt(line));
          input.close();
        } else { // Missing arguments
          System.out.println("Insufficient Arguments");
        }
      } catch (MalformedURLException mURLe) { // URL Error
        System.out.println("Inproper URL Error");
      } catch (IOException ioe) { // Possible internet connectivity issues
        System.out.println(ioe.toString());
        if (ioe.toString().toLowerCase().contains("unknownhostexception")) {
          System.out.println("Suggestion:");
          System.out.println("Check Internet Connection");
        }
      }

      // increment loop counter
      iterationCount++;
      // putting thread to sleep for time entered as second arg
      Thread.sleep(Long.parseLong(args[1], 10));
    }
  }

  /**
   * Further parses the current conditions to convert the condition to an
   * integer
   * 
   * @param weather
   *          the current condition to convert
   * @return integer representation of the given weather condition
   */
  public static int weatherToInt(String weather) {
    weather = weather.toLowerCase();
    if (weather.contains("sunny")) {
      return 1;
    } else if (weather.contains("thunder")) {
      return 4;
    } else if (weather.contains("rain") || weather.contains("shower")
        || weather.contains("drizzle")) {
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

}
