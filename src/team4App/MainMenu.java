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
				System.out.println("이용해 주셔서 감사합니다.");
				break;
			}
		}
	}
	void menu() {//메뉴 출력
		System.out.println("#########################");
		System.out.println("  1조 콘솔 시스템 ver1.0");
		System.out.println();
		System.out.println("# 1.피라미드 그리기");
		System.out.println("# 2.성적 관리");
		System.out.println("# 3.숫자 맞추기");
		System.out.println("# 4.가위,바위,보 게임");
		System.out.println("# 5.홀짝 게임");
		System.out.println();
		System.out.println("# 9.종료");
		System.out.println();
		System.out.println("원하는 번호를 입력하고,");
		System.out.println("Enter를 누르세요.");
	}
}

public class MainMenu {
	public static void main(String[] args) {
		ConsoleApp ca = new ConsoleApp();
		ca.input();
	}
}







