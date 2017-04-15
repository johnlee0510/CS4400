import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;

public class AdminRejectOrAccept extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminRejectOrAccept frame = new AdminRejectOrAccept();
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
	public AdminRejectOrAccept() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1088, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPendingDataPoints = new JLabel("Pending Data Points ");
		lblPendingDataPoints.setBounds(381, 28, 251, 33);
		contentPane.add(lblPendingDataPoints);
		
		table = new JTable();
		table.setBounds(507, 221, -165, -70);
		contentPane.add(table);
		
		table_1 = new JTable();
		table_1.setBounds(43, 104, 966, 236);
		contentPane.add(table_1);
		
		JButton btnBackToMenu = new JButton("Back to menu");
		btnBackToMenu.setBounds(43, 398, 205, 41);
		contentPane.add(btnBackToMenu);
		
		JButton btnReject = new JButton("Reject");
		btnReject.setBounds(369, 398, 171, 41);
		contentPane.add(btnReject);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.setBounds(617, 398, 171, 41);
		contentPane.add(btnAccept);
	}

}
