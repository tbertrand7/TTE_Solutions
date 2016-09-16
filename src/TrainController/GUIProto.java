package TrainController;

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

public class GUIProto extends JFrame {

	private JPanel contentPane;
	private JTextField SpeedLimit;
	private JTextField SpeedCurr;
	private JTextField SpeedReq;
	private JTextField txtDoors;
	private JTextField TempCurr;
	private JTextField TempReq;
	
	public JComboBox<Integer> TrainSelect; //selects which train to get information from
	private JTextField PowerCurr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIProto frame = new GUIProto();
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
	public GUIProto() {
		setTitle("Train Controller");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIProto.class.getResource("/TrainController/computer1.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<Integer> TrainSelect = new JComboBox<Integer>();
		TrainSelect.setMaximumRowCount(100);
		TrainSelect.setBounds(10, 11, 162, 20);
		contentPane.add(TrainSelect);
		//Quick check of dynamic addItem()s to ComboBox, delete later*******************************
		TrainSelect.addItem(new Integer(300000));
		TrainSelect.addItem(new Integer(300100));
		TrainSelect.addItem(new Integer(300300));
		TrainSelect.addActionListener(new ActionListener() {
			int i = 400000;
			public void actionPerformed(ActionEvent e) {
				System.out.println("Item select: " + TrainSelect.getSelectedItem());
				TrainSelect.addItem(new Integer(i));
				++i;
			}
		});
		//*******************************************************************************************
		
		SpeedLimit = new JTextField();
		SpeedLimit.setEditable(false);
		SpeedLimit.setBounds(528, 83, 86, 20);
		contentPane.add(SpeedLimit);
		SpeedLimit.setColumns(10);
		
		JLabel lblSpeedLimit = new JLabel("SPEED LIMIT");
		lblSpeedLimit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedLimit.setBounds(528, 66, 86, 14);
		contentPane.add(lblSpeedLimit);
		
		SpeedCurr = new JTextField();
		SpeedCurr.setHorizontalAlignment(SwingConstants.CENTER);
		SpeedCurr.setEditable(false);
		SpeedCurr.setBounds(528, 131, 86, 20);
		contentPane.add(SpeedCurr);
		SpeedCurr.setColumns(10);
		
		JLabel lblCurrentSpeed = new JLabel("Current Speed");
		lblCurrentSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSpeed.setBounds(528, 114, 86, 14);
		contentPane.add(lblCurrentSpeed);
		
		SpeedReq = new JTextField();
		SpeedReq.setHorizontalAlignment(SwingConstants.CENTER);
		SpeedReq.setBounds(528, 186, 86, 20);
		contentPane.add(SpeedReq);
		SpeedReq.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Request Speed");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(528, 169, 86, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnSpeedReq = new JButton("Go");
		btnSpeedReq.setBounds(624, 185, 55, 23);
		contentPane.add(btnSpeedReq);
		
		JButton btnEmergencyBrake = new JButton("Emergency Brake");
		btnEmergencyBrake.setBackground(UIManager.getColor("Button.background"));
		btnEmergencyBrake.setForeground(Color.RED);
		btnEmergencyBrake.setBounds(513, 217, 122, 23);
		contentPane.add(btnEmergencyBrake);
		
		JButton imgLight = new JButton("");
		imgLight.setBackground(UIManager.getColor("Button.disabledShadow"));
		imgLight.setEnabled(false);
		imgLight.setIcon(new ImageIcon(GUIProto.class.getResource("/TrainController/LightOnTiny.png")));
		imgLight.setBounds(119, 66, 43, 46);
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
		tglbtnLights.setBounds(10, 66, 86, 46);
		contentPane.add(tglbtnLights);
		
		txtDoors = new JTextField();
		txtDoors.setHorizontalAlignment(SwingConstants.CENTER);
		txtDoors.setEditable(false);
		txtDoors.setText("Closed");
		txtDoors.setBounds(103, 150, 75, 20);
		contentPane.add(txtDoors);
		txtDoors.setColumns(10);
		
		JToggleButton tglbtnDoors = new JToggleButton("Doors");
		tglbtnDoors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnDoors.isSelected()) {
					txtDoors.setText("Open");
				} else {
					txtDoors.setText("Closed");
				}
			}
		});
		tglbtnDoors.setBounds(10, 137, 86, 46);
		contentPane.add(tglbtnDoors);
		
		TempCurr = new JTextField();
		TempCurr.setHorizontalAlignment(SwingConstants.CENTER);
		TempCurr.setEditable(false);
		TempCurr.setColumns(10);
		TempCurr.setBounds(10, 231, 86, 20);
		contentPane.add(TempCurr);
		
		JLabel lblCurrentTemp = new JLabel("Current Temp");
		lblCurrentTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentTemp.setBounds(10, 214, 86, 14);
		contentPane.add(lblCurrentTemp);
		
		TempReq = new JTextField();
		TempReq.setHorizontalAlignment(SwingConstants.CENTER);
		TempReq.setColumns(10);
		TempReq.setBounds(10, 277, 86, 20);
		contentPane.add(TempReq);
		
		JLabel lblRequestTemp = new JLabel("Request Temp");
		lblRequestTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequestTemp.setBounds(10, 262, 86, 14);
		contentPane.add(lblRequestTemp);
		
		JButton btnTempReq = new JButton("Go");
		btnTempReq.setBounds(106, 276, 55, 23);
		contentPane.add(btnTempReq);
		
		JTextArea AnnCurr = new JTextArea();
		AnnCurr.setEditable(false);
		AnnCurr.setLineWrap(true);
		AnnCurr.setBounds(227, 338, 225, 33);
		contentPane.add(AnnCurr);
		
		JLabel lblCurrentAnnouncement = new JLabel("Current Announcement");
		lblCurrentAnnouncement.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentAnnouncement.setBounds(227, 323, 225, 14);
		contentPane.add(lblCurrentAnnouncement);
		
		JLabel lblCustomAnnouncement = new JLabel("Custom Announcement");
		lblCustomAnnouncement.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomAnnouncement.setBounds(227, 386, 225, 14);
		contentPane.add(lblCustomAnnouncement);
		
		JTextArea AnnCust = new JTextArea();
		AnnCust.setToolTipText("");
		AnnCust.setLineWrap(true);
		AnnCust.setBounds(227, 401, 225, 33);
		contentPane.add(AnnCust);
		
		JButton btnAnnCustReq = new JButton("Go");
		btnAnnCustReq.setBounds(462, 401, 64, 33);
		contentPane.add(btnAnnCustReq);
		
		JButton btnAnnNextReq = new JButton("Make \"Next Stop\" Announcement");
		btnAnnNextReq.setBounds(227, 443, 225, 23);
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
		txtMessages.setBounds(227, 66, 225, 230);
		contentPane.add(txtMessages);
		
		JLabel lblSystemMessages = new JLabel("System Messages");
		lblSystemMessages.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystemMessages.setBounds(227, 41, 225, 14);
		contentPane.add(lblSystemMessages);
		
		JToggleButton tglbtnAutomatic = new JToggleButton("Automatic");
		tglbtnAutomatic.setSelected(true);
		
		tglbtnAutomatic.setBounds(486, 11, 92, 20);
		contentPane.add(tglbtnAutomatic);
		
		JToggleButton tglbtnManual = new JToggleButton("Manual");
		tglbtnManual.setBounds(577, 11, 92, 20);
		contentPane.add(tglbtnManual);
		
		tglbtnAutomatic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnAutomatic.isSelected()) { //deselects Manual button, disables Manual buttons
					tglbtnManual.setSelected(false);
					tglbtnLights.setEnabled(false);
					tglbtnDoors.setEnabled(false);
					btnTempReq.setEnabled(false);
					btnAnnCustReq.setEnabled(false);
					btnAnnNextReq.setEnabled(false);
					btnSpeedReq.setEnabled(false);
					//btnEmergencyBrake.setEnabled(false); //need this? or should we always be able to emergency brake?
				} else { //selects Manual button, enables Manual buttons
					tglbtnManual.setSelected(true);
					tglbtnLights.setEnabled(true);
					tglbtnDoors.setEnabled(true);
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
					tglbtnDoors.setEnabled(true);
					btnTempReq.setEnabled(true);
					btnAnnCustReq.setEnabled(true);
					btnAnnNextReq.setEnabled(true);
					btnSpeedReq.setEnabled(true);
				} else { //selects Automatic button, disables Manual buttons
					tglbtnAutomatic.setSelected(true);
					tglbtnLights.setEnabled(false);
					tglbtnDoors.setEnabled(false);
					btnTempReq.setEnabled(false);
					btnAnnCustReq.setEnabled(false);
					btnAnnNextReq.setEnabled(false);
					btnSpeedReq.setEnabled(false);
				}
			}
		});
		
		//Initialize for default Automatic mode ************
		tglbtnLights.setEnabled(false);
		tglbtnDoors.setEnabled(false);
		btnTempReq.setEnabled(false);
		btnAnnCustReq.setEnabled(false);
		btnAnnNextReq.setEnabled(false);
		btnSpeedReq.setEnabled(false);
		//**************************************************
		
		JLabel lblCurrentPower = new JLabel("Current Power");
		lblCurrentPower.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPower.setBounds(528, 262, 86, 14);
		contentPane.add(lblCurrentPower);
		
		PowerCurr = new JTextField();
		PowerCurr.setHorizontalAlignment(SwingConstants.CENTER);
		PowerCurr.setEditable(false);
		PowerCurr.setColumns(10);
		PowerCurr.setBounds(528, 279, 86, 20);
		contentPane.add(PowerCurr);
	}
}
