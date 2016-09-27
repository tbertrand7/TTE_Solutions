package ctcOffice;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;

public class NewUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
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
	public NewUser() {
		setTitle("Create New User");
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewUser.class.getResource("/shared/TTE.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnConfirm.setBounds(50, 122, 84, 33);
		btnConfirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(204, 122, 84, 33);
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(btnCancel);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblUsername.setBounds(37, 29, 148, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblPassword.setBounds(37, 57, 148, 16);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(37, 85, 148, 16);
		contentPane.add(lblConfirmPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textField.setBounds(192, 25, 122, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		passwordField.setBounds(192, 53, 122, 28);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		passwordField_1.setBounds(192, 81, 122, 28);
		contentPane.add(passwordField_1);
		
		JLabel lblErrorMsg = new JLabel("Error: Bad Password");
		lblErrorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorMsg.setForeground(Color.RED);
		lblErrorMsg.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblErrorMsg.setBounds(6, 4, 332, 16);
		contentPane.add(lblErrorMsg);
	}
}
