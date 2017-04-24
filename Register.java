import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import java.awt.Button;

public class Register extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField email;
	private JPasswordField password;
	private JPasswordField confirm_pass;
	private JLabel lblUserType;
	private JTextField title;
	String[] city, state;
	String picked_city = "";
	String picked_state = "";

	/**
	 * Create the frame.
	 */
	public Register() {
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
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New User Registration");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(132, 80, 319, 30);
		contentPane.add(lblNewLabel);

		JLabel lblUser = new JLabel("*Username");
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblUser.setBounds(60, 200, 150, 40);
		contentPane.add(lblUser);

		JLabel lblEmailAddress = new JLabel("*Email Address");
		lblEmailAddress.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEmailAddress.setBounds(60, 250, 150, 40);
		contentPane.add(lblEmailAddress);

		JLabel lblPassword = new JLabel("*Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPassword.setBounds(60, 300, 150, 40);
		contentPane.add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("*Confirm Password");
		lblConfirmPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(60, 350, 150, 40);
		contentPane.add(lblConfirmPassword);

		username = new JTextField();
		username.setBounds(251, 207, 200, 30);
		contentPane.add(username);
		username.setColumns(10);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(251, 257, 200, 30);
		contentPane.add(email);

		password = new JPasswordField();
		password.setColumns(10);
		password.setBounds(251, 307, 200, 30);
		contentPane.add(password);

		confirm_pass = new JPasswordField();
		confirm_pass.setColumns(10);
		confirm_pass.setBounds(251, 357, 200, 30);
		contentPane.add(confirm_pass);

		lblUserType = new JLabel("*User Type");
		lblUserType.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblUserType.setBounds(60, 403, 150, 40);
		contentPane.add(lblUserType);

		String[] userType = { "City Officials", "City Scientists" };
		JComboBox<String> choice = new JComboBox<>(userType);
		choice.setBounds(251, 415, 200, 30);
		contentPane.add(choice);

		Button createButton = new Button("Create");
		createButton.setBounds(150, 688, 79, 24);
		contentPane.add(createButton);

		JLabel lblNewLabel_1 = new JLabel(
				"----------------------Fill out this form if you choose City Officials----------------------");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(60, 456, 475, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblCity.setBounds(150, 500, 60, 20);
		contentPane.add(lblCity);

		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblState.setBounds(150, 550, 60, 20);
		contentPane.add(lblState);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblTitle.setBounds(154, 600, 60, 20);
		contentPane.add(lblTitle);

		JComboBox<String> city_choice = new JComboBox<>(city);
		city_choice.setBounds(251, 498, 125, 22);
		contentPane.add(city_choice);
		city_choice.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				picked_city = city_choice.getItemAt(city_choice.getSelectedIndex());
			}
		});

		JComboBox<String> state_choice = new JComboBox<>(state);
		state_choice.setBounds(251, 550, 125, 22);
		contentPane.add(state_choice);
		state_choice.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				picked_state = state_choice.getItemAt(state_choice.getSelectedIndex());
			}
		});
		title = new JTextField();
		title.setBounds(251, 599, 200, 25);
		contentPane.add(title);
		title.setColumns(10);

		Button btnCancel = new Button("Cancel");
		btnCancel.setBounds(354, 687, 79, 24);
		contentPane.add(btnCancel);
		
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				Login page = new Login();
				page.setVisible(true);
			}
		});
		
		choice.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (choice.getItemAt(choice.getSelectedIndex()).contains("City Officials")) {
					city_choice.setEnabled(true);
					state_choice.setEnabled(true);
					title.setEnabled(true);
				} else {
					city_choice.setEnabled(false);
					state_choice.setEnabled(false);
					title.setText(null);
					title.setEnabled(false);
				}
			}
		});

		createButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				int userCheck = 0;
				try {
					PreparedStatement stmt1, stmt2 = null;
					ResultSet rs1 = null;
					Connection conn;
					ConnectDB db = new ConnectDB();
					conn = db.getConnection();
					String sql1 = "SELECT COUNT(*) FROM User WHERE username = '" + username.getText() + "'";
					stmt1 = conn.prepareStatement(sql1);
					rs1 = stmt1.executeQuery();
					if (rs1.next()) {
						userCheck = rs1.getInt(1);
					}

					if (!username.getText().isEmpty() && !email.getText().isEmpty()
							&& !password.getPassword().toString().isEmpty()
							&& !confirm_pass.getPassword().toString().isEmpty()) {
						System.out.println("all filled out");

						char[] pass = password.getPassword();
						String passString = new String(pass);
						char[] confPass = confirm_pass.getPassword();
						String confPassString = new String(confPass);

						if (userCheck <= 0) {
							System.out.println("not found existed user, proceed");
							if (passString.equals(confPassString)) {
								System.out.println("same password, proceed");
								if (choice.getItemAt(choice.getSelectedIndex()).contains("City Officials")) {
									if (!title.getText().isEmpty()) {
										String sql2 = "INSERT INTO CityOfficial(`username`, `emailAddress`,`password`,`city`, `state`, `title`,`approval`)"
												+ " VALUES(?, ?, ?, ?, ?, ?, ?)";
										try {
											stmt2 = conn.prepareStatement(sql2);
											stmt2.setString(1, username.getText());
											stmt2.setString(2, email.getText());
											stmt2.setString(3, passString);
											stmt2.setString(4, picked_city);
											stmt2.setString(5, picked_state);
											stmt2.setString(6, title.getText());
											stmt2.setInt(7, 0);
											stmt2.executeUpdate();

											JOptionPane.showMessageDialog(new JFrame(),
													"create account requested, please wait for the Admin to approve your account");
											Login frame = new Login();
											frame.setVisible(true);
											frame.setResizable(false);
											dispose();

										} catch (SQLException e2) {
											String message = "city and state does not match!";
											JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
													JOptionPane.ERROR_MESSAGE);
										}
									} else {
										String message = "please type your title";
										JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
												JOptionPane.ERROR_MESSAGE);
									}
								} else {
									String sql2 = "INSERT INTO User(`username`, `emailAddress`,`password`,`isCityOfficial`, `isAdmin`, `isCityScientist`)"
											+ " VALUES(?, ?, ?, ?, ?, ?)";
									try {
										stmt2 = conn.prepareStatement(sql2);
										stmt2.setString(1, username.getText());
										stmt2.setString(2, email.getText());
										stmt2.setString(3, passString);
										stmt2.setInt(4, 0);
										stmt2.setInt(5, 0);
										stmt2.setInt(6, 1);
										stmt2.executeUpdate();

										JOptionPane.showMessageDialog(new JFrame(),
												"Account created, please log in with your new account");
										Login frame = new Login();
										frame.setVisible(true);
										frame.setResizable(false);
										dispose();

									} catch (SQLException e2) {
										System.out.println("insert data error");
									}
								}

							} else {
								String message = "password does not match!";
								JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
										JOptionPane.ERROR_MESSAGE);
								System.out.println("error");
							}
						} else {
							String message = "username is already exist!";
							JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						String message = "Please fill out all the requirements!";
						JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
					}
					conn.close();
					stmt1.close();
					stmt2.close();
					rs1.close();
				} catch (SQLException ex) {
				}
			}

		});

	}
}
