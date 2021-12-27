package jij.model;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import jij.dto.CustomerInfo;

public class CustomerModel extends AbstractTableModel {
	// DB��ȸ����� ���⿡ ����
	// ���̹�Ƽ���� ��ȸ����� List�� ������
	
	List<CustomerInfo> list;
	String[] col_name = {"id", "password", "���̸�", "���ּ�", "�� ��", "�������", "�̸���", "��ȭ��ȣ", "���", "����Ʈ", "����"};
	Object[][] data;
	
	public void setData(List<CustomerInfo> i) {
		list = i;
		Iterator it = i.iterator();
		data = new Object[i.size()][col_name.length];
		int row = 0;// �� ��ȣ
		while(it.hasNext()) {
			CustomerInfo ci = (CustomerInfo)it.next();
			data[row][0] = ci.getCust_id();
			data[row][1] = ci.getCust_pwd();
			data[row][2] = ci.getCust_name();
			data[row][3] = ci.getCust_addr();
			data[row][4] = ci.getCust_gender();
			data[row][5] = ci.getCust_birth();
			data[row][6] = ci.getCust_email();
			data[row][7] = ci.getCust_phone();
			data[row][8] = ci.getCust_rating();
			data[row][9] = ci.getCust_point();
			data[row][10] = ci.getCust_coupon();
			row++;//�� ����
		}
	}

	@Override
	public String getColumnName(int colnum) {// �÷� ���� ����(����)
		return col_name[colnum];
	}
	
	@Override
	public int getColumnCount() {// �� ����
		return col_name.length;
	}

	@Override
	public int getRowCount() {// �� ����
		return list.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}
}
