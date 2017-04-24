import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.Box;
import javax.swing.JTable;

public class CityOfficialPOIDetail extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private DefaultTableModel tmodel;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private Connection conn;
	private int num;
	private String[] dataType;
	private String[] locName;
	private String[] dataValue;
	private String[] dataTime;

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

		JLabel lblPoi = new JLabel("POI Detail : " + CityOfficialSearchFilterPOI.selectedLocation);
		lblPoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoi.setBounds(381, 28, 349, 33);
		contentPane.add(lblPoi);

		JLabel lblType = new JLabel("Type :");
		lblType.setBounds(168, 88, 115, 33);
		contentPane.add(lblType);

		JLabel lblDate = new JLabel("Data value : ");
		lblDate.setBounds(168, 162, 115, 33);
		contentPane.add(lblDate);

		JLabel lblNewLabel = new JLabel("Time and Date : ");
		lblNewLabel.setBounds(168, 235, 115, 33);
		contentPane.add(lblNewLabel);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(330, 248, 200, 20);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		contentPane.add(dateChooser);

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(616, 248, 200, 20);
		contentPane.add(dateChooser_1);


		JButton btnBackToMenu = new JButton("Back to menu");
		btnBackToMenu.setBounds(295, 691, 171, 41);
		contentPane.add(btnBackToMenu);

		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CityOfficialSearchFilterPOI page = new CityOfficialSearchFilterPOI();
				page.setVisible(true);
				page.setResizable(false);
			}
		});
		JButton btnFlag = new JButton("Flag");
		btnFlag.setBounds(630, 691, 171, 41);
		contentPane.add(btnFlag);
		String[] columnNames = { "Data Type", "Data value", "Time&date of data reading" };
		tmodel = new DefaultTableModel(null, columnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tmodel);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane);
		scrollPane.setBounds(12, 425, 1068, 253);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		for (int i = 1; i < columnNames.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.err.print("ClassNotFoundException: ");
		}
		try {
			String sql = "SELECT * FROM DataType";
			ConnectDB db = new ConnectDB();
			conn = db.getConnection();

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			num = 0;
			String[] dummydataType = new String[1000];

			while (rs.next()) {
				String dataType_str = rs.getString("dataType");
				dummydataType[num] = dataType_str;
				num++;
			}

			dataType = new String[num];

			for (int i = 0; i < num; i++) {
				dataType[i] = dummydataType[i];
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JComboBox<String> type_comboBox = new JComboBox<String>(); //dataType
		type_comboBox.setBounds(422, 85, 147, 39);
		contentPane.add(type_comboBox);

		textField = new JTextField();
		textField.setBounds(419, 159, 115, 39);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(615, 159, 115, 39);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblTo = new JLabel("to");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setBounds(560, 162, 33, 33);
		contentPane.add(lblTo);
		
		try {
			String sql = "SELECT * FROM DataPoint WHERE locName = '" + CityOfficialSearchFilterPOI.selectedLocation + "';";
			ConnectDB db = new ConnectDB();
			conn = db.getConnection();

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			num = 0;

			String[] dummyLocName = new String[1000];
			String[] dummyDataType = new String[1000];
			String[] dummyDataValue = new String[1000];
			String[] dummyTime = new String[1000];
			
			while (rs.next()) {
				String locName_str = rs.getString("locName");
				String dataType_str = rs.getString("dataType");
				String dataValue_str = rs.getString("dataValue");
				String dateTime_str = rs.getString("dateTime");
				
				dummyLocName[num] = locName_str;
				dummyDataType[num] = dataType_str;
				dummyDataValue[num] = dataValue_str;
				dummyTime[num] = dateTime_str;
				num++;
			}
			locName = new String[num];
			dataValue = new String[num];
			dataTime = new String[num];
			dataType = new String[num];
			
			for (int i = 0; i < num; i++) {
				locName[i] = dummyLocName[i];
				dataValue[i] = dummyDataValue[i];
				dataTime[i] = dummyTime[i];
				dataType[i] = dummyDataType[i];
			}
			tmodel.setRowCount(num);
			for (int i = 0; i < num; i++) {
				table.setValueAt(dataType[i], i, 0);
				table.setValueAt(dataValue[i], i, 1);
				table.setValueAt(dataTime[i], i, 2);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JButton btnApplyFilter = new JButton("Apply filter");
		btnApplyFilter.setBounds(295, 309, 171, 41);
		contentPane.add(btnApplyFilter);

		JButton btnResetFilter = new JButton("Reset filter");
		btnResetFilter.setBounds(630, 309, 171, 41);
		contentPane.add(btnResetFilter);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(0, 378, 1058, 2);
		contentPane.add(horizontalStrut);
		
		JLabel label = new JLabel("to");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(560, 243, 33, 33);
		contentPane.add(label);
	}
}
