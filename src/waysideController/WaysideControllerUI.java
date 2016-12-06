package waysideController;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import TTEHome.TTEHomeGUI;

public class WaysideControllerUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtOn;
	private JTextField txtUp;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WaysideControllerUI frame = new WaysideControllerUI();
					frame.setVisible(true);
					//TC_UI_startup popup = new TC_UI_startup();
					//popup.setTitle("PLC Startup");
					//popup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void accessSystem(TTEHome.TTEHomeGUI sys)
	{
		//system = sys;
	}

	/**
	 * Create the frame.
	 */
	public WaysideControllerUI() {
		setTitle("Wayside Controller");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 507, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Choice waysideIDChoice = new Choice();
		waysideIDChoice.setBounds(6, 43, 97, 34);
		contentPane.add(waysideIDChoice);
		waysideIDChoice.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				//TO DO
				switch(waysideIDChoice.getSelectedItem())
				{
					case "Red 1":
						//wayside = TTEHome.TTEHomeGUI.redWC1;
						break;
					case "Red 2":
						//wayside = TTEHome.TTEHomeGUI.redWC2;
						break;
					case "Green 1":
						//wayside = TTEHome.TTEHomeGUI.greenWC1;
						break;
					case "Green 2":
						//wayside = TTEHome.TTEHomeGUI.greenWC2;
						break;
				}
				
			}
		});
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(121, 9, 350, 242);
		contentPane.add(tabbedPane);
		
		JPanel trackOccupancy = new JPanel();
		trackOccupancy.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tabbedPane.addTab("Track Occupancy", null, trackOccupancy, null);
		trackOccupancy.setLayout(null);
		
		JLabel lblTrain = new JLabel("Train:");
		lblTrain.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTrain.setBounds(20, 13, 46, 14);
		trackOccupancy.add(lblTrain);
		
		//Train Choice
		Choice trainIDChoice = new Choice();
		trainIDChoice.setBounds(20, 33, 214, 50);
		trackOccupancy.add(trainIDChoice);
		
		//Display for the current block of the train
		JLabel lblBlockNumber = new JLabel("Block Number:");
		lblBlockNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBlockNumber.setBounds(20, 83, 122, 14);
		trackOccupancy.add(lblBlockNumber);
		
		TextField textField = new TextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 26));
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(20, 108, 179, 40);
		trackOccupancy.add(textField);
		
		//Update the block field when a train is selected
		trainIDChoice.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				//TO DO
			}
		});
		
		//switch position panel
		JPanel switchPositionPanel = new JPanel();
		switchPositionPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tabbedPane.addTab("Switch Positions", null, switchPositionPanel, null);
		switchPositionPanel.setLayout(null);
		
		//choose which switch you want to see the position of
		Choice switchPositionChoice = new Choice();
		switchPositionChoice.setBounds(12, 47, 181, 22);
		switchPositionPanel.add(switchPositionChoice);
		
		JLabel lblSwitchNumber = new JLabel("Switch Number:");
		lblSwitchNumber.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSwitchNumber.setBounds(12, 13, 181, 28);
		switchPositionPanel.add(lblSwitchNumber);
		
		JLabel lblSwitchPosition = new JLabel("Switch Position:");
		lblSwitchPosition.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSwitchPosition.setBounds(12, 111, 181, 28);
		switchPositionPanel.add(lblSwitchPosition);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_1.setBounds(12, 144, 181, 35);
		switchPositionPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSetPosition = new JButton("Set Position");
		btnSetPosition.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSetPosition.setBounds(205, 144, 128, 35);
		switchPositionPanel.add(btnSetPosition);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tabbedPane.addTab("Railway Status", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblRailwayCrossings = new JLabel("Railway Crossings:");
		lblRailwayCrossings.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRailwayCrossings.setBounds(10, 11, 229, 26);
		panel_2.add(lblRailwayCrossings);
		
		JLabel lblLights = new JLabel("Lights:");
		lblLights.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLights.setBounds(53, 67, 48, 20);
		panel_2.add(lblLights);
		
		txtOn = new JTextField();
		txtOn.setBackground(Color.WHITE);
		txtOn.setEditable(false);
		txtOn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtOn.setBounds(111, 64, 56, 26);
		panel_2.add(txtOn);
		txtOn.setColumns(10);
		
		JLabel lblCrossbar = new JLabel("Crossbar:");
		lblCrossbar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCrossbar.setBounds(33, 107, 68, 14);
		panel_2.add(lblCrossbar);
		
		txtUp = new JTextField();
		txtUp.setBackground(Color.WHITE);
		txtUp.setEditable(false);
		txtUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUp.setColumns(10);
		txtUp.setBounds(111, 101, 56, 30);
		panel_2.add(txtUp);
		
		JLabel lblTrackIssues = new JLabel("Broken Rails:");
		lblTrackIssues.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTrackIssues.setBounds(10, 144, 118, 14);
		panel_2.add(lblTrackIssues);
		
		JLabel lblBrokenRails = new JLabel("No Broken Rails Detected");
		lblBrokenRails.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBrokenRails.setBounds(20, 170, 199, 20);
		panel_2.add(lblBrokenRails);
		
		JLabel lblCrossingStatus = new JLabel("Crossing Number:");
		lblCrossingStatus.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblCrossingStatus.setBounds(20, 38, 165, 26);
		panel_2.add(lblCrossingStatus);
		
		Choice choice_2 = new Choice();
		choice_2.setBounds(191, 43, 135, 22);
		panel_2.add(choice_2);
		
		JButton btnLoadPlcProgram = new JButton("Load PLC Program");
		btnLoadPlcProgram.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoadPlcProgram.setBounds(156, 262, 198, 23);
		contentPane.add(btnLoadPlcProgram);
		
		JLabel lblController = new JLabel("Controller:");
		lblController.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblController.setBounds(6, 13, 77, 14);
		contentPane.add(lblController);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(6, 261, 97, 25);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TO DO
			}
		});
		
		
	}
}
