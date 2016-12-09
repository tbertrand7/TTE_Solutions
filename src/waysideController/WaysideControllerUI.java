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
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
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
import javax.swing.JComboBox;

public class WaysideControllerUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtOn;
	private JTextField txtUp;
	private WaysideController wc;
	private static WaysideControllerInterface WCI;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WCI = WaysideControllerInterface.getInstance();
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

	/**
	 * Create the frame.
	 */
	public WaysideControllerUI() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		setTitle("Wayside Controller");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 507, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(6, 261, 97, 25);
		contentPane.add(btnUpdate);
				
		JComboBox<String> waysideIDChoice = new JComboBox<String>();
		waysideIDChoice.setBackground(Color.WHITE);
		waysideIDChoice.setBounds(6, 43, 97, 34);
		contentPane.add(waysideIDChoice);
		//waysideIDChoice.addItem("Red 1");
		waysideIDChoice.addItem("Red 2");
		
		waysideIDChoice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					switch(waysideIDChoice.getSelectedItem().toString())
					{
						case "Red 1":
							wc = WCI.WC[0];
							break;
						case "Red 2":
							wc = WCI.WC[1];
							break;
						case "Green 1":
							wc = WCI.WC[2];
							break;
						case "Green 2":
							wc = WCI.WC[3];
							break;
					}
					btnUpdate.doClick();
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
		JComboBox<String> trainIDChoice = new JComboBox<String>();
		trainIDChoice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		trainIDChoice.setBounds(20, 33, 179, 37);
		trackOccupancy.add(trainIDChoice);
		
		//Display for the current block of the train
		JLabel lblBlockNumber = new JLabel("Block Number:");
		lblBlockNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBlockNumber.setBounds(20, 83, 122, 14);
		trackOccupancy.add(lblBlockNumber);
		
		TextField trainBlock = new TextField();
		trainBlock.setFont(new Font("Dialog", Font.PLAIN, 26));
		trainBlock.setBackground(Color.WHITE);
		trainBlock.setEditable(false);
		trainBlock.setBounds(20, 108, 179, 40);
		trackOccupancy.add(trainBlock);
		
		//Update the block field when a train is selected
		trainIDChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = trainIDChoice.getSelectedItem().toString();
				if(!selected.equals("No trains to display"))
				{
					selected = selected.replaceAll("Train ", "");
					int trainNum = Integer.parseInt(selected);
					trainBlock.setText(""+wc.trains.get(trainNum));
				}
			}
		});
		
		//switch position panel
		JPanel switchPositionPanel = new JPanel();
		switchPositionPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tabbedPane.addTab("Switch Positions", null, switchPositionPanel, null);
		switchPositionPanel.setLayout(null);
		
		//choose which switch you want to see the position of
		JComboBox<String> switchPositionChoice = new JComboBox<String>();
		switchPositionChoice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		switchPositionChoice.setBounds(12, 47, 181, 35);
		switchPositionPanel.add(switchPositionChoice);
		
		
		JLabel lblSwitchNumber = new JLabel("Switch Number:");
		lblSwitchNumber.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSwitchNumber.setBounds(12, 13, 181, 28);
		switchPositionPanel.add(lblSwitchNumber);
		
		JLabel lblSwitchPosition = new JLabel("Switch Position:");
		lblSwitchPosition.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSwitchPosition.setBounds(12, 111, 181, 28);
		switchPositionPanel.add(lblSwitchPosition);
		
		JTextField switchPos = new JTextField();
		switchPos.setBackground(Color.WHITE);
		switchPos.setFont(new Font("Tahoma", Font.PLAIN, 19));
		switchPos.setBounds(12, 144, 181, 35);
		switchPositionPanel.add(switchPos);
		switchPos.setColumns(10);
		
		switchPositionChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sw = switchPositionChoice.getSelectedItem().toString();
				if(!sw.equals("No Switches to Display"))
				{
					sw = sw.replaceAll("Switch ", "");
					Integer[] position = wc.controlledSwitches.get(Integer.parseInt(sw));
					Integer connectBlock;
					if(position.equals(1))
					{
						connectBlock = position[3];
					}
					else
					{
						connectBlock = position[2];
					}
					switchPos.setText(position[1] + " connects to " + connectBlock);
				}
			}
		});
		
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
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPositionChoice.removeAllItems();
				if(wc.controlledSwitches.size() == 0)
				{
					switchPositionChoice.addItem("No Switches to Display");
					switchPositionChoice.setEnabled(false);
				}
				else
				{
					for(Integer i : wc.controlledSwitches.keySet())
					{
						switchPositionChoice.addItem("Switch "+i);
					}
				}
				
				trainIDChoice.removeAllItems();
				if(wc.trains.size() == 0)
				{
					trainIDChoice.addItem("No trains to display");
					trainIDChoice.setEnabled(false);
				}
				else
				{
					for(Integer i : wc.trains.keySet())
					{
						trainIDChoice.addItem("Train "+i);
					}
				}
			}
		});
	}
}
