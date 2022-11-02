package app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


public class FrameThanhToan extends JFrame  {

	public static JTable tableChonPhong;
	private static DefaultTableModel tableModelChonPhong;
	private JTextField txtTenBan;
	private JTextField txtLoaiPhong;
	private JTextField txtGiaPhong;
	private JTextField txtTienDV;
	private JTextField txtSoGio;
	private JTextField txtTienPhong;
	private JTextField txtNhanVien;
	private JLabel lblTongTienThanhToan;
	private JButton btnThanhToan;
	private JTextField txtThoiGianDen;
	private JTextField txtThoiGianTra;
	private Date thoiGianTraPhongDate;
	public static JComboBox<String> cmbChonPhong;
	private JLabel lblTenKH;
	private JTextField txtKH;
	private JButton btnTimPhong;
	private JButton btnInHoaDon;
	private JTextField txtThanhToan;

	public JPanel createPanelTraPhong() {


		setTitle("THANH TOÁN");
		setSize(948, 440);
		setLocationRelativeTo(null);
		setResizable(false);

		Toolkit toolkit = this.getToolkit(); /* Lấy độ phân giải màn hình */
		Dimension d = toolkit.getScreenSize();

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				new FrameDatBan().setVisible(true);
			}
		});
		JPanel pnlContentPane = new JPanel();
		pnlContentPane.setBackground(new Color(248, 227, 182));
		pnlContentPane.setLayout(null);
		setContentPane(pnlContentPane);
		/*
		 * Chọn phòng trả
		 */
		JPanel pnlTraPhong = new JPanel();
		pnlTraPhong.setLayout(new GridLayout(1, 1));
		pnlTraPhong.setBounds(350, 10, (int) (d.getWidth() - 570), (int) (d.getHeight() - 90));
		pnlTraPhong.setBackground(Color.WHITE);
		pnlTraPhong.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "CHỌN BÀN CẦN TRẢ: "));
		pnlContentPane.add(pnlTraPhong);

		String[] header = { "Mã bàn", "Tên bàn"};
		tableModelChonPhong = new DefaultTableModel(header, 0);
		tableChonPhong = new JTable(tableModelChonPhong) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				Color color1 = new Color(220, 220, 220);
				Color color2 = Color.WHITE;
				if (!c.getBackground().equals(getSelectionBackground())) {
					Color coleur = (row % 2 == 0 ? color1 : color2);
					c.setBackground(coleur);
					coleur = null;
				}
				return c;
			}
		};
		tableChonPhong.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableChonPhong.setGridColor(getBackground());
		tableChonPhong.setRowHeight(tableChonPhong.getRowHeight() + 20);
		tableChonPhong.setSelectionBackground(new Color(255, 255, 128));
		JTableHeader tableHeader = tableChonPhong.getTableHeader();
		tableHeader.setBackground(new Color(173, 119, 72));
		tableHeader.setForeground(new Color(255, 255, 255));
		tableHeader.setFont(new Font("Arial", Font.BOLD, 15));
		tableHeader.setPreferredSize(new Dimension(WIDTH, 40));
		tableHeader.setResizingAllowed(false);
		tableHeader.setReorderingAllowed(false);


		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		pnlTraPhong.add(new JScrollPane(tableChonPhong));

		// Chọn phòng
		JPanel pnlChonPhong = new JPanel();
		pnlChonPhong.setLayout(null);
		pnlChonPhong.setBounds(20, 18, 315, 102);
		pnlChonPhong.setBackground(new Color(252, 242, 217));
		pnlChonPhong.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), ""));
		pnlContentPane.add(pnlChonPhong);

		JLabel lblChonPhong = new JLabel("NHẬP TÊN BÀN CẦN TÌM: ", SwingConstants.CENTER);
		lblChonPhong.setBounds(1, 10, 313, 30);
		lblChonPhong.setOpaque(true);
		lblChonPhong.setBackground(new Color(131, 77, 30));
		lblChonPhong.setForeground(Color.WHITE);
		lblChonPhong.setFont(new Font("Arial", Font.BOLD, 15));
		pnlChonPhong.add(lblChonPhong);
		cmbChonPhong = new JComboBox<String>();
		cmbChonPhong.setBounds(40, 53, 200, 32);
		cmbChonPhong.setBackground(Color.WHITE);
		cmbChonPhong.setEditable(true);
		cmbChonPhong.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		AutoCompleteDecorator.decorate(cmbChonPhong);
		pnlChonPhong.add(cmbChonPhong);

		btnTimPhong = new JButton("", new ImageIcon("image/timkiem.png"));
		btnTimPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTimPhong.setBounds(240, 53, 35, 32);
		btnTimPhong.setBackground(new Color(131, 77, 30));
		btnTimPhong.setFocusPainted(false);
		pnlChonPhong.add(btnTimPhong);

		/*
		 * Thông tin hóa đơn
		 */
		JPanel pnThongTinHoaDon = new JPanel();
		pnThongTinHoaDon.setLayout(null);

//		pnThongTinHoaDon.setBounds(20, 125, 315, (int) (d.getHeight() - 205));

		pnThongTinHoaDon.setBounds(20, 135, 315, (int) (d.getHeight() - 215));

		pnThongTinHoaDon.setBackground(new Color(252, 242, 217));
		pnThongTinHoaDon.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "THÔNG TIN HÓA ĐƠN: "));
		pnlContentPane.add(pnThongTinHoaDon);

		JLabel lblNhanVien = new JLabel("TÊN NHÂN VIÊN: ");
		lblNhanVien.setBounds(15, 37, 150, 25);
		lblNhanVien.setFont(new Font("Arial", Font.BOLD, 13));
		pnThongTinHoaDon.add(lblNhanVien);
		txtNhanVien = new JTextField();
		txtNhanVien.setEditable(false);
		txtNhanVien.setBounds(145, 35, 150, 30);
		txtNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 13));
		pnThongTinHoaDon.add(txtNhanVien);


		JLabel lblTenPhong = new JLabel("TÊN BÀN: ");
		lblTenPhong.setBounds(15, 82, 150, 25);
		lblTenPhong.setFont(new Font("Arial", Font.BOLD, 13));
		pnThongTinHoaDon.add(lblTenPhong);
		txtTenBan = new JTextField();
		txtTenBan.setEditable(false);
		txtTenBan.setBounds(145, 80, 150, 30);
		txtTenBan.setFont(new Font("Times New Roman", Font.BOLD, 13));
		pnThongTinHoaDon.add(txtTenBan);

		JLabel lblTienPhong = new JLabel("TIỀN DỊCH VỤ:");
		lblTienPhong.setBounds(15, 127, 150, 25);
		lblTienPhong.setFont(new Font("Arial", Font.BOLD, 13));
		pnThongTinHoaDon.add(lblTienPhong);
		txtTienPhong = new JTextField();
		txtTienPhong.setEditable(false);
		txtTienPhong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTienPhong.setBounds(145, 125, 150, 30);
		pnThongTinHoaDon.add(txtTienPhong);

		JLabel lblTienDV = new JLabel("THUẾ:");
		lblTienDV.setFont(new Font("Arial", Font.BOLD, 13));
		lblTienDV.setBounds(15, 172, 150, 25);
		pnThongTinHoaDon.add(lblTienDV);
		txtTienDV = new JTextField();
		txtTienDV.setEditable(false);
		txtTienDV.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTienDV.setBounds(145, 170, 150, 30);
		pnThongTinHoaDon.add(txtTienDV);

		JLabel lblThanhToan = new JLabel("TỔNG TIỀN:");
		lblThanhToan.setFont(new Font("Arial", Font.BOLD, 13));
		lblThanhToan.setBounds(15, 217, 150, 25);
		pnThongTinHoaDon.add(lblThanhToan);
		txtThanhToan = new JTextField();
		txtThanhToan.setEditable(false);
		txtThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtThanhToan.setBounds(145, 215, 150, 30);
		pnThongTinHoaDon.add(txtThanhToan);

		btnInHoaDon = new JButton("IN HÓA ĐƠN");
		btnInHoaDon.setBounds(75, 270, 165, 40);
		btnInHoaDon.setIcon(new ImageIcon("image/inhoadon.png"));
		btnInHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInHoaDon.setBackground(new Color(131, 77, 30));
		btnInHoaDon.setForeground(Color.WHITE);
		btnInHoaDon.setFocusPainted(false);
		pnThongTinHoaDon.add(btnInHoaDon);

		btnThanhToan = new JButton("THANH TOÁN");
		btnThanhToan.setBounds(75, 330, 165, 40);
		btnThanhToan.setIcon(new ImageIcon("image/thanhtoan.png"));
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThanhToan.setBackground(new Color(131, 77, 30));
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFocusPainted(false);
		pnThongTinHoaDon.add(btnThanhToan);

		tableChonPhong.clearSelection();
		tableChonPhong.setDefaultEditor(Object.class, null);

		cmbChonPhong.setSelectedIndex(-1);
		return pnlContentPane;
	}
}
