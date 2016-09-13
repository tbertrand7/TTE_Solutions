package trackModel;
import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class trainModelUI {

	private JFrame frmTrackModelGui;

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
		frmTrackModelGui.setResizable(false);
		frmTrackModelGui.setTitle("Track Model");
		frmTrackModelGui.setBounds(100, 100, 500, 350);
		frmTrackModelGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JFrame parent = new JFrame();

		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmTrackModelGui.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel overview = new JPanel();
		overview.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Overview", null, overview, null);
		SpringLayout sl_overview = new SpringLayout();
		overview.setLayout(sl_overview);
		
		JLabel lblSelectLineTo = new JLabel("Select Line To Observe:");
		sl_overview.putConstraint(SpringLayout.NORTH, lblSelectLineTo, 16, SpringLayout.NORTH, overview);
		sl_overview.putConstraint(SpringLayout.WEST, lblSelectLineTo, 10, SpringLayout.WEST, overview);
		overview.add(lblSelectLineTo);
		
		JComboBox  comboBox= new JComboBox();
		sl_overview.putConstraint(SpringLayout.NORTH, comboBox, -3, SpringLayout.NORTH, lblSelectLineTo);
		sl_overview.putConstraint(SpringLayout.WEST, comboBox, 16, SpringLayout.EAST, lblSelectLineTo);
		comboBox.addItem("Red");
		comboBox.addItem("Blue");
		overview.add(comboBox);
		
		JLabel label_3 = new JLabel("Grade:");
		sl_overview.putConstraint(SpringLayout.NORTH, label_3, 0, SpringLayout.NORTH, lblSelectLineTo);
		sl_overview.putConstraint(SpringLayout.WEST, label_3, 20, SpringLayout.EAST, comboBox);
		overview.add(label_3);
		
		JLabel label_5 = new JLabel("0.0");
		sl_overview.putConstraint(SpringLayout.NORTH, label_5, 0, SpringLayout.NORTH, lblSelectLineTo);
		overview.add(label_5);
		
		JLabel label_6 = new JLabel("Direction:");
		sl_overview.putConstraint(SpringLayout.NORTH, label_6, 0, SpringLayout.NORTH, lblSelectLineTo);
		overview.add(label_6);
		
		JLabel label_7 = new JLabel("0.0");
		sl_overview.putConstraint(SpringLayout.WEST, label_5, 0, SpringLayout.WEST, label_7);
		sl_overview.putConstraint(SpringLayout.NORTH, label_7, 46, SpringLayout.NORTH, overview);
		sl_overview.putConstraint(SpringLayout.WEST, label_7, 453, SpringLayout.WEST, overview);
		overview.add(label_7);
		
		JLabel label_8 = new JLabel("0.0");
		sl_overview.putConstraint(SpringLayout.NORTH, label_8, 0, SpringLayout.NORTH, label_7);
		overview.add(label_8);
		
		JLabel label_9 = new JLabel("Speed Limit:");
		sl_overview.putConstraint(SpringLayout.WEST, label_6, 0, SpringLayout.WEST, label_9);
		sl_overview.putConstraint(SpringLayout.NORTH, label_9, 0, SpringLayout.NORTH, label_7);
		sl_overview.putConstraint(SpringLayout.EAST, label_9, -35, SpringLayout.WEST, label_7);
		overview.add(label_9);
		
		JLabel label_10 = new JLabel("0.0");
		sl_overview.putConstraint(SpringLayout.WEST, label_8, 0, SpringLayout.WEST, label_10);
		sl_overview.putConstraint(SpringLayout.NORTH, label_10, 0, SpringLayout.NORTH, lblSelectLineTo);
		sl_overview.putConstraint(SpringLayout.EAST, label_10, -62, SpringLayout.WEST, label_6);
		overview.add(label_10);
		
		JLabel label_11 = new JLabel("Elevation:");
		sl_overview.putConstraint(SpringLayout.NORTH, label_11, 0, SpringLayout.NORTH, label_7);
		sl_overview.putConstraint(SpringLayout.WEST, label_11, 0, SpringLayout.WEST, label_3);
		overview.add(label_11);
		
		JComboBox comboBox_1 = new JComboBox();
		sl_overview.putConstraint(SpringLayout.NORTH, comboBox_1, -3, SpringLayout.NORTH, label_7);
		sl_overview.putConstraint(SpringLayout.WEST, comboBox_1, 0, SpringLayout.WEST, comboBox);
		 for(int i=1; i<200; i++){
			 comboBox_1.addItem(i);
        }
		overview.add(comboBox_1);
		
		JLabel lblSelectBlockTo_1 = new JLabel("Select Block To Observe:");
		sl_overview.putConstraint(SpringLayout.NORTH, lblSelectBlockTo_1, 0, SpringLayout.NORTH, label_7);
		sl_overview.putConstraint(SpringLayout.WEST, lblSelectBlockTo_1, 0, SpringLayout.WEST, lblSelectLineTo);
		overview.add(lblSelectBlockTo_1);
		
		JPanel config = new JPanel();
		config.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("Config Tool", null, config, null);
		SpringLayout sl_config = new SpringLayout();
		config.setLayout(sl_config);
		
		JLabel lblNewLabel = new JLabel("Select Line To Modify:");
		sl_config.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, config);
		sl_config.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, config);
		sl_config.putConstraint(SpringLayout.EAST, lblNewLabel, 115, SpringLayout.WEST, config);
		config.add(lblNewLabel);
		
		JComboBox lineComboBox = new JComboBox();
		sl_config.putConstraint(SpringLayout.NORTH, lineComboBox, 7, SpringLayout.NORTH, config);
		lineComboBox.addItem("Red");
		lineComboBox.addItem("Blue");
		config.add(lineComboBox);
		
		JLabel lblSelectBlockTo = new JLabel("Select Block To Modify:");
		sl_config.putConstraint(SpringLayout.NORTH, lblSelectBlockTo, 19, SpringLayout.SOUTH, lblNewLabel);
		config.add(lblSelectBlockTo);
		
		JComboBox railComboBox = new JComboBox();
		sl_config.putConstraint(SpringLayout.NORTH, railComboBox, 13, SpringLayout.SOUTH, lineComboBox);
		sl_config.putConstraint(SpringLayout.WEST, railComboBox, 138, SpringLayout.WEST, config);
		sl_config.putConstraint(SpringLayout.WEST, lblSelectBlockTo, -128, SpringLayout.WEST, railComboBox);
		sl_config.putConstraint(SpringLayout.EAST, lblSelectBlockTo, -18, SpringLayout.WEST, railComboBox);
		sl_config.putConstraint(SpringLayout.WEST, lineComboBox, 0, SpringLayout.WEST, railComboBox);
		 for(int i=1; i<100; i++){
			 railComboBox.addItem(i);
        }
		config.add(railComboBox);
		
		JLabel lblGrade = new JLabel("Grade:");
		sl_config.putConstraint(SpringLayout.NORTH, lblGrade, 10, SpringLayout.NORTH, config);
		sl_config.putConstraint(SpringLayout.WEST, lblGrade, 25, SpringLayout.EAST, lineComboBox);
		config.add(lblGrade);
		
		JLabel lblElevation = new JLabel("Elevation:");
		sl_config.putConstraint(SpringLayout.NORTH, lblElevation, 19, SpringLayout.SOUTH, lblGrade);
		sl_config.putConstraint(SpringLayout.WEST, lblElevation, 0, SpringLayout.WEST, lblGrade);
		config.add(lblElevation);
		
		JTextField lblNewLabel_1 = new JTextField("0.0");
		sl_config.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 10, SpringLayout.NORTH, config);
		sl_config.putConstraint(SpringLayout.EAST, lblNewLabel_1, -163, SpringLayout.EAST, config);
		config.add(lblNewLabel_1);
		
		JTextField label = new JTextField("0.0");
		sl_config.putConstraint(SpringLayout.NORTH, label, 19, SpringLayout.SOUTH, lblNewLabel_1);
		sl_config.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, lblNewLabel_1);
		config.add(label);
		
		JTextField label_1 = new JTextField("0.0");
		sl_config.putConstraint(SpringLayout.NORTH, label_1, 43, SpringLayout.NORTH, config);
		sl_config.putConstraint(SpringLayout.EAST, label_1, -10, SpringLayout.EAST, config);
		config.add(label_1);
		
		JLabel lblSpeedLimit = new JLabel("Speed Limit:");
		sl_config.putConstraint(SpringLayout.NORTH, lblSpeedLimit, 43, SpringLayout.NORTH, config);
		sl_config.putConstraint(SpringLayout.EAST, lblSpeedLimit, -46, SpringLayout.EAST, config);
		config.add(lblSpeedLimit);
		
		JLabel lblDirection = new JLabel("Direction:");
		sl_config.putConstraint(SpringLayout.WEST, lblDirection, 0, SpringLayout.WEST, lblSpeedLimit);
		sl_config.putConstraint(SpringLayout.SOUTH, lblDirection, 0, SpringLayout.SOUTH, lblNewLabel);
		config.add(lblDirection);
		
		JTextField label_4 = new JTextField("0.0");
		sl_config.putConstraint(SpringLayout.WEST, label_4, 0, SpringLayout.WEST, label_1);
		sl_config.putConstraint(SpringLayout.SOUTH, label_4, 0, SpringLayout.SOUTH, lblNewLabel);
		config.add(label_4);
		
		JButton btnSave = new JButton("Save");
		sl_config.putConstraint(SpringLayout.NORTH, btnSave, -52, SpringLayout.SOUTH, config);
		sl_config.putConstraint(SpringLayout.WEST, btnSave, 0, SpringLayout.WEST, lblNewLabel);
		sl_config.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, config);
		sl_config.putConstraint(SpringLayout.EAST, btnSave, 0, SpringLayout.EAST, label_1);
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
		circuitFailure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String breakCircuitConfirm = JOptionPane.showInputDialog(parent, "Are you sure you want to break the circuit? (y/n)", null);			
			}
		});
		failurePanel.add(circuitFailure);

	}
}
