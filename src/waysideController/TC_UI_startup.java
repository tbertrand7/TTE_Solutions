package waysideController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TC_UI_startup extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TC_UI_startup dialog = new TC_UI_startup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TC_UI_startup() {
		setTitle("PLC Startup");
		setBounds(100, 100, 363, 119);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JLabel lblLocationOfPlc = new JLabel("Location of PLC program:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblLocationOfPlc, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblLocationOfPlc, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblLocationOfPlc);
		
		textField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, textField, -3, SpringLayout.NORTH, lblLocationOfPlc);
		sl_contentPanel.putConstraint(SpringLayout.WEST, textField, 13, SpringLayout.EAST, lblLocationOfPlc);
		sl_contentPanel.putConstraint(SpringLayout.EAST, textField, -33, SpringLayout.EAST, contentPanel);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
