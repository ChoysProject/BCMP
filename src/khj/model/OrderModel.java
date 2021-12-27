package khj.model;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import khj.dto.OrderDTO;

public class OrderModel extends AbstractTableModel {
	List<OrderDTO> list = null;
	String[] col_title = { "ID", "주문번호", "수량", "상품번호", "할부", "결제", "날짜", "총액", "쿠폰", "포인트" };
	Object[][] data;

	public void setData(List<OrderDTO> list) {
		this.list = list;
		Iterator it = this.list.iterator();
		data = new Object[list.size()][col_title.length];
		int row = 0;
		while (it.hasNext()) {
			OrderDTO od = (OrderDTO) it.next();
			data[row][0] = od.getOrder_id();
			data[row][1] = od.getOrder_number();
			data[row][2] = od.getOrder_count();
			data[row][3] = od.getOrder_product();
			data[row][4] = od.getOrder_installment();
			data[row][5] = od.getOrder_payment();
			data[row][6] = od.getOrder_date();
			data[row][7] = od.getOrder_total();
			data[row][8] = od.getOrder_coupon();
			data[row][9] = od.getOrder_point();
			row++;
		}
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return col_title[column];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return col_title.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return data[arg0][arg1];
	}
}