package khj;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class TestFrame extends JFrame {

	OrderManagement om = new OrderManagement();

	TestFrame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
		System.out.println();
		this.setLayout(new BorderLayout());
		this.add("Center", om);
		this.setTitle("��ǰ���� �ý���");
		this.setSize(900, 750);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestFrame("�׽�Ʈ");
	}
}