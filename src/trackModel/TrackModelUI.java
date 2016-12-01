package trackModel;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import trackModel.TrackBlock.BlockStatus;

import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Random;

public class TrackModelUI {

	private JFrame frmTrackModelGui;
	private JLabel textField_4;
	private JLabel textField_5;
	private JLabel textField_6;
	private JLabel textField_7;
	private TrackBlock block = new TrackBlock();
	private JTextField textField_2;
	private JTextField textField;
	private JPasswordField passwordField;
	private TrackModel opps = new TrackModel();

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
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		frmTrackModelGui = new JFrame();
		frmTrackModelGui.setIconImage(Toolkit.getDefaultToolkit().getImage(TrackModelUI.class.getResource("/shared/TTE.png")));
		frmTrackModelGui.setFont(new Font("Arial", Font.PLAIN, 14));
		frmTrackModelGui.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frmTrackModelGui.setTitle("Track Model");
		frmTrackModelGui.setBounds(100, 100, 500, 362);
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

		JLabel label_5 = new JLabel("Grade:");
		label_5.setFont(new Font("Arial", Font.PLAIN, 11));
		label_5.setBounds(203, 14, 48, 14);
		overview.add(label_5);

		textField_4 = new JLabel("-----");
		textField_4.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_4.setBounds(261, 14, 65, 20);
		overview.add(textField_4);

		JLabel lblCumulativeElevation = new JLabel("Elevation total:");
		lblCumulativeElevation.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCumulativeElevation.setBounds(317, 14, 77, 14);
		overview.add(lblCumulativeElevation);

		textField_5 = new JLabel("-----");
		textField_5.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_5.setBounds(404, 11, 65, 20);
		overview.add(textField_5);

		textField_6 = new JLabel("-----");
		textField_6.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_6.setBounds(404, 44, 65, 20);
		overview.add(textField_6);

		JLabel label_7 = new JLabel("Speed Limit:");
		label_7.setFont(new Font("Arial", Font.PLAIN, 11));
		label_7.setBounds(317, 46, 77, 14);
		overview.add(label_7);

		textField_7 = new JLabel("-----");
		textField_7.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_7.setBounds(261, 44, 65, 20);
		overview.add(textField_7);

		JLabel label_8 = new JLabel("Elevation:");
		label_8.setFont(new Font("Arial", Font.PLAIN, 11));
		label_8.setBounds(203, 47, 63, 14);
		overview.add(label_8);

		JComboBox<Integer> blockComboBoxView = new JComboBox<Integer>();
		blockComboBoxView.setFont(new Font("Arial", Font.PLAIN, 11));
		blockComboBoxView.setBounds(138, 42, 55, 20);
		overview.add(blockComboBoxView);
		for (int i = 1; i < 250; i++) {
			blockComboBoxView.addItem(i);
		}

		JLabel lblSelectBlockTo_1 = new JLabel("Select Block To View:");
		lblSelectBlockTo_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSelectBlockTo_1.setBounds(10, 47, 118, 14);
		overview.add(lblSelectBlockTo_1);

		JLabel lblBlockSize = new JLabel("Block Size:");
		lblBlockSize.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBlockSize.setBounds(10, 87, 90, 14);
		overview.add(lblBlockSize);

		JLabel lblNewLabel_2 = new JLabel("-----");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(138, 86, 113, 14);
		overview.add(lblNewLabel_2);

		JLabel lblBlockOccupied = new JLabel("Block Status:");
		lblBlockOccupied.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBlockOccupied.setBounds(10, 111, 90, 14);
		overview.add(lblBlockOccupied);

		JLabel lblFalse = new JLabel("-----");
		lblFalse.setFont(new Font("Arial", Font.PLAIN, 11));
		lblFalse.setBounds(138, 111, 113, 14);
		overview.add(lblFalse);

		JLabel lblRailCrossing = new JLabel("Rail Crossing:");
		lblRailCrossing.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRailCrossing.setBounds(10, 136, 90, 14);
		overview.add(lblRailCrossing);

		JLabel label_3 = new JLabel("-----");
		label_3.setFont(new Font("Arial", Font.PLAIN, 11));
		label_3.setBounds(138, 136, 113, 14);
		overview.add(label_3);

		JLabel lblStationBlock = new JLabel("Station Block:");
		lblStationBlock.setFont(new Font("Arial", Font.PLAIN, 11));
		lblStationBlock.setBounds(10, 161, 90, 14);
		overview.add(lblStationBlock);

		JLabel label_9 = new JLabel("-----");
		label_9.setFont(new Font("Arial", Font.PLAIN, 11));
		label_9.setBounds(138, 161, 113, 14);
		overview.add(label_9);

		JLabel lblCurrentSignal = new JLabel("Current Signal:");
		lblCurrentSignal.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCurrentSignal.setBounds(10, 186, 90, 14);
		overview.add(lblCurrentSignal);

		JLabel lblSwitchBlock = new JLabel("Switch Block:");
		lblSwitchBlock.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSwitchBlock.setBounds(10, 211, 90, 14);
		overview.add(lblSwitchBlock);

		JLabel lblHeaterStatus = new JLabel("Heater Status:");
		lblHeaterStatus.setFont(new Font("Arial", Font.PLAIN, 11));
		lblHeaterStatus.setBounds(268, 86, 126, 23);
		overview.add(lblHeaterStatus);

		JLabel lblOn = new JLabel("-----");
		lblOn.setFont(new Font("Arial", Font.PLAIN, 11));
		lblOn.setBounds(404, 89, 65, 14);
		overview.add(lblOn);

		JLabel lblCurrentTemp = new JLabel("Current Temp:");
		lblCurrentTemp.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCurrentTemp.setBounds(268, 111, 126, 23);
		overview.add(lblCurrentTemp);

		JLabel lblF = new JLabel("-----");
		lblF.setFont(new Font("Arial", Font.PLAIN, 11));
		lblF.setBounds(404, 114, 65, 14);
		overview.add(lblF);

		JLabel lblArrowDirection = new JLabel("Arrow Direction:");
		lblArrowDirection.setFont(new Font("Arial", Font.PLAIN, 11));
		lblArrowDirection.setBounds(268, 136, 126, 23);
		overview.add(lblArrowDirection);

		JLabel lblHead = new JLabel("-----");
		lblHead.setFont(new Font("Arial", Font.PLAIN, 11));
		lblHead.setBounds(404, 139, 65, 14);
		overview.add(lblHead);

		JLabel lblA = new JLabel("-----");
		lblA.setFont(new Font("Arial", Font.PLAIN, 11));
		lblA.setBounds(404, 164, 65, 14);
		overview.add(lblA);

		JLabel lblTrackSection = new JLabel("Track Section:");
		lblTrackSection.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTrackSection.setBounds(268, 161, 126, 23);
		overview.add(lblTrackSection);

		JLabel lblNumberOfPassengers = new JLabel("Number of Passengers:");
		lblNumberOfPassengers.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNumberOfPassengers.setBounds(268, 186, 126, 23);
		overview.add(lblNumberOfPassengers);

		JLabel lblSwitchPosition = new JLabel("Switch Position:");
		lblSwitchPosition.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSwitchPosition.setBounds(268, 211, 126, 23);
		overview.add(lblSwitchPosition);

		JLabel lblNotAtSwitch = new JLabel("-----");
		lblNotAtSwitch.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNotAtSwitch.setBounds(404, 214, 65, 14);
		overview.add(lblNotAtSwitch);

		JLabel label_30 = new JLabel("-----");
		label_30.setFont(new Font("Arial", Font.PLAIN, 11));
		label_30.setBounds(404, 189, 65, 14);
		overview.add(label_30);

		JPanel config = new JPanel();
		config.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("Config Tool", null, config, null);
		config.setLayout(null);

		JLabel lblNewLabel = new JLabel("Select Line To Modify:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 10, 129, 14);
		config.add(lblNewLabel);

		JComboBox<String> lineComboBox = new JComboBox<String>();
		lineComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		lineComboBox.setBounds(138, 7, 55, 20);
		lineComboBox.addItem("Red");
		lineComboBox.addItem("Green");
		config.add(lineComboBox);

		JLabel lblSelectBlockTo = new JLabel("Select Block To Modify:");
		lblSelectBlockTo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSelectBlockTo.setBounds(10, 40, 129, 14);
		config.add(lblSelectBlockTo);

		JComboBox<Integer> railComboBox = new JComboBox<Integer>();
		railComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		railComboBox.setBounds(138, 37, 55, 20);
		for (int i = 1; i <= 250; i++) {
			railComboBox.addItem(i);
		}
		config.add(railComboBox);

		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGrade.setBounds(208, 10, 33, 14);
		config.add(lblGrade);

		JLabel lblElevation = new JLabel("Elevation:");
		lblElevation.setFont(new Font("Arial", Font.PLAIN, 11));
		lblElevation.setBounds(208, 43, 48, 14);
		config.add(lblElevation);

		JTextField lblNewLabel_1 = new JTextField("");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(261, 10, 65, 20);
		config.add(lblNewLabel_1);

		JTextField label = new JTextField("");
		label.setFont(new Font("Arial", Font.PLAIN, 11));
		label.setBounds(261, 40, 65, 20);
		config.add(label);

		JTextField label_1 = new JTextField("");
		label_1.setFont(new Font("Arial", Font.PLAIN, 11));
		label_1.setBounds(404, 40, 65, 20);
		config.add(label_1);

		JLabel lblSpeedLimit = new JLabel("Speed Limit:");
		lblSpeedLimit.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSpeedLimit.setBounds(333, 43, 67, 14);
		config.add(lblSpeedLimit);

		JLabel lblDirection = new JLabel("Elevation Total:");
		lblDirection.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDirection.setBounds(328, 10, 72, 14);
		config.add(lblDirection);

		JTextField label_4 = new JTextField("");
		label_4.setFont(new Font("Arial", Font.PLAIN, 11));
		label_4.setBounds(404, 7, 65, 20);
		config.add(label_4);

		JLabel label_2 = new JLabel("Block Size:");
		label_2.setFont(new Font("Arial", Font.PLAIN, 11));
		label_2.setBounds(10, 68, 90, 14);
		config.add(label_2);

		JLabel lblBlockStatus = new JLabel("Block Status:");
		lblBlockStatus.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBlockStatus.setBounds(10, 93, 90, 14);
		config.add(lblBlockStatus);

		JLabel label_12 = new JLabel("Rail Crossing:");
		label_12.setFont(new Font("Arial", Font.PLAIN, 11));
		label_12.setBounds(10, 118, 90, 14);
		config.add(label_12);

		JLabel label_13 = new JLabel("Station Block:");
		label_13.setFont(new Font("Arial", Font.PLAIN, 11));
		label_13.setBounds(10, 143, 90, 14);
		config.add(label_13);

		JLabel label_14 = new JLabel("Current Signal:");
		label_14.setFont(new Font("Arial", Font.PLAIN, 11));
		label_14.setBounds(10, 168, 90, 14);
		config.add(label_14);

		JLabel lblSwitchBlock_1 = new JLabel("Switch Block:");
		lblSwitchBlock_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSwitchBlock_1.setBounds(10, 193, 90, 14);
		config.add(lblSwitchBlock_1);

		JTextField comboBox_2 = new JTextField();
		comboBox_2.setFont(new Font("Arial", Font.PLAIN, 11));
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(138, 190, 103, 20);
		config.add(comboBox_2);

		JTextField comboBox_3 = new JTextField();
		comboBox_3.setFont(new Font("Arial", Font.PLAIN, 11));
		comboBox_3.setEditable(true);
		comboBox_3.setBounds(138, 165, 103, 20);
		config.add(comboBox_3);

		JTextField label_17 = new JTextField("False");
		label_17.setFont(new Font("Arial", Font.PLAIN, 11));
		label_17.setBounds(138, 118, 55, -34);
		config.add(label_17);

		JTextField label_18 = new JTextField("False");
		label_18.setFont(new Font("Arial", Font.PLAIN, 11));
		label_18.setBounds(138, 93, 55, -9);
		config.add(label_18);

		JTextField label_19 = new JTextField("");
		label_19.setFont(new Font("Arial", Font.PLAIN, 11));
		label_19.setBounds(138, 90, 103, 20);
		config.add(label_19);

		JLabel label_11 = new JLabel("Heater Status:");
		label_11.setFont(new Font("Arial", Font.PLAIN, 11));
		label_11.setBounds(249, 68, 90, 23);
		config.add(label_11);

		JLabel label_20 = new JLabel("Current Temp:");
		label_20.setFont(new Font("Arial", Font.PLAIN, 11));
		label_20.setBounds(249, 93, 112, 23);
		config.add(label_20);

		JLabel label_21 = new JLabel("Arrow Direction:");
		label_21.setFont(new Font("Arial", Font.PLAIN, 11));
		label_21.setBounds(249, 118, 126, 23);
		config.add(label_21);

		JLabel label_22 = new JLabel("Track Section:");
		label_22.setFont(new Font("Arial", Font.PLAIN, 11));
		label_22.setBounds(249, 143, 126, 23);
		config.add(label_22);

		JTextField label_23 = new JTextField("");
		label_23.setFont(new Font("Arial", Font.PLAIN, 11));
		label_23.setBounds(385, 143, 84, 23);
		config.add(label_23);

		JTextField label_24 = new JTextField("");
		label_24.setFont(new Font("Arial", Font.PLAIN, 11));
		label_24.setBounds(385, 118, 84, 23);
		config.add(label_24);

		JSpinner label_25 = new JSpinner();
		label_25.setFont(new Font("Arial", Font.PLAIN, 11));
		label_25.setBounds(385, 93, 84, 23);
		config.add(label_25);

		JSpinner label_27 = new JSpinner();
		label_27.setFont(new Font("Arial", Font.PLAIN, 11));
		label_27.setBounds(385, 191, 84, 26);
		config.add(label_27);

		JLabel label_28 = new JLabel("Number of Passengers:");
		label_28.setFont(new Font("Arial", Font.PLAIN, 11));
		label_28.setBounds(249, 168, 126, 23);
		config.add(label_28);

		JSpinner label_29 = new JSpinner();
		label_29.setFont(new Font("Arial", Font.PLAIN, 11));
		label_29.setBounds(385, 168, 84, 22);
		config.add(label_29);

		JLabel label_31 = new JLabel("Switch Position:");
		label_31.setFont(new Font("Arial", Font.PLAIN, 11));
		label_31.setBounds(249, 193, 126, 23);
		config.add(label_31);

		JLabel label_6 = new JLabel("Occupied By:");
		label_6.setFont(new Font("Arial", Font.PLAIN, 11));
		label_6.setBounds(8, 218, 90, 14);
		config.add(label_6);

		JLabel label_36 = new JLabel("Underground:");
		label_36.setFont(new Font("Arial", Font.PLAIN, 11));
		label_36.setBounds(250, 219, 90, 14);
		config.add(label_36);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_2.setEditable(true);
		textField_2.setBounds(138, 215, 103, 20);
		config.add(textField_2);

		JCheckBox chckbxNewCheckBox = new JCheckBox("TRUE");
		chckbxNewCheckBox.setBounds(385, 219, 77, 23);
		config.add(chckbxNewCheckBox);

		JCheckBox chckbxOn = new JCheckBox("ON");
		chckbxOn.setEnabled(false);
		chckbxOn.setBounds(385, 67, 65, 23);
		config.add(chckbxOn);

		JTextField spinner = new JTextField();
		spinner.setFont(new Font("Arial", Font.PLAIN, 11));
		spinner.setBounds(138, 65, 103, 22);
		config.add(spinner);

		JPanel failurePanel = new JPanel();
		tabbedPane.addTab("Murphy's Failures", null, failurePanel, null);

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

		JButton powerFailure = new JButton("Cut Power");
		powerFailure.setFont(new Font("Arial", Font.PLAIN, 11));
		powerFailure.setBounds(172, 0, 159, 307);
		powerFailure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				opps.cutRail();
			}
		});
		failurePanel.add(powerFailure);

		JButton circuitFailure = new JButton("Break Circuit");
		circuitFailure.setBounds(335, 0, 159, 307);
		circuitFailure.setFont(new Font("Arial", Font.PLAIN, 11));
		circuitFailure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opps.breakRailCircuit();
			}
		});
		failurePanel.add(circuitFailure);

		JLabel label_33 = new JLabel("-----");
		label_33.setFont(new Font("Arial", Font.PLAIN, 11));
		label_33.setBounds(138, 186, 113, 14);
		overview.add(label_33);

		JLabel label_32 = new JLabel("-----");
		label_32.setFont(new Font("Arial", Font.PLAIN, 11));
		label_32.setBounds(138, 211, 113, 14);
		overview.add(label_32);

		JLabel lblOccupiedBy = new JLabel("Occupied By:");
		lblOccupiedBy.setFont(new Font("Arial", Font.PLAIN, 11));
		lblOccupiedBy.setBounds(10, 236, 90, 14);
		overview.add(lblOccupiedBy);

		JLabel label_15 = new JLabel("-----");
		label_15.setFont(new Font("Arial", Font.PLAIN, 11));
		label_15.setBounds(138, 236, 113, 14);
		overview.add(label_15);

		JLabel lblUnderground = new JLabel("Underground:");
		lblUnderground.setFont(new Font("Arial", Font.PLAIN, 11));
		lblUnderground.setBounds(269, 236, 90, 14);
		overview.add(lblUnderground);

		JLabel label_34 = new JLabel("-----");
		label_34.setFont(new Font("Arial", Font.PLAIN, 11));
		label_34.setBounds(404, 239, 72, 14);
		overview.add(label_34);

		JCheckBox checkBox = new JCheckBox("TRUE");
		checkBox.setBounds(138, 114, 77, 23);
		config.add(checkBox);

		JTextField checkBox_1 = new JTextField("");
		checkBox_1.setBounds(138, 139, 103, 23);
		config.add(checkBox_1);

		JLabel lblTrainId = new JLabel("Train ID:");
		lblTrainId.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTrainId.setBounds(10, 258, 90, 14);
		overview.add(lblTrainId);
		
		JLabel lblAuthority = new JLabel("Authority:");
		lblAuthority.setFont(new Font("Arial", Font.PLAIN, 11));
		lblAuthority.setBounds(269, 258, 90, 14);
		overview.add(lblAuthority);
		
		JLabel label_26 = new JLabel("-----");
		label_26.setFont(new Font("Arial", Font.PLAIN, 11));
		label_26.setBounds(138, 258, 113, 14);
		overview.add(label_26);
		
		JLabel label_35 = new JLabel("-----");
		label_35.setFont(new Font("Arial", Font.PLAIN, 11));
		label_35.setBounds(404, 257, 72, 14);
		overview.add(label_35);
		
		JButton getData = new JButton("Get Data");
		getData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				block = opps.getBlock(lineComboBoxView.getSelectedItem().toString(),
							(Integer) blockComboBoxView.getSelectedItem());

				lblNewLabel_2.setText(String.valueOf(block.blockLength) + " ft");
				textField_4.setText(String.valueOf(block.blockGrade) + " \u00b0");
				textField_7.setText(String.valueOf(block.elevation) + " ft");
				textField_5.setText(String.valueOf(block.cumualativeElevation) + " ft");
				textField_6.setText(String.valueOf(block.speedLimit) + " mph");
				label_26.setText(String.valueOf(block.trainID));
				label_35.setText(String.valueOf(block.authority));
				lblF.setText(String.valueOf(block.temp) + " \u00b0F");
				lblHead.setText(block.arrowDirection);
				lblA.setText(block.section);
				label_30.setText(String.valueOf(block.numPass));

				if (block.occupied.equals(""))
					label_15.setText("OPEN BLOCK");
				else
					label_15.setText(block.occupied);

				if (block.temp < 32)
					lblOn.setText("ON");
				else
					lblOn.setText("OFF");

				if (block.infrastructure.contains("STATION"))
					label_9.setText(block.infrastructure.substring(9));
				else
					label_9.setText("FALSE");

				if (block.infrastructure.contains("RAILWAY CROSSING"))
					label_3.setText("TRUE");
				else
					label_3.setText("FALSE");

				if (block.switchBlock.getID() != "") {
					label_32.setText(block.switchBlock.getID());
					lblNotAtSwitch.setText(String.valueOf(block.switchBlock.getPosition()));
				} else {
					label_32.setText("FALSE");
					lblNotAtSwitch.setText("N/A");
				}

				if (block.infrastructure.contains("UNDERGROUND"))
					label_34.setText("TRUE");
				else
					label_34.setText("FALSE");

				if (!block.status.equals(""))
					lblFalse.setText(block.status.name());
				else
					lblFalse.setText("OPEN");

				if (block.occupied.equals(""))
					label_33.setText("GREEN");
				else
					label_33.setText("RED");

			}
		});
		getData.setFont(new Font("Arial", Font.PLAIN, 11));
		getData.setBounds(6, 276, 488, 31);
		overview.add(getData);
		

		// Button to get live data for the config panel
		JButton btnCheckLive = new JButton("Check Live");
		btnCheckLive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				block = opps.getBlock(lineComboBox.getSelectedItem().toString(),
						(Integer) railComboBox.getSelectedItem());

				spinner.setText(String.valueOf(block.blockLength));
				lblNewLabel_1.setText(String.valueOf(block.blockGrade));
				label.setText(String.valueOf(block.elevation));
				label_4.setText(String.valueOf(block.cumualativeElevation));
				label_1.setText(String.valueOf(block.speedLimit));
				label_25.setValue(block.temp);
				label_24.setText(block.arrowDirection);
				label_23.setText(block.section);
				label_29.setValue(block.numPass);



				textField_2.setText(String.valueOf(block.trainID));

				if (block.temp < 32)
					chckbxOn.setSelected(true);
				else
					chckbxOn.setSelected(false);

				if (block.infrastructure.contains("STATION"))
					checkBox_1.setText(block.infrastructure.substring(9));
				else
					checkBox_1.setText("");

				if (block.infrastructure.contains("RAILWAY CROSSING"))
					checkBox.setSelected(true);
				else
					checkBox.setSelected(false);

				if (block.switchBlock.getID() != "") {
					comboBox_2.setText(block.switchBlock.getID());
					label_27.setValue(Integer.parseInt(block.switchBlock.getPosition()));
				} else {
					comboBox_2.setText("");
					label_27.setValue(0);
				}

				if (block.infrastructure.contains("UNDERGROUND"))
					chckbxNewCheckBox.setSelected(true);
				else
					chckbxNewCheckBox.setSelected(false);

				if (!block.status.equals(""))
					label_19.setText(block.status.name());
				else
					label_19.setText("");

				if (block.occupied.equals(""))
					comboBox_3.setText("GREEN");
				else
					comboBox_3.setText("RED");

			}
		});
		btnCheckLive.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCheckLive.setBounds(10, 244, 246, 63);
		config.add(btnCheckLive);

		// SAVE BUTTON FUNCTIONALITY
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveOpperation(lineComboBox, railComboBox, lblNewLabel_1, label, label_1, label_4, comboBox_2, label_19,
						label_23, label_24, label_25, label_27, label_29, chckbxNewCheckBox, spinner, checkBox,
						checkBox_1);

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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						db.resetDB();
					} catch (SQLException | IOException e) {
						// TODO Auto-generated catch block
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



	}

	private void saveOpperation(JComboBox<String> lineComboBox, JComboBox<Integer> railComboBox,
			JTextField lblNewLabel_1, JTextField label, JTextField label_1, JTextField label_4, JTextField comboBox_2,
			JTextField label_19, JTextField label_23, JTextField label_24, JSpinner label_25, JSpinner label_27,
			JSpinner label_29, JCheckBox chckbxNewCheckBox, JTextField spinner, JCheckBox checkBox,
			JTextField checkBox_1) {
		TrackModel db = new TrackModel();
		TrackBlock theBlock = db.getBlock((String) lineComboBox.getSelectedItem(), (Integer) railComboBox.getSelectedItem());
		
		theBlock.line = (String) lineComboBox.getSelectedItem();
		theBlock.section = label_23.getText();
		theBlock.blockNumber = (Integer) railComboBox.getSelectedItem();
		theBlock.blockLength = Double.parseDouble(spinner.getText());
		theBlock.blockGrade = Double.parseDouble(lblNewLabel_1.getText());
		theBlock.speedLimit = Double.parseDouble(label_1.getText());
		if (!checkBox_1.getText().equals("") && !checkBox_1.getText().equals("FALSE"))
			theBlock.infrastructure += "STATION; " + checkBox_1.getText() + " ";
		if (chckbxNewCheckBox.isSelected())
			theBlock.infrastructure += "UNDERGROUND ";
		if (checkBox.isSelected())
			theBlock.infrastructure += "RAILWAY CROSSING ";
		if (!comboBox_2.getText().equals("") && !comboBox_2.getText().equals("FALSE")) {
			theBlock.infrastructure += "SWITCH ";
			theBlock.switchBlock.id = comboBox_2.getText();
		}

		theBlock.elevation = Double.parseDouble(label.getText());
		theBlock.cumualativeElevation = Double.parseDouble(label_4.getText());
		theBlock.switchBlock.position = label_27.getValue().toString();
		theBlock.arrowDirection = label_24.getText();
		theBlock.numPass = (int) label_29.getValue();
		theBlock.temp = (int) label_25.getValue();
		theBlock.status = BlockStatus.valueOf(label_19.getText());
		theBlock.trainID = Integer.parseInt(textField_2.getText());
		if(theBlock.trainID >= 0)
			theBlock.status = BlockStatus.OCCUPIED;

		opps.setBlock(theBlock);
	}
}
