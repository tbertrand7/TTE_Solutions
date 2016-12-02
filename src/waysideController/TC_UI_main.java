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

public class TC_UI_main extends JFrame {

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
					TC_UI_main frame = new TC_UI_main();
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
	public TC_UI_main() {
		setTitle("Wayside Controller");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 507, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(121, 9, 350, 242);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Track Occupancy", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblTrain = new JLabel("Train:");
		lblTrain.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTrain.setBounds(20, 13, 46, 14);
		panel.add(lblTrain);
		
		JLabel lblBlockNumber = new JLabel("Block Number:");
		lblBlockNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBlockNumber.setBounds(20, 83, 122, 14);
		panel.add(lblBlockNumber);
		
		Choice choice = new Choice();
		choice.setBounds(20, 33, 214, 50);
		panel.add(choice);
		
		TextField textField = new TextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 26));
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(20, 108, 179, 40);
		panel.add(textField);

		choice.addItemListener(new ItemListener(){
	        public void itemStateChanged(ItemEvent ie)
	        {
	        		textField.setText(""+TTEHome.TTEHomeGUI.wc.trains.get(Integer.parseInt(choice.getSelectedItem())));
	        }
	    });
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Switch Positions", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblSwitchNumber = new JLabel("Switch Number:");
		lblSwitchNumber.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSwitchNumber.setBounds(12, 13, 181, 28);
		panel_1.add(lblSwitchNumber);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(12, 47, 181, 22);
		panel_1.add(choice_1);
		
		JLabel lblSwitchPosition = new JLabel("Switch Position:");
		lblSwitchPosition.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSwitchPosition.setBounds(12, 111, 181, 28);
		panel_1.add(lblSwitchPosition);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_1.setEditable(false);
		textField_1.setBounds(12, 144, 181, 35);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
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
		txtOn.setText("Red");
		txtOn.setBounds(111, 64, 40, 26);
		panel_2.add(txtOn);
		txtOn.setColumns(10);
		
		JLabel lblCrossbar = new JLabel("CrossBar:");
		lblCrossbar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCrossbar.setBounds(33, 107, 68, 14);
		panel_2.add(lblCrossbar);
		
		txtUp = new JTextField();
		txtUp.setBackground(Color.WHITE);
		txtUp.setEditable(false);
		txtUp.setText("Up");
		txtUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUp.setColumns(10);
		txtUp.setBounds(111, 101, 40, 26);
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
		lblCrossingStatus.setBounds(20, 38, 184, 26);
		panel_2.add(lblCrossingStatus);
		
		Choice choice_2 = new Choice();
		choice_2.setBounds(176, 42, 135, 22);
		panel_2.add(choice_2);
		
		JButton btnLoadPlcProgram = new JButton("Load PLC Program");
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
				choice.removeAll();
				TTEHome.TTEHomeGUI.wc.updateLocalTrackInfo();
				for(Integer i: TTEHome.TTEHomeGUI.wc.trains.keySet())
				{
					choice.add(""+i);
				}
			}
		});
		
		Choice choice_3 = new Choice();
		choice_3.setBounds(6, 43, 97, 34);
		contentPane.add(choice_3);
		choice_3.add("Green 1");
	}
}
