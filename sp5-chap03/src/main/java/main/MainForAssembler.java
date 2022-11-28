package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assembler.Assembler;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

public class MainForAssembler {

	public static void main(String[] args) throws IOException {
		// BufferedReader (== Scanner) System.in이니까 프로그램에서 사용자로부터 입력받기 위해 초기화
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) { // while 문이라 exit입력하기 전까지 계속 실행됨
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine(); //위 19행에서 초기화했으니까 여기서 입력받음
			if (command.equalsIgnoreCase("exit")) {	//사용자가 exit입력하면 종료됨
				System.out.println("종료합니다.");
				break;
			}
			if (command.startsWith("new ")) { //입력한 문자열이 NEW로 시작되면 processNewCommand() 실행 "new"뒤에 공백문자가 있음
				processNewCommand(command.split(" ")); // 이 command.split(" ") 코드는 command값이 "new a@a.com 이름 암호 암호"라면
				continue;									 // command.split(" ") -> {"new", "a@a.com", "이름", "암호", "암호"} 이런 결과를 만들어 processNewCommand 전달한다.
			} else if (command.startsWith("change ")) { //입력한 문자열이 change로 시작되면 processChangeCommand() 실행 "change로"뒤에 공백문자가 있음
				processChangeCommand(command.split(" "));
				continue;
			}
			printHelp(); //명령어를 잘못 입력한 경우 도움말 출력해주는 메서드 실행됨 
		}
	}

	private static Assembler assembler = new Assembler();

	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) {
			printHelp();
			return;
		}
		MemberRegisterService regSvc = assembler.getMemberRegisterService();
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.\n");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}

	private static void processChangeCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		ChangePasswordService changePwdSvc = 
				assembler.getChangePasswordService();
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
	}
}