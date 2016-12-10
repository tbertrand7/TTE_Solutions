package waysideController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.ComboBox;

import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class LoadPLCUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoadPLCUI dialog = new LoadPLCUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoadPLCUI() {
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
		setTitle("PLC Startup");
		setBounds(100, 100, 569, 206);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JLabel lblLocationOfPlc = new JLabel("Location of PLC program:");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblLocationOfPlc, 20, SpringLayout.WEST, contentPanel);
		lblLocationOfPlc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPanel.add(lblLocationOfPlc);
		
		textField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, textField, 75, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, textField, -5, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblLocationOfPlc, 3, SpringLayout.NORTH, textField);
		sl_contentPanel.putConstraint(SpringLayout.WEST, textField, 245, SpringLayout.WEST, contentPanel);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sl_contentPanel.putConstraint(SpringLayout.EAST, textField, -57, SpringLayout.EAST, contentPanel);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			lblController = new JLabel("Controller:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblController, 24, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, lblController, 0, SpringLayout.EAST, lblLocationOfPlc);
			lblController.setFont(new Font("Tahoma", Font.PLAIN, 18));
			contentPanel.add(lblController);
		}
		
			JComboBox<String> comboBox = new JComboBox<String>();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBox, -1, SpringLayout.NORTH, lblController);
			sl_contentPanel.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, textField);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, comboBox, -24, SpringLayout.NORTH, textField);
			sl_contentPanel.putConstraint(SpringLayout.EAST, comboBox, 0, SpringLayout.EAST, textField);
			contentPanel.add(comboBox);
			comboBox.addItem("Red 1");
			comboBox.addItem("Red 2");
			comboBox.addItem("Green 1");
			comboBox.addItem("Green 2");
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						boolean pass = WaysideControllerInterface.getInstance().loadPLC(textField.getText(), comboBox.getSelectedItem().toString());
						if(!pass)
							JOptionPane.showMessageDialog(getParent(), "Failed to Load PLC");
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
			}
		}
	}
}
