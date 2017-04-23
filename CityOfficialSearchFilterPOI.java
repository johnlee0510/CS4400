import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CityOfficialSearchFilterPOI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private String[] city;
	private String[] state;
	private String[] city_view;
	private String[] state_view;
	private String[] locName;
	private String[] locName_spin;
	private String[] zipCode;
	// private int[] flag; // original
	private String[] flag;
	private String[] dateFlagged;
	private JTextField zipCode_txtField;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private Connection conn;
	private DefaultTableModel tmodel;
	private String picked_POILoc;
	private String picked_city;
	private String picked_state;
	private int num;
	private int checked = 0;

	/**
	 * Create the frame.
	 */
	public CityOfficialSearchFilterPOI() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.err.print("ClassNotFoundException: ");
		}
		try {
			String sql = "SELECT * FROM CityState";
			ConnectDB db = new ConnectDB();
			conn = db.getConnection();

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			num = 1;
			String[] dummyCity = new String[1000];
			String[] dummyState = new String[1000];

			while (rs.next()) {
				String city_str = rs.getString("city");
				String state_str = rs.getString("state");
				dummyCity[num] = city_str;
				dummyState[num] = state_str;
				num++;
			}
			dummyCity[0] = "";
			dummyState[0] = "";
			city = new String[num];
			state = new String[num];

			for (int i = 0; i < num; i++) {
				city[i] = dummyCity[i];
				state[i] = dummyState[i];
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			String sql = "SELECT locName FROM POI";
			ConnectDB db = new ConnectDB();
			conn = db.getConnection();

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int num = 1;
			String[] dummyLocName = new String[1000];
			while (rs.next()) {
				String locName_str = rs.getString("locName");

				dummyLocName[num] = locName_str;
				num++;
			}
			dummyLocName[0] = "";
			locName_spin = new String[num];

			for (int i = 0; i < num; i++) {
				locName_spin[i] = dummyLocName[i];
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 724);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblViewPoi = new JLabel("View POI");
		lblViewPoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewPoi.setBounds(445, 25, 115, 33);
		contentPane.add(lblViewPoi);

		JLabel lblPoiLocationName = new JLabel("POI location name : ");
		lblPoiLocationName.setBounds(157, 71, 125, 33);
		contentPane.add(lblPoiLocationName);

		JLabel label = new JLabel("City : ");
		label.setBounds(157, 130, 78, 33);
		contentPane.add(label);

		JLabel label_1 = new JLabel("State : ");
		label_1.setBounds(157, 176, 94, 33);
		contentPane.add(label_1);

		JLabel lblZipCode = new JLabel("Zip code :");
		lblZipCode.setBounds(157, 222, 139, 33);
		contentPane.add(lblZipCode);

		JLabel lblFlagged = new JLabel("Flagged ?");
		lblFlagged.setBounds(157, 268, 115, 33);
		contentPane.add(lblFlagged);

		JLabel lblDateFlagged = new JLabel("Date flagged : ");
		lblDateFlagged.setBounds(157, 314, 94, 33);
		contentPane.add(lblDateFlagged);

		JButton btnApplyFilter = new JButton("Apply filter");
		btnApplyFilter.setBounds(235, 379, 150, 40);
		contentPane.add(btnApplyFilter);

		JButton btnResetFilter = new JButton("Reset filter");
		btnResetFilter.setBounds(580, 379, 150, 40);
		contentPane.add(btnResetFilter);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(326, 327, 200, 20);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		contentPane.add(dateChooser);

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(600, 327, 200, 20);
		contentPane.add(dateChooser_1);

		dateChooser.setEnabled(false);
		dateChooser_1.setEnabled(false);

		JLabel lbDateTo = new JLabel("To");
		lbDateTo.setHorizontalAlignment(SwingConstants.CENTER);
		lbDateTo.setBounds(534, 327, 56, 16);
		contentPane.add(lbDateTo);

		String[] columnNames = { "Location Name", "City", "State", "Zip code", "Flagged?", "Date Flagged" };
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
			String sql = "SELECT * FROM POI";
			ConnectDB db = new ConnectDB();
			conn = db.getConnection();

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			num = 0;

			String[] dummyLocName = new String[1000];
			String[] dummyCity = new String[1000];
			String[] dummyState = new String[1000];
			String[] dummyZipCode = new String[1000];
			// int[] dummyFlag = new int[1000]; // original
			String[] dummyFlag = new String[1000];
			String[] dummyDateFlag = new String[1000];
			while (rs.next()) {
				String locName_str = rs.getString("locName");
				String city_str = rs.getString("city");
				String state_str = rs.getString("state");
				String zipCode_str = rs.getString("zipCode");
				int flag_int = rs.getInt("flag"); // original
				// String flag = rs.getString("flag");
				String dateFlag_str = rs.getString("dateFlagged");
				if (flag_int == 0) {
					dummyFlag[num] = null;
				} else {
					dummyFlag[num] = "yes";
				}
				dummyLocName[num] = locName_str;
				dummyCity[num] = city_str;
				dummyState[num] = state_str;
				dummyZipCode[num] = zipCode_str;
				// dummyFlag[num] = flag_int; //original
				dummyDateFlag[num] = dateFlag_str;
				num++;
			}
			city_view = new String[num];
			state_view = new String[num];
			locName = new String[num];
			zipCode = new String[num];
			// flag = new int[num]; // original
			flag = new String[num];
			dateFlagged = new String[num];

			for (int i = 0; i < num; i++) {
				locName[i] = dummyLocName[i];
				city_view[i] = dummyCity[i];
				state_view[i] = dummyState[i];
				zipCode[i] = dummyZipCode[i];
				flag[i] = dummyFlag[i];
				dateFlagged[i] = dummyDateFlag[i];
			}
			tmodel.setRowCount(num);
			for (int i = 0; i < num; i++) {
				table.setValueAt(locName[i], i, 0);
				table.setValueAt(city_view[i], i, 1);
				table.setValueAt(state_view[i], i, 2);
				table.setValueAt(zipCode[i], i, 3);
				table.setValueAt(flag[i], i, 4);
				table.setValueAt(dateFlagged[i], i, 5);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		btnResetFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < table.getRowCount(); i++) {
					table.setValueAt("", i, 0);
					table.setValueAt("", i, 1);
					table.setValueAt("", i, 2);
					table.setValueAt("", i, 3);
					table.setValueAt("", i, 4);
					table.setValueAt("", i, 5);
				}
				try {
					String sql = "SELECT * FROM POI";
					ConnectDB db = new ConnectDB();
					conn = db.getConnection();

					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					num = 0;

					String[] dummyLocName = new String[1000];
					String[] dummyCity = new String[1000];
					String[] dummyState = new String[1000];
					String[] dummyZipCode = new String[1000];
					// int[] dummyFlag = new int[1000]; //original
					String[] dummyFlag = new String[1000];
					String[] dummyDateFlag = new String[1000];
					while (rs.next()) {
						String locName_str = rs.getString("locName");
						String city_str = rs.getString("city");
						String state_str = rs.getString("state");
						String zipCode_str = rs.getString("zipCode");
						int flag_int = rs.getInt("flag");
						if (flag_int == 0) {
							dummyFlag[num] = null;
						} else {
							dummyFlag[num] = "yes";
						}
						String dateFlag_str = rs.getString("dateFlagged");

						dummyLocName[num] = locName_str;
						dummyCity[num] = city_str;
						dummyState[num] = state_str;
						dummyZipCode[num] = zipCode_str;
						// dummyFlag[num] = flag_int; //original
						dummyDateFlag[num] = dateFlag_str;
						num++;
					}
					city_view = new String[num];
					state_view = new String[num];
					locName = new String[num];
					zipCode = new String[num];
					// flag = new int[num]; // original
					flag = new String[num];
					dateFlagged = new String[num];

					for (int i = 0; i < num; i++) {
						locName[i] = dummyLocName[i];
						city_view[i] = dummyCity[i];
						state_view[i] = dummyState[i];
						zipCode[i] = dummyZipCode[i];
						flag[i] = dummyFlag[i];
						dateFlagged[i] = dummyDateFlag[i];
					}
					tmodel.setRowCount(num);
					for (int i = 0; i < num; i++) {
						table.setValueAt(locName[i], i, 0);
						table.setValueAt(city_view[i], i, 1);
						table.setValueAt(state_view[i], i, 2);
						table.setValueAt(zipCode[i], i, 3);
						table.setValueAt(flag[i], i, 4);
						table.setValueAt(dateFlagged[i], i, 5);
					}
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		btnApplyFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str_date = "";
				String str_date1 = "";
				Date date = dateChooser.getDate();
				Date date1 = dateChooser_1.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				if (date != null) {
					str_date = sdf.format(date);
				}
				if (date1 != null) {
					str_date1 = sdf.format(date1);
				}
				System.out.println(str_date); // testing
				System.out.println(str_date1); // testing
				System.out.println(str_date.compareTo(str_date1)); // testing

				if (str_date.compareTo(str_date1) > 1) {
					String message = "Invalid date to date. Please enter the right range";
					JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
				} else {
					System.out.println("works"); // testing
					for (int i = 0; i < table.getRowCount(); i++) {
						table.setValueAt("", i, 0);
						table.setValueAt("", i, 1);
						table.setValueAt("", i, 2);
						table.setValueAt("", i, 3);
						table.setValueAt("", i, 4);
						table.setValueAt("", i, 5);
					}
					try {
						String sql = "";
						if (picked_POILoc == null) {
							picked_POILoc = "";
						}
						if (picked_city == null) {
							picked_city = "";
						}
						if (picked_state == null) {
							picked_state = "";
						}
						if (zipCode_txtField.getText().isEmpty()) {
							zipCode_txtField.setText("");
						}

						System.out.println(picked_POILoc + " " + picked_city + " " + picked_state + " "
								+ zipCode_txtField.getText()); // testing

						if (checked == 0) {
							if (picked_POILoc.equals("") && picked_city.equals("") && picked_state.equals("")
									&& zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE flag = 0;";
								System.out.println(sql); // test
							}
							if (!picked_POILoc.equals("") && picked_city.equals("") && picked_state.equals("")
									&& zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND flag = 0;";
								System.out.println(sql); // test
							}
							if (picked_POILoc.equals("") && !picked_city.equals("") && picked_state.equals("")
									&& zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE city = '" + picked_city + "'  AND flag = 0;";
								System.out.println(sql); // test
							}
							if (picked_POILoc.equals("") && picked_city.equals("") && !picked_state.equals("")
									&& zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE state = '" + picked_state + "'  AND flag = 0;";
								System.out.println(sql); // testing
							}
							if (picked_POILoc.equals("") && picked_city.equals("") && picked_state.equals("")
									&& !zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE zipCode = '" + zipCode_txtField.getText()
										+ "'  AND flag = 0;";
								System.out.println(sql); // test
							}

							if (!picked_POILoc.equals("") && !picked_city.equals("") && picked_state.equals("")
									&& zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND city = '"
										+ picked_city + "' AND flag = 0;";
								System.out.println(sql); // test
							}
							if (!picked_POILoc.equals("") && picked_city.equals("") && !picked_state.equals("")
									&& zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND state = '"
										+ picked_state + "' AND flag = 0;";
								System.out.println(sql); // test
							}
							if (!picked_POILoc.equals("") && picked_city.equals("") && picked_state.equals("")
									&& !zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND zipCode = '"
										+ zipCode_txtField.getText() + "' AND flag = 0;";
								System.out.println(sql); // test
							}

							if (picked_POILoc.equals("") && !picked_city.equals("") && !picked_state.equals("")
									&& zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE city = '" + picked_city + "' AND state = '"
										+ picked_state + "' AND flag = 0;";
								System.out.println(sql); // test
							}

							if (!picked_POILoc.equals("") && !picked_city.equals("") && !picked_state.equals("")
									&& zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND city = '"
										+ picked_city + "' AND state = '" + picked_state + "' AND flag = 0;";
								System.out.println(sql); // test
							}
							if (picked_POILoc.equals("") && !picked_city.equals("") && picked_state.equals("")
									&& !zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE city = '" + picked_city + "' AND zipCode ='"
										+ zipCode_txtField.getText() + "' AND flag = 0;";
								System.out.println(sql); // test
							}
							if (picked_POILoc.equals("") && picked_city.equals("") && !picked_state.equals("")
									&& !zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE state = '" + picked_state + "' AND zipCode ='"
										+ zipCode_txtField.getText() + "' AND flag = 0;";
								System.out.println(sql); // test
							}
							if (!picked_POILoc.equals("") && !picked_city.equals("") && !picked_state.equals("")
									&& !zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND city = '"
										+ picked_city + "' AND state = '" + picked_state + "' AND zipCode ='"
										+ zipCode_txtField.getText() + "' AND flag = 0;";
								System.out.println(sql); // test
							}
							// edge case
							if (picked_POILoc.equals("") && !picked_city.equals("") && !picked_state.equals("")
									&& !zipCode_txtField.getText().equals("")) {
								sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND city = '"
										+ picked_city + "' AND state = '" + picked_state + "' AND zipCode ='"
										+ zipCode_txtField.getText() + "' AND flag = 0;";
								System.out.println(sql); // test
							}
						} else {
							if (str_date != "" && str_date1 != "") {
								if (picked_POILoc.equals("") && picked_city.equals("") && picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}
								if (!picked_POILoc.equals("") && picked_city.equals("") && picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}
								if (picked_POILoc.equals("") && !picked_city.equals("") && picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE city = '" + picked_city + "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}
								if (picked_POILoc.equals("") && picked_city.equals("") && !picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE state = '" + picked_state + "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}
								if (picked_POILoc.equals("") && picked_city.equals("") && picked_state.equals("")
										&& !zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE zipCode = '" + zipCode_txtField.getText()
											+ "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}

								if (!picked_POILoc.equals("") && !picked_city.equals("") && picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND city = '"
											+ picked_city + "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}
								if (!picked_POILoc.equals("") && picked_city.equals("") && !picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND state = '"
											+ picked_state + "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}
								if (!picked_POILoc.equals("") && picked_city.equals("") && picked_state.equals("")
										&& !zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND zipCode = '"
											+ zipCode_txtField.getText() + "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}

								if (picked_POILoc.equals("") && !picked_city.equals("") && !picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE city = '" + picked_city + "' AND state = '"
											+ picked_state + "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}

								if (!picked_POILoc.equals("") && !picked_city.equals("") && !picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND city = '"
											+ picked_city + "' AND state = '" + picked_state + "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}
								if (picked_POILoc.equals("") && !picked_city.equals("") && picked_state.equals("")
										&& !zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE city = '" + picked_city + "' AND zipCode ='"
											+ zipCode_txtField.getText() + "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}
								if (picked_POILoc.equals("") && picked_city.equals("") && !picked_state.equals("")
										&& !zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE state = '" + picked_state + "' AND zipCode ='"
											+ zipCode_txtField.getText() + "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}
								if (!picked_POILoc.equals("") && !picked_city.equals("") && !picked_state.equals("")
										&& !zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND city = '"
											+ picked_city + "' AND state = '" + picked_state + "' AND zipCode ='"
											+ zipCode_txtField.getText() + "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}
								// edge case
								if (picked_POILoc.equals("") && !picked_city.equals("") && !picked_state.equals("")
										&& !zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND city = '"
											+ picked_city + "' AND state = '" + picked_state + "' AND zipCode ='"
											+ zipCode_txtField.getText() + "' AND flag= 1 AND dateFlagged Between '" + str_date + "' AND '" + str_date1 + "';";
									System.out.println(sql); // test
								}
							} else {
								if (picked_POILoc.equals("") && picked_city.equals("") && picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE flag= 1;";
									System.out.println(sql); // test
								}
								if (!picked_POILoc.equals("") && picked_city.equals("") && picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND flag= 1;";
									System.out.println(sql); // test
								}
								if (picked_POILoc.equals("") && !picked_city.equals("") && picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE city = '" + picked_city + "' AND flag= 1;";
									System.out.println(sql); // test
								}
								if (picked_POILoc.equals("") && picked_city.equals("") && !picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE state = '" + picked_state + "' AND flag= 1;";
									System.out.println(sql); // test
								}
								if (picked_POILoc.equals("") && picked_city.equals("") && picked_state.equals("")
										&& !zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE zipCode = '" + zipCode_txtField.getText()
											+ "' AND flag= 1;";
									System.out.println(sql); // test
								}

								if (!picked_POILoc.equals("") && !picked_city.equals("") && picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND city = '"
											+ picked_city + "' AND flag= 1;";
									System.out.println(sql); // test
								}
								if (!picked_POILoc.equals("") && picked_city.equals("") && !picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND state = '"
											+ picked_state + "' AND flag= 1;";
									System.out.println(sql); // test
								}
								if (!picked_POILoc.equals("") && picked_city.equals("") && picked_state.equals("")
										&& !zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND zipCode = '"
											+ zipCode_txtField.getText() + "' AND flag= 1;";
									System.out.println(sql); // test
								}

								if (picked_POILoc.equals("") && !picked_city.equals("") && !picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE city = '" + picked_city + "' AND state = '"
											+ picked_state + "' AND flag= 1;";
									System.out.println(sql); // test
								}

								if (!picked_POILoc.equals("") && !picked_city.equals("") && !picked_state.equals("")
										&& zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND city = '"
											+ picked_city + "' AND state = '" + picked_state + "' AND flag= 1;";
									System.out.println(sql); // test
								}
								if (picked_POILoc.equals("") && !picked_city.equals("") && picked_state.equals("")
										&& !zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE city = '" + picked_city + "' AND zipCode ='"
											+ zipCode_txtField.getText() + "' AND flag= 1;";
									System.out.println(sql); // test
								}
								if (picked_POILoc.equals("") && picked_city.equals("") && !picked_state.equals("")
										&& !zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE state = '" + picked_state + "' AND zipCode ='"
											+ zipCode_txtField.getText() + "' AND flag= 1;";
									System.out.println(sql); // test
								}
								if (!picked_POILoc.equals("") && !picked_city.equals("") && !picked_state.equals("")
										&& !zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND city = '"
											+ picked_city + "' AND state = '" + picked_state + "' AND zipCode ='"
											+ zipCode_txtField.getText() + "' AND flag= 1;";
									System.out.println(sql); // test
								}
								// edge case
								if (picked_POILoc.equals("") && !picked_city.equals("") && !picked_state.equals("")
										&& !zipCode_txtField.getText().equals("")) {
									sql = "SELECT * FROM POI WHERE locName = '" + picked_POILoc + "' AND city = '"
											+ picked_city + "' AND state = '" + picked_state + "' AND zipCode ='"
											+ zipCode_txtField.getText() + "' AND flag= 1;";
									System.out.println(sql); // test
								}

							}
						}
						ConnectDB db = new ConnectDB();
						conn = db.getConnection();

						stmt = conn.prepareStatement(sql);
						rs = stmt.executeQuery();
						int num_filter = 0;

						String[] dummyLocName = new String[1000];
						String[] dummyCity = new String[1000];
						String[] dummyState = new String[1000];
						String[] dummyZipCode = new String[1000];
						// int[] dummyFlag = new int[1000];//original
						String[] dummyFlag = new String[1000];

						String[] dummyDateFlag = new String[1000];
						while (rs.next()) {
							String locName_str = rs.getString("locName");
							String city_str = rs.getString("city");
							String state_str = rs.getString("state");
							String zipCode_str = rs.getString("zipCode");
							int flag_int = rs.getInt("flag");
							String dateFlag_str = rs.getString("dateFlagged");
							if (flag_int == 0) {
								dummyFlag[num_filter] = null;
							} else {
								dummyFlag[num_filter] = "yes";
							}
							dummyLocName[num_filter] = locName_str;
							dummyCity[num_filter] = city_str;
							dummyState[num_filter] = state_str;
							dummyZipCode[num_filter] = zipCode_str;
							// dummyFlag[num_filter] = flag_int; //original
							dummyDateFlag[num_filter] = dateFlag_str;
							num_filter++;
						}
						city = new String[num_filter];
						state = new String[num_filter];
						locName = new String[num_filter];
						zipCode = new String[num_filter];
						// flag = new int[num_filter];//original
						flag = new String[num_filter];

						dateFlagged = new String[num_filter];

						for (int i = 0; i < num_filter; i++) {
							locName[i] = dummyLocName[i];
							city[i] = dummyCity[i];
							state[i] = dummyState[i];
							zipCode[i] = dummyZipCode[i];
							flag[i] = dummyFlag[i];
							dateFlagged[i] = dummyDateFlag[i];
						}
						tmodel.setRowCount(num_filter);
						for (int i = 0; i < num_filter; i++) {
							table.setValueAt(locName[i], i, 0);
							table.setValueAt(city[i], i, 1);
							table.setValueAt(state[i], i, 2);
							table.setValueAt(zipCode[i], i, 3);
							table.setValueAt(flag[i], i, 4);
							table.setValueAt(dateFlagged[i], i, 5);
						}
						rs.close();
						stmt.close();
						conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JButton btnBackToMenu = new JButton("Back to menu");
		btnBackToMenu.setBounds(405, 379, 150, 40);
		btnBackToMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				SuperChooseFunctionalityPage page = new SuperChooseFunctionalityPage();
				page.setVisible(true);
				page.setResizable(false);
			}
		});
		contentPane.add(btnBackToMenu);

		JComboBox<String> POI_comboBox = new JComboBox<String>(locName_spin); // locName_spin
		POI_comboBox.setBounds(326, 68, 395, 39);
		contentPane.add(POI_comboBox);

		POI_comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				picked_POILoc = POI_comboBox.getItemAt(POI_comboBox.getSelectedIndex());
			}
		});
		JComboBox<String> city_comboBox = new JComboBox<String>(city); // city
		city_comboBox.setBounds(326, 126, 395, 39);
		contentPane.add(city_comboBox);

		city_comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				picked_city = city_comboBox.getItemAt(city_comboBox.getSelectedIndex());
			}
		});

		JComboBox<String> state_comboBox = new JComboBox<String>(state); // state
		state_comboBox.setBounds(326, 175, 115, 39);
		contentPane.add(state_comboBox);

		state_comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				picked_state = state_comboBox.getItemAt(state_comboBox.getSelectedIndex());
			}
		});

		JCheckBox chckbxFlaggedbox = new JCheckBox("FlaggedBox");
		chckbxFlaggedbox.setBounds(326, 266, 221, 41);
		contentPane.add(chckbxFlaggedbox);

		chckbxFlaggedbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getID() == ActionEvent.ACTION_PERFORMED ? "ACTION_PERFORMED" : e.getID()); // test
			}
		});
		chckbxFlaggedbox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					checked = 1;
					dateChooser.setEnabled(true);
					dateChooser_1.setEnabled(true);
				} else {
					checked = 0;
					dateChooser.setEnabled(false);
					dateChooser_1.setEnabled(false);
				}
			}
		});
		zipCode_txtField = new JTextField();
		zipCode_txtField.setBounds(326, 227, 115, 22);
		contentPane.add(zipCode_txtField);
		zipCode_txtField.setColumns(10);

	}
}
