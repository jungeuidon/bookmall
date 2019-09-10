package kr.co.itcen.bookmall.vo;

public class CartVo {
	private String no, book_no, cnt, all_price, user_no;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getBook_no() {
		return book_no;
	}

	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public String getall_price() {
		return all_price;
	}

	public void setall_price(String all_price) {
		this.all_price = all_price;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	@Override
	public String toString() {
		return "카트번호=" + no + ", 책번호=" + book_no + ", 책별수량=" + cnt + ", 총가격=" + all_price + ", 고객번호=" + user_no;
	}
	
}
