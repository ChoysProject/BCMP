package cys;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Qna_model extends AbstractTableModel {
	Object[][] data;	List<Qna_2> list;
	String[] col_title = {"이름", "분류","제목","내용"};
	public void setData(List<Qna_2> list) {
		this.list = list;
		Iterator it = this.list.iterator();
		data = new Object[list.size()][col_title.length];
		int row =0;
		while(it.hasNext()) {
			Qna_2 qna = (Qna_2)it.next();
			data[row][0]=qna.getNotice_name();
			data[row][1]=qna.getNotice_category();
			data[row][2]=qna.getNotice_title();
			data[row][3]=qna.getNotice_content();
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
