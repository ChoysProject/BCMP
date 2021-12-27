package jij;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import cys.Item_info;
import cys.Qna;
import khj.OrderManagement;
import mjh.ReturnSystem;

class ClockMenu extends JLabel implements Runnable{
	Thread timer;
	TotalSystem main;
	JLabel text;
	ClockMenu(TotalSystem main){
		this.main=main;
		this.setText(clockText());//�޴��� ����
		text=new JLabel();
		this.setFont(new Font("����ü",Font.BOLD,20));
		this.add(text);
		timer = new Thread(ClockMenu.this);
		timer.start();
	}
	String clockText() {
		Calendar c = Calendar.getInstance();
		int hour=c.get(Calendar.HOUR_OF_DAY);
		int min =c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		String hourTxt="                                                                                                                                                                       ����ð� : "+Integer.toString(hour);
		if(hour<10)hourTxt="0"+hourTxt;
		hourTxt=hourTxt+":";
		if(min<10)hourTxt=hourTxt+"0";
		hourTxt=hourTxt+min+":";
		if(sec<10) hourTxt=hourTxt+"0";
		hourTxt=hourTxt+sec;
		return hourTxt;
	}
	@Override
	public void run() {
		while(true) {
			try {
			Thread.sleep(1000);
		}catch(Exception e) {}
			this.setText(clockText());//1�ʸ��� ����
			main.setTitle("BCMP �����ý���"+clockText());
		}
	}
}


class BackGroundImage extends JPanel {
	final String PATH = "src\\jij\\�ΰ�4.png";
	Image image;
	BackGroundImage(){
		image = Toolkit.getDefaultToolkit().getImage(PATH);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}

public class TotalSystem extends JFrame implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == cust_item_mn) {
			cl.show(ttp, "cust");
				this.setSize(850,750);
				odm_mn.setForeground(Color.BLACK);
				cust_mn.setForeground(Color.RED);
				item_mn.setForeground(Color.BLACK);
				refund_mn.setForeground(Color.BLACK);
				notice_mn.setForeground(Color.BLACK);
		}else if(obj == odm_item_mn) {
			cl.show(ttp, "odm");
				this.setSize(900,750);
				odm_mn.setForeground(Color.RED);
				cust_mn.setForeground(Color.BLACK);
				item_mn.setForeground(Color.BLACK);
				refund_mn.setForeground(Color.BLACK);
				notice_mn.setForeground(Color.BLACK);
		}else if(obj == logo_item_mn) {
			cl.show(ttp, "logo");
				odm_mn.setForeground(Color.BLACK);
				cust_mn.setForeground(Color.BLACK);
				item_mn.setForeground(Color.BLACK);
				refund_mn.setForeground(Color.BLACK);
				notice_mn.setForeground(Color.BLACK);
			this.setTitle("����");
			cust_mn.setEnabled(false);
			odm_mn.setEnabled(false);
			logo_mn.setEnabled(false);
			item_mn.setEnabled(false);
			refund_mn.setEnabled(false);
			notice_mn.setEnabled(false);
		}else if(obj == item_item_mn) {
			cl.show(ttp, "item");
			this.setSize(1000,850);
				odm_mn.setForeground(Color.BLACK);
				cust_mn.setForeground(Color.BLACK);
				item_mn.setForeground(Color.RED);
				refund_mn.setForeground(Color.BLACK);
				notice_mn.setForeground(Color.BLACK);
		}else if(obj == refund_item_mn) {
			cl.show(ttp, "refund");
			this.setSize(900,850);
				odm_mn.setForeground(Color.BLACK);
				cust_mn.setForeground(Color.BLACK);
				item_mn.setForeground(Color.BLACK);
				refund_mn.setForeground(Color.RED);
				notice_mn.setForeground(Color.BLACK);
		}else if(obj == notice_item_mn) {
			cl.show(ttp, "notice");
			this.setSize(1050,750);
				odm_mn.setForeground(Color.BLACK);
				cust_mn.setForeground(Color.BLACK);
				item_mn.setForeground(Color.BLACK);
				refund_mn.setForeground(Color.BLACK);
				notice_mn.setForeground(Color.RED);
		}
	}
	JMenuBar jmb;
	JMenu cust_mn, logo_mn, item_mn, refund_mn, odm_mn, notice_mn;
	JMenuItem cust_item_mn;
	JMenuItem logo_item_mn;
	JMenuItem item_item_mn;
	JMenuItem refund_item_mn;
	JMenuItem odm_item_mn;
	JMenuItem notice_item_mn;
	CardLayout cl;
	Font font;
	JPanel bga;// ��� �̹���
	JPanel ttp;// ī�巹�̾ƿ� ��� �г�
	LoginSystem logsys;// �α���
	Item_info isys;// ��ǰ����
	Customer cust;// ȸ������
	OrderManagement odm;// �ֹ�����
	ReturnSystem rts;// ȯ�Ұ���
	Qna notice;// �ӽðԽ���
	
	Image winimg;// �����ܿ� �̹���
	
	void makeImage() {
		final String PATH = "src\\jij\\�ΰ�4.png";
		winimg = Toolkit.getDefaultToolkit().getImage(PATH);
	}
	
	void makeMenu() {
		font = new Font("����ü", Font.BOLD, 15);
		jmb = new JMenuBar();
		cust_mn = new JMenu("ȸ������");
			cust_mn.setFont(font);
			cust_mn.setEnabled(false);
		odm_mn = new JMenu("�ֹ�����");
			odm_mn.setFont(font);
			odm_mn.setEnabled(false);
		logo_mn = new JMenu("�α׾ƿ�");
			logo_mn.setFont(font);
			logo_mn.setEnabled(false);
		item_mn = new JMenu("��ǰ�˻�");
			item_mn.setFont(font);
			item_mn.setEnabled(false);
		refund_mn = new JMenu("ȯ�ҽý���");
			refund_mn.setFont(font);
			refund_mn.setEnabled(false);
		notice_mn = new JMenu("�Խ���");
			notice_mn.setFont(font);
			notice_mn.setEnabled(false);
		cust_item_mn = new JMenuItem("����");
			cust_item_mn.setFont(font);
			cust_item_mn.addActionListener(this);
		odm_item_mn = new JMenuItem("����");
			odm_item_mn.setFont(font);
			odm_item_mn.addActionListener(this);
		item_item_mn = new JMenuItem("����");
			item_item_mn.setFont(font);
			item_item_mn.addActionListener(this);
		refund_item_mn = new JMenuItem("����");
			refund_item_mn.setFont(font);
			refund_item_mn.addActionListener(this);
		notice_item_mn = new JMenuItem("����");
			notice_item_mn.setFont(font);
			notice_item_mn.addActionListener(this);
		logo_item_mn = new JMenuItem("������");
			logo_item_mn.setFont(font);
			logo_item_mn.addActionListener(this);
		cust_mn.add(cust_item_mn);
		odm_mn.add(odm_item_mn);
		logo_mn.add(logo_item_mn);
		item_mn.add(item_item_mn);
		refund_mn.add(refund_item_mn);
		notice_mn.add(notice_item_mn);
		jmb.add(cust_mn);
		jmb.add(odm_mn);
		jmb.add(item_mn);
		jmb.add(refund_mn);
		jmb.add(notice_mn);
		jmb.add(Box.createHorizontalGlue());// �� �������ʹ� �����ʿ� ����
		jmb.add(logo_mn);
	}
	
	public TotalSystem(String arg0) throws HeadlessException {
		super(arg0);
		bga = new BackGroundImage();
		cl = new CardLayout();
		ttp = new JPanel();
		ttp.setLayout(cl);
		logsys = new LoginSystem(this);
		odm = new OrderManagement();
		cust = new Customer(this);
		isys = new Item_info();
		rts = new ReturnSystem();
		notice = new Qna();
		ttp.add(logsys, "logo");
		ttp.add(odm, "odm");
		ttp.add(cust, "cust");
		ttp.add(isys, "item");
		ttp.add(rts, "refund");
		ttp.add(notice, "notice");
		ttp.add(bga, "bga");
		new ClockMenu(this);
		makeMenu();
		makeImage();
		setJMenuBar(jmb);// �޴��ٸ� �����쿡 ����
		
		add("Center", ttp);
		setSize(500, 400);
		setVisible(true);
		setIconImage(winimg);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	public static void main(String[] args) {
		new TotalSystem("����");
	}
}


