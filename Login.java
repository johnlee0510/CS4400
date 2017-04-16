import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton registerButton;
	private String username, password, emailAddress;
	private int isCityOfficial, isAdmin, isCityScientist;
	static User currentUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setResizable(false);
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblUsername.setBounds(180, 131, 115, 50);
		getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblPassword.setBounds(180, 230, 125, 40);
		getContentPane().add(lblPassword);

		textField = new JTextField();
		textField.setBounds(345, 135, 250, 50);
		getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(345, 220, 250, 50);
		getContentPane().add(passwordField);

		loginButton = new JButton("Login");
		loginButton.setBounds(180, 320, 150, 50);
		getContentPane().add(loginButton);

		registerButton = new JButton("Register");
		registerButton.setBounds(445, 320, 150, 50);
		getContentPane().add(registerButton);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setBounds(310, 40, 136, 50);
		getContentPane().add(lblNewLabel);

		registerButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("register");
				Register reg = new Register();
				dispose();
				reg.setResizable(false);
				reg.setVisible(true);
			}
		});

		loginButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PreparedStatement stmt = null;
				ResultSet rs = null;
				Connection conn;
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.err.print("ClassNotFoundException: ");
				}
				try {
					String usernameStr = textField.getText();
					char[] pass = passwordField.getPassword();
					String passString = new String(pass);

					String sql = "SELECT * FROM User WHERE username = '" + usernameStr + "'";
					ConnectDB db = new ConnectDB();
					conn = db.getConnection();

					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					while (rs.next()) {
						username = rs.getString("username");
						password = rs.getString("password");
						emailAddress = rs.getString("emailAddress");
						isCityOfficial = rs.getInt("isCityOfficial");
						isAdmin = rs.getInt("isAdmin");
						isCityScientist = rs.getInt("isCityScientist");

					}
					if (usernameStr.isEmpty() || passString.isEmpty()) {
						JOptionPane.showMessageDialog(new JFrame(), "please fill out your username and password",
								"error", JOptionPane.ERROR_MESSAGE);
					} else {
						if (usernameStr.equals(username)) {
							if (passString.equals(password)) {
								JOptionPane.showMessageDialog(new JFrame(), "welcome back, " + username + "!");
								currentUser = new User(username, emailAddress, password, isCityOfficial, isAdmin,
										isCityScientist);
								dispose();
								SuperChooseFunctionalityPage page = new SuperChooseFunctionalityPage();
								page.setVisible(true);
								page.setResizable(false);
							} else {
								JOptionPane.showMessageDialog(new JFrame(), "password mismatch, please try again",
										"password error", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(new JFrame(), "username does not exist", "error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
					stmt.close();
					conn.close();
				} catch (SQLException ex) {
					System.out.println("SQLException:" + ex.getMessage());
				}
			}
		});
	}
}
