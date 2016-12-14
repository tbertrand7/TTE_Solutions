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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import trainController.TrainController.Side;

import javax.swing.DefaultComboBoxModel;

public class TrainControllerUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private final AnnouncementThread athread;
	
	private boolean announcing;
	
	/**
	 * The TrainControllerInstances class this UI belongs to.
	 */
	private TrainControllerInstances parent;
	/**
	 * The TrainController this UI is connected to.
	 */
	public TrainController controller;
	
	private final int id;
	
	/**
	 * List of all announcements this UI cycles through (excluding station announcements).
	 */
	ArrayList<String> announcementList;
	private int announcementIndex;
	JTextArea AnnCurr;
	
	/**
	 * Sets the list of announcements to be displayed on the train.
	 * @param text - the list of announcements to be displayed, with '\n' as a delimiter
	 */
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
	
	/**
	 * Cycles to the next announcement.
	 */
	public void changeAnnouncement() {
		if (!announcementList.isEmpty()) {
			if (!announcing) {
				if (announcementList.size() <= announcementIndex) { //we don't want any NullPointerExceptions...
					announcementIndex = 0;
				}
				AnnCurr.setText(announcementList.get(announcementIndex));
				announcementIndex++;
			}
		}
	}
	
	/**
	 * Announces the next stop.
	 * @param stationName - name of the station to be announced
	 */
	public void announceStation(String stationName) {
		announcing = true;
		AnnCurr.setText("ATTENTION: Approaching " + stationName + ".");
	}
	
	//Test Panel button
	private JButton btnTestPanel;
	
	/**
	 * Creates and displays a new TestPanel.
	 */
	private void newTestPanel() {
		try {
			TestPanel frame = new TestPanel(id, this);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
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
	public JToggleButton tglbtnLights;
	public JButton imgLight;
	
	//Brakes
	JToggleButton btnEmergencyBrake;
	JToggleButton btnPassengerEmergencyBrake;
	JToggleButton btnServiceBrake;
	
	//Doors
	JToggleButton tglbtnRightDoors;
	private JTextField txtRightDoors;
	JToggleButton tglbtnLeftDoors;
	private JTextField txtLeftDoors;
	
	//Temperature and speed request buttons
	JButton btnTempReq;
	JButton btnSpeedReq;
	
	//Train Select
	public JComboBox<Integer> TrainSelect; //selects which train to get information from
	
	/**
	 * Add train ID to the drop-down.
	 * @param id
	 */
	public void addTrainID(int id) {
		TrainSelect.removeItem(id);
		TrainSelect.addItem(id);
	}
	
	/**
	 * Remove train ID from the drop-down.
	 * @param id
	 */
	public void deleteTrainID(int id) {
		TrainSelect.removeItem(id);
	}
	
	/**
	 * Attempt to change the train this UI is connected to.
	 * @param id - new train's ID
	 */
	public void changeTrainID(int id) {
		if (controller != null)
			disconnect();
		
		TrainController temp = parent.connectUI(id, this);
		
		if (temp == null) {
			TrainSelect.setSelectedIndex(0);
		} else {
			controller = temp;
		}
	}
	
	//System Messages
	JTextArea txtMessages;
	
	/**
	 * Display a message in the system messages box.
	 * @param message - message to be displayed
	 */
	public void message(String message) {
		txtMessages.setText(txtMessages.getText() + message);
	}
	
	/**
	 * Display a new speed limit.
	 * @param speed - new speed limit to be displayed, in m/s
	 */
	public void setSpeedLimit(double speed) {
		int americanlimit = (int) ((speed / 1609.34) * 3600); //m/s * (1 mi / 1609.34 m) * (3600 s / 1 h)
		SpeedLimit.setText(americanlimit + " MPH");
	}
	
	/**
	 * Display a new current speed.
	 * @param speed - new current speed to be displayed, in m/s
	 */
	public void setSpeedCurrent(double speed) {
		int americancurrent = (int) ((speed / 1609.34) * 3600);
		SpeedCurr.setText(americancurrent + " MPH");
	}
	
	/**
	 * Display a new power.
	 * @param power - new power to be displayed, in Watts
	 */
	public void setPower(double power) {
		if (power < 5000 && power > -5000)
			PowerCurr.setText((int)power + " W");
		else
			PowerCurr.setText((int)(power/1000) + " kW");
	}
	
	/**
	 * Sets lights to on or off.
	 * @param in - true if in a tunnel, false otherwise
	 */
	public void tunnel(boolean in) {
		if (in) {
			tglbtnLights.setSelected(true);
			imgLight.setEnabled(true);
		} else {
			tglbtnLights.setSelected(false);
			imgLight.setEnabled(false);
		}
	}
	
	/**
	 * Sets right doors to open or closed.
	 * @param open - true if doors are open, false otherwise
	 */
	public void setRightDoors(boolean open) {
		if (open) {
			tglbtnRightDoors.setSelected(true);
			txtRightDoors.setText("Open");
		} else {
			tglbtnRightDoors.setSelected(false);
			txtRightDoors.setText("Closed");
		}
	}
	
	/**
	 * Sets left doors to open or closed.
	 * @param open - true if doors are open, false otherwise
	 */
	public void setLeftDoors(boolean open) {
		if (open) {
			tglbtnLeftDoors.setSelected(true);
			txtLeftDoors.setText("Open");
		} else {
			tglbtnLeftDoors.setSelected(false);
			txtLeftDoors.setText("Closed");
		}
	}
	
	/**
	 * Set door status.
	 * @param left - true if left doors are open, false otherwise
	 * @param right - true if left doors are open, false otherwise
	 */
	public void setDoorsDirect(boolean left, boolean right) {
		announcing = false;
		
		if (left) {
			tglbtnLeftDoors.setSelected(true);
			txtLeftDoors.setText("Open");
		} else {
			tglbtnLeftDoors.setSelected(false);
			txtLeftDoors.setText("Closed");
		}
		if (right) {
			tglbtnRightDoors.setSelected(true);
			txtRightDoors.setText("Open");
		} else {
			tglbtnRightDoors.setSelected(false);
			txtRightDoors.setText("Closed");
		}
	}
	
	/**
	 * Sets the temperature, if it is safe.
	 * @param temp - temperature to be set
	 */
	public void setTemperature(int temp) {
		if (controller != null) {
			if (!controller.requestTemperature(temp)) {
				txtMessages.setText(txtMessages.getText() + "Temperature is not in the allowed range. Request rejected.\n");
			}
		}
	}
	
	/**
	 * Displays a new temperature.
	 * @param temp - temperature to display
	 */
	public void setTemperatureDirect(int temp) {
		TempCurr.setText(temp + " \u2109");
	}
	
	/**
	 * Sets the passenger emergency brake to false.
	 */
	public void setPassengerEmergencyBrake() {
		btnPassengerEmergencyBrake.setSelected(false);
	}
	
	/**
	 * Sets the emergency brake.
	 * @param set - true if brake is on, false otherwise
	 */
	public void setEmergencyBrake(boolean set) {
		btnEmergencyBrake.setSelected(set);
		if (set) {
			setPassengerEmergencyBrake();
			setServiceBrake(false);
		}
	}
	
	/**
	 * Sets the service brake.
	 * @param set - true if brake is on, false otherwise
	 */
	public void setServiceBrake(boolean set) {
		btnServiceBrake.setSelected(set);
		if (set) {
			setPassengerEmergencyBrake();
			setEmergencyBrake(false);
		}
	}
	
	/**
	 * Sets the mode to automatic.
	 */
	public void setAutomatic() {
		tglbtnAutomatic.setSelected(true);
		tglbtnManual.setSelected(false);
	}
	
	public void disconnect() {
		if (controller != null) {
			controller.disconnectFromUI();
			controller = null;
		}
		
		athread.stopRun();
		
		TrainSelect.setSelectedIndex(0);
		tglbtnLights.setEnabled(false);
		tglbtnRightDoors.setEnabled(false);
		tglbtnLeftDoors.setEnabled(false);
		btnTempReq.setEnabled(false);
		btnSpeedReq.setEnabled(false);
		btnServiceBrake.setEnabled(false);
	}
	
	/**
	 * Override the Window class dispose() function to disconnect this instance from its parent and controller.
	 */
	@Override
	public void dispose() {
		parent.deleteUI(id);
		
		parent = null;
		disconnect();
		
		athread.stopRun();
		
	    super.dispose();
	}
	
	/**
	 * Create the frame.
	 */
	public TrainControllerUI(int instid, TrainControllerInstances tci, boolean test) {
		id = instid;
		parent = tci;
		
		athread = new AnnouncementThread(); //default - can be changed
		
		announcementList = new ArrayList<String>();
		announcementIndex = 0;
		announcing = false;
		
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
		TrainSelect.setModel(new DefaultComboBoxModel(new String[] {"0"}));
		TrainSelect.setSelectedIndex(0);
		TrainSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (TrainSelect.getSelectedIndex() == 0) {
					disconnect();
				} else {
					int tempid = (int) TrainSelect.getSelectedItem();
					boolean change = false;
					
					if (controller == null) change = true;
					if (controller != null)
						if (controller.id != tempid)
							change = true;
						
					if (change) changeTrainID(tempid);
				}
			}
		});
		TrainSelect.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		TrainSelect.setMaximumRowCount(100);
		TrainSelect.setBounds(10, 41, 162, 28);
		contentPane.add(TrainSelect);
		
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
		
		btnSpeedReq = new JButton("Go");
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
		
		btnEmergencyBrake = new JToggleButton("Emergency Brake");
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
		
		btnTempReq = new JButton("Go");
		btnTempReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTemperature(Integer.parseInt(TempReq.getText()));
			}
		});
		btnTempReq.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnTempReq.setBounds(106, 339, 66, 29);
		contentPane.add(btnTempReq);
		
		AnnCurr = new JTextArea();
		AnnCurr.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		AnnCurr.setEditable(false);
		AnnCurr.setLineWrap(true);
		//AnnCurr.setBounds(184, 499, 449, 100);
		
		JScrollPane jspann = new JScrollPane(AnnCurr);
		jspann.setBounds(184, 499, 449, 100);
		
		contentPane.add(jspann);
		
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
		
		txtMessages = new JTextArea();
		txtMessages.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		txtMessages.setEditable(false);
		txtMessages.setLineWrap(true);
		//txtMessages.setBounds(300, 119, 225, 330);
		
		JScrollPane jsptxt = new JScrollPane(txtMessages);
		jsptxt.setBounds(300, 119, 225, 330);
		
		contentPane.add(jsptxt);
		
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
		
		btnServiceBrake = new JToggleButton("Service Brake");
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
		
		btnPassengerEmergencyBrake = new JToggleButton("Passenger Emergency Brake");
		btnPassengerEmergencyBrake.setForeground(Color.RED);
		btnPassengerEmergencyBrake.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnPassengerEmergencyBrake.setBackground(SystemColor.menu);
		btnPassengerEmergencyBrake.setBounds(10, 420, 267, 29);
		contentPane.add(btnPassengerEmergencyBrake);
		
		btnEmergencyBrake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (btnEmergencyBrake.isSelected()) {
					if (controller != null) {
						if (controller.setEmergencyBrake(true)) {
							btnServiceBrake.setSelected(false);
							btnPassengerEmergencyBrake.setSelected(false);
						} else {
							btnEmergencyBrake.setSelected(false);
							txtMessages.setText(txtMessages.getText() + "Brake setting is not allowed at the moment.\n");
						}
					}
					
				} else {
					if (controller != null) {
						if (!controller.setEmergencyBrake(false)) {
							btnEmergencyBrake.setSelected(true);
							txtMessages.setText(txtMessages.getText() + "Brake setting is not allowed at the moment.\n");
						}
					}
				}
			}
		});
		btnPassengerEmergencyBrake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (btnPassengerEmergencyBrake.isSelected()) {
					if (controller != null) {
						if (controller.setEmergencyBrake(true)) {
							btnServiceBrake.setSelected(false);
							btnEmergencyBrake.setSelected(false);
						} else {
							btnPassengerEmergencyBrake.setSelected(false);
							txtMessages.setText(txtMessages.getText() + "Brake setting is not allowed at the moment.\n");
						}
					}
					
				} else {
					if (controller != null) {
						if (!controller.setEmergencyBrake(false)) {
							btnPassengerEmergencyBrake.setSelected(true);
							txtMessages.setText(txtMessages.getText() + "Brake setting is not allowed at the moment.\n");
						}
					}
				}
			}
		});
		btnServiceBrake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (btnServiceBrake.isSelected()) {
					if (controller != null) {
						if (controller.setServiceBrake(true)) {
							btnEmergencyBrake.setSelected(false);
							btnPassengerEmergencyBrake.setSelected(false);
						} else {
							btnServiceBrake.setSelected(false);
							txtMessages.setText(txtMessages.getText() + "Brake setting is not allowed at the moment.\n");
						}
					}
				} else {
					if (controller != null) {
						if (!controller.setServiceBrake(false)) {
							btnServiceBrake.setSelected(true);
							txtMessages.setText(txtMessages.getText() + "Brake setting is not allowed at the moment.\n");
						}
					}
				}
			}
		});
		
		btnTestPanel = new JButton("Test Panel");
		btnTestPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				newTestPanel();
			}
		});
		btnTestPanel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnTestPanel.setBounds(652, 610, 170, 33);
		if (!test) btnTestPanel.setEnabled(false);
		contentPane.add(btnTestPanel);
		
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
