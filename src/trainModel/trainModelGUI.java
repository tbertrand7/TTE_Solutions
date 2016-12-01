package trainModel;
import java.awt.EventQueue;

import java.text.DecimalFormat;

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
import java.awt.Canvas;
import javax.swing.UIManager;

import javax.swing.JComboBox;

public class trainModelGUI {

	private JFrame frame;
	private JTextField leftDoorStatus;
	private JTextField rightDoorStatus;
	private JTextField lightsStatus;
	private JTextField powerInput;
	private JTextField currentSpeed;
	private JTextField serviceBrakeStatus;
	private JTextField emergencyBrakeStatus;
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
	private JTextField lineColor;
	private JTextField txtElevation;
	private JTextField txtTrainInfo;
	private Canvas canvas_3;
	private JTextField currentTrainPower;
	private Canvas canvas_4;
	private Canvas canvas_5;
	final JFrame parent = new JFrame();

	private Trains trainModelInstances;
	
	private TrainModel train;
	
	DecimalFormat dc = new DecimalFormat("#0.00");
	
	JComboBox<Integer> trainList = new JComboBox<Integer>();


	
	public void eBrake(boolean eBrake){
		if(!eBrake){
			emergencyBrakeStatus.setText("Disengaged");
		}
		else{
			emergencyBrakeStatus.setText("Engaged");
		}
	}
	
	public void sBrake(boolean sBrake){
		
		if(!sBrake){
			serviceBrakeStatus.setText("Disengaged");
		}
		else{
			serviceBrakeStatus.setText("Engaged");
		}		
		
	}
	
	public void lights(boolean lights){
		if(!lights){
			lightsStatus.setText("Off");
		}
		else{
			lightsStatus.setText("On");
		}
	}
	
	public void rDoors(boolean rDoors){
		if(!rDoors){
			rightDoorStatus.setText("Closed");
		}
		else{
			rightDoorStatus.setText("Open");
		}
	}
	
	public void lDoors(boolean lDoors){
		if(!lDoors){
			leftDoorStatus.setText("Closed");
		}
		else{
			leftDoorStatus.setText("Open");
		}
		
	}
	
	public void setTemp(int temp){
		TempDisp.setText(train.temperature + " *F");
	}
	
	
	
	
	
	public void changeTrainID(int id){
		if(train != null){
			disconnect();
		}
		
		TrainModel temp = trainModelInstances.connectUI(id, this);
		
		if (temp == null) {
			trainList.setSelectedIndex(0);
		} else {
			train = temp;
		}
		
	}
	
	
	public void disconnect() {
		if (train != null) {
			train.disconnectFromUI();
			train = null;
		}
		
		trainList.setSelectedIndex(0);
	}
	
	
	
	
	
	/**
	 * Create the contents of the frame.
	 */
	public trainModelGUI(TrainModel tm) {
		
		train = tm;
		
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
				//If in Test Mode
				if(testModeButton.isSelected()){

					if(train != null){
						if(train.leftDoorsOpen){
							train.setLeftDoorsOpen(false);
							leftDoorStatus.setText("Closed");
						}
						else{
							train.setLeftDoorsOpen(true);
							leftDoorStatus.setText("Open");
						}	
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
					if(train != null){
						if(train.rightDoorsOpen){
							train.setRightDoorsOpen(false);
							rightDoorStatus.setText("Closed");
						}
						else{
							train.setRightDoorsOpen(true);
							rightDoorStatus.setText("Open");
						}	
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
		if(train != null){
			if(train.leftDoorsOpen){
				leftDoorStatus.setText("Open");
			}
			else{
				leftDoorStatus.setText("Closed");				
			}
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
		if(train != null){
			if(train.rightDoorsOpen){
				rightDoorStatus.setText("Open");
			}
			else{
				rightDoorStatus.setText("Closed");				
			}
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
					if(train != null){
						if(train.lightsOn){
							train.changeLightsStatus(false);
							lightsStatus.setText("Off");
						}
						else{
							train.changeLightsStatus(true);
							lightsStatus.setText("On");
						}
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
		if(train != null){
			if(train.lightsOn){
				lightsStatus.setText("On");
			}
			else{
				lightsStatus.setText("Off");				
			}
		}
		lightsStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lightsStatus.setBounds(190, 511, 125, 40);
		frame.getContentPane().add(lightsStatus);
		lightsStatus.setColumns(10);
		
		
		/*
		 * Power Input Display Box
		 */
		powerInput = new JTextField();
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
					if(train != null){
						//display velocity in velocity display box
						String powerInputString = powerInput.getText();
						double trainPowerInput = Double.parseDouble(powerInputString) * 1000; //convert kilowatts to watts
						System.out.println("Velocity before: "+ train.velocity);
						train.setPower(trainPowerInput);
						
						System.out.println("Power = " +train.power);
						
						System.out.println("Velocity after: "+ train.velocity);
					}
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
		
		
		/*
		 * Current Speed Display
		 */
		currentSpeed = new JTextField();
		currentSpeed.setEditable(false); //editable status
		currentSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		if(train != null){
			currentSpeed.setText(train.velocity * 2.23694 +" mph");
		}
		currentSpeed.setFont(new Font("Courier New", Font.BOLD, 20));
		currentSpeed.setBounds(536, 189, 172, 40);
		frame.getContentPane().add(currentSpeed);
		currentSpeed.setColumns(10);
		
		
		/*
		 * Service Brake Status Display
		 */
		serviceBrakeStatus = new JTextField();
		serviceBrakeStatus.setEditable(false);
		if(train != null){
			if(train.serviceBrakeOn){
				serviceBrakeStatus.setText("Engaged");
			}
			else{
				serviceBrakeStatus.setText("Disengaged");
			}
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
		if(train != null){
			if(train.emergencyBrakeOn){
				emergencyBrakeStatus.setText("Engaged");
			}
			else{
				emergencyBrakeStatus.setText("Disengaged");
			}
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
					if(train != null){
						if(train.emergencyBrakeOn){
							train.setEmergencyBrake(false);
							emergencyBrakeStatus.setText("Disengaged");
						}
						else{
							train.setEmergencyBrake(true);
							emergencyBrakeStatus.setText("Engaged");
						}
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
					if(train != null){
						if(train.serviceBrakeOn){
							train.setServiceBrake(false);
							serviceBrakeStatus.setText("Disengaged");
						}
						else{
							train.setServiceBrake(true);
							serviceBrakeStatus.setText("Engaged");
						}
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
					if(train != null){
						JOptionPane.showMessageDialog(parent, "Danger!! Engine Failure!!");	
						train.initFailureProtocol();
						emergencyBrakeControl.setSelected(true);
						lightsControl.setSelected(true);
					}
				}
			}
		});
		engineFailureButton.setForeground(Color.RED);
		engineFailureButton.setFont(new Font("Courier New", Font.BOLD, 16));
		engineFailureButton.setBounds(41, 298, 260, 30);
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
					if(train != null){	
						JOptionPane.showMessageDialog(parent, "Danger!! Brake Failure!!");
						train.initFailureProtocol();
						emergencyBrakeControl.setSelected(true);
						lightsControl.setSelected(true);					
					}
				}
			}
		});
		brakeFailureButton.setForeground(Color.RED);
		brakeFailureButton.setFont(new Font("Courier New", Font.BOLD, 16));
		brakeFailureButton.setBounds(41, 329, 260, 30);
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
					if(train != null){
						JOptionPane.showMessageDialog(parent, "Danger!! Signal Pickup Failure!!");
						train.initFailureProtocol();
						emergencyBrakeControl.setSelected(true);
						lightsControl.setSelected(true);
					}				
				}
			}
		});
		signalPickupFailure.setForeground(Color.RED);
		signalPickupFailure.setFont(new Font("Courier New", Font.BOLD, 16));
		signalPickupFailure.setBounds(41, 360, 260, 30);
		frame.getContentPane().add(signalPickupFailure);
		
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
		if(train != null){
			CrewDisp.setText(Integer.toString(train.crewCount));
		}
		CrewDisp.setBounds(536, 269, 172, 40);
		frame.getContentPane().add(CrewDisp);
		CrewDisp.setColumns(10);
		
		speedLimitDisp = new JTextField();
		speedLimitDisp.setEditable(false);
		if(train != null){
			speedLimitDisp.setText(Double.toString(train.speedLimit) + " mph");
		}
		speedLimitDisp.setFont(new Font("Courier New", Font.BOLD, 20));
		speedLimitDisp.setHorizontalAlignment(SwingConstants.CENTER);
		speedLimitDisp.setBounds(536, 229, 172, 40);
		frame.getContentPane().add(speedLimitDisp);
		speedLimitDisp.setColumns(10);
		
		PassengerDisp = new JTextField();
		PassengerDisp.setFont(new Font("Courier New", Font.BOLD, 20));
		PassengerDisp.setHorizontalAlignment(SwingConstants.CENTER);
		PassengerDisp.setEditable(false);
		if(train != null){
			PassengerDisp.setText(Integer.toString(train.passengerCount));
		}
		PassengerDisp.setBounds(536, 309, 172, 40);
		frame.getContentPane().add(PassengerDisp);
		PassengerDisp.setColumns(10);
		
		TempDisp = new JTextField();
		TempDisp.setFont(new Font("Courier New", Font.BOLD, 20));
		TempDisp.setHorizontalAlignment(SwingConstants.CENTER);
		TempDisp.setEditable(false);
		if(train != null){
			TempDisp.setText(Integer.toString(train.temperature) + " *F");
		}
		TempDisp.setBounds(536, 349, 172, 40);
		frame.getContentPane().add(TempDisp);
		TempDisp.setColumns(10);
		
		NextBlockDisp = new JTextField();
		NextBlockDisp.setHorizontalAlignment(SwingConstants.CENTER);
		NextBlockDisp.setFont(new Font("Courier New", Font.BOLD, 20));
		NextBlockDisp.setEditable(false);
		NextBlockDisp.setBounds(575, 511, 133, 40);
		frame.getContentPane().add(NextBlockDisp);
		NextBlockDisp.setColumns(10);
		if(train != null){
			NextBlockDisp.setText(Integer.toString(train.nextBlockNum));
		}
		
		NextBlockStatusDisp = new JTextField();
		if(train != null){
			NextBlockStatusDisp.setText(train.trainLine);
		}
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
		ElevationDisp.setFont(new Font("Courier New", Font.BOLD, 20));
		ElevationDisp.setEditable(false);
		ElevationDisp.setColumns(10);
		ElevationDisp.setBounds(575, 591, 133, 40);
		frame.getContentPane().add(ElevationDisp);
		if(train != null){
			ElevationDisp.setText(Double.toString(train.elevation) + " ft");
		}
		
		CurrBlockDisp = new JTextField();
		CurrBlockDisp.setHorizontalAlignment(SwingConstants.CENTER);
		CurrBlockDisp.setFont(new Font("Courier New", Font.BOLD, 20));
		CurrBlockDisp.setEditable(false);
		CurrBlockDisp.setColumns(10);
		CurrBlockDisp.setBounds(575, 471, 133, 40);
		frame.getContentPane().add(CurrBlockDisp);
		if(train != null){
			CurrBlockDisp.setText(Integer.toString(train.curBlockNum));
		}
		
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
		
		lineColor = new JTextField();
		lineColor.setEditable(false);
		lineColor.setText("Line:");
		lineColor.setHorizontalAlignment(SwingConstants.RIGHT);
		lineColor.setFont(new Font("Courier New", Font.BOLD, 18));
		lineColor.setColumns(10);
		lineColor.setBounds(362, 551, 213, 40);
		frame.getContentPane().add(lineColor);
		
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
		if(train != null){
			currentTrainPower.setText(Double.toString(train.power));
		}

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
		
		trainList.setBounds(575, 61, 133, 40);
		frame.getContentPane().add(trainList);
		trainList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (trainList.getSelectedIndex() == 0) {
					disconnect();
				} else {
					int tempid = (int) trainList.getSelectedItem();
					boolean change = false;
					
					if (train == null) change = true;
					if (train != null)
						if (train.trainID != tempid)
							change = true;
						
					if (change) changeTrainID(tempid);
				}
			}});
		
		frame.setVisible(true);

	}
}
