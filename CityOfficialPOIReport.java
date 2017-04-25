import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

public class CityOfficialPOIReport extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tmodel;
	private JTable table;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private Connection conn;
	private int num;
	private String[] locName;
	private String[] city;
	private String[] state;
	private double[] MoldMin;
	private double[] MoldAvg;
	private double[] MoldMax;
	private double[] AQMin;
	private double[] AQAvg;
	private double[] AQMax;
	private int[] numDP;
	private String[] flag;

	CityOfficialPOIReport() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 724);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("POI Report");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(460, 80, 213, 65);
		contentPane.add(lblNewLabel);

		String[] columnNames = { "POI location", "City", "State", "Mold Min", "Mold Avg", "Mold Max", "AQ Min",
				"AQ Avg", "AQ Max", "#of data points", "Flagged?" };
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
		scrollPane.setBounds(12, 225, 1258, 345);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		for (int i = 1; i < columnNames.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(523, 613, 97, 25);
		contentPane.add(btnBack);
		
		
		try {
			String sql = "SELECT t1.locName 'locName',IFNULL(a,0) AS 'Mold Min', IFNULL(b,0) AS 'Mold Avg', IFNULL(c,0) AS 'Mold Max',"
					+ " IFNULL(d,0) AS 'AQ Min', IFNULL(e,0) AS 'AQ Avg', IFNULL(f,0) 'AQ Max', city, State,"
					+ " COUNT(t4.`locName`) AS '# of data points', Flag  FROM  (SELECT locName, Min(dataValue) As 'a',"
					+ " AVG(dataValue) AS 'b', MAX(dataValue) AS 'c', datatype FROM DataPoint WHERE dataType = 'Mold' GROUP BY locName) AS t1"
					+ " LEFT JOIN (SELECT locName,Min(dataValue) As 'd', AVG(dataValue) AS 'e', MAX(dataValue) AS 'f',"
					+ " datatype FROM DataPoint WHERE dataType = 'Air Quality' GROUP BY locName) t2 ON t1.locName = t2.locName"
					+ " RIGHT JOIN (SELECT locName FROM DataPoint) AS t4 ON t1.locName = t4.locName"
					+ " JOIN POI t3 ON t1.locName = t3.locName GROUP BY t3.locName";
			ConnectDB db = new ConnectDB();
			conn = db.getConnection();

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			String[] dummyLocName = new String[1000];
			String[] dummyCity = new String[1000];
			String[] dummyState = new String[1000];
			double[] dummyMoldMin = new double[1000];
			double[] dummyMoldAvg = new double[1000];
			double[] dummyMoldMax = new double[1000];
			double[] dummyAQMin = new double[1000];
			double[] dummyAQAvg = new double[1000];
			double[] dummyAQMax = new double[1000];
			int[] dummynumDP = new int[1000];
			String[] dummyFlag = new String[1000];
			
			num = 0;
			while (rs.next()) {
				String locName_str = rs.getString("locName");
				String city_str = rs.getString("city");
				String state_str = rs.getString("state");
				double moldMin_double = rs.getDouble("Mold Min");
				double moldAvg_double = rs.getDouble("Mold Avg");
				double moldMax_double = rs.getDouble("Mold Max");
				double AQMin_double = rs.getDouble("AQ Min");
				double AQAvg_double = rs.getDouble("AQ Avg");
				double AQMax_double = rs.getDouble("AQ Max");
				int numDP_int = rs.getInt("# of data points");
				int flag_int = rs.getInt("flag"); // original

				if (flag_int == 0) {
					dummyFlag[num] = null;
				} else {
					dummyFlag[num] = "yes";
				}
				dummyLocName[num] = locName_str;
				dummyCity[num] = city_str;
				dummyState[num] = state_str;
				dummyMoldMin[num] = moldMin_double;
				dummyMoldAvg[num] = moldAvg_double;
				dummyMoldMax[num] = moldMax_double;
				dummyAQMin[num] = AQMin_double;
				dummyAQAvg[num] = AQAvg_double;
				dummyAQMax[num] = AQMax_double;
				dummynumDP[num] = numDP_int;
				num++;
			}
			locName = new String[num];
			city = new String[num];
			state = new String[num];
			MoldMin = new double[num];
			MoldAvg = new double[num];
			MoldMax = new double[num];
			AQMin = new double[num];
			AQAvg = new double[num];
			AQMax = new double[num];
			numDP = new int[num];
			flag = new String[num];
	
			for (int i = 0; i < num; i++) {
				locName[i] = dummyLocName[i];
				city[i] = dummyCity[i];
				state[i] = dummyState[i];
				MoldMin[i] = dummyMoldMin[i];
				MoldAvg[i] = dummyMoldAvg[i];
				MoldMax[i] = dummyMoldMax[i];
				AQMin[i] = dummyAQMin[i];
				AQAvg[i] = dummyAQAvg[i];
				AQMax[i] = dummyAQMax[i];
				numDP[i] = dummynumDP[i];
				flag[i] = dummyFlag[i];
			}
			tmodel.setRowCount(num);
			for (int i = 0; i < num; i++) {
				table.setValueAt(locName[i], i, 0);
				table.setValueAt(city[i], i, 1);
				table.setValueAt(state[i], i, 2);
				table.setValueAt(String.format("%.2f", MoldMin[i]), i, 3);
				table.setValueAt(String.format("%.2f", MoldAvg[i]), i, 4);
				table.setValueAt(String.format("%.2f", MoldMax[i]), i, 5);
				table.setValueAt(String.format("%.2f", AQMin[i]), i, 6);
				table.setValueAt(String.format("%.2f", AQAvg[i]), i, 7);
				table.setValueAt(String.format("%.2f", AQMax[i]), i, 8);
				table.setValueAt(numDP[i], i, 9);
				table.setValueAt(flag[i], i, 10);

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SuperChooseFunctionalityPage page = new SuperChooseFunctionalityPage();
				page.setVisible(true);
				page.setResizable(false);
			}
		});
	}
}
