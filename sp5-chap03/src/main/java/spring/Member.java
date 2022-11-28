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

	// 46~50행은 암호 변경 기능 구현 파라미터값으로 전 비밀번호와 새로운 비밀번호를 입력받는다.
	public void changePassword(String oldPassword, String newPassword) {
		if (!password.equals(oldPassword)) // 
			throw new WrongIdPasswordException(); // throw 오류 처리 
		this.password = newPassword;
	}

}