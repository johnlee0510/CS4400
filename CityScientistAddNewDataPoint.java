import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class CityScientistAddNewDataPoint extends JFrame {

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
	private JComboBox<String> comboBox_location;
	private JComboBox<String> comboBox_dataType;
	private Connection conn;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private int num;
	private String[] location;
	
	/**
	 * Create the frame.
	 */
	public CityScientistAddNewDataPoint() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.err.print("ClassNotFoundException: ");
		}
		
		try {
			String sql = "SELECT locName FROM POI";
			ConnectDB db = new ConnectDB();
			conn = db.getConnection();
		
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			num = 1;
			String[] dummyLoc = new String[1000];
		
			while (rs.next()) {
				String loc_str = rs.getString("locName");
				dummyLoc[num] = loc_str;
				num++;
			}
			dummyLoc[0] = "";
			location = new String[num];
			
			for (int i = 0; i < num; i++) {
				location[i] = dummyLoc[i];
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(226, 165, 113, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAddNewLocation = new JButton("Add");
		btnAddNewLocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAddNewLocation.setBounds(343, 74, 113, 22);
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

		JDateChooser dateChooser = new JDateChooser();
	    dateChooser.setBounds(226, 106, 163, 20);
		contentPane.add(dateChooser);
		
		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setBounds(395, 110, 75, 16);
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date());
		contentPane.add(timeSpinner);
		
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
		
		comboBox_location = new JComboBox<>(location);
		comboBox_location.setBounds(226, 75, 109, 22);
		contentPane.add(comboBox_location);
		
		comboBox_dataType = new JComboBox<>();
		comboBox_dataType.addItem("Mold");
		comboBox_dataType.addItem("Air Quality");
		
		comboBox_dataType.setBounds(226, 136, 113, 22);
		contentPane.add(comboBox_dataType);
		
		
		btnAddNewLocation.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				dispose();
				CityScientistAddNewLocation loc = new CityScientistAddNewLocation();
				loc.setVisible(true);
				loc.setResizable(false);
			}
		});
		
		btnSubmit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				System.out.println("Submit");
			}
		});
		
		btnBack.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				dispose();
				SuperChooseFunctionalityPage page = new SuperChooseFunctionalityPage();
				page.setVisible(true);
				page.setResizable(false);
			}
		});
	}
	class DateLabelFormatter extends AbstractFormatter {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
}
