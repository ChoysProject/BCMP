package team4App;

import java.util.Scanner;

import cys.MenuTwo;
import khj.MenuOne;


class ConsoleApp {
	ConsoleApp(){}
	Scanner sc = new Scanner(System.in);
	void input() {
		while(true) {
			menu();
			int i = sc.nextInt();
			boolean flag = false;
			switch(i) {
			case 1: MenuOne mo = new MenuOne();
					mo.pyramid();
					break;
			case 2: MenuTwo mt = new MenuTwo();
					mt.score();
					break;
			case 3: 
					break;
			case 4: 

					break;
			case 5: 
					break;
			case 9: flag = true; 
			}
			if(flag) {
				System.out.println("�̿��� �ּż� �����մϴ�.");
				break;
			}
		}
	}
	void menu() {//�޴� ���
		System.out.println("#########################");
		System.out.println("  1�� �ܼ� �ý��� ver1.0");
		System.out.println();
		System.out.println("# 1.�Ƕ�̵� �׸���");
		System.out.println("# 2.���� ����");
		System.out.println("# 3.���� ���߱�");
		System.out.println("# 4.����,����,�� ����");
		System.out.println("# 5.Ȧ¦ ����");
		System.out.println();
		System.out.println("# 9.����");
		System.out.println();
		System.out.println("���ϴ� ��ȣ�� �Է��ϰ�,");
		System.out.println("Enter�� ��������.");
	}
}

public class MainMenu {
	public static void main(String[] args) {
		ConsoleApp ca = new ConsoleApp();
		ca.input();
	}
}







