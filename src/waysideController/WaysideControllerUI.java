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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import TTEHome.TTEHomeGUI;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class WaysideControllerUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtLightStatus;
	private JTextField txtCrossbarPos;
	private static JButton btnUpdate;
	private WaysideController wc;
	private static WaysideControllerInterface WCI;
	private JTextField txtBroken;

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
		setBounds(100, 100, 616, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdate.setBounds(6, 310, 97, 48);
		contentPane.add(btnUpdate);
				
		JComboBox<String> waysideIDChoice = new JComboBox<String>();
		waysideIDChoice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		waysideIDChoice.setBackground(Color.WHITE);
		waysideIDChoice.setBounds(6, 43, 103, 34);
		contentPane.add(waysideIDChoice);
		waysideIDChoice.addItem("Red 1");
		waysideIDChoice.addItem("Red 2");
		waysideIDChoice.addItem("Green 1");
		waysideIDChoice.addItem("Green 2");
		
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
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabbedPane.setBounds(121, 9, 465, 311);
		contentPane.add(tabbedPane);
		
		JPanel trackOccupancy = new JPanel();
		trackOccupancy.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tabbedPane.addTab("Track Occupancy", null, trackOccupancy, null);
		trackOccupancy.setLayout(null);
		
		JLabel lblTrain = new JLabel("Train:");
		lblTrain.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTrain.setBounds(20, 31, 46, 14);
		trackOccupancy.add(lblTrain);
		
		//Train Choice
		JComboBox<String> trainIDChoice = new JComboBox<String>();
		trainIDChoice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		trainIDChoice.setBounds(20, 58, 179, 37);
		trackOccupancy.add(trainIDChoice);
		
		//Display for the current block of the train
		JLabel lblBlockNumber = new JLabel("Block Number:");
		lblBlockNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBlockNumber.setBounds(20, 130, 122, 14);
		trackOccupancy.add(lblBlockNumber);
		
		TextField trainBlock = new TextField();
		trainBlock.setFont(new Font("Dialog", Font.PLAIN, 26));
		trainBlock.setBackground(Color.WHITE);
		trainBlock.setEditable(false);
		trainBlock.setBounds(20, 157, 179, 40);
		trackOccupancy.add(trainBlock);
		
		//Update the block field when a train is selected
		trainIDChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(trainIDChoice.getItemCount() > 0)
				{
					String selected = trainIDChoice.getSelectedItem().toString();
					if(!selected.equals("No trains to display"))
					{
						selected = selected.replaceAll("Train ", "");
						int trainNum = Integer.parseInt(selected);
						trainBlock.setText(""+wc.trains.get(trainNum));
					}
					else
					{
						trainBlock.setText("");
					}
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
				if(switchPositionChoice.getItemCount() > 0)
				{
					String sw = switchPositionChoice.getSelectedItem().toString();
					if(!sw.equals("No Switches to Display"))
					{
						sw = sw.replaceAll("Switch ", "");
						String[] position = wc.switches.get(Integer.parseInt(sw));
						Integer connectBlock;
						if(position[0].equals("1"))
						{
							switchPos.setText(position[3]);
						}
						else
						{
							switchPos.setText(position[2]);
						}
						
					}
				}
			}
		});
		
		JButton btnSetPosition = new JButton("Change Position");
		btnSetPosition.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSetPosition.setBounds(205, 146, 156, 35);
		switchPositionPanel.add(btnSetPosition);
		btnSetPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sw = switchPositionChoice.getSelectedItem().toString();
				if(!sw.equals("No Switches to Display"))
				{
					sw = sw.replaceAll("Switch ", "");
					wc.setSwitch(Integer.parseInt(sw));
				}
			}
		});
		
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
		lblLights.setBounds(20, 96, 48, 20);
		panel_2.add(lblLights);
		
		txtLightStatus = new JTextField();
		txtLightStatus.setBackground(Color.WHITE);
		txtLightStatus.setEditable(false);
		txtLightStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLightStatus.setBounds(80, 93, 96, 26);
		panel_2.add(txtLightStatus);
		txtLightStatus.setColumns(10);
		
		JLabel lblCrossbar = new JLabel("Crossbar:");
		lblCrossbar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCrossbar.setBounds(20, 146, 68, 14);
		panel_2.add(lblCrossbar);
		
		txtCrossbarPos = new JTextField();
		txtCrossbarPos.setBackground(Color.WHITE);
		txtCrossbarPos.setEditable(false);
		txtCrossbarPos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCrossbarPos.setColumns(10);
		txtCrossbarPos.setBounds(100, 132, 76, 30);
		panel_2.add(txtCrossbarPos);
		
		JLabel lblTrackIssues = new JLabel("Broken Rails:");
		lblTrackIssues.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTrackIssues.setBounds(10, 173, 166, 20);
		panel_2.add(lblTrackIssues);
		
		JLabel lblCrossingStatus = new JLabel("Crossing Number:");
		lblCrossingStatus.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblCrossingStatus.setBounds(20, 45, 165, 26);
		panel_2.add(lblCrossingStatus);
		
		JComboBox<String> railwayCrossingChoice = new JComboBox<String>();
		railwayCrossingChoice.setBounds(188, 45, 142, 27);
		panel_2.add(railwayCrossingChoice);
		railwayCrossingChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(railwayCrossingChoice.getItemCount() > 0 && !railwayCrossingChoice.getSelectedItem().equals("No crossings to display"))
				{
					int crossingBlock = Integer.parseInt(railwayCrossingChoice.getSelectedItem().toString().replace("Block ",""));
					String[] status = wc.crossing.get(crossingBlock);
				
					switch(status[0])
					{
						case "r":
							txtLightStatus.setText("Red");
							break;
						case "g":
							txtLightStatus.setText("Green");
							break;
						case "y":
							txtLightStatus.setText("Yellow");
							break;
					}
					
					if(status[1].equals("u"))
						txtCrossbarPos.setText("Up");
					else
						txtCrossbarPos.setText("Down");
				}
			}
		});
		
		//-------BROKEN RAILS-----------
		JComboBox<String> brokenRailChoice = new JComboBox<String>();
		brokenRailChoice.setBounds(34, 216, 142, 31);
		panel_2.add(brokenRailChoice);
		
		txtBroken = new JTextField();
		txtBroken.setBackground(Color.WHITE);
		txtBroken.setEditable(false);
		txtBroken.setBounds(188, 216, 152, 31);
		panel_2.add(txtBroken);
		txtBroken.setColumns(10);
		brokenRailChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(brokenRailChoice.getItemCount() > 0)
				{
					String selected = brokenRailChoice.getSelectedItem().toString();
					if(!selected.equals("No broken rails"))
					{
						selected = selected.replaceAll("Block ", "");
						int blockNum = Integer.parseInt(selected);
						txtBroken.setText(""+wc.brokenRails.get(blockNum));
					}
					else
					{
						txtBroken.setText("");
					}
				}
			}
		});
		
		//--------LOAD PLC--------------
		JButton btnLoadPlcProgram = new JButton("Load PLC Program");
		btnLoadPlcProgram.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLoadPlcProgram.setBounds(227, 322, 231, 36);
		contentPane.add(btnLoadPlcProgram);
		btnLoadPlcProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadPLCUI dialog = new LoadPLCUI();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		JLabel lblController = new JLabel("Controller:");
		lblController.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblController.setBounds(6, 13, 103, 17);
		contentPane.add(lblController);
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPositionChoice.removeAllItems();
				if(wc.switches.size() == 0)
				{
					switchPositionChoice.addItem("No Switches to Display");
					switchPositionChoice.setEnabled(false);
				}
				else
				{
					for(Integer i : wc.switches.keySet())
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
					trainIDChoice.setEnabled(true);
				}
				
				brokenRailChoice.removeAllItems();
				if(wc.brokenRails.size() == 0)
				{
					brokenRailChoice.addItem("No broken rails");
					brokenRailChoice.setEnabled(false);
					txtBroken.setText("");
				}
				else
				{
					for(Integer i: wc.brokenRails.keySet())
					{
						brokenRailChoice.addItem("Block "+i);
					}
					brokenRailChoice.setEnabled(true);
				}
				
				railwayCrossingChoice.removeAllItems();
				if(wc.crossing.size() == 0)
				{
					railwayCrossingChoice.addItem("No crossings to display");
					railwayCrossingChoice.setEnabled(false);
					txtLightStatus.setText("");
					txtCrossbarPos.setText("");
				}
				else
				{
					for(Integer i: wc.crossing.keySet())
					{
						railwayCrossingChoice.addItem("Block "+i);
					}
					railwayCrossingChoice.setEnabled(true);
				}
			}
		});
		
		//So the wayside starts with Red 1 selected and data populated
		waysideIDChoice.setSelectedItem("Red 1");
		btnUpdate.doClick();
	}
}
