import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.SpinnerModel;

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
	private String[] dateTime;
	private String dateTimes;
	private String dateTimes1;
	private String[] filterLocName;
	private String[] filterDataType;
	private String[] filterDataValue;
	private String[] filterDateTime;

	private String[] dataTime;
	private String dataType2;

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
		dateChooser.setBounds(312, 248, 130, 20);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		contentPane.add(dateChooser);

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(615, 248, 130, 20);
		contentPane.add(dateChooser_1);

		JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setBounds(446, 248, 75, 16);
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date());
		contentPane.add(timeSpinner);

		JSpinner timeSpinner1 = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor1 = new JSpinner.DateEditor(timeSpinner1, "HH:mm:ss");
		timeSpinner1.setEditor(timeEditor1);
		timeSpinner1.setValue(new Date());
		timeSpinner1.setBounds(748, 248, 75, 16);
		contentPane.add(timeSpinner1);

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

		btnFlag.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String flagged = CityOfficialSearchFilterPOI.selectedFlag;
				String location = CityOfficialSearchFilterPOI.selectedLocation;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (flagged.equals("no")) {
					try {
						String newSql = "Update POI SET flag = 1 WHERE locName = '" + location + "'";
						String dateSql = "Update POI SET dateFlagged = CURRENT_TIMESTAMP WHERE locName = '" + location
								+ "'";
						ConnectDB db = new ConnectDB();
						conn = db.getConnection();
						PreparedStatement statement = conn.prepareStatement(newSql);
						PreparedStatement statement2 = conn.prepareStatement(dateSql);
						statement.executeUpdate();
						statement2.executeUpdate();
					} catch (SQLException exception) {
						exception.printStackTrace();
					}
				} else {
					try {
						String newSql = "Update POI SET flag = 0 WHERE locName = '" + location + "'";
						String dateSql = "Update POI SET dateFlagged = null WHERE locName = '" + location
								+ "'";
						ConnectDB db = new ConnectDB();
						conn = db.getConnection();
						PreparedStatement statement = conn.prepareStatement(newSql);
						PreparedStatement statement2 = conn.prepareStatement(dateSql);
						statement.executeUpdate();
						statement2.executeUpdate();
					} catch (SQLException exception) {
						exception.printStackTrace();
					}
				}
			}
		});

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
		JComboBox<String> type_comboBox = new JComboBox<String>();// dataType
		type_comboBox.addItem("Mold");
		type_comboBox.addItem("Air Quality");
		type_comboBox.setBounds(422, 85, 147, 39);
		contentPane.add(type_comboBox);

		type_comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				dataType2 = type_comboBox.getItemAt(type_comboBox.getSelectedIndex());
			}
		});

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
			String sql = "SELECT * FROM DataPoint WHERE locName = '" + CityOfficialSearchFilterPOI.selectedLocation
					+ "';";
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
			dateTime = new String[num];
			dataType = new String[num];

			for (int i = 0; i < num; i++) {
				locName[i] = dummyLocName[i];
				dataValue[i] = dummyDataValue[i];
				dateTime[i] = dummyTime[i];
				dataType[i] = dummyDataType[i];
			}
			tmodel.setRowCount(num);
			for (int i = 0; i < num; i++) {
				table.setValueAt(dataType[i], i, 0);
				table.setValueAt(dataValue[i], i, 1);
				table.setValueAt(dateTime[i], i, 2);
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

		btnApplyFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str_date = "";
				String str_date1 = "";
				Date date = dateChooser.getDate();
				Date date1 = dateChooser_1.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				System.out.println(dateTime);
				if (date != null) {
					str_date = sdf.format(date);
					dateTimes = str_date + " " + timeEditor.getFormat().format(timeSpinner.getValue());
				}
				if (date1 != null) {
					str_date1 = sdf.format(date1);
					dateTimes1 = str_date1 + " " + timeEditor.getFormat().format(timeSpinner1.getValue());
				}
				System.out.println(dateTimes); // testing
				System.out.println(dateTimes1); // testing
				System.out.println(str_date.compareTo(str_date1)); // testing

				try {
					String sql = "SELECT * FROM DataPoint WHERE accepted IS NULL";
					if (str_date.compareTo(str_date1) > 1) {
						String message = "Invalid date to date. Please enter the correct range";
						JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
					} else {
						if (!dateTimes.equals("") && !dateTimes1.equals("")) {

							if (Double.parseDouble(textField.getText()) < Double.parseDouble(textField_1.getText())) {
								sql = "SELECT * FROM DataPoint WHERE accepted IS NULL AND dateTime BETWEEN '"
										+ dateTimes + "' AND '" + dateTimes1 + "' AND locName = '"
										+ CityOfficialSearchFilterPOI.selectedLocation + "' AND ;";
								System.out.println(sql);
								System.out.println("works"); // testing

							} else {
								String message = "Invalid data value to data value. Please enter the correct range";
								JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
										JOptionPane.ERROR_MESSAGE);
							}

						} else {
							String message = "Please enter the range of data time";
							JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
						}
					}
					for (int i = 0; i < table.getRowCount(); i++) {
						table.setValueAt("", i, 0);
						table.setValueAt("", i, 1);
						table.setValueAt("", i, 2);
					}

					ConnectDB db = new ConnectDB();
					conn = db.getConnection();
					System.out.println(sql + "end");
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					int num_filter = 0;

					String[] dummyDataType = new String[1000];
					String[] dummyDataValue = new String[1000];
					String[] dummyLocName = new String[1000];
					String[] dummyDateTime = new String[1000];

					while (rs.next()) {
						String locName_str = rs.getString("locName");
						String dateTime_str = rs.getString("dateTime");
						String dataValue_str = rs.getString("dataValue");
						String dataType_str = rs.getString("dataType");

						dummyLocName[num_filter] = locName_str;
						dummyDataValue[num_filter] = dataValue_str;
						dummyDataType[num_filter] = dataType_str;
						dummyDateTime[num_filter] = dateTime_str;
						num_filter++;
					}
					filterLocName = new String[num_filter];
					filterDataType = new String[num_filter];
					filterDataValue = new String[num_filter];
					filterDateTime = new String[num_filter];

					for (int i = 0; i < num_filter; i++) {
						filterLocName[i] = dummyLocName[i];
						filterDataType[i] = dummyDataType[i];
						filterDataValue[i] = dummyDataValue[i];
						filterDateTime[i] = dummyDateTime[i];
					}
					tmodel.setRowCount(num_filter);
					for (int i = 0; i < num_filter; i++) {
						table.setValueAt(filterDataType[i], i, 0);
						table.setValueAt(filterDataValue[i], i, 1);
						table.setValueAt(filterDateTime[i], i, 2);
					}
					rs.close();
					stmt.close();
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});

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
