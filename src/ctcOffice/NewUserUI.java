package ctcOffice;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.UIManager.*;

public class NewUserUI extends JFrame {

	private static final String BAD_PASSWORD = "Error: Passwords do not match";
	private static final String USERNAME_ALREADY_EXISTS = "Error: Username already exists";
	private static final String FILL_OUT_FIELDS = "Error: Fill out all fields";
	
	private JPanel contentPane;
	private JTextField txtFieldUsername;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	private JLabel lblErrorMsg;

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
					NewUserUI frame = new NewUserUI();
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
	public NewUserUI() {
		setTitle("Create New User");
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewUserUI.class.getResource("/shared/TTE.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 200);
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
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		txtFieldUsername = new JTextField();
		txtFieldUsername.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtFieldUsername.setBounds(192, 25, 122, 28);
		contentPane.add(txtFieldUsername);
		txtFieldUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		passwordField.setBounds(192, 53, 122, 28);
		contentPane.add(passwordField);
		
		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		passwordFieldConfirm.setBounds(192, 81, 122, 28);
		contentPane.add(passwordFieldConfirm);
		
		lblErrorMsg = new JLabel("");
		lblErrorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorMsg.setForeground(Color.RED);
		lblErrorMsg.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblErrorMsg.setBounds(6, 4, 332, 16);
		lblErrorMsg.setVisible(false);
		contentPane.add(lblErrorMsg);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConfirmClick();
			}
		});
		btnConfirm.setBounds(50, 122, 84, 33);
		btnConfirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(204, 122, 84, 33);
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnCancel);
	}
	
	private void btnConfirmClick()
	{
		if (txtFieldUsername.getText().equals("") || passwordField.getText().equals("") || passwordFieldConfirm.getText().equals(""))
		{
			lblErrorMsg.setText(FILL_OUT_FIELDS);
			lblErrorMsg.setVisible(true);
		}
		else
		{
			if (!OfficeLogin.checkNewUsername(txtFieldUsername.getText()))
			{
				lblErrorMsg.setText(USERNAME_ALREADY_EXISTS);
				lblErrorMsg.setVisible(true);
			}
			else
			{
				if (!OfficeLogin.checkNewPassword(passwordField.getText(), passwordFieldConfirm.getText()))
				{
					lblErrorMsg.setText(BAD_PASSWORD);
					lblErrorMsg.setVisible(true);
				}
				else
				{
					//OfficeLogin.createUser();
					JOptionPane.showMessageDialog(null, "User " + txtFieldUsername.getText() + " created successfully.", "User Created", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		}
	}
}
