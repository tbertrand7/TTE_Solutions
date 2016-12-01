package ctcOffice;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class DispatchNewUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DispatchNewUI frame = new DispatchNewUI();
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
	public DispatchNewUI() {
		setTitle("Dispatch New Train");
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
		setFont(new Font("SansSerif", Font.PLAIN, 16));
		setIconImage(Toolkit.getDefaultToolkit().getImage(DispatchNewUI.class.getResource("/shared/TTE.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{100, 25, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel topPanel = new JPanel();
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		contentPane.add(topPanel, gbc_topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{100, 0, 0};
		gbl_topPanel.rowHeights = new int[]{30, 30, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		JLabel lblLine = new JLabel("Line:");
		lblLine.setFont(new Font("SansSerif", Font.PLAIN, 16));
		GridBagConstraints gbc_lblLine = new GridBagConstraints();
		gbc_lblLine.insets = new Insets(0, 0, 5, 5);
		gbc_lblLine.gridx = 0;
		gbc_lblLine.gridy = 0;
		topPanel.add(lblLine, gbc_lblLine);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		topPanel.add(comboBox, gbc_comboBox);
		
		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setFont(new Font("SansSerif", Font.PLAIN, 16));
		GridBagConstraints gbc_lblDestination = new GridBagConstraints();
		gbc_lblDestination.insets = new Insets(0, 0, 0, 5);
		gbc_lblDestination.gridx = 0;
		gbc_lblDestination.gridy = 1;
		topPanel.add(lblDestination, gbc_lblDestination);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 1;
		topPanel.add(comboBox_1, gbc_comboBox_1);
		
		JPanel bottomPanel = new JPanel();
		GridBagConstraints gbc_bottomPanel = new GridBagConstraints();
		gbc_bottomPanel.fill = GridBagConstraints.BOTH;
		gbc_bottomPanel.gridx = 0;
		gbc_bottomPanel.gridy = 1;
		contentPane.add(bottomPanel, gbc_bottomPanel);
		
		JButton btnDispatch = new JButton("Dispatch");
		btnDispatch.setFont(new Font("SansSerif", Font.PLAIN, 16));
		bottomPanel.add(btnDispatch);
	}

}
