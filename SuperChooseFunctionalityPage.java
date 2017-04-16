import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class SuperChooseFunctionalityPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SuperChooseFunctionalityPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblChooseFunctionality = new JLabel("Choose Functionality");
		lblChooseFunctionality.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseFunctionality.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChooseFunctionality.setBounds(300, 13, 160, 33);
		contentPane.add(lblChooseFunctionality);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(500, 290, 97, 25);
		contentPane.add(btnLogOut);

		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "log out",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Goodbye, signed out");
					dispose();
					Login page = new Login();
					page.setVisible(true);
					page.setResizable(false);
				}
			}
		});

		JLabel lbLastLogin = new JLabel("");
		lbLastLogin.setBounds(500, 55, 160, 16);
		lbLastLogin.setText(Login.currentUser.getUsername() + "(" + Login.currentUser.getUserType() + ")");
		contentPane.add(lbLastLogin);

		if (Login.currentUser.getIsAdmin() == 1) {
			panel.setBounds(240, 150, 300, 120);
			contentPane.add(panel);
			JButton PendingDataPointButton = new JButton("Pending Data Points");
			panel.add(PendingDataPointButton);
			PendingDataPointButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					AdminRejectOrAccept page;
					
					page = new AdminRejectOrAccept();
					page.setVisible(true);
					page.setResizable(false);
					
				}
			});
			
			JButton pendingCityAccountsButton = new JButton("Pending City Official Accounts");
			panel.add(pendingCityAccountsButton);
			pendingCityAccountsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					AdminRejectOrAcceptOfficials page = new AdminRejectOrAcceptOfficials();
					page.setVisible(true);
					page.setResizable(false);
				}
			});
		} else if (Login.currentUser.getIsCityOfficial() == 1) {
			panel.setBounds(240, 150, 300, 120);
			contentPane.add(panel);
			JButton filerPOIButton = new JButton("Filter/Search POI");
			panel.add(filerPOIButton);
			filerPOIButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

			JButton poiReportButton = new JButton("POI Report");
			panel.add(poiReportButton);
			poiReportButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		} else if (Login.currentUser.getIsCityScientist() == 1) {
			panel.setBounds(240, 150, 300, 60);
			contentPane.add(panel);
			JButton PendingDataPointButton = new JButton("Add a new Data Point");
			panel.add(PendingDataPointButton);
			PendingDataPointButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					CityScientistAddNewDataPoint page = new CityScientistAddNewDataPoint();
					page.setVisible(true);
					page.setResizable(false);
				}
			});
		} else {
			return;
		}

	}
}
