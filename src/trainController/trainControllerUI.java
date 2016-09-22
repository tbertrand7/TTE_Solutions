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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<Integer> TrainSelect = new JComboBox<Integer>();
		TrainSelect.setMaximumRowCount(100);
		TrainSelect.setBounds(10, 30, 162, 20);
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
		SpeedLimit.setHorizontalAlignment(SwingConstants.CENTER);
		SpeedLimit.setEditable(false);
		SpeedLimit.setBounds(518, 146, 86, 20);
		contentPane.add(SpeedLimit);
		SpeedLimit.setColumns(10);
		SpeedLimit.setText("40 mph"); //FOR SHOWING OFF PURPOSES ONLY, DELETE LATER
		
		JLabel lblSpeedLimit = new JLabel("SPEED LIMIT");
		lblSpeedLimit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedLimit.setBounds(518, 129, 86, 14);
		contentPane.add(lblSpeedLimit);
		
		SpeedCurr = new JTextField();
		SpeedCurr.setHorizontalAlignment(SwingConstants.CENTER);
		SpeedCurr.setEditable(false);
		SpeedCurr.setBounds(518, 194, 86, 20);
		contentPane.add(SpeedCurr);
		SpeedCurr.setColumns(10);
		SpeedCurr.setText("35 mph"); //FOR SHOWING OFF PURPOSES ONLY, DELETE LATER
		
		JLabel lblCurrentSpeed = new JLabel("Current Speed");
		lblCurrentSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSpeed.setBounds(518, 177, 86, 14);
		contentPane.add(lblCurrentSpeed);
		
		SpeedReq = new JTextField();
		SpeedReq.setHorizontalAlignment(SwingConstants.CENTER);
		SpeedReq.setBounds(518, 249, 86, 20);
		contentPane.add(SpeedReq);
		SpeedReq.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Request Speed");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(518, 232, 86, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnSpeedReq = new JButton("Go");
		btnSpeedReq.setBounds(614, 249, 55, 20);
		contentPane.add(btnSpeedReq);
		
		JButton btnEmergencyBrake = new JButton("Emergency Brake");
		btnEmergencyBrake.setBackground(UIManager.getColor("Button.background"));
		btnEmergencyBrake.setForeground(Color.RED);
		btnEmergencyBrake.setBounds(503, 280, 122, 23);
		contentPane.add(btnEmergencyBrake);
		
		JButton imgLight = new JButton("");
		imgLight.setBackground(UIManager.getColor("Button.disabledShadow"));
		imgLight.setEnabled(false);
		imgLight.setIcon(new ImageIcon(trainControllerUI.class.getResource("/TrainController/LightOnTiny.png")));
		imgLight.setBounds(143, 129, 43, 46);
		contentPane.add(imgLight);
		
		JToggleButton tglbtnLights = new JToggleButton("Lights");
		tglbtnLights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnLights.isSelected()) {
					imgLight.setEnabled(true);
				} else {
					imgLight.setEnabled(false);
				}
			}
		});
		tglbtnLights.setBounds(10, 129, 107, 46);
		contentPane.add(tglbtnLights);
		
		txtRightDoors = new JTextField();
		txtRightDoors.setHorizontalAlignment(SwingConstants.CENTER);
		txtRightDoors.setEditable(false);
		txtRightDoors.setText("Closed");
		txtRightDoors.setBounds(127, 200, 75, 20);
		contentPane.add(txtRightDoors);
		txtRightDoors.setColumns(10);
		
		JToggleButton tglbtnRightDoors = new JToggleButton("Right Doors");
		tglbtnRightDoors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnRightDoors.isSelected()) {
					txtRightDoors.setText("Open");
				} else {
					txtRightDoors.setText("Closed");
				}
			}
		});
		tglbtnRightDoors.setBounds(10, 200, 107, 20);
		contentPane.add(tglbtnRightDoors);
		
		txtLeftDoors = new JTextField();
		txtLeftDoors.setText("Closed");
		txtLeftDoors.setHorizontalAlignment(SwingConstants.CENTER);
		txtLeftDoors.setEditable(false);
		txtLeftDoors.setColumns(10);
		txtLeftDoors.setBounds(127, 232, 75, 20);
		contentPane.add(txtLeftDoors);
		
		JToggleButton tglbtnLeftDoors = new JToggleButton("Left Doors");
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
		tglbtnLeftDoors.setBounds(10, 232, 107, 20);
		contentPane.add(tglbtnLeftDoors);
		
		TempCurr = new JTextField();
		TempCurr.setHorizontalAlignment(SwingConstants.CENTER);
		TempCurr.setEditable(false);
		TempCurr.setColumns(10);
		TempCurr.setBounds(10, 294, 86, 20);
		contentPane.add(TempCurr);
		TempCurr.setText("68 \u2109"); //FOR SHOWING OFF PURPOSES ONLY, DELETE LATER
		
		JLabel lblCurrentTemp = new JLabel("Current Temp");
		lblCurrentTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentTemp.setBounds(10, 277, 86, 14);
		contentPane.add(lblCurrentTemp);
		
		TempReq = new JTextField();
		TempReq.setHorizontalAlignment(SwingConstants.CENTER);
		TempReq.setColumns(10);
		TempReq.setBounds(10, 340, 86, 20);
		contentPane.add(TempReq);
		
		JLabel lblRequestTemp = new JLabel("Request Temp");
		lblRequestTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequestTemp.setBounds(10, 325, 86, 14);
		contentPane.add(lblRequestTemp);
		
		JButton btnTempReq = new JButton("Go");
		btnTempReq.setBounds(106, 339, 55, 23);
		contentPane.add(btnTempReq);
		
		JTextArea AnnCurr = new JTextArea();
		AnnCurr.setEditable(false);
		AnnCurr.setLineWrap(true);
		AnnCurr.setBounds(143, 401, 383, 33);
		contentPane.add(AnnCurr);
		
		JLabel lblCurrentAnnouncement = new JLabel("Current Announcement");
		lblCurrentAnnouncement.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentAnnouncement.setBounds(227, 386, 225, 14);
		contentPane.add(lblCurrentAnnouncement);
		
		JLabel lblCustomAnnouncement = new JLabel("Custom Announcement");
		lblCustomAnnouncement.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomAnnouncement.setBounds(227, 449, 225, 14);
		contentPane.add(lblCustomAnnouncement);
		
		JTextArea AnnCust = new JTextArea();
		AnnCust.setToolTipText("");
		AnnCust.setLineWrap(true);
		AnnCust.setBounds(143, 464, 383, 33);
		contentPane.add(AnnCust);
		
		JButton btnAnnCustReq = new JButton("Go");
		btnAnnCustReq.setBounds(550, 464, 64, 33);
		contentPane.add(btnAnnCustReq);
		
		JButton btnAnnNextReq = new JButton("Make \"Next Stop\" Announcement");
		btnAnnNextReq.setBounds(227, 506, 225, 23);
		contentPane.add(btnAnnNextReq);
		
		JTextPane txtMessages = new JTextPane();
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
		txtMessages.setBounds(227, 129, 225, 230);
		contentPane.add(txtMessages);
		
		JLabel lblSystemMessages = new JLabel("System Messages");
		lblSystemMessages.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystemMessages.setBounds(227, 104, 225, 14);
		contentPane.add(lblSystemMessages);
		
		JToggleButton tglbtnAutomatic = new JToggleButton("Automatic");
		tglbtnAutomatic.setSelected(true);
		
		tglbtnAutomatic.setBounds(469, 11, 101, 20);
		contentPane.add(tglbtnAutomatic);
		
		JToggleButton tglbtnManual = new JToggleButton("Manual");
		tglbtnManual.setBounds(568, 11, 101, 20);
		contentPane.add(tglbtnManual);
		
		tglbtnAutomatic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnAutomatic.isSelected()) { //deselects Manual button, disables Manual buttons
					tglbtnManual.setSelected(false);
					tglbtnLights.setEnabled(false);
					tglbtnRightDoors.setEnabled(false);
					tglbtnLeftDoors.setEnabled(false);
					btnTempReq.setEnabled(false);
					btnAnnCustReq.setEnabled(false);
					btnAnnNextReq.setEnabled(false);
					btnSpeedReq.setEnabled(false);
				} else { //selects Manual button, enables Manual buttons
					tglbtnManual.setSelected(true);
					tglbtnLights.setEnabled(true);
					tglbtnRightDoors.setEnabled(true);
					tglbtnLeftDoors.setEnabled(true);
					btnTempReq.setEnabled(true);
					btnAnnCustReq.setEnabled(true);
					btnAnnNextReq.setEnabled(true);
					btnSpeedReq.setEnabled(true);
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
					btnAnnCustReq.setEnabled(true);
					btnAnnNextReq.setEnabled(true);
					btnSpeedReq.setEnabled(true);
				} else { //selects Automatic button, disables Manual buttons
					tglbtnAutomatic.setSelected(true);
					tglbtnLights.setEnabled(false);
					tglbtnRightDoors.setEnabled(false);
					tglbtnLeftDoors.setEnabled(false);
					btnTempReq.setEnabled(false);
					btnAnnCustReq.setEnabled(false);
					btnAnnNextReq.setEnabled(false);
					btnSpeedReq.setEnabled(false);
				}
			}
		});
		
		//Initialize for default Automatic mode ************
		tglbtnLights.setEnabled(false);
		tglbtnRightDoors.setEnabled(false);
		tglbtnLeftDoors.setEnabled(false);
		btnTempReq.setEnabled(false);
		btnAnnCustReq.setEnabled(false);
		btnAnnNextReq.setEnabled(false);
		btnSpeedReq.setEnabled(false);
		//**************************************************
		
		JLabel lblCurrentPower = new JLabel("Current Power");
		lblCurrentPower.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPower.setBounds(518, 325, 86, 14);
		contentPane.add(lblCurrentPower);
		
		PowerCurr = new JTextField();
		PowerCurr.setHorizontalAlignment(SwingConstants.CENTER);
		PowerCurr.setEditable(false);
		PowerCurr.setColumns(10);
		PowerCurr.setBounds(518, 342, 86, 20);
		contentPane.add(PowerCurr);
		PowerCurr.setText("10 W"); //FOR SHOWING OFF PURPOSES ONLY, DELETE LATER (disclaimer: I know nothing about power)
		
		JLabel TTEIcon = new JLabel("");
		TTEIcon.setIcon(new ImageIcon(trainControllerUI.class.getResource("/shared/TTESmall.png")));
		TTEIcon.setBounds(10, 465, 64, 65);
		contentPane.add(TTEIcon);
		
		JSlider sliderSimSpeed = new JSlider();
		sliderSimSpeed.setMaximum(10);
		sliderSimSpeed.setMinimum(1);
		sliderSimSpeed.setValue(1);
		sliderSimSpeed.setSnapToTicks(true);
		sliderSimSpeed.setPaintTicks(true);
		sliderSimSpeed.setPaintLabels(true);
		sliderSimSpeed.setMajorTickSpacing(1);
		sliderSimSpeed.setBounds(469, 62, 200, 38);
		contentPane.add(sliderSimSpeed);
		
		JLabel lblSimulationSpeed = new JLabel("Simulation Speed");
		lblSimulationSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSimulationSpeed.setBounds(469, 42, 200, 14);
		contentPane.add(lblSimulationSpeed);
		
		//Only unnecessary if we find out we need separate simulation speeds on our UIs
		sliderSimSpeed.setVisible(false);
		lblSimulationSpeed.setVisible(false);
		//******************************************************************************
		
		JLabel lblTrainId = new JLabel("Train ID");
		lblTrainId.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrainId.setBounds(10, 11, 162, 14);
		contentPane.add(lblTrainId);
	}
}
