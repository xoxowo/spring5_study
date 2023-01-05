package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// chap05 @Component 속성값을 붙인 예
import org.springframework.stereotype.Component;
// 속성값을 붙이면 빈 이름은 속성값이 빈 이름이다. 
@Component("infoprinter")
public class MemberInfoPrinter {
	private MemberDao memDao;
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if (member == null) {
			System.out.println("데이터 없음");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}
	@Autowired
	@Qualifier("printer")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
}
