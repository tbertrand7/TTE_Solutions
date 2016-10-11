package ctcOffice;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.text.*;

import ctcOffice.CTCOffice.Mode;

import java.text.*;
import java.util.*;

public class OfficeUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextArea notificationArea;
	private JTable table;
	private JSlider simulationSpeed;
	private CTCOffice ctcOffice;
	private JTextField txtFieldSpeed;
	private JToggleButton[] greenLine = new JToggleButton[152];
	private JToggleButton[] redLine = new JToggleButton[77];

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
		ctcOffice = new CTCOffice();	
		
		setFont(new Font("SansSerif", Font.PLAIN, 16));
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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(OfficeUI.class.getResource("/shared/TTE.png")));
		setBackground(new Color(240, 240, 240));
		setTitle("CTC Office Control Panel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1172, 862);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("SansSerif", Font.PLAIN, 16));
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("SansSerif", Font.PLAIN, 14));
		menuBar.add(mnFile);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mnFile.add(mntmLogout);
		
		JMenu mnSchedule = new JMenu("Schedule");
		mnSchedule.setFont(new Font("SansSerif", Font.PLAIN, 14));
		menuBar.add(mnSchedule);
		
		JMenuItem mntmLoadSchedule = new JMenuItem("Load Schedule");
		mntmLoadSchedule.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mnSchedule.add(mntmLoadSchedule);
		
		JMenuItem mntmEditSchedule = new JMenuItem("Edit Schedule");
		mntmEditSchedule.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mnSchedule.add(mntmEditSchedule);
		
		JMenuItem mntmScheduleByTrain = new JMenuItem("Schedule By Train");
		mntmScheduleByTrain.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mnSchedule.add(mntmScheduleByTrain);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel trackDisplayPanel = new JPanel();
		trackDisplayPanel.setBackground(new Color(255, 255, 255));
		trackDisplayPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		trackDisplayPanel.setBounds(419, 64, 737, 733);
		contentPane.add(trackDisplayPanel);
		trackDisplayPanel.setLayout(null);
		
		JToggleButton toggleButtonG1 = new JToggleButton("");
		toggleButtonG1.setSelected(true);
		toggleButtonG1.setBackground(Color.LIGHT_GRAY);
		toggleButtonG1.setBounds(354, 24, 15, 15);
		trackDisplayPanel.add(toggleButtonG1);
		greenLine[0] = toggleButtonG1;
		
		JToggleButton toggleButtonG2 = new JToggleButton("");
		toggleButtonG2.setBackground(Color.LIGHT_GRAY);
		toggleButtonG2.setBounds(373, 44, 20, 20);
		trackDisplayPanel.add(toggleButtonG2);
		greenLine[1] = toggleButtonG2;
		
		JToggleButton toggleButtonG3 = new JToggleButton("");
		toggleButtonG3.setBackground(Color.LIGHT_GRAY);
		toggleButtonG3.setBounds(387, 61, 15, 15);
		trackDisplayPanel.add(toggleButtonG3);
		greenLine[2] = toggleButtonG3;
		
		JToggleButton toggleButtonG4 = new JToggleButton("");
		toggleButtonG4.setBackground(Color.LIGHT_GRAY);
		toggleButtonG4.setBounds(404, 76, 15, 15);
		trackDisplayPanel.add(toggleButtonG4);
		greenLine[3] = toggleButtonG4;
		
		JToggleButton toggleButtonG5 = new JToggleButton("");
		toggleButtonG5.setBackground(Color.LIGHT_GRAY);
		toggleButtonG5.setBounds(427, 91, 15, 15);
		trackDisplayPanel.add(toggleButtonG5);
		greenLine[4] = toggleButtonG5;
		
		JToggleButton toggleButtonG6 = new JToggleButton("");
		toggleButtonG6.setBackground(Color.LIGHT_GRAY);
		toggleButtonG6.setBounds(455, 97, 15, 15);
		trackDisplayPanel.add(toggleButtonG6);
		greenLine[5] = toggleButtonG6;
		
		JToggleButton toggleButtonG7 = new JToggleButton("");
		toggleButtonG7.setBackground(Color.LIGHT_GRAY);
		toggleButtonG7.setBounds(502, 91, 15, 15);
		trackDisplayPanel.add(toggleButtonG7);
		greenLine[6] = toggleButtonG7;
		
		JToggleButton toggleButtonG8 = new JToggleButton("");
		toggleButtonG8.setBackground(Color.LIGHT_GRAY);
		toggleButtonG8.setBounds(557, 68, 15, 15);
		trackDisplayPanel.add(toggleButtonG8);
		greenLine[7] = toggleButtonG8;
		
		JToggleButton toggleButtonG9 = new JToggleButton("");
		toggleButtonG9.setBackground(Color.LIGHT_GRAY);
		toggleButtonG9.setBounds(491, 32, 20, 20);
		trackDisplayPanel.add(toggleButtonG9);
		greenLine[8] = toggleButtonG9;
		
		JToggleButton toggleButtonG10 = new JToggleButton("");
		toggleButtonG10.setBackground(Color.LIGHT_GRAY);
		toggleButtonG10.setBounds(466, 25, 15, 15);
		trackDisplayPanel.add(toggleButtonG10);
		greenLine[9] = toggleButtonG10;
		
		JToggleButton toggleButtonG11 = new JToggleButton("");
		toggleButtonG11.setBackground(Color.LIGHT_GRAY);
		toggleButtonG11.setBounds(427, 20, 15, 15);
		trackDisplayPanel.add(toggleButtonG11);
		greenLine[10] = toggleButtonG11;
		
		JToggleButton toggleButtonG12 = new JToggleButton("");
		toggleButtonG12.setBackground(Color.LIGHT_GRAY);
		toggleButtonG12.setBounds(354, 11, 15, 15);
		trackDisplayPanel.add(toggleButtonG12);
		greenLine[11] = toggleButtonG12;
		
		JToggleButton toggleButtonG13 = new JToggleButton("");
		toggleButtonG13.setBackground(Color.LIGHT_GRAY);
		toggleButtonG13.setBounds(302, 14, 15, 15);
		trackDisplayPanel.add(toggleButtonG13);
		greenLine[12] = toggleButtonG13;
		
		JToggleButton toggleButtonG14 = new JToggleButton("");
		toggleButtonG14.setBackground(Color.LIGHT_GRAY);
		toggleButtonG14.setBounds(280, 14, 15, 15);
		trackDisplayPanel.add(toggleButtonG14);
		greenLine[13] = toggleButtonG14;
		
		JToggleButton toggleButtonG15 = new JToggleButton("");
		toggleButtonG15.setBackground(Color.LIGHT_GRAY);
		toggleButtonG15.setBounds(255, 15, 15, 15);
		trackDisplayPanel.add(toggleButtonG15);
		greenLine[14] = toggleButtonG15;
		
		JToggleButton toggleButtonG16 = new JToggleButton("");
		toggleButtonG16.setBackground(Color.LIGHT_GRAY);
		toggleButtonG16.setBounds(235, 14, 20, 20);
		trackDisplayPanel.add(toggleButtonG16);
		greenLine[15] = toggleButtonG16;
		
		JToggleButton toggleButtonG17 = new JToggleButton("");
		toggleButtonG17.setBackground(Color.LIGHT_GRAY);
		toggleButtonG17.setBounds(188, 22, 15, 15);
		trackDisplayPanel.add(toggleButtonG17);
		greenLine[16] = toggleButtonG17;
		
		JToggleButton toggleButtonG18 = new JToggleButton("");
		toggleButtonG18.setBackground(Color.LIGHT_GRAY);
		toggleButtonG18.setBounds(167, 34, 15, 15);
		trackDisplayPanel.add(toggleButtonG18);
		greenLine[17] = toggleButtonG18;
		
		JToggleButton toggleButtonG19 = new JToggleButton("");
		toggleButtonG19.setBackground(Color.LIGHT_GRAY);
		toggleButtonG19.setBounds(150, 47, 15, 15);
		trackDisplayPanel.add(toggleButtonG19);
		greenLine[18] = toggleButtonG19;
		
		JToggleButton toggleButtonG20 = new JToggleButton("");
		toggleButtonG20.setBackground(Color.LIGHT_GRAY);
		toggleButtonG20.setBounds(138, 61, 15, 15);
		trackDisplayPanel.add(toggleButtonG20);
		greenLine[19] = toggleButtonG20;
		
		JToggleButton toggleButtonG21 = new JToggleButton("");
		toggleButtonG21.setBackground(Color.LIGHT_GRAY);
		toggleButtonG21.setBounds(134, 93, 15, 15);
		trackDisplayPanel.add(toggleButtonG21);
		greenLine[20] = toggleButtonG21;
		
		JToggleButton toggleButtonG22 = new JToggleButton("");
		toggleButtonG22.setBackground(Color.LIGHT_GRAY);
		toggleButtonG22.setBounds(132, 105, 20, 20);
		trackDisplayPanel.add(toggleButtonG22);
		greenLine[21] = toggleButtonG22;
		
		JToggleButton toggleButtonG23 = new JToggleButton("");
		toggleButtonG23.setBackground(Color.LIGHT_GRAY);
		toggleButtonG23.setBounds(135, 122, 15, 15);
		trackDisplayPanel.add(toggleButtonG23);
		greenLine[22] = toggleButtonG23;
		
		JToggleButton toggleButtonG24 = new JToggleButton("");
		toggleButtonG24.setBackground(Color.LIGHT_GRAY);
		toggleButtonG24.setBounds(135, 134, 15, 15);
		trackDisplayPanel.add(toggleButtonG24);
		greenLine[23] = toggleButtonG24;
		
		JToggleButton toggleButtonG25 = new JToggleButton("");
		toggleButtonG25.setBackground(Color.LIGHT_GRAY);
		toggleButtonG25.setBounds(135, 146, 15, 15);
		trackDisplayPanel.add(toggleButtonG25);
		greenLine[24] = toggleButtonG25;
		
		JToggleButton toggleButtonG26 = new JToggleButton("");
		toggleButtonG26.setBackground(Color.YELLOW);
		toggleButtonG26.setBounds(135, 158, 15, 15);
		trackDisplayPanel.add(toggleButtonG26);
		greenLine[25] = toggleButtonG26;
		
		JToggleButton toggleButtonG27 = new JToggleButton("");
		toggleButtonG27.setBackground(Color.LIGHT_GRAY);
		toggleButtonG27.setBounds(135, 170, 15, 15);
		trackDisplayPanel.add(toggleButtonG27);
		greenLine[26] = toggleButtonG27;
		
		JToggleButton toggleButtonG28 = new JToggleButton("");
		toggleButtonG28.setBackground(Color.LIGHT_GRAY);
		toggleButtonG28.setBounds(135, 182, 15, 15);
		trackDisplayPanel.add(toggleButtonG28);
		greenLine[27] = toggleButtonG28;
		
		JToggleButton toggleButtonG29 = new JToggleButton("");
		toggleButtonG29.setBackground(Color.LIGHT_GRAY);
		toggleButtonG29.setBounds(134, 199, 15, 15);
		trackDisplayPanel.add(toggleButtonG29);
		greenLine[28] = toggleButtonG29;
		
		JToggleButton toggleButtonG30 = new JToggleButton("");
		toggleButtonG30.setBackground(Color.LIGHT_GRAY);
		toggleButtonG30.setBounds(134, 211, 15, 15);
		trackDisplayPanel.add(toggleButtonG30);
		greenLine[29] = toggleButtonG30;
		
		JToggleButton toggleButtonG31 = new JToggleButton("");
		toggleButtonG31.setBackground(Color.BLUE);
		toggleButtonG31.setBounds(132, 223, 20, 20);
		trackDisplayPanel.add(toggleButtonG31);
		greenLine[30] = toggleButtonG31;
		
		JToggleButton toggleButtonG32 = new JToggleButton("");
		toggleButtonG32.setBackground(Color.LIGHT_GRAY);
		toggleButtonG32.setBounds(135, 240, 15, 15);
		trackDisplayPanel.add(toggleButtonG32);
		greenLine[31] = toggleButtonG32;
		
		JToggleButton toggleButtonG33 = new JToggleButton("");
		toggleButtonG33.setBackground(Color.LIGHT_GRAY);
		toggleButtonG33.setBounds(136, 262, 15, 15);
		trackDisplayPanel.add(toggleButtonG33);
		greenLine[32] = toggleButtonG33;
		
		JToggleButton toggleButtonG34 = new JToggleButton("");
		toggleButtonG34.setBackground(Color.LIGHT_GRAY);
		toggleButtonG34.setBounds(147, 281, 15, 15);
		trackDisplayPanel.add(toggleButtonG34);
		greenLine[33] = toggleButtonG34;
		
		JToggleButton toggleButtonG35 = new JToggleButton("");
		toggleButtonG35.setBackground(Color.LIGHT_GRAY);
		toggleButtonG35.setBounds(177, 299, 15, 15);
		trackDisplayPanel.add(toggleButtonG35);
		greenLine[34] = toggleButtonG35;
		
		JToggleButton toggleButtonG36 = new JToggleButton("");
		toggleButtonG36.setBackground(Color.LIGHT_GRAY);
		toggleButtonG36.setBounds(205, 303, 15, 15);
		trackDisplayPanel.add(toggleButtonG36);
		greenLine[35] = toggleButtonG36;
		
		JToggleButton toggleButtonG37 = new JToggleButton("");
		toggleButtonG37.setBackground(Color.LIGHT_GRAY);
		toggleButtonG37.setBounds(218, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG37);
		greenLine[36] = toggleButtonG37;
		
		JToggleButton toggleButtonG38 = new JToggleButton("");
		toggleButtonG38.setBackground(Color.LIGHT_GRAY);
		toggleButtonG38.setBounds(230, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG38);
		greenLine[37] = toggleButtonG38;
		
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
		toggleButtonG62.setBounds(656, 356, 15, 15);
		trackDisplayPanel.add(toggleButtonG62);
		
		JToggleButton toggleButtonG63 = new JToggleButton("");
		toggleButtonG63.setBackground(Color.LIGHT_GRAY);
		toggleButtonG63.setBounds(676, 413, 15, 15);
		trackDisplayPanel.add(toggleButtonG63);
		
		JToggleButton toggleButtonG64 = new JToggleButton("");
		toggleButtonG64.setBackground(Color.LIGHT_GRAY);
		toggleButtonG64.setBounds(676, 434, 15, 15);
		trackDisplayPanel.add(toggleButtonG64);
		
		JToggleButton toggleButtonG65 = new JToggleButton("");
		toggleButtonG65.setBackground(Color.LIGHT_GRAY);
		toggleButtonG65.setBounds(673, 454, 20, 20);
		trackDisplayPanel.add(toggleButtonG65);
		
		JToggleButton toggleButtonG66 = new JToggleButton("");
		toggleButtonG66.setBackground(Color.LIGHT_GRAY);
		toggleButtonG66.setBounds(675, 483, 15, 15);
		trackDisplayPanel.add(toggleButtonG66);
		
		JToggleButton toggleButtonG67 = new JToggleButton("");
		toggleButtonG67.setBackground(Color.LIGHT_GRAY);
		toggleButtonG67.setBounds(675, 513, 15, 15);
		trackDisplayPanel.add(toggleButtonG67);
		
		JToggleButton toggleButtonG68 = new JToggleButton("");
		toggleButtonG68.setBackground(Color.LIGHT_GRAY);
		toggleButtonG68.setBounds(676, 549, 15, 15);
		trackDisplayPanel.add(toggleButtonG68);
		
		JToggleButton toggleButtonG69 = new JToggleButton("");
		toggleButtonG69.setBackground(Color.LIGHT_GRAY);
		toggleButtonG69.setBounds(671, 623, 15, 15);
		trackDisplayPanel.add(toggleButtonG69);
		
		JToggleButton toggleButtonG70 = new JToggleButton("");
		toggleButtonG70.setBackground(Color.LIGHT_GRAY);
		toggleButtonG70.setBounds(660, 651, 15, 15);
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
		toggleButtonG100.setBounds(175, 694, 15, 15);
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
		toggleButtonG108.setBounds(605, 617, 15, 15);
		trackDisplayPanel.add(toggleButtonG108);
		
		JToggleButton toggleButtonG109 = new JToggleButton("");
		toggleButtonG109.setBackground(Color.LIGHT_GRAY);
		toggleButtonG109.setBounds(625, 584, 15, 15);
		trackDisplayPanel.add(toggleButtonG109);
		
		JToggleButton toggleButtonG110 = new JToggleButton("");
		toggleButtonG110.setBackground(Color.LIGHT_GRAY);
		toggleButtonG110.setBounds(631, 543, 15, 15);
		trackDisplayPanel.add(toggleButtonG110);
		
		JToggleButton toggleButtonG111 = new JToggleButton("");
		toggleButtonG111.setBackground(Color.LIGHT_GRAY);
		toggleButtonG111.setBounds(632, 529, 15, 15);
		trackDisplayPanel.add(toggleButtonG111);
		
		JToggleButton toggleButtonG112 = new JToggleButton("");
		toggleButtonG112.setBackground(Color.LIGHT_GRAY);
		toggleButtonG112.setBounds(631, 513, 15, 15);
		trackDisplayPanel.add(toggleButtonG112);
		
		JToggleButton toggleButtonG113 = new JToggleButton("");
		toggleButtonG113.setBackground(Color.LIGHT_GRAY);
		toggleButtonG113.setBounds(631, 499, 15, 15);
		trackDisplayPanel.add(toggleButtonG113);
		
		JToggleButton toggleButtonG114 = new JToggleButton("");
		toggleButtonG114.setBackground(Color.LIGHT_GRAY);
		toggleButtonG114.setBounds(631, 482, 20, 20);
		trackDisplayPanel.add(toggleButtonG114);
		
		JToggleButton toggleButtonG115 = new JToggleButton("");
		toggleButtonG115.setBackground(Color.LIGHT_GRAY);
		toggleButtonG115.setBounds(631, 468, 15, 15);
		trackDisplayPanel.add(toggleButtonG115);
		
		JToggleButton toggleButtonG116 = new JToggleButton("");
		toggleButtonG116.setBackground(Color.LIGHT_GRAY);
		toggleButtonG116.setBounds(631, 454, 15, 15);
		trackDisplayPanel.add(toggleButtonG116);
		
		JToggleButton toggleButtonG117 = new JToggleButton("");
		toggleButtonG117.setBackground(Color.LIGHT_GRAY);
		toggleButtonG117.setBounds(628, 420, 15, 15);
		trackDisplayPanel.add(toggleButtonG117);
		
		JToggleButton toggleButtonG118 = new JToggleButton("");
		toggleButtonG118.setBackground(Color.LIGHT_GRAY);
		toggleButtonG118.setBounds(618, 400, 15, 15);
		trackDisplayPanel.add(toggleButtonG118);
		
		JToggleButton toggleButtonG119 = new JToggleButton("");
		toggleButtonG119.setBackground(Color.LIGHT_GRAY);
		toggleButtonG119.setBounds(601, 381, 15, 15);
		trackDisplayPanel.add(toggleButtonG119);
		
		JToggleButton toggleButtonG120 = new JToggleButton("");
		toggleButtonG120.setBackground(Color.LIGHT_GRAY);
		toggleButtonG120.setBounds(576, 362, 15, 15);
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
		toggleButtonG151.setBounds(604, 292, 15, 15);
		trackDisplayPanel.add(toggleButtonG151);
		
		JToggleButton toggleButtonG152 = new JToggleButton("");
		toggleButtonG152.setBackground(Color.LIGHT_GRAY);
		toggleButtonG152.setBounds(672, 323, 15, 15);
		trackDisplayPanel.add(toggleButtonG152);
		
		JToggleButton toggleButtonR1 = new JToggleButton("");
		toggleButtonR1.setBackground(Color.LIGHT_GRAY);
		toggleButtonR1.setBounds(500, 179, 15, 15);
		trackDisplayPanel.add(toggleButtonR1);
		
		JToggleButton toggleButtonR2 = new JToggleButton("");
		toggleButtonR2.setBackground(Color.LIGHT_GRAY);
		toggleButtonR2.setBounds(522, 175, 15, 15);
		trackDisplayPanel.add(toggleButtonR2);
		
		JToggleButton toggleButtonR3 = new JToggleButton("");
		toggleButtonR3.setBackground(Color.LIGHT_GRAY);
		toggleButtonR3.setBounds(537, 168, 15, 15);
		trackDisplayPanel.add(toggleButtonR3);
		
		JToggleButton toggleButtonR4 = new JToggleButton("");
		toggleButtonR4.setBackground(Color.LIGHT_GRAY);
		toggleButtonR4.setBounds(549, 137, 15, 15);
		trackDisplayPanel.add(toggleButtonR4);
		
		JToggleButton toggleButtonR5 = new JToggleButton("");
		toggleButtonR5.setBackground(Color.LIGHT_GRAY);
		toggleButtonR5.setBounds(569, 124, 15, 15);
		trackDisplayPanel.add(toggleButtonR5);
		
		JToggleButton toggleButtonR6 = new JToggleButton("");
		toggleButtonR6.setBackground(Color.LIGHT_GRAY);
		toggleButtonR6.setBounds(591, 115, 15, 15);
		trackDisplayPanel.add(toggleButtonR6);
		
		JToggleButton toggleButtonR7 = new JToggleButton("");
		toggleButtonR7.setBackground(Color.LIGHT_GRAY);
		toggleButtonR7.setBounds(637, 108, 20, 20);
		trackDisplayPanel.add(toggleButtonR7);
		
		JToggleButton toggleButtonR8 = new JToggleButton("");
		toggleButtonR8.setBackground(Color.LIGHT_GRAY);
		toggleButtonR8.setBounds(665, 121, 15, 15);
		trackDisplayPanel.add(toggleButtonR8);
		
		JToggleButton toggleButtonR9 = new JToggleButton("");
		toggleButtonR9.setBackground(Color.LIGHT_GRAY);
		toggleButtonR9.setBounds(691, 133, 15, 15);
		trackDisplayPanel.add(toggleButtonR9);
		
		JToggleButton toggleButtonR10 = new JToggleButton("");
		toggleButtonR10.setBackground(Color.LIGHT_GRAY);
		toggleButtonR10.setBounds(684, 176, 15, 15);
		trackDisplayPanel.add(toggleButtonR10);
		
		JToggleButton toggleButtonR11 = new JToggleButton("");
		toggleButtonR11.setBackground(Color.LIGHT_GRAY);
		toggleButtonR11.setBounds(656, 186, 15, 15);
		trackDisplayPanel.add(toggleButtonR11);
		
		JToggleButton toggleButtonR12 = new JToggleButton("");
		toggleButtonR12.setBackground(Color.LIGHT_GRAY);
		toggleButtonR12.setBounds(618, 190, 15, 15);
		trackDisplayPanel.add(toggleButtonR12);
		
		JToggleButton toggleButtonR13 = new JToggleButton("");
		toggleButtonR13.setBackground(Color.LIGHT_GRAY);
		toggleButtonR13.setBounds(549, 193, 15, 15);
		trackDisplayPanel.add(toggleButtonR13);
		
		JToggleButton toggleButtonR14 = new JToggleButton("");
		toggleButtonR14.setBackground(Color.LIGHT_GRAY);
		toggleButtonR14.setBounds(524, 192, 15, 15);
		trackDisplayPanel.add(toggleButtonR14);
		
		JToggleButton toggleButtonR15 = new JToggleButton("");
		toggleButtonR15.setBackground(Color.LIGHT_GRAY);
		toggleButtonR15.setBounds(498, 193, 15, 15);
		trackDisplayPanel.add(toggleButtonR15);
		
		JToggleButton toggleButtonR16 = new JToggleButton("");
		toggleButtonR16.setBackground(Color.LIGHT_GRAY);
		toggleButtonR16.setBounds(456, 191, 20, 20);
		trackDisplayPanel.add(toggleButtonR16);
		
		JToggleButton toggleButtonR17 = new JToggleButton("");
		toggleButtonR17.setBackground(Color.LIGHT_GRAY);
		toggleButtonR17.setBounds(444, 191, 15, 15);
		trackDisplayPanel.add(toggleButtonR17);
		
		JToggleButton toggleButtonR18 = new JToggleButton("");
		toggleButtonR18.setBackground(Color.LIGHT_GRAY);
		toggleButtonR18.setBounds(432, 191, 15, 15);
		trackDisplayPanel.add(toggleButtonR18);
		
		JToggleButton toggleButtonR19 = new JToggleButton("");
		toggleButtonR19.setBackground(Color.LIGHT_GRAY);
		toggleButtonR19.setBounds(420, 191, 15, 15);
		trackDisplayPanel.add(toggleButtonR19);
		
		JToggleButton toggleButtonR20 = new JToggleButton("");
		toggleButtonR20.setBackground(Color.LIGHT_GRAY);
		toggleButtonR20.setBounds(408, 191, 15, 15);
		trackDisplayPanel.add(toggleButtonR20);
		
		JToggleButton toggleButtonR21 = new JToggleButton("");
		toggleButtonR21.setBackground(Color.LIGHT_GRAY);
		toggleButtonR21.setBounds(373, 194, 20, 20);
		trackDisplayPanel.add(toggleButtonR21);
		
		JToggleButton toggleButtonR22 = new JToggleButton("");
		toggleButtonR22.setBackground(Color.LIGHT_GRAY);
		toggleButtonR22.setBounds(359, 201, 15, 15);
		trackDisplayPanel.add(toggleButtonR22);
		
		JToggleButton toggleButtonR23 = new JToggleButton("");
		toggleButtonR23.setBackground(Color.LIGHT_GRAY);
		toggleButtonR23.setBounds(346, 207, 15, 15);
		trackDisplayPanel.add(toggleButtonR23);
		
		JToggleButton toggleButtonR24 = new JToggleButton("");
		toggleButtonR24.setBackground(Color.LIGHT_GRAY);
		toggleButtonR24.setBounds(330, 222, 15, 15);
		trackDisplayPanel.add(toggleButtonR24);
		
		JToggleButton toggleButtonR25 = new JToggleButton("");
		toggleButtonR25.setBackground(Color.LIGHT_GRAY);
		toggleButtonR25.setBounds(329, 233, 20, 20);
		trackDisplayPanel.add(toggleButtonR25);
		
		JToggleButton toggleButtonR26 = new JToggleButton("");
		toggleButtonR26.setBackground(Color.LIGHT_GRAY);
		toggleButtonR26.setBounds(330, 250, 15, 15);
		trackDisplayPanel.add(toggleButtonR26);
		
		JToggleButton toggleButtonR27 = new JToggleButton("");
		toggleButtonR27.setBackground(Color.LIGHT_GRAY);
		toggleButtonR27.setBounds(330, 262, 15, 15);
		trackDisplayPanel.add(toggleButtonR27);
		
		JToggleButton toggleButtonR28 = new JToggleButton("");
		toggleButtonR28.setBackground(Color.LIGHT_GRAY);
		toggleButtonR28.setBounds(330, 274, 15, 15);
		trackDisplayPanel.add(toggleButtonR28);
		
		JToggleButton toggleButtonR29 = new JToggleButton("");
		toggleButtonR29.setBackground(Color.LIGHT_GRAY);
		toggleButtonR29.setBounds(330, 286, 15, 15);
		trackDisplayPanel.add(toggleButtonR29);
		
		JToggleButton toggleButtonR30 = new JToggleButton("");
		toggleButtonR30.setBackground(Color.LIGHT_GRAY);
		toggleButtonR30.setBounds(330, 298, 15, 15);
		trackDisplayPanel.add(toggleButtonR30);
		
		JToggleButton toggleButtonR31 = new JToggleButton("");
		toggleButtonR31.setBackground(Color.LIGHT_GRAY);
		toggleButtonR31.setBounds(331, 314, 15, 15);
		trackDisplayPanel.add(toggleButtonR31);
		
		JToggleButton toggleButtonR32 = new JToggleButton("");
		toggleButtonR32.setBackground(Color.LIGHT_GRAY);
		toggleButtonR32.setBounds(331, 326, 15, 15);
		trackDisplayPanel.add(toggleButtonR32);
		
		JToggleButton toggleButtonR33 = new JToggleButton("");
		toggleButtonR33.setBackground(Color.LIGHT_GRAY);
		toggleButtonR33.setBounds(330, 338, 15, 15);
		trackDisplayPanel.add(toggleButtonR33);
		
		JToggleButton toggleButtonR34 = new JToggleButton("");
		toggleButtonR34.setBackground(Color.LIGHT_GRAY);
		toggleButtonR34.setBounds(330, 351, 15, 15);
		trackDisplayPanel.add(toggleButtonR34);
		
		JToggleButton toggleButtonR35 = new JToggleButton("");
		toggleButtonR35.setBackground(Color.LIGHT_GRAY);
		toggleButtonR35.setBounds(329, 363, 20, 20);
		trackDisplayPanel.add(toggleButtonR35);
		
		JToggleButton toggleButtonR36 = new JToggleButton("");
		toggleButtonR36.setBackground(Color.LIGHT_GRAY);
		toggleButtonR36.setBounds(330, 380, 15, 15);
		trackDisplayPanel.add(toggleButtonR36);
		
		JToggleButton toggleButtonR37 = new JToggleButton("");
		toggleButtonR37.setBackground(Color.LIGHT_GRAY);
		toggleButtonR37.setBounds(330, 391, 15, 15);
		trackDisplayPanel.add(toggleButtonR37);
		
		JToggleButton toggleButtonR38 = new JToggleButton("");
		toggleButtonR38.setBackground(Color.LIGHT_GRAY);
		toggleButtonR38.setBounds(330, 402, 15, 15);
		trackDisplayPanel.add(toggleButtonR38);
		
		JToggleButton toggleButtonR39 = new JToggleButton("");
		toggleButtonR39.setBackground(Color.LIGHT_GRAY);
		toggleButtonR39.setBounds(330, 413, 15, 15);
		trackDisplayPanel.add(toggleButtonR39);
		
		JToggleButton toggleButtonR40 = new JToggleButton("");
		toggleButtonR40.setBackground(Color.LIGHT_GRAY);
		toggleButtonR40.setBounds(331, 425, 15, 15);
		trackDisplayPanel.add(toggleButtonR40);
		
		JToggleButton toggleButtonR41 = new JToggleButton("");
		toggleButtonR41.setBackground(Color.LIGHT_GRAY);
		toggleButtonR41.setBounds(331, 436, 15, 15);
		trackDisplayPanel.add(toggleButtonR41);
		
		JToggleButton toggleButtonR42 = new JToggleButton("");
		toggleButtonR42.setBackground(Color.LIGHT_GRAY);
		toggleButtonR42.setBounds(331, 447, 15, 15);
		trackDisplayPanel.add(toggleButtonR42);
		
		JToggleButton toggleButtonR43 = new JToggleButton("");
		toggleButtonR43.setBackground(Color.LIGHT_GRAY);
		toggleButtonR43.setBounds(331, 459, 15, 15);
		trackDisplayPanel.add(toggleButtonR43);
		
		JToggleButton toggleButtonR44 = new JToggleButton("");
		toggleButtonR44.setBackground(Color.LIGHT_GRAY);
		toggleButtonR44.setBounds(331, 470, 15, 15);
		trackDisplayPanel.add(toggleButtonR44);
		
		JToggleButton toggleButtonR45 = new JToggleButton("");
		toggleButtonR45.setBackground(Color.LIGHT_GRAY);
		toggleButtonR45.setBounds(329, 482, 20, 20);
		trackDisplayPanel.add(toggleButtonR45);
		
		JToggleButton toggleButtonR46 = new JToggleButton("");
		toggleButtonR46.setBackground(Color.LIGHT_GRAY);
		toggleButtonR46.setBounds(318, 527, 15, 15);
		trackDisplayPanel.add(toggleButtonR46);
		
		JToggleButton toggleButtonR47 = new JToggleButton("");
		toggleButtonR47.setBackground(Color.LIGHT_GRAY);
		toggleButtonR47.setBounds(293, 547, 15, 15);
		trackDisplayPanel.add(toggleButtonR47);
		
		JToggleButton toggleButtonR48 = new JToggleButton("");
		toggleButtonR48.setBackground(Color.LIGHT_GRAY);
		toggleButtonR48.setBounds(263, 557, 20, 20);
		trackDisplayPanel.add(toggleButtonR48);
		
		JToggleButton toggleButtonR49 = new JToggleButton("");
		toggleButtonR49.setBackground(Color.LIGHT_GRAY);
		toggleButtonR49.setBounds(223, 561, 15, 15);
		trackDisplayPanel.add(toggleButtonR49);
		
		JToggleButton toggleButtonR50 = new JToggleButton("");
		toggleButtonR50.setBackground(Color.LIGHT_GRAY);
		toggleButtonR50.setBounds(208, 562, 15, 15);
		trackDisplayPanel.add(toggleButtonR50);
		
		JToggleButton toggleButtonR51 = new JToggleButton("");
		toggleButtonR51.setBackground(Color.LIGHT_GRAY);
		toggleButtonR51.setBounds(192, 561, 15, 15);
		trackDisplayPanel.add(toggleButtonR51);
		
		JToggleButton toggleButtonR52 = new JToggleButton("");
		toggleButtonR52.setBackground(Color.LIGHT_GRAY);
		toggleButtonR52.setBounds(152, 562, 15, 15);
		trackDisplayPanel.add(toggleButtonR52);
		
		JToggleButton toggleButtonR53 = new JToggleButton("");
		toggleButtonR53.setBackground(Color.LIGHT_GRAY);
		toggleButtonR53.setBounds(132, 561, 15, 15);
		trackDisplayPanel.add(toggleButtonR53);
		
		JToggleButton toggleButtonR54 = new JToggleButton("");
		toggleButtonR54.setBackground(Color.LIGHT_GRAY);
		toggleButtonR54.setBounds(114, 559, 15, 15);
		trackDisplayPanel.add(toggleButtonR54);
		
		JToggleButton toggleButtonR55 = new JToggleButton("");
		toggleButtonR55.setBackground(Color.LIGHT_GRAY);
		toggleButtonR55.setBounds(73, 554, 15, 15);
		trackDisplayPanel.add(toggleButtonR55);
		
		JToggleButton toggleButtonR56 = new JToggleButton("");
		toggleButtonR56.setBackground(Color.LIGHT_GRAY);
		toggleButtonR56.setBounds(42, 536, 15, 15);
		trackDisplayPanel.add(toggleButtonR56);
		
		JToggleButton toggleButtonR57 = new JToggleButton("");
		toggleButtonR57.setBackground(Color.LIGHT_GRAY);
		toggleButtonR57.setBounds(22, 499, 15, 15);
		trackDisplayPanel.add(toggleButtonR57);
		
		JToggleButton toggleButtonR58 = new JToggleButton("");
		toggleButtonR58.setBackground(Color.LIGHT_GRAY);
		toggleButtonR58.setBounds(26, 449, 15, 15);
		trackDisplayPanel.add(toggleButtonR58);
		
		JToggleButton toggleButtonR59 = new JToggleButton("");
		toggleButtonR59.setBackground(Color.LIGHT_GRAY);
		toggleButtonR59.setBounds(40, 432, 15, 15);
		trackDisplayPanel.add(toggleButtonR59);
		
		JToggleButton toggleButtonR60 = new JToggleButton("");
		toggleButtonR60.setBackground(Color.LIGHT_GRAY);
		toggleButtonR60.setBounds(55, 417, 20, 20);
		trackDisplayPanel.add(toggleButtonR60);
		
		JToggleButton toggleButtonR61 = new JToggleButton("");
		toggleButtonR61.setBackground(Color.LIGHT_GRAY);
		toggleButtonR61.setBounds(92, 424, 15, 15);
		trackDisplayPanel.add(toggleButtonR61);
		
		JToggleButton toggleButtonR62 = new JToggleButton("");
		toggleButtonR62.setBackground(Color.LIGHT_GRAY);
		toggleButtonR62.setBounds(105, 447, 15, 15);
		trackDisplayPanel.add(toggleButtonR62);
		
		JToggleButton toggleButtonR63 = new JToggleButton("");
		toggleButtonR63.setBackground(Color.LIGHT_GRAY);
		toggleButtonR63.setBounds(116, 488, 15, 15);
		trackDisplayPanel.add(toggleButtonR63);
		
		JToggleButton toggleButtonR64 = new JToggleButton("");
		toggleButtonR64.setBackground(Color.LIGHT_GRAY);
		toggleButtonR64.setBounds(122, 532, 15, 15);
		trackDisplayPanel.add(toggleButtonR64);
		
		JToggleButton toggleButtonR65 = new JToggleButton("");
		toggleButtonR65.setBackground(Color.LIGHT_GRAY);
		toggleButtonR65.setBounds(134, 545, 15, 15);
		trackDisplayPanel.add(toggleButtonR65);
		
		JToggleButton toggleButtonR66 = new JToggleButton("");
		toggleButtonR66.setBackground(Color.LIGHT_GRAY);
		toggleButtonR66.setBounds(152, 550, 15, 15);
		trackDisplayPanel.add(toggleButtonR66);
		
		JToggleButton toggleButtonR67 = new JToggleButton("");
		toggleButtonR67.setBackground(Color.LIGHT_GRAY);
		toggleButtonR67.setBounds(308, 466, 15, 15);
		trackDisplayPanel.add(toggleButtonR67);
		
		JToggleButton toggleButtonR68 = new JToggleButton("");
		toggleButtonR68.setBackground(Color.LIGHT_GRAY);
		toggleButtonR68.setBounds(285, 446, 15, 15);
		trackDisplayPanel.add(toggleButtonR68);
		
		JToggleButton toggleButtonR69 = new JToggleButton("");
		toggleButtonR69.setBackground(Color.LIGHT_GRAY);
		toggleButtonR69.setBounds(285, 430, 15, 15);
		trackDisplayPanel.add(toggleButtonR69);
		
		JToggleButton toggleButtonR70 = new JToggleButton("");
		toggleButtonR70.setBackground(Color.LIGHT_GRAY);
		toggleButtonR70.setBounds(285, 415, 15, 15);
		trackDisplayPanel.add(toggleButtonR70);
		
		JToggleButton toggleButtonR71 = new JToggleButton("");
		toggleButtonR71.setBackground(Color.LIGHT_GRAY);
		toggleButtonR71.setBounds(308, 399, 15, 15);
		trackDisplayPanel.add(toggleButtonR71);
		
		JToggleButton toggleButtonR72 = new JToggleButton("");
		toggleButtonR72.setBackground(Color.LIGHT_GRAY);
		toggleButtonR72.setBounds(303, 325, 15, 15);
		trackDisplayPanel.add(toggleButtonR72);
		
		JToggleButton toggleButtonR73 = new JToggleButton("");
		toggleButtonR73.setBackground(Color.LIGHT_GRAY);
		toggleButtonR73.setBounds(283, 314, 15, 15);
		trackDisplayPanel.add(toggleButtonR73);
		
		JToggleButton toggleButtonR74 = new JToggleButton("");
		toggleButtonR74.setBackground(Color.LIGHT_GRAY);
		toggleButtonR74.setBounds(283, 297, 15, 15);
		trackDisplayPanel.add(toggleButtonR74);
		
		JToggleButton toggleButtonR75 = new JToggleButton("");
		toggleButtonR75.setBackground(Color.LIGHT_GRAY);
		toggleButtonR75.setBounds(283, 285, 15, 15);
		trackDisplayPanel.add(toggleButtonR75);
		
		JToggleButton toggleButtonR76 = new JToggleButton("");
		toggleButtonR76.setBackground(Color.BLACK);
		toggleButtonR76.setBounds(303, 268, 15, 15);
		trackDisplayPanel.add(toggleButtonR76);
		
		JToggleButton toggleButtonR77 = new JToggleButton("");
		toggleButtonR77.setBackground(Color.LIGHT_GRAY);
		toggleButtonR77.setBounds(692, 196, 15, 15);
		trackDisplayPanel.add(toggleButtonR77);
		
		JLabel lblTrackPicture = new JLabel("");
		lblTrackPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrackPicture.setIcon(new ImageIcon(OfficeUI.class.getResource("/ctcOffice/track.png")));
		lblTrackPicture.setBounds(2, 2, 730, 729);
		trackDisplayPanel.add(lblTrackPicture);
		
		JPanel topButtonPanel = new JPanel();
		topButtonPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		topButtonPanel.setBounds(0, 0, 1156, 66);
		contentPane.add(topButtonPanel);
		topButtonPanel.setLayout(null);
		
		simulationSpeed = new JSlider();
		simulationSpeed.setFont(new Font("SansSerif", Font.PLAIN, 16));
		simulationSpeed.setToolTipText("Set speed of auto simulation");
		simulationSpeed.setMajorTickSpacing(3);
		simulationSpeed.setSnapToTicks(true);
		simulationSpeed.setPaintLabels(true);
		simulationSpeed.setPaintTicks(true);
		simulationSpeed.setMinimum(1);
		simulationSpeed.setMinorTickSpacing(1);
		simulationSpeed.setMaximum(10);
		simulationSpeed.setBounds(971, 20, 175, 42);
		simulationSpeed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				simulationSpeedChanged();
			}
		});
		topButtonPanel.add(simulationSpeed);
		
		JRadioButton rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setFont(new Font("SansSerif", Font.PLAIN, 16));
		rdbtnManual.setToolTipText("Enable manual mode");
		rdbtnManual.setSelected(true);
		rdbtnManual.setBounds(885, 12, 80, 23);
		rdbtnManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnManual_Click();
			}
		});
		topButtonPanel.add(rdbtnManual);
		
		JRadioButton rdbtnAuto = new JRadioButton("Auto");
		rdbtnAuto.setFont(new Font("SansSerif", Font.PLAIN, 16));
		rdbtnAuto.setToolTipText("Enable automatic simulation");
		rdbtnAuto.setBounds(885, 32, 80, 23);
		rdbtnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAuto_Click();
			}
		});
		topButtonPanel.add(rdbtnAuto);		
		
		ButtonGroup execMode = new ButtonGroup();
		execMode.add(rdbtnAuto);
		execMode.add(rdbtnManual);
		
		JLabel lblSimulationSpeed = new JLabel("Simulation Speed");
		lblSimulationSpeed.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSimulationSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSimulationSpeed.setBounds(971, 2, 175, 18);
		topButtonPanel.add(lblSimulationSpeed);
		
		JLabel lblCtcOfficeControl = new JLabel("CTC Office Control Panel");
		lblCtcOfficeControl.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtcOfficeControl.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblCtcOfficeControl.setBounds(182, 0, 691, 64);
		topButtonPanel.add(lblCtcOfficeControl);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(OfficeUI.class.getResource("/ctcOffice/officeLogo.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(2, 2, 180, 62);
		topButtonPanel.add(lblLogo);
		
		JTabbedPane mainMenuTabPane = new JTabbedPane(JTabbedPane.TOP);
		mainMenuTabPane.setFont(new Font("SansSerif", Font.PLAIN, 16));
		mainMenuTabPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainMenuTabPane.setBounds(0, 297, 420, 500);
		contentPane.add(mainMenuTabPane);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainMenuTabPane.addTab("Details", null, statusPanel, null);
		statusPanel.setLayout(null);
		
		JLabel lblTrackInfo = new JLabel("Track Info");
		lblTrackInfo.setBounds(5, 6, 165, 14);
		statusPanel.add(lblTrackInfo);
		lblTrackInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTrackInfo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblLine = new JLabel("Line:");
		lblLine.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblLine.setBounds(5, 31, 75, 15);
		statusPanel.add(lblLine);
		
		JLabel lblLineInfo = new JLabel("Green");
		lblLineInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblLineInfo.setBounds(80, 31, 90, 15);
		statusPanel.add(lblLineInfo);
		lblLineInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSection.setBounds(5, 56, 75, 15);
		statusPanel.add(lblSection);
		
		JLabel lblBlock = new JLabel("Block:");
		lblBlock.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblBlock.setBounds(5, 81, 58, 15);
		statusPanel.add(lblBlock);
		
		JLabel lblLength = new JLabel("Length:");
		lblLength.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLength.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblLength.setBounds(5, 106, 75, 19);
		statusPanel.add(lblLength);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblGrade.setBounds(5, 131, 75, 15);
		statusPanel.add(lblGrade);
		
		JLabel lblSpeedLimit = new JLabel("Speed Limit:");
		lblSpeedLimit.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSpeedLimit.setBounds(5, 156, 90, 19);
		statusPanel.add(lblSpeedLimit);
		
		JLabel lblElevation = new JLabel("Elevation:");
		lblElevation.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblElevation.setBounds(5, 181, 75, 15);
		statusPanel.add(lblElevation);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblStatus.setBounds(5, 206, 75, 15);
		statusPanel.add(lblStatus);
		
		JButton btnCloseTrack = new JButton("Close Track");
		btnCloseTrack.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnCloseTrack.setBounds(29, 231, 112, 30);
		statusPanel.add(btnCloseTrack);
		
		JLabel lblStatusInfo = new JLabel("Open");
		lblStatusInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblStatusInfo.setBounds(80, 206, 90, 19);
		statusPanel.add(lblStatusInfo);
		lblStatusInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblElevationInfo = new JLabel("0.5 ft");
		lblElevationInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblElevationInfo.setBounds(80, 181, 90, 15);
		statusPanel.add(lblElevationInfo);
		lblElevationInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSpeedLimitInfo = new JLabel("20 mph");
		lblSpeedLimitInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSpeedLimitInfo.setBounds(80, 156, 90, 15);
		statusPanel.add(lblSpeedLimitInfo);
		lblSpeedLimitInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblGradeInfo = new JLabel("0.5%");
		lblGradeInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblGradeInfo.setBounds(80, 131, 90, 15);
		statusPanel.add(lblGradeInfo);
		lblGradeInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblLengthInfo = new JLabel("150 ft");
		lblLengthInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblLengthInfo.setBounds(80, 106, 90, 15);
		statusPanel.add(lblLengthInfo);
		lblLengthInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblBlockInfo = new JLabel("1");
		lblBlockInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblBlockInfo.setBounds(80, 80, 90, 15);
		statusPanel.add(lblBlockInfo);
		lblBlockInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSectionInfo = new JLabel("A");
		lblSectionInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSectionInfo.setBounds(80, 56, 90, 15);
		statusPanel.add(lblSectionInfo);
		lblSectionInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSwitch = new JLabel("Switch:");
		lblSwitch.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSwitch.setBounds(226, 81, 75, 15);
		statusPanel.add(lblSwitch);
		
		JLabel lblStation = new JLabel("Station:");
		lblStation.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblStation.setBounds(226, 31, 75, 15);
		statusPanel.add(lblStation);
		
		JLabel lblInfrastracture = new JLabel("Infrastructure");
		lblInfrastracture.setBounds(238, 6, 165, 14);
		statusPanel.add(lblInfrastracture);
		lblInfrastracture.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfrastracture.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblAuthority = new JLabel("Authority:");
		lblAuthority.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAuthority.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAuthority.setBounds(118, 355, 75, 18);
		statusPanel.add(lblAuthority);
		
		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblDestination.setBounds(118, 330, 90, 14);
		statusPanel.add(lblDestination);
		
		JLabel lblSpeed = new JLabel("Speed:");
		lblSpeed.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSpeed.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSpeed.setBounds(118, 305, 75, 19);
		statusPanel.add(lblSpeed);
		
		JLabel lblTrainInfo = new JLabel("Train Info");
		lblTrainInfo.setBounds(140, 255, 143, 14);
		statusPanel.add(lblTrainInfo);
		lblTrainInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTrainInfo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 16));
		tabbedPane.setBounds(2, 380, 415, 80);
		statusPanel.add(tabbedPane);
		tabbedPane.setToolTipText("");
		
		JPanel speedPanel = new JPanel();
		tabbedPane.addTab("Speed", null, speedPanel, null);
		
		JLabel lblSetNewSpeed = new JLabel("Enter new speed:");
		lblSetNewSpeed.setFont(new Font("SansSerif", Font.PLAIN, 16));
		speedPanel.add(lblSetNewSpeed);
		
		txtFieldSpeed = new JTextField();
		speedPanel.add(txtFieldSpeed);
		txtFieldSpeed.setColumns(10);
		
		JLabel lblMph = new JLabel("mph");
		lblMph.setFont(new Font("SansSerif", Font.PLAIN, 16));
		speedPanel.add(lblMph);
		
		JButton btnSetSpeed = new JButton("Set Speed");
		btnSetSpeed.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnSetSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSetSpeed_Click();
			}
		});
		speedPanel.add(btnSetSpeed);
		
		JPanel authorityPanel = new JPanel();
		tabbedPane.addTab("Authority", null, authorityPanel, null);
		
		JLabel lblEnterNewAuthority = new JLabel("Enter new authority:");
		lblEnterNewAuthority.setFont(new Font("SansSerif", Font.PLAIN, 16));
		authorityPanel.add(lblEnterNewAuthority);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		authorityPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMi = new JLabel("mi");
		lblMi.setFont(new Font("SansSerif", Font.PLAIN, 16));
		authorityPanel.add(lblMi);
		
		JButton btnSetAuthority = new JButton("Set Authority");
		btnSetAuthority.setFont(new Font("SansSerif", Font.PLAIN, 16));
		authorityPanel.add(btnSetAuthority);
		
		JPanel destPanel = new JPanel();
		tabbedPane.addTab("Destination", null, destPanel, null);
		
		JLabel lblSelectNewDestination = new JLabel("Select new destination:");
		lblSelectNewDestination.setFont(new Font("SansSerif", Font.PLAIN, 15));
		destPanel.add(lblSelectNewDestination);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Station Name"}));
		destPanel.add(comboBox);
		
		JButton btnSetDestination = new JButton("Set Destination");
		btnSetDestination.setFont(new Font("SansSerif", Font.PLAIN, 14));
		destPanel.add(btnSetDestination);
		
		JLabel lblUnder = new JLabel("Underground:");
		lblUnder.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUnder.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblUnder.setBounds(226, 133, 103, 19);
		statusPanel.add(lblUnder);
		
		JLabel lblStationInfo = new JLabel("N/A");
		lblStationInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblStationInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStationInfo.setBounds(313, 31, 90, 15);
		statusPanel.add(lblStationInfo);
		
		JLabel lblSwitchInfo = new JLabel("1");
		lblSwitchInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSwitchInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSwitchInfo.setBounds(313, 81, 90, 15);
		statusPanel.add(lblSwitchInfo);
		
		JLabel lblUnderInfo = new JLabel("No");
		lblUnderInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblUnderInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnderInfo.setBounds(323, 133, 80, 15);
		statusPanel.add(lblUnderInfo);
		
		JLabel lblSpeedInfo = new JLabel("N/A");
		lblSpeedInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSpeedInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpeedInfo.setBounds(193, 305, 90, 15);
		statusPanel.add(lblSpeedInfo);
		
		JLabel lblDestInfo = new JLabel("N/A");
		lblDestInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblDestInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestInfo.setBounds(193, 330, 90, 15);
		statusPanel.add(lblDestInfo);
		
		JLabel lblAuthInfo = new JLabel("N/A");
		lblAuthInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAuthInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthInfo.setBounds(193, 355, 90, 15);
		statusPanel.add(lblAuthInfo);
		
		JLabel lblCrossing = new JLabel("Railway Crossing:");
		lblCrossing.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCrossing.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblCrossing.setBounds(226, 158, 125, 19);
		statusPanel.add(lblCrossing);
		
		JLabel lblCrossingInfo = new JLabel("No");
		lblCrossingInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblCrossingInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCrossingInfo.setBounds(362, 158, 41, 15);
		statusPanel.add(lblCrossingInfo);
		
		JLabel lblThroughput = new JLabel("Throughput:");
		lblThroughput.setVerticalAlignment(SwingConstants.BOTTOM);
		lblThroughput.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblThroughput.setBounds(226, 56, 90, 19);
		statusPanel.add(lblThroughput);
		
		JLabel lblThroughputInfo = new JLabel("2 trains/hr");
		lblThroughputInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblThroughputInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblThroughputInfo.setBounds(313, 56, 90, 15);
		statusPanel.add(lblThroughputInfo);
		
		JLabel lblSwitchPos = new JLabel("Switch Position:");
		lblSwitchPos.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSwitchPos.setBounds(226, 106, 125, 15);
		statusPanel.add(lblSwitchPos);
		
		JLabel lblSwPosInfo = new JLabel("1");
		lblSwPosInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSwPosInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSwPosInfo.setBounds(313, 106, 90, 15);
		statusPanel.add(lblSwPosInfo);
		
		JButton btnToggleSwitch = new JButton("Toggle Switch Position");
		btnToggleSwitch.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnToggleSwitch.setBounds(216, 182, 187, 30);
		statusPanel.add(btnToggleSwitch);
		
		JLabel lblTrainNum = new JLabel("Train Number:");
		lblTrainNum.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTrainNum.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTrainNum.setBounds(118, 280, 103, 19);
		statusPanel.add(lblTrainNum);
		
		JLabel lblTrainNumInfo = new JLabel("N/A");
		lblTrainNumInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTrainNumInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTrainNumInfo.setBounds(193, 280, 90, 15);
		statusPanel.add(lblTrainNumInfo);
		
		JPanel schedulePanel = new JPanel();
		schedulePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainMenuTabPane.addTab("Schedule", null, schedulePanel, null);
		schedulePanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 406, 451);
		schedulePanel.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("SansSerif", Font.PLAIN, 14));
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Green", new Integer(1), "Pioneer", new Float(2.3f)},
				{"Green", new Integer(2), "Edgebrook", new Float(2.3f)},
				{"Green", new Integer(3), "IngleWood", new Float(2.9f)},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Line", "Train", "Destination", "Time"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, String.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(45);
		scrollPane.setViewportView(table);
		
		JPanel notificationPanel = new JPanel();
		notificationPanel.setBounds(0, 64, 420, 234);
		contentPane.add(notificationPanel);
		notificationPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		notificationPanel.setLayout(null);
		
		JLabel lblNotifications = new JLabel("Notifications");
		lblNotifications.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotifications.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNotifications.setBounds(122, 6, 165, 22);
		notificationPanel.add(lblNotifications);		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 27, 408, 201);
		notificationPanel.add(scrollPane_1);
		
		notificationArea = new JTextArea();
		notificationArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
		scrollPane_1.setViewportView(notificationArea);
		notificationArea.setEditable(false);
	}
	
	/** Set Manual Mode */
	private void rdbtnManual_Click()
	{
		ctcOffice.setMode(Mode.MAUNAL);
		logNotification("Manual Mode Set");
	}
	
	/** Set Automatic Mode */
	private void rdbtnAuto_Click()
	{
		ctcOffice.setMode(Mode.AUTOMATIC);
		logNotification("Auto Mode Set");
	}
	
	/** Change simulation speed */
	private void simulationSpeedChanged()
	{
		if (!simulationSpeed.getValueIsAdjusting() && simulationSpeed.getValue() != ctcOffice.getSimulationSpeed())
		{
			ctcOffice.setSimulationSpeed(simulationSpeed.getValue());
			logNotification("New Simulation Speed is " + simulationSpeed.getValue() +"X wall clock speed");
		}
	}
	
	private void btnSetSpeed_Click()
	{
		try 
		{
			int newTrainSpeed = Integer.parseInt(txtFieldSpeed.getText());
			
			if (newTrainSpeed < 1 || newTrainSpeed > 1000)
				throw new NumberFormatException();
			else
			{
				ctcOffice.suggestSpeed(newTrainSpeed);
				logNotification("Speed of " + newTrainSpeed + " mph suggested");
				txtFieldSpeed.setText("");
			}
		}
		catch(NumberFormatException nfe)
		{
			logNotification("ERROR: '" + txtFieldSpeed.getText() + "' is not a valid speed");
			txtFieldSpeed.setText("");
		}
	}
	
	private void logNotification(String msg)
	{
		String timeStamp = new SimpleDateFormat("hh:mm:ss aa").format(Calendar.getInstance().getTime());
		notificationArea.append(timeStamp + ": " + msg + "\n");
	}
}
