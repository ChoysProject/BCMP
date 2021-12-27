package cys;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Item_model extends AbstractTableModel {
	Object[][] data;	List<Item_info2> list;
	String[] col_title = {"상품번호", "이름","가격","색상","사이즈","브랜드","원산지","종류","재료","대상","구매일자"};
	public void setData(List<Item_info2> list) {
		this.list = list;
		Iterator it = this.list.iterator();
		data = new Object[list.size()][col_title.length];
		int row =0;
		while(it.hasNext()) {
			Item_info2 info = (Item_info2)it.next();
			data[row][0]=info.getItem_num();//상품이름
			data[row][1]=info.getItem_name();//상품이름
			data[row][2]=info.getItem_price();//상품가격
			data[row][3]=info.getItem_color();//상품설명
			data[row][4]=info.getItem_size();//원산지
			data[row][5]=info.getItem_brand();
			data[row][6]=info.getItem_from();
			data[row][7]=info.getItem_kind();
			data[row][8]=info.getItem_material();
			data[row][9]=info.getItem_target();
			data[row][10]=info.getItem_ymd();
			row++;
		}
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return col_title.length;
	}
	@Override
	public String getColumnName(int column) {
		return col_title[column];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

}
