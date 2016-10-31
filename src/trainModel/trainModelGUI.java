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
import java.awt.Canvas;
import javax.swing.UIManager;

public class trainModelGUI {

	private JFrame frame;
	private JTextField leftDoorStatus;
	private JTextField rightDoorStatus;
	private JTextField lightsStatus;
	private JTextField powerInput;
	private JTextField currentSpeed;
	private JTextField serviceBrakeStatus;
	private JTextField emergencyBrakeStatus;
	private JTextField trainID;
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
	private Canvas canvas_3;
	private JTextField currentTrainPower;
	private Canvas canvas_4;
	private Canvas canvas_5;
	final JFrame parent = new JFrame();

	
	private TrainModel train = new TrainModel(); 
	DecimalFormat dc = new DecimalFormat("#0.00");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trainModelGUI window = new trainModelGUI();
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
	public trainModelGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 749, 680);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		 * Label for GUI Window
		 */
		JLabel lblTrainModel = new JLabel("Train Model");
		lblTrainModel.setForeground(Color.BLUE);
		lblTrainModel.setFont(new Font("Courier New", Font.BOLD, 36));
		lblTrainModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrainModel.setBounds(219, 28, 260, 40);
		frame.getContentPane().add(lblTrainModel);
		
		
		/*
		 * Test Mode Radio Button
		 */
		JRadioButton testModeButton = new JRadioButton("Test Mode");
		testModeButton.setSelected(true);
		testModeButton.setFont(new Font("Courier New", Font.BOLD, 20));
		testModeButton.setBackground(Color.LIGHT_GRAY);
		testModeButton.setBounds(10, 40, 140, 23);
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
		leftDoorControl.setFont(new Font("Courier New", Font.BOLD, 16));
		leftDoorControl.setBounds(10, 591, 175, 40);
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
		rightDoorsControl.setFont(new Font("Courier New", Font.BOLD, 16));
		rightDoorsControl.setBounds(10, 551, 175, 40);
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
		leftDoorStatus.setBounds(190, 591, 125, 40);
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
		rightDoorStatus.setBounds(190, 551, 125, 40);
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
		lightsControl.setFont(new Font("Courier New", Font.BOLD, 16));
		lightsControl.setBounds(10, 511, 175, 40);
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
		lightsStatus.setBounds(190, 511, 125, 40);
		frame.getContentPane().add(lightsStatus);
		lightsStatus.setColumns(10);
		
		
		/*
		 * Power Input Display Box
		 */
		powerInput = new JTextField();
		
		double trainPower = train.power;
		powerInput.setText(Double.toString(trainPower));
		
		powerInput.setHorizontalAlignment(SwingConstants.CENTER);
		powerInput.setFont(new Font("Courier New", Font.BOLD, 24));
		powerInput.setBounds(41, 187, 242, 40);
		frame.getContentPane().add(powerInput);
		powerInput.setColumns(10);
		
		
		/*
		 * Power Input Button
		 */
		JButton powerInput_button = new JButton("Input Power");
		powerInput_button.setBackground(UIManager.getColor("Button.background"));
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
		powerInput_button.setFont(new Font("Courier New", Font.BOLD, 18));
		powerInput_button.setBounds(41, 229, 243, 40);
		frame.getContentPane().add(powerInput_button);
		
		
		/*
		 * KiloWatts Label
		 */
		JLabel powerLabel = new JLabel("KW");
		powerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		powerLabel.setFont(new Font("Courier New", Font.PLAIN, 24));
		powerLabel.setBounds(285, 200, 30, 20);
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
		currentSpeed.setFont(new Font("Courier New", Font.BOLD, 20));
		currentSpeed.setBounds(536, 189, 172, 40);
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
		serviceBrakeStatus.setBounds(190, 407, 125, 52);
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
		emergencyBrakeStatus.setBounds(190, 459, 125, 52);
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
		emergencyBrakeControl.setFont(new Font("Courier New", Font.BOLD, 15));
		emergencyBrakeControl.setForeground(Color.RED);
		emergencyBrakeControl.setBounds(10, 459, 175, 52);
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
		serviceBrakeControl.setFont(new Font("Courier New", Font.BOLD, 16));
		serviceBrakeControl.setBounds(10, 407, 175, 52);
		frame.getContentPane().add(serviceBrakeControl);
		
		
		/*
		 * Engine Failure Button
		 */
		JButton engineFailureButton = new JButton("Engine Failure");
		engineFailureButton.setBackground(UIManager.getColor("Button.background"));
		engineFailureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If in Test Mode
				if(testModeButton.isSelected()){
					
					
					JOptionPane.showMessageDialog(parent, "Danger!! Engine Failure!!");	
					train.initFailureProtocol();
			
				}
			}
		});
		engineFailureButton.setForeground(Color.RED);
		engineFailureButton.setFont(new Font("Courier New", Font.BOLD, 16));
		engineFailureButton.setBounds(41, 298, 243, 30);
		frame.getContentPane().add(engineFailureButton);
		
		
		/*
		 * Brake Failure Button
		 */
		JButton brakeFailureButton = new JButton("Brake Failure");
		brakeFailureButton.setBackground(UIManager.getColor("Button.background"));
		brakeFailureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If in Test Mode
				if(testModeButton.isSelected()){
						
					JOptionPane.showMessageDialog(parent, "Danger!! Brake Failure!!");
					train.initFailureProtocol();
			
				}
			}
		});
		brakeFailureButton.setForeground(Color.RED);
		brakeFailureButton.setFont(new Font("Courier New", Font.BOLD, 16));
		brakeFailureButton.setBounds(41, 329, 243, 30);
		frame.getContentPane().add(brakeFailureButton);
		
		
		/*
		 * Signal Pickup Failure Button
		 */
		JButton signalPickupFailure = new JButton("Signal Pickup Failure");
		signalPickupFailure.setBackground(UIManager.getColor("Button.background"));
		signalPickupFailure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If in Test Mode
				if(testModeButton.isSelected()){	 
					
					
					JOptionPane.showMessageDialog(parent, "Danger!! Signal Pickup Failure!!");
					train.initFailureProtocol();
					
				}
			}
		});
		signalPickupFailure.setForeground(Color.RED);
		signalPickupFailure.setFont(new Font("Courier New", Font.BOLD, 16));
		signalPickupFailure.setBounds(41, 360, 243, 30);
		frame.getContentPane().add(signalPickupFailure);
		
		
		/*
		 * Train ID Display Box
		 */
		trainID = new JTextField();
		trainID.setEditable(false);
		trainID.setHorizontalAlignment(SwingConstants.CENTER);
		trainID.setFont(new Font("Courier New", Font.BOLD, 24));
		trainID.setText(Integer.toString(train.trainID));
		trainID.setBounds(593, 59, 115, 40);
		frame.getContentPane().add(trainID);
		trainID.setColumns(10);
		
		JLabel trainIDLabel = new JLabel("TRAIN ID:");
		trainIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		trainIDLabel.setFont(new Font("Courier New", Font.BOLD, 20));
		trainIDLabel.setBounds(593, 36, 115, 23);
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
		CrewDisp.setFont(new Font("Courier New", Font.BOLD, 20));
		CrewDisp.setEditable(false);
		int crewCount;
		crewCount = train.getCrewCount();
		CrewDisp.setText(Integer.toString(crewCount));
		CrewDisp.setBounds(536, 269, 172, 40);
		frame.getContentPane().add(CrewDisp);
		CrewDisp.setColumns(10);
		
		speedLimitDisp = new JTextField();
		speedLimitDisp.setEditable(false);
		
			speedLimitDisp.setText("45.00"+" mph");
		
		speedLimitDisp.setFont(new Font("Courier New", Font.BOLD, 20));
		speedLimitDisp.setHorizontalAlignment(SwingConstants.CENTER);
		speedLimitDisp.setBounds(536, 229, 172, 40);
		frame.getContentPane().add(speedLimitDisp);
		speedLimitDisp.setColumns(10);
		
		PassengerDisp = new JTextField();
		PassengerDisp.setFont(new Font("Courier New", Font.BOLD, 20));
		PassengerDisp.setHorizontalAlignment(SwingConstants.CENTER);
		PassengerDisp.setEditable(false);
		int passCount;
		passCount = train.getPassengerCount();
		PassengerDisp.setText(Integer.toString(passCount));
		PassengerDisp.setBounds(536, 309, 172, 40);
		frame.getContentPane().add(PassengerDisp);
		PassengerDisp.setColumns(10);
		
		TempDisp = new JTextField();
		TempDisp.setFont(new Font("Courier New", Font.BOLD, 20));
		TempDisp.setHorizontalAlignment(SwingConstants.CENTER);
		TempDisp.setEditable(false);
		int temperature;
		temperature = train.getTemperature();
		TempDisp.setText(Integer.toString(temperature) + " \u2109");
		TempDisp.setBounds(536, 349, 172, 40);
		frame.getContentPane().add(TempDisp);
		TempDisp.setColumns(10);
		
		NextBlockDisp = new JTextField();
		NextBlockDisp.setFont(new Font("Courier New", Font.BOLD, 20));
		NextBlockDisp.setEditable(false);
		NextBlockDisp.setBounds(575, 511, 133, 40);
		frame.getContentPane().add(NextBlockDisp);
		NextBlockDisp.setColumns(10);
		
		NextBlockStatusDisp = new JTextField();
		
		NextBlockStatusDisp.setText("Unoccupied");
		
		NextBlockStatusDisp.setFont(new Font("Courier New", Font.BOLD, 18));
		NextBlockStatusDisp.setHorizontalAlignment(SwingConstants.CENTER);
		NextBlockStatusDisp.setEditable(false);
		NextBlockStatusDisp.setBounds(575, 551, 133, 40);
		frame.getContentPane().add(NextBlockStatusDisp);
		NextBlockStatusDisp.setColumns(10);
		
		txtSpeedLimit = new JTextField();
		txtSpeedLimit.setFont(new Font("Courier New", Font.BOLD, 18));
		txtSpeedLimit.setText("Speed Limit:");
		txtSpeedLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSpeedLimit.setEditable(false);
		txtSpeedLimit.setBounds(362, 229, 174, 40);
		frame.getContentPane().add(txtSpeedLimit);
		txtSpeedLimit.setColumns(10);
		
		txtCrewCount = new JTextField();
		txtCrewCount.setFont(new Font("Courier New", Font.BOLD, 18));
		txtCrewCount.setText("Crew:");
		txtCrewCount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCrewCount.setEditable(false);
		txtCrewCount.setColumns(10);
		txtCrewCount.setBounds(362, 269, 174, 40);
		frame.getContentPane().add(txtCrewCount);
		
		txtPassengers = new JTextField();
		txtPassengers.setFont(new Font("Courier New", Font.BOLD, 18));
		txtPassengers.setText("Passengers:");
		txtPassengers.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPassengers.setEditable(false);
		txtPassengers.setColumns(10);
		txtPassengers.setBounds(362, 309, 174, 40);
		frame.getContentPane().add(txtPassengers);
		
		txtTemperature = new JTextField();
		txtTemperature.setFont(new Font("Courier New", Font.BOLD, 18));
		txtTemperature.setText("Temperature:");
		txtTemperature.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTemperature.setEditable(false);
		txtTemperature.setColumns(10);
		txtTemperature.setBounds(362, 349, 174, 40);
		frame.getContentPane().add(txtTemperature);
		
		txtCurrentSpeed = new JTextField();
		txtCurrentSpeed.setEditable(false);
		txtCurrentSpeed.setFont(new Font("Courier New", Font.BOLD, 18));
		txtCurrentSpeed.setText("Current Speed:");
		txtCurrentSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCurrentSpeed.setBounds(362, 189, 174, 40);
		frame.getContentPane().add(txtCurrentSpeed);
		txtCurrentSpeed.setColumns(10);
		
		ElevationDisp = new JTextField();
		ElevationDisp.setHorizontalAlignment(SwingConstants.CENTER);
		ElevationDisp.setFont(new Font("Courier New", Font.PLAIN, 20));
		ElevationDisp.setEditable(false);
		ElevationDisp.setColumns(10);
		ElevationDisp.setBounds(575, 591, 133, 40);
		frame.getContentPane().add(ElevationDisp);
		
		CurrBlockDisp = new JTextField();
		CurrBlockDisp.setFont(new Font("Courier New", Font.BOLD, 20));
		CurrBlockDisp.setEditable(false);
		CurrBlockDisp.setColumns(10);
		CurrBlockDisp.setBounds(575, 471, 133, 40);
		frame.getContentPane().add(CurrBlockDisp);
		
		txtTrackInfo = new JTextField();
		txtTrackInfo.setEditable(false);
		txtTrackInfo.setText("TRACK INFO");
		txtTrackInfo.setFont(new Font("Courier New", Font.BOLD, 26));
		txtTrackInfo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTrackInfo.setColumns(10);
		txtTrackInfo.setBounds(362, 418, 346, 52);
		frame.getContentPane().add(txtTrackInfo);
		
		txtCurrentBlock = new JTextField();
		txtCurrentBlock.setEditable(false);
		txtCurrentBlock.setText("Current Block #:");
		txtCurrentBlock.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCurrentBlock.setFont(new Font("Courier New", Font.BOLD, 18));
		txtCurrentBlock.setColumns(10);
		txtCurrentBlock.setBounds(362, 471, 213, 40);
		frame.getContentPane().add(txtCurrentBlock);
		
		txtNextBlock = new JTextField();
		txtNextBlock.setEditable(false);
		txtNextBlock.setText("Next Block #:");
		txtNextBlock.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNextBlock.setFont(new Font("Courier New", Font.BOLD, 18));
		txtNextBlock.setColumns(10);
		txtNextBlock.setBounds(362, 511, 213, 40);
		frame.getContentPane().add(txtNextBlock);
		
		txtNextBlockStatus = new JTextField();
		txtNextBlockStatus.setEditable(false);
		txtNextBlockStatus.setText("Next Block Status:");
		txtNextBlockStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNextBlockStatus.setFont(new Font("Courier New", Font.BOLD, 18));
		txtNextBlockStatus.setColumns(10);
		txtNextBlockStatus.setBounds(362, 551, 213, 40);
		frame.getContentPane().add(txtNextBlockStatus);
		
		txtElevation = new JTextField();
		txtElevation.setEditable(false);
		txtElevation.setText("Elevation:");
		txtElevation.setHorizontalAlignment(SwingConstants.RIGHT);
		txtElevation.setFont(new Font("Courier New", Font.BOLD, 18));
		txtElevation.setColumns(10);
		txtElevation.setBounds(362, 591, 213, 40);
		frame.getContentPane().add(txtElevation);
		
		txtTrainInfo = new JTextField();
		txtTrainInfo.setEditable(false);
		txtTrainInfo.setText("TRAIN INFO");
		txtTrainInfo.setFont(new Font("Courier New", Font.BOLD, 26));
		txtTrainInfo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTrainInfo.setBounds(362, 136, 346, 54);
		frame.getContentPane().add(txtTrainInfo);
		txtTrainInfo.setColumns(10);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.BLACK);
		canvas.setBounds(340, 110, 5, 521);
		frame.getContentPane().add(canvas);
		
		Canvas canvas_1 = new Canvas();
		canvas_1.setBackground(Color.BLACK);
		canvas_1.setBounds(10, 280, 305, 5);
		frame.getContentPane().add(canvas_1);
		
		Canvas canvas_2 = new Canvas();
		canvas_2.setBackground(Color.BLACK);
		canvas_2.setBounds(10, 396, 305, 5);
		frame.getContentPane().add(canvas_2);
		
		canvas_3 = new Canvas();
		canvas_3.setBackground(Color.BLACK);
		canvas_3.setBounds(10, 110, 305, 5);
		frame.getContentPane().add(canvas_3);
		
		currentTrainPower = new JTextField();
		currentTrainPower.setHorizontalAlignment(SwingConstants.CENTER);
		currentTrainPower.setFont(new Font("Courier New", Font.BOLD, 24));
		
		double currTrainPwr;
		currTrainPwr = 00.00;
		currentTrainPower.setText(Double.toString(currTrainPwr));

		currentTrainPower.setEditable(false);
		currentTrainPower.setBounds(41, 136, 242, 40);
		frame.getContentPane().add(currentTrainPower);
		currentTrainPower.setColumns(10);
		
		JLabel lblKw = new JLabel("KW");
		lblKw.setHorizontalAlignment(SwingConstants.CENTER);
		lblKw.setFont(new Font("Courier New", Font.PLAIN, 24));
		lblKw.setBounds(285, 156, 30, 20);
		frame.getContentPane().add(lblKw);
		
		canvas_4 = new Canvas();
		canvas_4.setBackground(Color.BLACK);
		canvas_4.setBounds(362, 396, 346, 5);
		frame.getContentPane().add(canvas_4);
		
		canvas_5 = new Canvas();
		canvas_5.setBackground(Color.BLACK);
		canvas_5.setBounds(362, 110, 346, 5);
		frame.getContentPane().add(canvas_5);
		

	}
}
