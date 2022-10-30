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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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

public class FrameBan extends JFrame  {
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnCapNhat;
	public static DefaultTableModel tableModel;
	public static JTable table;
	private JButton btnLamMoi;
	private JButton btnTimKiem;
	public static JComboBox<String> cmbMaPhong;
	public static JComboBox<String> cmbTenPhong;
	private JTextField txtGiaMin;
	private JTextField txtGiaMax;
	private JComboBox<String> cmbLoaiPhong;
	private JComboBox<String> cmbTrangThai;
	private JButton btnXuatExcel;
	private JComboBox<String> cmbTieuChi;
	private JRadioButton radTangDan;
	private JRadioButton radGiamDan;


	public JPanel createPanelPhongHat() throws ParseException {
		
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

		btnXoa = new JButton("XÓA", new ImageIcon("image/xoa.png"));
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
		// Thông tin tìm kiếm
		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), ""));
		pnlTimKiem.setBounds(20, 15, 195, (int) (d.getHeight() - 109));
		pnlTimKiem.setBackground(new Color(252, 242, 217));
		pnlTimKiem.setLayout(null);
		pnlContentPane.add(pnlTimKiem);
		
		JLabel lblMaPhong = new JLabel("<html><div style='text-align: center;'>MÃ BÀN: </div></html>", SwingConstants.CENTER);
		lblMaPhong.setOpaque(true);
		lblMaPhong.setBackground(new Color(173, 119, 72));
		lblMaPhong.setBounds(1, 10, 200, 30);
		lblMaPhong.setForeground(Color.WHITE);
		lblMaPhong.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblMaPhong);
		cmbMaPhong = new JComboBox<String>();
		cmbMaPhong.setBounds(26, 50, 150, 30);
		cmbMaPhong.setBackground(Color.WHITE);
		cmbMaPhong.setEditable(true);
		cmbMaPhong.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		AutoCompleteDecorator.decorate(cmbMaPhong);
		pnlTimKiem.add(cmbMaPhong);
		cmbMaPhong.setMaximumRowCount(3);

		JLabel lblTenPhong = new JLabel("<html><div style='text-align: center;'>TÊN BÀN: </div></html>", SwingConstants.CENTER);
		lblTenPhong.setOpaque(true);
		lblTenPhong.setBackground(new Color(173, 119, 72));
		lblTenPhong.setBounds(1, 100, 193, 30);
		lblTenPhong.setForeground(Color.WHITE);
		lblTenPhong.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblTenPhong);
		cmbTenPhong = new JComboBox<String>();
		cmbTenPhong.setBounds(22, 140, 150, 30);
		cmbTenPhong.setBackground(Color.WHITE);
		cmbTenPhong.setEditable(true);
		cmbTenPhong.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		AutoCompleteDecorator.decorate(cmbTenPhong);
		cmbTenPhong.setMaximumRowCount(3);
		pnlTimKiem.add(cmbTenPhong);

		JLabel lblTrangThai = new JLabel("<html><div style='text-align: center;'>TRẠNG THÁI:  </div></html>", SwingConstants.CENTER);
		lblTrangThai.setBounds(1, 190, 193, 30);
		lblTrangThai.setOpaque(true);
		lblTrangThai.setBackground(new Color(173, 119, 72));
		lblTrangThai.setForeground(Color.WHITE);
		lblTrangThai.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblTrangThai);
		String[] trangthai = { "Tất cả", "Đã đặt", "Trống" };
		cmbTrangThai = new JComboBox<String>(trangthai);
		cmbTrangThai.setBounds(22, 230, 150, 30);
		cmbTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cmbTrangThai.setFocusable(false);
		pnlTimKiem.add(cmbTrangThai);

		JLabel lblSapXep = new JLabel("<html><div style='text-align: center;'>SẮP XẾP THEO: </div></html>", SwingConstants.CENTER);
		lblSapXep.setBounds(1, 280, 193, 30);
		lblSapXep.setOpaque(true);
		lblSapXep.setBackground(new Color(173, 119, 72));
		lblSapXep.setForeground(Color.WHITE);
		lblSapXep.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblSapXep);
		String[] tieuChi = { "Mã bàn", "Tên bàn"};
		cmbTieuChi = new JComboBox<String>(tieuChi);
		cmbTieuChi.setBounds(22, 320, 150, 30);
		cmbTieuChi.setFocusable(false);
		cmbTieuChi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		pnlTimKiem.add(cmbTieuChi);
		
		radTangDan = new JRadioButton("Tăng dần");
		radTangDan.setBounds(15, 360, 80, 30);
		radTangDan.setBackground(Color.WHITE);
		radTangDan.setFont(new Font("Arial", Font.ITALIC, 13));
		radTangDan.setSelected(true);
		radGiamDan = new JRadioButton("Giảm dần");
		radGiamDan.setBounds(95, 360, 90, 30);
		radGiamDan.setBackground(Color.WHITE);
		radGiamDan.setFont(new Font("Arial", Font.ITALIC, 13));
		ButtonGroup bg = new ButtonGroup();
		bg.add(radTangDan);
		bg.add(radGiamDan);
		pnlTimKiem.add(radTangDan);
		pnlTimKiem.add(radGiamDan);
		
		btnTimKiem = new JButton("TÌM KIẾM", new ImageIcon("image/timkiem.png"));
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTimKiem.setBackground(new Color(131, 77, 30));
		btnTimKiem.setBounds(14, 410, 170, 45);
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFocusPainted(false);
		pnlTimKiem.add(btnTimKiem);
		
		btnLamMoi = new JButton("LÀM MỚI", new ImageIcon("image/lammoi.png"));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBackground(new Color(131, 77, 30));
		btnLamMoi.setBounds(14, 470, 170, 45);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFocusPainted(false);
		pnlTimKiem.add(btnLamMoi);

		/*
		 * Danh sách phòng hát
		 */
		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH PHÒNG HÁT: "));
		pnlDanhSach.setBounds(230, 75, (int) (d.getWidth() - 450), (int) (d.getHeight() - 165));
		pnlDanhSach.setBackground(new Color(255, 255, 255));
		pnlDanhSach.setLayout(new GridLayout(1, 0, 0, 0));

		String[] header = { "Mã bàn", "Tên bàn", "Trạng thái" };
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
		tableHeader.setForeground(new Color(255, 255, 255));
		tableHeader.setFont(new Font("Arial", Font.BOLD, 15));
		tableHeader.setPreferredSize(new Dimension(WIDTH, 40));
		tableHeader.setResizingAllowed(false);
		pnlDanhSach.add(new JScrollPane(table));
		
		pnlContentPane.add(pnlDanhSach);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);


		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		return pnlContentPane;
	}


}
