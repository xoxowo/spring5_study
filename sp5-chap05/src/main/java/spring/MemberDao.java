package spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
// chap05 @Component 클래스에 붙인 예
import org.springframework.stereotype.Component;
// 속성값이 없으면 빈 이름은 클래스의 이름의 첫 글자를 소문자로 바꾼 이름인 memberDao가 빈 이름이된다
@Component
public class MemberDao {

	private static long nextId = 0;

	private Map<String, Member> map = new HashMap<>();

	public Member selectByEmail(String email) {
		return map.get(email);
	}

	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}

	public void update(Member member) {
		map.put(member.getEmail(), member);
	}

	public Collection<Member> selectAll() {
		return map.values();
	}
}