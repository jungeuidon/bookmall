package kr.co.itcen.bookmall.vo;

public class CategoryVo {
	private String no, kind;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Override
	public String toString() {
		return "카테고리번호=" + no + ", 책종류=" + kind;
	}
	
	
}
