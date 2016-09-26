package trainController;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JScrollBar;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.Box;
import javax.swing.JSlider;

import java.awt.SystemColor;
import java.awt.Font;

public class trainControllerUI extends JFrame {

	private JPanel contentPane;
	private JTextField SpeedLimit;
	private JTextField SpeedCurr;
	private JTextField SpeedReq;
	private JTextField txtRightDoors;
	private JTextField TempCurr;
	private JTextField TempReq;
	
	public JComboBox<Integer> TrainSelect; //selects which train to get information from
	private JTextField PowerCurr;
	private JTextField txtLeftDoors;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trainControllerUI frame = new trainControllerUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public trainControllerUI() {
		setTitle("Train Controller");
		setIconImage(Toolkit.getDefaultToolkit().getImage(trainControllerUI.class.getResource("/TrainController/computer1.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 828, 688);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<Integer> TrainSelect = new JComboBox<Integer>();
		TrainSelect.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		TrainSelect.setMaximumRowCount(100);
		TrainSelect.setBounds(10, 41, 162, 28);
		contentPane.add(TrainSelect);
		//Quick check of dynamic addItem()s to ComboBox, delete later*******************************
		TrainSelect.addItem(new Integer(300000));
		TrainSelect.addItem(new Integer(300100));
		TrainSelect.addItem(new Integer(300300));
		TrainSelect.addActionListener(new ActionListener() {
			int i = 400000;
			public void actionPerformed(ActionEvent e) {
				TrainSelect.addItem(new Integer(i));
				++i;
			}
		});
		TrainSelect.setSelectedIndex(0);
		//*******************************************************************************************
		
		SpeedLimit = new JTextField();
		SpeedLimit.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		SpeedLimit.setHorizontalAlignment(SwingConstants.CENTER);
		SpeedLimit.setEditable(false);
		SpeedLimit.setBounds(589, 129, 134, 28);
		contentPane.add(SpeedLimit);
		SpeedLimit.setColumns(10);
		SpeedLimit.setText("40 mph"); //FOR SHOWING OFF PURPOSES ONLY, DELETE LATER
		
		JLabel lblSpeedLimit = new JLabel("SPEED LIMIT");
		lblSpeedLimit.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblSpeedLimit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedLimit.setBounds(589, 99, 134, 28);
		contentPane.add(lblSpeedLimit);
		
		SpeedCurr = new JTextField();
		SpeedCurr.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		SpeedCurr.setHorizontalAlignment(SwingConstants.CENTER);
		SpeedCurr.setEditable(false);
		SpeedCurr.setBounds(589, 197, 134, 28);
		contentPane.add(SpeedCurr);
		SpeedCurr.setColumns(10);
		SpeedCurr.setText("35 mph"); //FOR SHOWING OFF PURPOSES ONLY, DELETE LATER
		
		JLabel lblCurrentSpeed = new JLabel("Current Speed");
		lblCurrentSpeed.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblCurrentSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSpeed.setBounds(589, 168, 134, 28);
		contentPane.add(lblCurrentSpeed);
		
		SpeedReq = new JTextField();
		SpeedReq.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		SpeedReq.setHorizontalAlignment(SwingConstants.CENTER);
		SpeedReq.setBounds(589, 266, 134, 28);
		contentPane.add(SpeedReq);
		SpeedReq.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Request Speed");
		lblNewLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(589, 236, 134, 28);
		contentPane.add(lblNewLabel);
		
		JButton btnSpeedReq = new JButton("Go");
		btnSpeedReq.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnSpeedReq.setBounds(731, 266, 71, 28);
		contentPane.add(btnSpeedReq);
		
		JButton btnEmergencyBrake = new JButton("Emergency Brake");
		btnEmergencyBrake.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnEmergencyBrake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEmergencyBrake.setBackground(UIManager.getColor("Button.background"));
		btnEmergencyBrake.setForeground(Color.RED);
		btnEmergencyBrake.setBounds(562, 343, 189, 29);
		contentPane.add(btnEmergencyBrake);
		
		JButton imgLight = new JButton("");
		imgLight.setBackground(UIManager.getColor("Button.disabledShadow"));
		imgLight.setEnabled(false);
		imgLight.setIcon(new ImageIcon(trainControllerUI.class.getResource("/TrainController/LightOnTiny.png")));
		imgLight.setBounds(184, 86, 43, 46);
		contentPane.add(imgLight);
		
		JToggleButton tglbtnLights = new JToggleButton("Lights");
		tglbtnLights.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		tglbtnLights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnLights.isSelected()) {
					imgLight.setEnabled(true);
				} else {
					imgLight.setEnabled(false);
				}
			}
		});
		tglbtnLights.setBounds(10, 86, 148, 46);
		contentPane.add(tglbtnLights);
		
		txtRightDoors = new JTextField();
		txtRightDoors.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		txtRightDoors.setHorizontalAlignment(SwingConstants.CENTER);
		txtRightDoors.setEditable(false);
		txtRightDoors.setText("Closed");
		txtRightDoors.setBounds(168, 157, 75, 28);
		contentPane.add(txtRightDoors);
		txtRightDoors.setColumns(10);
		
		JToggleButton tglbtnRightDoors = new JToggleButton("Right Doors");
		tglbtnRightDoors.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		tglbtnRightDoors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnRightDoors.isSelected()) {
					txtRightDoors.setText("Open");
				} else {
					txtRightDoors.setText("Closed");
				}
			}
		});
		tglbtnRightDoors.setBounds(10, 157, 148, 28);
		contentPane.add(tglbtnRightDoors);
		
		txtLeftDoors = new JTextField();
		txtLeftDoors.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		txtLeftDoors.setText("Closed");
		txtLeftDoors.setHorizontalAlignment(SwingConstants.CENTER);
		txtLeftDoors.setEditable(false);
		txtLeftDoors.setColumns(10);
		txtLeftDoors.setBounds(168, 189, 75, 28);
		contentPane.add(txtLeftDoors);
		
		JToggleButton tglbtnLeftDoors = new JToggleButton("Left Doors");
		tglbtnLeftDoors.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		tglbtnLeftDoors.setEnabled(false);
		tglbtnLeftDoors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnLeftDoors.isSelected()) {
					txtLeftDoors.setText("Open");
				} else {
					txtLeftDoors.setText("Closed");
				}
			}
		});
		tglbtnLeftDoors.setBounds(10, 189, 148, 28);
		contentPane.add(tglbtnLeftDoors);
		
		TempCurr = new JTextField();
		TempCurr.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		TempCurr.setHorizontalAlignment(SwingConstants.CENTER);
		TempCurr.setEditable(false);
		TempCurr.setColumns(10);
		TempCurr.setBounds(10, 266, 148, 28);
		contentPane.add(TempCurr);
		TempCurr.setText("68 \u2109"); //FOR SHOWING OFF PURPOSES ONLY, DELETE LATER
		
		JLabel lblCurrentTemp = new JLabel("Current Temp");
		lblCurrentTemp.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblCurrentTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentTemp.setBounds(10, 234, 148, 21);
		contentPane.add(lblCurrentTemp);
		
		TempReq = new JTextField();
		TempReq.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		TempReq.setHorizontalAlignment(SwingConstants.CENTER);
		TempReq.setColumns(10);
		TempReq.setBounds(10, 340, 86, 28);
		contentPane.add(TempReq);
		
		JLabel lblRequestTemp = new JLabel("Request Temp");
		lblRequestTemp.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblRequestTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequestTemp.setBounds(10, 305, 148, 24);
		contentPane.add(lblRequestTemp);
		
		JButton btnTempReq = new JButton("Go");
		btnTempReq.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnTempReq.setBounds(106, 339, 66, 29);
		contentPane.add(btnTempReq);
		
		JTextArea AnnCurr = new JTextArea();
		AnnCurr.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		AnnCurr.setEditable(false);
		AnnCurr.setLineWrap(true);
		AnnCurr.setBounds(184, 499, 449, 100);
		contentPane.add(AnnCurr);
		
		JLabel lblCurrentAnnouncement = new JLabel("Current Announcement");
		lblCurrentAnnouncement.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblCurrentAnnouncement.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentAnnouncement.setBounds(300, 460, 225, 28);
		contentPane.add(lblCurrentAnnouncement);
		
		JButton btnAnnCustReq = new JButton("Edit Announcements");
		btnAnnCustReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					editAnnouncementsUI frame2 = new editAnnouncementsUI();
					frame2.setVisible(true);
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		btnAnnCustReq.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnAnnCustReq.setBounds(300, 610, 225, 33);
		contentPane.add(btnAnnCustReq);
		
		JTextPane txtMessages = new JTextPane();
		txtMessages.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		//Dynamic test of system messages, delete later*********************************************
		txtMessages.addMouseListener(new MouseAdapter() {
			int n = 1, i = 0;
			public void mouseClicked(MouseEvent arg0) {
				if (i <= 2) {
					if (i != 1)
						txtMessages.setText(txtMessages.getText() + "This is message #" + (n++) + ".\n");
					else
						txtMessages.setText(txtMessages.getText() + "THIS IS MESSAGE #" + (n++) + " AND IT IS CRITICAL!\n");
					++i;
				} else {
					txtMessages.setText("");
					i = 0;
				}
			}
		});
		//*******************************************************************************************
		txtMessages.setEditable(false);
		txtMessages.setBounds(300, 119, 225, 330);
		contentPane.add(txtMessages);
		
		JLabel lblSystemMessages = new JLabel("System Messages");
		lblSystemMessages.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblSystemMessages.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystemMessages.setBounds(301, 86, 225, 29);
		contentPane.add(lblSystemMessages);
		
		JToggleButton tglbtnAutomatic = new JToggleButton("Automatic");
		tglbtnAutomatic.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		tglbtnAutomatic.setSelected(true);
		
		tglbtnAutomatic.setBounds(544, 41, 130, 28);
		contentPane.add(tglbtnAutomatic);
		
		JToggleButton tglbtnManual = new JToggleButton("Manual");
		tglbtnManual.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		tglbtnManual.setBounds(672, 41, 130, 28);
		contentPane.add(tglbtnManual);
		
		JButton btnServiceBrake = new JButton("Service Brake");
		btnServiceBrake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnServiceBrake.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnServiceBrake.setForeground(Color.BLACK);
		btnServiceBrake.setBackground(SystemColor.menu);
		btnServiceBrake.setBounds(562, 305, 189, 27);
		contentPane.add(btnServiceBrake);
		
		tglbtnAutomatic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnAutomatic.isSelected()) { //deselects Manual button, disables Manual buttons
					tglbtnManual.setSelected(false);
					tglbtnLights.setEnabled(false);
					tglbtnRightDoors.setEnabled(false);
					tglbtnLeftDoors.setEnabled(false);
					btnTempReq.setEnabled(false);
					btnSpeedReq.setEnabled(false);
					btnServiceBrake.setEnabled(false);
				} else { //selects Manual button, enables Manual buttons
					tglbtnManual.setSelected(true);
					tglbtnLights.setEnabled(true);
					tglbtnRightDoors.setEnabled(true);
					tglbtnLeftDoors.setEnabled(true);
					btnTempReq.setEnabled(true);
					btnSpeedReq.setEnabled(true);
					btnServiceBrake.setEnabled(true);
				}
			}
		});
		tglbtnManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnManual.isSelected()) { //deselects Automatic button, enables Manual buttons
					tglbtnAutomatic.setSelected(false);
					tglbtnLights.setEnabled(true);
					tglbtnRightDoors.setEnabled(true);
					tglbtnLeftDoors.setEnabled(true);
					btnTempReq.setEnabled(true);
					btnSpeedReq.setEnabled(true);
					btnServiceBrake.setEnabled(true);
				} else { //selects Automatic button, disables Manual buttons
					tglbtnAutomatic.setSelected(true);
					tglbtnLights.setEnabled(false);
					tglbtnRightDoors.setEnabled(false);
					tglbtnLeftDoors.setEnabled(false);
					btnTempReq.setEnabled(false);
					btnSpeedReq.setEnabled(false);
					btnServiceBrake.setEnabled(false);
				}
			}
		});
		
		JLabel lblCurrentPower = new JLabel("Current Power");
		lblCurrentPower.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblCurrentPower.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPower.setBounds(589, 397, 134, 21);
		contentPane.add(lblCurrentPower);
		
		PowerCurr = new JTextField();
		PowerCurr.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		PowerCurr.setHorizontalAlignment(SwingConstants.CENTER);
		PowerCurr.setEditable(false);
		PowerCurr.setColumns(10);
		PowerCurr.setBounds(589, 421, 134, 28);
		contentPane.add(PowerCurr);
		PowerCurr.setText("10 W"); //FOR SHOWING OFF PURPOSES ONLY, DELETE LATER (disclaimer: I know nothing about power)
		
		JLabel TTEIcon = new JLabel("");
		TTEIcon.setIcon(new ImageIcon(trainControllerUI.class.getResource("/shared/TTESmall.png")));
		TTEIcon.setBounds(10, 578, 64, 65);
		contentPane.add(TTEIcon);
		
		JLabel lblTrainId = new JLabel("Train ID");
		lblTrainId.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblTrainId.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrainId.setBounds(10, 11, 162, 21);
		contentPane.add(lblTrainId);
		
		//Initialize for default Automatic mode ************
		tglbtnLights.setEnabled(false);
		tglbtnRightDoors.setEnabled(false);
		tglbtnLeftDoors.setEnabled(false);
		btnTempReq.setEnabled(false);
		btnSpeedReq.setEnabled(false);
		btnServiceBrake.setEnabled(false);
		
		JButton btnPassengerEmergencyBrake = new JButton("Passenger Emergency Brake");
		btnPassengerEmergencyBrake.setForeground(Color.RED);
		btnPassengerEmergencyBrake.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnPassengerEmergencyBrake.setBackground(SystemColor.menu);
		btnPassengerEmergencyBrake.setBounds(10, 420, 267, 29);
		contentPane.add(btnPassengerEmergencyBrake);
		//**************************************************
	}
}
