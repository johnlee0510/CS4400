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

import javax.swing.JTextField;
import java.awt.Choice;
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


	/**
	 * Create the frame.
	 */
	public Register() {
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
		
		Choice choice = new Choice();
		choice.setBounds(251, 415, 200, 40);
		choice.add("City Officials");
		choice.add("City Scientists");
		contentPane.add(choice);
		
		Button createButton = new Button("Create");
		createButton.setBounds(260, 690, 79, 24);
		contentPane.add(createButton);
		
		JLabel lblNewLabel_1 = new JLabel("----------------------Fill out this form if you choose City Officials----------------------");
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
		
		Choice city_choice = new Choice();
		city_choice.setBounds(251, 498, 125, 22);
		contentPane.add(city_choice);
		
		Choice state_choice = new Choice();
		state_choice.setBounds(251, 550, 125, 22);
		contentPane.add(state_choice);
		
		title = new JTextField();
		title.setBounds(251, 599, 200, 25);
		contentPane.add(title);
		title.setColumns(10);
		choice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
	        	 if (choice.getItem(choice.getSelectedIndex()).contains("City Officials")) {
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
		
		
		createButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (!username.getText().isEmpty() && !email.getText().isEmpty() &&
						!password.getPassword().toString().isEmpty() && !confirm_pass.getPassword().toString().isEmpty()) {
					System.out.println("test");
					
					char[] pass = password.getPassword();
					String passString = new String(pass);
					char[] confPass = confirm_pass.getPassword();
					String confPassString = new String(confPass);
					
					if (passString.equals(confPassString)) {
						JOptionPane.showMessageDialog(new JFrame(), "Account created, please login with your new account");
						Login frame = new Login();
						frame.setVisible(true);
						frame.setResizable(false);
						dispose();
					} else {
						String message = "password does not match!";
					    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
					        JOptionPane.ERROR_MESSAGE);
					   System.out.println("error");
					}
				} else {
					String message = "Please fill out all the requirements!";
					    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
					        JOptionPane.ERROR_MESSAGE);
					   System.out.println("error");
				}
				
			}
		});

	}
}
