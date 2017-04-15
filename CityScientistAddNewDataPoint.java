import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class CityScientistAddNewDataPoint extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 810, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblAddANew = new JLabel("Add a new Data Point");
		lblAddANew.setBounds(206, 28, 303, 33);
		panel.add(lblAddANew);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(235, 89, 246, 39);
		panel.add(comboBox);
		
		JLabel lblPoiLocation = new JLabel("POI location :");
		lblPoiLocation.setBounds(40, 92, 183, 33);
		panel.add(lblPoiLocation);
		
		JButton addLocation = new JButton("Add a new location");
		addLocation.setFont(addLocation.getFont().deriveFont(8));
		addLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addLocation.setBounds(520, 88, 222, 41);
		panel.add(addLocation);
		
		
	}
}
