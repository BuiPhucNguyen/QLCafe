package app;

import java.awt.BorderLayout;
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
import java.util.ArrayList;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.toedter.calendar.JDateChooser;


public class FrameDatBan extends JFrame  {
	public static  JTextField txtTenKH;
	public static  JTextField txtCmnd;
	public static  JTextField txtLienLac;
	
	public static DefaultTableModel tableModelPhongTrong;
	public static JTable tablePhongTrong;
	public static DefaultTableModel tableModelPhongDaDat;
	public static JTable tablePhongDaDat;
	

	public static String maPhongMoiDat = "";
	public static String maHDMoiDat = "";
	public static String maKHDatPhong = "";
	

	private JButton btnDatPhong;
	private JButton btnHuyDatPhong;
	private JButton btnLamMoi;
	private JButton btnDatDV;
	private JButton btnTimKHCu;
	private JButton btnTimKH;
	
	public static  JDateChooser txtNgaySinh;
	
	public static JComboBox<String> cmbDanhSachSdt;
	public static  JComboBox<String> cmbGioiTinh;
	public static JComboBox<String> cmbTimKhachHang;
	private JComboBox<String> cmbLoaiPhong;
	private DefaultTableModel tableModelListDV;
	private JTable tableListDV;
	private DefaultTableModel tableModelHoaDonDV;
	private JTable tableHoaDonDV;
	private JComboBox<String> cmbDanhSachPhong;
	private JComboBox<String> cmbDanhSachDV;
	private JButton btnDatDichVu;
	private JButton btnHuyDichVu;
	private JTextField txtSoLuong;
	private JLabel lblBanDangChon;



	public JPanel createPanelDatPhong() {
		

		setTitle("ĐẶT PHÒNG");
		setSize(948, 660);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Toolkit toolkit = this.getToolkit(); /* Lấy độ phân giải màn hình */
		Dimension d = toolkit.getScreenSize();

		JPanel pnlContentPane = new JPanel();
		pnlContentPane.setBackground(new Color(248, 227, 182));
		setContentPane(pnlContentPane);
		pnlContentPane.setLayout(null);



		/*
		 * Danh sách bàn trống
		 */
		JPanel pnlPhongTrong = new JPanel();
		pnlPhongTrong.setBounds(20, 15, (int) (d.getWidth() - 250), 275);
		pnlContentPane.add(pnlPhongTrong);
		pnlPhongTrong.setBackground(Color.WHITE);
		pnlPhongTrong.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"DANH SÁCH BÀN TRỐNG: "));
		pnlPhongTrong.setLayout(new GridLayout(1, 0, 0, 0));

		String[] header = { "Mã bàn", "Tên bàn",};
		tableModelPhongTrong = new DefaultTableModel(header, 0);
		tablePhongTrong = new JTable(tableModelPhongTrong) {
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
		tablePhongTrong.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tablePhongTrong.setGridColor(getBackground());
		tablePhongTrong.setRowHeight(tablePhongTrong.getRowHeight() + 20);
		tablePhongTrong.setSelectionBackground(new Color(255, 255, 128));
		JTableHeader tableHeader = tablePhongTrong.getTableHeader();
		tableHeader.setBackground(new Color(173, 119, 72));
		tableHeader.setForeground(new Color(255, 255, 255));
		tableHeader.setFont(new Font("Arial", Font.BOLD, 15));
		tableHeader.setPreferredSize(new Dimension(WIDTH, 40));
		tableHeader.setResizingAllowed(false);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

		pnlPhongTrong.add(new JScrollPane(tablePhongTrong));

		

		/*
		 * Danh sách bàn đã đặt
		 */
		
		JPanel pnlPhongDaDat = new JPanel();
		pnlPhongDaDat.setBackground(new Color(248, 227, 182));
		pnlPhongDaDat.setBounds(20, 290, (int) (d.getWidth() - 250), (int) (d.getHeight() - 380));
		pnlPhongDaDat.setLayout(null);
		pnlContentPane.add(pnlPhongDaDat);
		
		

		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH DỊCH VỤ: "));
		pnlDanhSach.setBounds(0, 25, 475, 460);
		pnlDanhSach.setBackground(Color.WHITE);
		pnlDanhSach.setLayout(new GridLayout(1, 0, 0, 0));
		pnlPhongDaDat.add(pnlDanhSach);
		
		String[] header1 = { "Mã đồ uống", "Tên đồ uống", "Giá tiền" };
		tableModelListDV = new DefaultTableModel(header1, 0);
		tableListDV = new JTable(tableModelListDV) {
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
		tableListDV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableListDV.setGridColor(getBackground());
		tableListDV.setRowHeight(tableListDV.getRowHeight() + 20);
		tableListDV.setSelectionBackground(new Color(255, 255, 128));
		JTableHeader tableHeader1 = tableListDV.getTableHeader();
		tableHeader1.setBackground(new Color(173, 119, 72));
		tableHeader1.setForeground(new Color(255, 255, 255));
		tableHeader1.setFont(new Font("Arial", Font.BOLD, 15));
		tableHeader1.setPreferredSize(new Dimension(WIDTH, 40));
		tableHeader1.setResizingAllowed(false);

		
		DefaultTableCellRenderer rightRenderer1 = new DefaultTableCellRenderer();
		rightRenderer1.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tableListDV.getColumn("Giá tiền").setCellRenderer(rightRenderer1);

		pnlDanhSach.add(new JScrollPane(tableListDV));

		JPanel pnlDatDichVu = new JPanel();
		pnlDatDichVu.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"HÓA ĐƠN DỊCH VỤ CỦA BÀN: "));
		pnlDatDichVu.setBounds(700, 25, 580, 460);
		pnlDatDichVu.setBackground(Color.WHITE);
		pnlDatDichVu.setLayout(new GridLayout(1, 0, 0, 0));
		pnlPhongDaDat.add(pnlDatDichVu);
		
		String[] header2 = {"Mã đồ uống","Tên đồ uống", "Số lượng", "Giá tiền", "Thành tiền" };
		tableModelHoaDonDV = new DefaultTableModel(header2, 0);
		tableHoaDonDV = new JTable(tableModelHoaDonDV) {
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
		tableHoaDonDV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableHoaDonDV.setGridColor(getBackground());
		tableHoaDonDV.setRowHeight(tableHoaDonDV.getRowHeight() + 20);
		tableHoaDonDV.setSelectionBackground(new Color(255, 255, 128));
		JTableHeader tableHeader2 = tableHoaDonDV.getTableHeader();
		tableHeader2.setBackground(new Color(173, 119, 72));
		tableHeader2.setForeground(new Color(255, 255, 255));
		tableHeader2.setFont(new Font("Arial", Font.BOLD, 15));
		tableHeader2.setPreferredSize(new Dimension(WIDTH, 40));
		tableHeader2.setResizingAllowed(false);
		
		
		pnlDatDichVu.add(new JScrollPane(tableHoaDonDV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		tableHoaDonDV.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableHoaDonDV.getColumnModel().getColumn(1).setPreferredWidth(20);
		tableHoaDonDV.getColumnModel().getColumn(2).setPreferredWidth(5);
		tableHoaDonDV.getColumnModel().getColumn(3).setPreferredWidth(5);
		tableHoaDonDV.getColumnModel().getColumn(4).setPreferredWidth(20);
		
		JPanel pnlCmbPhong = new JPanel();
		pnlCmbPhong.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"BÀN ĐANG CHỌN: "));
		pnlCmbPhong.setBounds(490, 32, 200, 50);
		pnlCmbPhong.setBackground(Color.WHITE);
		pnlCmbPhong.setLayout(new GridLayout(1, 0, 0, 0));
		lblBanDangChon = new JLabel("BÀN SỐ 1");
		lblBanDangChon.setBounds(0, 0, 170, 33);
		lblBanDangChon.setBackground(Color.WHITE);
		lblBanDangChon.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblBanDangChon.setForeground(new Color(131, 77, 30));
		pnlCmbPhong.add(lblBanDangChon);
		pnlPhongDaDat.add(pnlCmbPhong);

		JPanel pnlCmbDV = new JPanel();
		pnlCmbDV.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"NHẬP TÊN DỊCH VỤ CẦN TÌM: "));
		pnlCmbDV.setBounds(490, 92, 200, 50);
		pnlCmbDV.setBackground(Color.WHITE);
		pnlCmbDV.setLayout(new GridLayout(1, 0, 0, 0));
		cmbDanhSachDV = new JComboBox<String>();
		cmbDanhSachDV.setBounds(0, 0, 170, 33);
		cmbDanhSachDV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cmbDanhSachDV.setBackground(Color.WHITE);
		cmbDanhSachDV.setEditable(true);
		AutoCompleteDecorator.decorate(cmbDanhSachDV);
		pnlCmbDV.add(cmbDanhSachDV);
		pnlPhongDaDat.add(pnlCmbDV);

		// Button
		btnDatDichVu = new JButton("ĐẶT DỊCH VỤ");
		btnDatDichVu.setBounds(490, 200, 200, 42);
		btnDatDichVu.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDatDichVu.setIcon(new ImageIcon("image/datdichvu.png"));
		btnDatDichVu.setBackground(new Color(131, 77, 30));
		btnDatDichVu.setForeground(Color.WHITE);
		btnDatDichVu.setFocusPainted(false);
		pnlPhongDaDat.add(btnDatDichVu);

		btnHuyDichVu = new JButton("HỦY DỊCH VỤ");
		btnHuyDichVu.setBounds(490, 260, 200, 42);
		btnHuyDichVu.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHuyDichVu.setIcon(new ImageIcon("image/huydatphong.png"));
		btnHuyDichVu.setBackground(new Color(131, 77, 30));
		btnHuyDichVu.setForeground(Color.WHITE);
		btnHuyDichVu.setFocusPainted(false);
		pnlPhongDaDat.add(btnHuyDichVu);

		JLabel lblSoLuong = new JLabel("NHẬP SỐ LƯỢNG: ");
		lblSoLuong.setBounds(490, 150, 220, 42);
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnlPhongDaDat.add(lblSoLuong);
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtSoLuong.setBounds(640, 155, 50, 30);
		pnlPhongDaDat.add(txtSoLuong);
		
		tableHoaDonDV.setDefaultEditor(Object.class, null);
		tableListDV.setDefaultEditor(Object.class, null);
		tableHoaDonDV.getTableHeader().setReorderingAllowed(false);
		tableListDV.getTableHeader().setReorderingAllowed(false);
		tablePhongTrong.setDefaultEditor(Object.class, null);
		tablePhongTrong.getTableHeader().setReorderingAllowed(false);
		return pnlContentPane;
	}
}