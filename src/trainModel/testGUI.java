package trainModel;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class testGUI {

	private JFrame frame;
	private JTextField leftDoorStatus;
	private JTextField rightDoorStatus;
	private JTextField lightsStatus;
	private JTextField powerInput;
	private JTextField passengerCount;
	private JTextField crewCount;
	private JTextField currentSpeed;
	private JTextField speedLimit;
	private JTextField setpointSpeed;
	private JTextField serviceBrakeStatus;
	private JTextField emergencyBrakeStatus;
	private JTextField temperature;
	private JTextField elevation;
	private JTextField trackCircuitInfo;
	final JFrame parent = new JFrame();
	private JTextField trainID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testGUI window = new testGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public testGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 749, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTrainModel = new JLabel("Train Model");
		lblTrainModel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblTrainModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrainModel.setBounds(209, 31, 280, 40);
		frame.getContentPane().add(lblTrainModel);
		
		JRadioButton testModeRadioButton = new JRadioButton("Test Mode");
		testModeRadioButton.setSelected(false);
		testModeRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		testModeRadioButton.setBackground(Color.LIGHT_GRAY);
		testModeRadioButton.setBounds(6, 43, 109, 23);
		frame.getContentPane().add(testModeRadioButton);
		
		
		
		JToggleButton leftDoorControl = new JToggleButton("Left Doors");
		leftDoorControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(leftDoorStatus.getText().contentEquals("Open")){
					leftDoorStatus.setText("Closed");
				}
				else{
					leftDoorStatus.setText("Open");
				}				
			}
		});
		leftDoorControl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		leftDoorControl.setBounds(6, 509, 150, 40);
		frame.getContentPane().add(leftDoorControl);
		
		JToggleButton rightDoorsControl = new JToggleButton("Right Doors");
		rightDoorsControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rightDoorStatus.getText().contentEquals("Open")){
					rightDoorStatus.setText("Closed");
				}
				else{
					rightDoorStatus.setText("Open");
				}				
			}
		});
		rightDoorsControl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rightDoorsControl.setBounds(6, 458, 150, 40);
		frame.getContentPane().add(rightDoorsControl);
		
		leftDoorStatus = new JTextField();
		leftDoorStatus.setText("Closed");
		leftDoorStatus.setHorizontalAlignment(SwingConstants.CENTER);
		leftDoorStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		leftDoorStatus.setBounds(166, 509, 80, 40);
		frame.getContentPane().add(leftDoorStatus);
		leftDoorStatus.setColumns(10);
		
		rightDoorStatus = new JTextField();
		rightDoorStatus.setHorizontalAlignment(SwingConstants.CENTER);
		rightDoorStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		rightDoorStatus.setText("Closed");
		rightDoorStatus.setBounds(166, 458, 80, 40);
		frame.getContentPane().add(rightDoorStatus);
		rightDoorStatus.setColumns(10);
		
		JToggleButton lightsControl = new JToggleButton("Lights");
		lightsControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lightsStatus.getText().contentEquals("Off")){
					lightsStatus.setText("On");
				}
				else{
					lightsStatus.setText("Off");
				}				
			}
		});
		lightsControl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lightsControl.setBounds(6, 407, 150, 40);
		frame.getContentPane().add(lightsControl);
		
		lightsStatus = new JTextField();
		lightsStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		lightsStatus.setText("Off");
		lightsStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lightsStatus.setBounds(166, 407, 80, 40);
		frame.getContentPane().add(lightsStatus);
		lightsStatus.setColumns(10);
		
		powerInput = new JTextField();
		powerInput.setHorizontalAlignment(SwingConstants.CENTER);
		powerInput.setFont(new Font("Tahoma", Font.BOLD, 18));
		powerInput.setText("50");
		powerInput.setBounds(10, 80, 150, 40);
		frame.getContentPane().add(powerInput);
		powerInput.setColumns(10);
		
		JButton powerInput_button = new JButton("Input Power");
		powerInput_button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		powerInput_button.setBounds(6, 131, 150, 23);
		frame.getContentPane().add(powerInput_button);
		
		JLabel powerLabel = new JLabel("W");
		powerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		powerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		powerLabel.setBounds(160, 100, 30, 20);
		frame.getContentPane().add(powerLabel);
		
		passengerCount = new JTextField();
		passengerCount.setText("85");
		passengerCount.setHorizontalAlignment(SwingConstants.CENTER);
		passengerCount.setFont(new Font("Tahoma", Font.BOLD, 18));
		passengerCount.setBounds(6, 356, 150, 40);
		frame.getContentPane().add(passengerCount);
		passengerCount.setColumns(10);
		
		JLabel lblPassengerCount = new JLabel("Passenger Count");
		lblPassengerCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassengerCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassengerCount.setBounds(6, 335, 150, 20);
		frame.getContentPane().add(lblPassengerCount);
		
		crewCount = new JTextField();
		crewCount.setEditable(false);//editable status
		crewCount.setText("12");
		crewCount.setHorizontalAlignment(SwingConstants.CENTER);
		crewCount.setFont(new Font("Tahoma", Font.BOLD, 18));
		crewCount.setBounds(6, 285, 150, 40);
		frame.getContentPane().add(crewCount);
		crewCount.setColumns(10);
		
		JLabel lblCrewCount = new JLabel("Crew Count");
		lblCrewCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrewCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCrewCount.setBounds(6, 265, 150, 20);
		frame.getContentPane().add(lblCrewCount);
		
		currentSpeed = new JTextField();
		currentSpeed.setEditable(false); //editable status
		currentSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		currentSpeed.setText("55");
		currentSpeed.setFont(new Font("Tahoma", Font.BOLD, 18));
		currentSpeed.setBounds(603, 89, 66, 40);
		frame.getContentPane().add(currentSpeed);
		currentSpeed.setColumns(10);
		
		JLabel lblCurrentSpeed = new JLabel("Current Speed");
		lblCurrentSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSpeed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCurrentSpeed.setBounds(577, 66, 110, 20);
		frame.getContentPane().add(lblCurrentSpeed);
		
		JLabel lblMph = new JLabel("mph");
		lblMph.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMph.setBounds(688, 109, 35, 20);
		frame.getContentPane().add(lblMph);
		
		speedLimit = new JTextField();
		speedLimit.setEditable(false); //editable status
		speedLimit.setText("60");
		speedLimit.setHorizontalAlignment(SwingConstants.CENTER);
		speedLimit.setFont(new Font("Tahoma", Font.BOLD, 18));
		speedLimit.setBounds(603, 165, 66, 40);
		frame.getContentPane().add(speedLimit);
		speedLimit.setColumns(10);
		
		JLabel lblMph_1 = new JLabel("mph");
		lblMph_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMph_1.setBounds(688, 176, 35, 20);
		frame.getContentPane().add(lblMph_1);
		
		JLabel lblSpeedLimit = new JLabel("Speed Limit");
		lblSpeedLimit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedLimit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSpeedLimit.setBounds(577, 141, 110, 20);
		frame.getContentPane().add(lblSpeedLimit);
		
		JLabel lblNewLabel_1 = new JLabel("Setpoint Speed");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(577, 216, 110, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		setpointSpeed = new JTextField();
		setpointSpeed.setEditable(false); //editable status
		setpointSpeed.setText("55");
		setpointSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		setpointSpeed.setFont(new Font("Tahoma", Font.BOLD, 18));
		setpointSpeed.setBounds(603, 247, 66, 40);
		frame.getContentPane().add(setpointSpeed);
		setpointSpeed.setColumns(10);
		
		JLabel lblMph_2 = new JLabel("mph");
		lblMph_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMph_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMph_2.setBounds(688, 258, 35, 20);
		frame.getContentPane().add(lblMph_2);
		
		serviceBrakeStatus = new JTextField();
		serviceBrakeStatus.setEditable(false);
		serviceBrakeStatus.setText("Disengaged");
		serviceBrakeStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		serviceBrakeStatus.setHorizontalAlignment(SwingConstants.CENTER);
		serviceBrakeStatus.setBounds(170, 183, 115, 30);
		frame.getContentPane().add(serviceBrakeStatus);
		serviceBrakeStatus.setColumns(10);
		
		emergencyBrakeStatus = new JTextField();
		emergencyBrakeStatus.setEditable(false);
		emergencyBrakeStatus.setText("Disengaged");
		emergencyBrakeStatus.setHorizontalAlignment(SwingConstants.CENTER);
		emergencyBrakeStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		emergencyBrakeStatus.setBounds(170, 224, 115, 30);
		frame.getContentPane().add(emergencyBrakeStatus);
		emergencyBrakeStatus.setColumns(10);
		
		JToggleButton emergencyBrakeControl = new JToggleButton("Emergency Brake");
		emergencyBrakeControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(emergencyBrakeStatus.getText().contentEquals("Disengaged")){
					emergencyBrakeStatus.setText("Engaged");
				}
				else{
					emergencyBrakeStatus.setText("Disengaged");
				}
			}
		});
		emergencyBrakeControl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emergencyBrakeControl.setForeground(Color.RED);
		emergencyBrakeControl.setBounds(10, 224, 146, 30);
		frame.getContentPane().add(emergencyBrakeControl);
		
		JToggleButton serviceBrakeControl = new JToggleButton("Service Brake");
		serviceBrakeControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(serviceBrakeStatus.getText().contentEquals("Disengaged")){

					serviceBrakeStatus.setText("Engaged");
				}
				else{
					serviceBrakeStatus.setText("Disengaged");
				}
			}
		});
		serviceBrakeControl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		serviceBrakeControl.setBounds(10, 183, 146, 30);
		frame.getContentPane().add(serviceBrakeControl);
		
		temperature = new JTextField();
		temperature.setEditable(false);
		temperature.setHorizontalAlignment(SwingConstants.CENTER);
		temperature.setText("72");
		temperature.setFont(new Font("Tahoma", Font.BOLD, 18));
		temperature.setBounds(603, 333, 66, 40);
		frame.getContentPane().add(temperature);
		temperature.setColumns(10);
		
		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTemperature.setBounds(577, 305, 110, 20);
		frame.getContentPane().add(lblTemperature);
		
		JLabel lblNewLabel_2 = new JLabel("\u2109");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial Unicode MS", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(688, 344, 35, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		elevation = new JTextField();
		elevation.setEditable(false);
		elevation.setText("255");
		elevation.setFont(new Font("Tahoma", Font.BOLD, 18));
		elevation.setHorizontalAlignment(SwingConstants.CENTER);
		elevation.setBounds(603, 419, 66, 40);
		frame.getContentPane().add(elevation);
		elevation.setColumns(10);
		
		JLabel lblElevation = new JLabel("Elevation");
		lblElevation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblElevation.setHorizontalAlignment(SwingConstants.CENTER);
		lblElevation.setBounds(577, 385, 110, 20);
		frame.getContentPane().add(lblElevation);
		
		JLabel lblFt = new JLabel("ft.");
		lblFt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFt.setHorizontalAlignment(SwingConstants.CENTER);
		lblFt.setBounds(679, 436, 35, 20);
		frame.getContentPane().add(lblFt);
		
		JLabel lblTrackCircuitInfo = new JLabel("Track Circuit Info");
		lblTrackCircuitInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTrackCircuitInfo.setBounds(577, 478, 130, 20);
		frame.getContentPane().add(lblTrackCircuitInfo);
		
		trackCircuitInfo = new JTextField();
		trackCircuitInfo.setEditable(false);
		trackCircuitInfo.setText("Unoccupied");
		trackCircuitInfo.setHorizontalAlignment(SwingConstants.CENTER);
		trackCircuitInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		trackCircuitInfo.setBounds(587, 509, 100, 40);
		frame.getContentPane().add(trackCircuitInfo);
		trackCircuitInfo.setColumns(10);
		
		JButton engineFailureButton = new JButton("Engine Failure");
		engineFailureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(parent, "Danger!! Engine Failure!!");	
			}
		});
		engineFailureButton.setForeground(Color.RED);
		engineFailureButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		engineFailureButton.setBounds(336, 413, 160, 30);
		frame.getContentPane().add(engineFailureButton);
		
		JButton brakeFailureButton = new JButton("Brake Failure");
		brakeFailureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(parent, "Danger!! Brake Failure!!");
			}
		});
		brakeFailureButton.setForeground(Color.RED);
		brakeFailureButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		brakeFailureButton.setBounds(336, 466, 160, 30);
		frame.getContentPane().add(brakeFailureButton);
		
		JButton signalPickupFailure = new JButton("Signal Pickup Failure");
		signalPickupFailure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(parent, "Danger!! Signal Pickup Failure!!");
			}
		});
		signalPickupFailure.setForeground(Color.RED);
		signalPickupFailure.setFont(new Font("Tahoma", Font.PLAIN, 15));
		signalPickupFailure.setBounds(336, 519, 160, 30);
		frame.getContentPane().add(signalPickupFailure);
		
		trainID = new JTextField();
		trainID.setHorizontalAlignment(SwingConstants.CENTER);
		trainID.setFont(new Font("Tahoma", Font.BOLD, 20));
		trainID.setText("12345");
		trainID.setBounds(336, 89, 115, 40);
		frame.getContentPane().add(trainID);
		trainID.setColumns(10);
		
		JLabel lblTrainId = new JLabel("TRAIN ID:");
		lblTrainId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTrainId.setBounds(216, 102, 90, 20);
		frame.getContentPane().add(lblTrainId);
		

	}
}
