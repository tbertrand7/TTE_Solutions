package trainController;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EditAnnouncementsUI extends JFrame {

	private JPanel contentPane;
	
	private TrainControllerUI tcui;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAnnouncementsUI frame = new EditAnnouncementsUI(null, null);
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
	public EditAnnouncementsUI(TrainControllerUI ui, ArrayList<String> list) {
		tcui = ui;
		
		setTitle("Edit Announcements");
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditAnnouncementsUI.class.getResource("/trainController/computer1.jpg")));
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtArea = new JTextArea();
		txtArea.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);
		txtArea.setBounds(10, 11, 649, 356);
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			sb.append(s);
			sb.append("\n");
		}
		txtArea.setText(sb.toString());
		contentPane.add(txtArea);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tcui.setAnnouncements(txtArea.getText().toCharArray());
			}
		});
		btnSave.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		btnSave.setBounds(290, 378, 89, 33);
		contentPane.add(btnSave);
	}
}
