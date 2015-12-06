import java.util.HashMap;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.*;

public class WallpaperModule implements Runnable {
	
	private String[] urls;
	private int stateInt;
	
	public WallpaperModule(String url1, String url2, String url3, String url4, String url5, String url6, String url7, int weatherInt){
		urls = new String[7];
		if (url7.isEmpty())
		{
			urls[0] = "C:/Users/Justin/git/ambient-interface/Ambient images/other.jpg";
		}
		else
		{
			urls[0] = url7;
		}
		if (url1.isEmpty())
		{
			urls[1] = "C:/Users/Justin/git/ambient-interface/Ambient images/sun.jpg";
		}
		else
		{
			urls[1] = url1;
		}
		if (url2.isEmpty())
		{
			urls[2] = "C:/Users/Justin/git/ambient-interface/Ambient images/rain.jpg";
		}
		else
		{
			urls[2] = url2;
		}
		if (url3.isEmpty())
		{
			urls[3] = "C:/Users/Justin/git/ambient-interface/Ambient images/wind.jpg";
		}
		else
		{
			urls[3] = url3;
		}
		if (url4.isEmpty())
		{
			urls[4] = "C:/Users/Justin/git/ambient-interface/Ambient images/thunder.jpg";
		}
		else
		{
			urls[4] = url4;
		}
		if (url5.isEmpty())
		{
			urls[5] = "C:/Users/Justin/git/ambient-interface/Ambient images/snow.jpg";
		}
		else
		{
			urls[5] = url5;
		}
		if (url6.isEmpty())
		{
			urls[6] = "C:/Users/Justin/git/ambient-interface/Ambient images/cloudy.jpg";
		}
		else
		{
			urls[6] = url6;
		}
		
		stateInt = weatherInt;
		
	}

	
	/*
	 * Most of the code below taken from code written by 
	 * user Mark Peters on StackOverflow @ http://bit.ly/1QnsYV6
	 */
	@Override
	public void run() {
		
	      //Set wallpaper path
	      String path = urls[stateInt].replace("\\", "/");

	      SPI.INSTANCE.SystemParametersInfo(
	          new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), 
	          new UINT_PTR(0), 
	          path, 
	          new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));

	}

	public interface SPI extends StdCallLibrary {

		// from MSDN article
		long SPI_SETDESKWALLPAPER = 20;
		long SPIF_UPDATEINIFILE = 0x01;
		long SPIF_SENDWININICHANGE = 0x02;

		SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class, new HashMap<Object, Object>() {
			{
				put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
				put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
			}
		});

		boolean SystemParametersInfo(UINT_PTR uiAction, UINT_PTR uiParam, String pvParam, UINT_PTR fWinIni);
	}

}
