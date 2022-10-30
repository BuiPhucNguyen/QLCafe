package app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.toedter.calendar.JDateChooser;


public class FrameNhanVien extends JFrame {
	private static JComboBox<String> cmbChucVu;
	private static JComboBox<String> cmbCa;
	private JButton btnThem;
	private JButton btnXoa;
	public static DefaultTableModel tableModel;
	public static JTable table;
	private JButton btnTimKiem;
	public static JComboBox<String> cmbTenNV;
	public static JComboBox<String> cmbSdt;
	public static JComboBox<String> cmbCmnd;
	public static JComboBox<String> cmbMaNV;
	private JButton btnLamMoi;
	private JButton btnCapNhat;
	private JButton btnXuatExcel;
	private static JCheckBox chkDangLam;
	private static JCheckBox chkDaNghiViec;
	private static JDateChooser txtNgaySinh;
	private static String actor;

	public JPanel createPanelNhanVien() {
		
		Toolkit toolkit = this.getToolkit(); /* Lấy độ phân giải màn hình */
		Dimension d = toolkit.getScreenSize();

		JPanel pnlContentPane = new JPanel();
		pnlContentPane.setBackground(new Color(248, 227, 182));
		pnlContentPane.setLayout(null);
		setContentPane(pnlContentPane);

		/*
		 * Chức năng
		 */
		btnThem = new JButton("THÊM MỚI", new ImageIcon("image/them.png"));
		btnThem.setBounds((int) (d.getWidth() - 910), 15, 165, 45);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThem.setBackground(new Color(131, 77, 30));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFocusPainted(false);
		pnlContentPane.add(btnThem);

		btnXoa = new JButton("NGHỈ VIỆC", new ImageIcon("image/xoa.png"));
		btnXoa.setBounds((int) (d.getWidth() - 740), 15, 165, 45);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXoa.setBackground(new Color(131, 77, 30));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFocusPainted(false);
		pnlContentPane.add(btnXoa);

		btnCapNhat = new JButton("CẬP NHẬT", new ImageIcon("image/capnhat.png"));
		btnCapNhat.setBounds((int) (d.getWidth() - 570), 15, 165, 45);
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCapNhat.setBackground(new Color(131, 77, 30));
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFocusPainted(false);
		pnlContentPane.add(btnCapNhat);

		btnXuatExcel = new JButton("XUẤT EXCEL", new ImageIcon("image/xuatexcel.png"));
		btnXuatExcel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXuatExcel.setBackground(new Color(131, 77, 30));
		btnXuatExcel.setForeground(Color.WHITE);
		btnXuatExcel.setBounds((int) (d.getWidth() - 400), 15, 165, 45);
		btnXuatExcel.setFocusPainted(false);
		pnlContentPane.add(btnXuatExcel);

		// Tìm kiếm
		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), ""));
		pnlTimKiem.setBounds(20, 15, 202, (int) (d.getHeight() - 109));
		pnlTimKiem.setBackground(new Color(252, 242, 217));
		pnlTimKiem.setLayout(null);
		pnlContentPane.add(pnlTimKiem);

		JLabel lblMaNV = new JLabel("<html><div style='text-align: center;'>MÃ NHÂN VIÊN: </div></html>",
				SwingConstants.CENTER);
		lblMaNV.setOpaque(true);
		lblMaNV.setBackground(new Color(173, 119, 72));
		lblMaNV.setBounds(1, 10, 200, 30);
		lblMaNV.setForeground(Color.WHITE);
		lblMaNV.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblMaNV);
		cmbMaNV = new JComboBox<String>();
		cmbMaNV.setBounds(26, 50, 150, 30);
		cmbMaNV.setBackground(Color.WHITE);
		cmbMaNV.setEditable(true);
		cmbMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		AutoCompleteDecorator.decorate(cmbMaNV);
		pnlTimKiem.add(cmbMaNV);
		cmbMaNV.setMaximumRowCount(3);

		JLabel lblTenNV = new JLabel("<html><div style='text-align: center;'>TÊN NHÂN VIÊN: </div></html>",
				SwingConstants.CENTER);
		lblTenNV.setOpaque(true);
		lblTenNV.setBackground(new Color(173, 119, 72));
		lblTenNV.setBounds(1, 100, 200, 30);
		lblTenNV.setForeground(Color.WHITE);
		lblTenNV.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblTenNV);
		cmbTenNV = new JComboBox<String>();
		cmbTenNV.setBounds(26, 140, 150, 30);
		cmbTenNV.setBackground(Color.WHITE);
		cmbTenNV.setEditable(true);
		cmbTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		AutoCompleteDecorator.decorate(cmbTenNV);
		pnlTimKiem.add(cmbTenNV);
		cmbTenNV.setMaximumRowCount(3);

		JLabel lblSdt = new JLabel("<html><div style='text-align: center;'>SỐ ĐIỆN THOẠI: </div></html>",
				SwingConstants.CENTER);
		lblSdt.setOpaque(true);
		lblSdt.setBackground(new Color(173, 119, 72));
		lblSdt.setBounds(1, 190, 200, 30);
		lblSdt.setForeground(Color.WHITE);
		lblSdt.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblSdt);
		cmbSdt = new JComboBox<String>();
		cmbSdt.setBounds(26, 230, 150, 30);
		cmbSdt.setBackground(Color.WHITE);
		cmbSdt.setEditable(true);
		cmbSdt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		AutoCompleteDecorator.decorate(cmbSdt);
		pnlTimKiem.add(cmbSdt);
		cmbSdt.setMaximumRowCount(3);

		JLabel lblNgaySinh = new JLabel("<html><div style='text-align: center;'>NGÀY SINH: </div></html>",
				SwingConstants.CENTER);
		lblNgaySinh.setBounds(1, 280, 200, 30);
		lblNgaySinh.setOpaque(true);
		lblNgaySinh.setBackground(new Color(173, 119, 72));
		lblNgaySinh.setForeground(Color.WHITE);
		lblNgaySinh.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblNgaySinh);
		txtNgaySinh = new JDateChooser();
		txtNgaySinh.setDateFormatString("yyyy-MM-dd");
		txtNgaySinh.setBounds(26, 320, 150, 30);
		txtNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		pnlTimKiem.add(txtNgaySinh);

		JLabel lblCmnd = new JLabel("<html><div style='text-align: center;'>CMND/CCCD: </div></html>",
				SwingConstants.CENTER);
		lblCmnd.setOpaque(true);
		lblCmnd.setBackground(new Color(173, 119, 72));
		lblCmnd.setBounds(1, 370, 200, 30);
		lblCmnd.setForeground(Color.WHITE);
		lblCmnd.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblCmnd);
		cmbCmnd = new JComboBox<String>();
		cmbCmnd.setBounds(26, 410, 150, 30);
		cmbCmnd.setBackground(Color.WHITE);
		cmbCmnd.setEditable(true);
		cmbCmnd.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		AutoCompleteDecorator.decorate(cmbCmnd);
		pnlTimKiem.add(cmbCmnd);
		cmbCmnd.setMaximumRowCount(3);

		JLabel lblNoiLamViec = new JLabel("<html><div style='text-align: center;'>NƠI LÀM VIỆC: </div></html>",
				SwingConstants.CENTER);
		lblNoiLamViec.setOpaque(true);
		lblNoiLamViec.setBackground(new Color(173, 119, 72));
		lblNoiLamViec.setForeground(Color.WHITE);
		lblNoiLamViec.setBounds(1, 460, 200, 30);
		lblNoiLamViec.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblNoiLamViec);

		JLabel lblCa = new JLabel("Ca: ");
		lblCa.setBounds(10, 500, 150, 30);
		lblCa.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTimKiem.add(lblCa);
		String[] ca = { "Tất cả", "8:00AM-4:00PM", "4:00PM-12:00AM", "12:00AM-8:00AM" };
		cmbCa = new JComboBox<String>(ca);
		cmbCa.setBounds(55, 500, 135, 30);
		cmbCa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cmbCa.setFocusable(false);
		pnlTimKiem.add(cmbCa);

		JLabel lblChucvu = new JLabel("Chức vụ:  ");
		lblChucvu.setBounds(10, 540, 150, 30);
		lblChucvu.setFont(new Font("Arial", Font.PLAIN, 15));
		;
		pnlTimKiem.add(lblChucvu);
		String[] chucVu = { "Tất cả", "Phục vụ", "Lễ tân" };
		cmbChucVu = new JComboBox<String>(chucVu);
		cmbChucVu.setBounds(90, 540, 100, 30);
		cmbChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cmbChucVu.setFocusable(false);
		pnlTimKiem.add(cmbChucVu);

		chkDangLam = new JCheckBox("Đang làm");
		chkDangLam.setBounds(10, 580, 85, 30);
		chkDangLam.setFont(new Font("Arial", Font.ITALIC, 13));
		chkDangLam.setBackground(Color.WHITE);
		chkDangLam.setSelected(true);
		pnlTimKiem.add(chkDangLam);
		chkDaNghiViec = new JCheckBox("Đã nghỉ việc");
		chkDaNghiViec.setBounds(97, 580, 97, 30);
		chkDaNghiViec.setFont(new Font("Arial", Font.ITALIC, 13));
		chkDaNghiViec.setSelected(true);
		chkDaNghiViec.setBackground(Color.WHITE);
		pnlTimKiem.add(chkDaNghiViec);

		btnTimKiem = new JButton("TÌM KIẾM", new ImageIcon("image/timkiem.png"));
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTimKiem.setBackground(new Color(131, 77, 30));
		btnTimKiem.setBounds(17, 630, 170, 45);
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFocusPainted(false);
		pnlTimKiem.add(btnTimKiem);

		btnLamMoi = new JButton("LÀM MỚI", new ImageIcon("image/lammoi.png"));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBackground(new Color(131, 77, 30));
		btnLamMoi.setBounds(17, 690, 170, 45);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFocusPainted(false);
		pnlTimKiem.add(btnLamMoi);

		/*
		 * Danh sách nhân viên
		 */
		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH NHÂN VIÊN: "));
		pnlDanhSach.setBounds(250, 75, (int) (d.getWidth() - 470), (int) (d.getHeight() - 165));
		pnlDanhSach.setBackground(new Color(255, 255, 255));
		pnlDanhSach.setLayout(new GridLayout(1, 0, 0, 0));
		pnlContentPane.add(pnlDanhSach);

		String[] header = { "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "CMND/CCCD", "SĐT", "Chức vụ", "Lương", "Tài khoản", "Trạng thái" };
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel) {
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
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table.setRowHeight(table.getRowHeight() + 20);
		table.setSelectionBackground(new Color(255, 255, 128));
		table.setGridColor(getBackground());
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(new Color(173, 119, 72));
		tableHeader.setFont(new Font("Arial", Font.BOLD, 15));
		tableHeader.setPreferredSize(new Dimension(WIDTH, 40));
		tableHeader.setResizingAllowed(false);
		tableHeader.setForeground(new Color(255, 255, 255));
		pnlDanhSach.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		table.getColumn("Lương").setCellRenderer(rightRenderer);

		

		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);

		return pnlContentPane;
	}

}
