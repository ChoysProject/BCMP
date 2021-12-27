package jij;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import jij.crud.CrudLogin;
import jij.dto.CustomerInfo;

public class LoginSystem extends JPanel implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == login) {
			String loginId = id.getText();
			// ������
//			String password = pwd.getText();
			// �Ź���
			char[] chars = pwd.getPassword();
			String password = "";
			for(int i = 0; i < chars.length; i++) {
				password = password + String.valueOf(chars[i]);
			}
			
			CrudLogin crud = new CrudLogin();
			CustomerInfo customer = new CustomerInfo();
			customer.setCust_id(loginId);
			customer.setCust_pwd(password);
			CustomerInfo ci = crud.getId(customer);
			if(ci== null) System.out.println("ci is null");
			if(ci != null &&
				ci.getCust_id() != null &&
				ci.getCust_pwd() != null &&
				ci.getCust_id().equals(loginId) &&
				ci.getCust_pwd().equals(password)) {
				JOptionPane.showMessageDialog(this, loginId + "(��)�� �α��� ��");
				id.setText("");
				pwd.setText("");
				// ������ ���� �α��� ���� ���
				String title = loginId + "(��)�� �α��� ��";
				ts.setTitle(title);
				// TotalSystem�� ȭ���� �ٲ�
				ts.cl.show(ts.ttp, "bga");
				ts.cust_mn.setEnabled(true);
				ts.odm_mn.setEnabled(true);
				ts.logo_mn.setEnabled(true);
				ts.item_mn.setEnabled(true);
				ts.refund_mn.setEnabled(true);
				ts.notice_mn.setEnabled(true);
				
			}else {
				
				JOptionPane.showMessageDialog(this, "��й�ȣ�� Ȯ�����ּ���.");
			}
			
		}else if(obj == cancel) {
			id.setText("");
			pwd.setText("");
		}else if(obj == join) {
			
		}
	}

	JLabel lb_id, lb_pw;
	String[] lb_title = {"����", "��ȣ"};
	JTextField id;
	JPasswordField pwd;
	JButton login, cancel, join;
	String[] btn_title = {"�α���", "���"};
	JPanel idp, pwp, idpwp, btnp;
	JPanel center, south, centerDown, centerUp;
	Font font;
	TotalSystem ts;
	
	void makeLabel() {
		font = new Font("����ü", Font.BOLD, 20);
		lb_id = new JLabel(lb_title[0]);
		lb_id.setFont(font);
		lb_pw = new JLabel(lb_title[1]);
		lb_pw.setFont(font);
	}
	void makeTextField() {
		id = new JTextField(20);
		pwd = new JPasswordField(20);
	}
	void makeButton() {
		font = new Font("����ü", Font.BOLD, 20);
		login = new JButton(btn_title[0]);
		login.setFont(font);
		login.addActionListener(this);
		cancel = new JButton(btn_title[1]);
		cancel.setFont(font);
		cancel.addActionListener(this);
	}
	void makePanel() {
		center = new JPanel();
		south = new JPanel();
		centerDown = new JPanel();
		centerUp = new JPanel();
		center.setLayout(new GridLayout(2, 1));
		centerUp.add(lb_id);
		centerUp.add(id);
		centerDown.add(lb_pw);
		centerDown.add(pwd);

		center.add(centerUp);
		center.add(centerDown);
		south.add(login);
		south.add(cancel);
	}
	
	public LoginSystem(TotalSystem ts) throws HeadlessException {
		this.ts = ts;
		this.setLayout(new BorderLayout());
		
		makeLabel();
		makeTextField();
		makeButton();
		makePanel();
		
		add("Center", center);
		add("South", south);
	}
}