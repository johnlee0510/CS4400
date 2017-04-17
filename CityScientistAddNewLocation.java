import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class CityScientistAddNewLocation extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String[] city;
	private String[] state;

	/**
	 * Create the frame.
	 */
	public CityScientistAddNewLocation() {
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
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add a new location");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(63, 13, 319, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("POI location name:");
		lblNewLabel_1.setBounds(45, 71, 111, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("City: ");
		lblNewLabel_2.setBounds(126, 100, 30, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblState = new JLabel("State:");
		lblState.setBounds(121, 129, 35, 16);
		contentPane.add(lblState);

		JLabel lblZip = new JLabel("Zip code:");
		lblZip.setBounds(103, 158, 53, 16);
		contentPane.add(lblZip);

		textField = new JTextField();
		textField.setBounds(168, 68, 189, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<>(city);
		comboBox.setBounds(168, 97, 189, 22);
		contentPane.add(comboBox);

		JComboBox<String> comboBox_1 = new JComboBox<>(state);
		comboBox_1.setBounds(168, 126, 189, 22);
		contentPane.add(comboBox_1);

		textField_1 = new JTextField();
		textField_1.setBounds(168, 155, 189, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(126, 204, 97, 25);
		contentPane.add(btnBack);

		btnBack.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				CityScientistAddNewDataPoint page = new CityScientistAddNewDataPoint();
				page.setVisible(true);
			}
		});

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(270, 204, 97, 25);
		contentPane.add(btnSubmit);
	}
}
