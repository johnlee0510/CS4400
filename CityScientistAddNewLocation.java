import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class CityScientistAddNewLocation extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_locName;
	private JTextField textField_zip;
	private String[] city;
	private String[] state;
	private String picked_City;
	private String picked_State; 
	private JComboBox<String> comboBox_1;
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
			int num = 1;
			String[] dummyCity = new String[1000];
			String[] dummyState = new String[1000];
			while (rs.next()) {
				String city_str = rs.getString("city");
				String state_str = rs.getString("state");
				dummyCity[num] = city_str;
				dummyState[num] = state_str;
				num++;
			}
			dummyCity[0] = "";
			dummyState[0] = "";
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

		textField_locName = new JTextField();
		textField_locName.setBounds(168, 68, 189, 22);
		contentPane.add(textField_locName);
		textField_locName.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<>(city);
		comboBox.setBounds(168, 97, 189, 22);
		contentPane.add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				picked_City = comboBox.getItemAt(comboBox.getSelectedIndex());
			}
		});

		comboBox_1 = new JComboBox<>(state);
		comboBox_1.insertItemAt("", 0);
		comboBox_1.setBounds(168, 126, 189, 22);
		contentPane.add(comboBox_1);
		comboBox_1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				picked_State = comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
			}
		});

		textField_zip = new JTextField();
		textField_zip.setBounds(168, 155, 189, 22);
		contentPane.add(textField_zip);
		textField_zip.setColumns(10);

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
		
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					PreparedStatement stmt2 = null;
					ConnectDB db = new ConnectDB();
					Connection conn = db.getConnection();
//					String sql1 = "SELECT * FROM POI";
//					stmt1 = conn.prepareStatement(sql1);
//					rs1 = stmt1.executeQuery();
					
					if (textField_locName.getText() != null & textField_zip.getText() != null) {
						String sql2 = "INSERT INTO POI(`locName`, `city`, `State`, `zipCode`, `flag`, `dateFlagged`)"
								+ " VALUES(?, ?, ?, ?, ?, ?)";
						try {
							stmt2 = conn.prepareStatement(sql2);
							stmt2.setString(1, textField_locName.getText());
							stmt2.setString(2, picked_City);
							stmt2.setString(3, picked_State);
							stmt2.setString(4, textField_zip.getText());
							stmt2.setInt(5, 0);
							stmt2.setString(6, null);
							stmt2.executeUpdate();
							
							if (textField_locName.getText() == "" || picked_City == null || picked_State == null || textField_zip.getText() == "" ) {
								throw new IllegalArgumentException("please make sure to fill all required fields");
							}
							
							JOptionPane.showMessageDialog(new JFrame(),
									"new location added");
							CityScientistAddNewDataPoint frame = new CityScientistAddNewDataPoint();
							frame.setVisible(true);
							frame.setResizable(false);
							dispose();
							
							stmt2.close();
						
						} catch (SQLException e2) {
							String message = "Please make sure all fields are filled in";
							JOptionPane.showMessageDialog(new JFrame(), message, "Please make sure all fields are filled in", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						String message = "please fill everything";
						JOptionPane.showMessageDialog(new JFrame(), message);
					}
					conn.close();
				} catch (SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					String message = "State and City should match";
					JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}