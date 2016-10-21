package trainModel;
import java.awt.EventQueue;

import java.text.DecimalFormat;
import java.lang.*;

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
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class testGUI {

	private JFrame frame;
	private JTextField leftDoorStatus;
	private JTextField rightDoorStatus;
	private JTextField lightsStatus;
	private JTextField powerInput;
	private JTextField currentSpeed;
	private JTextField serviceBrakeStatus;
	private JTextField emergencyBrakeStatus;
	final JFrame parent = new JFrame();
	private JTextField trainID;
	
	private TrainModel train = new TrainModel(); 
	
	DecimalFormat dc = new DecimalFormat("#0.00");
	private JTable table;
	private JTextField CrewDisp;
	private JTextField speedLimitDisp;
	private JTextField PassengerDisp;
	private JTextField TempDisp;
	private JTextField NextBlockDisp;
	private JTextField NextBlockStatusDisp;
	private JTextField txtSpeedLimit;
	private JTextField txtCrewCount;
	private JTextField txtPassengers;
	private JTextField txtTemperature;
	private JTextField txtCurrentSpeed;
	private JTextField ElevationDisp;
	private JTextField CurrBlockDisp;
	private JTextField txtTrackInfo;
	private JTextField txtCurrentBlock;
	private JTextField txtNextBlock;
	private JTextField txtNextBlockStatus;
	private JTextField txtElevation;
	private JTextField txtTrainInfo;
	

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		 * Label for GUI Window
		 */
		JLabel lblTrainModel = new JLabel("Train Model");
		lblTrainModel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblTrainModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrainModel.setBounds(209, 31, 280, 40);
		frame.getContentPane().add(lblTrainModel);
		
		
		/*
		 * Test Mode Radio Button
		 */
		JRadioButton testModeButton = new JRadioButton("Test Mode");
		testModeButton.setSelected(true);
		testModeButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		testModeButton.setBackground(Color.LIGHT_GRAY);
		testModeButton.setBounds(577, 31, 109, 23);
		frame.getContentPane().add(testModeButton);
		
		if(!testModeButton.isSelected()){
			powerInput.setEditable(false);
		}
		
		
		/*
		 *Left Doors Button 
		 */
		JToggleButton leftDoorControl = new JToggleButton("Left Doors");
		leftDoorControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean leftDoorTrain = train.getLeftDoorStatus();
				
				//If in Test Mode
				if(testModeButton.isSelected()){
					
					
					if(leftDoorTrain){
						train.changeLeftDoors();
						leftDoorStatus.setText("Closed");
					}
					else{
						train.changeLeftDoors();
						leftDoorStatus.setText("Open");
					}	
					
				}
			}
		});
		leftDoorControl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		leftDoorControl.setBounds(10, 416, 146, 40);
		frame.getContentPane().add(leftDoorControl);
		
		
		/*
		 * Right Doors Button
		 */
		JToggleButton rightDoorsControl = new JToggleButton("Right Doors");
		rightDoorsControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If in Test Mode
				if(testModeButton.isSelected()){
					boolean rightDoorTrain = train.getRightDoorStatus();
					if(rightDoorTrain){
						train.changeRightDoors();
						rightDoorStatus.setText("Closed");
					}
					else{
						train.changeRightDoors();
						rightDoorStatus.setText("Open");
					}	
				}	
			}
		});
		rightDoorsControl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rightDoorsControl.setBounds(10, 365, 146, 40);
		frame.getContentPane().add(rightDoorsControl);
		
		
		/*
		 * Left Door Status Window
		 */
		leftDoorStatus = new JTextField();
		leftDoorStatus.setEditable(false);
		boolean leftDoors = train.getLeftDoorStatus();
			if(leftDoors){
				leftDoorStatus.setText("Open");
			}
			else{
				leftDoorStatus.setText("Closed");				
			}
		leftDoorStatus.setHorizontalAlignment(SwingConstants.CENTER);
		leftDoorStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		leftDoorStatus.setBounds(166, 416, 80, 40);
		frame.getContentPane().add(leftDoorStatus);
		leftDoorStatus.setColumns(10);
		
		
		/*
		 * Right door Status Window
		 */
		rightDoorStatus = new JTextField();
		rightDoorStatus.setEditable(false);
		boolean rightDoors = train.getRightDoorStatus();
			if(rightDoors){
				rightDoorStatus.setText("Open");
			}
			else{
				rightDoorStatus.setText("Closed");				
			}
		rightDoorStatus.setHorizontalAlignment(SwingConstants.CENTER);
		rightDoorStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		rightDoorStatus.setBounds(166, 365, 80, 40);
		frame.getContentPane().add(rightDoorStatus);
		rightDoorStatus.setColumns(10);
		
		
		/*
		 * Lights Button
		 */
		JToggleButton lightsControl = new JToggleButton("Lights");
		lightsControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If in Test Mode
				if(testModeButton.isSelected()){
					
					boolean lightsTrain = train.getLightStatus();
					if(lightsTrain){
						train.changeLightsStatus();
						lightsStatus.setText("Off");
					}
					else{
						train.changeLightsStatus();
						lightsStatus.setText("On");
					}
				}	
				
			}
		});
		lightsControl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lightsControl.setBounds(10, 314, 146, 40);
		frame.getContentPane().add(lightsControl);
		
		
		/*
		 * Lights Status Box
		 */
		lightsStatus = new JTextField();
		lightsStatus.setEditable(false);
		lightsStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		boolean lights = train.getLightStatus();
			if(lights){
				lightsStatus.setText("On");
			}
			else{
				lightsStatus.setText("Off");				
			}
		lightsStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lightsStatus.setBounds(166, 314, 80, 40);
		frame.getContentPane().add(lightsStatus);
		lightsStatus.setColumns(10);
		
		
		/*
		 * Power Input Display Box
		 */
		powerInput = new JTextField();
		
		double trainPower = train.power;
		powerInput.setText(Double.toString(trainPower));
		
		powerInput.setHorizontalAlignment(SwingConstants.CENTER);
		powerInput.setFont(new Font("Tahoma", Font.BOLD, 20));
		powerInput.setBounds(10, 80, 175, 40);
		frame.getContentPane().add(powerInput);
		powerInput.setColumns(10);
		
		
		/*
		 * Power Input Button
		 */
		JButton powerInput_button = new JButton("Input Power");
		powerInput_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If in Test Mode
				if(testModeButton.isSelected()){
			
					//display velocity in velocity display box
					String powerInputString = powerInput.getText();
					double trainPowerInput = Double.parseDouble(powerInputString);
					double trainVelocity;
					
					train.setPower(trainPowerInput);				
					trainVelocity = train.getVelocity();
					
					String trainSpeedString1 = dc.format(trainVelocity);
					currentSpeed.setText(trainSpeedString1+" mph");
			
				}
				
			}
		});
		powerInput_button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		powerInput_button.setBounds(10, 131, 175, 40);
		frame.getContentPane().add(powerInput_button);
		
		
		/*
		 * Horsepower Label
		 */
		JLabel powerLabel = new JLabel("W");
		powerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		powerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		powerLabel.setBounds(195, 100, 30, 20);
		frame.getContentPane().add(powerLabel);
		int trainPassengers = train.getPassengerCount();
		int trainCrew = train.getCrewCount();
		
		
		/*
		 * Current Speed Display
		 */
		currentSpeed = new JTextField();
		currentSpeed.setEditable(false); //editable status
		currentSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		double trainSpeed;
		trainSpeed = train.getVelocity();
		String trainSpeedString = dc.format(trainSpeed);
		currentSpeed.setText(trainSpeedString+" mph");
		currentSpeed.setFont(new Font("Tahoma", Font.BOLD, 20));
		currentSpeed.setBounds(557, 149, 153, 40);
		frame.getContentPane().add(currentSpeed);
		currentSpeed.setColumns(10);
		
		
		/*
		 * Service Brake Status Display
		 */
		serviceBrakeStatus = new JTextField();
		serviceBrakeStatus.setEditable(false);
		boolean trainBrakes = train.getServiceBrakeStatus();
			if(trainBrakes){
				serviceBrakeStatus.setText("Engaged");
			}
			else{
				serviceBrakeStatus.setText("Disengaged");
			}
		serviceBrakeStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		serviceBrakeStatus.setHorizontalAlignment(SwingConstants.CENTER);
		serviceBrakeStatus.setBounds(166, 187, 125, 52);
		frame.getContentPane().add(serviceBrakeStatus);
		serviceBrakeStatus.setColumns(10);
		
		
		/*
		 * Emergency Brake Status Display
		 */
		emergencyBrakeStatus = new JTextField();
		emergencyBrakeStatus.setEditable(false);
		boolean trainEmergencyBrakes = train.getEmergencyBrakeStatus();
			if(trainEmergencyBrakes){
				emergencyBrakeStatus.setText("Engaged");
			}
			else{
				emergencyBrakeStatus.setText("Disengaged");
			}
		emergencyBrakeStatus.setHorizontalAlignment(SwingConstants.CENTER);
		emergencyBrakeStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		emergencyBrakeStatus.setBounds(166, 250, 125, 52);
		frame.getContentPane().add(emergencyBrakeStatus);
		emergencyBrakeStatus.setColumns(10);
		
		
		/*
		 * Emergency Brake Button
		 */
		JToggleButton emergencyBrakeControl = new JToggleButton("Emergency Brake");
		emergencyBrakeControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//only if in test mode
				if(testModeButton.isSelected()){
					boolean eBrakeStatus = train.getEmergencyBrakeStatus();
						if(eBrakeStatus){
							train.emergencyBrake();
							emergencyBrakeStatus.setText("Disengaged");
						}
						else{
							train.emergencyBrake();
							emergencyBrakeStatus.setText("Engaged");
						}
				}
			}
		});
		emergencyBrakeControl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emergencyBrakeControl.setForeground(Color.RED);
		emergencyBrakeControl.setBounds(10, 251, 146, 52);
		frame.getContentPane().add(emergencyBrakeControl);
		
		
		
		/*
		 * Service Brake Button
		 */
		JToggleButton serviceBrakeControl = new JToggleButton("Service Brake");
		serviceBrakeControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Only if in test mode
				if(testModeButton.isSelected()){
					boolean trainBrakeStatus = train.getServiceBrakeStatus();
						if(trainBrakeStatus){
						train.serviceBrake();
						serviceBrakeStatus.setText("Disengaged");
					}
					else{
							train.serviceBrake();
						serviceBrakeStatus.setText("Engaged");
					}
				}
			}
		});
		serviceBrakeControl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		serviceBrakeControl.setBounds(10, 187, 146, 52);
		frame.getContentPane().add(serviceBrakeControl);
		
		
		/*
		 * Engine Failure Button
		 */
		JButton engineFailureButton = new JButton("Engine Failure");
		engineFailureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If in Test Mode
				if(testModeButton.isSelected()){
					
					
					JOptionPane.showMessageDialog(parent, "Danger!! Engine Failure!!");	
			
				}
			}
		});
		engineFailureButton.setForeground(Color.RED);
		engineFailureButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		engineFailureButton.setBounds(10, 471, 160, 30);
		frame.getContentPane().add(engineFailureButton);
		
		
		/*
		 * Brake Failure Button
		 */
		JButton brakeFailureButton = new JButton("Brake Failure");
		brakeFailureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If int Test Mode
				if(testModeButton.isSelected()){
				
					
				JOptionPane.showMessageDialog(parent, "Danger!! Brake Failure!!");
			
				
				}
			}
		});
		brakeFailureButton.setForeground(Color.RED);
		brakeFailureButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		brakeFailureButton.setBounds(10, 512, 160, 30);
		frame.getContentPane().add(brakeFailureButton);
		
		
		/*
		 * Signal Pickup Failure Button
		 */
		JButton signalPickupFailure = new JButton("Signal Pickup Failure");
		signalPickupFailure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If in Test Mode
				if(testModeButton.isSelected()){	 
					
					
					JOptionPane.showMessageDialog(parent, "Danger!! Signal Pickup Failure!!");
					
					
				}
			}
		});
		signalPickupFailure.setForeground(Color.RED);
		signalPickupFailure.setFont(new Font("Tahoma", Font.PLAIN, 15));
		signalPickupFailure.setBounds(10, 553, 160, 30);
		frame.getContentPane().add(signalPickupFailure);
		
		
		/*
		 * Train ID Display Box
		 */
		trainID = new JTextField();
		trainID.setEditable(false);
		trainID.setHorizontalAlignment(SwingConstants.CENTER);
		trainID.setFont(new Font("Tahoma", Font.BOLD, 20));
		trainID.setText("12345");
		trainID.setBounds(103, 25, 115, 40);
		frame.getContentPane().add(trainID);
		trainID.setColumns(10);
		
		JLabel trainIDLabel = new JLabel("TRAIN ID:");
		trainIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		trainIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		trainIDLabel.setBounds(0, 45, 90, 20);
		frame.getContentPane().add(trainIDLabel);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setBackground(Color.WHITE);
		table.setBounds(502, 291, -131, -124);
		frame.getContentPane().add(table);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(511, 233, -213, 120);
		frame.getContentPane().add(textPane);
		
		CrewDisp = new JTextField();
		CrewDisp.setHorizontalAlignment(SwingConstants.CENTER);
		CrewDisp.setFont(new Font("Tahoma", Font.BOLD, 20));
		CrewDisp.setEditable(false);
		int crewCount;
		crewCount = train.getCrewCount();
		CrewDisp.setText(Integer.toString(crewCount));
		CrewDisp.setBounds(557, 229, 153, 40);
		frame.getContentPane().add(CrewDisp);
		CrewDisp.setColumns(10);
		
		speedLimitDisp = new JTextField();
		speedLimitDisp.setEditable(false);
		
			speedLimitDisp.setText("45.00"+" mph");
		
		speedLimitDisp.setFont(new Font("Tahoma", Font.BOLD, 20));
		speedLimitDisp.setHorizontalAlignment(SwingConstants.CENTER);
		speedLimitDisp.setBounds(557, 189, 153, 40);
		frame.getContentPane().add(speedLimitDisp);
		speedLimitDisp.setColumns(10);
		
		PassengerDisp = new JTextField();
		PassengerDisp.setFont(new Font("Tahoma", Font.BOLD, 20));
		PassengerDisp.setHorizontalAlignment(SwingConstants.CENTER);
		PassengerDisp.setEditable(false);
		int passCount;
		passCount = train.getPassengerCount();
		PassengerDisp.setText(Integer.toString(passCount));
		PassengerDisp.setBounds(557, 269, 153, 40);
		frame.getContentPane().add(PassengerDisp);
		PassengerDisp.setColumns(10);
		
		TempDisp = new JTextField();
		TempDisp.setFont(new Font("Tahoma", Font.BOLD, 20));
		TempDisp.setHorizontalAlignment(SwingConstants.CENTER);
		TempDisp.setEditable(false);
		int temperature;
		temperature = train.getTemperature();
		TempDisp.setText(Integer.toString(temperature));
		TempDisp.setBounds(557, 309, 153, 40);
		frame.getContentPane().add(TempDisp);
		TempDisp.setColumns(10);
		
		NextBlockDisp = new JTextField();
		NextBlockDisp.setFont(new Font("Tahoma", Font.BOLD, 20));
		NextBlockDisp.setEditable(false);
		NextBlockDisp.setBounds(577, 442, 133, 40);
		frame.getContentPane().add(NextBlockDisp);
		NextBlockDisp.setColumns(10);
		
		NextBlockStatusDisp = new JTextField();
		NextBlockStatusDisp.setFont(new Font("Tahoma", Font.BOLD, 18));
		NextBlockStatusDisp.setHorizontalAlignment(SwingConstants.CENTER);
		NextBlockStatusDisp.setEditable(false);
		NextBlockStatusDisp.setBounds(577, 482, 133, 40);
		frame.getContentPane().add(NextBlockStatusDisp);
		NextBlockStatusDisp.setColumns(10);
		
		txtSpeedLimit = new JTextField();
		txtSpeedLimit.setFont(new Font("Courier New", Font.BOLD, 18));
		txtSpeedLimit.setText("Speed Limit:");
		txtSpeedLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSpeedLimit.setEditable(false);
		txtSpeedLimit.setBounds(362, 189, 195, 40);
		frame.getContentPane().add(txtSpeedLimit);
		txtSpeedLimit.setColumns(10);
		
		txtCrewCount = new JTextField();
		txtCrewCount.setFont(new Font("Courier New", Font.BOLD, 18));
		txtCrewCount.setText("Crew:");
		txtCrewCount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCrewCount.setEditable(false);
		txtCrewCount.setColumns(10);
		txtCrewCount.setBounds(362, 229, 195, 40);
		frame.getContentPane().add(txtCrewCount);
		
		txtPassengers = new JTextField();
		txtPassengers.setFont(new Font("Courier New", Font.BOLD, 18));
		txtPassengers.setText("Passengers:");
		txtPassengers.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPassengers.setEditable(false);
		txtPassengers.setColumns(10);
		txtPassengers.setBounds(362, 269, 195, 40);
		frame.getContentPane().add(txtPassengers);
		
		txtTemperature = new JTextField();
		txtTemperature.setFont(new Font("Courier New", Font.BOLD, 18));
		txtTemperature.setText("Temperature:");
		txtTemperature.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTemperature.setEditable(false);
		txtTemperature.setColumns(10);
		txtTemperature.setBounds(362, 309, 195, 40);
		frame.getContentPane().add(txtTemperature);
		
		txtCurrentSpeed = new JTextField();
		txtCurrentSpeed.setEditable(false);
		txtCurrentSpeed.setFont(new Font("Courier New", Font.BOLD, 18));
		txtCurrentSpeed.setText("Current Speed:");
		txtCurrentSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCurrentSpeed.setBounds(362, 149, 195, 40);
		frame.getContentPane().add(txtCurrentSpeed);
		txtCurrentSpeed.setColumns(10);
		
		ElevationDisp = new JTextField();
		ElevationDisp.setHorizontalAlignment(SwingConstants.CENTER);
		ElevationDisp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ElevationDisp.setEditable(false);
		ElevationDisp.setColumns(10);
		ElevationDisp.setBounds(577, 522, 133, 40);
		frame.getContentPane().add(ElevationDisp);
		
		CurrBlockDisp = new JTextField();
		CurrBlockDisp.setFont(new Font("Tahoma", Font.BOLD, 20));
		CurrBlockDisp.setEditable(false);
		CurrBlockDisp.setColumns(10);
		CurrBlockDisp.setBounds(577, 402, 133, 40);
		frame.getContentPane().add(CurrBlockDisp);
		
		txtTrackInfo = new JTextField();
		txtTrackInfo.setEditable(false);
		txtTrackInfo.setText("TRACK INFO");
		txtTrackInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtTrackInfo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTrackInfo.setColumns(10);
		txtTrackInfo.setBounds(362, 362, 348, 40);
		frame.getContentPane().add(txtTrackInfo);
		
		txtCurrentBlock = new JTextField();
		txtCurrentBlock.setEditable(false);
		txtCurrentBlock.setText("Current Block #:");
		txtCurrentBlock.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCurrentBlock.setFont(new Font("Courier New", Font.BOLD, 18));
		txtCurrentBlock.setColumns(10);
		txtCurrentBlock.setBounds(362, 402, 215, 40);
		frame.getContentPane().add(txtCurrentBlock);
		
		txtNextBlock = new JTextField();
		txtNextBlock.setEditable(false);
		txtNextBlock.setText("Next Block #:");
		txtNextBlock.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNextBlock.setFont(new Font("Courier New", Font.BOLD, 18));
		txtNextBlock.setColumns(10);
		txtNextBlock.setBounds(362, 442, 215, 40);
		frame.getContentPane().add(txtNextBlock);
		
		txtNextBlockStatus = new JTextField();
		txtNextBlockStatus.setEditable(false);
		txtNextBlockStatus.setText("Next Block Status:");
		txtNextBlockStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNextBlockStatus.setFont(new Font("Courier New", Font.BOLD, 18));
		txtNextBlockStatus.setColumns(10);
		txtNextBlockStatus.setBounds(362, 482, 215, 40);
		frame.getContentPane().add(txtNextBlockStatus);
		
		txtElevation = new JTextField();
		txtElevation.setEditable(false);
		txtElevation.setText("Elevation:");
		txtElevation.setHorizontalAlignment(SwingConstants.RIGHT);
		txtElevation.setFont(new Font("Courier New", Font.BOLD, 18));
		txtElevation.setColumns(10);
		txtElevation.setBounds(362, 522, 215, 40);
		frame.getContentPane().add(txtElevation);
		
		txtTrainInfo = new JTextField();
		txtTrainInfo.setEditable(false);
		txtTrainInfo.setText("TRAIN INFO");
		txtTrainInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtTrainInfo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTrainInfo.setBounds(362, 110, 348, 40);
		frame.getContentPane().add(txtTrainInfo);
		txtTrainInfo.setColumns(10);
		

	}
}
