package ctcOffice;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class OfficeUI extends JFrame {

	private JPanel contentPane;

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
		setTitle("CTC Office Control Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel TrackDisplayPanel = new JPanel();
		contentPane.add(TrackDisplayPanel, BorderLayout.CENTER);
		
		JPanel HeaderPanel = new JPanel();
		contentPane.add(HeaderPanel, BorderLayout.NORTH);
		HeaderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCtcOfficeControl = new JLabel("CTC Office Control");
		lblCtcOfficeControl.setFont(new Font("Times New Roman", Font.BOLD, 32));
		HeaderPanel.add(lblCtcOfficeControl);
		
		JPanel MainButtonPanel = new JPanel();
		contentPane.add(MainButtonPanel, BorderLayout.SOUTH);
	}

}
