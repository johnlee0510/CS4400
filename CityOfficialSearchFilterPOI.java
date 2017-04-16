import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class CityOfficialSearchFilterPOI extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CityOfficialSearchFilterPOI frame = new CityOfficialSearchFilterPOI();
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
	public CityOfficialSearchFilterPOI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 724);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblViewPoi = new JLabel("View POI");
		lblViewPoi.setBounds(465, 28, 115, 33);
		contentPane.add(lblViewPoi);
		
		JLabel lblPoiLocationName = new JLabel("POI location name : ");
		lblPoiLocationName.setBounds(26, 68, 244, 33);
		contentPane.add(lblPoiLocationName);
		
		JLabel label = new JLabel("City : ");
		label.setBounds(198, 129, 78, 33);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("State : ");
		label_1.setBounds(182, 178, 94, 33);
		contentPane.add(label_1);
		
		JLabel lblZipCode = new JLabel("Zip code :");
		lblZipCode.setBounds(147, 222, 139, 33);
		contentPane.add(lblZipCode);
		
		JLabel lblFlagged = new JLabel("Flagged ?");
		lblFlagged.setBounds(157, 270, 115, 33);
		contentPane.add(lblFlagged);
		
		JLabel lblDateFlagged = new JLabel("Date flagged : ");
		lblDateFlagged.setBounds(95, 318, 183, 33);
		contentPane.add(lblDateFlagged);
		
		JButton btnApplyFilter = new JButton("Apply filter");
		btnApplyFilter.setBounds(265, 379, 171, 41);
		contentPane.add(btnApplyFilter);
		
		JButton btnResetFilter = new JButton("Reset filter");
		btnResetFilter.setBounds(577, 379, 171, 41);
		contentPane.add(btnResetFilter);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(0, 427, 1078, 16);
		contentPane.add(horizontalStrut);
		
		table = new JTable();
		table.setBounds(26, 448, 1026, 130);
		contentPane.add(table);
		
		JButton btnBackToMenu = new JButton("Back to menu");
		btnBackToMenu.setBounds(405, 584, 208, 41);
		contentPane.add(btnBackToMenu);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(326, 68, 395, 39);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(326, 126, 395, 39);
		contentPane.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(326, 175, 115, 39);
		contentPane.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(326, 219, 171, 39);
		contentPane.add(comboBox_3);
		
		JCheckBox chckbxFlaggedbox = new JCheckBox("FlaggedBox");
		chckbxFlaggedbox.setBounds(326, 266, 221, 41);
		contentPane.add(chckbxFlaggedbox);
	}
}
