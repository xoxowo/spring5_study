package spring;

import java.time.LocalDateTime;

public class MemberRegisterService {
	private MemberDao memberDao;
	// ���깆��瑜� �듯�� ��議� 媛�泥대�� 二쇱�� 諛���
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	 public Long regist(RegisterRequest req) { //二쇱�� 諛��� ��議� 媛�泥댁�� 硫�����瑜� �ъ��
	 	Member member = memberDao.selectByEmail(req.getEmail());
	 	if (member != null) {
	 		throw new DuplicateMemberException("dup email" + req.getEmail());
	 	}
	 	Member newMember = new Member(
	 			req.getEmail(),req.getPassword(), req.getName(), LocalDateTime.now());
	 	memberDao.insert(newMember);
	 	return newMember.getId();
	 }
}
