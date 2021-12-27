package jij;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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

import jij.crud.CrudCustomer;
import jij.dto.CustomerInfo;
import jij.model.CustomerModel;


public class Customer extends JPanel implements ItemListener, ActionListener, MouseListener{
	
	public void itemStateChanged(ItemEvent e) {
//		Object obj = e.getSource();
//		if(obj == jcos[2]) {
//			jcos[3].removeAllItems();
//			int m = jcos[2].getSelectedIndex() + 1;
//			switch(m) {
//			case 2:
//				for(int i = 1; i <= 28; i++) {
//					jcos[3].addItem(i);
//				}
//				break;
//			case 4:
//			case 6:
//			case 9:
//			case 11:
//				for(int i = 1; i <= 30; i++) {
//					jcos[3].addItem(i);
//				}
//				break;
//			default : 
//				for(int i = 1; i <= 31; i++) {
//					jcos[3].addItem(i);
//				}
//				break;
//			}
//		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// 클릭한 행 찾기
		int row = table.getSelectedRow();
		String id = (String)table.getValueAt(row, 0);
		String pwd = (String)table.getValueAt(row, 1);
		String name = (String)table.getValueAt(row, 2);
		String addr = (String)table.getValueAt(row, 3);
		String gender = (String)table.getValueAt(row, 4);
		String birth = (String)table.getValueAt(row, 5);
		String email = (String)table.getValueAt(row, 6);
		String phone = (String)table.getValueAt(row, 7);
		String rating = (String)table.getValueAt(row, 8);
		int point = (int)table.getValueAt(row, 9);
		String coupon = (String)table.getValueAt(row, 10);
//		System.out.println(id + ", " + name  + ", " + addr + ", " + phone + ", " + gender + ", " + point);
		// 클릭한 열 찾기
		int col = table.getSelectedColumn();
		txt[0].setText(id);
		txt[1].setText(pwd);
		txt[2].setText(name);
		txt[3].setText(addr);
		if(gender.equals("M")) {
			male.setSelected(true);
		}else if(gender.equals("F")) {
			female.setSelected(true);
		}
//		jcos[0].setSelectedIndex();
//		jcos[1].setSelectedIndex();
//		jcos[2].setSelectedIndex();
		txt[4].setText(email);
		// phone을 3개로 나눔
		String p1 = phone.substring(0, 3);
		String p2 = phone.substring(3, 7);
		String p3 = phone.substring(7, 11);
//		System.out.println(p1 + ", " + p2 + ", " + p3);
		txt[5].setText(p1);
		txt[6].setText(p2);
		txt[7].setText(p3);
		txt[8].setText(rating);
		txt[9].setText(point + "");
		txt[10].setText(coupon);
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if(obj == btns[0]) {// 수정
			int r = JOptionPane.showConfirmDialog(this, "정말 이대로 수정하시겠습니까?");
			if(r == JOptionPane.OK_OPTION) {
				CrudCustomer crud = new CrudCustomer();
				CustomerInfo c = new CustomerInfo();
				
				String id = txt[0].getText();
				String pwd = txt[1].getText();
				String name = txt[2].getText();
				String addr = txt[3].getText();
				String gender = "";
				if(male.isSelected()) {
					gender = "M";
				}else if(female.isSelected()) {
					gender = "F";
				}
				String birth =
						jcos[0].getSelectedItem() + "/" + 
						jcos[1].getSelectedItem() + "/" + 
						jcos[2].getSelectedItem();
				String email = txt[4].getText();
				String phone = txt[5].getText() + txt[6].getText() + txt[7].getText();
				String rating = txt[8].getText();
				Integer point = txt[9].getColumns();
				String coupon = txt[10].getText();
				c.setCust_id(id);
				c.setCust_pwd(pwd);
				c.setCust_name(name);
				c.setCust_addr(addr);
				c.setCust_gender(gender);
				c.setCust_birth(birth);
				c.setCust_email(email);
				c.setCust_phone(phone);
				c.setCust_rating(rating);
				c.setCust_point(point);
				c.setCust_coupon(coupon);
				int result = crud.updateCustomer(c);
				if(result > 0) {
					JOptionPane.showMessageDialog(this, "수정 완료");
				}else {
					JOptionPane.showMessageDialog(this, "수정 실패");
				}
			}
		}else if(obj == btns[1]) {// 탈퇴
			int r = JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?");
			if(r == JOptionPane.OK_OPTION) {
				CrudCustomer crud = new CrudCustomer();
				String id = txt[0].getText();
				String pwd = txt[1].getText();
				String name = txt[2].getText();
				String addr = txt[3].getText();
				String gender = "";
				if(male.isSelected()) {
					gender = "M";
				}else if(female.isSelected()) {
					gender = "F";
				}
				String birth =
						jcos[0].getSelectedItem() + "/" + 
						jcos[1].getSelectedItem() + "/" + 
						jcos[2].getSelectedItem();
				System.out.println(birth);
				String email = txt[4].getText();
				String phone = txt[5].getText() + txt[6].getText() + txt[7].getText();
				String rating = txt[8].getText();
				String point = txt[9].getText();
				String coupon = txt[10].getText();
				HashMap<String, Object> map = new HashMap<String, Object>();
				if(!name.equals("")) {
					map.put("cust_id", id);
				}
				if(!name.equals("")) {
					map.put("cust_pwd", pwd);
				}
				if(!name.equals("")) {
					map.put("cust_name", name);
				}
				if(!addr.equals("")) {
					map.put("cust_addr", addr);
				}
				if(!gender.equals("")) {
					map.put("cust_gender", gender);
				}
				if(!birth.equals("")) {
					map.put("cust_birth", birth);
				}
				if(!email.equals("")) {
					map.put("cust_email", email);
				}
				if(!phone.equals("")) {
					map.put("cust_phone", phone);
				}
				if(!phone.equals("")) {
					map.put("cust_rating", rating);
				}
				if(!phone.equals("")) {
					map.put("cust_point", point);
				}
				if(!phone.equals("")) {
					map.put("cust_coupon", coupon);
				}
				int result = crud.deleteCustomer(map);
				if(result > 0) {
					JOptionPane.showMessageDialog(this, "삭제 완료");
				}else {
					JOptionPane.showMessageDialog(this, "삭제 실패");
				}
			}// if End
		}else if(obj == btns[2]) {// 조회
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(!txt[0].getText().equals("")) {
				// 조건처리
				map.put("cust_id", txt[0].getText());
			}
			if(!txt[1].getText().equals("")) {
				map.put("cust_name", txt[1].getText());
			}
			if(!txt[2].getText().equals("")) {
				map.put("cust_addr", txt[2].getText());
			}
			String phone = txt[3].getText() + txt[4].getText() + txt[5].getText();
			if(!phone.equals("")) {
				map.put("cust_phone", phone);
			}
			String gender = "";
			if(male.isSelected()) {
				gender = "M";
			}else if(female.isSelected()) {
				gender = "F";
			}
			if(!gender.equals("")) {// 선택된 고객 성별과 비어있는 문자열을 비교
				map.put("cust_gender", gender);
			}
			CrudCustomer crud = new CrudCustomer();
			List<CustomerInfo> list = crud.selectInfo(map);
			CustomerModel cm = new CustomerModel();
			cm.setData(list);
			table.setModel(cm);
			txt[0].setEnabled(false);// 아이디 비활성화
			txt[8].setEnabled(false);// 등급 비활성화
			txt[9].setEnabled(false);// 포인트 비활성화
		}else if(obj == btns[3]) {// 지우기
			txt[0].setText(" "); txt[0].setText("");
			txt[1].setText(" "); txt[1].setText("");
			txt[2].setText(" "); txt[2].setText("");
			txt[3].setText(" "); txt[3].setText("");
			txt[4].setText(" "); txt[4].setText("");
			txt[5].setText(" "); txt[5].setText("");
			txt[6].setText(" "); txt[6].setText("");
			txt[7].setText(" "); txt[7].setText("");
			txt[8].setText(" "); txt[8].setText("");
			txt[9].setText(" "); txt[9].setText("");
			txt[0].setEnabled(true);// 아이디 활성화
			txt[8].setEnabled(true);// 등급 활성화
			txt[9].setEnabled(true);// 포인트 활성화
		}else if(obj == calendar) {//달력
			new CalendarBySwing(this);
		}
	}

	JLabel[] lbl;
		String[] lbl_title = {"I D", "암 호", "이 름", "주 소", "성 별", "생년월일", "이메일",
							"전화번호", "등 급", "포인트", "쿠 폰"};
	JTextField[] txt;
	JRadioButton male,female;
	ButtonGroup group;
	JComboBox[] jcos;
	JButton[] btns;
		String[] btn_title= {"수 정", "탈 퇴", "조 회", "비우기", "달력"};
	JButton calendar;
	Font font;
	JPanel[] pnls;
	JPanel south, west, center;
	JTable table;
	JScrollPane jsp;
	TotalSystem ts;
	
	void makePanel() {
		center = new JPanel(new GridLayout(2, 1));
		west = new JPanel();
		west.setLayout(new GridLayout(10, 1));
		pnls = new JPanel[10];
		table = new JTable();
		table.addMouseListener(this);
		jsp = new JScrollPane(table);
		center.add(west);
		center.add(jsp);
		
		for(int i = 0; i < pnls.length; i++) {
			pnls[i] = new JPanel();
			switch(i) {
			case 0: pnls[i].add(lbl[i]);// 아이디
					pnls[i].add(txt[0]);break;
			case 1: pnls[i].add(lbl[i]);// 비번
					pnls[i].add(txt[1]);break;
			case 2: pnls[i].add(lbl[i]);// 이름
					pnls[i].add(txt[2]);break;
			case 3: pnls[i].add(lbl[i]);// 주소
					pnls[i].add(txt[3]);break;
			case 4: pnls[i].add(lbl[i]);// 성별
					pnls[i].add(male);
					pnls[i].add(female);break;
			case 5: pnls[i].add(lbl[i]);// 생일
					pnls[i].add(jcos[0]);
					pnls[i].add(jcos[1]);
					pnls[i].add(jcos[2]);
					pnls[i].add(calendar);
					break;
			case 6: pnls[i].add(lbl[i]);// 이메일
					pnls[i].add(txt[4]);break;
			case 7: pnls[i].add(lbl[i]);// 전화번호
					pnls[i].add(txt[5]);
					pnls[i].add(txt[6]);
					pnls[i].add(txt[7]);break;
			case 8: pnls[i].add(lbl[i]);// 등급
					pnls[i].add(txt[8]);break;
			case 9: pnls[i].add(lbl[i]);// 포인트
					pnls[i].add(txt[9]);break;
			case 10: pnls[i].add(lbl[i]);// 쿠폰
					pnls[i].add(txt[10]);break;
			}
			west.add(pnls[i]);
		}
		south = new JPanel();
		for(int i=0; i<btns.length; i++) {
			south.add(btns[i]);
		}
	}
	void makeButton() {
		btns = new JButton[4];
		calendar = new JButton("달력");
		calendar.addActionListener(this);
		for(int i=0; i<btns.length; i++) {
			btns[i] = new JButton(btn_title[i]);
			btns[i].addActionListener(this);
		}
	}
	void makeRadio() {
		group = new ButtonGroup();
		male = new JRadioButton("남자", false);
		female = new JRadioButton("여자", false);
		group.add(male);
		group.add(female);
	}
	void makeLabel() {
		font = new Font("굴림체",Font.BOLD,20);
		lbl = new JLabel[lbl_title.length];
		for(int i=0; i<lbl.length; i++) {
			lbl[i] = new JLabel(lbl_title[i]);
			lbl[i].setFont(font);
		}
	}
	void makeTextField() {
		txt = new JTextField[11];
		for(int i=0; i<txt.length; i++) {
			switch(i) {
			case 5: txt[i] = new JTextField(4); break;
			case 6: 
			case 7: txt[i] = new JTextField(7); break;
			default : txt[i] = new JTextField(25);
			}
		}
	}
	void makeComboBox() {
		font = new Font("굴림체", Font.BOLD, 20);
		jcos = new JComboBox[3];
		for(int i = 0; i < jcos.length; i++) {
			switch(i){
			case 0: jcos[i] = new JComboBox();
				for(int j = 2020; j >= 1920; j--) {
					jcos[i].addItem(j);
				}
				break;
			case 1: jcos[i] = new JComboBox();
				for(int j = 1; j <= 12; j++) {
					jcos[i].addItem(j);
				}
				break;
			case 2: jcos[i] = new JComboBox();
				jcos[i].addItem(1);
				break;
			}
			jcos[i].setFont(font);
			jcos[i].addItemListener(this);
		}
	}
		
	public Customer(TotalSystem ts) throws HeadlessException {
		this.ts = ts;
		this.setLayout(new BorderLayout());
		
		table = new JTable();
		table.addMouseListener(this);
		
		makeTextField();
		makeLabel();
		makeRadio();
		makeButton();
		makeComboBox();
		makePanel();
		
		add("Center", center);
		add("West", west);
		add("South", south);
		
		setSize(900,700);
		setVisible(true);
	}
}
	