package trackModel;
import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.Font;
import java.awt.Toolkit;

public class trainModelUI {

	private JFrame frmTrackModelGui;
	private JLabel textField_4;
	private JLabel textField_5;
	private JLabel textField_6;
	private JLabel textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trainModelUI window = new trainModelUI();
					window.frmTrackModelGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public trainModelUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrackModelGui = new JFrame();
		frmTrackModelGui.setIconImage(Toolkit.getDefaultToolkit().getImage(trainModelUI.class.getResource("/shared/TTE.png")));
		frmTrackModelGui.setFont(new Font("Arial", Font.PLAIN, 14));
		frmTrackModelGui.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		frmTrackModelGui.setTitle("Track Model");
		frmTrackModelGui.setBounds(100, 100, 500, 350);
		frmTrackModelGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JFrame parent = new JFrame();

		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 11));
		frmTrackModelGui.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		
		JPanel overview = new JPanel();
		overview.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Overview", null, overview, null);
		overview.setLayout(null);
		
		JLabel lblSelectLineTo = new JLabel("Select Line To View:");
		lblSelectLineTo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSelectLineTo.setBounds(10, 14, 118, 14);
		overview.add(lblSelectLineTo);
		
		JComboBox lineComboBoxView = new JComboBox();
		lineComboBoxView.setFont(new Font("Arial", Font.PLAIN, 11));
		lineComboBoxView.setBounds(138, 11, 55, 20);
		overview.add(lineComboBoxView);
		lineComboBoxView.addItem("Red");
		lineComboBoxView.addItem("Blue");

		
		JLabel label_5 = new JLabel("Grade:");
		label_5.setFont(new Font("Arial", Font.PLAIN, 11));
		label_5.setBounds(203, 14, 48, 14);
		overview.add(label_5);
		
		textField_4 = new JLabel("0.0");
		textField_4.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_4.setBounds(261, 14, 65, 20);
		overview.add(textField_4);
		
		JLabel label_6 = new JLabel("Direction:");
		label_6.setFont(new Font("Arial", Font.PLAIN, 11));
		label_6.setBounds(317, 14, 65, 14);
		overview.add(label_6);
		
		textField_5 = new JLabel("0.0");
		textField_5.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_5.setBounds(404, 11, 65, 20);
		overview.add(textField_5);
		
		textField_6 = new JLabel("0.0");
		textField_6.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_6.setBounds(404, 44, 65, 20);
		overview.add(textField_6);
		
		JLabel label_7 = new JLabel("Speed Limit:");
		label_7.setFont(new Font("Arial", Font.PLAIN, 11));
		label_7.setBounds(317, 46, 77, 14);
		overview.add(label_7);
		
		textField_7 = new JLabel("0.0");
		textField_7.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_7.setBounds(261, 44, 65, 20);
		overview.add(textField_7);
		
		JLabel label_8 = new JLabel("Elevation:");
		label_8.setFont(new Font("Arial", Font.PLAIN, 11));
		label_8.setBounds(203, 47, 63, 14);
		overview.add(label_8);
		
		JComboBox blockComboBoxView = new JComboBox();
		blockComboBoxView.setFont(new Font("Arial", Font.PLAIN, 11));
		blockComboBoxView.setBounds(138, 42, 55, 20);
		overview.add(blockComboBoxView);
		 for(int i=1; i<200; i++){
			 blockComboBoxView.addItem(i);
        }
		
		JLabel lblSelectBlockTo_1 = new JLabel("Select Block To View:");
		lblSelectBlockTo_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSelectBlockTo_1.setBounds(10, 47, 118, 14);
		overview.add(lblSelectBlockTo_1);
		
		JButton getData = new JButton("Get Data");
		getData.setFont(new Font("Arial", Font.PLAIN, 11));
		getData.setBounds(10, 246, 200, 23);
		overview.add(getData);
		
		JButton btnRefreshData = new JButton("Refresh Data");
		btnRefreshData.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRefreshData.setBounds(269, 246, 200, 23);
		overview.add(btnRefreshData);
		
		JPanel config = new JPanel();
		config.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("Config Tool", null, config, null);
		config.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Line To Modify:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 10, 129, 14);
		config.add(lblNewLabel);
		
		JComboBox lineComboBox = new JComboBox();
		lineComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		lineComboBox.setBounds(138, 7, 45, 20);
		lineComboBox.addItem("Red");
		lineComboBox.addItem("Blue");
		config.add(lineComboBox);
		
		JLabel lblSelectBlockTo = new JLabel("Select Block To Modify:");
		lblSelectBlockTo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSelectBlockTo.setBounds(10, 43, 129, 14);
		config.add(lblSelectBlockTo);
		
		JComboBox railComboBox = new JComboBox();
		railComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		railComboBox.setBounds(138, 40, 45, 20);
		 for(int i=1; i<100; i++){
			 railComboBox.addItem(i);
        }
		config.add(railComboBox);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGrade.setBounds(208, 10, 33, 14);
		config.add(lblGrade);
		
		JLabel lblElevation = new JLabel("Elevation:");
		lblElevation.setFont(new Font("Arial", Font.PLAIN, 11));
		lblElevation.setBounds(208, 43, 48, 14);
		config.add(lblElevation);
		
		JTextField lblNewLabel_1 = new JTextField("0.0");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(261, 10, 65, 20);
		config.add(lblNewLabel_1);
		
		JTextField label = new JTextField("0.0");
		label.setFont(new Font("Arial", Font.PLAIN, 11));
		label.setBounds(261, 40, 65, 20);
		config.add(label);
		
		JTextField label_1 = new JTextField("0.0");
		label_1.setFont(new Font("Arial", Font.PLAIN, 11));
		label_1.setBounds(404, 40, 65, 20);
		config.add(label_1);
		
		JLabel lblSpeedLimit = new JLabel("Speed Limit:");
		lblSpeedLimit.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSpeedLimit.setBounds(336, 43, 58, 14);
		config.add(lblSpeedLimit);
		
		JLabel lblDirection = new JLabel("Direction:");
		lblDirection.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDirection.setBounds(336, 10, 46, 14);
		config.add(lblDirection);
		
		JTextField label_4 = new JTextField("0.0");
		label_4.setFont(new Font("Arial", Font.PLAIN, 11));
		label_4.setBounds(404, 7, 65, 20);
		config.add(label_4);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSave.setBounds(10, 230, 459, 42);
		config.add(btnSave);

		JPanel failurePanel = new JPanel();
		tabbedPane.addTab("Murphy's Failures", null, failurePanel, null);
		failurePanel.setLayout(new GridLayout(1, 3, 0, 0));	
		
		JButton breakRail = new JButton("Break Rail");
		breakRail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String railToBreak = JOptionPane.showInputDialog(parent, "What rail would you like to break?", null);			
		        }
		});
		failurePanel.add(breakRail);
		
		JButton powerFailure = new JButton("Cut Power");
		powerFailure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cutPowerConfirm = JOptionPane.showInputDialog(parent, "Are you sure you want to cut the power? (y/n)", null);			
			}
		});
		failurePanel.add(powerFailure);
		
		JButton circuitFailure = new JButton("Break Circuit");
		circuitFailure.setFont(new Font("Arial", Font.PLAIN, 11));
		circuitFailure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String breakCircuitConfirm = JOptionPane.showInputDialog(parent, "Are you sure you want to break the circuit? (y/n)", null);			
			}
		});
		failurePanel.add(circuitFailure);

	}
}
