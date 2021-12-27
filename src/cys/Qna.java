package cys;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class BackGroundImage extends JPanel {
	
	Image image;
	JPanel j;
	final String PATH = "src\\cys\\9654.jpg";
	BackGroundImage(JPanel j){
		this.j = j;
		image=Toolkit.getDefaultToolkit().getImage(PATH);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, 
			this.getWidth(),this.getHeight(),j);
		setOpaque(false);
	}
}

public class Qna extends JPanel implements ActionListener, 
	MouseListener{
	
	JLabel mouselabel;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txt[0].setText(table.getValueAt(row, 0)+"");
		txt[1].setText(table.getValueAt(row, 2)+"");
		txta.setText(table.getValueAt(row, 3)+"");
		String category = (String)table.getValueAt(row, 1);
		if(category.equals("���")) {
			combo.setSelectedIndex(0);
		}else if(category.equals("��ǰ")) {
			combo.setSelectedIndex(1);
		}else if(category.equals("ȸ������")) {
			combo.setSelectedIndex(2);
		}else if(category.equals("��Ÿ")) {
			combo.setSelectedIndex(3);
		}

	}
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX(); //���콺 Ŭ���� x��ǥ
		int y = e.getY(); //���콺 Ŭ���� y��ǥ
		y = y - 20; //������ ����� ���̸�ŭ ����.
		this.add(mouselabel); //�ش���ġ�� ���̺� ���
		mouselabel.setLocation(x,y);//�ش� ��ġ�� ���̺� ���
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	public void actionPerformed(ActionEvent e) {
	
		Object o = e.getSource();
		
		if(o == btn1) {
			int r = JOptionPane.showConfirmDialog(this, "�ۼ��Ͻðڽ��ϱ�?");
			if(r == JOptionPane.OK_OPTION) {
				qnaCrud crud = new qnaCrud();
				Qna_2 q2 = new Qna_2();
				
				String name = txt[0].getText();
				String category = (String)combo.getSelectedItem();
				String title = txt[1].getText();
				String content = txta.getText();
				
				q2.setNotice_name(name);
				q2.setNotice_category(category);
				q2.setNotice_title(title);
				q2.setNotice_content(content);
				
				int result = crud.putqna(q2);
				if(result>0) {
					JOptionPane.showMessageDialog(this, "�ۼ��Ϸ�");
				}else {
					JOptionPane.showMessageDialog(this, "�ۼ�����");
				}
			}
		}else if(o ==btn2){
			
			txt[0].setText(" ");
			txt[0].setText("");
			txt[1].setText(" ");
			txt[1].setText("");
			combo.setSelectedIndex(0);
			txta.setText(""
					+ "�Խñ��� �ۼ��ÿ���, �ݵ�� ȸ��ID��(��ȸ�� �ֹ���ȣ) ���� �ּ���!\r\n" + 
					"�ֹ���ȣ : \r\n" + 
					"�̸� : \r\n" + 
					"��ȭ��ȣ : ");
			
		}else if(o ==btn4){
			int r = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?");
			if(r == JOptionPane.OK_OPTION) {
				Qna_2 q2 = new Qna_2();
				qnaCrud crud = new qnaCrud();
				
				String name = txt[0].getText();
				String category = (String)combo.getSelectedItem();
				String title = txt[1].getText();
				String content = txta.getText();
				
				q2.setNotice_name(name);
				q2.setNotice_category(category);
				q2.setNotice_title(title);
				q2.setNotice_content(content);
				
				int result = crud.updateqna(q2);
				if(result >0) {
					JOptionPane.showMessageDialog(this, "����Ǿ����ϴ�.");
				}else {
					JOptionPane.showMessageDialog(this, "�������");
				}
			}
		}else if(o ==btn5){
			int r = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?");
			if(r == JOptionPane.OK_OPTION) {
				String name = txt[0].getText();
				String category = "";
				int idx = combo.getSelectedIndex();
				if(idx !=0) {
					category = (String)combo.getSelectedItem();
				}
				String title = txt[1].getText();
				String content = txta.getText();
				
				HashMap<String,String> map = 
						new HashMap<String, String>();
				
				if(!name.equals("")) {
					map.put("notice_name", name);
				}
				if(!category.equals("")) {
					map.put("notice_category", category);
				}
				if(!title.equals("")) {
					map.put("notice_title", title);
				}
				if(!content.equals("")) {
					map.put("notice_content", content);
				}
				qnaCrud crud = new qnaCrud();
				int r2 = crud.deleteqna(map);
				if(r2 > 0) {
					JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
				}else {
					JOptionPane.showMessageDialog(this, "��������");
				}
			}
		}else if(o ==btn3) {
			int result = JOptionPane.showConfirmDialog(
					this, "��ȸ�Ͻðڽ��ϱ�?");
			if(result == JOptionPane.OK_OPTION) {
				
				String name = txt[0].getText();
				int idx = combo.getSelectedIndex();
				String category = "";
				if(idx !=0) {
					String categorys = (String)combo.getSelectedItem();
				}
				String title = txt[1].getText();
				String content = txta.getText();
				
				HashMap<String,String> map =
						new HashMap<String,String>();
				
				if(!name.equals("")) {
					map.put("notice_name", name);
				}
				if(!category.equals("")) {
					map.put("notice_category", category);
				}
				if(!title.equals("")) {
					map.put("notice_title", title);
				}
				if(!content.equals("")) {
					map.put("notice_content", content);
				}
				
				qnaCrud crud = new qnaCrud();
				List<Qna_2> list =
						crud.getqna(map);
				Qna_model qm = new Qna_model();
				qm.setData(list);
				table.addMouseListener(this);
				table.setModel(qm);
				
				int cnt = list.size();
				JOptionPane.showMessageDialog(this, cnt + " ���� ��ȸ�Ǿ����ϴ�.");
			}
		}	
	}
	
	JLabel[] label; JComboBox combo; 
	String[] name = {"NAME","CATEGORY","TITLE","CONTENT"};
	String[] cate = {"���","��ǰ","ȸ������","��Ÿ"};
	JTextField[] txt;
	JTextArea txta;
	JButton btn1, btn2, btn3, btn4, btn5;
	JPanel[] panel;
	JTable table;
	JScrollPane sp;
	JScrollPane txtaa;
	Font font;
	JPanel son;
	
	MovingPanel mp;
	int x1; int y1;
	boolean flag1 = true; boolean flag3 = true;

	
	void makeImage() {
		son = new BackGroundImage(this);
	}
	void makebutton() {
		font = new Font("SanSerif",Font.BOLD,15);
		btn1 = new JButton("�� ��");
		btn2 = new JButton("�����");
		btn3 = new JButton("�Խ��� ��ü��ȸ");
		btn4 = new JButton("����");
		btn5 = new JButton("����");
		btn1.setFont(font);
		btn2.setFont(font);
		btn3.setFont(font);
		btn4.setFont(font);
		btn5.setFont(font);
		for(int i = 0; i <cate.length;i++) {
		combo = new JComboBox(cate);
		combo.setFont(font);
		}
		combo.setBounds(450, 50, 100, 30);
		this.add(combo);
		btn1.setBounds(210, 600, 100, 30);
		this.add(btn1);
		btn2.setBounds(390, 600, 100, 30);
		this.add(btn2);
		btn3.setBounds(850, 600, 150, 30);
		this.add(btn3);
		btn4.setBounds(730, 600, 100, 30);
		this.add(btn4);
		btn5.setBounds(610, 600, 100, 30);
		this.add(btn5);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
	}
	
	void maketext() {
		font = new Font("����ü",Font.BOLD,14);
		txt = new JTextField[2];
		txta = new JTextArea(5,50);
		txta.setText("�Խñ��� �ۼ��ÿ���, �ݵ�� ȸ��ID��(��ȸ�� �ֹ���ȣ) ���� �ּ���!\r\n" + 
				"�ֹ���ȣ : \r\n" + 
				"�̸� : \r\n" + 
				"��ȭ��ȣ : ");
		txtaa = new JScrollPane(txta);
		table = new JTable();
		sp = new JScrollPane(table);
		
		sp.setBounds(600, 50, 400, 500);
		this.add(sp);
		
		txt[0] = new JTextField(100);
		txt[1] = new JTextField(40);
		
		txt[0].setBounds(150, 50, 150, 30);
		this.add(txt[0]);
		txt[1].setBounds(150, 150, 400, 30);
		this.add(txt[1]);
		txtaa.setBounds(150, 250, 400, 300);
		this.add(txtaa);
	}
	void makeLabel() {
		font = new Font("����ü",Font.BOLD+Font.PLAIN,20);
		label = new JLabel[name.length];
		for(int i =0; i <name.length;i++) {
			label[i] = new JLabel(name[i]);
			label[i].setFont(font);
			this.add(label[i]);
		}
		label[0].setBounds(50, 50, 100, 30);
		label[1].setBounds(330, 50, 100, 30);
		label[2].setBounds(50, 150, 100, 30);
		label[3].setBounds(50, 380, 100, 30);
		}
	class Refresh extends Thread{
		@Override
		public void run() {
		while(true) {
			try {
				Thread.sleep(10);
			}catch(Exception e) {}
			
			if(x1 > 100
					||x1<0) {
				flag1  = !flag1;
			}
			if(flag1)x1++;
			else x1--;
			
			mp.repaint();//�г��� �ٽ� �׸�
		}	
		}
	}
	class MovingPanel extends JLabel{
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			g.setFont(new Font("Fixedsys",Font.BOLD+Font.ITALIC,18));
			g.drawString("����� �ݵ�� �ۼ����ּ���.", x1, y1);
		}	
	}
	public Qna(/*String title*/){
//		super(title);
		x1 = y1 = 100;
		mp = new MovingPanel();
		Refresh rf = new Refresh(); rf.start();
		mp.setBounds(200, 480, 300, 300);
		this.add(mp);
		makeImage();
		maketext();
		makeLabel();
		makebutton();
		this.add(son);
		this.setLayout(null);
		mouselabel = new JLabel("H E L L O ~ !");
		mouselabel.setSize(200,20);
		this.addMouseListener(this);

//		this.setSize(1100,700);
//		this.setVisible(true);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
//
//	public static void main(String[] args) {
//		new Qna("Q & A");
	}
//}
