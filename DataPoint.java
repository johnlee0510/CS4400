import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class DataPoint extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblAddNewData;
	private JLabel lblNewLabel;
	private JLabel lblTimeAndData;
	private JLabel lblDataType;
	private JLabel lblDataValue;
	private JButton btnBack;
	private JButton btnSubmit;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_dataType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataPoint frame = new DataPoint();
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
	public DataPoint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(226, 165, 113, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAddNewLocation = new JButton("Add new Location");
		btnAddNewLocation.setBounds(343, 74, 89, 25);
		contentPane.add(btnAddNewLocation);
		
		lblAddNewData = new JLabel("Add new data point");
		lblAddNewData.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblAddNewData.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewData.setBounds(63, 13, 319, 30);
		contentPane.add(lblAddNewData);
		
		lblNewLabel = new JLabel("POI location name:");
		lblNewLabel.setBounds(83, 75, 109, 22);
		contentPane.add(lblNewLabel);
		
		lblTimeAndData = new JLabel("Time and Data of data reading:");
		lblTimeAndData.setBounds(12, 110, 180, 16);
		contentPane.add(lblTimeAndData);
		
		lblDataType = new JLabel("Data type:");
		lblDataType.setBounds(133, 139, 59, 16);
		contentPane.add(lblDataType);
		
		lblDataValue = new JLabel("Data value:");
		lblDataValue.setBounds(127, 168, 65, 16);
		contentPane.add(lblDataValue);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(83, 215, 97, 25);
		contentPane.add(btnBack);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(242, 215, 97, 25);
		contentPane.add(btnSubmit);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(226, 75, 109, 22);
		contentPane.add(comboBox);
		
		comboBox_dataType = new JComboBox<>();
		comboBox_dataType.addItem("Mold readig");
		comboBox_dataType.addItem("Air Quality reading");
		
		comboBox_dataType.setBounds(226, 136, 113, 22);
		contentPane.add(comboBox_dataType);
		
		
		btnAddNewLocation.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				Location loc = new Location();
				loc.setVisible(true);
			}
		});
		
		btnSubmit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				System.out.println("Submit");
			}
		});
		
		btnBack.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				System.out.println("Back");
			}
		});
	}
}
