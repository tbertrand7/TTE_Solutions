package trainController;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JComboBox;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

public class TestPanel extends JFrame {

	private JPanel contentPane;
	private JTextField txtSpeedLimit;
	private JTextField txtSpeedCommand;
	private JTextField txtCurrentSpeed;
	
	private double limit;
	private double command;
	private double current;
	
	private JComboBox<Integer> TrainSelect;
	
	private final TrainControllerInstances parent;
	private final int id;
	private boolean keySetInitialized;
	TestSpeed ts;
	private JTextField txtTemperature;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPanel frame = new TestPanel(0, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void dispose() {
	    ts.stopRun();
	    super.dispose();
	}
	
	public void initialize() {
		updateTrainInstances();
		//parent.setSpeedCommand((int)TrainSelect.getSelectedItem(), 30);
		
		ts = new TestSpeed(this);
		ts.start();
	}
	
	public void checkBrakes() {
		if (parent.getSBrakeEngaged((int)TrainSelect.getSelectedItem()))
			ts.engageServiceBrake();
		else if (parent.getEBrakeEngaged((int)TrainSelect.getSelectedItem()))
			ts.engageEmergencyBrake();
		else
			ts.disengageBrakes();
	}
	
	public void updateTrainInstances() {
		if (!keySetInitialized) {
			TrainSelect.removeAllItems();
			keySetInitialized = true;
		}
		
		while(parent.getKeySet().isEmpty());
		for (int key : parent.getKeySet()) {
			boolean add = true;
			for (int i = 0; i < TrainSelect.getItemCount(); ++i)
				if (TrainSelect.getItemAt(i).equals(key)) { add = false; break;}
			if (add) TrainSelect.addItem(new Integer(key));
		}
		
		TrainSelect.setSelectedIndex(0);
	}
	
	private void sendSpeedLimit() {
		double americanlimit = Double.parseDouble(txtSpeedLimit.getText());
		limit = americanlimit * 1609.34 / 3600;
		parent.setSpeedLimit((int)TrainSelect.getSelectedItem(), limit);
	}
	
	public double getSpeedLimit() {
		return limit;
	}
	
	private void sendSpeedCommand() {
		double americancommand = Double.parseDouble(txtSpeedCommand.getText());
		command = americancommand * 1609.34 / 3600;
		parent.setSpeedCommand((int)TrainSelect.getSelectedItem(), command);
	}
	
	private void sendCurrentSpeed() {
		double americancurrent = Double.parseDouble(txtCurrentSpeed.getText());
		current = americancurrent * 1609.34 / 3600;
		parent.setSpeedCurrent((int)TrainSelect.getSelectedItem(), current);
	}
	
	public void setCurrentSpeed(double speed) {
		current = speed;
		parent.setSpeedCurrent((int)TrainSelect.getSelectedItem(), current);
	}
	
	public double getCurrentSpeed() {
		return current;
	}
	
	public double getPower() {
		return parent.getPower((int)TrainSelect.getSelectedItem());
	}

	/**
	 * Create the frame.
	 */
	public TestPanel(int i, TrainControllerInstances tci) {
		id = i;
		parent = tci;
		keySetInitialized = false;
		
		setTitle("Train Controller Test Panel");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TestPanel.class.getResource("/trainController/computer1.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TrainSelect = new JComboBox<Integer>();
		TrainSelect.addItem(123);
		TrainSelect.setSelectedIndex(0);
		TrainSelect.setMaximumRowCount(100);
		TrainSelect.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		TrainSelect.setBounds(10, 47, 154, 31);
		contentPane.add(TrainSelect);
		
		JLabel lblTrainId = new JLabel("Instance ID");
		lblTrainId.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblTrainId.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrainId.setBounds(10, 11, 154, 25);
		contentPane.add(lblTrainId);
		
		JLabel lblSpeedLimit = new JLabel("Speed Limit");
		lblSpeedLimit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedLimit.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblSpeedLimit.setBounds(10, 102, 154, 31);
		contentPane.add(lblSpeedLimit);
		
		txtSpeedLimit = new JTextField();
		txtSpeedLimit.setBounds(187, 102, 86, 31);
		contentPane.add(txtSpeedLimit);
		txtSpeedLimit.setColumns(10);
		
		JButton btnSendSpeedLimit = new JButton("Send");
		btnSendSpeedLimit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				sendSpeedLimit();
			}
		});
		btnSendSpeedLimit.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnSendSpeedLimit.setBounds(283, 102, 69, 31);
		contentPane.add(btnSendSpeedLimit);
		
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
				parent.setRightDoors((int)TrainSelect.getSelectedItem(), btnOpenRightDoors.isSelected());
			}
		});
		btnOpenRightDoors.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnOpenRightDoors.setBounds(20, 372, 253, 31);
		contentPane.add(btnOpenRightDoors);
		
		JToggleButton btnOpenLeftDoors = new JToggleButton("Open Left Doors");
		btnOpenLeftDoors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				parent.setLeftDoors((int)TrainSelect.getSelectedItem(), btnOpenLeftDoors.isSelected());
			}
		});
		btnOpenLeftDoors.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnOpenLeftDoors.setBounds(20, 404, 253, 31);
		contentPane.add(btnOpenLeftDoors);
		
		JToggleButton tglbtnInTunnel = new JToggleButton("In Tunnel");
		tglbtnInTunnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				parent.setInTunnel((int)TrainSelect.getSelectedItem(), tglbtnInTunnel.isSelected());
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
				parent.setTemp((int)TrainSelect.getSelectedItem(), Integer.parseInt(txtTemperature.getText()));
			}
		});
		btnSendTemp.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnSendTemp.setBounds(283, 252, 69, 31);
		contentPane.add(btnSendTemp);
	}
}
