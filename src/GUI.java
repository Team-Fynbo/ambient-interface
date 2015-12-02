import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class GUI {

	private Thread urlThread;
	private URLModule urlMod;

	/**
	 * GUI SPECIFIC VARIABLES
	 */
	private JFrame frmFynboAmbientInterface;
	private JTextField urlBox;
	private JTextField updateBox;
	private JTextField sunBox;
	private JTextField thunderBox;
	private JTextField rainBox;
	private JTextField windBox;
	private JTextField snowBox;
	private JTextField cloudBox;
	private JTextField otherBox;
	private JTextField sunSoundBox;
	private JTextField thunderSoundBox;
	private JTextField rainSoundBox;
	private JTextField windSoundBox;
	private JTextField snowSoundBox;
	private JTextField cloudSoundBox;
	private JTextField otherSoundBox;
	private JButton stop;
	private JButton start;
	private JComboBox<String> unitCombo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmFynboAmbientInterface.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException cnfe) {
			showException(cnfe);
		} catch (InstantiationException ie) {
			showException(ie);
		} catch (IllegalAccessException iae) {
			showException(iae);
		} catch (UnsupportedLookAndFeelException ulafe) {
			showException(ulafe);
		}

		frmFynboAmbientInterface = new JFrame();
		frmFynboAmbientInterface.setTitle("FYNBO Ambient Interface");
		frmFynboAmbientInterface.setBounds(100, 100, 800, 400);
		frmFynboAmbientInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frmFynboAmbientInterface.getContentPane().setLayout(gridBagLayout);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 6;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 0;
		frmFynboAmbientInterface.getContentPane().add(separator_1, gbc_separator_1);

		JSeparator separator_9 = new JSeparator();
		GridBagConstraints gbc_separator_9 = new GridBagConstraints();
		gbc_separator_9.gridheight = 17;
		gbc_separator_9.insets = new Insets(0, 0, 0, 5);
		gbc_separator_9.gridx = 0;
		gbc_separator_9.gridy = 1;
		frmFynboAmbientInterface.getContentPane().add(separator_9, gbc_separator_9);

		JLabel sunLabel = new JLabel("Sun Image: ");
		GridBagConstraints gbc_sunLabel = new GridBagConstraints();
		gbc_sunLabel.anchor = GridBagConstraints.EAST;
		gbc_sunLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sunLabel.gridx = 1;
		gbc_sunLabel.gridy = 1;
		frmFynboAmbientInterface.getContentPane().add(sunLabel, gbc_sunLabel);

		sunBox = new JTextField();
		sunBox.setEditable(false);
		GridBagConstraints gbc_sunBox = new GridBagConstraints();
		gbc_sunBox.insets = new Insets(0, 0, 5, 5);
		gbc_sunBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_sunBox.gridx = 2;
		gbc_sunBox.gridy = 1;
		frmFynboAmbientInterface.getContentPane().add(sunBox, gbc_sunBox);
		sunBox.setColumns(10);

		JButton sunImageBrowse = new JButton("Browse");
		GridBagConstraints gbc_sunImageBrowse = new GridBagConstraints();
		gbc_sunImageBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_sunImageBrowse.gridx = 3;
		gbc_sunImageBrowse.gridy = 1;
		frmFynboAmbientInterface.getContentPane().add(sunImageBrowse, gbc_sunImageBrowse);
		sunImageBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					sunBox.setText(getFile("Sun Image").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JLabel sunSoundLabel = new JLabel("Sun Sound: ");
		GridBagConstraints gbc_sunSoundLabel = new GridBagConstraints();
		gbc_sunSoundLabel.anchor = GridBagConstraints.EAST;
		gbc_sunSoundLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sunSoundLabel.gridx = 4;
		gbc_sunSoundLabel.gridy = 1;
		frmFynboAmbientInterface.getContentPane().add(sunSoundLabel, gbc_sunSoundLabel);

		sunSoundBox = new JTextField();
		sunSoundBox.setEditable(false);
		GridBagConstraints gbc_sunSoundBox = new GridBagConstraints();
		gbc_sunSoundBox.insets = new Insets(0, 0, 5, 5);
		gbc_sunSoundBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_sunSoundBox.gridx = 5;
		gbc_sunSoundBox.gridy = 1;
		frmFynboAmbientInterface.getContentPane().add(sunSoundBox, gbc_sunSoundBox);
		sunSoundBox.setColumns(10);

		JButton sunSoundBrowse = new JButton("Browse");
		GridBagConstraints gbc_sunSoundBrowse = new GridBagConstraints();
		gbc_sunSoundBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_sunSoundBrowse.gridx = 6;
		gbc_sunSoundBrowse.gridy = 1;
		frmFynboAmbientInterface.getContentPane().add(sunSoundBrowse, gbc_sunSoundBrowse);
		sunSoundBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					sunSoundBox.setText(getFile("Sun Sound").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JSeparator separator_10 = new JSeparator();
		GridBagConstraints gbc_separator_10 = new GridBagConstraints();
		gbc_separator_10.gridheight = 17;
		gbc_separator_10.gridx = 7;
		gbc_separator_10.gridy = 1;
		frmFynboAmbientInterface.getContentPane().add(separator_10, gbc_separator_10);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 6;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 2;
		frmFynboAmbientInterface.getContentPane().add(separator, gbc_separator);

		JLabel thunderLabel = new JLabel("Thunder Storm Image:");
		GridBagConstraints gbc_thunderLabel = new GridBagConstraints();
		gbc_thunderLabel.anchor = GridBagConstraints.EAST;
		gbc_thunderLabel.insets = new Insets(0, 0, 5, 5);
		gbc_thunderLabel.gridx = 1;
		gbc_thunderLabel.gridy = 3;
		frmFynboAmbientInterface.getContentPane().add(thunderLabel, gbc_thunderLabel);

		thunderBox = new JTextField();
		thunderBox.setEditable(false);
		GridBagConstraints gbc_thunderBox = new GridBagConstraints();
		gbc_thunderBox.insets = new Insets(0, 0, 5, 5);
		gbc_thunderBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_thunderBox.gridx = 2;
		gbc_thunderBox.gridy = 3;
		frmFynboAmbientInterface.getContentPane().add(thunderBox, gbc_thunderBox);
		thunderBox.setColumns(10);

		JButton thunderImageBrowse = new JButton("Browse");
		GridBagConstraints gbc_thunderImageBrowse = new GridBagConstraints();
		gbc_thunderImageBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_thunderImageBrowse.gridx = 3;
		gbc_thunderImageBrowse.gridy = 3;
		frmFynboAmbientInterface.getContentPane().add(thunderImageBrowse, gbc_thunderImageBrowse);
		thunderImageBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					thunderBox.setText(getFile("Thunder Image").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JLabel thunderSoundLabel = new JLabel("Thunder Sound: ");
		GridBagConstraints gbc_thunderSoundLabel = new GridBagConstraints();
		gbc_thunderSoundLabel.anchor = GridBagConstraints.EAST;
		gbc_thunderSoundLabel.insets = new Insets(0, 0, 5, 5);
		gbc_thunderSoundLabel.gridx = 4;
		gbc_thunderSoundLabel.gridy = 3;
		frmFynboAmbientInterface.getContentPane().add(thunderSoundLabel, gbc_thunderSoundLabel);

		thunderSoundBox = new JTextField();
		thunderSoundBox.setEditable(false);
		GridBagConstraints gbc_thunderSoundBox = new GridBagConstraints();
		gbc_thunderSoundBox.insets = new Insets(0, 0, 5, 5);
		gbc_thunderSoundBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_thunderSoundBox.gridx = 5;
		gbc_thunderSoundBox.gridy = 3;
		frmFynboAmbientInterface.getContentPane().add(thunderSoundBox, gbc_thunderSoundBox);
		thunderSoundBox.setColumns(10);

		JButton thunderSoundBrowse = new JButton("Browse");
		GridBagConstraints gbc_thunderSoundBrowse = new GridBagConstraints();
		gbc_thunderSoundBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_thunderSoundBrowse.gridx = 6;
		gbc_thunderSoundBrowse.gridy = 3;
		frmFynboAmbientInterface.getContentPane().add(thunderSoundBrowse, gbc_thunderSoundBrowse);
		thunderSoundBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					thunderSoundBox.setText(getFile("Thunder Sound").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.gridwidth = 6;
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 1;
		gbc_separator_2.gridy = 4;
		frmFynboAmbientInterface.getContentPane().add(separator_2, gbc_separator_2);

		JLabel rain = new JLabel("Rain Image: ");
		GridBagConstraints gbc_rain = new GridBagConstraints();
		gbc_rain.anchor = GridBagConstraints.EAST;
		gbc_rain.insets = new Insets(0, 0, 5, 5);
		gbc_rain.gridx = 1;
		gbc_rain.gridy = 5;
		frmFynboAmbientInterface.getContentPane().add(rain, gbc_rain);

		rainBox = new JTextField();
		rainBox.setEditable(false);
		GridBagConstraints gbc_rainBox = new GridBagConstraints();
		gbc_rainBox.insets = new Insets(0, 0, 5, 5);
		gbc_rainBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_rainBox.gridx = 2;
		gbc_rainBox.gridy = 5;
		frmFynboAmbientInterface.getContentPane().add(rainBox, gbc_rainBox);
		rainBox.setColumns(10);

		JButton rainImageBrowse = new JButton("Browse");
		GridBagConstraints gbc_rainImageBrowse = new GridBagConstraints();
		gbc_rainImageBrowse.anchor = GridBagConstraints.EAST;
		gbc_rainImageBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_rainImageBrowse.gridx = 3;
		gbc_rainImageBrowse.gridy = 5;
		frmFynboAmbientInterface.getContentPane().add(rainImageBrowse, gbc_rainImageBrowse);
		rainImageBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					rainBox.setText(getFile("Rain Image").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JLabel rainSoundLabel = new JLabel("Rain Sound: ");
		GridBagConstraints gbc_rainSoundLabel = new GridBagConstraints();
		gbc_rainSoundLabel.anchor = GridBagConstraints.EAST;
		gbc_rainSoundLabel.insets = new Insets(0, 0, 5, 5);
		gbc_rainSoundLabel.gridx = 4;
		gbc_rainSoundLabel.gridy = 5;
		frmFynboAmbientInterface.getContentPane().add(rainSoundLabel, gbc_rainSoundLabel);

		rainSoundBox = new JTextField();
		rainSoundBox.setEditable(false);
		GridBagConstraints gbc_rainSoundBox = new GridBagConstraints();
		gbc_rainSoundBox.insets = new Insets(0, 0, 5, 5);
		gbc_rainSoundBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_rainSoundBox.gridx = 5;
		gbc_rainSoundBox.gridy = 5;
		frmFynboAmbientInterface.getContentPane().add(rainSoundBox, gbc_rainSoundBox);
		rainSoundBox.setColumns(10);

		JButton rainSoundBrowse = new JButton("Browse");
		GridBagConstraints gbc_rainSoundBrowse = new GridBagConstraints();
		gbc_rainSoundBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_rainSoundBrowse.gridx = 6;
		gbc_rainSoundBrowse.gridy = 5;
		frmFynboAmbientInterface.getContentPane().add(rainSoundBrowse, gbc_rainSoundBrowse);
		rainSoundBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					rainSoundBox.setText(getFile("Rain Sound").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.gridwidth = 6;
		gbc_separator_3.insets = new Insets(0, 0, 5, 5);
		gbc_separator_3.gridx = 1;
		gbc_separator_3.gridy = 6;
		frmFynboAmbientInterface.getContentPane().add(separator_3, gbc_separator_3);

		JLabel windLabel = new JLabel("Wind Image: ");
		GridBagConstraints gbc_windLabel = new GridBagConstraints();
		gbc_windLabel.anchor = GridBagConstraints.EAST;
		gbc_windLabel.insets = new Insets(0, 0, 5, 5);
		gbc_windLabel.gridx = 1;
		gbc_windLabel.gridy = 7;
		frmFynboAmbientInterface.getContentPane().add(windLabel, gbc_windLabel);

		windBox = new JTextField();
		windBox.setEditable(false);
		GridBagConstraints gbc_windBox = new GridBagConstraints();
		gbc_windBox.insets = new Insets(0, 0, 5, 5);
		gbc_windBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_windBox.gridx = 2;
		gbc_windBox.gridy = 7;
		frmFynboAmbientInterface.getContentPane().add(windBox, gbc_windBox);
		windBox.setColumns(10);

		JButton windImageBrowse = new JButton("Browse");
		GridBagConstraints gbc_windImageBrowse = new GridBagConstraints();
		gbc_windImageBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_windImageBrowse.gridx = 3;
		gbc_windImageBrowse.gridy = 7;
		frmFynboAmbientInterface.getContentPane().add(windImageBrowse, gbc_windImageBrowse);
		windImageBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					windBox.setText(getFile("Wind Image").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JLabel windSoundLabel = new JLabel("Wind Sound: ");
		GridBagConstraints gbc_windSoundLabel = new GridBagConstraints();
		gbc_windSoundLabel.anchor = GridBagConstraints.EAST;
		gbc_windSoundLabel.insets = new Insets(0, 0, 5, 5);
		gbc_windSoundLabel.gridx = 4;
		gbc_windSoundLabel.gridy = 7;
		frmFynboAmbientInterface.getContentPane().add(windSoundLabel, gbc_windSoundLabel);

		windSoundBox = new JTextField();
		windSoundBox.setEditable(false);
		GridBagConstraints gbc_windSoundBox = new GridBagConstraints();
		gbc_windSoundBox.insets = new Insets(0, 0, 5, 5);
		gbc_windSoundBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_windSoundBox.gridx = 5;
		gbc_windSoundBox.gridy = 7;
		frmFynboAmbientInterface.getContentPane().add(windSoundBox, gbc_windSoundBox);
		windSoundBox.setColumns(10);

		JButton windSoundBrowse = new JButton("Browse");
		GridBagConstraints gbc_windSoundBrowse = new GridBagConstraints();
		gbc_windSoundBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_windSoundBrowse.gridx = 6;
		gbc_windSoundBrowse.gridy = 7;
		frmFynboAmbientInterface.getContentPane().add(windSoundBrowse, gbc_windSoundBrowse);
		windSoundBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					windSoundBox.setText(getFile("Wind Sound").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JSeparator separator_4 = new JSeparator();
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.gridwidth = 6;
		gbc_separator_4.insets = new Insets(0, 0, 5, 5);
		gbc_separator_4.gridx = 1;
		gbc_separator_4.gridy = 8;
		frmFynboAmbientInterface.getContentPane().add(separator_4, gbc_separator_4);

		JLabel snowLabel = new JLabel("Snow Image: ");
		GridBagConstraints gbc_snowLabel = new GridBagConstraints();
		gbc_snowLabel.anchor = GridBagConstraints.EAST;
		gbc_snowLabel.insets = new Insets(0, 0, 5, 5);
		gbc_snowLabel.gridx = 1;
		gbc_snowLabel.gridy = 9;
		frmFynboAmbientInterface.getContentPane().add(snowLabel, gbc_snowLabel);

		snowBox = new JTextField();
		snowBox.setEditable(false);
		GridBagConstraints gbc_snowBox = new GridBagConstraints();
		gbc_snowBox.insets = new Insets(0, 0, 5, 5);
		gbc_snowBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_snowBox.gridx = 2;
		gbc_snowBox.gridy = 9;
		frmFynboAmbientInterface.getContentPane().add(snowBox, gbc_snowBox);
		snowBox.setColumns(10);

		JButton snowImageBrowse = new JButton("Browse");
		GridBagConstraints gbc_snowImageBrowse = new GridBagConstraints();
		gbc_snowImageBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_snowImageBrowse.gridx = 3;
		gbc_snowImageBrowse.gridy = 9;
		frmFynboAmbientInterface.getContentPane().add(snowImageBrowse, gbc_snowImageBrowse);
		snowImageBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					snowBox.setText(getFile("Snow Image").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JLabel snowSoundLabel = new JLabel("Snow Sound: ");
		GridBagConstraints gbc_snowSoundLabel = new GridBagConstraints();
		gbc_snowSoundLabel.anchor = GridBagConstraints.EAST;
		gbc_snowSoundLabel.insets = new Insets(0, 0, 5, 5);
		gbc_snowSoundLabel.gridx = 4;
		gbc_snowSoundLabel.gridy = 9;
		frmFynboAmbientInterface.getContentPane().add(snowSoundLabel, gbc_snowSoundLabel);

		snowSoundBox = new JTextField();
		snowSoundBox.setEditable(false);
		GridBagConstraints gbc_snowSoundBox = new GridBagConstraints();
		gbc_snowSoundBox.insets = new Insets(0, 0, 5, 5);
		gbc_snowSoundBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_snowSoundBox.gridx = 5;
		gbc_snowSoundBox.gridy = 9;
		frmFynboAmbientInterface.getContentPane().add(snowSoundBox, gbc_snowSoundBox);
		snowSoundBox.setColumns(10);

		JButton snowSoundBrowse = new JButton("Browse");
		GridBagConstraints gbc_snowSoundBrowse = new GridBagConstraints();
		gbc_snowSoundBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_snowSoundBrowse.gridx = 6;
		gbc_snowSoundBrowse.gridy = 9;
		frmFynboAmbientInterface.getContentPane().add(snowSoundBrowse, gbc_snowSoundBrowse);
		snowSoundBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					snowSoundBox.setText(getFile("Snow Sound").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JSeparator separator_5 = new JSeparator();
		GridBagConstraints gbc_separator_5 = new GridBagConstraints();
		gbc_separator_5.gridwidth = 6;
		gbc_separator_5.insets = new Insets(0, 0, 5, 5);
		gbc_separator_5.gridx = 1;
		gbc_separator_5.gridy = 10;
		frmFynboAmbientInterface.getContentPane().add(separator_5, gbc_separator_5);

		JLabel cloudLabel = new JLabel("Cloud Image: ");
		GridBagConstraints gbc_cloudLabel = new GridBagConstraints();
		gbc_cloudLabel.anchor = GridBagConstraints.EAST;
		gbc_cloudLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cloudLabel.gridx = 1;
		gbc_cloudLabel.gridy = 11;
		frmFynboAmbientInterface.getContentPane().add(cloudLabel, gbc_cloudLabel);

		cloudBox = new JTextField();
		cloudBox.setEditable(false);
		GridBagConstraints gbc_cloudBox = new GridBagConstraints();
		gbc_cloudBox.insets = new Insets(0, 0, 5, 5);
		gbc_cloudBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_cloudBox.gridx = 2;
		gbc_cloudBox.gridy = 11;
		frmFynboAmbientInterface.getContentPane().add(cloudBox, gbc_cloudBox);
		cloudBox.setColumns(10);

		JButton cloudImageBrowse = new JButton("Browse");
		GridBagConstraints gbc_cloudImageBrowse = new GridBagConstraints();
		gbc_cloudImageBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_cloudImageBrowse.gridx = 3;
		gbc_cloudImageBrowse.gridy = 11;
		frmFynboAmbientInterface.getContentPane().add(cloudImageBrowse, gbc_cloudImageBrowse);
		cloudImageBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					cloudBox.setText(getFile("Cloud Image").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JLabel cloudSoundLabel = new JLabel("Cloud Sound: ");
		GridBagConstraints gbc_cloudSoundLabel = new GridBagConstraints();
		gbc_cloudSoundLabel.anchor = GridBagConstraints.EAST;
		gbc_cloudSoundLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cloudSoundLabel.gridx = 4;
		gbc_cloudSoundLabel.gridy = 11;
		frmFynboAmbientInterface.getContentPane().add(cloudSoundLabel, gbc_cloudSoundLabel);

		cloudSoundBox = new JTextField();
		cloudSoundBox.setEditable(false);
		GridBagConstraints gbc_cloudSoundBox = new GridBagConstraints();
		gbc_cloudSoundBox.insets = new Insets(0, 0, 5, 5);
		gbc_cloudSoundBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_cloudSoundBox.gridx = 5;
		gbc_cloudSoundBox.gridy = 11;
		frmFynboAmbientInterface.getContentPane().add(cloudSoundBox, gbc_cloudSoundBox);
		cloudSoundBox.setColumns(10);

		JButton cloudSoundBrowse = new JButton("Browse");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 11;
		frmFynboAmbientInterface.getContentPane().add(cloudSoundBrowse, gbc_btnNewButton);
		cloudSoundBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					cloudSoundBox.setText(getFile("Cloud Sound").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JSeparator separator_6 = new JSeparator();
		GridBagConstraints gbc_separator_6 = new GridBagConstraints();
		gbc_separator_6.gridwidth = 6;
		gbc_separator_6.insets = new Insets(0, 0, 5, 5);
		gbc_separator_6.gridx = 1;
		gbc_separator_6.gridy = 12;
		frmFynboAmbientInterface.getContentPane().add(separator_6, gbc_separator_6);

		JLabel other = new JLabel("Other Image:");
		GridBagConstraints gbc_other = new GridBagConstraints();
		gbc_other.anchor = GridBagConstraints.EAST;
		gbc_other.insets = new Insets(0, 0, 5, 5);
		gbc_other.gridx = 1;
		gbc_other.gridy = 13;
		frmFynboAmbientInterface.getContentPane().add(other, gbc_other);

		otherBox = new JTextField();
		otherBox.setEditable(false);
		GridBagConstraints gbc_otherBox = new GridBagConstraints();
		gbc_otherBox.insets = new Insets(0, 0, 5, 5);
		gbc_otherBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_otherBox.gridx = 2;
		gbc_otherBox.gridy = 13;
		frmFynboAmbientInterface.getContentPane().add(otherBox, gbc_otherBox);
		otherBox.setColumns(10);

		JButton otherImageBrowse = new JButton("Browse");
		GridBagConstraints gbc_otherImageBrowse = new GridBagConstraints();
		gbc_otherImageBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_otherImageBrowse.gridx = 3;
		gbc_otherImageBrowse.gridy = 13;
		frmFynboAmbientInterface.getContentPane().add(otherImageBrowse, gbc_otherImageBrowse);
		otherImageBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					otherBox.setText(getFile("Other Image").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JLabel otherSoundLabel = new JLabel("Other Sound: ");
		GridBagConstraints gbc_otherSoundLabel = new GridBagConstraints();
		gbc_otherSoundLabel.anchor = GridBagConstraints.EAST;
		gbc_otherSoundLabel.insets = new Insets(0, 0, 5, 5);
		gbc_otherSoundLabel.gridx = 4;
		gbc_otherSoundLabel.gridy = 13;
		frmFynboAmbientInterface.getContentPane().add(otherSoundLabel, gbc_otherSoundLabel);

		otherSoundBox = new JTextField();
		otherSoundBox.setEditable(false);
		GridBagConstraints gbc_otherSoundBox = new GridBagConstraints();
		gbc_otherSoundBox.insets = new Insets(0, 0, 5, 5);
		gbc_otherSoundBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_otherSoundBox.gridx = 5;
		gbc_otherSoundBox.gridy = 13;
		frmFynboAmbientInterface.getContentPane().add(otherSoundBox, gbc_otherSoundBox);
		otherSoundBox.setColumns(10);

		JButton otherSoundBrowse = new JButton("Browse");
		GridBagConstraints gbc_otherSoundBrowse = new GridBagConstraints();
		gbc_otherSoundBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_otherSoundBrowse.gridx = 6;
		gbc_otherSoundBrowse.gridy = 13;
		frmFynboAmbientInterface.getContentPane().add(otherSoundBrowse, gbc_otherSoundBrowse);
		otherSoundBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					otherSoundBox.setText(getFile("Other Sound").toString());
				} catch (FileOperationCancelledException foce) {
					showMessage(foce.getMessage());
				}

			}

		});

		JSeparator separator_7 = new JSeparator();
		GridBagConstraints gbc_separator_7 = new GridBagConstraints();
		gbc_separator_7.gridwidth = 6;
		gbc_separator_7.insets = new Insets(0, 0, 5, 5);
		gbc_separator_7.gridx = 1;
		gbc_separator_7.gridy = 14;
		frmFynboAmbientInterface.getContentPane().add(separator_7, gbc_separator_7);

		JLabel urlLabel = new JLabel("URL: ");
		urlLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_urlLabel = new GridBagConstraints();
		gbc_urlLabel.anchor = GridBagConstraints.EAST;
		gbc_urlLabel.insets = new Insets(0, 0, 5, 5);
		gbc_urlLabel.gridx = 1;
		gbc_urlLabel.gridy = 15;
		frmFynboAmbientInterface.getContentPane().add(urlLabel, gbc_urlLabel);

		urlBox = new JTextField();
		GridBagConstraints gbc_urlBox = new GridBagConstraints();
		gbc_urlBox.anchor = GridBagConstraints.EAST;
		gbc_urlBox.gridwidth = 2;
		gbc_urlBox.insets = new Insets(0, 0, 5, 5);
		gbc_urlBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_urlBox.gridx = 2;
		gbc_urlBox.gridy = 15;
		frmFynboAmbientInterface.getContentPane().add(urlBox, gbc_urlBox);
		urlBox.setColumns(10);

		JLabel updateLabel = new JLabel("Update Time:");
		updateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_updateLabel = new GridBagConstraints();
		gbc_updateLabel.anchor = GridBagConstraints.EAST;
		gbc_updateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_updateLabel.gridx = 4;
		gbc_updateLabel.gridy = 15;
		frmFynboAmbientInterface.getContentPane().add(updateLabel, gbc_updateLabel);

		updateBox = new JTextField();
		GridBagConstraints gbc_updateBox = new GridBagConstraints();
		gbc_updateBox.anchor = GridBagConstraints.EAST;
		gbc_updateBox.insets = new Insets(0, 0, 5, 5);
		gbc_updateBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_updateBox.gridx = 5;
		gbc_updateBox.gridy = 15;
		frmFynboAmbientInterface.getContentPane().add(updateBox, gbc_updateBox);
		updateBox.setColumns(10);

		unitCombo = new JComboBox<String>();
		unitCombo.setModel(new DefaultComboBoxModel<String>(new String[] { "Seconds", "Minutes", "Hours" }));
		GridBagConstraints gbc_unitCombo = new GridBagConstraints();
		gbc_unitCombo.anchor = GridBagConstraints.EAST;
		gbc_unitCombo.insets = new Insets(0, 0, 5, 5);
		gbc_unitCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_unitCombo.gridx = 6;
		gbc_unitCombo.gridy = 15;
		frmFynboAmbientInterface.getContentPane().add(unitCombo, gbc_unitCombo);

		JSeparator separator_8 = new JSeparator();
		GridBagConstraints gbc_separator_8 = new GridBagConstraints();
		gbc_separator_8.gridwidth = 6;
		gbc_separator_8.insets = new Insets(0, 0, 5, 5);
		gbc_separator_8.gridx = 1;
		gbc_separator_8.gridy = 16;
		frmFynboAmbientInterface.getContentPane().add(separator_8, gbc_separator_8);

		JSlider slider = new JSlider();
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.anchor = GridBagConstraints.SOUTH;
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.gridwidth = 4;
		gbc_slider.insets = new Insets(0, 0, 0, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 17;
		frmFynboAmbientInterface.getContentPane().add(slider, gbc_slider);

		start = new JButton("Start");
		GridBagConstraints gbc_start = new GridBagConstraints();
		gbc_start.anchor = GridBagConstraints.SOUTH;
		gbc_start.insets = new Insets(0, 0, 0, 5);
		gbc_start.gridx = 5;
		gbc_start.gridy = 17;
		frmFynboAmbientInterface.getContentPane().add(start, gbc_start);

		stop = new JButton("Stop");
		GridBagConstraints gbc_stop = new GridBagConstraints();
		gbc_stop.anchor = GridBagConstraints.SOUTH;
		gbc_stop.insets = new Insets(0, 0, 0, 5);
		gbc_stop.gridx = 6;
		gbc_stop.gridy = 17;
		frmFynboAmbientInterface.getContentPane().add(stop, gbc_stop);

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				start.setEnabled(false);
				stop.setEnabled(true);
				String[] wallpapers = { sunBox.getText(), thunderBox.getText(), rainBox.getText(), windBox.getText(),
						snowBox.getText(), cloudBox.getText(), otherBox.getText() };
				String[] audios = {sunSoundBox.getText(), thunderSoundBox.getText(), rainSoundBox.getText(), windSoundBox.getText(), snowSoundBox.getText(), cloudSoundBox.getText(), otherSoundBox.getText()};
				urlMod = new URLModule(getURL(), convertToMillis(getUpdate(), getUnit()), wallpapers, audios);
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
			unitCombo.setSelectedIndex(1);
			return 10;
		}
	}

	/**
	 * Gets the units that the user inputs from the GUI
	 * 
	 * @return the integer representation of the time unit
	 */
	public int getUnit() {
		String temp = unitCombo.getSelectedItem().toString();
		if (temp.equalsIgnoreCase("seconds")) {
			return 1;
		} else if (temp.equalsIgnoreCase("minutes")) {
			return 2;
		} else {
			return 3;
		}
	}

	/**
	 * Shows the given exception in a new window.
	 * 
	 * @param ex
	 *            -The exception in string form.
	 */
	public static void showException(Exception ex) {
		JFrame exceptionWindow = new JFrame("Exception");
		exceptionWindow.setSize(500, 200);
		exceptionWindow.setLocationRelativeTo(null);
		JLabel exception = new JLabel(ex.toString(), JLabel.CENTER);
		exceptionWindow.getContentPane().add(exception);
		exceptionWindow.setVisible(true);
		exceptionWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Shows the given message in a new window.
	 * 
	 * @param msg
	 *            -The message in string form.
	 */
	public static void showMessage(String msg) {
		JFrame exceptionWindow = new JFrame("Message");
		exceptionWindow.setSize(500, 200);
		exceptionWindow.setLocationRelativeTo(null);
		JLabel message = new JLabel(msg, JLabel.CENTER);
		exceptionWindow.getContentPane().add(message);
		exceptionWindow.setVisible(true);
	}

	/**
	 * Gets the file (using JfileChooser) needed for input.
	 * 
	 * @return The file chosen -null if the user presses cancel
	 * @throws FileOperationCancelledException
	 *             File operation cancelled.
	 */

	public File getFile(String title) throws FileOperationCancelledException {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(title);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int response = fc.showDialog(frmFynboAmbientInterface, "use");
		if (response == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		} else {
			throw new FileOperationCancelledException("File operation cancelled");
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

	/**
	 * Converts the given seconds to milliseconds
	 * 
	 * @param sec
	 *            the seconds to convert
	 * @return the milliseconds as a result of conversion
	 */
	private static long convertSecToMillis(long sec) {
		return sec * 1000;
	}

	/**
	 * Converts the given time in minutes to milliseconds
	 * 
	 * @param min
	 *            the minutes the convert to milliseconds
	 * @return the milliseconds as a result of conversion
	 */
	private static long convertMinToMillis(long min) {
		return convertSecToMillis(min * 60);
	}

	/**
	 * Converts the given time in hours to milliseconds
	 * 
	 * @param h
	 *            the hours to convert to milliseconds
	 * @return the milliseconds as a result of the conversion
	 */
	private static long convertHourToMillis(long h) {
		return convertMinToMillis(h * 60);
	}

}
