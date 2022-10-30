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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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


public class FrameThongKeDichVu extends JFrame {
	private JComboBox<String> cmbTieuChi;
	private JButton btnTimKiem;
	private JButton btnLamMoi;
	private DefaultTableModel tableModel;
	private JTable table;
	private JRadioButton radHomNay;
	private JRadioButton radMotTuan;
	private JRadioButton radMotThang;
	private JRadioButton radLuaChonKhac;
	private JRadioButton radTangDan;
	private JRadioButton radGiamDan;
	private JButton btnXuatExcel;
	private JButton btnSoLanDenQuan;
	private JComboBox<String> cmbMaKH;
	private JDateChooser dateNgayMin;
	private JDateChooser dateNgayMax;

	public JPanel createPanelKhachHang() {
		
		Toolkit toolkit = this.getToolkit(); /* Lấy độ phân giải màn hình */
		Dimension d = toolkit.getScreenSize();

		JPanel pnlContentPane = new JPanel();
		pnlContentPane.setBackground(new Color(248, 227, 182));
		pnlContentPane.setLayout(null);
		setContentPane(pnlContentPane);
		// Chức năng
//		btnSoLanDenQuan = new JButton("XEM SỐ LẦN ĐẾN QUÁN", new ImageIcon("image/thongtin.png"));
//		btnSoLanDenQuan.setBounds((int) (d.getWidth() - 670), 15, 260, 45);
//		btnSoLanDenQuan.setFont(new Font("Tahoma", Font.BOLD, 15));
//		btnSoLanDenQuan.setBackground(new Color(79, 173, 84));
//		btnSoLanDenQuan.setForeground(Color.WHITE);
//		btnSoLanDenQuan.setFocusPainted(false);
//		pnlContentPane.add(btnSoLanDenQuan);


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
		pnlTimKiem.setBounds(20, 15, 195, (int) (d.getHeight() - 149));
		pnlTimKiem.setBackground(new Color(252, 242, 217));
		pnlTimKiem.setLayout(null);
		pnlContentPane.add(pnlTimKiem);

		JLabel lblThongKe = new JLabel("<html><div style='text-align: center;'>THỐNG KÊ THEO: </div></html>",
				SwingConstants.CENTER);
		lblThongKe.setOpaque(true);
		lblThongKe.setBackground(new Color(173, 119, 72));
		lblThongKe.setBounds(1, 10, 193, 30);
		lblThongKe.setForeground(Color.WHITE);
		lblThongKe.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblThongKe);
		radHomNay = new JRadioButton("Hôm nay");
		radHomNay.setBounds(20, 50, 100, 30);
		radHomNay.setBackground(Color.WHITE);
		radHomNay.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		radHomNay.setSelected(true);
		radMotTuan = new JRadioButton("1 tuần");
		radMotTuan.setBounds(20, 80, 100, 30);
		radMotTuan.setBackground(Color.WHITE);
		radMotTuan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		radMotThang = new JRadioButton("1 tháng");
		radMotThang.setBounds(20, 110, 100, 30);
		radMotThang.setBackground(Color.WHITE);
		radMotThang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		radLuaChonKhac = new JRadioButton("Lựa chọn khác");
		radLuaChonKhac.setBounds(20, 140, 120, 30);
		radLuaChonKhac.setBackground(Color.WHITE);
		radLuaChonKhac.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		ButtonGroup bg = new ButtonGroup();
		bg.add(radHomNay);
		bg.add(radMotTuan);
		bg.add(radMotThang);
		bg.add(radLuaChonKhac);
		pnlTimKiem.add(radHomNay);
		pnlTimKiem.add(radMotTuan);
		pnlTimKiem.add(radMotThang);
		pnlTimKiem.add(radLuaChonKhac);

		JLabel lblNgayMin = new JLabel("Từ: ");
		lblNgayMin.setBounds(30, 175, 120, 30);
		lblNgayMin.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTimKiem.add(lblNgayMin);
		dateNgayMin = new JDateChooser();
		dateNgayMin.setDateFormatString("yyyy-MM-dd");
		dateNgayMin.setBounds(75, 175, 100, 30);
		dateNgayMin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		pnlTimKiem.add(dateNgayMin);

		JLabel lblNgayMax = new JLabel("Đến: ");
		lblNgayMax.setBounds(30, 215, 120, 30);
		lblNgayMax.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTimKiem.add(lblNgayMax);
		dateNgayMax = new JDateChooser();
		dateNgayMax.setDateFormatString("yyyy-MM-dd");
		dateNgayMax.setBounds(75, 215, 100, 30);
		dateNgayMax.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		pnlTimKiem.add(dateNgayMax);

		JLabel lblMaKH = new JLabel("MÃ ĐỒ UỐNG:", SwingConstants.CENTER);
		lblMaKH.setOpaque(true);
		lblMaKH.setBackground(new Color(173, 119, 72));
		lblMaKH.setBounds(1, 265, 193, 30);
		lblMaKH.setForeground(Color.WHITE);
		lblMaKH.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblMaKH);
		cmbMaKH = new JComboBox<String>();
		cmbMaKH.setBounds(12, 310, 170, 30);
		cmbMaKH.setBackground(Color.WHITE);
		cmbMaKH.setEditable(true);
		cmbMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		AutoCompleteDecorator.decorate(cmbMaKH);
		pnlTimKiem.add(cmbMaKH);
		cmbMaKH.setMaximumRowCount(1);

		JLabel lblSX = new JLabel("<html><div style='text-align: center;'>SẮP XẾP THEO: </div></html>",
				SwingConstants.CENTER);
		lblSX.setOpaque(true);
		lblSX.setBackground(new Color(173, 119, 72));
		lblSX.setBounds(1, 355, 193, 30);
		lblSX.setForeground(Color.WHITE);
		lblSX.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblSX);
		String[] loai = { "Mã đồ uống", "Tên đồ uống", "Số lượng bán" };
		cmbTieuChi = new JComboBox<String>(loai);
		cmbTieuChi.setBounds(12, 400, 170, 30);
		cmbTieuChi.setBackground(Color.WHITE);
		cmbTieuChi.setFocusable(false);
		cmbTieuChi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		pnlTimKiem.add(cmbTieuChi);

		radTangDan = new JRadioButton("Tăng dần");
		radTangDan.setBounds(15, 440, 80, 30);
		radTangDan.setBackground(Color.WHITE);
		radTangDan.setFont(new Font("Arial", Font.ITALIC, 13));
		radTangDan.setSelected(true);
		radGiamDan = new JRadioButton("Giảm dần");
		radGiamDan.setBounds(95, 440, 90, 30);
		radGiamDan.setBackground(Color.WHITE);
		radGiamDan.setFont(new Font("Arial", Font.ITALIC, 13));
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(radTangDan);
		bg2.add(radGiamDan);
		pnlTimKiem.add(radTangDan);
		pnlTimKiem.add(radGiamDan);

		btnTimKiem = new JButton("TÌM KIẾM", new ImageIcon("image/timkiem.png"));
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTimKiem.setBackground(new Color(131, 77, 30));
		btnTimKiem.setBounds(13, 490, 170, 45);
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFocusPainted(false);
		pnlTimKiem.add(btnTimKiem);

		btnLamMoi = new JButton("LÀM MỚI", new ImageIcon("image/lammoi.png"));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBackground(new Color(131, 77, 30));
		btnLamMoi.setBounds(13, 550, 170, 45);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFocusPainted(false);
		pnlTimKiem.add(btnLamMoi);

		/*
		 * Danh sách hóa đơn
		 */
		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "DANH SÁCH KHÁCH HÀNG: "));
		pnlDanhSach.setBounds(230, 75, (int) (d.getWidth() - 455), (int) (d.getHeight() - 205));
		pnlDanhSach.setBackground(Color.WHITE);
		pnlDanhSach.setLayout(new GridLayout(1, 0, 0, 0));
		pnlContentPane.add(pnlDanhSach);

		String[] header = { "Mã đồ uống", "Tên đồ uống", "Số lượng bán" };
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
		table.setGridColor(getBackground());
		table.setRowHeight(table.getRowHeight() + 20);
		table.setSelectionBackground(new Color(255, 255, 128));
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(new Color(173, 119, 72));
		tableHeader.setForeground(new Color(255, 255, 255));
		tableHeader.setFont(new Font("Arial", Font.BOLD, 15));
		tableHeader.setPreferredSize(new Dimension(WIDTH, 40));
		tableHeader.setResizingAllowed(false);
		pnlDanhSach.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));


		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		cmbMaKH.setSelectedIndex(-1);

		return pnlContentPane;
	}

}
