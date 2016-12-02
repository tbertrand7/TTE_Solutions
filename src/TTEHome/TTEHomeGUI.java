package TTEHome;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ctcOffice.*;
import trackModel.TrackModelUI;
import trainController.TrainControllerInstances;
import trainModel.TrainModel;
import trainModel.trainModelGUI;
import waysideController.TC_UI_main;
import waysideController.TC_UI_startup;
import waysideController.WaysideController;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class TTEHomeGUI {

	private JFrame frmTteTrainHome;
	public static WaysideController wc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TTEHomeGUI window = new TTEHomeGUI();
					window.frmTteTrainHome.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		String[] demoBlocks = new String[]{"102-0","103-0","104-0","105-0","106-0","107-0","108-0","109-0","110-0","111-0","112-0","113-0",
				"114-0","115-0","116-0","117-0","118-0","119-0","120-0","121-0","122-0","123-0","124-0","125-0",
				"126-0","127-0","128-0","129-0","130-0","131-0","132-0","133-0","134-0","135-0","136-0","137-0",
				"138-0","139-0","140-0","141-0","142-0","143-0","144-0","145-0","146-0","147-0","148-0","149-0"};
		wc = new WaysideController("Green",demoBlocks,null);
	}

	/**
	 * Create the application.
	 */
	public TTEHomeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		frmTteTrainHome = new JFrame();
		frmTteTrainHome.setIconImage(Toolkit.getDefaultToolkit().getImage(TTEHomeGUI.class.getResource("/shared/TTE.png")));
		frmTteTrainHome.setTitle("TTE Train Home");
		frmTteTrainHome.setBounds(100, 100, 450, 300);
		frmTteTrainHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTteTrainHome.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("The Office");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new OfficeLogin();
			}
		});
		btnNewButton.setBounds(10, 11, 135, 109);
		frmTteTrainHome.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TTEHomeGUI.class.getResource("/shared/TTESmall.png")));
		lblNewLabel.setBounds(186, 34, 60, 60);
		frmTteTrainHome.getContentPane().add(lblNewLabel);
		
		JButton btnWaysideController = new JButton("Wayside Controller");
		btnWaysideController.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TC_UI_main.main(null);
			}
		});
		btnWaysideController.setBounds(10, 141, 135, 109);
		frmTteTrainHome.getContentPane().add(btnWaysideController);
		
		TrainControllerInstances tci = new TrainControllerInstances();
		TrainModel usethis = tci.createTrain(1, "green");
		
		JButton btnTrainController = new JButton("Train Controller");
		btnTrainController.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tci.newUI();
			}
		});
		btnTrainController.setBounds(289, 11, 135, 109);
		frmTteTrainHome.getContentPane().add(btnTrainController);
		
		//Trains trainModelInstances = new Trains();
		JButton btnTrainModel = new JButton("Train Model");
		btnTrainModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: @Matt revisit after fixing Trains class
				//trainModelInstances.newUI();
				
				trainModelGUI tmg = new trainModelGUI(usethis);
				tci.connectModelToUI(1, tmg);
			}
		});
		btnTrainModel.setBounds(289, 141, 135, 109);
		frmTteTrainHome.getContentPane().add(btnTrainModel);
		
		JButton btnTrackModel = new JButton("Track Model");
		btnTrackModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrackModelUI.main(null);
			}
		});
		btnTrackModel.setBounds(150, 141, 135, 109);
		frmTteTrainHome.getContentPane().add(btnTrackModel);
		
		
	}
}
