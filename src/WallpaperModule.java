import java.util.HashMap;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.*;

public class WallpaperModule implements Runnable {
	
	private String[] urls;
	private int stateInt;
	
	public WallpaperModule(String url1, String url2, String url3, String url4, String url5, String url6, String url7, int weatherInt){
		
		urls[0] = "C:/wallpaper.jpg";
		urls[1] = url1;
		urls[2] = url2;
		urls[3] = url3;
		urls[4] = url4;
		urls[5] = url5;
		urls[6] = url6;
		urls[7] = url7;
		
		stateInt = weatherInt;
		
	}

	@Override
	public void run() {
		
	      //Set wallpaper path
	      String path = urls[stateInt].replace("\\", "\\\\");

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
