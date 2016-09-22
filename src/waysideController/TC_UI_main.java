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

public class TC_UI_main extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TC_UI_main frame = new TC_UI_main();
					frame.setVisible(true);
					TC_UI_startup popup = new TC_UI_startup();
					popup.setTitle("PLC Startup");
					popup.setVisible(true);
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
		setBounds(100, 100, 581, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Track Occupancy", null, panel, null);
		
		JLabel lblGreenLine_2 = new JLabel("Green Line");
		
		table_2 = new JTable();
		table_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"Trains:", "Block Position:"},
				{"Train 1", "5"},
				{"Train 2", "10"},
				{"Train 3", "15"},
				{"Train 4", "20"},
			},
			new String[] {
				"New column", "New column"
			}
		));
		
		JLabel label_12 = new JLabel("Red Line");
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{"Trains:", "Block Position:"},
				{"Train 8", "3"},
				{"Train 10", "9"},
				{"Train 25", "18"},
				{"Train 30", "27"},
				{"Train 45", "33"},
				{"Train 22", "50"},
			},
			new String[] {
				"New column", "New column"
			}
		));
		table_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGreenLine_2)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(table_2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(table_3, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
					.addGap(52))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblGreenLine_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(table_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_12)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(table_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLACK);
		tabbedPane.addTab("Switch Position", null, panel_1, null);
		
		JLabel lblGreenLine = new JLabel("Green Line:");
		
		JLabel lblRedLine = new JLabel("Red Line:");
		
		JLabel lblSwitch = new JLabel("Switch 0:");
		
		JLabel label = new JLabel("Switch 1:");
		
		JLabel label_1 = new JLabel("Switch 2:");
		
		JLabel label_2 = new JLabel("Switch 3:");
		
		JLabel label_3 = new JLabel("Switch 4:");
		
		JLabel label_4 = new JLabel("Switch 5:");
		
		JLabel label_5 = new JLabel("Switch 6:");
		
		JLabel label_6 = new JLabel("Switch 7:");
		
		JLabel label_7 = new JLabel("Switch 8:");
		
		JLabel label_8 = new JLabel("Switch 9:");
		
		JLabel label_9 = new JLabel("Switch 10:");
		
		JLabel label_10 = new JLabel("Switch 11:");
		
		JLabel label_11 = new JLabel("Switch 12:");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		JLabel lblAToD = new JLabel("A to D");
		
		JLabel lblDToC = new JLabel("D to C");
		lblDToC.setForeground(Color.BLUE);
		
		JLabel lblNewLabel = new JLabel("F to G");
		lblNewLabel.setForeground(Color.BLUE);
		
		JLabel lblIToZz = new JLabel("Z to F");
		
		JLabel lblIToJ = new JLabel("I to J");
		lblIToJ.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_1 = new JLabel("I to ZZ");
		
		JLabel lblNewLabel_2 = new JLabel("M to N");
		
		JLabel lblRToN = new JLabel("N to R");
		lblRToN.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_3 = new JLabel("N to O");
		
		JLabel lblQToN = new JLabel("Q to N");
		lblQToN.setForeground(Color.BLUE);
		
		JLabel lblJToK = new JLabel("J to K");
		lblJToK.setForeground(Color.BLUE);
		
		JLabel lblYyToK = new JLabel("YY to K");
		
		JLabel lblAToK = new JLabel("A to F");
		
		JLabel lblEToF = new JLabel("E to F");
		lblEToF.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_4 = new JLabel("H to T");
		
		JLabel lblHToH = new JLabel("H to H");
		lblHToH.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_5 = new JLabel("H to R");
		
		JLabel lblHToH_1 = new JLabel("H to H");
		lblHToH_1.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_6 = new JLabel("Q to H");
		
		JLabel lblHToH_2 = new JLabel("H to H");
		lblHToH_2.setForeground(Color.BLUE);
		
		JLabel lblOToH = new JLabel("O to H");
		
		JLabel lblHToH_3 = new JLabel("H to H");
		lblHToH_3.setForeground(Color.BLUE);
		
		JLabel lblNToJ = new JLabel("N to J");
		
		JLabel lblJToJ = new JLabel("J to J");
		lblJToJ.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_7 = new JLabel("C to D");
		
		JLabel lblCToU = new JLabel("C to U");
		lblCToU.setForeground(Color.BLUE);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGreenLine)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblSwitch)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
								.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addGap(18)
							.addComponent(lblQToN))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel)
								.addComponent(lblIToJ))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblRToN)
								.addComponent(lblIToZz)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblAToD)
							.addGap(18)
							.addComponent(lblDToC))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblJToK)
							.addGap(18)
							.addComponent(lblYyToK)))
					.addGap(35)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label_9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_7)
									.addGap(18)
									.addComponent(lblCToU))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_6)
									.addGap(18)
									.addComponent(lblHToH_2))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addGap(18)
									.addComponent(lblHToH_1))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblAToK)
									.addGap(18)
									.addComponent(lblEToF))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addGap(18)
									.addComponent(lblHToH))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNToJ)
									.addGap(18)
									.addComponent(lblJToJ))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblOToH)
									.addGap(18)
									.addComponent(lblHToH_3))))
						.addComponent(lblRedLine))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblGreenLine)
								.addComponent(lblRedLine))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSwitch)
								.addComponent(label_5)
								.addComponent(lblJToK)
								.addComponent(lblYyToK)
								.addComponent(lblAToK)
								.addComponent(lblEToF))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(label_6)
								.addComponent(lblAToD)
								.addComponent(lblDToC)
								.addComponent(lblNewLabel_4)
								.addComponent(lblHToH))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(label_7)
								.addComponent(lblNewLabel)
								.addComponent(lblIToZz)
								.addComponent(lblNewLabel_5)
								.addComponent(lblHToH_1))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(label_8)
								.addComponent(lblIToJ)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_6)
								.addComponent(lblHToH_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3)
								.addComponent(label_9)
								.addComponent(lblNewLabel_2)
								.addComponent(lblRToN)
								.addComponent(lblOToH)
								.addComponent(lblHToH_3))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_4)
								.addComponent(label_10)
								.addComponent(lblNewLabel_3)
								.addComponent(lblQToN)
								.addComponent(lblNToJ)
								.addComponent(lblJToJ, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_11)
								.addComponent(lblNewLabel_7)
								.addComponent(lblCToU))))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Railway Status", null, panel_2, null);
		
		JLabel lblGreenLine_1 = new JLabel("Green Line");
		
		JLabel lblBlock = new JLabel("Block 19 Crossing:");
		
		table = new JTable();
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Lights", "Crossbar"},
				{"red", "up"},
			},
			new String[] {
				"Lights", "Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JLabel lblRedLine_1 = new JLabel("Red Line");
		
		JLabel lblBlockCrossing = new JLabel("Block 47 Crossing:");
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Lights", "Crossbar"},
				{"green", "down"},
			},
			new String[] {
				"New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel lblBrokenRailsNone = new JLabel("Broken Rails: None");
		
		JLabel lblBrokenRailsNone_1 = new JLabel("Broken Rails: None");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGreenLine_1)
						.addComponent(lblRedLine_1)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblBlockCrossing, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblBlock, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(10)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(table, 0, 0, Short.MAX_VALUE)
								.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBrokenRailsNone_1)
								.addComponent(lblBrokenRailsNone))))
					.addGap(168))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGreenLine_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBlock)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBrokenRailsNone))
					.addGap(18)
					.addComponent(lblRedLine_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblBrokenRailsNone_1))
						.addComponent(lblBlockCrossing))
					.addContainerGap(92, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
	}
}
