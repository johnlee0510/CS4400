import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		registerButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				System.out.println("register");
				Register reg = new Register();
				dispose();
				reg.setResizable(false);
				reg.setVisible(true);
			}
		});
		
		loginButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				System.out.println("login clicked");
			}
		});
		
	}
}
