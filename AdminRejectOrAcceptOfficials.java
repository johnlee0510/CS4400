import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;

public class AdminRejectOrAcceptOfficials extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminRejectOrAcceptOfficials frame = new AdminRejectOrAcceptOfficials();
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
	public AdminRejectOrAcceptOfficials() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pending City Official Accounts");
		lblNewLabel.setBounds(335, 28, 365, 33);
		contentPane.add(lblNewLabel);
		
		table = new JTable();
		table.setBounds(81, 89, 915, 262);
		contentPane.add(table);
		
		JButton btnBackToMenu = new JButton("Back to menu");
		btnBackToMenu.setBounds(91, 388, 246, 41);
		contentPane.add(btnBackToMenu);
		
		JButton btnReject = new JButton("Reject");
		btnReject.setBounds(590, 388, 171, 41);
		contentPane.add(btnReject);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.setBounds(825, 388, 171, 41);
		contentPane.add(btnAccept);
	}

}
