package mjh;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
class ClockMenu extends JLabel implements Runnable{
	Thread timer;
	ClockMenu(){
		this.setText(clockText());//메뉴의 제목
		this.setFont(new Font("굴림체",Font.BOLD,20));
		timer = new Thread(ClockMenu.this);
		timer.start();
	}
	String clockText() {
		Calendar c = Calendar.getInstance();
		int hour=c.get(Calendar.HOUR_OF_DAY);
		int min =c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		String hourTxt="                               현재시각:"+Integer.toString(hour);
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
			this.setText(clockText());//1초마다 실행
		}
	}
}
public class ReturnSystem extends JPanel implements ItemListener,ActionListener,MouseListener{
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row =table.getSelectedRow();
		int col=5;
		Integer number=(Integer)table.getValueAt(row, 0);
		txt1.setText(number+"");
		String cust_id=(String)table.getValueAt(row, 1);
		txt2.setText(cust_id);
		String how=(String)table.getValueAt(row, 2);
		box[0].setSelectedItem(how);
		String date=(String)table.getValueAt(row, 3);
		String[] ymd=date.split("/");
		box[1].setSelectedItem(Integer.parseInt(ymd[0]));
		box[2].setSelectedItem(Integer.parseInt(ymd[1]));
		box[3].setSelectedItem(Integer.parseInt(ymd[2]));
		String reason=(String)table.getValueAt(row, 4);
		box[4].setSelectedItem(reason);
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj=arg0.getSource();
		if(obj==calendar) {
			new CalendarBySwing(this);
		}
		else if(obj==btn[1]) {
			String number=txt1.getText();//
			String id=txt2.getText();//
			String how=(String)box[0].getSelectedItem();//
			String date=box[1].getSelectedItem()+"/"+
					box[2].getSelectedItem()+"/"+
					box[3].getSelectedItem();
			String reason=(String)box[4].getSelectedItem();
			HashMap<String,String> map = new HashMap<String,String>();
			if(!number.equals(""))
				map.put("return_number", number);
			if(!id.equals(""))
				map.put("cust_id", id);
			if(!how.equals("선택하세요"))
				map.put("return_method", how);
			if(!date.equals("선택하세요/선택하세요/선택하세요"))
				map.put("return_date", date);
			if(!reason.equals("선택하세요"))
				map.put("return_reason", reason);
			Crud crud = new Crud();
			List<ReturnInfo> list=crud.getReturn(map);
			ReturnModel rm= new ReturnModel();
			rm.setData(list);
			table.setModel(rm);	
		}else if(obj==btn[0]) {
			int r=JOptionPane.showConfirmDialog(this, "정말로 삽입하시겠습니까?");
			if(r==JOptionPane.OK_OPTION) {
				Crud crud = new Crud();
				String number=txt1.getText();
				int count=crud.returnCount(Integer.parseInt(number));
				if(count!=0)
					JOptionPane.showMessageDialog(this,"이미 저장된 번호입니다.");
				else {
					ReturnInfo ri= new ReturnInfo();
					String id=txt2.getText();//
					String how=(String)box[0].getSelectedItem();//
					String date=box[1].getSelectedItem()+"/"+
							box[2].getSelectedItem()+"/"+
							box[3].getSelectedItem();
					String reason=(String)box[4].getSelectedItem();
					ri.setReturn_number(Integer.parseInt(number));
					ri.setCust_id(id);
					ri.setReturn_method(how);
					ri.setReturn_date(date);
					ri.setReturn_reason(reason);
					int result=crud.putReturn(ri);
					if(result>0)
						JOptionPane.showMessageDialog(this, "정상적으로 삽입되었습니다.");
					else
						JOptionPane.showMessageDialog(this, "삽입중 문제발생");
				}
			}
		}else if(obj==btn[3]) {
			int r=JOptionPane.showConfirmDialog(this, "정말로 삭제하시겠습니까?");
			if(r==JOptionPane.OK_OPTION) {
				String number=txt1.getText();//
				String id=txt2.getText();//
				String how=(String)box[0].getSelectedItem();//
				String date=box[1].getSelectedItem()+"/"+
						box[2].getSelectedItem()+"/"+
						box[3].getSelectedItem();
				String reason=(String)box[4].getSelectedItem();
				HashMap<String,String>map =new HashMap<String,String>();
				if(!number.equals(""))
					map.put("return_number", number);
				if(!id.equals(""))
					map.put("cust_id", id);
				if(!how.equals("선택하세요"))
					map.put("return_method", how);
				if(!date.equals("선택하세요/선택하세요/선택하세요"))
					map.put("return_date", date);
				if(!reason.equals("선택하세요"))
					map.put("return_reason", reason);
				Crud crud = new Crud();
				int result=crud.deleteReturn(map);
				if(result>0) {
					JOptionPane.showMessageDialog(this, "정상적으로 삭제되었습니다.");
				}else {
					JOptionPane.showMessageDialog(this, "삭제중 실패");
				}
			}
		}else if(obj==btn[2]) {
			String number=txt1.getText();//
			String id=txt2.getText();//
			String how=(String)box[0].getSelectedItem();//
			String date=box[1].getSelectedItem()+"/"+
					box[2].getSelectedItem()+"/"+
					box[3].getSelectedItem();
			String reason=(String)box[4].getSelectedItem();
			ReturnInfo ri= new ReturnInfo();
			ri.setReturn_number(Integer.parseInt(number));
			ri.setCust_id(id);
			ri.setReturn_method(how);
			ri.setReturn_date(date);
			ri.setReturn_reason(reason);
			Crud crud = new Crud();
			int result=crud.updateReturn(ri);
			if(result>0) {
				JOptionPane.showMessageDialog(this, "정상적으로 변경되었습니다.");
			}else {
				JOptionPane.showMessageDialog(this, "변경중 오류");
			}
		}else if(obj==btn[4]) {
			txt1.setText("");
			txt2.setText("");
			box[0].setSelectedIndex(0);
			box[1].setSelectedIndex(0);
			box[2].setSelectedIndex(0);
			box[3].setSelectedIndex(0);
			box[4].setSelectedIndex(0);
		}
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Object obj = arg0.getSource();
		if(obj==box[2]) {//월을 선택한 경우
			box[3].removeAllItems();//지원다.
			box[3].addItem("선택하세요");
			int m= box[2].getSelectedIndex()+1;
			switch(m) {
			case 2:for(int i=1;i<=28;i++) {
				box[3].addItem(i);
				}break;
			case 4:
			case 6:
			case 11:for(int i=1;i<=30;i++) {
				box[3].addItem(i);
			}
			break;
		default:for(int i=1;i<=31;i++) {
			box[3].addItem(i);
		}
		}
	}
	}
	JTable table; JScrollPane sp;
	JLabel[] label; String[] lbl_Title= {"환불 번호","고객번호","환불방법","환불 날짜","환불 사유"};
	JTextField txt1,txt2;
	JComboBox[] box;
	String[] howToReturn= {"계좌이체","카드취소","포인트로 적립"};
	String[] return_reason= {"단순변심","교환","상품하자"};
	JButton[] btn; String[] btn_Title= {"삽입","조회","변경","삭제","리셋"};  
	JPanel center,south;
	JPanel[] pnl;
	JButton calendar;
	 JLabel clock;
	void makePanel() {
		
		center=new JPanel(); south=new JPanel();
		
		for(int i=0;i<btn_Title.length;i++) {
			south.add(btn[i]);
		}
			center.setLayout(new GridLayout(5,1));
		pnl=new JPanel[5];
		for(int i=0;i<pnl.length;i++) {
			pnl[i]=new JPanel();
		}
		pnl[0].add(label[0]);pnl[0].add(txt1);
		pnl[1].add(label[1]);pnl[1].add(txt2);
		pnl[2].add(label[2]);pnl[2].add(box[0]);
		pnl[3].add(label[3]);pnl[3].add(box[1]);
		pnl[3].add(box[2]);pnl[3].add(box[3]);
		
		pnl[3].add(calendar);
		pnl[4].add(label[4]);pnl[4].add(box[4]);
		for(int i=0;i<pnl.length;i++) {
			center.add(pnl[i]);
		}
		clock = new ClockMenu();//디지털 시계
		south.add(clock);//메뉴바에 메뉴를 붙임
		

	}
	void makeComboBox() {
		box = new JComboBox[5];//부서,년,월,일
		for(int i=0; i<box.length;i++) {
			box[i]=new JComboBox();
			box[i].addItem("선택하세요");
			switch(i) {
			case 0: 
			for(int j=0;j<howToReturn.length;j++) {
				box[i].addItem(howToReturn[j]);
			}
					break;
			case 1: 
				for(int j=2020;j>=1920;j--) {
					box[i].addItem(j);
				}
					break;
			case 2:	
				for(int j=1;j<=12;j++) {
					box[i].addItem(j);
				}
					break;
			case 3:
		
			break;
			case 4: 
			for(int j=0;j<howToReturn.length;j++) {
				box[i].addItem(return_reason[j]);
			}
			}
			box[i].addItemListener(this);
		}
		
	}
	void makeLabel() {
		label = new JLabel[5];
		for(int i=0;i<label.length;i++) {
			label[i]=new JLabel(lbl_Title[i]);
		}
	}
	void makeTextField() {
		txt1= new JTextField(20);
		txt2= new JTextField(20);
	}
	void makeButton() {
		calendar = new JButton("달력");
		calendar.addActionListener(this);
		btn = new JButton[5];
		for(int i=0;i<btn.length;i++) {
			btn[i]=new JButton(btn_Title[i]);
			btn[i].addActionListener(this);
		}
	}
	public ReturnSystem() throws HeadlessException {
//		super(arg0);
		this.setLayout(new BorderLayout());
		table=new JTable();
		sp= new JScrollPane(table);
//		mb = new JMenuBar();
//		this.setJMenuBar(mb);;//메뉴바를 윈도우에 붙임
		makeLabel();
		makeButton();
		makeTextField();
		makeComboBox();
		makePanel();
		add("Center",center);
		add("South",south);
		add("East",sp);
		table.addMouseListener(this);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setVisible(true);
//		this.setSize(900,500);
	}
//	public static void main(String[] args) {
//		new ReturnSystem("리턴 시스템");
//	}
}
