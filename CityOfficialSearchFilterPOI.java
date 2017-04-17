import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CityOfficialSearchFilterPOI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private String[] city;
	private String[] state;
	private JTextField zipCode_txtField;

	/**
	 * Create the frame.
	 */
	public CityOfficialSearchFilterPOI() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.err.print("ClassNotFoundException: ");
		}
		try {
			String sql = "SELECT * FROM CityState";
			ConnectDB db = new ConnectDB();
			conn = db.getConnection();

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int num = 0;
			String[] dummyCity = new String[1000];
			String[] dummyState = new String[1000];
			while (rs.next()) {
				String city_str = rs.getString("city");
				String state_str = rs.getString("state");
				dummyCity[num] = city_str;
				dummyState[num] = state_str;
				num++;
			}
			city = new String[num];
			state = new String[num];

			for (int i = 0; i < num; i++) {
				city[i] = dummyCity[i];
				state[i] = dummyState[i];
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 724);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblViewPoi = new JLabel("View POI");
		lblViewPoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewPoi.setBounds(445, 25, 115, 33);
		contentPane.add(lblViewPoi);
		
		JLabel lblPoiLocationName = new JLabel("POI location name : ");
		lblPoiLocationName.setBounds(157, 71, 125, 33);
		contentPane.add(lblPoiLocationName);
		
		JLabel label = new JLabel("City : ");
		label.setBounds(157, 130, 78, 33);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("State : ");
		label_1.setBounds(157, 176, 94, 33);
		contentPane.add(label_1);
		
		JLabel lblZipCode = new JLabel("Zip code :");
		lblZipCode.setBounds(157, 222, 139, 33);
		contentPane.add(lblZipCode);
		
		JLabel lblFlagged = new JLabel("Flagged ?");
		lblFlagged.setBounds(157, 268, 115, 33);
		contentPane.add(lblFlagged);
		
		JLabel lblDateFlagged = new JLabel("Date flagged : ");
		lblDateFlagged.setBounds(157, 314, 94, 33);
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
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SuperChooseFunctionalityPage page = new SuperChooseFunctionalityPage();
				page.setVisible(true);
				page.setResizable(false);
			}
		});
		contentPane.add(btnBackToMenu);
		
		JComboBox<String> POI_comboBox = new JComboBox();
		POI_comboBox.setBounds(326, 68, 395, 39);
		contentPane.add(POI_comboBox);
		
		JComboBox<String> city_comboBox = new JComboBox<String>(city);
		city_comboBox.setBounds(326, 126, 395, 39);
		contentPane.add(city_comboBox);
		
		JComboBox<String> state_comboBox = new JComboBox<String>(state);
		state_comboBox.setBounds(326, 175, 115, 39);
		contentPane.add(state_comboBox);
		
		JCheckBox chckbxFlaggedbox = new JCheckBox("FlaggedBox");
		chckbxFlaggedbox.setBounds(326, 266, 221, 41);
		contentPane.add(chckbxFlaggedbox);
		
		zipCode_txtField = new JTextField();
		zipCode_txtField.setBounds(326, 227, 115, 22);
		contentPane.add(zipCode_txtField);
		zipCode_txtField.setColumns(10);
	}
}
