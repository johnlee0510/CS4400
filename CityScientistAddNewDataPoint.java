import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

public class CityScientistAddNewDataPoint extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CityScientistAddNewDataPoint frame = new CityScientistAddNewDataPoint();
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
	public CityScientistAddNewDataPoint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1091, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblAddANew = new JLabel("Add a new Data Point");
		lblAddANew.setBounds(369, 28, 303, 33);
		panel.add(lblAddANew);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(458, 89, 246, 39);
		panel.add(comboBox);
		
		JLabel lblPoiLocation = new JLabel("POI location :");
		lblPoiLocation.setBounds(249, 89, 183, 33);
		panel.add(lblPoiLocation);
		
		JButton addLocation = new JButton("Add new location");
		addLocation.setFont(addLocation.getFont().deriveFont(8));
		addLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addLocation.setBounds(755, 88, 261, 41);
		panel.add(addLocation);
		
		JLabel lblNewLabel = new JLabel("time and date of date reading :");
		lblNewLabel.setBounds(42, 153, 376, 33);
		panel.add(lblNewLabel);
		
		JLabel lblDataType = new JLabel("Data Type :");
		lblDataType.setBounds(274, 214, 163, 33);
		panel.add(lblDataType);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(458, 211, 246, 39);
		panel.add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(458, 150, 246, 39);
		panel.add(textField);
		textField.setColumns(10);
		
		table = new JTable();
		table.setBounds(533, 333, 1, 1);
		panel.add(table);
		
		textField_1 = new JTextField();
		textField_1.setBounds(458, 278, 246, 39);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDataValue = new JLabel("Data value :");
		lblDataValue.setBounds(268, 281, 150, 33);
		panel.add(lblDataValue);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(583, 384, 203, 41);
		panel.add(btnSubmit);
		
		JButton btnBackToMenu = new JButton("Back to menu");
		btnBackToMenu.setBounds(261, 384, 203, 41);
		panel.add(btnBackToMenu);
		
		
	}
}
