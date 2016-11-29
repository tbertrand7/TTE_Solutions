package trainController;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import trainController.TrainControllerInterface.Side;
import trainController.TrainControllerInterface.Signal;

public class TestPanel extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1832253994295494975L;
	
	private JPanel contentPane;
	private JTextField txtSpeedCommand;
	private JTextField txtCurrentSpeed;
	
	private double command;
	private double current;
	
	private JTextField TrainSelect;
	
	private TrainControllerUI parent;
	private final int id;
	
	TestSpeed ts;
	private JTextField txtTemperature;
	private JTextField txtAuthority;
	private JTextField txtSignal;
	
	@Override
	public void dispose() {
		parent = null;
		
	    ts.stopRun();
	    
	    super.dispose();
	}
	
	public void checkBrakes() {
		if (parent.controller != null) {
			if (parent.controller.getServiceBrake())
				ts.engageServiceBrake();
			else if (parent.controller.getEmergencyBrake())
				ts.engageEmergencyBrake();
			else
				ts.disengageBrakes();
		}
	}
	
	private void sendSpeedCommand() {
		if (parent.controller != null) {
			double americancommand = Double.parseDouble(txtSpeedCommand.getText());
			command = americancommand * 1609.34 / 3600;
			parent.controller.setSpeedCommand(command);
		}
	}
	
	private void sendCurrentSpeed() {
		if (parent.controller != null) {
			double americancurrent = Double.parseDouble(txtCurrentSpeed.getText());
			current = americancurrent * 1609.34 / 3600;
			parent.setSpeedCurrent(current);
			parent.controller.setSpeedCurrent(current);
		}
	}
	
	public void setCurrentSpeed(double speed) {
		if (parent.controller != null) {
			current = speed;
			parent.setSpeedCurrent(current);
			parent.controller.setSpeedCurrent(current);
		}
	}
	
	public double getCurrentSpeed() {
		return current;
	}
	
	public double getPower() {
		if (parent.controller != null)
			return parent.controller.getPower();
		else return -1;
	}

	/**
	 * Create the frame.
	 */
	public TestPanel(int i, TrainControllerUI tcui) {
		id = i;
		parent = tcui;
		
		setTitle("Train Controller Test Panel");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TestPanel.class.getResource("/trainController/computer1.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 726, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TrainSelect = new JTextField();
		TrainSelect.setHorizontalAlignment(SwingConstants.CENTER);
		TrainSelect.setEditable(false);
		TrainSelect.setText(Integer.toString(id));
		TrainSelect.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		TrainSelect.setBounds(10, 47, 154, 31);
		contentPane.add(TrainSelect);
		
		JLabel lblTrainId = new JLabel("Instance ID");
		lblTrainId.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblTrainId.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrainId.setBounds(10, 11, 154, 25);
		contentPane.add(lblTrainId);
		
		JLabel lblSpeedCommand = new JLabel("Speed Command");
		lblSpeedCommand.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedCommand.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblSpeedCommand.setBounds(10, 144, 154, 31);
		contentPane.add(lblSpeedCommand);
		
		txtSpeedCommand = new JTextField();
		txtSpeedCommand.setColumns(10);
		txtSpeedCommand.setBounds(187, 144, 86, 31);
		contentPane.add(txtSpeedCommand);
		
		JButton btnSendSpeedCommand = new JButton("Send");
		btnSendSpeedCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				sendSpeedCommand();
			}
		});
		btnSendSpeedCommand.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnSendSpeedCommand.setBounds(283, 144, 69, 31);
		contentPane.add(btnSendSpeedCommand);
		
		JLabel lblCurrentSpeed = new JLabel("Current Speed");
		lblCurrentSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSpeed.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblCurrentSpeed.setBounds(10, 186, 154, 31);
		contentPane.add(lblCurrentSpeed);
		
		txtCurrentSpeed = new JTextField();
		txtCurrentSpeed.setColumns(10);
		txtCurrentSpeed.setBounds(187, 186, 86, 31);
		contentPane.add(txtCurrentSpeed);
		
		JButton btnSendCurrentSpeed = new JButton("Send");
		btnSendCurrentSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				sendCurrentSpeed();
			}
		});
		btnSendCurrentSpeed.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnSendCurrentSpeed.setBounds(283, 186, 69, 31);
		contentPane.add(btnSendCurrentSpeed);
		
		JToggleButton btnOpenRightDoors = new JToggleButton("Open Right Doors");
		btnOpenRightDoors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (parent.controller != null) {
					if (parent.controller.requestDoors(Side.RIGHT, btnOpenRightDoors.isSelected()))
						parent.setRightDoors(btnOpenRightDoors.isSelected());
				}
			}
		});
		btnOpenRightDoors.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnOpenRightDoors.setBounds(20, 415, 253, 31);
		contentPane.add(btnOpenRightDoors);
		
		JToggleButton btnOpenLeftDoors = new JToggleButton("Open Left Doors");
		btnOpenLeftDoors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (parent.controller != null) {
					if (parent.controller.requestDoors(Side.LEFT, btnOpenLeftDoors.isSelected()))
						parent.setLeftDoors(btnOpenLeftDoors.isSelected());
				}
			}
		});
		btnOpenLeftDoors.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnOpenLeftDoors.setBounds(20, 447, 253, 31);
		contentPane.add(btnOpenLeftDoors);
		
		JToggleButton tglbtnInTunnel = new JToggleButton("In Tunnel");
		tglbtnInTunnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (parent.controller != null)
					parent.controller.setInTunnel(tglbtnInTunnel.isSelected());
			}
		});
		tglbtnInTunnel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		tglbtnInTunnel.setBounds(20, 324, 253, 31);
		contentPane.add(tglbtnInTunnel);
		
		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblTemperature.setBounds(10, 252, 154, 31);
		contentPane.add(lblTemperature);
		
		txtTemperature = new JTextField();
		txtTemperature.setColumns(10);
		txtTemperature.setBounds(187, 252, 86, 31);
		contentPane.add(txtTemperature);
		
		JButton btnSendTemp = new JButton("Send");
		btnSendTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				parent.setTemperature(Integer.parseInt(txtTemperature.getText()));
			}
		});
		btnSendTemp.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnSendTemp.setBounds(283, 252, 69, 31);
		contentPane.add(btnSendTemp);
		
		JToggleButton tglbtnApproachingStation = new JToggleButton("Approaching Station");
		tglbtnApproachingStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (tglbtnApproachingStation.isSelected()) {
					if (parent.controller != null)
						parent.controller.approachStation("Arbitrary Name", Side.RIGHT);
				}
			}
		});
		tglbtnApproachingStation.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		tglbtnApproachingStation.setBounds(20, 356, 253, 31);
		contentPane.add(tglbtnApproachingStation);
		
		JLabel lblAuthority = new JLabel("Authority");
		lblAuthority.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthority.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblAuthority.setBounds(368, 102, 154, 31);
		contentPane.add(lblAuthority);
		
		txtAuthority = new JTextField();
		txtAuthority.setColumns(10);
		txtAuthority.setBounds(545, 102, 86, 31);
		contentPane.add(txtAuthority);
		
		JButton btnSendAuthority = new JButton("Send");
		btnSendAuthority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (parent.controller != null)
					parent.controller.setAuthority(Integer.parseInt(txtAuthority.getText()));
			}
		});
		btnSendAuthority.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnSendAuthority.setBounds(641, 102, 69, 31);
		contentPane.add(btnSendAuthority);
		
		JLabel lblSignal = new JLabel("Signal");
		lblSignal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignal.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblSignal.setBounds(368, 144, 154, 31);
		contentPane.add(lblSignal);
		
		txtSignal = new JTextField();
		txtSignal.setColumns(10);
		txtSignal.setBounds(545, 144, 86, 31);
		contentPane.add(txtSignal);
		
		JButton btnSendSignal = new JButton("Send");
		btnSendSignal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (parent.controller != null) {
					Signal sig = null;
					
					if (txtSignal.getText().compareToIgnoreCase("engine") == 0) {
						sig = Signal.ENGINE_FAILURE;
					} else if (txtSignal.getText().compareToIgnoreCase("rail") == 0) {
						sig = Signal.RAIL_FAILURE;
					} else if (txtSignal.getText().compareToIgnoreCase("signal pickup") == 0) {
						sig = Signal.SIGNAL_PICKUP_FAILURE;
					} else if (txtSignal.getText().compareToIgnoreCase("brake") == 0) {
						sig = Signal.BRAKE_FAILURE;
					}
					
					if (sig != null)
						parent.controller.signal(sig);
				}
			}
		});
		btnSendSignal.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnSendSignal.setBounds(641, 144, 69, 31);
		contentPane.add(btnSendSignal);
		
		ts = new TestSpeed(this);
		ts.start();
	}
}
