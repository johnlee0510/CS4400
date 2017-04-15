import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Location extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Location frame = new Location();
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
	public Location() {
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
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(168, 97, 189, 22);
		contentPane.add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<>();
		comboBox_1.setBounds(168, 126, 189, 22);
		contentPane.add(comboBox_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(168, 155, 189, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(126, 204, 97, 25);
		contentPane.add(btnBack);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(270, 204, 97, 25);
		contentPane.add(btnSubmit);
	}
}
