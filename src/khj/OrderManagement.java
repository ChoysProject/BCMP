package khj;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import khj.crud.CrudOrder;
import khj.dto.OrderDTO;
import khj.model.OrderModel;

public class OrderManagement extends JPanel implements ActionListener, ItemListener, MouseListener {
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow(), col = table.getSelectedColumn();
		textField[0].setText((String) table.getValueAt(row, 0));
		textField[1].setText(String.valueOf(table.getValueAt(row, 1)));
		count.setSelectedItem(table.getValueAt(row, 2));
		textField[2].setText(String.valueOf(table.getValueAt(row, 3)));
		installment.setSelectedItem(table.getValueAt(row, 4));
		String payment = (String) table.getValueAt(row, 5);
		if (payment.equals("card"))
			this.payment[0].setSelected(true);
		else if (payment.equals("cash"))
			this.payment[1].setSelected(true);
		else if (payment.equals("account"))
			this.payment[2].setSelected(true);
		String date = (String.valueOf(table.getValueAt(row, 6)));
		String[] ymd = date.split("/");
		year.setSelectedItem(Integer.parseInt(ymd[0]));
		month.setSelectedItem(Integer.parseInt(ymd[1]));
		day.removeAllItems();
		day.addItem("일");
		switch (Integer.parseInt(ymd[1])) {
		case 2:
			for (int i = 1; i <= 28; i++)
				day.addItem(i);
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			for (int i = 1; i <= 30; i++)
				day.addItem(i);
			break;
		default:
			for (int i = 1; i <= 31; i++)
				day.addItem(i);
		}
		day.setSelectedItem(Integer.parseInt(ymd[2]));
		textField[3].setText(String.valueOf(table.getValueAt(row, 7)));
		String coupon = String.valueOf(table.getValueAt(row, 8));
		this.coupon.setSelectedItem(Integer.parseInt(coupon));
		String point = String.valueOf(table.getValueAt(row, 9));
		this.point.setSelectedItem(Integer.parseInt(point));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj == month) {
			day.removeAllItems();
			day.addItem("일");
			int month = (int) this.month.getSelectedIndex();
			switch (month) {
			case 0:
				return;
			case 2:
				for (int i = 1; i <= 28; i++)
					day.addItem(i);
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				for (int i = 1; i <= 30; i++)
					day.addItem(i);
				break;
			default:
				for (int i = 1; i <= 31; i++)
					day.addItem(i);
			}
		} else if (obj == payment[0] && !installment.isEnabled())
			installment.setEnabled(true);
		else if ((obj == payment[1] || obj == payment[2]) && (payment[1].isSelected() || payment[2].isSelected())
				&& installment.isEnabled()) {
			installment.setSelectedIndex(0);
			installment.setEnabled(false);
			JOptionPane.showMessageDialog(this, "현금 및 계좌 이체는 할부 개월을 선택하실 수 없습니다.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object obj = arg0.getSource();
		if (obj == button[0]) {
			if (nullCheck()) {
				JOptionPane.showMessageDialog(this, "데이터를 모두 입력해주세요.");
				return;
			}
			int confirm = JOptionPane.showConfirmDialog(this, "등록 하겠습니까?");
			if (confirm == JOptionPane.OK_OPTION) {
				CrudOrder co = new CrudOrder();
				String id = textField[0].getText();
				String number = textField[1].getText();
				int check = co.duplicateCheck(number);
				if (check != 0) {
					JOptionPane.showMessageDialog(this, "이미 등록되어 있는 주문 번호입니다.");
					return;
				}
				String count = "";
				if (this.count.getSelectedIndex() != 0)
					count = String.valueOf(this.count.getSelectedItem());
				String product = textField[2].getText();
				String installment = "";
				if (this.installment.getSelectedIndex() != 0)
					installment = String.valueOf(this.installment.getSelectedItem());
				String payment = "";
				if (this.payment[0].isSelected())
					payment = "card";
				else if (this.payment[1].isSelected())
					payment = "cash";
				else if (this.payment[2].isSelected())
					payment = "account";
				String date = "";
				if (year.getSelectedIndex() != 0 && month.getSelectedIndex() != 0 && day.getSelectedIndex() != 0)
					date = year.getSelectedItem() + "/" + month.getSelectedItem() + "/" + day.getSelectedItem();
				String total = textField[3].getText();
				String coupon = "";
				if (this.coupon.getSelectedIndex() != 0)
					coupon = String.valueOf(this.coupon.getSelectedItem());
				String point = "";
				if (this.point.getSelectedIndex() != 0)
					point = String.valueOf(this.point.getSelectedItem());
				OrderDTO od = new OrderDTO();
				od.setOrder_id(id);
				od.setOrder_number(Integer.parseInt(number));
				od.setOrder_count(Integer.parseInt(count));
				od.setOrder_product(Integer.parseInt(product));
				if (!installment.equals(""))
					od.setOrder_installment(Integer.parseInt(installment));
				od.setOrder_payment(payment);
				od.setOrder_date(date);
				od.setOrder_total(Integer.parseInt(total));
				if (!coupon.equals(""))
					od.setOrder_coupon(Integer.parseInt(coupon));
				if (!point.equals(""))
					od.setOrder_point(Integer.parseInt(point));
				int result = co.insertOrder(od);
				if (result > 0)
					JOptionPane.showMessageDialog(this, "등록 되었습니다.");
				else
					JOptionPane.showMessageDialog(this, "문제가 발생했습니다.");
			}
		} else if (obj == button[1]) {
			int confirm = JOptionPane.showConfirmDialog(this, "삭제 하겠습니까?");
			if (confirm == JOptionPane.OK_OPTION) {
				String id = textField[0].getText();
				String number = textField[1].getText();
				String count = "";
				if (this.count.getSelectedIndex() != 0)
					count = String.valueOf(this.count.getSelectedItem());
				String product = textField[2].getText();
				String installment = "";
				if (this.installment.getSelectedIndex() != 0)
					installment = String.valueOf(this.installment.getSelectedItem());
				String payment = "";
				if (this.payment[0].isSelected())
					payment = "card";
				else if (this.payment[1].isSelected())
					payment = "cash";
				else if (this.payment[2].isSelected())
					payment = "account";
				String date = "";
				if (year.getSelectedIndex() != 0 && month.getSelectedIndex() != 0 && day.getSelectedIndex() != 0)
					date = year.getSelectedItem() + "/" + month.getSelectedItem() + "/" + day.getSelectedItem();
				String total = textField[3].getText();
				String coupon = "";
				if (this.coupon.getSelectedIndex() != 0)
					coupon = String.valueOf(this.coupon.getSelectedItem());
				String point = "";
				if (this.point.getSelectedIndex() != 0)
					point = String.valueOf(this.point.getSelectedItem());
				HashMap<String, String> map = new HashMap<String, String>();
				if (!id.equals(""))
					map.put("order_id", id);
				if (!number.equals(""))
					map.put("order_number", number);
				if (!count.equals(""))
					map.put("order_count", count);
				if (!product.equals(""))
					map.put("order_product", product);
				if (!installment.equals(""))
					map.put("order_installment", installment);
				if (!payment.equals(""))
					map.put("order_payment", payment);
				if (!date.equals(""))
					map.put("order_date", date);
				if (!total.equals(""))
					map.put("order_total", total);
				if (!coupon.equals(""))
					map.put("order_coupon", coupon);
				if (!point.equals(""))
					map.put("order_point", point);
				CrudOrder co = new CrudOrder();
				int result = co.deleteOrder(map);
				if (result > 0)
					JOptionPane.showMessageDialog(this, "삭제 되었습니다.");
				else
					JOptionPane.showMessageDialog(this, "문제가 발생했습니다.");
			}
		} else if (obj == button[2]) {
			if (nullCheck()) {
				JOptionPane.showMessageDialog(this, "데이터를 모두 입력해주세요.");
				return;
			}
			int confirm = JOptionPane.showConfirmDialog(this, "수정 하겠습니까?");
			if (confirm == JOptionPane.OK_OPTION) {
				String id = textField[0].getText();
				String number = textField[1].getText();
				String count = "";
				if (this.count.getSelectedIndex() != 0)
					count = String.valueOf(this.count.getSelectedItem());
				String product = textField[2].getText();
				String installment = "";
				if (this.installment.getSelectedIndex() != 0)
					installment = String.valueOf(this.installment.getSelectedItem());
				String payment = "";
				if (this.payment[0].isSelected())
					payment = "card";
				else if (this.payment[1].isSelected())
					payment = "cash";
				else if (this.payment[2].isSelected())
					payment = "account";
				String date = "";
				if (year.getSelectedIndex() != 0 && month.getSelectedIndex() != 0 && day.getSelectedIndex() != 0)
					date = year.getSelectedItem() + "/" + month.getSelectedItem() + "/" + day.getSelectedItem();
				String total = textField[3].getText();
				String coupon = "";
				if (this.coupon.getSelectedIndex() != 0)
					coupon = String.valueOf(this.coupon.getSelectedItem());
				String point = "";
				if (this.point.getSelectedIndex() != 0)
					point = String.valueOf(this.point.getSelectedItem());
				OrderDTO od = new OrderDTO();
				od.setOrder_id(id);
				od.setOrder_number(Integer.parseInt(number));
				od.setOrder_count(Integer.parseInt(count));
				od.setOrder_product(Integer.parseInt(product));
				if (!installment.equals(""))
					od.setOrder_installment(Integer.parseInt(installment));
				od.setOrder_payment(payment);
				od.setOrder_date(date);
				od.setOrder_total(Integer.parseInt(total));
				if (!coupon.equals(""))
					od.setOrder_coupon(Integer.parseInt(coupon));
				if (!point.equals(""))
					od.setOrder_point(Integer.parseInt(point));
				CrudOrder co = new CrudOrder();
				int result = co.updateOrder(od);
				if (result > 0)
					JOptionPane.showMessageDialog(this, "수정 되었습니다.");
				else
					JOptionPane.showMessageDialog(this, "문제가 발생했습니다.");
			}
		} else if (obj == button[3]) {
			String id = textField[0].getText();
			String number = textField[1].getText();
			String count = "";
			if (this.count.getSelectedIndex() != 0)
				count = String.valueOf(this.count.getSelectedItem());
			String product = textField[2].getText();
			String installment = "";
			if (this.installment.getSelectedIndex() != 0)
				installment = String.valueOf(this.installment.getSelectedItem());
			String payment = "";
			if (this.payment[0].isSelected())
				payment = "card";
			else if (this.payment[1].isSelected())
				payment = "cash";
			else if (this.payment[2].isSelected())
				payment = "account";
			String date = "";
			if (year.getSelectedIndex() != 0 && month.getSelectedIndex() != 0 && day.getSelectedIndex() != 0)
				date = year.getSelectedItem() + "/" + month.getSelectedItem() + "/" + day.getSelectedItem();
			String total = textField[3].getText();
			String coupon = "";
			if (this.coupon.getSelectedIndex() != 0)
				coupon = String.valueOf(this.coupon.getSelectedItem());
			String point = "";
			if (this.point.getSelectedIndex() != 0)
				point = String.valueOf(this.point.getSelectedItem());
			HashMap<String, String> map = new HashMap<String, String>();
			if (!id.equals(""))
				map.put("order_id", id);
			if (!number.equals(""))
				map.put("order_number", number);
			if (!count.equals(""))
				map.put("order_count", count);
			if (!product.equals(""))
				map.put("order_product", product);
			if (!installment.equals(""))
				map.put("order_installment", installment);
			if (!payment.equals(""))
				map.put("order_payment", payment);
			if (!date.equals(""))
				map.put("order_date", date);
			if (!total.equals(""))
				map.put("order_total", total);
			if (!coupon.equals(""))
				map.put("order_coupon", coupon);
			if (!point.equals(""))
				map.put("order_point", point);
			CrudOrder co = new CrudOrder();
			List<OrderDTO> list = co.getOrderDTO(map);
			OrderModel om = new OrderModel();
			om.setData(list);
			table.setModel(om);
			JOptionPane.showMessageDialog(this, list.size() + "건 검색 되었습니다.");
		} else if (obj == button[4]) {
			CrudOrder co = new CrudOrder();
			List<OrderDTO> list = co.allOrder();
			OrderModel om = new OrderModel();
			om.setData(list);
			table.setModel(om);
			JOptionPane.showMessageDialog(this, "총 " + list.size() + "건 검색 되었습니다.");
		} else if (obj == button[5]) {
			for (int i = 0; i < textField.length; i++) {
				textField[i].setText(null);
				count.setSelectedIndex(0);
				installment.setEnabled(true);
				installment.setSelectedIndex(0);
				coupon.setSelectedIndex(0);
				point.setSelectedIndex(0);
				year.setSelectedIndex(0);
				month.setSelectedIndex(0);
				day.setSelectedIndex(0);
				paymentGroup.clearSelection();
			}
		} else if (obj == calender)
			new CalendarBySwing(this);
	}

	private boolean nullCheck() {
		boolean flag = false;
		for (int i = 0; i < textField.length; i++) {
			if (textField[i].getText().equals(""))
				flag = true;
		}
		if (count.getSelectedIndex() == 0) {
			flag = true;
		}
		if (!payment[0].isSelected() && !payment[1].isSelected() && !payment[2].isSelected()) {
			flag = true;
		}
		if (year.getSelectedIndex() == 0 || month.getSelectedIndex() == 0 || day.getSelectedIndex() == 0) {
			flag = true;
		}
		return flag;
	}

	Font font = new Font("굴림체", Font.BOLD, 13);
	JButton[] button;
	JButton calender;
	String[] btn_title = { "등 록", "삭 제", "수 정", "검 색", "전체 검색", "지우기" };
	JComboBox count, installment, coupon, point, year, month, day;
	JLabel[] label, sublabel;
	String[] lbl_title = { "ID", "주문 번호", "주문 수량", "상품 번호", "할부", "결제 방법", "주문 날짜", "주문 총액", "쿠폰 사용 여부", "포인트 사용 여부" };
	String[] sublbl_title = { "개", "개월", "원", "％" };
	JRadioButton[] payment;
	ButtonGroup paymentGroup;
	String[] radio_title = { "카드", "현금", "계좌 이체" };
	String[] confirmCheck = { "예", "아니오" };
	JTextField[] textField;
	JPanel center, south, east;
	JPanel[] part;
	JTable table;
	JScrollPane sp;

	void makeButton() {
		button = new JButton[btn_title.length];
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton(btn_title[i]);
			button[i].addActionListener(this);
		}
		calender = new JButton("달력");
		calender.addActionListener(this);
	}

	void makeLabel() {
		label = new JLabel[lbl_title.length];
		for (int i = 0; i < label.length; i++) {
			label[i] = new JLabel(lbl_title[i]);
			label[i].setFont(font);
		}
		sublabel = new JLabel[sublbl_title.length];
		for (int i = 0; i < sublabel.length; i++) {
			sublabel[i] = new JLabel(sublbl_title[i]);
			sublabel[i].setFont(font);
		}
	}

	void makeTextField() {
		textField = new JTextField[4];
		int[] fieldSize = { 10, 10, 10, 7 };
		for (int i = 0; i < textField.length; i++)
			textField[i] = new JTextField(fieldSize[i]);
		textField[3].setHorizontalAlignment(JTextField.RIGHT);
	}

	void makeComboBax() {
		count = new JComboBox();
		installment = new JComboBox();
		year = new JComboBox();
		month = new JComboBox();
		day = new JComboBox();
		coupon = new JComboBox();
		point = new JComboBox();
		count.addItem("수량");
		installment.addItem("선택");
		year.addItem("년");
		month.addItem("월");
		day.addItem("일");
		coupon.addItem("선택");
		point.addItem("선택");
		for (int i = 1; i <= 30; i++)
			count.addItem(i);
		for (int i = 3; i <= 24; i++)
			installment.addItem(i);
		for (int i = 1; i <= 6; i++)
			coupon.addItem(i * 5);
		for (int i = 1; i <= 50; i++)
			point.addItem(i * 100);
		for (int i = 2020; i >= 1960; i--)
			year.addItem(i);
		for (int i = 1; i <= 12; i++)
			month.addItem(i);
		month.addItemListener(this);
		count.setSelectedIndex(0);
		installment.setSelectedIndex(0);
		year.setSelectedIndex(0);
		month.setSelectedIndex(0);
		((JLabel) count.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((JLabel) installment.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((JLabel) coupon.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((JLabel) point.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((JLabel) year.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((JLabel) month.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((JLabel) day.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
	}

	void makeRadioButton() {
		payment = new JRadioButton[radio_title.length];
		paymentGroup = new ButtonGroup();
		for (int i = 0; i < payment.length; i++) {
			payment[i] = new JRadioButton(radio_title[i]);
			payment[i].setFont(font);
			paymentGroup.add(payment[i]);
			payment[i].addItemListener(this);
		}
	}

	void makePanel() {
		center = new JPanel(new GridLayout(10, 1));
		part = new JPanel[10];
		for (int i = 0; i < part.length; i++) {
			part[i] = new JPanel();
			part[i].add(label[i]);
			center.add(part[i]);
			switch (i) {
			case 0:
				part[0].add(textField[0]);
				break;
			case 1:
				part[1].add(textField[1]);
				break;
			case 2:
				part[2].add(count);
				part[2].add(sublabel[0]);
				break;
			case 3:
				part[3].add(textField[2]);
				break;
			case 4:
				part[4].add(installment);
				part[4].add(sublabel[1]);
				break;
			case 5:
				part[5].add(payment[0]);
				part[5].add(payment[1]);
				part[5].add(payment[2]);
				break;
			case 6:
				part[6].add(year);
				part[6].add(month);
				part[6].add(day);
				part[6].add(calender);
				break;
			case 7:
				part[7].add(textField[3]);
				part[7].add(sublabel[2]);
				break;
			case 8:
				part[8].add(coupon);
				part[8].add(sublabel[3]);
				break;
			case 9:
				part[9].add(point);
			}
		}
		south = new JPanel();
		for (int i = 0; i < btn_title.length; i++)
			south.add(button[i]);
		east = new JPanel();
		east.add(sp);
	}

	void makeTable() {
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.addMouseListener(this);
		sp = new JScrollPane(table);
	}

	public OrderManagement() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		makeTable();
		makeButton();
		makeLabel();
		makeTextField();
		makeComboBax();
		makeRadioButton();
		makePanel();
		sp.setPreferredSize(new Dimension(580, 300));
		this.add("Center", center);
		this.add("South", south);
		this.add("East", east);
		this.setVisible(true);
	}
}