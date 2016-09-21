package ctcOffice;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JInternalFrame;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class OfficeUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfficeUI frame = new OfficeUI();
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
	public OfficeUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OfficeUI.class.getResource("/shared/TTE.png")));
		setBackground(new Color(240, 240, 240));
		setTitle("CTC Office Control Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 934, 835);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel trackDisplayPanel = new JPanel();
		trackDisplayPanel.setBackground(new Color(255, 255, 255));
		trackDisplayPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		trackDisplayPanel.setBounds(181, 64, 737, 733);
		contentPane.add(trackDisplayPanel);
		trackDisplayPanel.setLayout(null);
		
		ButtonGroup sectionBtns = new ButtonGroup();
		
		JToggleButton toggleButtonG1 = new JToggleButton("");
		toggleButtonG1.setSelected(true);
		toggleButtonG1.setBackground(Color.LIGHT_GRAY);
		toggleButtonG1.setBounds(354, 24, 15, 15);
		trackDisplayPanel.add(toggleButtonG1);
		
		JToggleButton toggleButtonG2 = new JToggleButton("");
		toggleButtonG2.setBackground(Color.LIGHT_GRAY);
		toggleButtonG2.setBounds(373, 44, 20, 20);
		trackDisplayPanel.add(toggleButtonG2);
		
		JToggleButton toggleButtonG3 = new JToggleButton("");
		toggleButtonG3.setBackground(Color.LIGHT_GRAY);
		toggleButtonG3.setBounds(387, 61, 15, 15);
		trackDisplayPanel.add(toggleButtonG3);
		
		JToggleButton toggleButtonG4 = new JToggleButton("");
		toggleButtonG4.setBackground(Color.LIGHT_GRAY);
		toggleButtonG4.setBounds(404, 76, 15, 15);
		trackDisplayPanel.add(toggleButtonG4);
		
		JToggleButton toggleButtonG5 = new JToggleButton("");
		toggleButtonG5.setBackground(Color.LIGHT_GRAY);
		toggleButtonG5.setBounds(427, 91, 15, 15);
		trackDisplayPanel.add(toggleButtonG5);
		
		JToggleButton toggleButtonG6 = new JToggleButton("");
		toggleButtonG6.setBackground(Color.LIGHT_GRAY);
		toggleButtonG6.setBounds(455, 97, 15, 15);
		trackDisplayPanel.add(toggleButtonG6);
		
		JToggleButton toggleButtonG7 = new JToggleButton("");
		toggleButtonG7.setBackground(Color.LIGHT_GRAY);
		toggleButtonG7.setBounds(502, 91, 15, 15);
		trackDisplayPanel.add(toggleButtonG7);
		
		JToggleButton toggleButtonG8 = new JToggleButton("");
		toggleButtonG8.setBackground(Color.LIGHT_GRAY);
		toggleButtonG8.setBounds(556, 63, 15, 15);
		trackDisplayPanel.add(toggleButtonG8);
		
		JToggleButton toggleButtonG9 = new JToggleButton("");
		toggleButtonG9.setBackground(Color.LIGHT_GRAY);
		toggleButtonG9.setBounds(491, 32, 20, 20);
		trackDisplayPanel.add(toggleButtonG9);
		
		JToggleButton toggleButtonG10 = new JToggleButton("");
		toggleButtonG10.setBackground(Color.LIGHT_GRAY);
		toggleButtonG10.setBounds(466, 25, 15, 15);
		trackDisplayPanel.add(toggleButtonG10);
		
		JToggleButton toggleButtonG11 = new JToggleButton("");
		toggleButtonG11.setBackground(Color.LIGHT_GRAY);
		toggleButtonG11.setBounds(427, 20, 15, 15);
		trackDisplayPanel.add(toggleButtonG11);
		
		JToggleButton toggleButtonG12 = new JToggleButton("");
		toggleButtonG12.setBackground(Color.LIGHT_GRAY);
		toggleButtonG12.setBounds(354, 11, 15, 15);
		trackDisplayPanel.add(toggleButtonG12);
		
		JToggleButton toggleButtonG13 = new JToggleButton("");
		toggleButtonG13.setBackground(Color.LIGHT_GRAY);
		toggleButtonG13.setBounds(302, 14, 15, 15);
		trackDisplayPanel.add(toggleButtonG13);
		
		JToggleButton toggleButtonG14 = new JToggleButton("");
		toggleButtonG14.setBackground(Color.LIGHT_GRAY);
		toggleButtonG14.setBounds(280, 14, 15, 15);
		trackDisplayPanel.add(toggleButtonG14);
		
		JToggleButton toggleButtonG15 = new JToggleButton("");
		toggleButtonG15.setBackground(Color.LIGHT_GRAY);
		toggleButtonG15.setBounds(255, 15, 15, 15);
		trackDisplayPanel.add(toggleButtonG15);
		
		JToggleButton toggleButtonG16 = new JToggleButton("");
		toggleButtonG16.setBackground(Color.LIGHT_GRAY);
		toggleButtonG16.setBounds(235, 14, 20, 20);
		trackDisplayPanel.add(toggleButtonG16);
		
		JToggleButton toggleButtonG17 = new JToggleButton("");
		toggleButtonG17.setBackground(Color.LIGHT_GRAY);
		toggleButtonG17.setBounds(188, 22, 15, 15);
		trackDisplayPanel.add(toggleButtonG17);
		
		JToggleButton toggleButtonG18 = new JToggleButton("");
		toggleButtonG18.setBackground(Color.LIGHT_GRAY);
		toggleButtonG18.setBounds(167, 28, 15, 15);
		trackDisplayPanel.add(toggleButtonG18);
		
		JToggleButton toggleButtonG19 = new JToggleButton("");
		toggleButtonG19.setBackground(Color.LIGHT_GRAY);
		toggleButtonG19.setBounds(149, 43, 15, 15);
		trackDisplayPanel.add(toggleButtonG19);
		
		JToggleButton toggleButtonG20 = new JToggleButton("");
		toggleButtonG20.setBackground(Color.LIGHT_GRAY);
		toggleButtonG20.setBounds(138, 61, 15, 15);
		trackDisplayPanel.add(toggleButtonG20);
		
		JToggleButton toggleButtonG21 = new JToggleButton("");
		toggleButtonG21.setBackground(Color.LIGHT_GRAY);
		toggleButtonG21.setBounds(134, 93, 15, 15);
		trackDisplayPanel.add(toggleButtonG21);
		
		JToggleButton toggleButtonG22 = new JToggleButton("");
		toggleButtonG22.setBackground(Color.LIGHT_GRAY);
		toggleButtonG22.setBounds(132, 105, 20, 20);
		trackDisplayPanel.add(toggleButtonG22);
		
		JToggleButton toggleButtonG23 = new JToggleButton("");
		toggleButtonG23.setBackground(Color.LIGHT_GRAY);
		toggleButtonG23.setBounds(135, 122, 15, 15);
		trackDisplayPanel.add(toggleButtonG23);
		
		JToggleButton toggleButtonG24 = new JToggleButton("");
		toggleButtonG24.setBackground(Color.LIGHT_GRAY);
		toggleButtonG24.setBounds(135, 134, 15, 15);
		trackDisplayPanel.add(toggleButtonG24);
		
		JToggleButton toggleButtonG25 = new JToggleButton("");
		toggleButtonG25.setBackground(Color.LIGHT_GRAY);
		toggleButtonG25.setBounds(135, 146, 15, 15);
		trackDisplayPanel.add(toggleButtonG25);
		
		JToggleButton toggleButtonG26 = new JToggleButton("");
		toggleButtonG26.setBackground(Color.YELLOW);
		toggleButtonG26.setBounds(135, 158, 15, 15);
		trackDisplayPanel.add(toggleButtonG26);
		
		JToggleButton toggleButtonG27 = new JToggleButton("");
		toggleButtonG27.setBackground(Color.LIGHT_GRAY);
		toggleButtonG27.setBounds(135, 170, 15, 15);
		trackDisplayPanel.add(toggleButtonG27);
		
		JToggleButton toggleButtonG28 = new JToggleButton("");
		toggleButtonG28.setBackground(Color.LIGHT_GRAY);
		toggleButtonG28.setBounds(135, 182, 15, 15);
		trackDisplayPanel.add(toggleButtonG28);
		
		JToggleButton toggleButtonG29 = new JToggleButton("");
		toggleButtonG29.setBackground(Color.LIGHT_GRAY);
		toggleButtonG29.setBounds(134, 199, 15, 15);
		trackDisplayPanel.add(toggleButtonG29);
		
		JToggleButton toggleButtonG30 = new JToggleButton("");
		toggleButtonG30.setBackground(Color.LIGHT_GRAY);
		toggleButtonG30.setBounds(134, 211, 15, 15);
		trackDisplayPanel.add(toggleButtonG30);
		
		JToggleButton toggleButtonG31 = new JToggleButton("");
		toggleButtonG31.setBackground(Color.BLUE);
		toggleButtonG31.setBounds(132, 223, 20, 20);
		trackDisplayPanel.add(toggleButtonG31);
		
		JToggleButton toggleButtonG32 = new JToggleButton("");
		toggleButtonG32.setBackground(Color.LIGHT_GRAY);
		toggleButtonG32.setBounds(135, 240, 15, 15);
		trackDisplayPanel.add(toggleButtonG32);
		
		JToggleButton toggleButtonG33 = new JToggleButton("");
		toggleButtonG33.setBackground(Color.LIGHT_GRAY);
		toggleButtonG33.setBounds(136, 262, 15, 15);
		trackDisplayPanel.add(toggleButtonG33);
		
		JToggleButton toggleButtonG34 = new JToggleButton("");
		toggleButtonG34.setBackground(Color.LIGHT_GRAY);
		toggleButtonG34.setBounds(147, 281, 15, 15);
		trackDisplayPanel.add(toggleButtonG34);
		
		JToggleButton toggleButtonG35 = new JToggleButton("");
		toggleButtonG35.setBackground(Color.LIGHT_GRAY);
		toggleButtonG35.setBounds(177, 299, 15, 15);
		trackDisplayPanel.add(toggleButtonG35);
		
		JToggleButton toggleButtonG36 = new JToggleButton("");
		toggleButtonG36.setBackground(Color.LIGHT_GRAY);
		toggleButtonG36.setBounds(205, 303, 15, 15);
		trackDisplayPanel.add(toggleButtonG36);
		
		JToggleButton toggleButtonG37 = new JToggleButton("");
		toggleButtonG37.setBackground(Color.LIGHT_GRAY);
		toggleButtonG37.setBounds(218, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG37);
		
		JToggleButton toggleButtonG38 = new JToggleButton("");
		toggleButtonG38.setBackground(Color.LIGHT_GRAY);
		toggleButtonG38.setBounds(230, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG38);
		
		JToggleButton toggleButtonG39 = new JToggleButton("");
		toggleButtonG39.setBackground(Color.LIGHT_GRAY);
		toggleButtonG39.setBounds(242, 302, 20, 20);
		trackDisplayPanel.add(toggleButtonG39);
		
		JToggleButton toggleButtonG40 = new JToggleButton("");
		toggleButtonG40.setBackground(Color.LIGHT_GRAY);
		toggleButtonG40.setBounds(260, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG40);
		
		JToggleButton toggleButtonG41 = new JToggleButton("");
		toggleButtonG41.setBackground(Color.LIGHT_GRAY);
		toggleButtonG41.setBounds(271, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG41);
		
		JToggleButton toggleButtonG42 = new JToggleButton("");
		toggleButtonG42.setBackground(Color.LIGHT_GRAY);
		toggleButtonG42.setBounds(295, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG42);
		
		JToggleButton toggleButtonG43 = new JToggleButton("");
		toggleButtonG43.setBackground(Color.LIGHT_GRAY);
		toggleButtonG43.setBounds(307, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG43);
		
		JToggleButton toggleButtonG44 = new JToggleButton("");
		toggleButtonG44.setBackground(Color.LIGHT_GRAY);
		toggleButtonG44.setBounds(319, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG44);
		
		JToggleButton toggleButtonG45 = new JToggleButton("");
		toggleButtonG45.setBackground(Color.LIGHT_GRAY);
		toggleButtonG45.setBounds(344, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG45);
		
		JToggleButton toggleButtonG46 = new JToggleButton("");
		toggleButtonG46.setBackground(Color.LIGHT_GRAY);
		toggleButtonG46.setBounds(357, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG46);
		
		JToggleButton toggleButtonG47 = new JToggleButton("");
		toggleButtonG47.setBackground(Color.LIGHT_GRAY);
		toggleButtonG47.setBounds(370, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG47);
		
		JToggleButton toggleButtonG48 = new JToggleButton("");
		toggleButtonG48.setBackground(Color.LIGHT_GRAY);
		toggleButtonG48.setBounds(383, 303, 20, 20);
		trackDisplayPanel.add(toggleButtonG48);
		
		JToggleButton toggleButtonG49 = new JToggleButton("");
		toggleButtonG49.setBackground(Color.LIGHT_GRAY);
		toggleButtonG49.setBounds(403, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG49);
		
		JToggleButton toggleButtonG50 = new JToggleButton("");
		toggleButtonG50.setBackground(Color.LIGHT_GRAY);
		toggleButtonG50.setBounds(416, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG50);
		
		JToggleButton toggleButtonG51 = new JToggleButton("");
		toggleButtonG51.setBackground(Color.LIGHT_GRAY);
		toggleButtonG51.setBounds(428, 305, 15, 15);
		trackDisplayPanel.add(toggleButtonG51);
		
		JToggleButton toggleButtonG52 = new JToggleButton("");
		toggleButtonG52.setBackground(Color.LIGHT_GRAY);
		toggleButtonG52.setBounds(441, 303, 15, 15);
		trackDisplayPanel.add(toggleButtonG52);
		
		JToggleButton toggleButtonG53 = new JToggleButton("");
		toggleButtonG53.setBackground(Color.LIGHT_GRAY);
		toggleButtonG53.setBounds(453, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG53);
		
		JToggleButton toggleButtonG54 = new JToggleButton("");
		toggleButtonG54.setBackground(Color.LIGHT_GRAY);
		toggleButtonG54.setBounds(467, 303, 15, 15);
		trackDisplayPanel.add(toggleButtonG54);
		
		JToggleButton toggleButtonG55 = new JToggleButton("");
		toggleButtonG55.setBackground(Color.LIGHT_GRAY);
		toggleButtonG55.setBounds(480, 303, 15, 15);
		trackDisplayPanel.add(toggleButtonG55);
		
		JToggleButton toggleButtonG56 = new JToggleButton("");
		toggleButtonG56.setBackground(Color.LIGHT_GRAY);
		toggleButtonG56.setBounds(494, 303, 15, 15);
		trackDisplayPanel.add(toggleButtonG56);
		
		JToggleButton toggleButtonG57 = new JToggleButton("");
		toggleButtonG57.setBackground(Color.LIGHT_GRAY);
		toggleButtonG57.setBounds(508, 301, 20, 20);
		trackDisplayPanel.add(toggleButtonG57);
		
		JToggleButton toggleButtonG58 = new JToggleButton("");
		toggleButtonG58.setBackground(Color.LIGHT_GRAY);
		toggleButtonG58.setBounds(546, 307, 15, 15);
		trackDisplayPanel.add(toggleButtonG58);
		
		JToggleButton toggleButtonG59 = new JToggleButton("");
		toggleButtonG59.setBackground(Color.LIGHT_GRAY);
		toggleButtonG59.setBounds(579, 310, 15, 15);
		trackDisplayPanel.add(toggleButtonG59);
		
		JToggleButton toggleButtonG60 = new JToggleButton("");
		toggleButtonG60.setBackground(Color.LIGHT_GRAY);
		toggleButtonG60.setBounds(602, 318, 15, 15);
		trackDisplayPanel.add(toggleButtonG60);
		
		JToggleButton toggleButtonG61 = new JToggleButton("");
		toggleButtonG61.setBackground(Color.LIGHT_GRAY);
		toggleButtonG61.setBounds(635, 335, 15, 15);
		trackDisplayPanel.add(toggleButtonG61);
		
		JToggleButton toggleButtonG62 = new JToggleButton("");
		toggleButtonG62.setBackground(Color.LIGHT_GRAY);
		toggleButtonG62.setBounds(661, 363, 15, 15);
		trackDisplayPanel.add(toggleButtonG62);
		
		JToggleButton toggleButtonG63 = new JToggleButton("");
		toggleButtonG63.setBackground(Color.LIGHT_GRAY);
		toggleButtonG63.setBounds(675, 413, 15, 15);
		trackDisplayPanel.add(toggleButtonG63);
		
		JToggleButton toggleButtonG64 = new JToggleButton("");
		toggleButtonG64.setBackground(Color.LIGHT_GRAY);
		toggleButtonG64.setBounds(676, 434, 15, 15);
		trackDisplayPanel.add(toggleButtonG64);
		
		JToggleButton toggleButtonG65 = new JToggleButton("");
		toggleButtonG65.setBackground(Color.LIGHT_GRAY);
		toggleButtonG65.setBounds(672, 454, 20, 20);
		trackDisplayPanel.add(toggleButtonG65);
		
		JToggleButton toggleButtonG66 = new JToggleButton("");
		toggleButtonG66.setBackground(Color.LIGHT_GRAY);
		toggleButtonG66.setBounds(676, 481, 15, 15);
		trackDisplayPanel.add(toggleButtonG66);
		
		JToggleButton toggleButtonG67 = new JToggleButton("");
		toggleButtonG67.setBackground(Color.LIGHT_GRAY);
		toggleButtonG67.setBounds(676, 510, 15, 15);
		trackDisplayPanel.add(toggleButtonG67);
		
		JToggleButton toggleButtonG68 = new JToggleButton("");
		toggleButtonG68.setBackground(Color.LIGHT_GRAY);
		toggleButtonG68.setBounds(675, 547, 15, 15);
		trackDisplayPanel.add(toggleButtonG68);
		
		JToggleButton toggleButtonG69 = new JToggleButton("");
		toggleButtonG69.setBackground(Color.LIGHT_GRAY);
		toggleButtonG69.setBounds(673, 618, 15, 15);
		trackDisplayPanel.add(toggleButtonG69);
		
		JToggleButton toggleButtonG70 = new JToggleButton("");
		toggleButtonG70.setBackground(Color.LIGHT_GRAY);
		toggleButtonG70.setBounds(665, 645, 15, 15);
		trackDisplayPanel.add(toggleButtonG70);
		
		JToggleButton toggleButtonG71 = new JToggleButton("");
		toggleButtonG71.setBackground(Color.LIGHT_GRAY);
		toggleButtonG71.setBounds(646, 673, 15, 15);
		trackDisplayPanel.add(toggleButtonG71);
		
		JToggleButton toggleButtonG72 = new JToggleButton("");
		toggleButtonG72.setBackground(Color.LIGHT_GRAY);
		toggleButtonG72.setBounds(621, 692, 15, 15);
		trackDisplayPanel.add(toggleButtonG72);
		
		JToggleButton toggleButtonG73 = new JToggleButton("");
		toggleButtonG73.setBackground(Color.LIGHT_GRAY);
		toggleButtonG73.setBounds(591, 701, 20, 20);
		trackDisplayPanel.add(toggleButtonG73);
		
		JToggleButton toggleButtonG74 = new JToggleButton("");
		toggleButtonG74.setBackground(Color.LIGHT_GRAY);
		toggleButtonG74.setBounds(538, 709, 15, 15);
		trackDisplayPanel.add(toggleButtonG74);
		
		JToggleButton toggleButtonG75 = new JToggleButton("");
		toggleButtonG75.setBackground(Color.LIGHT_GRAY);
		toggleButtonG75.setBounds(467, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG75);
		
		JToggleButton toggleButtonG76 = new JToggleButton("");
		toggleButtonG76.setBackground(Color.LIGHT_GRAY);
		toggleButtonG76.setBounds(401, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG76);
		
		JToggleButton toggleButtonG77 = new JToggleButton("");
		toggleButtonG77.setBackground(Color.LIGHT_GRAY);
		toggleButtonG77.setBounds(349, 705, 20, 20);
		trackDisplayPanel.add(toggleButtonG77);
		
		JToggleButton toggleButtonG78 = new JToggleButton("");
		toggleButtonG78.setBackground(Color.LIGHT_GRAY);
		toggleButtonG78.setBounds(334, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG78);
		
		JToggleButton toggleButtonG79 = new JToggleButton("");
		toggleButtonG79.setBackground(Color.LIGHT_GRAY);
		toggleButtonG79.setBounds(318, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG79);
		
		JToggleButton toggleButtonG80 = new JToggleButton("");
		toggleButtonG80.setBackground(Color.LIGHT_GRAY);
		toggleButtonG80.setBounds(303, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG80);
		
		JToggleButton toggleButtonG81 = new JToggleButton("");
		toggleButtonG81.setBackground(Color.LIGHT_GRAY);
		toggleButtonG81.setBounds(286, 706, 15, 15);
		trackDisplayPanel.add(toggleButtonG81);
		
		JToggleButton toggleButtonG82 = new JToggleButton("");
		toggleButtonG82.setBackground(Color.LIGHT_GRAY);
		toggleButtonG82.setBounds(271, 706, 15, 15);
		trackDisplayPanel.add(toggleButtonG82);
		
		JToggleButton toggleButtonG83 = new JToggleButton("");
		toggleButtonG83.setBackground(Color.LIGHT_GRAY);
		toggleButtonG83.setBounds(258, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG83);
		
		JToggleButton toggleButtonG84 = new JToggleButton("");
		toggleButtonG84.setBackground(Color.LIGHT_GRAY);
		toggleButtonG84.setBounds(244, 708, 15, 15);
		trackDisplayPanel.add(toggleButtonG84);
		
		JToggleButton toggleButtonG85 = new JToggleButton("");
		toggleButtonG85.setBackground(Color.LIGHT_GRAY);
		toggleButtonG85.setBounds(228, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG85);
		
		JToggleButton toggleButtonG86 = new JToggleButton("");
		toggleButtonG86.setBackground(Color.LIGHT_GRAY);
		toggleButtonG86.setBounds(178, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG86);
		
		JToggleButton toggleButtonG87 = new JToggleButton("");
		toggleButtonG87.setBackground(Color.LIGHT_GRAY);
		toggleButtonG87.setBounds(146, 706, 15, 15);
		trackDisplayPanel.add(toggleButtonG87);
		
		JToggleButton toggleButtonG88 = new JToggleButton("");
		toggleButtonG88.setBackground(Color.LIGHT_GRAY);
		toggleButtonG88.setBounds(121, 704, 20, 20);
		trackDisplayPanel.add(toggleButtonG88);
		
		JToggleButton toggleButtonG89 = new JToggleButton("");
		toggleButtonG89.setBackground(Color.LIGHT_GRAY);
		toggleButtonG89.setBounds(91, 705, 15, 15);
		trackDisplayPanel.add(toggleButtonG89);
		
		JToggleButton toggleButtonG90 = new JToggleButton("");
		toggleButtonG90.setBackground(Color.LIGHT_GRAY);
		toggleButtonG90.setBounds(73, 689, 15, 15);
		trackDisplayPanel.add(toggleButtonG90);
		
		JToggleButton toggleButtonG91 = new JToggleButton("");
		toggleButtonG91.setBackground(Color.LIGHT_GRAY);
		toggleButtonG91.setBounds(62, 674, 15, 15);
		trackDisplayPanel.add(toggleButtonG91);
		
		JToggleButton toggleButtonG92 = new JToggleButton("");
		toggleButtonG92.setBackground(Color.LIGHT_GRAY);
		toggleButtonG92.setBounds(58, 659, 15, 15);
		trackDisplayPanel.add(toggleButtonG92);
		
		JToggleButton toggleButtonG93 = new JToggleButton("");
		toggleButtonG93.setBackground(Color.LIGHT_GRAY);
		toggleButtonG93.setBounds(58, 639, 15, 15);
		trackDisplayPanel.add(toggleButtonG93);
		
		JToggleButton toggleButtonG94 = new JToggleButton("");
		toggleButtonG94.setBackground(Color.LIGHT_GRAY);
		toggleButtonG94.setBounds(64, 621, 15, 15);
		trackDisplayPanel.add(toggleButtonG94);
		
		JToggleButton toggleButtonG95 = new JToggleButton("");
		toggleButtonG95.setBackground(Color.LIGHT_GRAY);
		toggleButtonG95.setBounds(79, 599, 15, 15);
		trackDisplayPanel.add(toggleButtonG95);
		
		JToggleButton toggleButtonG96 = new JToggleButton("");
		toggleButtonG96.setBackground(Color.LIGHT_GRAY);
		toggleButtonG96.setBounds(111, 593, 20, 20);
		trackDisplayPanel.add(toggleButtonG96);
		
		JToggleButton toggleButtonG97 = new JToggleButton("");
		toggleButtonG97.setBackground(Color.LIGHT_GRAY);
		toggleButtonG97.setBounds(139, 621, 15, 15);
		trackDisplayPanel.add(toggleButtonG97);
		
		JToggleButton toggleButtonG98 = new JToggleButton("");
		toggleButtonG98.setBackground(Color.LIGHT_GRAY);
		toggleButtonG98.setBounds(146, 665, 15, 15);
		trackDisplayPanel.add(toggleButtonG98);
		
		JToggleButton toggleButtonG99 = new JToggleButton("");
		toggleButtonG99.setBackground(Color.LIGHT_GRAY);
		toggleButtonG99.setBounds(156, 683, 15, 15);
		trackDisplayPanel.add(toggleButtonG99);
		
		JToggleButton toggleButtonG100 = new JToggleButton("");
		toggleButtonG100.setBackground(Color.LIGHT_GRAY);
		toggleButtonG100.setBounds(178, 692, 15, 15);
		trackDisplayPanel.add(toggleButtonG100);
		
		JToggleButton toggleButtonG101 = new JToggleButton("");
		toggleButtonG101.setBackground(Color.LIGHT_GRAY);
		toggleButtonG101.setBounds(395, 682, 15, 15);
		trackDisplayPanel.add(toggleButtonG101);
		
		JToggleButton toggleButtonG102 = new JToggleButton("");
		toggleButtonG102.setBackground(Color.LIGHT_GRAY);
		toggleButtonG102.setBounds(432, 660, 15, 15);
		trackDisplayPanel.add(toggleButtonG102);
		
		JToggleButton toggleButtonG103 = new JToggleButton("");
		toggleButtonG103.setBackground(Color.LIGHT_GRAY);
		toggleButtonG103.setBounds(457, 660, 15, 15);
		trackDisplayPanel.add(toggleButtonG103);
		
		JToggleButton toggleButtonG104 = new JToggleButton("");
		toggleButtonG104.setBackground(Color.LIGHT_GRAY);
		toggleButtonG104.setBounds(481, 662, 15, 15);
		trackDisplayPanel.add(toggleButtonG104);
		
		JToggleButton toggleButtonG105 = new JToggleButton("");
		toggleButtonG105.setBackground(Color.LIGHT_GRAY);
		toggleButtonG105.setBounds(524, 660, 20, 20);
		trackDisplayPanel.add(toggleButtonG105);
		
		JToggleButton toggleButtonG106 = new JToggleButton("");
		toggleButtonG106.setBackground(Color.LIGHT_GRAY);
		toggleButtonG106.setBounds(554, 655, 15, 15);
		trackDisplayPanel.add(toggleButtonG106);
		
		JToggleButton toggleButtonG107 = new JToggleButton("");
		toggleButtonG107.setBackground(Color.LIGHT_GRAY);
		toggleButtonG107.setBounds(587, 635, 15, 15);
		trackDisplayPanel.add(toggleButtonG107);
		
		JToggleButton toggleButtonG108 = new JToggleButton("");
		toggleButtonG108.setBackground(Color.LIGHT_GRAY);
		toggleButtonG108.setBounds(608, 612, 15, 15);
		trackDisplayPanel.add(toggleButtonG108);
		
		JToggleButton toggleButtonG109 = new JToggleButton("");
		toggleButtonG109.setBackground(Color.LIGHT_GRAY);
		toggleButtonG109.setBounds(625, 577, 15, 15);
		trackDisplayPanel.add(toggleButtonG109);
		
		JToggleButton toggleButtonG110 = new JToggleButton("");
		toggleButtonG110.setBackground(Color.LIGHT_GRAY);
		toggleButtonG110.setBounds(627, 543, 15, 15);
		trackDisplayPanel.add(toggleButtonG110);
		
		JToggleButton toggleButtonG111 = new JToggleButton("");
		toggleButtonG111.setBackground(Color.LIGHT_GRAY);
		toggleButtonG111.setBounds(628, 529, 15, 15);
		trackDisplayPanel.add(toggleButtonG111);
		
		JToggleButton toggleButtonG112 = new JToggleButton("");
		toggleButtonG112.setBackground(Color.LIGHT_GRAY);
		toggleButtonG112.setBounds(628, 513, 15, 15);
		trackDisplayPanel.add(toggleButtonG112);
		
		JToggleButton toggleButtonG113 = new JToggleButton("");
		toggleButtonG113.setBackground(Color.LIGHT_GRAY);
		toggleButtonG113.setBounds(627, 499, 15, 15);
		trackDisplayPanel.add(toggleButtonG113);
		
		JToggleButton toggleButtonG114 = new JToggleButton("");
		toggleButtonG114.setBackground(Color.LIGHT_GRAY);
		toggleButtonG114.setBounds(627, 482, 20, 20);
		trackDisplayPanel.add(toggleButtonG114);
		
		JToggleButton toggleButtonG115 = new JToggleButton("");
		toggleButtonG115.setBackground(Color.LIGHT_GRAY);
		toggleButtonG115.setBounds(628, 468, 15, 15);
		trackDisplayPanel.add(toggleButtonG115);
		
		JToggleButton toggleButtonG116 = new JToggleButton("");
		toggleButtonG116.setBackground(Color.LIGHT_GRAY);
		toggleButtonG116.setBounds(628, 454, 15, 15);
		trackDisplayPanel.add(toggleButtonG116);
		
		JToggleButton toggleButtonG117 = new JToggleButton("");
		toggleButtonG117.setBackground(Color.LIGHT_GRAY);
		toggleButtonG117.setBounds(626, 421, 15, 15);
		trackDisplayPanel.add(toggleButtonG117);
		
		JToggleButton toggleButtonG118 = new JToggleButton("");
		toggleButtonG118.setBackground(Color.LIGHT_GRAY);
		toggleButtonG118.setBounds(615, 400, 15, 15);
		trackDisplayPanel.add(toggleButtonG118);
		
		JToggleButton toggleButtonG119 = new JToggleButton("");
		toggleButtonG119.setBackground(Color.LIGHT_GRAY);
		toggleButtonG119.setBounds(594, 377, 15, 15);
		trackDisplayPanel.add(toggleButtonG119);
		
		JToggleButton toggleButtonG120 = new JToggleButton("");
		toggleButtonG120.setBackground(Color.LIGHT_GRAY);
		toggleButtonG120.setBounds(564, 362, 15, 15);
		trackDisplayPanel.add(toggleButtonG120);
		
		JToggleButton toggleButtonG121 = new JToggleButton("");
		toggleButtonG121.setBackground(Color.LIGHT_GRAY);
		toggleButtonG121.setBounds(526, 349, 15, 15);
		trackDisplayPanel.add(toggleButtonG121);
		
		JToggleButton toggleButtonG122 = new JToggleButton("");
		toggleButtonG122.setBackground(Color.LIGHT_GRAY);
		toggleButtonG122.setBounds(484, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG122);
		
		JToggleButton toggleButtonG123 = new JToggleButton("");
		toggleButtonG123.setBackground(Color.LIGHT_GRAY);
		toggleButtonG123.setBounds(466, 342, 20, 20);
		trackDisplayPanel.add(toggleButtonG123);
		
		JToggleButton toggleButtonG124 = new JToggleButton("");
		toggleButtonG124.setBackground(Color.LIGHT_GRAY);
		toggleButtonG124.setBounds(454, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG124);
		
		JToggleButton toggleButtonG125 = new JToggleButton("");
		toggleButtonG125.setBackground(Color.LIGHT_GRAY);
		toggleButtonG125.setBounds(441, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG125);
		
		JToggleButton toggleButtonG126 = new JToggleButton("");
		toggleButtonG126.setBackground(Color.LIGHT_GRAY);
		toggleButtonG126.setBounds(428, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG126);
		
		JToggleButton toggleButtonG127 = new JToggleButton("");
		toggleButtonG127.setBackground(Color.LIGHT_GRAY);
		toggleButtonG127.setBounds(416, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG127);
		
		JToggleButton toggleButtonG128 = new JToggleButton("");
		toggleButtonG128.setBackground(Color.LIGHT_GRAY);
		toggleButtonG128.setBounds(406, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG128);
		
		JToggleButton toggleButtonG129 = new JToggleButton("");
		toggleButtonG129.setBackground(Color.LIGHT_GRAY);
		toggleButtonG129.setBounds(394, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG129);
		
		JToggleButton toggleButtonG130 = new JToggleButton("");
		toggleButtonG130.setBackground(Color.LIGHT_GRAY);
		toggleButtonG130.setBounds(383, 345, 15, 15);
		trackDisplayPanel.add(toggleButtonG130);
		
		JToggleButton toggleButtonG131 = new JToggleButton("");
		toggleButtonG131.setBackground(Color.LIGHT_GRAY);
		toggleButtonG131.setBounds(371, 345, 15, 15);
		trackDisplayPanel.add(toggleButtonG131);
		
		JToggleButton toggleButtonG132 = new JToggleButton("");
		toggleButtonG132.setBackground(Color.LIGHT_GRAY);
		toggleButtonG132.setBounds(355, 342, 20, 20);
		trackDisplayPanel.add(toggleButtonG132);
		
		JToggleButton toggleButtonG133 = new JToggleButton("");
		toggleButtonG133.setBackground(Color.LIGHT_GRAY);
		toggleButtonG133.setBounds(343, 345, 15, 15);
		trackDisplayPanel.add(toggleButtonG133);
		
		JToggleButton toggleButtonG134 = new JToggleButton("");
		toggleButtonG134.setBackground(Color.LIGHT_GRAY);
		toggleButtonG134.setBounds(317, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG134);
		
		JToggleButton toggleButtonG135 = new JToggleButton("");
		toggleButtonG135.setBackground(Color.LIGHT_GRAY);
		toggleButtonG135.setBounds(304, 343, 15, 15);
		trackDisplayPanel.add(toggleButtonG135);
		
		JToggleButton toggleButtonG136 = new JToggleButton("");
		toggleButtonG136.setBackground(Color.LIGHT_GRAY);
		toggleButtonG136.setBounds(290, 342, 15, 15);
		trackDisplayPanel.add(toggleButtonG136);
		
		JToggleButton toggleButtonG137 = new JToggleButton("");
		toggleButtonG137.setBackground(Color.LIGHT_GRAY);
		toggleButtonG137.setBounds(276, 343, 15, 15);
		trackDisplayPanel.add(toggleButtonG137);
		
		JToggleButton toggleButtonG138 = new JToggleButton("");
		toggleButtonG138.setBackground(Color.LIGHT_GRAY);
		toggleButtonG138.setBounds(262, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG138);
		
		JToggleButton toggleButtonG139 = new JToggleButton("");
		toggleButtonG139.setBackground(Color.LIGHT_GRAY);
		toggleButtonG139.setBounds(247, 343, 15, 15);
		trackDisplayPanel.add(toggleButtonG139);
		
		JToggleButton toggleButtonG140 = new JToggleButton("");
		toggleButtonG140.setBackground(Color.LIGHT_GRAY);
		toggleButtonG140.setBounds(232, 343, 15, 15);
		trackDisplayPanel.add(toggleButtonG140);
		
		JToggleButton toggleButtonG141 = new JToggleButton("");
		toggleButtonG141.setBackground(Color.LIGHT_GRAY);
		toggleButtonG141.setBounds(214, 340, 20, 20);
		trackDisplayPanel.add(toggleButtonG141);
		
		JToggleButton toggleButtonG142 = new JToggleButton("");
		toggleButtonG142.setBackground(Color.LIGHT_GRAY);
		toggleButtonG142.setBounds(199, 343, 15, 15);
		trackDisplayPanel.add(toggleButtonG142);
		
		JToggleButton toggleButtonG143 = new JToggleButton("");
		toggleButtonG143.setBackground(Color.LIGHT_GRAY);
		toggleButtonG143.setBounds(180, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG143);
		
		JToggleButton toggleButtonG144 = new JToggleButton("");
		toggleButtonG144.setBackground(Color.LIGHT_GRAY);
		toggleButtonG144.setBounds(140, 340, 15, 15);
		trackDisplayPanel.add(toggleButtonG144);
		
		JToggleButton toggleButtonG145 = new JToggleButton("");
		toggleButtonG145.setBackground(Color.LIGHT_GRAY);
		toggleButtonG145.setBounds(109, 327, 15, 15);
		trackDisplayPanel.add(toggleButtonG145);
		
		JToggleButton toggleButtonG146 = new JToggleButton("");
		toggleButtonG146.setBackground(Color.LIGHT_GRAY);
		toggleButtonG146.setBounds(92, 306, 15, 15);
		trackDisplayPanel.add(toggleButtonG146);
		
		JToggleButton toggleButtonG147 = new JToggleButton("");
		toggleButtonG147.setBackground(Color.LIGHT_GRAY);
		toggleButtonG147.setBounds(90, 280, 15, 15);
		trackDisplayPanel.add(toggleButtonG147);
		
		JToggleButton toggleButtonG148 = new JToggleButton("");
		toggleButtonG148.setBackground(Color.LIGHT_GRAY);
		toggleButtonG148.setBounds(90, 265, 15, 15);
		trackDisplayPanel.add(toggleButtonG148);
		
		JToggleButton toggleButtonG149 = new JToggleButton("");
		toggleButtonG149.setBackground(Color.LIGHT_GRAY);
		toggleButtonG149.setBounds(90, 249, 15, 15);
		trackDisplayPanel.add(toggleButtonG149);
		
		JToggleButton toggleButtonG150 = new JToggleButton("");
		toggleButtonG150.setBackground(Color.LIGHT_GRAY);
		toggleButtonG150.setBounds(108, 219, 15, 15);
		trackDisplayPanel.add(toggleButtonG150);
		
		JToggleButton toggleButtonG151 = new JToggleButton("");
		toggleButtonG151.setBackground(Color.LIGHT_GRAY);
		toggleButtonG151.setBounds(606, 291, 15, 15);
		trackDisplayPanel.add(toggleButtonG151);
		
		JToggleButton toggleButtonG152 = new JToggleButton("");
		toggleButtonG152.setBackground(Color.LIGHT_GRAY);
		toggleButtonG152.setBounds(673, 302, 15, 15);
		trackDisplayPanel.add(toggleButtonG152);
		
		JToggleButton toggleButton_1 = new JToggleButton("");
		toggleButton_1.setBackground(Color.LIGHT_GRAY);
		toggleButton_1.setBounds(500, 179, 15, 15);
		trackDisplayPanel.add(toggleButton_1);
		
		JToggleButton toggleButton_2 = new JToggleButton("");
		toggleButton_2.setBackground(Color.LIGHT_GRAY);
		toggleButton_2.setBounds(522, 175, 15, 15);
		trackDisplayPanel.add(toggleButton_2);
		
		JToggleButton toggleButton_3 = new JToggleButton("");
		toggleButton_3.setBackground(Color.LIGHT_GRAY);
		toggleButton_3.setBounds(537, 168, 15, 15);
		trackDisplayPanel.add(toggleButton_3);
		
		JToggleButton toggleButton_4 = new JToggleButton("");
		toggleButton_4.setBackground(Color.LIGHT_GRAY);
		toggleButton_4.setBounds(549, 137, 15, 15);
		trackDisplayPanel.add(toggleButton_4);
		
		JToggleButton toggleButton_5 = new JToggleButton("");
		toggleButton_5.setBackground(Color.LIGHT_GRAY);
		toggleButton_5.setBounds(569, 124, 15, 15);
		trackDisplayPanel.add(toggleButton_5);
		
		JToggleButton toggleButton_6 = new JToggleButton("");
		toggleButton_6.setBackground(Color.LIGHT_GRAY);
		toggleButton_6.setBounds(591, 115, 15, 15);
		trackDisplayPanel.add(toggleButton_6);
		
		JToggleButton toggleButton_7 = new JToggleButton("");
		toggleButton_7.setBackground(Color.LIGHT_GRAY);
		toggleButton_7.setBounds(637, 108, 20, 20);
		trackDisplayPanel.add(toggleButton_7);
		
		JToggleButton toggleButton_8 = new JToggleButton("");
		toggleButton_8.setBackground(Color.LIGHT_GRAY);
		toggleButton_8.setBounds(665, 121, 15, 15);
		trackDisplayPanel.add(toggleButton_8);
		
		JToggleButton toggleButton_9 = new JToggleButton("");
		toggleButton_9.setBackground(Color.LIGHT_GRAY);
		toggleButton_9.setBounds(691, 133, 15, 15);
		trackDisplayPanel.add(toggleButton_9);
		
		JToggleButton toggleButton_10 = new JToggleButton("");
		toggleButton_10.setBackground(Color.LIGHT_GRAY);
		toggleButton_10.setBounds(684, 176, 15, 15);
		trackDisplayPanel.add(toggleButton_10);
		
		JToggleButton toggleButton_11 = new JToggleButton("");
		toggleButton_11.setBackground(Color.LIGHT_GRAY);
		toggleButton_11.setBounds(656, 186, 15, 15);
		trackDisplayPanel.add(toggleButton_11);
		
		JToggleButton toggleButton_12 = new JToggleButton("");
		toggleButton_12.setBackground(Color.LIGHT_GRAY);
		toggleButton_12.setBounds(618, 190, 15, 15);
		trackDisplayPanel.add(toggleButton_12);
		
		JToggleButton toggleButton_13 = new JToggleButton("");
		toggleButton_13.setBackground(Color.LIGHT_GRAY);
		toggleButton_13.setBounds(549, 193, 15, 15);
		trackDisplayPanel.add(toggleButton_13);
		
		JToggleButton toggleButton_14 = new JToggleButton("");
		toggleButton_14.setBackground(Color.LIGHT_GRAY);
		toggleButton_14.setBounds(524, 192, 15, 15);
		trackDisplayPanel.add(toggleButton_14);
		
		JToggleButton toggleButton_15 = new JToggleButton("");
		toggleButton_15.setBackground(Color.LIGHT_GRAY);
		toggleButton_15.setBounds(498, 193, 15, 15);
		trackDisplayPanel.add(toggleButton_15);
		
		JToggleButton toggleButton_16 = new JToggleButton("");
		toggleButton_16.setBackground(Color.LIGHT_GRAY);
		toggleButton_16.setBounds(456, 191, 20, 20);
		trackDisplayPanel.add(toggleButton_16);
		
		JToggleButton toggleButton_17 = new JToggleButton("");
		toggleButton_17.setBackground(Color.LIGHT_GRAY);
		toggleButton_17.setBounds(444, 191, 15, 15);
		trackDisplayPanel.add(toggleButton_17);
		
		JToggleButton toggleButton_18 = new JToggleButton("");
		toggleButton_18.setBackground(Color.LIGHT_GRAY);
		toggleButton_18.setBounds(432, 191, 15, 15);
		trackDisplayPanel.add(toggleButton_18);
		
		JToggleButton toggleButton_19 = new JToggleButton("");
		toggleButton_19.setBackground(Color.LIGHT_GRAY);
		toggleButton_19.setBounds(420, 191, 15, 15);
		trackDisplayPanel.add(toggleButton_19);
		
		JToggleButton toggleButton_20 = new JToggleButton("");
		toggleButton_20.setBackground(Color.LIGHT_GRAY);
		toggleButton_20.setBounds(408, 191, 15, 15);
		trackDisplayPanel.add(toggleButton_20);
		
		JToggleButton toggleButton_21 = new JToggleButton("");
		toggleButton_21.setBackground(Color.LIGHT_GRAY);
		toggleButton_21.setBounds(373, 194, 20, 20);
		trackDisplayPanel.add(toggleButton_21);
		
		JToggleButton toggleButton_22 = new JToggleButton("");
		toggleButton_22.setBackground(Color.LIGHT_GRAY);
		toggleButton_22.setBounds(359, 201, 15, 15);
		trackDisplayPanel.add(toggleButton_22);
		
		JToggleButton toggleButton_23 = new JToggleButton("");
		toggleButton_23.setBackground(Color.LIGHT_GRAY);
		toggleButton_23.setBounds(346, 207, 15, 15);
		trackDisplayPanel.add(toggleButton_23);
		
		JToggleButton toggleButton_24 = new JToggleButton("");
		toggleButton_24.setBackground(Color.LIGHT_GRAY);
		toggleButton_24.setBounds(330, 222, 15, 15);
		trackDisplayPanel.add(toggleButton_24);
		
		JToggleButton toggleButton_25 = new JToggleButton("");
		toggleButton_25.setBackground(Color.LIGHT_GRAY);
		toggleButton_25.setBounds(329, 233, 20, 20);
		trackDisplayPanel.add(toggleButton_25);
		
		JToggleButton toggleButton_26 = new JToggleButton("");
		toggleButton_26.setBackground(Color.LIGHT_GRAY);
		toggleButton_26.setBounds(330, 250, 15, 15);
		trackDisplayPanel.add(toggleButton_26);
		
		JToggleButton toggleButton_27 = new JToggleButton("");
		toggleButton_27.setBackground(Color.LIGHT_GRAY);
		toggleButton_27.setBounds(330, 262, 15, 15);
		trackDisplayPanel.add(toggleButton_27);
		
		JToggleButton toggleButton_28 = new JToggleButton("");
		toggleButton_28.setBackground(Color.LIGHT_GRAY);
		toggleButton_28.setBounds(330, 274, 15, 15);
		trackDisplayPanel.add(toggleButton_28);
		
		JToggleButton toggleButton_29 = new JToggleButton("");
		toggleButton_29.setBackground(Color.LIGHT_GRAY);
		toggleButton_29.setBounds(330, 286, 15, 15);
		trackDisplayPanel.add(toggleButton_29);
		
		JToggleButton toggleButton_30 = new JToggleButton("");
		toggleButton_30.setBackground(Color.LIGHT_GRAY);
		toggleButton_30.setBounds(330, 298, 15, 15);
		trackDisplayPanel.add(toggleButton_30);
		
		JToggleButton toggleButton_31 = new JToggleButton("");
		toggleButton_31.setBackground(Color.LIGHT_GRAY);
		toggleButton_31.setBounds(331, 314, 15, 15);
		trackDisplayPanel.add(toggleButton_31);
		
		JToggleButton toggleButton_32 = new JToggleButton("");
		toggleButton_32.setBackground(Color.LIGHT_GRAY);
		toggleButton_32.setBounds(331, 326, 15, 15);
		trackDisplayPanel.add(toggleButton_32);
		
		JToggleButton toggleButton_33 = new JToggleButton("");
		toggleButton_33.setBackground(Color.LIGHT_GRAY);
		toggleButton_33.setBounds(330, 338, 15, 15);
		trackDisplayPanel.add(toggleButton_33);
		
		JToggleButton toggleButton_34 = new JToggleButton("");
		toggleButton_34.setBackground(Color.LIGHT_GRAY);
		toggleButton_34.setBounds(330, 351, 15, 15);
		trackDisplayPanel.add(toggleButton_34);
		
		JToggleButton toggleButton_35 = new JToggleButton("");
		toggleButton_35.setBackground(Color.LIGHT_GRAY);
		toggleButton_35.setBounds(329, 363, 20, 20);
		trackDisplayPanel.add(toggleButton_35);
		
		JToggleButton toggleButton_36 = new JToggleButton("");
		toggleButton_36.setBackground(Color.LIGHT_GRAY);
		toggleButton_36.setBounds(330, 380, 15, 15);
		trackDisplayPanel.add(toggleButton_36);
		
		JToggleButton toggleButton_37 = new JToggleButton("");
		toggleButton_37.setBackground(Color.LIGHT_GRAY);
		toggleButton_37.setBounds(330, 391, 15, 15);
		trackDisplayPanel.add(toggleButton_37);
		
		JToggleButton toggleButton_38 = new JToggleButton("");
		toggleButton_38.setBackground(Color.LIGHT_GRAY);
		toggleButton_38.setBounds(330, 402, 15, 15);
		trackDisplayPanel.add(toggleButton_38);
		
		JToggleButton toggleButton_39 = new JToggleButton("");
		toggleButton_39.setBackground(Color.LIGHT_GRAY);
		toggleButton_39.setBounds(330, 413, 15, 15);
		trackDisplayPanel.add(toggleButton_39);
		
		JToggleButton toggleButton_40 = new JToggleButton("");
		toggleButton_40.setBackground(Color.LIGHT_GRAY);
		toggleButton_40.setBounds(331, 425, 15, 15);
		trackDisplayPanel.add(toggleButton_40);
		
		JToggleButton toggleButton_41 = new JToggleButton("");
		toggleButton_41.setBackground(Color.LIGHT_GRAY);
		toggleButton_41.setBounds(331, 436, 15, 15);
		trackDisplayPanel.add(toggleButton_41);
		
		JToggleButton toggleButton_42 = new JToggleButton("");
		toggleButton_42.setBackground(Color.LIGHT_GRAY);
		toggleButton_42.setBounds(331, 447, 15, 15);
		trackDisplayPanel.add(toggleButton_42);
		
		JToggleButton toggleButton_43 = new JToggleButton("");
		toggleButton_43.setBackground(Color.LIGHT_GRAY);
		toggleButton_43.setBounds(331, 459, 15, 15);
		trackDisplayPanel.add(toggleButton_43);
		
		JToggleButton toggleButton_44 = new JToggleButton("");
		toggleButton_44.setBackground(Color.LIGHT_GRAY);
		toggleButton_44.setBounds(331, 470, 15, 15);
		trackDisplayPanel.add(toggleButton_44);
		
		JToggleButton toggleButton_45 = new JToggleButton("");
		toggleButton_45.setBackground(Color.LIGHT_GRAY);
		toggleButton_45.setBounds(329, 482, 20, 20);
		trackDisplayPanel.add(toggleButton_45);
		
		JToggleButton toggleButton_46 = new JToggleButton("");
		toggleButton_46.setBackground(Color.LIGHT_GRAY);
		toggleButton_46.setBounds(318, 527, 15, 15);
		trackDisplayPanel.add(toggleButton_46);
		
		JToggleButton toggleButton_47 = new JToggleButton("");
		toggleButton_47.setBackground(Color.LIGHT_GRAY);
		toggleButton_47.setBounds(293, 547, 15, 15);
		trackDisplayPanel.add(toggleButton_47);
		
		JToggleButton toggleButton_48 = new JToggleButton("");
		toggleButton_48.setBackground(Color.LIGHT_GRAY);
		toggleButton_48.setBounds(263, 557, 20, 20);
		trackDisplayPanel.add(toggleButton_48);
		
		JToggleButton toggleButton_49 = new JToggleButton("");
		toggleButton_49.setBackground(Color.LIGHT_GRAY);
		toggleButton_49.setBounds(223, 561, 15, 15);
		trackDisplayPanel.add(toggleButton_49);
		
		JToggleButton toggleButton_50 = new JToggleButton("");
		toggleButton_50.setBackground(Color.LIGHT_GRAY);
		toggleButton_50.setBounds(208, 562, 15, 15);
		trackDisplayPanel.add(toggleButton_50);
		
		JToggleButton toggleButton_51 = new JToggleButton("");
		toggleButton_51.setBackground(Color.LIGHT_GRAY);
		toggleButton_51.setBounds(192, 561, 15, 15);
		trackDisplayPanel.add(toggleButton_51);
		
		JToggleButton toggleButton_52 = new JToggleButton("");
		toggleButton_52.setBackground(Color.LIGHT_GRAY);
		toggleButton_52.setBounds(152, 562, 15, 15);
		trackDisplayPanel.add(toggleButton_52);
		
		JToggleButton toggleButton_53 = new JToggleButton("");
		toggleButton_53.setBackground(Color.LIGHT_GRAY);
		toggleButton_53.setBounds(132, 561, 15, 15);
		trackDisplayPanel.add(toggleButton_53);
		
		JToggleButton toggleButton_54 = new JToggleButton("");
		toggleButton_54.setBackground(Color.LIGHT_GRAY);
		toggleButton_54.setBounds(114, 559, 15, 15);
		trackDisplayPanel.add(toggleButton_54);
		
		JToggleButton toggleButton_55 = new JToggleButton("");
		toggleButton_55.setBackground(Color.LIGHT_GRAY);
		toggleButton_55.setBounds(73, 554, 15, 15);
		trackDisplayPanel.add(toggleButton_55);
		
		JToggleButton toggleButton_56 = new JToggleButton("");
		toggleButton_56.setBackground(Color.LIGHT_GRAY);
		toggleButton_56.setBounds(42, 536, 15, 15);
		trackDisplayPanel.add(toggleButton_56);
		
		JToggleButton toggleButton_57 = new JToggleButton("");
		toggleButton_57.setBackground(Color.LIGHT_GRAY);
		toggleButton_57.setBounds(22, 499, 15, 15);
		trackDisplayPanel.add(toggleButton_57);
		
		JToggleButton toggleButton_58 = new JToggleButton("");
		toggleButton_58.setBackground(Color.LIGHT_GRAY);
		toggleButton_58.setBounds(26, 449, 15, 15);
		trackDisplayPanel.add(toggleButton_58);
		
		JToggleButton toggleButton_59 = new JToggleButton("");
		toggleButton_59.setBackground(Color.LIGHT_GRAY);
		toggleButton_59.setBounds(40, 432, 15, 15);
		trackDisplayPanel.add(toggleButton_59);
		
		JToggleButton toggleButton_60 = new JToggleButton("");
		toggleButton_60.setBackground(Color.LIGHT_GRAY);
		toggleButton_60.setBounds(55, 417, 20, 20);
		trackDisplayPanel.add(toggleButton_60);
		
		JToggleButton toggleButton_61 = new JToggleButton("");
		toggleButton_61.setBackground(Color.LIGHT_GRAY);
		toggleButton_61.setBounds(92, 424, 15, 15);
		trackDisplayPanel.add(toggleButton_61);
		
		JToggleButton toggleButton_62 = new JToggleButton("");
		toggleButton_62.setBackground(Color.LIGHT_GRAY);
		toggleButton_62.setBounds(105, 447, 15, 15);
		trackDisplayPanel.add(toggleButton_62);
		
		JToggleButton toggleButton_63 = new JToggleButton("");
		toggleButton_63.setBackground(Color.LIGHT_GRAY);
		toggleButton_63.setBounds(116, 488, 15, 15);
		trackDisplayPanel.add(toggleButton_63);
		
		JToggleButton toggleButton_64 = new JToggleButton("");
		toggleButton_64.setBackground(Color.LIGHT_GRAY);
		toggleButton_64.setBounds(122, 532, 15, 15);
		trackDisplayPanel.add(toggleButton_64);
		
		JToggleButton toggleButton_65 = new JToggleButton("");
		toggleButton_65.setBackground(Color.LIGHT_GRAY);
		toggleButton_65.setBounds(134, 545, 15, 15);
		trackDisplayPanel.add(toggleButton_65);
		
		JToggleButton toggleButton_66 = new JToggleButton("");
		toggleButton_66.setBackground(Color.LIGHT_GRAY);
		toggleButton_66.setBounds(152, 550, 15, 15);
		trackDisplayPanel.add(toggleButton_66);
		
		JToggleButton toggleButton_67 = new JToggleButton("");
		toggleButton_67.setBackground(Color.LIGHT_GRAY);
		toggleButton_67.setBounds(308, 466, 15, 15);
		trackDisplayPanel.add(toggleButton_67);
		
		JToggleButton toggleButton_68 = new JToggleButton("");
		toggleButton_68.setBackground(Color.LIGHT_GRAY);
		toggleButton_68.setBounds(282, 446, 15, 15);
		trackDisplayPanel.add(toggleButton_68);
		
		JToggleButton toggleButton_69 = new JToggleButton("");
		toggleButton_69.setBackground(Color.LIGHT_GRAY);
		toggleButton_69.setBounds(282, 430, 15, 15);
		trackDisplayPanel.add(toggleButton_69);
		
		JToggleButton toggleButton_70 = new JToggleButton("");
		toggleButton_70.setBackground(Color.LIGHT_GRAY);
		toggleButton_70.setBounds(282, 415, 15, 15);
		trackDisplayPanel.add(toggleButton_70);
		
		JToggleButton toggleButton_71 = new JToggleButton("");
		toggleButton_71.setBackground(Color.LIGHT_GRAY);
		toggleButton_71.setBounds(308, 399, 15, 15);
		trackDisplayPanel.add(toggleButton_71);
		
		JToggleButton toggleButton_72 = new JToggleButton("");
		toggleButton_72.setBackground(Color.LIGHT_GRAY);
		toggleButton_72.setBounds(307, 329, 15, 15);
		trackDisplayPanel.add(toggleButton_72);
		
		JToggleButton toggleButton_73 = new JToggleButton("");
		toggleButton_73.setBackground(Color.LIGHT_GRAY);
		toggleButton_73.setBounds(283, 314, 15, 15);
		trackDisplayPanel.add(toggleButton_73);
		
		JToggleButton toggleButton_74 = new JToggleButton("");
		toggleButton_74.setBackground(Color.LIGHT_GRAY);
		toggleButton_74.setBounds(283, 297, 15, 15);
		trackDisplayPanel.add(toggleButton_74);
		
		JToggleButton toggleButton_75 = new JToggleButton("");
		toggleButton_75.setBackground(Color.LIGHT_GRAY);
		toggleButton_75.setBounds(283, 285, 15, 15);
		trackDisplayPanel.add(toggleButton_75);
		
		JToggleButton toggleButton_76 = new JToggleButton("");
		toggleButton_76.setBackground(Color.BLACK);
		toggleButton_76.setBounds(308, 270, 15, 15);
		trackDisplayPanel.add(toggleButton_76);
		
		JToggleButton toggleButton_77 = new JToggleButton("");
		toggleButton_77.setBackground(Color.LIGHT_GRAY);
		toggleButton_77.setBounds(691, 202, 15, 15);
		trackDisplayPanel.add(toggleButton_77);
		
		JLabel lblLegend = new JLabel("");
		lblLegend.setIcon(new ImageIcon(OfficeUI.class.getResource("/ctcOffice/legend.png")));
		lblLegend.setBounds(14, 46, 105, 110);
		trackDisplayPanel.add(lblLegend);
		
		JLabel lblTrackPicture = new JLabel("");
		lblTrackPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrackPicture.setIcon(new ImageIcon(OfficeUI.class.getResource("/ctcOffice/track.png")));
		lblTrackPicture.setBounds(2, 2, 730, 729);
		trackDisplayPanel.add(lblTrackPicture);
		
		ButtonGroup execMode = new ButtonGroup();
		
		JPanel topButtonPanel = new JPanel();
		topButtonPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		topButtonPanel.setBounds(0, 0, 918, 66);
		contentPane.add(topButtonPanel);
		topButtonPanel.setLayout(null);
		
		JSlider slider = new JSlider();
		slider.setToolTipText("Set speed of auto simulation");
		slider.setMajorTickSpacing(3);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinimum(1);
		slider.setMinorTickSpacing(1);
		slider.setMaximum(10);
		slider.setBounds(710, 20, 175, 42);
		topButtonPanel.add(slider);
		
		JRadioButton rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setToolTipText("Enable manual mode");
		rdbtnManual.setSelected(true);
		rdbtnManual.setBounds(635, 12, 69, 23);
		topButtonPanel.add(rdbtnManual);
		
		JRadioButton rdbtnAuto = new JRadioButton("Auto");
		rdbtnAuto.setToolTipText("Enable automatic simulation");
		rdbtnAuto.setBounds(635, 32, 69, 23);
		topButtonPanel.add(rdbtnAuto);
		execMode.add(rdbtnAuto);
		execMode.add(rdbtnManual);
		
		JLabel lblSimulationSpeed = new JLabel("Simulation Speed");
		lblSimulationSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSimulationSpeed.setBounds(752, 5, 103, 18);
		topButtonPanel.add(lblSimulationSpeed);
		
		JLabel lblCtcOfficeControl = new JLabel("CTC Office Control Panel");
		lblCtcOfficeControl.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtcOfficeControl.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblCtcOfficeControl.setBounds(182, 0, 432, 64);
		topButtonPanel.add(lblCtcOfficeControl);
		
		JLabel lblLogo = new JLabel("Logo");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(6, 5, 175, 55);
		topButtonPanel.add(lblLogo);
		
		JTabbedPane mainMenuTabPane = new JTabbedPane(JTabbedPane.TOP);
		mainMenuTabPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainMenuTabPane.setBounds(0, 64, 184, 733);
		contentPane.add(mainMenuTabPane);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainMenuTabPane.addTab("Details", null, statusPanel, null);
		statusPanel.setLayout(null);
		
		JLabel lblTrackInfo = new JLabel("Track Info");
		lblTrackInfo.setBounds(6, 6, 165, 14);
		statusPanel.add(lblTrackInfo);
		lblTrackInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrackInfo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblLine = new JLabel("Line:");
		lblLine.setBounds(6, 31, 75, 15);
		statusPanel.add(lblLine);
		
		JLabel lblLineInfo = new JLabel("Green");
		lblLineInfo.setBounds(81, 31, 90, 15);
		statusPanel.add(lblLineInfo);
		lblLineInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(6, 56, 75, 15);
		statusPanel.add(lblSection);
		
		JLabel lblBlock = new JLabel("Block:");
		lblBlock.setBounds(6, 81, 58, 15);
		statusPanel.add(lblBlock);
		
		JLabel lblLength = new JLabel("Length:");
		lblLength.setBounds(6, 106, 75, 15);
		statusPanel.add(lblLength);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(6, 131, 75, 15);
		statusPanel.add(lblGrade);
		
		JLabel lblSpeedLimit = new JLabel("Speed Limit:");
		lblSpeedLimit.setBounds(6, 156, 75, 15);
		statusPanel.add(lblSpeedLimit);
		
		JLabel lblElevation = new JLabel("Elevation:");
		lblElevation.setBounds(6, 181, 75, 15);
		statusPanel.add(lblElevation);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(6, 206, 75, 15);
		statusPanel.add(lblStatus);
		
		JButton btnCloseTrack = new JButton("Close Track");
		btnCloseTrack.setBounds(44, 231, 94, 23);
		statusPanel.add(btnCloseTrack);
		
		JLabel lblStatusInfo = new JLabel("Open");
		lblStatusInfo.setBounds(81, 206, 90, 15);
		statusPanel.add(lblStatusInfo);
		lblStatusInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblElevationInfo = new JLabel("0.5 m");
		lblElevationInfo.setBounds(81, 181, 90, 15);
		statusPanel.add(lblElevationInfo);
		lblElevationInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSpeedLimitInfo = new JLabel("40 km/h");
		lblSpeedLimitInfo.setBounds(81, 156, 90, 15);
		statusPanel.add(lblSpeedLimitInfo);
		lblSpeedLimitInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblGradeInfo = new JLabel("0.5%");
		lblGradeInfo.setBounds(81, 131, 90, 15);
		statusPanel.add(lblGradeInfo);
		lblGradeInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblLengthInfo = new JLabel("50 m");
		lblLengthInfo.setBounds(81, 106, 90, 15);
		statusPanel.add(lblLengthInfo);
		lblLengthInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblBlockInfo = new JLabel("1");
		lblBlockInfo.setBounds(81, 80, 90, 15);
		statusPanel.add(lblBlockInfo);
		lblBlockInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSectionInfo = new JLabel("A");
		lblSectionInfo.setBounds(81, 56, 90, 15);
		statusPanel.add(lblSectionInfo);
		lblSectionInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSwitch = new JLabel("Switch:");
		lblSwitch.setBounds(6, 321, 75, 15);
		statusPanel.add(lblSwitch);
		
		JLabel lblStation = new JLabel("Station:");
		lblStation.setBounds(6, 296, 75, 15);
		statusPanel.add(lblStation);
		
		JLabel lblInfrastracture = new JLabel("Infrastructure");
		lblInfrastracture.setBounds(6, 271, 165, 14);
		statusPanel.add(lblInfrastracture);
		lblInfrastracture.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfrastracture.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblAuthority = new JLabel("Authority:");
		lblAuthority.setBounds(6, 523, 58, 14);
		statusPanel.add(lblAuthority);
		
		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setBounds(6, 498, 75, 14);
		statusPanel.add(lblDestination);
		
		JLabel lblSpeed = new JLabel("Speed:");
		lblSpeed.setBounds(6, 473, 46, 14);
		statusPanel.add(lblSpeed);
		
		JLabel lblTrainInfo = new JLabel("Train Info");
		lblTrainInfo.setBounds(44, 448, 81, 14);
		statusPanel.add(lblTrainInfo);
		lblTrainInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrainInfo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(3, 552, 176, 145);
		statusPanel.add(tabbedPane);
		tabbedPane.setToolTipText("");
		
		JPanel speedPanel = new JPanel();
		tabbedPane.addTab("Speed", null, speedPanel, null);
		
		JLabel lblSetNewSpeed = new JLabel("Enter new speed:");
		speedPanel.add(lblSetNewSpeed);
		
		textField = new JTextField();
		speedPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblKmh = new JLabel("km/h");
		speedPanel.add(lblKmh);
		
		JButton btnSetSpeed = new JButton("Set Speed");
		speedPanel.add(btnSetSpeed);
		
		JPanel authorityPanel = new JPanel();
		tabbedPane.addTab("Authority", null, authorityPanel, null);
		
		JLabel lblEnterNewAuthority = new JLabel("Enter new authority:");
		authorityPanel.add(lblEnterNewAuthority);
		
		textField_1 = new JTextField();
		authorityPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSetAuthority = new JButton("Set Authority");
		authorityPanel.add(btnSetAuthority);
		
		JPanel destPanel = new JPanel();
		tabbedPane.addTab("Destination", null, destPanel, null);
		
		JLabel lblSelectNewDestination = new JLabel("Select new destination:");
		destPanel.add(lblSelectNewDestination);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Station Name"}));
		destPanel.add(comboBox);
		
		JButton btnSetDestination = new JButton("Set Destination");
		destPanel.add(btnSetDestination);
		
		JLabel lblUnder = new JLabel("Underground:");
		lblUnder.setBounds(6, 346, 80, 15);
		statusPanel.add(lblUnder);
		
		JLabel lblStationInfo = new JLabel("N/A");
		lblStationInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStationInfo.setBounds(81, 296, 90, 15);
		statusPanel.add(lblStationInfo);
		
		JLabel lblSwitchInfo = new JLabel("1");
		lblSwitchInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSwitchInfo.setBounds(81, 321, 90, 15);
		statusPanel.add(lblSwitchInfo);
		
		JLabel lblUnderInfo = new JLabel("No");
		lblUnderInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnderInfo.setBounds(91, 346, 80, 15);
		statusPanel.add(lblUnderInfo);
		
		JLabel lblSpeedInfo = new JLabel("N/A");
		lblSpeedInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpeedInfo.setBounds(81, 473, 90, 15);
		statusPanel.add(lblSpeedInfo);
		
		JLabel lblDestInfo = new JLabel("N/A");
		lblDestInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestInfo.setBounds(81, 498, 90, 15);
		statusPanel.add(lblDestInfo);
		
		JLabel lblAuthInfo = new JLabel("N/A");
		lblAuthInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthInfo.setBounds(81, 523, 90, 15);
		statusPanel.add(lblAuthInfo);
		
		JLabel lblCrossing = new JLabel("Railway Crossing:");
		lblCrossing.setBounds(6, 371, 100, 15);
		statusPanel.add(lblCrossing);
		
		JLabel lblCrossingInfo = new JLabel("No");
		lblCrossingInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCrossingInfo.setBounds(130, 371, 41, 15);
		statusPanel.add(lblCrossingInfo);
		
		JPanel schedulePanel = new JPanel();
		schedulePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainMenuTabPane.addTab("Schedule", null, schedulePanel, null);
		schedulePanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 170, 650);
		schedulePanel.add(scrollPane);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("SansSerif", Font.PLAIN, 10));
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Green", "Pioneer", new Float(2.3f)},
				{"Green", "Edgebrook", new Float(2.3f)},
				{"Green", "IngleWood", new Float(2.9f)},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Line", "Destination", "Time"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		scrollPane.setViewportView(table);
		
		JButton btnEditSchedule = new JButton("Edit Schedule");
		btnEditSchedule.setBounds(38, 664, 104, 28);
		schedulePanel.add(btnEditSchedule);
	}
}
