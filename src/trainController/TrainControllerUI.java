package trainController;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import trainController.TrainControllerInterface.Side;

public class TrainControllerUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private final AnnouncementThread athread;
	
	/**
	 * The TrainControllerInstances class this UI belongs to.
	 */
	private TrainControllerInstances parent;
	/**
	 * The TrainController this UI is connected to.
	 */
	private TrainController controller;
	
	/**
	 * List of all announcements this UI cycles through (excluding station announcements).
	 */
	ArrayList<String> announcementList;
	private int announcementIndex;
	JTextArea AnnCurr;
	
	public void setAnnouncements(char[] text) {
		announcementList.clear();
		StringBuilder sb = new StringBuilder();
		
		for (char c : text) {
			if (c == '\n') { //add current string to the list, clear string builder
				if (sb.length() != 0) { //if string builder is not empty
					announcementList.add(sb.toString());
					sb = new StringBuilder();
				}
			} else { //add char to string builder
				sb.append(c);
			}
		}
	}
	
	public void changeAnnouncement() {
		if (!announcementList.isEmpty()) {
			if (announcementList.size() <= announcementIndex) { //we don't want any NullPointerExceptions...
				announcementIndex = 0;
			}
			AnnCurr.setText(announcementList.get(announcementIndex));
			announcementIndex++;
		}
	}
	
	//Speed/Power
	private JTextField SpeedLimit;
	private JTextField SpeedCurr;
	private JTextField SpeedReq;
	private JTextField PowerCurr;
	
	//Temperature
	private JTextField TempCurr;
	private JTextField TempReq;
	
	//Automatic/Manual mode
	private JToggleButton tglbtnAutomatic;
	private JToggleButton tglbtnManual;
	
	//Lights
	private JToggleButton tglbtnLights;
	private JButton imgLight;
	
	//Doors
	JToggleButton tglbtnRightDoors;
	private JTextField txtRightDoors;
	JToggleButton tglbtnLeftDoors;
	private JTextField txtLeftDoors;
	
	//Train Select
	public JComboBox<Integer> TrainSelect; //selects which train to get information from
	//TODO handle changes to this
	
	//System Messages
	JTextPane txtMessages;
	
	private final int id;
	
	public void setSpeedLimit(double speed) {
		int americanlimit = (int) ((speed / 1609.34) * 3600); //m/s * (1 mi / 1609.34 m) * (3600 s / 1 h)
		SpeedLimit.setText(americanlimit + " MPH");
	}
	
	public void setSpeedCurrent(double speed) {
		int americancurrent = (int) ((speed / 1609.34) * 3600);
		SpeedCurr.setText(americancurrent + " MPH");
	}
	
	public void setPower(double power) {
		PowerCurr.setText((int)power + " W"); //Watts OK for power
	}
	
	public void tunnel(boolean in) {
		if (in) {
			tglbtnLights.setSelected(true);
			imgLight.setEnabled(true);
		} else {
			tglbtnLights.setSelected(false);
			imgLight.setEnabled(false);
		}
	}
	
	public void setRightDoors(boolean open) {
		if (open) {
			tglbtnRightDoors.setSelected(true);
			txtRightDoors.setText("Open");
		} else {
			tglbtnRightDoors.setSelected(false);
			txtRightDoors.setText("Closed");
		}
	}
	
	public void setLeftDoors(boolean open) {
		if (open) {
			tglbtnLeftDoors.setSelected(true);
			txtLeftDoors.setText("Open");
		} else {
			tglbtnLeftDoors.setSelected(false);
			txtLeftDoors.setText("Closed");
		}
	}
	
	public void disconnect() {
		controller.disconnectFromUI();
		controller = null;
		
		athread.stopRun();
		
		//TODO add more to this (change TrainSelect, disable things, etc...)
	}
	
	/**
	 * Override the Window class dispose() function to disconnect this instance from its parent and controller.
	 */
	@Override
	public void dispose() {
		parent = null;
		disconnect();
		
		athread.stopRun();
		
	    super.dispose();
	}
	
	/**
	 * Create the frame.
	 */
	public TrainControllerUI(int instid, TrainControllerInstances tci) {
		id = instid;
		parent = tci;
		
		athread = new AnnouncementThread(); //default - can be changed
		
		announcementList = new ArrayList<String>();
		announcementIndex = 0;
		
		setResizable(false);
		setTitle("Train Controller (Instance " + id + ")");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TrainControllerUI.class.getResource("/TrainController/computer1.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 828, 688);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TrainSelect = new JComboBox<Integer>();
		TrainSelect.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		TrainSelect.setMaximumRowCount(100);
		TrainSelect.setBounds(10, 41, 162, 28);
		contentPane.add(TrainSelect);
		TrainSelect.addItem(new Integer(null));
		TrainSelect.setSelectedIndex(0);
		
		SpeedLimit = new JTextField();
		SpeedLimit.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		SpeedLimit.setHorizontalAlignment(SwingConstants.CENTER);
		SpeedLimit.setEditable(false);
		SpeedLimit.setBounds(589, 129, 134, 33);
		contentPane.add(SpeedLimit);
		SpeedLimit.setColumns(10);
		SpeedLimit.setText("0 MPH");
		
		JLabel lblSpeedLimit = new JLabel("SPEED LIMIT");
		lblSpeedLimit.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblSpeedLimit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedLimit.setBounds(589, 99, 134, 28);
		contentPane.add(lblSpeedLimit);
		
		SpeedCurr = new JTextField();
		SpeedCurr.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		SpeedCurr.setHorizontalAlignment(SwingConstants.CENTER);
		SpeedCurr.setEditable(false);
		SpeedCurr.setBounds(589, 197, 134, 33);
		contentPane.add(SpeedCurr);
		SpeedCurr.setColumns(10);
		SpeedCurr.setText("0 MPH");
		
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
		btnSpeedReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (controller != null) {
					controller.setSpeedRequest(Double.parseDouble(SpeedReq.getText()));
				}
			}
		});
		btnSpeedReq.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnSpeedReq.setBounds(731, 266, 71, 28);
		contentPane.add(btnSpeedReq);
		
		JToggleButton btnEmergencyBrake = new JToggleButton("Emergency Brake");
		btnEmergencyBrake.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnEmergencyBrake.setBackground(UIManager.getColor("Button.background"));
		btnEmergencyBrake.setForeground(Color.RED);
		btnEmergencyBrake.setBounds(562, 343, 189, 29);
		contentPane.add(btnEmergencyBrake);
		
		imgLight = new JButton("");
		imgLight.setBackground(UIManager.getColor("Button.disabledShadow"));
		imgLight.setEnabled(false);
		imgLight.setIcon(new ImageIcon(TrainControllerUI.class.getResource("/TrainController/LightOnTiny.png")));
		imgLight.setBounds(184, 86, 43, 46);
		contentPane.add(imgLight);
		
		tglbtnLights = new JToggleButton("Lights");
		tglbtnLights.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		tglbtnLights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnLights.isSelected()) {
					if (controller != null)
						controller.setLights(true);
						
					imgLight.setEnabled(true);
				} else {
					if (controller != null)
						controller.setLights(false);
					
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
		
		tglbtnRightDoors = new JToggleButton("Right Doors");
		tglbtnRightDoors.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		tglbtnRightDoors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnRightDoors.isSelected()) {
					if (controller != null) {
						if (controller.requestDoors(Side.RIGHT, true)) {
							txtRightDoors.setText("Open");
						} else {
							tglbtnRightDoors.setSelected(false);
							txtMessages.setText(txtMessages.getText() + "Train is not at a complete stop, you can't open the doors!\n");
						}
					} else {
						tglbtnRightDoors.setSelected(false);
						txtMessages.setText(txtMessages.getText() + "Train is not at a complete stop, you can't open the doors!\n");
					}
				} else {
					if (controller != null) {
						if (controller.requestDoors(Side.RIGHT, false)) {
							txtRightDoors.setText("Closed");
						} else {
							tglbtnRightDoors.setSelected(true);
							txtMessages.setText(txtMessages.getText() + "Train is not at a complete stop, you can't open the doors!\n");
						}
					} else {
						tglbtnRightDoors.setSelected(true);
						txtMessages.setText(txtMessages.getText() + "Train is not at a complete stop.\n");
					}
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
		
		tglbtnLeftDoors = new JToggleButton("Left Doors");
		tglbtnLeftDoors.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		tglbtnLeftDoors.setEnabled(false);
		tglbtnLeftDoors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnLeftDoors.isSelected()) {
					if (controller != null) {
						if (controller.requestDoors(Side.LEFT, true)) {
							txtLeftDoors.setText("Open");
						} else {
							tglbtnLeftDoors.setSelected(false);
							txtMessages.setText(txtMessages.getText() + "Train is not at a complete stop, you can't open the doors!\n");
						}
					} else {
						tglbtnLeftDoors.setSelected(false);
						txtMessages.setText(txtMessages.getText() + "Train is not at a complete stop, you can't open the doors!\n");
					}
				} else {
					if (controller != null) {
						if (controller.requestDoors(Side.LEFT, false)) {
							txtLeftDoors.setText("Closed");
						} else {
							tglbtnLeftDoors.setSelected(true);
							txtMessages.setText(txtMessages.getText() + "Train is not at a complete stop.\n");
						}
					} else {
						tglbtnLeftDoors.setSelected(true);
						txtMessages.setText(txtMessages.getText() + "Train is not at a complete stop.\n");
					}
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
		TempCurr.setText("68 \u2109"); //initialization
		
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
		btnTempReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controller != null) {
					if (controller.requestTemperature(Integer.parseInt(TempReq.getText()))) {
						TempCurr.setText(Integer.parseInt(TempReq.getText()) + " \u2109");
					} else {
						txtMessages.setText(txtMessages.getText() + "Temperature is not in the allowed range. Request rejected.\n");
					}
				}
			}
		});
		btnTempReq.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnTempReq.setBounds(106, 339, 66, 29);
		contentPane.add(btnTempReq);
		
		AnnCurr = new JTextArea();
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
		TrainControllerUI instance = this;
		btnAnnCustReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EditAnnouncementsUI frame2 = new EditAnnouncementsUI(instance, announcementList);
					frame2.setVisible(true);
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		btnAnnCustReq.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnAnnCustReq.setBounds(300, 610, 225, 33);
		contentPane.add(btnAnnCustReq);
		
		txtMessages = new JTextPane();
		txtMessages.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		txtMessages.setEditable(false);
		txtMessages.setBounds(300, 119, 225, 330);
		contentPane.add(txtMessages);
		
		JLabel lblSystemMessages = new JLabel("System Messages");
		lblSystemMessages.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblSystemMessages.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystemMessages.setBounds(301, 86, 225, 29);
		contentPane.add(lblSystemMessages);
		
		tglbtnAutomatic = new JToggleButton("Automatic");
		tglbtnAutomatic.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		tglbtnAutomatic.setSelected(true);
		
		tglbtnAutomatic.setBounds(544, 41, 130, 28);
		contentPane.add(tglbtnAutomatic);
		
		tglbtnManual = new JToggleButton("Manual");
		tglbtnManual.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		tglbtnManual.setBounds(672, 41, 130, 28);
		contentPane.add(tglbtnManual);
		
		JToggleButton btnServiceBrake = new JToggleButton("Service Brake");
		btnServiceBrake.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnServiceBrake.setForeground(Color.BLACK);
		btnServiceBrake.setBackground(SystemColor.menu);
		btnServiceBrake.setBounds(562, 305, 189, 27);
		contentPane.add(btnServiceBrake);
		
		tglbtnAutomatic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnAutomatic.isSelected()) { //deselects Manual button, disables Manual buttons
					if (controller != null)
						controller.setAutomatic(true);
					
					tglbtnManual.setSelected(false);
					tglbtnLights.setEnabled(false);
					tglbtnRightDoors.setEnabled(false);
					tglbtnLeftDoors.setEnabled(false);
					btnTempReq.setEnabled(false);
					btnSpeedReq.setEnabled(false);
					btnServiceBrake.setEnabled(false);
				} else {
					tglbtnAutomatic.setSelected(true);
				}
			}
		});
		tglbtnManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnManual.isSelected()) { //deselects Automatic button, enables Manual buttons
					if (controller != null)
						controller.setAutomatic(false);
					
					tglbtnAutomatic.setSelected(false);
					tglbtnLights.setEnabled(true);
					tglbtnRightDoors.setEnabled(true);
					tglbtnLeftDoors.setEnabled(true);
					btnTempReq.setEnabled(true);
					btnSpeedReq.setEnabled(true);
					btnServiceBrake.setEnabled(true);
				} else {
					tglbtnManual.setSelected(true);
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
		PowerCurr.setBounds(589, 421, 134, 33);
		contentPane.add(PowerCurr);
		PowerCurr.setText("0 W");
		
		JLabel TTEIcon = new JLabel("");
		TTEIcon.setIcon(new ImageIcon(TrainControllerUI.class.getResource("/shared/TTESmall.png")));
		TTEIcon.setBounds(10, 578, 64, 65);
		contentPane.add(TTEIcon);
		
		JLabel lblTrainId = new JLabel("Train ID");
		lblTrainId.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblTrainId.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrainId.setBounds(10, 11, 162, 21);
		contentPane.add(lblTrainId);
		
		JToggleButton btnPassengerEmergencyBrake = new JToggleButton("Passenger Emergency Brake");
		btnPassengerEmergencyBrake.setForeground(Color.RED);
		btnPassengerEmergencyBrake.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnPassengerEmergencyBrake.setBackground(SystemColor.menu);
		btnPassengerEmergencyBrake.setBounds(10, 420, 267, 29);
		contentPane.add(btnPassengerEmergencyBrake);
		
		btnEmergencyBrake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (btnEmergencyBrake.isSelected()) {
					if (controller != null) {
						controller.setEmergencyBrake(true);
						controller.setServiceBrake(false);
					}
					btnServiceBrake.setSelected(false);
					btnPassengerEmergencyBrake.setSelected(false);
				} else {
					if (controller != null)
						controller.setEmergencyBrake(false);
				}
			}
		});
		btnPassengerEmergencyBrake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (btnPassengerEmergencyBrake.isSelected()) {
					if (controller != null) {
						controller.setEmergencyBrake(true);
						controller.setServiceBrake(false);
					}
					btnServiceBrake.setSelected(false);
					btnEmergencyBrake.setSelected(false);
				} else {
					if (controller != null)
						controller.setEmergencyBrake(false);
				}
			}
		});
		btnServiceBrake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (btnServiceBrake.isSelected()) {
					if (controller != null)
						controller.setServiceBrake(true);
					
					btnEmergencyBrake.setSelected(false);
					btnPassengerEmergencyBrake.setSelected(false);
				} else {
					if (controller != null)
						controller.setServiceBrake(false);
				}
			}
		});
		
		//Initialization for default Automatic mode ************
		tglbtnLights.setEnabled(false);
		tglbtnRightDoors.setEnabled(false);
		tglbtnLeftDoors.setEnabled(false);
		btnTempReq.setEnabled(false);
		btnSpeedReq.setEnabled(false);
		btnServiceBrake.setEnabled(false);
		//**************************************************
		
		this.setVisible(true);
		athread.start();
	}
	
	/**
	 * Cycles through announcements after a specific amount of time has passed.
	 * @author anna
	 *
	 */
	private class AnnouncementThread extends Thread {
		
		private final long timer; //how long to wait between announcement changes
		
		private boolean proceed;
		
		public AnnouncementThread() {
			timer = 10000; //default 10 seconds
		}
		
		public AnnouncementThread(long time) {
			timer = time;
		}
		
		public void stopRun() {
			proceed = false;
		}
		
		/**
		 * Changes announcements every "timer" milliseconds.
		 */
		public void run() {
			proceed = true;
			
			while (proceed) {
				//Sleep for specified time (not perfect)
				try {
					sleep(timer);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				changeAnnouncement();
			}
		}
		
	}

}
