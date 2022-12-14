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
import java.rmi.RemoteException;
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

import dao.impl.DAOImpl_Ban;
import dao.impl.DAOImpl_CTHD;
import dao.impl.DAOImpl_HoaDon;
import dao.impl.DAOImpl_NhanVien;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;


public class FrameThanhToan extends JFrame  {

	public static JTable tableChonPhong;
	private JTextField txtTenBan;
	private JTextField txtTienDV;
	private JTextField txtTienPhong;
	private JTextField txtNhanVien;
	private JButton btnThanhToan;
	public static JComboBox<String> cmbChonPhong;
	private JButton btnTimPhong;
	private JButton btnInHoaDon;
	private JTextField txtThanhToan;
	private JButton btnXemCTHD;
	private JButton btnLamMoi;
	private static DAOImpl_Ban dao_Ban;
	private static DAOImpl_NhanVien dao_NV;
	private static DAOImpl_HoaDon dao_HD;
	private static DAOImpl_CTHD dao_CTHD;
	public static DefaultTableModel tableModelChonBan;
	public static JTable tableChonBan;
	

	public JPanel createPanelTraPhong() throws RemoteException {
		
		dao_Ban = new DAOImpl_Ban();
		dao_NV = new DAOImpl_NhanVien();
		dao_HD = new DAOImpl_HoaDon();
		dao_CTHD = new DAOImpl_CTHD();

		setTitle("THANH TO??N");
		setSize(948, 440);
		setLocationRelativeTo(null);
		setResizable(false);

		Toolkit toolkit = this.getToolkit(); /* L???y ????? ph??n gi???i m??n h??nh */
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
		 * Ch???n ph??ng tr???
		 */
		JPanel pnlTraPhong = new JPanel();
		pnlTraPhong.setLayout(new GridLayout(1, 1));
		pnlTraPhong.setBounds(350, 10, (int) (d.getWidth() - 570), (int) (d.getHeight() - 90));
		pnlTraPhong.setBackground(Color.WHITE);
		pnlTraPhong.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "CH???N B??N C???N TR???: "));
		pnlContentPane.add(pnlTraPhong);

		String[] header = { "M?? b??n", "T??n b??n"};
		tableModelChonBan = new DefaultTableModel(header, 0);
		tableChonBan = new JTable(tableModelChonBan) {
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
		tableChonBan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableChonBan.setGridColor(getBackground());
		tableChonBan.setRowHeight(tableChonBan.getRowHeight() + 20);
		tableChonBan.setSelectionBackground(new Color(255, 255, 128));
		JTableHeader tableHeader = tableChonBan.getTableHeader();
		tableHeader.setBackground(new Color(173, 119, 72));
		tableHeader.setForeground(new Color(255, 255, 255));
		tableHeader.setFont(new Font("Arial", Font.BOLD, 15));
		tableHeader.setPreferredSize(new Dimension(WIDTH, 40));
		tableHeader.setResizingAllowed(false);
		tableHeader.setReorderingAllowed(false);


		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		pnlTraPhong.add(new JScrollPane(tableChonBan));

		// Ch???n ph??ng
		JPanel pnlChonPhong = new JPanel();
		pnlChonPhong.setLayout(null);
		pnlChonPhong.setBounds(20, 18, 315, 102);
		pnlChonPhong.setBackground(new Color(252, 242, 217));
		pnlChonPhong.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), ""));
		pnlContentPane.add(pnlChonPhong);

		JLabel lblChonPhong = new JLabel("NH???P T??N B??N C???N T??M: ", SwingConstants.CENTER);
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
		 * Th??ng tin h??a ????n
		 */
		JPanel pnThongTinHoaDon = new JPanel();
		pnThongTinHoaDon.setLayout(null);


		pnThongTinHoaDon.setBounds(20, 135, 315, (int) (d.getHeight() - 215));

		pnThongTinHoaDon.setBackground(new Color(252, 242, 217));
		pnThongTinHoaDon.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "TH??NG TIN H??A ????N: "));
		pnlContentPane.add(pnThongTinHoaDon);

		JLabel lblNhanVien = new JLabel("T??N NH??N VI??N: ");
		lblNhanVien.setBounds(15, 37, 150, 25);
		lblNhanVien.setFont(new Font("Arial", Font.BOLD, 13));
		pnThongTinHoaDon.add(lblNhanVien);
		txtNhanVien = new JTextField();
		txtNhanVien.setEditable(false);
		txtNhanVien.setBounds(145, 35, 150, 30);
		txtNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 15));
		pnThongTinHoaDon.add(txtNhanVien);


		JLabel lblTenPhong = new JLabel("T??N B??N: ");
		lblTenPhong.setBounds(15, 82, 150, 25);
		lblTenPhong.setFont(new Font("Arial", Font.BOLD, 13));
		pnThongTinHoaDon.add(lblTenPhong);
		txtTenBan = new JTextField();
		txtTenBan.setEditable(false);
		txtTenBan.setBounds(145, 80, 150, 30);
		txtTenBan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		pnThongTinHoaDon.add(txtTenBan);

		JLabel lblTienPhong = new JLabel("TI???N D???CH V???:");
		lblTienPhong.setBounds(15, 127, 150, 25);
		lblTienPhong.setFont(new Font("Arial", Font.BOLD, 13));
		pnThongTinHoaDon.add(lblTienPhong);
		txtTienPhong = new JTextField();
		txtTienPhong.setEditable(false);
		txtTienPhong.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtTienPhong.setBounds(145, 125, 150, 30);
		pnThongTinHoaDon.add(txtTienPhong);

		JLabel lblTienDV = new JLabel("PH??? THU(8% VAT):");
		lblTienDV.setFont(new Font("Arial", Font.BOLD, 13));
		lblTienDV.setBounds(15, 172, 150, 25);
		pnThongTinHoaDon.add(lblTienDV);
		txtTienDV = new JTextField();
		txtTienDV.setEditable(false);
		txtTienDV.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtTienDV.setBounds(145, 170, 150, 30);
		pnThongTinHoaDon.add(txtTienDV);

		JLabel lblThanhToan = new JLabel("T???NG TI???N:");
		lblThanhToan.setFont(new Font("Arial", Font.BOLD, 13));
		lblThanhToan.setBounds(15, 217, 150, 25);
		pnThongTinHoaDon.add(lblThanhToan);
		txtThanhToan = new JTextField();
		txtThanhToan.setEditable(false);
		txtThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtThanhToan.setBounds(145, 215, 150, 30);
		pnThongTinHoaDon.add(txtThanhToan);
		
		btnXemCTHD = new JButton("XEM CTHD");
		btnXemCTHD.setBounds(75, 270, 165, 40);
		btnXemCTHD.setIcon(new ImageIcon("image/timkiem.png"));
		btnXemCTHD.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXemCTHD.setBackground(new Color(131, 77, 30));
		btnXemCTHD.setForeground(Color.WHITE);
		btnXemCTHD.setFocusPainted(false);
		pnThongTinHoaDon.add(btnXemCTHD);
		
		btnLamMoi = new JButton("L??M M???I");
		btnLamMoi.setBounds(75, 330, 165, 40);
		btnLamMoi.setIcon(new ImageIcon("image/lammoi.png"));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoi.setBackground(new Color(131, 77, 30));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFocusPainted(false);
		pnThongTinHoaDon.add(btnLamMoi);

		btnInHoaDon = new JButton("IN H??A ????N");
		btnInHoaDon.setBounds(75, 390, 165, 40);
		btnInHoaDon.setIcon(new ImageIcon("image/inhoadon.png"));
		btnInHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInHoaDon.setBackground(new Color(131, 77, 30));
		btnInHoaDon.setForeground(Color.WHITE);
		btnInHoaDon.setFocusPainted(false);
		pnThongTinHoaDon.add(btnInHoaDon);

		btnThanhToan = new JButton("THANH TO??N");
		btnThanhToan.setBounds(75, 450, 165, 40);
		btnThanhToan.setIcon(new ImageIcon("image/thanhtoan.png"));
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThanhToan.setBackground(new Color(131, 77, 30));
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFocusPainted(false);
		pnThongTinHoaDon.add(btnThanhToan);

		tableChonBan.clearSelection();
		tableChonBan.setDefaultEditor(Object.class, null);

		cmbChonPhong.setSelectedIndex(-1);
		
		docDuLieuDatabaseVaoTableBanDaDat();
		docDLCmbBan();
		
		NhanVien nv_temp = FrameDangNhap.getTaiKhoan().getTenTaiKhoan();
		NhanVien nv = dao_NV.getNhanVienTheoMa(nv_temp.getMaNV());
		
		txtNhanVien.setText(nv.getTenNV());
		
		tableChonBan.addMouseListener(new MouseListener() {
			

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int r = tableChonBan.getSelectedRow();
				txtTenBan.setText(tableModelChonBan.getValueAt(r, 1).toString());
				
				try {
					String maHD = "";
					List<HoaDon> listhd = dao_HD.getHDChuaThanhToan();
					for (HoaDon hd_temp : listhd) {
						if (hd_temp.getMaBan().getTenBan().equalsIgnoreCase(tableModelChonBan.getValueAt(r, 1).toString())) {
							maHD = hd_temp.getMaHD();
							System.out.println(hd_temp.getNgayDat());
						}
					}
					List<ChiTietHoaDon> list = dao_CTHD.getCTHDTheoMa(maHD);
					double tong = 0;
					for (ChiTietHoaDon ct : list) {
						tong+=(ct.getSoLuong()*ct.getMaNuoc().getGiaTien());
					}
					txtTienPhong.setText(""+tong);
					txtTienDV.setText(""+tong*8/100);
					txtThanhToan.setText(""+(tong+tong*8/100));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnXemCTHD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tenBan = txtTenBan.getText().trim();
				if (tenBan.equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Vui l??ng ch???n b??n c???n xem chi ti???t h??a ????n");
				} else {
					try {
						new FormHienThiCTHD(tenBan).setVisible(true);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnLamMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtTenBan.setText("");
				txtTienPhong.setText("");
				txtTienDV.setText("");
				txtThanhToan.setText("");
				
				tableChonBan.clearSelection();
				
				cmbChonPhong.setSelectedIndex(0);
			}
		});
		
		btnTimPhong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s = cmbChonPhong.getSelectedItem().toString();
				if (s.equalsIgnoreCase("")) {
					XoaDLTableBanDaDat();
					try {
						docDuLieuDatabaseVaoTableBanDaDat();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						XoaDLTableBanDaDat();
						List<Ban> list = dao_Ban.getAllBanDaDat();
						Ban ban = null;
						for (Ban b : list) {
							if (b.getTenBan().equalsIgnoreCase(s)) {
								ban = b;
								break;
							}
						}
						if (ban!=null) {
							tableModelChonBan.addRow(new Object[] { ban.getMaBan(), ban.getTenBan() });
						} else {
							try {
								XoaDLTableBanDaDat();
								docDuLieuDatabaseVaoTableBanDaDat();
							} catch (Exception e2) {
								// TODO: handle exception
								e2.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y b??n!");
							
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnThanhToan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					boolean tonTaiMangVe = false;
					for (int i = 0; i < tableModelChonBan.getRowCount(); i++) {
						if (tableModelChonBan.getValueAt(i, 0).toString().equalsIgnoreCase("B9999")) {
							tonTaiMangVe=true;
							break;
						}
					}
					
					if (tonTaiMangVe && !txtTenBan.getText().equalsIgnoreCase("Mua mang v???")) {
						JOptionPane.showMessageDialog(null, "C???n thanh to??n Mua mang v??? tr?????c!");
					} else {
						double thanhTien = Double.parseDouble(txtThanhToan.getText());
						HoaDon hd = null;
						List<HoaDon> listhd = dao_HD.getHDChuaThanhToan();
						for (HoaDon hd_temp : listhd) {
							if (hd_temp.getMaBan().getTenBan().equalsIgnoreCase(txtTenBan.getText())) {
								hd = hd_temp;
								break;
							}
						}
						
						if (dao_HD.thanhToan(hd.getMaHD(), thanhTien)) {
							JOptionPane.showMessageDialog(null, "Thanh to??n th??nh c??ng!");
							Ban b = hd.getMaBan();
							b.setTrangThai(false);
							dao_Ban.capnhatBan(b);
							
							txtTenBan.setText("");
							txtTienPhong.setText("");
							txtTienDV.setText("");
							txtThanhToan.setText("");
							
							FrameDatBan.XoaDLTableHoaDon();
							FrameDatBan.XoaDLTableBan();
							FrameDatBan.docDuLieuDatabaseVaoTableBan();
							FrameDatBan.XoaDLTableBanDaDat();
							FrameDatBan.docDuLieuDatabaseVaoTableBanDaDat();
							FrameDatBan.tableBanDaDat.clearSelection();
							FrameDatBan.tableListDV.clearSelection();
							FrameDatBan.tablePhongTrong.clearSelection();
							FrameDatBan.tableHoaDonDV.clearSelection();
							if (FrameDatBan.chkMangVe.isSelected()) {
								FrameDatBan.chkMangVe.setSelected(false);
							}
							FrameDatBan.lblBanDangChon.setText("");
							
							DefaultTableModel dm = (DefaultTableModel) tableChonBan.getModel();
							int rowCount = dm.getRowCount();
							// Remove rows one by one from the end of the table
							for (int i = rowCount - 1; i >= 0; i--) {
								dm.removeRow(i);
							}
							FrameDatBan.tablePhongTrong.enable(true);
							FrameDatBan.tableBanDaDat.enable(true);
							FrameDatBan.txtSoLuong.setText("1");
							
							docDuLieuDatabaseVaoTableBanDaDat();
							tableChonBan.clearSelection();
							
							cmbChonPhong.setSelectedIndex(0);
						}
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
				
		btnInHoaDon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r = tableChonBan.getSelectedRow();
				if (r == -1) {
					JOptionPane.showMessageDialog(null, "Vui ph??ng ch???n b??n c???n in h??a ????n!");
				} else {
					String tenBan = tableModelChonBan.getValueAt(r, 1).toString();
					String maHD = "";
					Date date_temp = null;
					try {
						List<HoaDon> list = dao_HD.getHDChuaThanhToan();
						for (HoaDon hd_temp : list) {
							if (hd_temp.getMaBan().getTenBan().equalsIgnoreCase(tenBan)) {
								maHD = hd_temp.getMaHD();
								date_temp = hd_temp.getNgayDat();
							}
						}
						
						NhanVien nv_temp = FrameDangNhap.getTaiKhoan().getTenTaiKhoan();
						NhanVien nv = dao_NV.getNhanVienTheoMa(nv_temp.getMaNV());
						
						new FrameHoaDonTinhTien(nv.getTenNV(), maHD, date_temp, tenBan).setVisible(true);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
			}
		});
		
		return pnlContentPane;
	}
	
	public static void docDuLieuDatabaseVaoTableBanDaDat() throws RemoteException {
		List<Ban> list = dao_Ban.getAllBanDaDat();

		for (Ban b : list) {
//			if (!b.getMaBan().equalsIgnoreCase("B9999")) {
				tableModelChonBan.addRow(new Object[] { b.getMaBan(), b.getTenBan() });
//			}
		}
	}
	public static void XoaDLTableBanDaDat() {
		DefaultTableModel md = (DefaultTableModel) tableChonBan.getModel();
		md.getDataVector().removeAllElements();
	}
	
	public static void docDLCmbBan() throws RemoteException {
		cmbChonPhong.removeAllItems();
		cmbChonPhong.addItem("");

		List<Ban> list = dao_Ban.getAllBanDaDat();
		for (Ban b : list) {
			cmbChonPhong.addItem(b.getTenBan());
		}
	}
}
