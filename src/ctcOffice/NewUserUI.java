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

	private OfficeLogin officeLogin;

	private JPanel contentPane;
	private JTextField txtFieldUsername;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	private JLabel lblErrorMsg;

	public NewUserUI(OfficeLogin login) {
		setTitle("Create New User");
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewUserUI.class.getResource("/shared/TTE.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 220);
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

		officeLogin = login;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{350, 0};
		gbl_contentPane.rowHeights = new int[]{24, 110, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblErrorMsg = new JLabel("");
		GridBagConstraints gbc_lblErrorMsg = new GridBagConstraints();
		gbc_lblErrorMsg.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorMsg.insets = new Insets(0, 0, 5, 0);
		gbc_lblErrorMsg.gridx = 0;
		gbc_lblErrorMsg.gridy = 0;
		contentPane.add(lblErrorMsg, gbc_lblErrorMsg);
		lblErrorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorMsg.setForeground(Color.RED);
		lblErrorMsg.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblErrorMsg.setVisible(false);
		
		JPanel inputPanel = new JPanel();
		GridBagConstraints gbc_inputPanel = new GridBagConstraints();
		gbc_inputPanel.fill = GridBagConstraints.BOTH;
		gbc_inputPanel.gridx = 0;
		gbc_inputPanel.gridy = 1;
		contentPane.add(inputPanel, gbc_inputPanel);
		GridBagLayout gbl_inputPanel = new GridBagLayout();
		gbl_inputPanel.columnWidths = new int[]{160, 0, 0};
		gbl_inputPanel.rowHeights = new int[]{28, 28, 28, 0, 0};
		gbl_inputPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_inputPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		inputPanel.setLayout(gbl_inputPanel);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		inputPanel.add(lblUsername, gbc_lblUsername);
		lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		txtFieldUsername = new JTextField();
		GridBagConstraints gbc_txtFieldUsername = new GridBagConstraints();
		gbc_txtFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldUsername.gridx = 1;
		gbc_txtFieldUsername.gridy = 0;
		inputPanel.add(txtFieldUsername, gbc_txtFieldUsername);
		txtFieldUsername.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		inputPanel.add(lblPassword, gbc_lblPassword);
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		inputPanel.add(passwordField, gbc_passwordField);
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		GridBagConstraints gbc_lblConfirmPassword = new GridBagConstraints();
		gbc_lblConfirmPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmPassword.gridx = 0;
		gbc_lblConfirmPassword.gridy = 2;
		inputPanel.add(lblConfirmPassword, gbc_lblConfirmPassword);
		lblConfirmPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		passwordFieldConfirm = new JPasswordField();
		GridBagConstraints gbc_passwordFieldConfirm = new GridBagConstraints();
		gbc_passwordFieldConfirm.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldConfirm.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldConfirm.gridx = 1;
		gbc_passwordFieldConfirm.gridy = 2;
		inputPanel.add(passwordFieldConfirm, gbc_passwordFieldConfirm);
		passwordFieldConfirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JButton btnConfirm = new JButton("Confirm");
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfirm.gridx = 0;
		gbc_btnConfirm.gridy = 3;
		inputPanel.add(btnConfirm, gbc_btnConfirm);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConfirmClick();
			}
		});
		btnConfirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 3;
		inputPanel.add(btnCancel, gbc_btnCancel);
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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
			if (!officeLogin.validateNewUser(txtFieldUsername.getText()))
			{
				lblErrorMsg.setText(USERNAME_ALREADY_EXISTS);
				lblErrorMsg.setVisible(true);
			}
			else
			{
				if (!officeLogin.validateNewPassword(passwordField.getText(), passwordFieldConfirm.getText()))
				{
					lblErrorMsg.setText(BAD_PASSWORD);
					lblErrorMsg.setVisible(true);
				}
				else
				{
					officeLogin.createNewUser(txtFieldUsername.getText(), passwordField.getText());
					JOptionPane.showMessageDialog(null, "User " + txtFieldUsername.getText() + " created successfully.", "User Created", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		}
	}
}
