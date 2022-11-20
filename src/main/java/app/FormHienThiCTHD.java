package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import dao.impl.DAOImpl_CTHD;
import dao.impl.DAOImpl_HoaDon;
import entity.ChiTietHoaDon;
import entity.HoaDon;

public class FormHienThiCTHD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3887157344579679842L;
	private static DAOImpl_CTHD dao_CTHD;
	private static DAOImpl_HoaDon dao_HD;
	private JLabel lblTieuDe;
	private static DefaultTableModel tableModelHoaDonDV;
	private static JTable tableHoaDonDV;
	public FormHienThiCTHD(String tenBan) throws RemoteException {
		
		dao_CTHD = new DAOImpl_CTHD();
		dao_HD = new DAOImpl_HoaDon();
		
		setTitle("CHI TIẾT HÓA ĐƠN");
		setSize(600,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel p = new JPanel(new BorderLayout());
		p.setBackground(new Color(252, 242, 217));
		this.add(p);
		
		JPanel pTop = new JPanel();
		pTop.setBackground(new Color(252, 242, 217));
		lblTieuDe = new JLabel(tenBan);
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		lblTieuDe.setForeground(new Color(131, 77, 30));
		pTop.add(lblTieuDe);
		
		String[] header = { "Mã đồ uống", "Tên đồ uống", "Số lượng", "Giá tiền", "Thành tiền" };
		tableModelHoaDonDV = new DefaultTableModel(header, 0);
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
		JTableHeader tableHeader = tableHoaDonDV.getTableHeader();
		tableHeader.setBackground(new Color(173, 119, 72));
		tableHeader.setForeground(new Color(255, 255, 255));
		tableHeader.setFont(new Font("Arial", Font.BOLD, 15));
		tableHeader.setPreferredSize(new Dimension(WIDTH, 40));
		tableHeader.setResizingAllowed(false);
		
		p.add(new JScrollPane(tableHoaDonDV),BorderLayout.CENTER);
		
		p.add(pTop, BorderLayout.NORTH);
		
		docDuLieuDatabaseVaoTableHD(tenBan);
	}
	
	public static void docDuLieuDatabaseVaoTableHD(String tenBan) throws RemoteException {
		String maHD = "";
		List<HoaDon> listhd = dao_HD.getHDChuaThanhToan();
		for (HoaDon hd_temp : listhd) {
			if (hd_temp.getMaBan().getTenBan().equalsIgnoreCase(tenBan)) {
				maHD = hd_temp.getMaHD();
			}
		}
		List<ChiTietHoaDon> list = dao_CTHD.getCTHDTheoMa(maHD);
		DecimalFormat df = new DecimalFormat("#,##0.0");
		for (ChiTietHoaDon cthd : list) {
			tableModelHoaDonDV.addRow(
					new Object[] { cthd.getMaNuoc().getMaNuoc(), cthd.getMaNuoc().getTenNuoc(), cthd.getSoLuong(),
							cthd.getMaNuoc().getGiaTien(), cthd.getMaNuoc().getGiaTien() * cthd.getSoLuong() });
		}
	}
	
	public static void XoaDLTableHoaDon() {
		DefaultTableModel md = (DefaultTableModel) tableHoaDonDV.getModel();
		md.getDataVector().removeAllElements();
	}
}
