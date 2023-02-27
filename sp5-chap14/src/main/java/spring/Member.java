package spring;

import java.time.LocalDateTime;

public class Member {

	private Long id;
	private String email;
	private String password;
	private String name;
	private LocalDateTime registerDateTime;

	public Member(String email, String password, 
			String name, LocalDateTime regDateTime) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = regDateTime;
	}

	void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}

	// 46~50���� ���� 蹂�寃� 湲곕�� 援ы�� ���쇰�명�곌��쇰� �� 鍮�諛�踰��몄�� ��濡��� 鍮�諛�踰��몃�� ���λ�����.
	public void changePassword(String oldPassword, String newPassword) {
		if (!password.equals(oldPassword)) // 
			throw new WrongIdPasswordException(); // throw �ㅻ� 泥�由� 
		this.password = newPassword;
	}
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}

}