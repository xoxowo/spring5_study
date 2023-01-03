package spring;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;;

public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;

	// chap04 - 인자가 없는 기본생성자 추가
	public MemberListPrinter(){
	}
	
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m -> printer.print(m));
	}
	// chap04 새로 추가
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	@Autowired
	public void setMemberDaoPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

}
