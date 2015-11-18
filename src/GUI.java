import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class GUI {

	private Thread urlThread;
	private URLModule urlMod;

	/**
	 * GUI SPECIFIC VARIABLES
	 */
	private static JFrame window;
	private static GridLayout gl;
	private static JButton start;
	private static JButton stop;
	private static JSlider vol;
	private static JLabel label1;
	private static JLabel label2;
	private static JLabel label3;
	private static JLabel label4;
	private static JLabel label5;
	private static JLabel urlLabel;
	private static JTextArea urlBox;
	private static JLabel updateLabel;
	private static JTextArea updateBox;
	private static JComboBox<String> unitBox;

	/**
	 * Constructs a new GUI with the given title and default width and height.
	 * 
	 * @param title
	 *            -The title to display on the window.
	 */
	public GUI(String title) {
		this(title, 600, 600);
	}


	/**
	 * Constructs a new GUI with the given title, width, and
	 * height.
	 * 
	 * @param title
	 *            -The title to display on the window.
	 * @param width
	 *            -The width of the window.
	 * @param height
	 *            -The height of the window.
	 */
	public GUI(String title, int width, int height) {
		try {
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
		} catch (Exception e) {
			showException(e);
		}
		window = new JFrame(title);
		window.setSize(width, height);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gl = new GridLayout(3, 5, 2, 2);
		window.setLayout(gl);
		vol = new JSlider(0, 100);
		urlLabel = new JLabel("URL: ");
		urlLabel.setHorizontalAlignment(JLabel.CENTER);
		updateLabel = new JLabel("Update Interval: ");
		updateLabel.setHorizontalAlignment(JLabel.CENTER);
		urlBox = new JTextArea();
		updateBox = new JTextArea();
		unitBox = new JComboBox<String>();
		unitBox.addItem("Seconds");
		unitBox.addItem("Minutes");
		unitBox.addItem("Hours");
		unitBox.setEditable(false);
		setLabels();
		window.add(urlLabel);
		window.add(urlBox);
		window.add(updateLabel);
		window.add(updateBox);
		window.add(unitBox);
		window.add(vol);
		setButtons();

	}

	/**
	 * sets up labels as place holders for now
	 */
	private void setLabels() {
		label1 = new JLabel("LABEL 1");
		label2 = new JLabel("LABEL 2");
		label3 = new JLabel("LABEL 3");
		label4 = new JLabel("LABEL 4");
		label5 = new JLabel("LABEL 5");
		window.add(label1);
		window.add(label2);
		window.add(label3);
		window.add(label4);
		window.add(label5);
	}

	/**
	 * sets up the start and stop buttons for this GUI
	 */
	private void setButtons() {
		start = new JButton("Start");
		stop = new JButton("Stop");
		window.add(start);
		window.add(stop);
		stop.setEnabled(false);

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				start.setEnabled(false);
				stop.setEnabled(true);
				urlMod = new URLModule(getURL(), convertToMillis(getUpdate(), getUnit()));
				urlThread = new Thread(urlMod);
				urlThread.setName("URL Thread");
				urlThread.start();
			}

		});

		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stop.setEnabled(false);
				start.setEnabled(true);
				urlMod.stop();
			}

		});
	}

	/**
	 * Gets the URL that the user inputs to the GUI
	 * 
	 * @return the URL from the GUI to parse data from
	 */
	public String getURL() {
		return urlBox.getText().trim();
	}

	/**
	 * Gets the update interval time from the GUI
	 * 
	 * @return the long representation of the update interval time
	 */
	public long getUpdate() {
		String temp = updateBox.getText().trim();
		try {
			return Long.parseLong(temp, 10);
		} catch (NumberFormatException nfe) {
			showMessage("Value entered in update interval was not a valid number, using a default of 10 min.");
			unitBox.setSelectedIndex(1);
			return 10;
		}
	}

	/**
	 * Gets the units that the user inputs from the GUI
	 * 
	 * @return the integer representation of the time unit
	 */
	public int getUnit() {
		String temp = unitBox.getSelectedItem().toString();
		if (temp.equalsIgnoreCase("seconds")) {
			return 1;
		} else if (temp.equalsIgnoreCase("minutes")) {
			return 2;
		} else {
			return 3;
		}
	}

	/**
	 * Adds this component to the GUI window.
	 * 
	 * @param comp
	 *            The component to add to the window.
	 * @return true if and only if the component was added to the window.
	 */
	public boolean add(Component comp) {
		return window.add(comp) != null;
	}

	/**
	 * Shows the window.
	 */
	public void showWindow() {
		window.setVisible(true);
	}

	/**
	 * Shows the given exception in a new window.
	 * 
	 * @param ex
	 *            -The exception in string form.
	 */
	public static void showException(Exception ex) {
		window = new JFrame("Exception");
		window.setSize(500, 200);
		window.setLocationRelativeTo(null);
		JLabel exception = new JLabel(ex.toString(), JLabel.CENTER);
		window.add(exception);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Shows the given message in a new window.
	 * 
	 * @param msg
	 *            -The message in string form.
	 */
	public static void showMessage(String msg) {
		window = new JFrame("Message");
		window.setSize(500, 200);
		window.setLocationRelativeTo(null);
		JLabel message = new JLabel(msg, JLabel.CENTER);
		window.add(message);
		window.setVisible(true);
	}

	/**
	 * Gets the file (using JfileChooser) needed for input.
	 * 
	 * @return The file chosen -null if the user presses cancel
	 * @throws FileOperationCancelledException
	 *             File operation cancelled.
	 */

	public static File getFile(String title) throws FileOperationCancelledException {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(title);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int response = fc.showDialog(window, "use");
		if (response == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		} else {
			throw new FileOperationCancelledException("File operation cancelled");
		}

	}

	/**
	 * Gets the file (using the JFileChooser) needed for input.
	 * 
	 * @return The file the user chooses.
	 * @throws FileOperationCancelledException
	 *             File operation cancelled.
	 */

	public static File getFolder(String title) throws FileOperationCancelledException {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(title);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int response = fc.showDialog(window, "use");
		if (response == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		} else {
			throw new FileOperationCancelledException("Folder operation cancelled");
		}

	}

	/**
	 * Converts the given time to milliseconds using the given units to do the
	 * proper conversion.
	 * 
	 * @param time
	 *            the time to convert
	 * @param unit
	 *            the unit the time is using
	 * @return the time in milliseconds as a result of the conversion
	 */
	public static long convertToMillis(long time, int unit) {
		if (unit == 1) {
			return convertSecToMillis(time);
		} else if (unit == 2) {
			return convertMinToMillis(time);
		} else {
			return convertHourToMillis(time);
		}
	}

	private static long convertSecToMillis(long sec) {
		return sec * 1000;
	}

	private static long convertMinToMillis(long min) {
		return convertSecToMillis(min * 60);
	}

	private static long convertHourToMillis(long h) {
		return convertMinToMillis(h * 60);
	}

}
