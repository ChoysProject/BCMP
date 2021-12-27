package mjh;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ReturnModel extends AbstractTableModel {
List<ReturnInfo> list;
String  [] colTitle= {"ȯ�� ��ȣ","�� ���̵�","ȯ�� ���","ȯ�ҳ�¥","ȯ�� ����"};
Object [][] data;
public void setData(List<ReturnInfo> list) {
	this.list=list;
	data=new Object[list.size()][colTitle.length];
	Iterator it =list.iterator();
	int row=0;
	while(it.hasNext()) {
		ReturnInfo ri = (ReturnInfo)it.next();
		data[row][0]=ri.getReturn_number();
		data[row][1]=ri.getCust_id();
		data[row][2]=ri.getReturn_method();
		data[row][3]=ri.getReturn_date();
		data[row][4]=ri.getReturn_reason();
		row++;
	}
}


@Override
public String getColumnName(int arg0) {
	return colTitle[arg0];
}


@Override
public int getColumnCount() {
	return colTitle.length;
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
