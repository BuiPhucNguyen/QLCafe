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
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import dao.impl.DAOImpl_NhanVien;
import entity.NhanVien;


public class FrameNhanVien extends JFrame {
	private static JComboBox<String> cmbChucVu;
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
	private static JDateChooser txtNgaySinh;
	private static DAOImpl_NhanVien dao_NhanVien;

	public JPanel createPanelNhanVien() throws RemoteException {
		dao_NhanVien = new DAOImpl_NhanVien();
		
		Toolkit toolkit = this.getToolkit(); /* L???y ????? ph??n gi???i m??n h??nh */
		Dimension d = toolkit.getScreenSize();

		JPanel pnlContentPane = new JPanel();
		pnlContentPane.setBackground(new Color(248, 227, 182));
		pnlContentPane.setLayout(null);
		setContentPane(pnlContentPane);

		/*
		 * Ch???c n??ng
		 */
		btnThem = new JButton("TH??M M???I", new ImageIcon("image/them.png"));
		btnThem.setBounds((int) (d.getWidth() - 910), 15, 165, 45);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThem.setBackground(new Color(131, 77, 30));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFocusPainted(false);
		pnlContentPane.add(btnThem);

		btnXoa = new JButton("NGH??? VI???C", new ImageIcon("image/xoa.png"));
		btnXoa.setBounds((int) (d.getWidth() - 740), 15, 165, 45);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXoa.setBackground(new Color(131, 77, 30));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFocusPainted(false);
		pnlContentPane.add(btnXoa);

		btnCapNhat = new JButton("C???P NH???T", new ImageIcon("image/capnhat.png"));
		btnCapNhat.setBounds((int) (d.getWidth() - 570), 15, 165, 45);
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCapNhat.setBackground(new Color(131, 77, 30));
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFocusPainted(false);
		pnlContentPane.add(btnCapNhat);

		btnXuatExcel = new JButton("XU???T EXCEL", new ImageIcon("image/xuatexcel.png"));
		btnXuatExcel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXuatExcel.setBackground(new Color(131, 77, 30));
		btnXuatExcel.setForeground(Color.WHITE);
		btnXuatExcel.setBounds((int) (d.getWidth() - 400), 15, 165, 45);
		btnXuatExcel.setFocusPainted(false);
		pnlContentPane.add(btnXuatExcel);

		// T??m ki???m
		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), ""));
		pnlTimKiem.setBounds(20, 15, 202, (int) (d.getHeight() - 109));
		pnlTimKiem.setBackground(new Color(252, 242, 217));
		pnlTimKiem.setLayout(null);
		pnlContentPane.add(pnlTimKiem);

		JLabel lblMaNV = new JLabel("<html><div style='text-align: center;'>M?? NH??N VI??N: </div></html>",
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

		JLabel lblTenNV = new JLabel("<html><div style='text-align: center;'>T??N NH??N VI??N: </div></html>",
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

		JLabel lblSdt = new JLabel("<html><div style='text-align: center;'>S??? ??I???N THO???I: </div></html>",
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

		JLabel lblNgaySinh = new JLabel("<html><div style='text-align: center;'>NG??Y SINH: </div></html>",
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

		JLabel lblNoiLamViec = new JLabel("<html><div style='text-align: center;'>CH???C V???: </div></html>",
				SwingConstants.CENTER);
		lblNoiLamViec.setOpaque(true);
		lblNoiLamViec.setBackground(new Color(173, 119, 72));
		lblNoiLamViec.setForeground(Color.WHITE);
		lblNoiLamViec.setBounds(1, 460, 200, 30);
		lblNoiLamViec.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTimKiem.add(lblNoiLamViec);

		JLabel lblChucvu = new JLabel("Ch???c v???:  ");
		lblChucvu.setBounds(10, 500, 150, 30);
		lblChucvu.setFont(new Font("Arial", Font.PLAIN, 15));
		;
		pnlTimKiem.add(lblChucvu);
		String[] chucVu = { "T???t c???", "Qu???n l??", "Nh??n vi??n" };
		cmbChucVu = new JComboBox<String>(chucVu);
		cmbChucVu.setBounds(90, 500, 100, 30);
		cmbChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cmbChucVu.setFocusable(false);
		pnlTimKiem.add(cmbChucVu);

		btnTimKiem = new JButton("T??M KI???M", new ImageIcon("image/timkiem.png"));
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTimKiem.setBackground(new Color(131, 77, 30));
		btnTimKiem.setBounds(17, 550, 170, 45);
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFocusPainted(false);
		pnlTimKiem.add(btnTimKiem);

		btnLamMoi = new JButton("L??M M???I", new ImageIcon("image/lammoi.png"));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBackground(new Color(131, 77, 30));
		btnLamMoi.setBounds(17, 610, 170, 45);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFocusPainted(false);
		pnlTimKiem.add(btnLamMoi);

		/*
		 * Danh s??ch nh??n vi??n
		 */
		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH S??CH NH??N VI??N: "));
		pnlDanhSach.setBounds(250, 75, (int) (d.getWidth() - 470), (int) (d.getHeight() - 165));
		pnlDanhSach.setBackground(new Color(255, 255, 255));
		pnlDanhSach.setLayout(new GridLayout(1, 0, 0, 0));
		pnlContentPane.add(pnlDanhSach);

		String[] header = { "M?? nh??n vi??n", "T??n nh??n vi??n", "Ng??y sinh", "Gi???i t??nh", "CMND/CCCD", "S??T", "Ch???c v???", "L????ng", "T??i kho???n" };
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
		table.getColumn("L????ng").setCellRenderer(rightRenderer);

		

		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new FormThemNV().setVisible(true);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCapNhat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r = table.getSelectedRow();
				if (r == -1) {
					JOptionPane.showMessageDialog(null, "Vui l??ng ch???n nh??n vi??n c???n c???p nh???t!", "L???i",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					try {
						new FormCapNhatNV().setVisible(true);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}	
		});
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r = table.getSelectedRow();
				if (r == -1) {
					JOptionPane.showMessageDialog(null, "Vui l??ng ch???n nh??n vi??n cho ngh??? vi???c!", "L???i",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (JOptionPane.showConfirmDialog(null, "B???n c?? ch???c s??? cho nh??n vi??n n??y ngh??? vi???c kh??ng?",
						"C???nh b??o", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION) {
					try {
						String maNV = tableModel.getValueAt(r, 0).toString();
						if (dao_NhanVien.xoaNhanVien(maNV)) {
							JOptionPane.showMessageDialog(null, "X??a th??nh c??ng!");
						}
						xoaHetDL();
						docDuLieuDatabaseVaoTable();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "X??a kh??ng th??nh c??ng!");
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		btnLamMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					lamMoiDL();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String maNV = cmbMaNV.getSelectedItem().toString().trim();
				String tenNV = cmbTenNV.getSelectedItem().toString().trim();
				String sdt = cmbSdt.getSelectedItem().toString().trim();
				Date ngaySinh = txtNgaySinh.getDate();
				String cmnd = cmbCmnd.getSelectedItem().toString().trim();
				String chucVu = cmbChucVu.getSelectedItem().toString().trim();
				
				xoaHetDL();
				List<NhanVien> listNV = new ArrayList<NhanVien>();
				listNV = dao_NhanVien.getAllNhanVien();
				if (!maNV.trim().equals("")) {
					List<NhanVien> listTemp = new ArrayList<NhanVien>();
					for (NhanVien nv : listNV) {
						listTemp.add(nv);
					}
					listNV.clear();
					for (NhanVien nv : listTemp) {
						if (nv.getMaNV().trim().contains(maNV)) {
							listNV.add(nv);
						}
					}
				}
				if (!tenNV.trim().equals("")) {
					List<NhanVien> listTemp = new ArrayList<NhanVien>();
					for (NhanVien nv : listNV) {
						listTemp.add(nv);
					}
					listNV.clear();
					for (NhanVien nv : listTemp) {
						if (nv.getTenNV().trim().contains(tenNV)) {
							listNV.add(nv);
						}
					}
				}
				if (!sdt.trim().equals("")) {
					List<NhanVien> listTemp = new ArrayList<NhanVien>();
					for (NhanVien nv : listNV) {
						listTemp.add(nv);
					}
					listNV.clear();
					for (NhanVien nv : listTemp) {
						if (nv.getSdt().trim().contains(sdt)) {
							listNV.add(nv);
						}
					}
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				if (ngaySinh != null) {
					List<NhanVien> listTemp = new ArrayList<NhanVien>();
					for (NhanVien nv : listNV) {
						listTemp.add(nv);
					}
					listNV.clear();
					for (NhanVien nv : listTemp) {
						if (df.format(nv.getNgaySinh()).equals(df.format(ngaySinh))) {
							listNV.add(nv);
						}
					}
				}
				if (!cmnd.trim().equals("")) {
					List<NhanVien> listTemp = new ArrayList<NhanVien>();
					for (NhanVien nv : listNV) {
						listTemp.add(nv);
					}
					listNV.clear();
					for (NhanVien nv : listTemp) {
						if (nv.getCmnd().trim().contains(cmnd)) {
							listNV.add(nv);
						}
					}
				}
				if (!chucVu.trim().equals("T???t c???")) {
					List<NhanVien> listTemp = new ArrayList<NhanVien>();
					for (NhanVien nv : listNV) {
						listTemp.add(nv);
					}
					listNV.clear();
					for (NhanVien nv : listTemp) {
						if (nv.getChucVu().trim().equalsIgnoreCase(chucVu)) {
							listNV.add(nv);
						}
					}
				}

				if (listNV.size() == 0) {
					JOptionPane.showMessageDialog(null, "Kh??ng c?? nh??n vi??n n??o ph?? h???p v???i ti??u ch??", "L???i",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				DecimalFormat dfMoney = new DecimalFormat("#,##0.0");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for (NhanVien nv : listNV) {
					tableModel.addRow(new Object[] { nv.getMaNV().trim(), nv.getTenNV().trim(), sdf.format(nv.getNgaySinh()),
							nv.isGioiTinh() == true ? "Nam" : "N???", nv.getCmnd().trim(), nv.getSdt().trim(),
							nv.getChucVu().trim(), dfMoney.format(nv.getLuong()),
							nv.getTaiKhoan().getTenTaiKhoan().getMaNV()});
				}
			}
		});
		
		btnXuatExcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileDialog = new JFileChooser("excel") {
					@Override
					protected JDialog createDialog(Component parent) throws HeadlessException {
						JDialog dialog = super.createDialog(parent);
						ImageIcon icon = new ImageIcon("image/logodark.png");
						dialog.setIconImage(icon.getImage());
						return dialog;
					}
				};
				FileFilter filter = new FileNameExtensionFilter("Excel(.xls)", ".xls");
				fileDialog.setAcceptAllFileFilterUsed(false);
				fileDialog.addChoosableFileFilter(filter);
				int returnVal = fileDialog.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					java.io.File file = fileDialog.getSelectedFile();
					String filePath = file.getAbsolutePath();
					if(!(filePath.endsWith(".xls") || filePath.endsWith(".xlsx"))) {
						filePath += ".xls";
					}
					if (xuatExcel(filePath))
						JOptionPane.showMessageDialog(null, "Ghi file th??nh c??ng!!", "Th??nh c??ng",
								JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Ghi file th???t b???i!!", "L???i", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		docDuLieuDatabaseVaoTable();
		docDuLieuVaoCmbCmnd();
		docDuLieuVaoCmbMaNV();
		docDuLieuVaoCmbSdt();
		docDuLieuVaoCmbTenNV();
		return pnlContentPane;
	}
	
	public static void lamMoiDL() throws RemoteException {
		cmbChucVu.setSelectedIndex(0);
		cmbTenNV.setSelectedIndex(0);
		cmbMaNV.setSelectedIndex(0);
		cmbCmnd.setSelectedIndex(0);
		cmbSdt.setSelectedIndex(0);
		txtNgaySinh.setDate(null);

		xoaHetDL();
		docDuLieuDatabaseVaoTable();
	}
	
	public static void xoaHetDL() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.setRowCount(0);
	}
	
	public static void docDuLieuDatabaseVaoTable() throws RemoteException {
		dao_NhanVien = new DAOImpl_NhanVien();
		List<NhanVien> listNV = new ArrayList<NhanVien>();
		
		listNV = dao_NhanVien.getAllNhanVien();
		DecimalFormat df = new DecimalFormat("#,##0.0");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (NhanVien nv : listNV) {
			tableModel.addRow(new Object[] { nv.getMaNV().trim(), nv.getTenNV().trim(), sdf.format(nv.getNgaySinh()),
					nv.isGioiTinh() == true ? "Nam" : "N???", nv.getCmnd().trim(), nv.getSdt().trim(),
					nv.getChucVu().trim(), df.format(nv.getLuong()),
					nv.getTaiKhoan().getTenTaiKhoan().getMaNV()});
		}
	}
	
	public static void docDuLieuVaoCmbTenNV() throws RemoteException {
		dao_NhanVien = new DAOImpl_NhanVien();
		List<NhanVien> listNV = new ArrayList<NhanVien>();
		
		listNV = dao_NhanVien.getAllNhanVien();
		cmbTenNV.addItem("");
		for (NhanVien nv : listNV) {
			cmbTenNV.addItem(nv.getTenNV().trim());
		}
	}

	public static void docDuLieuVaoCmbMaNV() throws RemoteException {
		dao_NhanVien = new DAOImpl_NhanVien();
		List<NhanVien> listNV = new ArrayList<NhanVien>();
		
		listNV = dao_NhanVien.getAllNhanVien();
		cmbMaNV.addItem("");
		for (NhanVien nv : listNV) {
			cmbMaNV.addItem(nv.getMaNV().trim());
		}
	}

	public static void docDuLieuVaoCmbCmnd() throws RemoteException {
		dao_NhanVien = new DAOImpl_NhanVien();
		List<NhanVien> listNV = new ArrayList<NhanVien>();
		
		listNV = dao_NhanVien.getAllNhanVien();
		cmbCmnd.addItem("");
		for (NhanVien nv : listNV) {
			cmbCmnd.addItem(nv.getCmnd().trim());
		}
	}

	public static void docDuLieuVaoCmbSdt() throws RemoteException {
		dao_NhanVien = new DAOImpl_NhanVien();
		List<NhanVien> listNV = new ArrayList<NhanVien>();
		
		listNV = dao_NhanVien.getAllNhanVien();
		cmbSdt.addItem("");
		for (NhanVien nv : listNV) {
			cmbSdt.addItem(nv.getSdt().trim());
		}
	}
	public boolean xuatExcel(String filePath) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filePath);
			// T???o sheet Danh s??ch kh??ch h??ng
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("DANH S??CH NH??N VI??N");

			HSSFRow row;
			HSSFCell cell;

			// D??ng 1 t??n
			cell = worksheet.createRow(1).createCell(1);

			HSSFFont newFont = cell.getSheet().getWorkbook().createFont();
			newFont.setBold(true);
			newFont.setFontHeightInPoints((short) 13);
			CellStyle styleTenDanhSach = worksheet.getWorkbook().createCellStyle();
			styleTenDanhSach.setAlignment(HorizontalAlignment.CENTER);
			styleTenDanhSach.setFont(newFont);

			cell.setCellValue("DANH S??CH NH??N VI??N");
			cell.setCellStyle(styleTenDanhSach);

			String[] header = { "STT", "M?? nh??n vi??n", "T??n nh??n vi??n", "Ng??y sinh", "Gi???i t??nh", "CMND/CCCD", "S??T",
					"Ch???c v???", "L????ng", "T??i kho???n" };
			worksheet.addMergedRegion(new CellRangeAddress(1, 1, 1, header.length));

			// D??ng 2 ng?????i l???p
			row = worksheet.createRow(2);

			cell = row.createCell(1);
			cell.setCellValue("Ng?????i l???p:");
			cell = row.createCell(2);

			NhanVien nv = FrameDangNhap.getTaiKhoan().getTenTaiKhoan();
			
			cell.setCellValue(dao_NhanVien.getNhanVienTheoMa(nv.getMaNV()).getTenNV());
			worksheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 3));

			// D??ng 3 ng??y l???p
			row = worksheet.createRow(3);
			cell = row.createCell(1);
			cell.setCellValue("Ng??y l???p:");
			cell = row.createCell(2);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			cell.setCellValue(df.format(new Date()));
			worksheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 3));

			// D??ng 4 t??n c??c c???t
			row = worksheet.createRow(4);

			HSSFFont fontHeader = cell.getSheet().getWorkbook().createFont();
			fontHeader.setBold(true);

			CellStyle styleHeader = worksheet.getWorkbook().createCellStyle();
			styleHeader.setFont(fontHeader);
			styleHeader.setBorderBottom(BorderStyle.THIN);
			styleHeader.setBorderTop(BorderStyle.THIN);
			styleHeader.setBorderLeft(BorderStyle.THIN);
			styleHeader.setBorderRight(BorderStyle.THIN);
			styleHeader.setAlignment(HorizontalAlignment.CENTER);

			styleHeader.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
			styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			for (int i = 0; i < header.length; i++) {
				cell = row.createCell(i + 1);
				cell.setCellValue(header[i]);
				cell.setCellStyle(styleHeader);
			}

			if (table.getRowCount() == 0) {
				return false;
			}

			HSSFFont fontRow = cell.getSheet().getWorkbook().createFont();
			fontRow.setBold(false);

			CellStyle styleRow = worksheet.getWorkbook().createCellStyle();
			styleRow.setFont(fontRow);
			styleRow.setBorderBottom(BorderStyle.THIN);
			styleRow.setBorderTop(BorderStyle.THIN);
			styleRow.setBorderLeft(BorderStyle.THIN);
			styleRow.setBorderRight(BorderStyle.THIN);

			// Ghi d??? li???u v??o b???ng
			int STT = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				row = worksheet.createRow(5 + i);
				for (int j = 0; j < header.length; j++) {
					cell = row.createCell(j + 1);
					if (STT == i) {
						cell.setCellValue(STT + 1);
						STT++;
					} else {
						if (table.getValueAt(i, j - 1) != null) {
							if (j == header.length - 2) {
								String luong[] = tableModel.getValueAt(i, j - 1).toString().split(",");
								String tienLuong = "";
								for (int t = 0; t < luong.length; t++)
									tienLuong += luong[t];
								cell.setCellValue(Double.parseDouble(tienLuong));
							} else
								cell.setCellValue(table.getValueAt(i, j - 1).toString().trim());
						}
					}
					cell.setCellStyle(styleRow);
				}
			}

			for (int i = 1; i < header.length + 1; i++) {
				worksheet.autoSizeColumn(i);
			}

			workbook.write(fileOut);
			workbook.close();
			fileOut.flush();
			fileOut.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
