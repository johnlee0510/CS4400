import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTable;

public class CityOfficialPOIDetail extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CityOfficialPOIDetail frame = new CityOfficialPOIDetail();
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
	public CityOfficialPOIDetail() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 821);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPoi = new JLabel("POI Detail");
		lblPoi.setBounds(429, 28, 177, 33);
		contentPane.add(lblPoi);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setBounds(83, 88, 115, 33);
		contentPane.add(lblType);
		
		JLabel lblDate = new JLabel("Data value : ");
		lblDate.setBounds(83, 162, 165, 33);
		contentPane.add(lblDate);
		
		JLabel lblNewLabel = new JLabel("Time and Date : ");
		lblNewLabel.setBounds(83, 236, 200, 33);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(422, 85, 147, 39);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(419, 159, 115, 39);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(615, 159, 115, 39);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(560, 162, 33, 33);
		contentPane.add(lblTo);
		
		JButton btnApplyFilter = new JButton("Apply filter");
		btnApplyFilter.setBounds(422, 309, 171, 41);
		contentPane.add(btnApplyFilter);
		
		JButton btnResetFilter = new JButton("Reset filter");
		btnResetFilter.setBounds(658, 309, 171, 41);
		contentPane.add(btnResetFilter);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(0, 378, 1058, 2);
		contentPane.add(horizontalStrut);
		
		JButton btnBackToMenu = new JButton("Back to menu");
		btnBackToMenu.setBounds(435, 680, 213, 41);
		contentPane.add(btnBackToMenu);
		
		JButton btnFlag = new JButton("Flag");
		btnFlag.setBounds(766, 680, 171, 41);
		contentPane.add(btnFlag);
		
		table = new JTable();
		table.setBounds(93, 408, 939, 253);
		contentPane.add(table);
	}
}
