package trackModel;
import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.Font;
import java.awt.Toolkit;

public class trackModelUI {

	private JFrame frmTrackModelGui;
	private JLabel textField_4;
	private JLabel textField_5;
	private JLabel textField_6;
	private JLabel textField_7;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trackModelUI window = new trackModelUI();
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
	public trackModelUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrackModelGui = new JFrame();
		frmTrackModelGui.setIconImage(Toolkit.getDefaultToolkit().getImage(trackModelUI.class.getResource("/shared/TTE.png")));
		frmTrackModelGui.setFont(new Font("Arial", Font.PLAIN, 14));
		frmTrackModelGui.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frmTrackModelGui.setTitle("Track Model");
		frmTrackModelGui.setBounds(100, 100, 500, 350);
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
		
		JComboBox lineComboBoxView = new JComboBox();
		lineComboBoxView.setFont(new Font("Arial", Font.PLAIN, 11));
		lineComboBoxView.setBounds(138, 11, 55, 20);
		overview.add(lineComboBoxView);
		lineComboBoxView.addItem("Red");
		lineComboBoxView.addItem("Blue");

		
		JLabel label_5 = new JLabel("Grade:");
		label_5.setFont(new Font("Arial", Font.PLAIN, 11));
		label_5.setBounds(203, 14, 48, 14);
		overview.add(label_5);
		
		textField_4 = new JLabel("0.0 deg");
		textField_4.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_4.setBounds(261, 14, 65, 20);
		overview.add(textField_4);
		
		JLabel label_6 = new JLabel("Direction:");
		label_6.setFont(new Font("Arial", Font.PLAIN, 11));
		label_6.setBounds(317, 14, 65, 14);
		overview.add(label_6);
		
		textField_5 = new JLabel("0.0 mph");
		textField_5.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_5.setBounds(404, 11, 65, 20);
		overview.add(textField_5);
		
		textField_6 = new JLabel("0.0 mph");
		textField_6.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_6.setBounds(404, 44, 65, 20);
		overview.add(textField_6);
		
		JLabel label_7 = new JLabel("Speed Limit:");
		label_7.setFont(new Font("Arial", Font.PLAIN, 11));
		label_7.setBounds(317, 46, 77, 14);
		overview.add(label_7);
		
		textField_7 = new JLabel("0.0 ft");
		textField_7.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_7.setBounds(261, 44, 65, 20);
		overview.add(textField_7);
		
		JLabel label_8 = new JLabel("Elevation:");
		label_8.setFont(new Font("Arial", Font.PLAIN, 11));
		label_8.setBounds(203, 47, 63, 14);
		overview.add(label_8);
		
		JComboBox blockComboBoxView = new JComboBox();
		blockComboBoxView.setFont(new Font("Arial", Font.PLAIN, 11));
		blockComboBoxView.setBounds(138, 42, 55, 20);
		overview.add(blockComboBoxView);
		 for(int i=1; i<200; i++){
			 blockComboBoxView.addItem(i);
        }
		
		JLabel lblSelectBlockTo_1 = new JLabel("Select Block To View:");
		lblSelectBlockTo_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSelectBlockTo_1.setBounds(10, 47, 118, 14);
		overview.add(lblSelectBlockTo_1);
		
		JButton getData = new JButton("Get Data");
		getData.setFont(new Font("Arial", Font.PLAIN, 11));
		getData.setBounds(10, 246, 200, 23);
		overview.add(getData);
		
		JButton btnRefreshData = new JButton("Refresh Data");
		btnRefreshData.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRefreshData.setBounds(269, 246, 200, 23);
		overview.add(btnRefreshData);
		
		JLabel lblBlockSize = new JLabel("Block Size:");
		lblBlockSize.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBlockSize.setBounds(10, 86, 90, 14);
		overview.add(lblBlockSize);
		
		JLabel lblNewLabel_2 = new JLabel("0.0 ft");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(138, 86, 55, 14);
		overview.add(lblNewLabel_2);
		
		JLabel lblBlockOccupied = new JLabel("Block Occupied:");
		lblBlockOccupied.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBlockOccupied.setBounds(10, 111, 90, 14);
		overview.add(lblBlockOccupied);
		
		JLabel lblFalse = new JLabel("False");
		lblFalse.setFont(new Font("Arial", Font.PLAIN, 11));
		lblFalse.setBounds(138, 111, 55, 14);
		overview.add(lblFalse);
		
		JLabel lblRailCrossing = new JLabel("Rail Crossing:");
		lblRailCrossing.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRailCrossing.setBounds(10, 136, 90, 14);
		overview.add(lblRailCrossing);
		
		JLabel label_3 = new JLabel("False");
		label_3.setFont(new Font("Arial", Font.PLAIN, 11));
		label_3.setBounds(138, 136, 55, 14);
		overview.add(label_3);
		
		JLabel lblStationBlock = new JLabel("Station Block:");
		lblStationBlock.setFont(new Font("Arial", Font.PLAIN, 11));
		lblStationBlock.setBounds(10, 161, 90, 14);
		overview.add(lblStationBlock);
		
		JLabel label_9 = new JLabel("False");
		label_9.setFont(new Font("Arial", Font.PLAIN, 11));
		label_9.setBounds(138, 161, 55, 14);
		overview.add(label_9);
		
		JLabel lblCurrentSignal = new JLabel("Current Signal:");
		lblCurrentSignal.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCurrentSignal.setBounds(10, 186, 90, 14);
		overview.add(lblCurrentSignal);
		
		JLabel lblSwitchBlock = new JLabel("Switch Block:");
		lblSwitchBlock.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSwitchBlock.setBounds(10, 211, 90, 14);
		overview.add(lblSwitchBlock);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		comboBox.setBounds(138, 183, 55, 20);
		overview.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Arial", Font.PLAIN, 11));
		comboBox_1.setBounds(138, 208, 55, 20);
		overview.add(comboBox_1);
		
		JLabel lblHeaterStatus = new JLabel("Heater Status:");
		lblHeaterStatus.setFont(new Font("Arial", Font.PLAIN, 11));
		lblHeaterStatus.setBounds(268, 86, 126, 23);
		overview.add(lblHeaterStatus);
		
		JLabel lblOn = new JLabel("OFF");
		lblOn.setFont(new Font("Arial", Font.PLAIN, 11));
		lblOn.setBounds(404, 89, 65, 14);
		overview.add(lblOn);
		
		JLabel lblCurrentTemp = new JLabel("Current Temp:");
		lblCurrentTemp.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCurrentTemp.setBounds(268, 111, 126, 23);
		overview.add(lblCurrentTemp);
		
		JLabel lblF = new JLabel("70 F");
		lblF.setFont(new Font("Arial", Font.PLAIN, 11));
		lblF.setBounds(404, 114, 65, 14);
		overview.add(lblF);
		
		JLabel lblArrowDirection = new JLabel("Arrow Direction:");
		lblArrowDirection.setFont(new Font("Arial", Font.PLAIN, 11));
		lblArrowDirection.setBounds(268, 136, 126, 23);
		overview.add(lblArrowDirection);
		
		JLabel lblHead = new JLabel("HEAD");
		lblHead.setFont(new Font("Arial", Font.PLAIN, 11));
		lblHead.setBounds(404, 139, 65, 14);
		overview.add(lblHead);
		
		JLabel lblA = new JLabel("A");
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
		
		JLabel lblNotAtSwitch = new JLabel("N/A");
		lblNotAtSwitch.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNotAtSwitch.setBounds(404, 214, 65, 14);
		overview.add(lblNotAtSwitch);
		
		JLabel label_30 = new JLabel("50");
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
		
		JComboBox lineComboBox = new JComboBox();
		lineComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		lineComboBox.setBounds(138, 7, 55, 20);
		lineComboBox.addItem("Red");
		lineComboBox.addItem("Blue");
		config.add(lineComboBox);
		
		JLabel lblSelectBlockTo = new JLabel("Select Block To Modify:");
		lblSelectBlockTo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSelectBlockTo.setBounds(10, 43, 129, 14);
		config.add(lblSelectBlockTo);
		
		JComboBox railComboBox = new JComboBox();
		railComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		railComboBox.setBounds(138, 40, 55, 20);
		 for(int i=1; i<100; i++){
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
		
		JTextField lblNewLabel_1 = new JTextField("0.0");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(261, 10, 65, 20);
		config.add(lblNewLabel_1);
		
		JTextField label = new JTextField("0.0");
		label.setFont(new Font("Arial", Font.PLAIN, 11));
		label.setBounds(261, 40, 65, 20);
		config.add(label);
		
		JTextField label_1 = new JTextField("0.0");
		label_1.setFont(new Font("Arial", Font.PLAIN, 11));
		label_1.setBounds(404, 40, 65, 20);
		config.add(label_1);
		
		JLabel lblSpeedLimit = new JLabel("Speed Limit:");
		lblSpeedLimit.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSpeedLimit.setBounds(336, 43, 58, 14);
		config.add(lblSpeedLimit);
		
		JLabel lblDirection = new JLabel("Direction:");
		lblDirection.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDirection.setBounds(336, 10, 46, 14);
		config.add(lblDirection);
		
		JTextField label_4 = new JTextField("0.0");
		label_4.setFont(new Font("Arial", Font.PLAIN, 11));
		label_4.setBounds(404, 7, 65, 20);
		config.add(label_4);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSave.setBounds(10, 230, 231, 42);
		config.add(btnSave);
		
		JButton btnAddNew = new JButton("Add New Block");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//launch new gui 
				newBlockGUI.main(null);
			}
		});
		btnAddNew.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAddNew.setBounds(238, 230, 231, 42);
		config.add(btnAddNew);
		
		JLabel label_2 = new JLabel("Block Size:");
		label_2.setFont(new Font("Arial", Font.PLAIN, 11));
		label_2.setBounds(10, 68, 90, 14);
		config.add(label_2);
		
		JLabel label_10 = new JLabel("Block Occupied:");
		label_10.setFont(new Font("Arial", Font.PLAIN, 11));
		label_10.setBounds(10, 93, 90, 14);
		config.add(label_10);
		
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
		
		JLabel label_15 = new JLabel("Station Block:");
		label_15.setFont(new Font("Arial", Font.PLAIN, 11));
		label_15.setBounds(10, 193, 90, 14);
		config.add(label_15);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Arial", Font.PLAIN, 11));
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(138, 190, 55, 20);
		config.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setFont(new Font("Arial", Font.PLAIN, 11));
		comboBox_3.setEditable(true);
		comboBox_3.setBounds(138, 165, 55, 20);
		config.add(comboBox_3);
		
		JTextField label_16 = new JTextField("False");
		label_16.setFont(new Font("Arial", Font.PLAIN, 11));
		label_16.setBounds(138, 137, 55, 26);
		config.add(label_16);
		
		JTextField label_17 = new JTextField("False");
		label_17.setFont(new Font("Arial", Font.PLAIN, 11));
		label_17.setBounds(138, 118, 55, -34);
		config.add(label_17);
		
		JTextField label_18 = new JTextField("False");
		label_18.setFont(new Font("Arial", Font.PLAIN, 11));
		label_18.setBounds(138, 93, 55, -9);
		config.add(label_18);
		
		JTextField label_19 = new JTextField("0.0");
		label_19.setFont(new Font("Arial", Font.PLAIN, 11));
		label_19.setBounds(138, 90, 55, 20);
		config.add(label_19);
		
		JLabel label_11 = new JLabel("Heater Status:");
		label_11.setFont(new Font("Arial", Font.PLAIN, 11));
		label_11.setBounds(271, 71, 126, 23);
		config.add(label_11);
		
		JLabel label_20 = new JLabel("Current Temp:");
		label_20.setFont(new Font("Arial", Font.PLAIN, 11));
		label_20.setBounds(271, 96, 126, 23);
		config.add(label_20);
		
		JLabel label_21 = new JLabel("Arrow Direction:");
		label_21.setFont(new Font("Arial", Font.PLAIN, 11));
		label_21.setBounds(271, 121, 126, 23);
		config.add(label_21);
		
		JLabel label_22 = new JLabel("Track Section:");
		label_22.setFont(new Font("Arial", Font.PLAIN, 11));
		label_22.setBounds(271, 146, 126, 23);
		config.add(label_22);
		
		JTextField label_23 = new JTextField("A");
		label_23.setFont(new Font("Arial", Font.PLAIN, 11));
		label_23.setBounds(407, 146, 65, 23);
		config.add(label_23);
		
		JTextField label_24 = new JTextField("HEAD");
		label_24.setFont(new Font("Arial", Font.PLAIN, 11));
		label_24.setBounds(407, 121, 65, 23);
		config.add(label_24);
		
		JSpinner label_25 = new JSpinner();
		label_25.setFont(new Font("Arial", Font.PLAIN, 11));
		label_25.setBounds(407, 96, 65, 23);
		config.add(label_25);
		
		JComboBox label_26 = new JComboBox();
		label_26.setFont(new Font("Arial", Font.PLAIN, 11));
		label_26.setBounds(407, 74, 65, 20);
		config.add(label_26);
		
		textField = new JTextField("False");
		textField.setFont(new Font("Arial", Font.PLAIN, 11));
		textField.setBounds(138, 112, 55, 23);
		config.add(textField);
		
		textField_1 = new JTextField("0.0");
		textField_1.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_1.setBounds(138, 64, 55, 20);
		config.add(textField_1);
		
		JComboBox label_27 = new JComboBox();
		label_27.setFont(new Font("Arial", Font.PLAIN, 11));
		label_27.setBounds(407, 194, 65, 26);
		config.add(label_27);
		
		JLabel label_28 = new JLabel("Number of Passengers:");
		label_28.setFont(new Font("Arial", Font.PLAIN, 11));
		label_28.setBounds(271, 171, 126, 23);
		config.add(label_28);
		
		JSpinner label_29 = new JSpinner();
		label_29.setFont(new Font("Arial", Font.PLAIN, 11));
		label_29.setBounds(407, 171, 65, 22);
		config.add(label_29);
		
		JLabel label_31 = new JLabel("Switch Position:");
		label_31.setFont(new Font("Arial", Font.PLAIN, 11));
		label_31.setBounds(271, 196, 126, 23);
		config.add(label_31);

		JPanel failurePanel = new JPanel();
		tabbedPane.addTab("Murphy's Failures", null, failurePanel, null);
		
		JButton breakRail = new JButton("Break Rail");
		breakRail.setFont(new Font("Arial", Font.PLAIN, 11));
		breakRail.setBounds(1, 0, 159, 283);
		breakRail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String railToBreak = JOptionPane.showInputDialog(parent, "What rail would you like to break?", null);			
		        }
		});
		failurePanel.setLayout(null);
		failurePanel.add(breakRail);
		
		JButton powerFailure = new JButton("Cut Power");
		powerFailure.setFont(new Font("Arial", Font.PLAIN, 11));
		powerFailure.setBounds(160, 0, 159, 283);
		powerFailure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cutPowerConfirm = JOptionPane.showInputDialog(parent, "Are you sure you want to cut the power? (y/n)", null);			
			}
		});
		failurePanel.add(powerFailure);
		
		JButton circuitFailure = new JButton("Break Circuit");
		circuitFailure.setBounds(319, 0, 159, 283);
		circuitFailure.setFont(new Font("Arial", Font.PLAIN, 11));
		circuitFailure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String breakCircuitConfirm = JOptionPane.showInputDialog(parent, "Are you sure you want to break the circuit? (y/n)", null);			
			}
		});
		failurePanel.add(circuitFailure);

	}
}
