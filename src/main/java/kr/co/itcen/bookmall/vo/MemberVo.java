package kr.co.itcen.bookmall.vo;

public class MemberVo {
	private String no, name, tel, email, passwd;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "고객번호=" + no + ", 고객명=" + name + ", 전화번호=" + tel + ", 이메일=" + email + ", 비밀번호=" + passwd;
	}
	
	
}
