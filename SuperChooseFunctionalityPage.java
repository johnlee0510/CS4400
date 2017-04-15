import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class SuperChooseFunctionalityPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuperChooseFunctionalityPage frame = new SuperChooseFunctionalityPage();
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
	public SuperChooseFunctionalityPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton PendingDataPointButton = new JButton("Pending Data Points");
		PendingDataPointButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		PendingDataPointButton.setBounds(304, 80, 379, 36);
		contentPane.add(PendingDataPointButton);
		
		JButton pendingCityAccountsButton  = new JButton("Pending City Official Accounts");
		pendingCityAccountsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pendingCityAccountsButton.setBounds(288, 144, 416, 36);
		contentPane.add(pendingCityAccountsButton);
		
		JButton filerPOIButton = new JButton("Filter/Search POI");
		filerPOIButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		filerPOIButton.setBounds(304, 208, 379, 36);
		contentPane.add(filerPOIButton);
		
		JButton poiReportButton = new JButton("POI Report");
		poiReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		poiReportButton.setBounds(304, 272, 379, 36);
		contentPane.add(poiReportButton);
		
		JLabel lblChooseFunctionality = new JLabel("Choose Functionality");
		lblChooseFunctionality.setBounds(370, 15, 255, 33);
		contentPane.add(lblChooseFunctionality);
		
		JButton btnAddANew = new JButton("Add a new Data Point");
		btnAddANew.setBounds(342, 336, 316, 41);
		contentPane.add(btnAddANew);
	}
}

