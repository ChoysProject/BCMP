package cys;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Clock;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
class Picture extends JPanel{
	Image image;
	Picture(String path){
		image=Toolkit.getDefaultToolkit().getImage(
				path);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, 
			this.getWidth(),this.getHeight(),this);
	}
}

public class Item_info extends JPanel implements ActionListener,MouseListener,ItemListener{

	Panel pnl;
	JLabel[] label; JTextField[] txt; JButton[] btns; JRadioButton[] check; JComboBox[] choice;
	JPanel center, west, east, north, south, southeast;
	JPanel[] panel; JPanel imgpanel;
	String[] nation = {"�ѱ�","�߱�","�̱�","�Ϻ�","��Ż����","ĳ����","���þ�"};
	ButtonGroup group;
	JLabel maintitle;
	JButton ymd;
	String[] kind = {"�ƿ���","Ƽ����","����","����","��Ʈ","Ʈ���̴�","�Ź�","����","�Ǽ��縮"};
	String[] size = {"S","M","L","Free"};
	String[] title = {"��ǰ��ȣ","��ǰ�̸�"," ��  �� ", " ��  �� ","������  ","�귣��  ","������  ","��ǰ���� ","��ǰ���","��ǰ��� ","��������"};
	String[] btntitle = {"�����Է�","��������","������ȸ","��������","�ʱ�ȭ"};
	JTable table; JScrollPane sp; Font font;
	JPanel clock;
	JPanel tablepanel;
	JPanel picpanel;
	String[] tabletitle= {"��ǰ��ȣ","��ǰ�̸�","����","����","������","�귣��","������","��ǰ���","��ǰ���","��������"};
	JPanel[] clothes;   CardLayout card;
	JPanel clothesPanel;
	Container cp;
	JSplitPane sp1,sp2,sp3; 
	BlinkLabel blink; long delay; 
	
	class BlinkLabel extends JLabel implements Runnable{
		BlinkLabel(String text, long delay){
			super(text);
			Item_info.this.delay = delay;
			this.setOpaque(true);
			Thread t = new Thread(this);
			t.start();
		}
		@Override
		public void run() {
		int color = (int)(Math.random()*5);
		while(true) {
			if(color == 0) {
				this.setBackground(Color.YELLOW);
			}else if(color == 1) {
				this.setBackground(Color.GREEN);
			}else if(color == 2) {
				this.setBackground(Color.DARK_GRAY);
			}else if(color == 3) {
				this.setBackground(Color.BLUE);
			}else if(color == 4) {
				this.setBackground(Color.ORANGE);
			}else if(color == 5) {
				this.setBackground(Color.MAGENTA);
			}
			if(color ==0) {color =1;}
			else {color =0;}
			try {
				Thread.sleep(delay);
			}catch(Exception e) {
				return;
			}
		}
	}
}
	
	@Override
	public void itemStateChanged(ItemEvent e) {

	}
	@Override
	
	public void mouseClicked(MouseEvent e) {
		
		int row = table.getSelectedRow();
		txt[0].setText(table.getValueAt(row, 0)+"");
		txt[1].setText(table.getValueAt(row, 1)+"");
		txt[2].setText(table.getValueAt(row, 2)+"");
		txt[3].setText(table.getValueAt(row, 3)+"");
		String size = (String)table.getValueAt(row, 4);
		if(size.equals("S")) {
			choice[0].setSelectedIndex(1);
		}else if(size.equals("M")) {
			choice[0].setSelectedIndex(2);
		}else if(size.equals("L")) {
			choice[0].setSelectedIndex(3);
		}else if(size.equals("Free")) {
			choice[0].setSelectedIndex(4);
		}
		txt[4].setText(table.getValueAt(row, 5)+"");
		
		String from = (String)table.getValueAt(row, 6);
		if(from.equals("�ѱ�")) {
			choice[1].setSelectedIndex(1);
		}else if(from.equals("�߱�")) {
			choice[1].setSelectedIndex(2);
		}else if(from.equals("�̱�")) {
			choice[1].setSelectedIndex(3);
		}else if(from.equals("�Ϻ�")) {
			choice[1].setSelectedIndex(4);
		}else if(from.equals("��Ż����")) {
			choice[1].setSelectedIndex(5);
		}else if(from.equals("ĳ����")) {
			choice[1].setSelectedIndex(6);
		}else if(from.equals("���þ�")) {
			choice[1].setSelectedIndex(7);
		}
		
		String kind = (String)table.getValueAt(row, 7);
		if(kind.equals("�ƿ���")) {
			choice[2].setSelectedIndex(1);
		}else if(kind.equals("Ƽ����")) {
			choice[2].setSelectedIndex(2);
		}else if(kind.equals("����")) {
			choice[2].setSelectedIndex(3);
		}else if(kind.equals("����")) {
			choice[2].setSelectedIndex(4);
		}else if(kind.equals("��Ʈ")) {
			choice[2].setSelectedIndex(5);
		}else if(kind.equals("Ʈ���̴�")) {
			choice[2].setSelectedIndex(6);
		}else if(kind.equals("�Ź�")) {
			choice[2].setSelectedIndex(7);
		}else if(kind.equals("����")) {
			choice[2].setSelectedIndex(8);
		}else if(kind.equals("�Ǽ��縮")) {
			choice[2].setSelectedIndex(9);
		}
		txt[5].setText(table.getValueAt(row, 8)+"");
		
		String gender = (String)table.getValueAt(row, 9);
		if(gender.equals("����")) {
			check[0].setSelected(true);
		}else if(gender.equals("����")) {
			check[1].setSelected(true);
		}
		
		
		String ci = table.getValueAt(row, 1)+"";
		String color = table.getValueAt(row, 3)+"";
		if(ci.equals("�񵧹���")) {
			if(color.equals("īŰ")) {
				sp3.setTopComponent(clothes[2]);
				sp3.setDividerLocation(400);
			}else if(color.equals("��")) {
				sp3.setTopComponent(clothes[1]);
				sp3.setDividerLocation(400);
			}else if(color.equals("���̺���")) {
				sp3.setTopComponent(clothes[0]);
				sp3.setDividerLocation(400);
			}
		}
		if(ci.equals("�⺻������")) {
			if(color.equals("����")) {
				sp3.setTopComponent(clothes[3]);
				sp3.setDividerLocation(400);
			}else if(color.equals("����")) {
				sp3.setTopComponent(clothes[4]);
				sp3.setDividerLocation(400);
			}else if(color.equals("������")) {
				sp3.setTopComponent(clothes[5]);
				sp3.setDividerLocation(400);
			}else if(color.equals("ȭ��Ʈ")) {
				sp3.setTopComponent(clothes[6]);
				sp3.setDividerLocation(400);
			}	
		}
		if(ci.equals("�⺻��Ʈ")) {
			if(color.equals("������")) {
				sp3.setTopComponent(clothes[17]);
				sp3.setDividerLocation(400);
			}else if(color.equals("��")) {
				sp3.setTopComponent(clothes[18]);
				sp3.setDividerLocation(400);
			}
		}
		if(ci.equals("�⺻Ʈ���̴�")) {
			if(color.equals("��ũ")) {
				sp3.setTopComponent(clothes[21]);
				sp3.setDividerLocation(400);
			}else if(color.equals("����")){
				sp3.setTopComponent(clothes[19]);
				sp3.setDividerLocation(400);
			}else if(color.equals("���")) {
				sp3.setTopComponent(clothes[22]);
				sp3.setDividerLocation(400);
			}else if(color.equals("��")) {
				sp3.setTopComponent(clothes[20]);
				sp3.setDividerLocation(400);
			}
		}
		
		if(ci.equals("�⺻û����")) {
			if(color.equals("�׸�")) {
				sp3.setTopComponent(clothes[15]);
				sp3.setDividerLocation(400);
			}else if(color.equals("����Ʈ���")) {
				sp3.setTopComponent(clothes[13]);
				sp3.setDividerLocation(400);
			}else if(color.equals("���̽����")) {
				sp3.setTopComponent(clothes[14]);
				sp3.setDividerLocation(400);
			}else if(color.equals("��û")) {
				sp3.setTopComponent(clothes[16]);
				sp3.setDividerLocation(400);
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {

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
		if(o == ymd) {
			new CalendarBySwing(this);
			choice[3].setEnabled(false);
			choice[4].setEnabled(false);
			choice[5].setEnabled(false);
			
		}else if(o==btns[4]) {
			
			txt[0].setText(" "); txt[0].setText("");
			txt[1].setText(" "); txt[1].setText("");
			txt[2].setText(" "); txt[2].setText("");
			txt[3].setText(" "); txt[3].setText("");
			txt[4].setText(" "); txt[4].setText("");
			txt[5].setText(" "); txt[5].setText("");
			choice[0].setSelectedIndex(0);
			choice[1].setSelectedIndex(0);
			choice[2].setSelectedIndex(0);
			choice[3].setSelectedIndex(0);
			choice[4].setSelectedIndex(0);
			choice[5].setSelectedIndex(0);
			group.clearSelection();
			sp3.setTopComponent(clothes[25]);
			sp3.setDividerLocation(400);
			
		}else if(o == btns[0]) {
			int r = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?");
			if(r==JOptionPane.OK_OPTION) {
				itemCrud crud = new itemCrud();
				Item_info2 ii = new Item_info2();
				String num = txt[0].getText();
				String name = txt[1].getText();
				String price = txt[2].getText();
				String color = txt[3].getText();
				String size = (String)choice[0].getSelectedItem();
				String brand = txt[4].getText();
				String from = (String)choice[1].getSelectedItem();
				String kind = (String)choice[2].getSelectedItem();
				String material = txt[5].getText();
				String gender = "";
				if(check[0].isSelected()) {
					gender="����";
				}else if(check[1].isSelected()) {
					gender="����";
				}
				String ymd = choice[3].getSelectedItem()+"0"+choice[4].getSelectedItem()+
						choice[5].getSelectedItem();
				ii.setItem_num(Integer.parseInt(num));
				ii.setItem_name(name);
				ii.setItem_price(Integer.parseInt(price));
				ii.setItem_color(color);
				ii.setItem_size(size);
				ii.setItem_brand(brand);
				ii.setItem_from(from);
				ii.setItem_kind(kind);
				ii.setItem_material(material);
				ii.setItem_target(gender);
				ii.setItem_ymd(ymd);
				
				int result = crud.putitem(ii);
				if(result>0) {
					JOptionPane.showMessageDialog(this, "���� ó���Ǿ����ϴ�.");
				}else {
					JOptionPane.showMessageDialog(this, "���Ͻ���");
				}
			}
			
		}
		
		else if(o == btns[2]) {
			int result = JOptionPane.showConfirmDialog(this, "��ȸ�Ͻðڽ��ϱ�?");	
			if(result == JOptionPane.OK_OPTION) {
				
				String num = txt[0].getText();
//				Integer num2 = Integer.toString(num);
				String name = txt[1].getText();
				String price = txt[2].getText();
//				Integer price2 = Integer.parseInt(price);
				String color = txt[3].getText();
				int idx = choice[0].getSelectedIndex();
				String size ="";
				if(idx !=0) {
					size = (String)choice[0].getSelectedItem();
				}
				String brand = txt[4].getText();
				int idx2 = choice[1].getSelectedIndex();
				String from ="";
				if(idx2 !=0) {
					from = (String)choice[1].getSelectedItem();
				}
				int idx3 = choice[2].getSelectedIndex();
				String kind = "";
				if(idx3 !=0) {
					kind = (String)choice[2].getSelectedItem();
				}
				String material = txt[5].getText();
				String gender = "";
				if(check[0].isSelected()) {
					gender="����";
				}else if(check[1].isSelected()) {
					gender="����";
				}
				String ymd = String.valueOf(choice[3].getSelectedItem())+"0"+
						String.valueOf(choice[4].getSelectedItem())+
						String.valueOf(choice[5].getSelectedItem());
				
				System.out.println(ymd);
				HashMap<String,String> map = new HashMap<String,String>();
				
				if(!num.equals("")) {
					map.put("item_num", num);
				}
				if(!name.equals("")) {
					map.put("item_name", name);
				}
				if(!price.equals("")) {
					map.put("item_price", price);
				}
				if(!color.equals("")) {
					map.put("item_color", color);
				}
				if(!size.equals("")) {
					map.put("item_size", size);
				}
				if(!brand.equals("")) {
					map.put("item_brand", brand);
				}
				if(!from.equals("")) {
					map.put("item_from", from);
				}
				if(!kind.equals("")) {
					map.put("item_kind", kind);
				}
				if(!material.equals("")) {
					map.put("item_material", material);
				}
				if(!gender.equals("")) {
					map.put("item_target", gender);
				}
				if(!ymd.equals("")) {
					map.put("item_ymd", ymd);
				}
				itemCrud crud = new itemCrud();
				List<Item_info2> list = 
						crud.getiteminfo(map);
				Item_model im = new Item_model();
				im.setData(list);
				table.setModel(im);
				table.getColumnModel().getColumn(0).setPreferredWidth(90);
				table.getColumnModel().getColumn(1).setPreferredWidth(135);
				table.getColumnModel().getColumn(2).setPreferredWidth(65);
				table.getColumnModel().getColumn(3).setPreferredWidth(115);
				table.getColumnModel().getColumn(8).setPreferredWidth(70);
				table.getColumnModel().getColumn(10).setPreferredWidth(115);
				int cnt = list.size();
				JOptionPane.showMessageDialog(this, cnt+" ���� ��ȸ�Ǿ����ϴ�.");
			}
			}else if(o == btns[1]) {//����
				int r = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?");
				if(r==JOptionPane.OK_OPTION) {
					String num = txt[0].getText();
					String name = txt[1].getText();
					String price = txt[2].getText();
					String color = txt[3].getText();
					String size ="";
					int idx = idx = choice[0].getSelectedIndex();
					if(idx !=0) {
						size = (String)choice[0].getSelectedItem();
					}
					String brand = txt[4].getText();
					String from = "";
					int idx2 = choice[1].getSelectedIndex();
					if(idx2 !=0 ) {
						from = (String)choice[1].getSelectedItem();
					}
					String kind = "";
					int idx3 = choice[2].getSelectedIndex();
					if(idx3 != 0) {
						kind = (String)choice[2].getSelectedItem();
					}
					String material = txt[5].getText();
					String gender = "";
					if(check[0].isSelected()) {
						gender = "����";
					}else if(check[1].isSelected()) {
						gender = "����";
					}
					String ymb = choice[3].getSelectedItem()+"0"+
							choice[4].getSelectedItem()+
							choice[5].getSelectedItem();
					HashMap<String,String> map = 
							new HashMap<String, String>();
					if(!num.equals("")) {
						map.put("item_num", num);
					}if(!name.equals("")) {
						map.put("item_name", name);
					}if(!price.equals("")) {
						map.put("item_price", price);
					}if(!color.equals("")) {
						map.put("item_color", color);
					}if(!size.equals("")) {
						map.put("item_size", size);
					}if(!brand.equals("")) {
						map.put("item_brand", brand);
					}if(!from.equals("")) {
						map.put("item_from", from);
					}if(!kind.equals("")) {
						map.put("item_kind", kind);
					}if(!material.equals("")) {
						map.put("item_material", material);
					}if(gender.equals("")) {
						map.put("item_target", gender);
					}if(ymd.equals("")) {
						map.put("item_ymd", ymb);
					}
					itemCrud crud = new itemCrud();
					int result2 = crud.deleteitem(map);
					if(result2 > 0) {
						JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
					}else {
						JOptionPane.showMessageDialog(this, "���� ����");
					}
				}
		}else if(o==btns[3]) {
			int r = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?");
			if(r == JOptionPane.OK_OPTION) {
				
				Item_info2 ii = new Item_info2();
				itemCrud crud = new itemCrud();
				
				String num = txt[0].getText();
				String name = txt[1].getText();
				String price = txt[2].getText();
				String color = txt[3].getText();
				String size = (String)choice[0].getSelectedItem();
				String brand = txt[4].getText();
				String from = (String)choice[1].getSelectedItem();
				String kind = (String)choice[2].getSelectedItem();
				String material = txt[5].getText();
				String gender = "";
				if(check[0].isSelected()) {
					gender="����";
				}else if(check[1].isSelected()) {
					gender="����";
				}
				String ymd = choice[3].getSelectedItem()+"0"+choice[4].getSelectedItem()+
						choice[5].getSelectedItem();
				
				ii.setItem_num(Integer.parseInt(num));
				ii.setItem_name(name);
				ii.setItem_price(Integer.parseInt(price));
				ii.setItem_color(color);
				ii.setItem_size(size);
				ii.setItem_brand(brand);
				ii.setItem_from(from);
				ii.setItem_kind(kind);
				ii.setItem_material(material);
				ii.setItem_target(gender);
				ii.setItem_ymd(ymd);
				
				int result = crud.updateitem(ii);
				if(result >0) {
					JOptionPane.showMessageDialog(this, "����Ǿ����ϴ�.");
				}else{
					JOptionPane.showMessageDialog(this, "���� ����");
				}
			}
		}
	}
	@Override
	public void paintComponents(Graphics g) {
	
	}
	
	

	class TimeClock extends JPanel implements Runnable{
		Thread TimeClock;
		JLabel text;
		Item_info main;
		Font font;
		TimeClock(Item_info main){
			this.main=main;
			text=new JLabel();
			TimeClock = new Thread(TimeClock.this);
			this.setFont(new Font("����ü",Font.BOLD+Font.ITALIC,20));
			
			text.setText(clockText());
			this.add(text);
			TimeClock.start();
		}
		String clockText() {
			Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int min = c.get(Calendar.MINUTE);
			int sec = c.get(Calendar.SECOND);
			String hourTxt = Integer.toString(hour);
			if(hour<10) {
				hourTxt="0"+hourTxt;
			}hourTxt=hourTxt+":";
			if(min<10) {
				hourTxt=hourTxt+"0";
			}hourTxt=hourTxt+min+":";
			if(sec<10) {
				hourTxt=hourTxt+"0";	
			}hourTxt=hourTxt+sec;
			return hourTxt;
		}
		@Override
		public void run() {
			while(true) {
				try {
				Thread.sleep(1000);
				}catch(Exception e){}
					text.setText(clockText());//1�ʸ��� ����
//				main.setTitle("��ǰ���������ý��� "
//+ "  					                                                                                                             	                      		                                   "
//						+clockText());
			}	
		}
	} 
	void makeImage() {//�̹��� 6�� ����
		
		clothes = new JPanel[27];
		for(int i=0; i<clothes.length; i++) {
			String path="src\\cys\\";			
			switch(i) {
			case 0: path=path+"�񵧹��� ���̺���.jpg"; break;
			case 1: path=path+"�񵧹��� ����.jpg"; break;
			case 2: path=path+"�񵧹��� īŰ.jpg";break;
			case 3: path=path+"�⺻������ ����.jpg";break;
			case 4: path=path+"�⺻������ ����.jpg";break;
			case 5: path=path+"�⺻������ ������.jpg";break;
			case 6: path=path+"�⺻������ ȭ��Ʈ.jpg";break;
			case 7: path=path+"�⺻������ ��.jpg";break;
			case 8: path=path+"�⺻������ í��.jpg";break;
			case 9: path=path+"�⺻������ īŰ.jpg";break;
			case 10: path=path+"�⺻������ �׸�.jpg";break;
			case 11: path=path+"�⺻������ ������.jpg";break;
			case 12: path=path+"�⺻ û���� �׷���.jpg";break;
			case 13: path=path+"�⺻û���� ����Ʈ���.jpg";break;
			case 14: path=path+"�⺻û���� ���̽����.jpg";break;
			case 15: path=path+"�⺻û���� �׸�.jpg";break;
			case 16: path=path+"�⺻û���� ��û.jpg";break;
			case 17: path=path+"�⺻�ڵ� ������.jpg";break;
			case 18: path=path+"�⺻�ڵ� ��.jpg";break;
			case 19: path=path+"�⺻Ʈ���̴� ����.jpg";break;
			case 20: path=path+"�⺻Ʈ���̴� ��.jpg";break;
			case 21: path=path+"�⺻Ʈ���̴� ��ũ.jpg";break;
			case 22: path=path+"�⺻Ʈ���̴� ���.jpg";break;
			case 23: path=path+"�⺻�ĵ� ������.jpg";break;
			case 24: path=path+"�⺻�ĵ�Ƽ ��.jpg";break;
			case 25: path=path+"�ΰ�.png"; break;
			case 26: path=path+"�ΰ�.png"; break;
			}
			clothes[i] = new Picture(path);
		}
		
	}
	void makePanel() {
		
		panel = new JPanel[12];
		center = new JPanel();
		west = new JPanel();
		south = new JPanel();
		north = new JPanel();
		east = new JPanel();
		southeast = new JPanel();
		center.setLayout(new GridLayout(12,1));
		north.add(maintitle);
		table = new JTable();
		table.addMouseListener(this);
		sp = new JScrollPane(table);
		tablepanel = new JPanel();
		picpanel = new JPanel();
		tablepanel.add(sp);
		clock = new JPanel();
		
		for(int i = 0; i<=4;i++) {
			south.add(btns[i]);
		}
		southeast.add(clock);
		south.add(southeast);
		
		for(int i= 0 ;i<panel.length;i++) {
			panel[i] = new JPanel();
			center.add(north);
			center.add(panel[i]);
			switch(i) {
			case 0:
				panel[i].add(label[0]);
				panel[i].add(txt[0]);
				break;
			case 1:
				panel[i].add(label[1]);
				panel[i].add(txt[1]);
				break;
			case 2:
				panel[i].add(label[2]);
				panel[i].add(txt[2]);
				break;
			case 3:
				panel[i].add(label[3]);
				panel[i].add(txt[3]);
				break;
			case 4:
				panel[i].add(label[4]);
				panel[i].add(choice[0]);
				break;
			case 5:
				panel[i].add(label[5]);
				panel[i].add(txt[4]);
				break;
			case 6:
				panel[i].add(label[6]);
				panel[i].add(choice[1]);
				break;
			case 7:
				panel[i].add(label[7]);
				panel[i].add(choice[2]);
				break;
			case 8:
				panel[i].add(label[8]);
				panel[i].add(txt[5]);
				break;
			case 9:
				panel[i].add(label[9]);
				panel[i].add(check[0]);
				panel[i].add(check[1]);
				break;
			case 10:
				panel[i].add(label[10]);
				panel[i].add(choice[3]);
				panel[i].add(choice[4]);
				panel[i].add(choice[5]);
				panel[i].add(ymd);
				break;
			case 11:
				panel[i].add(blink);
				break;
				
			}
		}
	}
	void makeComboBox() {
		font = new Font("����ü",font.BOLD,20);
		choice = new JComboBox[6];
		
		for(int i =0 ;i <choice.length;i++) {
			choice[i] = new JComboBox();
		}
		choice[0].addItem("����� �����ϼ���.");
		for(int i = 0 ; i<size.length;i++) {
		choice[0].addItem(size[i]);}
		choice[1].addItem("�������� �����ϼ���.");
		for(int i = 0; i<nation.length;i++) {
			choice[1].addItem(nation[i]);
		}
		choice[2].addItem("�Ƿ����� �����ϼ���.");
		for(int i = 0 ; i<kind.length;i++) {
			choice[2].addItem(kind[i]);
		}
		for(int i = 0; i<choice.length;i++) {
			switch(i) {
			case 3: choice[i] = new JComboBox();
//					choice[3].addItem("�⵵");
					for(int j = 2020;j>1920;j--) {
						choice[i].addItem(j);
					}
			
					break;
			case 4: choice[i] = new JComboBox();
//					choice[4].addItem("��");
					for(int j =1;j<=12;j++) {
						choice[i].addItem(j);
					}
					break;
			case 5:choice[i] = new JComboBox();
//					choice[5].addItem("��");
					choice[i].addItem(1);
			}
			choice[i].setFont(font);
			choice[i].addItemListener(this);
		}

	}
	void makeCheckBox() {
		font = new Font("����ü",font.BOLD,20);
		check = new JRadioButton[2];
		group = new ButtonGroup();
		check[0] = new JRadioButton("����",false);
		check[1] = new JRadioButton("����",false);
		check[0].setFont(font);
		check[1].setFont(font);
		group.add(check[0]); group.add(check[1]);
	}
	void makeButton() {
		btns = new JButton[btntitle.length];
		font = new Font("����ü",font.BOLD,20);
		ymd = new JButton("�޷�");
		for(int i = 0;i<btns.length;i++) {
			btns[i] = new JButton(btntitle[i]);
			btns[i].setFont(font);
			ymd.setFont(font);
			btns[i].addActionListener(this);
		}
		ymd.addActionListener(this);
	}
	void makeTextField() {
		txt = new JTextField[6];
		font = new Font("����ü",font.BOLD,20);
		for(int i = 0 ;i<txt.length;i++) {
			txt[i] = new JTextField(20);		
		}
	}
	void makeLabel() {
		font = new Font("����ü",font.BOLD,40);
		font = new Font("����ü",font.BOLD,20);
		label = new JLabel[title.length];
	for(int i = 0; i<label.length;i++) {
		label[i] = new JLabel(title[i]);
		label[i].setFont(font);
	}
		maintitle = new JLabel("�� ǰ �� ��");
		maintitle = new JLabel();
		maintitle.setFont(font);
		
		for(int i = 0; i<label.length;i++) {
			label[i] = new JLabel(title[i]);
			label[i].setFont(font);

		}
	}
	public Item_info(/*String title*/) {
//		super(title);
		font = new Font("����ü",Font.CENTER_BASELINE+font.ITALIC,25);
		blink = new BlinkLabel("�� �� �� �� P C Ȯ �� ��",1000);
		blink.setFont(font);
		this.add(blink);
		this.setLayout(new BorderLayout());
//		new TimeClock(this);
//		this.setTitle();
		
		makeLabel();
		makeTextField();
		makeComboBox();
		makeCheckBox();
		makeButton();
		makeImage();
		makePanel();
		this.add("North",north);
		this.add("Center",center);
		this.add("East",east);
		this.add("West",west);
		this.add("South",south);
		sp1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		sp2=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sp3=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sp1.setLeftComponent(center);
		sp1.setDividerLocation(420);
		sp3.setTopComponent(clothes[26]);
		sp3.setBottomComponent(sp);
		sp1.setRightComponent(sp2);
		sp2.setTopComponent(sp3);
		sp3.setDividerLocation(400);
		this.add("Center",sp1);
//		Container cp = getContentPane();
//		cp.add(sp1,"Center");
		table.addMouseListener(this);
//		this.setResizable(false);
		this.setSize(1000,850);
		this.setVisible(true);
		
	}
//			public static void main(String[] args) {
//				new Item_info("��ǰ���� �ý���");
	}
//}
