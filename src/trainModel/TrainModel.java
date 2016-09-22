package trainModel;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TrainModel {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainModel window = new TrainModel();
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
	public TrainModel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 681, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel doorControl = new JPanel();
		doorControl.setBounds(10, 386, 150, 50);
		frame.getContentPane().add(doorControl);
		
		JPanel setpointSpeed = new JPanel();
		setpointSpeed.setBounds(10, 127, 150, 50);
		frame.getContentPane().add(setpointSpeed);
		
		JPanel brakeCommand = new JPanel();
		brakeCommand.setBounds(10, 217, 150, 50);
		frame.getContentPane().add(brakeCommand);
		
		JPanel speedLimit = new JPanel();
		speedLimit.setBounds(505, 127, 150, 50);
		frame.getContentPane().add(speedLimit);
		
		JPanel currentSpeed = new JPanel();
		currentSpeed.setBounds(505, 217, 150, 50);
		frame.getContentPane().add(currentSpeed);
		
		JPanel elevation = new JPanel();
		elevation.setBounds(505, 300, 150, 50);
		frame.getContentPane().add(elevation);
		
		JPanel locationInfo = new JPanel();
		locationInfo.setBounds(505, 386, 150, 50);
		frame.getContentPane().add(locationInfo);
		
		JPanel passengerCount = new JPanel();
		passengerCount.setBounds(261, 300, 150, 50);
		frame.getContentPane().add(passengerCount);
		
		JPanel temperature = new JPanel();
		temperature.setBounds(261, 487, 150, 50);
		frame.getContentPane().add(temperature);
		
		JPanel trackCircuitInfo = new JPanel();
		trackCircuitInfo.setBounds(505, 487, 150, 50);
		frame.getContentPane().add(trackCircuitInfo);
		
		JPanel crewCount = new JPanel();
		crewCount.setBounds(261, 386, 150, 50);
		frame.getContentPane().add(crewCount);
		
		JPanel emergencyBrake = new JPanel();
		emergencyBrake.setBounds(10, 300, 150, 50);
		frame.getContentPane().add(emergencyBrake);
		
		JPanel lightsControl = new JPanel();
		lightsControl.setBounds(10, 487, 150, 50);
		frame.getContentPane().add(lightsControl);
		
		JLabel setpointLabel = new JLabel("Setpoint Speed (mph)");
		setpointLabel.setLabelFor(setpointSpeed);
		setpointLabel.setBounds(10, 113, 150, 14);
		frame.getContentPane().add(setpointLabel);
		
		JLabel brakeLabel = new JLabel("Brake Command");
		brakeLabel.setLabelFor(brakeCommand);
		brakeLabel.setBounds(10, 201, 150, 14);
		frame.getContentPane().add(brakeLabel);
		
		JLabel eBrakeLabel = new JLabel("Emergency Brake");
		eBrakeLabel.setLabelFor(emergencyBrake);
		eBrakeLabel.setBounds(10, 286, 150, 14);
		frame.getContentPane().add(eBrakeLabel);
		
		JLabel lblDoorControl = new JLabel("Door Control");
		lblDoorControl.setLabelFor(doorControl);
		lblDoorControl.setBounds(10, 372, 150, 14);
		frame.getContentPane().add(lblDoorControl);
		
		JLabel lblLights = new JLabel("Lights");
		lblLights.setLabelFor(lightsControl);
		lblLights.setBounds(10, 470, 150, 14);
		frame.getContentPane().add(lblLights);
		
		JLabel lblPassengerCount = new JLabel("Passenger Count");
		lblPassengerCount.setLabelFor(passengerCount);
		lblPassengerCount.setBounds(261, 286, 150, 14);
		frame.getContentPane().add(lblPassengerCount);
		
		JLabel lblCrewCount = new JLabel("Crew Count");
		lblCrewCount.setLabelFor(crewCount);
		lblCrewCount.setBounds(261, 372, 150, 14);
		frame.getContentPane().add(lblCrewCount);
		
		JLabel lblTemperaturef = new JLabel("Temperature (*F)");
		lblTemperaturef.setLabelFor(temperature);
		lblTemperaturef.setBounds(261, 470, 150, 14);
		frame.getContentPane().add(lblTemperaturef);
		
		JLabel lblSpeedLimitmph = new JLabel("Speed Limit (mph)");
		lblSpeedLimitmph.setLabelFor(speedLimit);
		lblSpeedLimitmph.setBounds(505, 113, 150, 14);
		frame.getContentPane().add(lblSpeedLimitmph);
		
		JLabel lblCurrentSpeedmph = new JLabel("Current Speed (mph)");
		lblCurrentSpeedmph.setLabelFor(currentSpeed);
		lblCurrentSpeedmph.setBounds(505, 201, 150, 14);
		frame.getContentPane().add(lblCurrentSpeedmph);
		
		JLabel lblElevationft = new JLabel("Elevation (ft)");
		lblElevationft.setLabelFor(elevation);
		lblElevationft.setBounds(505, 286, 150, 14);
		frame.getContentPane().add(lblElevationft);
		
		JLabel lblNewLabel = new JLabel("Location");
		lblNewLabel.setLabelFor(locationInfo);
		lblNewLabel.setBounds(505, 372, 150, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTrackCircuitInfo = new JLabel("Track Circuit Info");
		lblTrackCircuitInfo.setLabelFor(trackCircuitInfo);
		lblTrackCircuitInfo.setBounds(505, 470, 150, 14);
		frame.getContentPane().add(lblTrackCircuitInfo);
		
		JLabel lblTrainModel = new JLabel("Train Model");
		lblTrainModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrainModel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTrainModel.setLabelFor(frame);
		lblTrainModel.setBounds(261, 11, 150, 50);
		frame.getContentPane().add(lblTrainModel);
	}
}
