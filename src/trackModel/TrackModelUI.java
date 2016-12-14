package trackModel;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.lang.Object;
import trackModel.TrackBlock.BlockStatus;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;

public class TrackModelUI {

	private JFrame frmTrackModelGui;
	private JLabel gradeDispOverview;
	private JLabel elevationDispOverview;
	private JLabel speedlimitDispOverview;
	private JLabel elevation2DispOverview;
	private TrackBlock block = new TrackBlock();
	private JTextField occupiedConfigInput;
	private JTextField textField;
	private JPasswordField passwordField;
	private TrackModel opps = new TrackModel();
	private UserDBInteraction userDB = null;
	

	/**
	 * Launch the application.
	 */	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrackModelUI window = new TrackModelUI();
					window.frmTrackModelGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TrackModelUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		}
		
		//***************************************************************************************************************************
		//All code for the construction of the Track Model UI
		//In here there is some functionality for populating the UI as well as testing the different components of the DB
		//This file is rather large and Java does not have #region functionality similar to c# my apologies 
		//***************************************************************************************************************************
		frmTrackModelGui = new JFrame();
		frmTrackModelGui.setIconImage(Toolkit.getDefaultToolkit().getImage(TrackModelUI.class.getResource("/shared/TTE.png")));
		frmTrackModelGui.setFont(new Font("Arial", Font.PLAIN, 14));
		frmTrackModelGui.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frmTrackModelGui.setTitle("Track Model");
		frmTrackModelGui.setBounds(100, 100, 515, 385);
		frmTrackModelGui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final JFrame parent = new JFrame();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 11));
		frmTrackModelGui.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel overview = new JPanel();
		overview.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Overview", null, overview, null);
		overview.setLayout(null);

		JLabel lblSelectLineTo = new JLabel("Select Line To View:");
		lblSelectLineTo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSelectLineTo.setBounds(10, 14, 118, 14);
		overview.add(lblSelectLineTo);

		JComboBox<String> lineComboBoxView = new JComboBox<String>();
		lineComboBoxView.setFont(new Font("Arial", Font.PLAIN, 11));
		lineComboBoxView.setBounds(138, 11, 55, 20);
		overview.add(lineComboBoxView);
		lineComboBoxView.addItem("Red");
		lineComboBoxView.addItem("Green");

		JLabel gradeOverview = new JLabel("Grade:");
		gradeOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		gradeOverview.setBounds(203, 14, 48, 14);
		overview.add(gradeOverview);

		gradeDispOverview = new JLabel("-----");
		gradeDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		gradeDispOverview.setBounds(261, 14, 65, 20);
		overview.add(gradeDispOverview);

		JLabel elevationOverview = new JLabel("Elevation total:");
		elevationOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		elevationOverview.setBounds(317, 14, 77, 14);
		overview.add(elevationOverview);

		elevationDispOverview = new JLabel("-----");
		elevationDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		elevationDispOverview.setBounds(404, 11, 65, 20);
		overview.add(elevationDispOverview);

		speedlimitDispOverview = new JLabel("-----");
		speedlimitDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		speedlimitDispOverview.setBounds(404, 44, 65, 20);
		overview.add(speedlimitDispOverview);

		JLabel speedlimitOverview = new JLabel("Speed Limit:");
		speedlimitOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		speedlimitOverview.setBounds(317, 46, 77, 14);
		overview.add(speedlimitOverview);

		elevation2DispOverview = new JLabel("-----");
		elevation2DispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		elevation2DispOverview.setBounds(261, 44, 65, 20);
		overview.add(elevation2DispOverview);

		JLabel elevation2Overview = new JLabel("Elevation:");
		elevation2Overview.setFont(new Font("Arial", Font.PLAIN, 11));
		elevation2Overview.setBounds(203, 47, 63, 14);
		overview.add(elevation2Overview);

		JComboBox<Integer> blockComboBoxView = new JComboBox<Integer>();
		blockComboBoxView.setFont(new Font("Arial", Font.PLAIN, 11));
		blockComboBoxView.setBounds(138, 42, 55, 20);
		overview.add(blockComboBoxView);
		for (int i = 1; i < 152; i++) {
			blockComboBoxView.addItem(i);
		}

		JLabel lblSelectBlockTo_1 = new JLabel("Select Block To View:");
		lblSelectBlockTo_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSelectBlockTo_1.setBounds(10, 47, 118, 14);
		overview.add(lblSelectBlockTo_1);

		JLabel blockSizeOverview = new JLabel("Block Size:");
		blockSizeOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		blockSizeOverview.setBounds(10, 87, 90, 14);
		overview.add(blockSizeOverview);

		JLabel sizeDispOverview = new JLabel("-----");
		sizeDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		sizeDispOverview.setBounds(138, 86, 113, 14);
		overview.add(sizeDispOverview);

		JLabel blockStatusOverview = new JLabel("Block Status:");
		blockStatusOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		blockStatusOverview.setBounds(10, 111, 90, 14);
		overview.add(blockStatusOverview);

		JLabel statusDispOverview = new JLabel("-----");
		statusDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		statusDispOverview.setBounds(138, 111, 113, 14);
		overview.add(statusDispOverview);

		JLabel railCrossingOverview = new JLabel("Rail Crossing:");
		railCrossingOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		railCrossingOverview.setBounds(10, 136, 90, 14);
		overview.add(railCrossingOverview);

		JLabel crossingDispOverview = new JLabel("-----");
		crossingDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		crossingDispOverview.setBounds(138, 136, 113, 14);
		overview.add(crossingDispOverview);

		JLabel stationOverview = new JLabel("Station Block:");
		stationOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		stationOverview.setBounds(10, 161, 90, 14);
		overview.add(stationOverview);

		JLabel stationDispOverview = new JLabel("-----");
		stationDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		stationDispOverview.setBounds(138, 161, 113, 14);
		overview.add(stationDispOverview);

		JLabel signalOverview = new JLabel("Current Signal:");
		signalOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		signalOverview.setBounds(10, 186, 90, 14);
		overview.add(signalOverview);

		JLabel switchOverview = new JLabel("Switch Block:");
		switchOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		switchOverview.setBounds(10, 211, 90, 14);
		overview.add(switchOverview);

		JLabel heaterOverview = new JLabel("Heater Status:");
		heaterOverview.setToolTipText("Heaters will turn on when system temp is < 32 F");
		heaterOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		heaterOverview.setBounds(268, 86, 126, 23);
		overview.add(heaterOverview);

		JLabel heaterDispOverview = new JLabel("-----");
		heaterDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		heaterDispOverview.setBounds(404, 89, 65, 14);
		overview.add(heaterDispOverview);

		JLabel tempOverview = new JLabel("Current Temp:");
		tempOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		tempOverview.setBounds(268, 111, 126, 23);
		overview.add(tempOverview);

		JLabel tempDispOverview = new JLabel("-----");
		tempDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		tempDispOverview.setBounds(404, 114, 65, 14);
		overview.add(tempDispOverview);

		JLabel directionOverview = new JLabel("Arrow Direction:");
		directionOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		directionOverview.setBounds(268, 136, 126, 23);
		overview.add(directionOverview);

		JLabel directionDispOverview = new JLabel("-----");
		directionDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		directionDispOverview.setBounds(404, 139, 65, 14);
		overview.add(directionDispOverview);

		JLabel sectionDispOverview = new JLabel("-----");
		sectionDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		sectionDispOverview.setBounds(404, 164, 65, 14);
		overview.add(sectionDispOverview);

		JLabel sectionOverview = new JLabel("Track Section:");
		sectionOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		sectionOverview.setBounds(268, 161, 126, 23);
		overview.add(sectionOverview);

		JLabel passengersOverview = new JLabel("Number of Passengers:");
		passengersOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		passengersOverview.setBounds(268, 186, 126, 23);
		overview.add(passengersOverview);

		JLabel switchPositionOverview = new JLabel("Switch Position:");
		switchPositionOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		switchPositionOverview.setBounds(268, 211, 126, 23);
		overview.add(switchPositionOverview);

		JLabel positionDispOverview = new JLabel("-----");
		positionDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		positionDispOverview.setBounds(404, 214, 65, 14);
		overview.add(positionDispOverview);

		JLabel passengersDispOverview = new JLabel("-----");
		passengersDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		passengersDispOverview.setBounds(404, 189, 65, 14);
		overview.add(passengersDispOverview);

		JPanel config = new JPanel();
		config.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("Config Tool", null, config, null);
		config.setLayout(null);

		JLabel lineConfig = new JLabel("Select Line To Modify:");
		lineConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		lineConfig.setBounds(10, 10, 129, 14);
		config.add(lineConfig);

		JComboBox<String> lineComboBox = new JComboBox<String>();
		lineComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		lineComboBox.setBounds(138, 7, 55, 20);
		lineComboBox.addItem("Red");
		lineComboBox.addItem("Green");
		config.add(lineComboBox);

		JLabel blockConfig = new JLabel("Select Block To Modify:");
		blockConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		blockConfig.setBounds(10, 40, 129, 14);
		config.add(blockConfig);

		JComboBox<Integer> railComboBox = new JComboBox<Integer>();
		railComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		railComboBox.setBounds(138, 37, 55, 20);
		for (int i = 1; i <= 152; i++) {
			railComboBox.addItem(i);
		}
		config.add(railComboBox);

		JLabel gradeConfig = new JLabel("Grade:");
		gradeConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		gradeConfig.setBounds(208, 10, 48, 14);
		config.add(gradeConfig);

		JLabel elevationConfig = new JLabel("Elevation:");
		elevationConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		elevationConfig.setBounds(208, 43, 48, 14);
		config.add(elevationConfig);

		JTextField gradeConfigInput = new JTextField("");
		gradeConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		gradeConfigInput.setBounds(261, 10, 65, 20);
		config.add(gradeConfigInput);

		JTextField elevationConfigInput = new JTextField("");
		elevationConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		elevationConfigInput.setBounds(261, 40, 65, 20);
		config.add(elevationConfigInput);

		JTextField speedlimitConfigInput = new JTextField("");
		speedlimitConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		speedlimitConfigInput.setBounds(404, 40, 65, 20);
		config.add(speedlimitConfigInput);

		JLabel speedlimitConfig = new JLabel("Speed Limit:");
		speedlimitConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		speedlimitConfig.setBounds(333, 43, 67, 14);
		config.add(speedlimitConfig);

		JLabel elevationTotalConfig = new JLabel("Elevation Total:");
		elevationTotalConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		elevationTotalConfig.setBounds(328, 10, 72, 14);
		config.add(elevationTotalConfig);

		JTextField elevationTotalConfigInput = new JTextField("");
		elevationTotalConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		elevationTotalConfigInput.setBounds(404, 7, 65, 20);
		config.add(elevationTotalConfigInput);

		JLabel sizeConfig = new JLabel("Block Size:");
		sizeConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		sizeConfig.setBounds(10, 68, 90, 14);
		config.add(sizeConfig);

		JLabel statusConfig = new JLabel("Block Status:");
		statusConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		statusConfig.setBounds(10, 93, 90, 14);
		config.add(statusConfig);

		JLabel crossingConfig = new JLabel("Rail Crossing:");
		crossingConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		crossingConfig.setBounds(10, 118, 90, 14);
		config.add(crossingConfig);

		JLabel stationConfig = new JLabel("Station Block:");
		stationConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		stationConfig.setBounds(10, 143, 90, 14);
		config.add(stationConfig);

		JLabel signalConfig = new JLabel("Current Signal:");
		signalConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		signalConfig.setBounds(10, 168, 90, 14);
		config.add(signalConfig);

		JLabel switchConfig = new JLabel("Switch Block:");
		switchConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		switchConfig.setBounds(10, 193, 90, 14);
		config.add(switchConfig);

		JTextField switchConfigInput = new JTextField();
		switchConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		switchConfigInput.setEditable(true);
		switchConfigInput.setBounds(138, 190, 103, 20);
		config.add(switchConfigInput);

		JTextField signalConfigInput = new JTextField();
		signalConfigInput.setEnabled(false);
		signalConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		signalConfigInput.setBounds(138, 165, 103, 20);
		config.add(signalConfigInput);

		JTextField label_17 = new JTextField("False");
		label_17.setFont(new Font("Arial", Font.PLAIN, 11));
		label_17.setBounds(138, 118, 55, -34);
		config.add(label_17);

		JTextField label_18 = new JTextField("False");
		label_18.setFont(new Font("Arial", Font.PLAIN, 11));
		label_18.setBounds(138, 93, 55, -9);
		config.add(label_18);

		JTextField statusConfigInput = new JTextField("");
		statusConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		statusConfigInput.setBounds(138, 90, 103, 20);
		config.add(statusConfigInput);

		JLabel heaterConfig = new JLabel("Heater Status:");
		heaterConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		heaterConfig.setBounds(249, 68, 90, 23);
		config.add(heaterConfig);

		JLabel tempConfig = new JLabel("Current Temp:");
		tempConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		tempConfig.setBounds(249, 93, 112, 23);
		config.add(tempConfig);

		JLabel directionConfig = new JLabel("Arrow Direction:");
		directionConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		directionConfig.setBounds(249, 118, 126, 23);
		config.add(directionConfig);

		JLabel sectionConfig = new JLabel("Track Section:");
		sectionConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		sectionConfig.setBounds(249, 143, 126, 23);
		config.add(sectionConfig);

		JTextField sectionConfigInput = new JTextField("");
		sectionConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		sectionConfigInput.setBounds(385, 143, 84, 23);
		config.add(sectionConfigInput);

		JTextField directionConfigInput = new JTextField("");
		directionConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		directionConfigInput.setBounds(385, 118, 84, 23);
		config.add(directionConfigInput);

		JSpinner tempConfigInput = new JSpinner();
		tempConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		tempConfigInput.setBounds(385, 93, 84, 23);
		config.add(tempConfigInput);

		JSpinner positionConfigInput = new JSpinner();
		positionConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		positionConfigInput.setBounds(385, 191, 84, 26);
		config.add(positionConfigInput);

		JLabel passengersConfig = new JLabel("Number of Passengers:");
		passengersConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		passengersConfig.setBounds(249, 168, 126, 23);
		config.add(passengersConfig);

		JSpinner passengerConfigInput = new JSpinner();
		passengerConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		passengerConfigInput.setBounds(385, 168, 84, 22);
		config.add(passengerConfigInput);

		JLabel positionConfig = new JLabel("Switch Position:");
		positionConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		positionConfig.setBounds(249, 193, 126, 23);
		config.add(positionConfig);

		JLabel occupiedConfig = new JLabel("Occupied By:");
		occupiedConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		occupiedConfig.setBounds(8, 218, 90, 14);
		config.add(occupiedConfig);

		JLabel undergroundConfig = new JLabel("Underground:");
		undergroundConfig.setFont(new Font("Arial", Font.PLAIN, 11));
		undergroundConfig.setBounds(250, 219, 90, 14);
		config.add(undergroundConfig);

		occupiedConfigInput = new JTextField();
		occupiedConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		occupiedConfigInput.setEditable(true);
		occupiedConfigInput.setBounds(138, 215, 103, 20);
		config.add(occupiedConfigInput);

		JCheckBox undergroundConfigInput = new JCheckBox("TRUE");
		undergroundConfigInput.setBounds(385, 219, 77, 23);
		config.add(undergroundConfigInput);

		JCheckBox heaterConfigInput = new JCheckBox("ON");
		heaterConfigInput.setEnabled(false);
		heaterConfigInput.setBounds(385, 67, 65, 23);
		config.add(heaterConfigInput);

		JTextField sizeConfigInput = new JTextField();
		sizeConfigInput.setFont(new Font("Arial", Font.PLAIN, 11));
		sizeConfigInput.setBounds(138, 65, 103, 22);
		config.add(sizeConfigInput);

		JPanel failurePanel = new JPanel();
		tabbedPane.addTab("Murphy's Failures", null, failurePanel, null);

		//*********************************************************************
		//This is the logic for breaking the rail which executes in Track Model
		//*********************************************************************
		JButton breakRail = new JButton("Break Rail");
		breakRail.setFont(new Font("Arial", Font.PLAIN, 11));
		breakRail.setBounds(1, 0, 159, 307);
		breakRail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				opps.breakRail();
			}
		});
		failurePanel.setLayout(null);
		failurePanel.add(breakRail);

		//*********************************************************************
		//This is the logic for cutting the rail which executes in Track Model
		//*********************************************************************
		JButton powerFailure = new JButton("Cut Power");
		powerFailure.setFont(new Font("Arial", Font.PLAIN, 11));
		powerFailure.setBounds(158, 0, 173, 307);
		powerFailure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				opps.cutRail();
			}
		});
		failurePanel.add(powerFailure);

		//************************************************************************
		//This is the logic for breaking the circuit which executes in Track Model
		//************************************************************************
		JButton circuitFailure = new JButton("Break Circuit");
		circuitFailure.setBounds(329, 0, 165, 307);
		circuitFailure.setFont(new Font("Arial", Font.PLAIN, 11));
		circuitFailure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opps.breakRailCircuit();
			}
		});
		failurePanel.add(circuitFailure);

		JLabel signalDispOverview = new JLabel("-----");
		signalDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		signalDispOverview.setBounds(138, 186, 113, 14);
		overview.add(signalDispOverview);

		JLabel switchDispOverview = new JLabel("-----");
		switchDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		switchDispOverview.setBounds(138, 211, 113, 14);
		overview.add(switchDispOverview);

		JLabel occupiedByOverview = new JLabel("Occupied By:");
		occupiedByOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		occupiedByOverview.setBounds(10, 236, 90, 14);
		overview.add(occupiedByOverview);

		JLabel occupiedDispOverview = new JLabel("-----");
		occupiedDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		occupiedDispOverview.setBounds(138, 236, 113, 14);
		overview.add(occupiedDispOverview);

		JLabel undergroundOverview = new JLabel("Underground:");
		undergroundOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		undergroundOverview.setBounds(269, 236, 90, 14);
		overview.add(undergroundOverview);

		JLabel undergroundDispOverview = new JLabel("-----");
		undergroundDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		undergroundDispOverview.setBounds(404, 239, 72, 14);
		overview.add(undergroundDispOverview);

		JCheckBox railCrossingConfigInput = new JCheckBox("TRUE");
		railCrossingConfigInput.setBounds(138, 114, 77, 23);
		config.add(railCrossingConfigInput);

		JTextField stationConfigInput = new JTextField("");
		stationConfigInput.setBounds(138, 139, 103, 23);
		config.add(stationConfigInput);

		JLabel trainIDOverview = new JLabel("Train ID:");
		trainIDOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		trainIDOverview.setBounds(10, 258, 90, 14);
		overview.add(trainIDOverview);
		
		JLabel authorityOverview = new JLabel("Authority:");
		authorityOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		authorityOverview.setBounds(269, 258, 90, 14);
		overview.add(authorityOverview);
		
		JLabel trainIDDispOverview = new JLabel("-----");
		trainIDDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		trainIDDispOverview.setBounds(138, 258, 113, 14);
		overview.add(trainIDDispOverview);
		
		JLabel authorityDispOverview = new JLabel("-----");
		authorityDispOverview.setFont(new Font("Arial", Font.PLAIN, 11));
		authorityDispOverview.setBounds(404, 257, 72, 14);
		overview.add(authorityDispOverview);

		//*****************************************************************************
		//This is the logic for populating the UI with returned information from the DB
		//*****************************************************************************
		JButton getData = new JButton("Get Data");
		getData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				block = opps.getBlock(lineComboBoxView.getSelectedItem().toString(),
							(Integer) blockComboBoxView.getSelectedItem());

				sizeDispOverview.setText(String.valueOf(block.blockLength) + " ft");
				gradeDispOverview.setText(String.valueOf(block.blockGrade) + " \u00b0");
				elevation2DispOverview.setText(String.valueOf(block.elevation) + " ft");
				elevationDispOverview.setText(String.valueOf(block.cumualativeElevation) + " ft");
				speedlimitDispOverview.setText(String.valueOf(block.speedLimit) + " mph");
				trainIDDispOverview.setText(String.valueOf(block.trainID));
				authorityDispOverview.setText(String.valueOf(block.authority));
				tempDispOverview.setText(String.valueOf(block.temp) + " \u00b0F");
				directionDispOverview.setText(block.arrowDirection);
				sectionDispOverview.setText(block.section);
				passengersDispOverview.setText(String.valueOf(block.numPass));

				if (block.occupied.equals(""))
					occupiedDispOverview.setText("OPEN BLOCK");
				else
					occupiedDispOverview.setText(block.occupied);

				if (block.temp < 32)
					heaterDispOverview.setText("ON");
				else
					heaterDispOverview.setText("OFF");

				if (block.infrastructure.contains("STATION"))
					stationDispOverview.setText(block.infrastructure.split(";")[1]);
				else
					stationDispOverview.setText("FALSE");

				if (block.infrastructure.contains("RAILWAY CROSSING"))
					crossingDispOverview.setText("TRUE");
				else
					crossingDispOverview.setText("FALSE");

				if (block.switchBlock.getID() != "") {
					switchDispOverview.setText(block.switchBlock.getID());
					positionDispOverview.setText(String.valueOf(block.switchBlock.getPosition()));
				} else {
					switchDispOverview.setText("FALSE");
					positionDispOverview.setText("N/A");
				}

				if (block.infrastructure.contains("UNDERGROUND"))
					undergroundDispOverview.setText("TRUE");
				else
					undergroundDispOverview.setText("FALSE");

				if (!block.status.equals(""))
					statusDispOverview.setText(block.status.name());
				else
					statusDispOverview.setText("OPEN");

				if (block.status.toString().equals("UNOCCUPIED"))
					signalDispOverview.setText("GREEN");
				else
					signalDispOverview.setText("RED");

			}
		});
		getData.setFont(new Font("Arial", Font.PLAIN, 11));
		getData.setBounds(6, 276, 488, 31);
		overview.add(getData);
		
		
		//**************************************************************************
		//This is the logic for checking live information in the database for the UI
		//**************************************************************************		
		JButton btnCheckLive = new JButton("Check Live");
		btnCheckLive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				block = opps.getBlock(lineComboBox.getSelectedItem().toString(),
						(Integer) railComboBox.getSelectedItem());

				sizeConfigInput.setText(String.valueOf(block.blockLength));
				gradeConfigInput.setText(String.valueOf(block.blockGrade));
				elevationConfigInput.setText(String.valueOf(block.elevation));
				elevationTotalConfigInput.setText(String.valueOf(block.cumualativeElevation));
				speedlimitConfigInput.setText(String.valueOf(block.speedLimit));
				tempConfigInput.setValue(block.temp);
				directionConfigInput.setText(block.arrowDirection);
				sectionConfigInput.setText(block.section);
				passengerConfigInput.setValue(block.numPass);



				occupiedConfigInput.setText(String.valueOf(block.trainID));

				if (block.temp < 32)
					heaterConfigInput.setSelected(true);
				else
					heaterConfigInput.setSelected(false);

				if (block.infrastructure.contains("STATION"))
					stationConfigInput.setText(block.infrastructure.split(";")[1]);
				else
					stationConfigInput.setText("");

				if (block.infrastructure.contains("RAILWAY CROSSING"))
					railCrossingConfigInput.setSelected(true);
				else
					railCrossingConfigInput.setSelected(false);

				if (block.switchBlock.getID() != "") {
					switchConfigInput.setText(block.switchBlock.getID());
					positionConfigInput.setValue(Integer.parseInt(block.switchBlock.getPosition()));
				} else {
					switchConfigInput.setText("");
					positionConfigInput.setValue(0);
				}

				if (block.infrastructure.contains("UNDERGROUND"))
					undergroundConfigInput.setSelected(true);
				else
					undergroundConfigInput.setSelected(false);

				if (!block.status.equals(""))
					statusConfigInput.setText(block.status.name());
				else
					statusConfigInput.setText("");

				if (block.status.toString().equals("UNOCCUPIED"))
					signalConfigInput.setText("GREEN");
				else
					signalConfigInput.setText("RED");

			}
		});
		btnCheckLive.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCheckLive.setBounds(10, 244, 246, 63);
		config.add(btnCheckLive);


		//**********************************************************
		//This is action the save button performs in the config tool 
		//**********************************************************
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveOpperation(lineComboBox, railComboBox, gradeConfigInput, elevationConfigInput, speedlimitConfigInput, elevationTotalConfigInput, switchConfigInput, statusConfigInput,
						sectionConfigInput, directionConfigInput, tempConfigInput, positionConfigInput, passengerConfigInput, undergroundConfigInput, sizeConfigInput, railCrossingConfigInput,
						stationConfigInput);

			}
		});
		btnSave.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSave.setBounds(260, 245, 234, 62);
		config.add(btnSave);

		JPanel adminControls = new JPanel();
		tabbedPane.addTab("Admin Controls", null, adminControls, null);
		adminControls.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(10, 70, 90, 28);
		adminControls.add(lblUserName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 117, 90, 29);
		adminControls.add(lblPassword);

		textField = new JTextField();
		textField.setBounds(110, 66, 140, 36);
		adminControls.add(textField);
		textField.setColumns(10);
		textField.setText("tteuser");

		passwordField = new JPasswordField();
		passwordField.setBounds(110, 113, 140, 36);
		passwordField.setText("ttesolutions");
		adminControls.add(passwordField);


		//****************************************************************************
		//This is the logic for reseting the database
		//If the username is not "tteuser" & pw is not "ttesolutions" this will not run
		//*****************************************************************************
		JButton resetDB = new JButton("Reset Database");
		resetDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (textField.getText().equals("tteuser")
						&& String.valueOf(passwordField.getPassword()).equals("ttesolutions")) {
					TrackDBInteraction db = null;
					try {
						db = new TrackDBInteraction();
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e) {
						e.printStackTrace();
					}
					try {
						db.resetDB();
					} catch (SQLException | IOException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(parent, "Database was reset!");
				}
				else{
					JOptionPane.showMessageDialog(parent, "INVALID USER!!!");
				}
			}

		});
		resetDB.setBounds(285, 6, 209, 301);
		adminControls.add(resetDB);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Testing Tool", null, panel, null);
		panel.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(6, 191, 488, 39);
		panel.add(progressBar);
		
		JCheckBox chckbxResetDatabase = new JCheckBox("Reset Database");
		chckbxResetDatabase.setEnabled(false);
		chckbxResetDatabase.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chckbxResetDatabase.setBounds(6, 6, 200, 25);
		panel.add(chckbxResetDatabase);
		
		JCheckBox chckbxGetTrackBlock = new JCheckBox("Get Track Block");
		chckbxGetTrackBlock.setEnabled(false);
		chckbxGetTrackBlock.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chckbxGetTrackBlock.setBounds(6, 43, 200, 25);
		panel.add(chckbxGetTrackBlock);
		
		JCheckBox chckbxGetTrackLine = new JCheckBox("Get Track Line ");
		chckbxGetTrackLine.setEnabled(false);
		chckbxGetTrackLine.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chckbxGetTrackLine.setBounds(6, 80, 200, 25);
		panel.add(chckbxGetTrackLine);
		
		JCheckBox chckbxSetTrackBlock = new JCheckBox("Set Track Block");
		chckbxSetTrackBlock.setEnabled(false);
		chckbxSetTrackBlock.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chckbxSetTrackBlock.setBounds(6, 117, 200, 25);
		panel.add(chckbxSetTrackBlock);
		
		JCheckBox chckbxBreakRail = new JCheckBox("Break Rail");
		chckbxBreakRail.setEnabled(false);
		chckbxBreakRail.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chckbxBreakRail.setBounds(6, 154, 200, 25);
		panel.add(chckbxBreakRail);
		
		JCheckBox chckbxCutPower = new JCheckBox("Cut Power");
		chckbxCutPower.setEnabled(false);
		chckbxCutPower.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chckbxCutPower.setBounds(294, 6, 200, 25);
		panel.add(chckbxCutPower);
		
		JCheckBox chckbxBreakRailCircuit = new JCheckBox("Break Rail Circuit");
		chckbxBreakRailCircuit.setEnabled(false);
		chckbxBreakRailCircuit.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chckbxBreakRailCircuit.setBounds(294, 43, 200, 25);
		panel.add(chckbxBreakRailCircuit);
		
		JCheckBox chckbxAddUser = new JCheckBox("Add User");
		chckbxAddUser.setEnabled(false);
		chckbxAddUser.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chckbxAddUser.setBounds(294, 80, 200, 25);
		panel.add(chckbxAddUser);
		
		JCheckBox chckbxVerifyUser = new JCheckBox("Verify User");
		chckbxVerifyUser.setEnabled(false);
		chckbxVerifyUser.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chckbxVerifyUser.setBounds(294, 117, 200, 25);
		panel.add(chckbxVerifyUser);
		
		JCheckBox chckbxAllTestsPassed = new JCheckBox("All Tests Passed");
		chckbxAllTestsPassed.setEnabled(false);
		chckbxAllTestsPassed.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chckbxAllTestsPassed.setBounds(294, 154, 200, 25);
		panel.add(chckbxAllTestsPassed);
		
		

		//*****************************************************************************
		//This is the logic for testing the database
		//This logic is included in this file due to the interaction it has with the UI
		//*****************************************************************************
		JButton runTests = new JButton("Run Tests");
		runTests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Finish Unit Testing Tool
				
				 Runnable runner = new Runnable()
				    {
				        public void run() {
				        	//Beginning of unit testing 
							boolean failure = false;
							progressBar.setMaximum(100);
							runTests.setEnabled(false);
							
							//Test 1: 
							//Reset the Database
							try {
								failure = !opps.resetDB();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							if(!failure){
								chckbxResetDatabase.setSelected(true);
								progressBar.setValue(10);
								panel.repaint();
							}
								
													
							//Test 2: 
							//Get Track Block Info
							try {
								opps.resetDB();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							TrackBlock temp = opps.getBlock("Red", 1);
							if(temp.speed != -1 && temp.authority != -1 && temp.blockLength != 164 && temp.cumualativeElevation != 2.69 && !temp.arrowDirection.equals("Head") && !temp.switchBlock.id.equals("Switch 6"))
								failure = true;
							else{
								chckbxGetTrackBlock.setSelected(true);
								progressBar.setValue(20);
								progressBar.repaint();				
							}
							
							
							//Test 3: 
							//Get Track Line Info 
							//This test checks both Red and Green lines
							try {
								opps.resetDB();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							TrackBlock[] tempArrayRed = opps.getBlock("Red");
							TrackBlock[] tempArrayGreen = opps.getBlock("Green");
							if(tempArrayRed.length != 77 && !tempArrayRed[1].equals(opps.getBlock("Red", 1)) && !tempArrayRed[10].equals(opps.getBlock("Red", 10))
									&& tempArrayGreen.length != 152 && !tempArrayGreen[1].equals(opps.getBlock("Green", 1)) && !tempArrayGreen[10].equals(opps.getBlock("Green", 10)))
								failure = true;
							else{
									chckbxGetTrackLine.setSelected(true);
									progressBar.setValue(30);
									panel.repaint();				
								}
								
							
							//Test 4:
							//Set Track Block Data 
							try {
								opps.resetDB();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							TrackBlock testGreen = new TrackBlock();
							testGreen = opps.getBlock("Green", 1);
							testGreen.authority = 666;
							testGreen.speed = 123;
							testGreen.arrowDirection = "TestGreen";
							testGreen.infrastructure = "TestGreen";
							testGreen.blockGrade = 12345;
							testGreen.destination = 123;
							testGreen.blockLength = 123;
							testGreen.cumualativeElevation = 555;
							testGreen.elevation = 2.5;
							testGreen.nextBlock = 54321;
							testGreen.numPass = 100;
							testGreen.section = "ZZ";
							testGreen.speed = 123.456;
							testGreen.speedLimit = 456;
							testGreen.temp = 72;
							testGreen.trainID = 1005;
							opps.setBlock(testGreen);
							
							TrackBlock testRed = new TrackBlock();
							testRed = opps.getBlock("Red", 1);
							testRed.authority = 666;
							testRed.speed = 123;
							testRed.arrowDirection = "TestGreen";
							testRed.infrastructure = "TestGreen";
							testRed.blockGrade = 12345;
							testRed.destination = 123;
							testRed.blockLength = 123;
							testRed.cumualativeElevation = 555;
							testRed.elevation = 2.5;
							testRed.nextBlock = 54321;
							testRed.numPass = 100;
							testRed.section = "ZZ";
							testRed.speed = 123.456;
							testRed.speedLimit = 456;
							testRed.temp = 72;
							testRed.trainID = 1005;
							opps.setBlock(testRed);
							if(!testRed.equals(opps.getBlock("Red", 1)) && !testGreen.equals(opps.getBlock("Green", 1)))
								failure = true;
							else{
								chckbxSetTrackBlock.setSelected(true);
								progressBar.setValue(40);
								panel.repaint();
							}
							

							
							//Test 5:
							//Break Rail Test
							try {
								opps.resetDB();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							opps.breakRail("Red",1);
							temp = opps.getBlock("Red", 1);
							if(temp.status != BlockStatus.BROKENRAIL)
								failure = true;
							else{
								chckbxBreakRail.setSelected(true);
								progressBar.setValue(50);
								panel.repaint();
							}
							
							
							//Test: 6
							//Cut Power Test
							try {
								opps.resetDB();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							opps.cutRail("Red",1);
							temp = opps.getBlock("Red", 1);
							if(temp.status != BlockStatus.CUTRAIL)
								failure = true;
							else{
								chckbxCutPower.setSelected(true);
								progressBar.setValue(60);
								panel.repaint();
							}
							
							
							//Test: 7
							//Break Rail Circuit Test
							try {
								opps.resetDB();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							opps.breakRailCircuit("Red",1);
							temp = opps.getBlock("Red", 1);
							if(temp.status != BlockStatus.BROKENCIRCUIT)
								failure = true;
							else{
								chckbxBreakRailCircuit.setSelected(true);
								progressBar.setValue(70);
								panel.repaint();
							}
							
							
							//Test: 8
							//Add User Test
							try {
								userDB = new UserDBInteraction();
								if(!userDB.addUser("Admin", "password")){
									chckbxAddUser.setSelected(true);
									progressBar.setValue(80);
									panel.repaint();
								}
								else
									failure=true;
							} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							
							
							//Test: 9
							//Verify User Test
							try {
								if(userDB.verifyUser("Admin", "password")){
									chckbxVerifyUser.setSelected(true);
									progressBar.setValue(90);
									panel.repaint();
								}
								else{
									failure=true;
								}
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							
							//Clean up
							//Final reset of the database to revert all changes made
							try {
								opps.resetDB();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							//Print results to the user 
							if (failure) {
								JOptionPane.showMessageDialog(parent, "Tests Failed!");
								runTests.setEnabled(true);
							} else {
								progressBar.setValue(100);
								chckbxAllTestsPassed.setSelected(true);
								JOptionPane.showMessageDialog(parent, "Tests Passed!");
								runTests.setEnabled(true);
							}
				        }
				    };
				    Thread t = new Thread(runner, "Code Executer");
				    t.start();				
			}
		});
		runTests.setBounds(6, 230, 488, 77);
		panel.add(runTests);
	}

	
	//*************************************************************************************
	//This method takes all of the UI input and converts it into a actual TrackBlock object
	//This object is then passed to the DB
	//*************************************************************************************
	private void saveOpperation(JComboBox<String> lineComboBox, JComboBox<Integer> railComboBox,
			JTextField lblNewLabel_1, JTextField label, JTextField label_1, JTextField label_4, JTextField comboBox_2,
			JTextField label_19, JTextField label_23, JTextField label_24, JSpinner label_25, JSpinner label_27,
			JSpinner label_29, JCheckBox chckbxNewCheckBox, JTextField spinner, JCheckBox checkBox,
			JTextField checkBox_1) {
		
		try{
		TrackModel db = new TrackModel();
		TrackBlock theBlock = db.getBlock((String) lineComboBox.getSelectedItem(), (Integer) railComboBox.getSelectedItem());
		
		theBlock.line = (String) lineComboBox.getSelectedItem();
		theBlock.section = label_23.getText();
		theBlock.blockNumber = (Integer) railComboBox.getSelectedItem();
		theBlock.blockLength = Double.parseDouble(spinner.getText());
		theBlock.blockGrade = Double.parseDouble(lblNewLabel_1.getText());
		theBlock.speedLimit = Double.parseDouble(label_1.getText());
		
		theBlock.infrastructure = "";
		
		if (!checkBox_1.getText().equals("") && !checkBox_1.getText().equals("FALSE"))
			theBlock.infrastructure += "STATION;" + checkBox_1.getText() + ";";
		if (chckbxNewCheckBox.isSelected())
			theBlock.infrastructure += "UNDERGROUND;";
		if (checkBox.isSelected())
			theBlock.infrastructure += "RAILWAY CROSSING;";
		if (!comboBox_2.getText().equals("") && !comboBox_2.getText().equals("FALSE")) {
			theBlock.infrastructure += "SWITCH;";
			theBlock.switchBlock.id = comboBox_2.getText();
		}
		else
		{
			theBlock.switchBlock.id = "";
		}

		theBlock.elevation = Double.parseDouble(label.getText());
		theBlock.cumualativeElevation = Double.parseDouble(label_4.getText());
		theBlock.switchBlock.position = label_27.getValue().toString();
		theBlock.arrowDirection = label_24.getText();
		theBlock.numPass = (int) label_29.getValue();
		theBlock.temp = (int) label_25.getValue();
		theBlock.status = BlockStatus.valueOf(label_19.getText());
		theBlock.trainID = Integer.parseInt(occupiedConfigInput.getText());
		if(theBlock.trainID > 0)
			theBlock.status = BlockStatus.OCCUPIED;

		opps.setBlock(theBlock);
		}
		//Check for empty strings if the user did not check live data first!
		catch(NumberFormatException n){
			JFrame parent = new JFrame();
			JOptionPane.showMessageDialog(parent,"Please click the Check Live button before saving to the database","TRACK MODEL ERROR", JOptionPane.ERROR_MESSAGE);	
		}
	}
}
